package atn;

import metadata.Constants;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import simulation.simjob.SimJobSZT;

import java.util.ArrayList;
import java.util.Map;

/**
 * Implements the ATN model differential equations in a form usable by the Apache Commons Math integrators.
 *
 * @author Ben Saylor
 * @see <a href="https://commons.apache.org/proper/commons-math/userguide/ode.html">The Apache Commons Math ode package documentation</a>
 */
public class ATNEquations implements FirstOrderDifferentialEquations {

    private int numSpecies;  // Number of species
    private int[] nodeID;    // Node ID of each species

    // Organism type of each species (Constants.ORGANISM_TYPE_PLANT or Constants.ORGANISM_TYPE_ANIMAL)
    private int[] organismType;

    private ArrayList<Integer> producers;  // i/j indices of producers in parameter arrays
    private ArrayList<Integer> consumers;  // i/j indices of consumers in parameter arrays

    private ArrayList<ArrayList<Integer>> predatorsOf;  // predatorsOf[i] is the list of i/j indices of predators of species i
    private ArrayList<ArrayList<Integer>> preyOf;       // preyOf[i] is the list of i/j indices of prey of species i

    // ATN model parameters: species-level
    private double[] x;  // Mass-specific metabolic rate
    private double[] r;  // Maximum mass-specific growth rate
    private double[] k;  // Carrying capacity

    // ATN model parameters: link-level
    private double[][] y;  // Maximum ingestion rate
    private double[][] d;  // Predator interference
    private double[][] q;  // Functional response control parameter
    private double[][] a;  // Relative half saturation density
    private double[][] b0; // Half saturation density
    private double[][] e;  // Assimilation efficiency

    // ATN model intermediate calculations
    private double[][] F;  // Functional response

    public ATNEquations(SimJobSZT[] speciesZoneTypes,
                        Map<Integer, NodeRelationships> nodeRelationships,
                        LinkParams linkParams) {

        numSpecies = speciesZoneTypes.length;

        // Build list of node IDs
        nodeID = new int[numSpecies];
        for (int i = 0; i < numSpecies; i++) {
            nodeID[i] = speciesZoneTypes[i].getNodeIndex();
        }

        // Build lists of producers and consumers
        organismType = new int[numSpecies];
        producers = new ArrayList<>();
        consumers = new ArrayList<>();
        for (int i = 0; i < numSpecies; i++) {
            organismType[i] = speciesZoneTypes[i].getSpeciesType().getOrganismType();
            if (organismType[i] == Constants.ORGANISM_TYPE_PLANT) {
                producers.add(i);
            } else {
                consumers.add(i);
            }
        }

        // Build lists of predators and prey for each species
        predatorsOf = new ArrayList<>(numSpecies);
        preyOf = new ArrayList<>(numSpecies);
        for (int i = 0; i < numSpecies; i++) {
            ArrayList<Integer> predatorsOfI = new ArrayList<>();  // predators of i
            ArrayList<Integer> preyOfI = new ArrayList<>();       // prey of i
            predatorsOf.add(predatorsOfI);
            preyOf.add(preyOfI);
            NodeRelationships relationships = nodeRelationships.get(nodeID[i]);

            // For each other species j, update the predators and prey lists of i
            // (Do not update the predators and prey lists of j;
            // they will be updated when j comes around as i, because nodeRelationships is symmetrical.
            for (int j = 0; j < numSpecies; j++) {
                String relationshipToJ = relationships.getReln(nodeID[j]);
                switch (relationshipToJ) {
                    case "d":  // i predator of j
                        preyOfI.add(j);
                        break;
                    case "b":  // i and j predate on each other
                    case "c":  // i==j (cannibal)
                        predatorsOfI.add(j);
                        preyOfI.add(j);
                        break;
                    case "y":  // i prey of j
                        predatorsOfI.add(j);
                        break;
                }
            }
        }

        // Initialize parameter arrays
        x = new double[numSpecies];
        r = new double[numSpecies];
        k = new double[numSpecies];
        y = new double[numSpecies][numSpecies];
        d = new double[numSpecies][numSpecies];
        q = new double[numSpecies][numSpecies];
        a = new double[numSpecies][numSpecies];
        b0 = new double[numSpecies][numSpecies];
        e = new double[numSpecies][numSpecies];

        // Read ATN parameters from speciesZoneTypes and linkParams
        for (int i = 0; i < numSpecies; i++) {

            // Read species-level parameters
            SimJobSZT szt = speciesZoneTypes[i];
            x[i] = szt.getParamX();
            r[i] = szt.getParamR();
            k[i] = szt.getParamK() / Constants.BIOMASS_SCALE;

            // Read link-level parameters (note: link-level parameters are currently the same for all species)
            for (int j = 0; j < numSpecies; j++) {
                y[i][j] = linkParams.getParamY();
                d[i][j] = linkParams.getParamD();
                q[i][j] = linkParams.getParamQ();
                a[i][j] = linkParams.getParamA();
                b0[i][j] = linkParams.getParamB0();

                // Assimilation efficiency of predator j depends on whether prey i is a plant or animal
                e[j][i] = (organismType[i] == Constants.ORGANISM_TYPE_PLANT)
                        ? linkParams.getParamEPlant()
                        : linkParams.getParamEAnimal();
            }
        }

        // Initialize functional response matrix
        F = new double[numSpecies][numSpecies];
    }

    @Override
    public int getDimension() {
        return numSpecies;
    }

    /**
     * The equations come from section 2.1 of Justina Cotter's master's thesis.
     *
     * @param t Time
     * @param B Biomass of each species at time t
     * @param BDot Output: derivative of biomass of each species at time t
     */
    @Override
    public void computeDerivatives(double t, double[] B, double[] BDot) {

        // Compute functional response values
        for (int i : consumers) {
            for (int j : preyOf.get(i)) {
                double numerator = Math.pow(B[j] / b0[i][j], 1 + q[i][j]);
                double denominator = 1 + d[i][j] * B[i];
                for (int k : preyOf.get(i)) {
                    // FIXME: this is the same as the numerator calculation; factor it out
                    denominator += Math.pow(B[k] / b0[i][k], 1 + q[i][k]);
                }
                F[i][j] = numerator / denominator;
            }
        }

        // Compute derivatives for producers
        for (int i : producers) {
            double G = 1 - B[i] / k[i];
            BDot[i] = r[i] * B[i] * G - x[i] * B[i];
            for (int j : predatorsOf.get(i)) {
                BDot[i] -= x[j] * y[j][i] * B[j] * F[j][i] / e[j][i];
            }
        }

        // Compute derivatives for consumers
        for (int i : consumers) {
            BDot[i] = -x[i] * B[i];
            for (int j : preyOf.get(i)) {
                // FIXME: Factor this out to avoid recomputation
                BDot[i] += x[i] * y[i][j] * B[i] * F[i][j];
            }
            for (int j : predatorsOf.get(i)) {
                // FIXME: this is the same as the predator loop above - factor it out
                BDot[i] -= x[j] * y[j][i] * B[j] * F[j][i] / e[j][i];
            }
        }
    }
}
