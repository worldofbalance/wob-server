/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import java.util.List;
import shared.metadata.Constants;
import shared.simulation.ParamValue;
import shared.simulation.SpeciesZoneType;

/**
 * SimJobSZT extends SpeciesZoneType for purposes of testing new species
 * parameter types and values without interfering with current functionality;
 * overrides current functionality. Obtains alternate initial parameter values
 * from SimTestNode, which may then be overridden by SimJobSZT methods.
 *
 * @author Justina
 */
public final class SimJobSZT extends SpeciesZoneType {

    private int speciesIndex;
    protected float distribution;
    //fields from new table, simTestNode, are stored in the following object
    //incl: metType, categoryId, perSpeciesBiomass, paramK, paramR, paramX overrides
    protected int metType;
    protected int categoryId;
    protected double aR;

    public SimJobSZT(String name, int nodeIndex, int speciesCount,
            double perSpeciesBiomass, double currentBiomass,
            SpeciesTypeEnum type, boolean useSimTestNodeVals) {
        super(name, nodeIndex, speciesCount, perSpeciesBiomass, currentBiomass, type);
        this.speciesIndex = speciesType.getID();

        //use settings in simTestNode for any values that have not been passed to
        //constructor (these overrides any values set by SpeciesZoneType/SpeciesType)
        SimTestNode simTestNode = speciesType.getSimTestNode(nodeIndex);
        this.metType = simTestNode.getMetType();
        this.categoryId = simTestNode.getCategoryId();
        if (this.perSpeciesBiomass == 0) {
            this.perSpeciesBiomass = simTestNode.getPerUnitBiomass();
        } else {
            this.perSpeciesBiomass = perSpeciesBiomass;
        }
        if (useSimTestNodeVals) {
            double testVal;
            //10/27/14, JTC, made correction to prevent param val from being overwritten
            //when test val was not configured (-1)
            //11/9/14, jtc, also, want dflt param values to remain as non-experimental
            //defaults; commenting out.
            testVal = simTestNode.getParamK();
            if (testVal != Constants.PARAM_INITVALUE) {
                this.paramK = testVal;
                //this.dfltK = paramK;
            }
            testVal = simTestNode.getParamR();
            if (testVal != Constants.PARAM_INITVALUE) {
                this.paramR = testVal;
                //this.dfltR = paramR;
            }
            testVal = simTestNode.getParamX();
            if (testVal != Constants.PARAM_INITVALUE) {
                this.paramX = testVal;
                //this.dfltX = paramX;
            }
            testVal = simTestNode.getAR();
            if (testVal != Constants.PARAM_INITVALUE) {
                this.aR = testVal;
            }
        }

        this.distribution = speciesType.getNodeDistribution(nodeIndex);
        //override default biomass (also sets count)
        this.calcSpeciesCount();
    }

    //10/27/14, jtc, make copy of SimJobSZT
    public SimJobSZT(SimJobSZT sourceSZT) {
        super(sourceSZT);

        this.speciesIndex = sourceSZT.speciesIndex;
        this.distribution = sourceSZT.distribution;
        this.metType = sourceSZT.metType;
        this.categoryId = sourceSZT.categoryId;
        this.aR = sourceSZT.aR;
    }

    /*4/17/14, JTC, Calculation derived from SimulationEngine.getBiomass() code,
     but including adjustment for node distribution */
    @Override
    public int calcSpeciesCount() {
        if (perSpeciesBiomass > 0 && distribution > 0) {
            return currentBiomass < 1 ? 0 : (int) Math.round(currentBiomass
                    / (perSpeciesBiomass * distribution));
        } else {
            return 0;
        }
    }

    public int getMetType() {
        return metType;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getAR() {
        return aR;
    }

    public void setMetType(int metType) {
        this.metType = metType;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setAR(double aR) {
        this.aR = aR;
    }

    /**
     * Calculates and stores paramY for all prey species that exist in current
     * ecosystem.
     *
     * @param job
     */
    public void setAllParamY(SimJob job) {
        double y;
        SimJobSZT preySzt;

        //set parameter value
        y = Constants.F_J[metType] * Constants.A_J[metType] / Constants.A_T[metType];

        //loop through all of this species' prey
        for (Integer preyId : speciesType.getPreyIDs()) {
            //is Id'd prey in current ecosystem?
            if ((preySzt = job.getSpeciesZoneBySpeciesId(preyId)) == null) {
                continue;
            }
            //create ParamValue and store
            this.setParamY(new ParamValue(preySzt.getNodeIndex(), y));
        }
    }

    /*the following two methods are used by separate SimAnalyzer (getTrophicGroup()
     getDietGroup*/
    /*an attempt at binning trophic level 
     first attempt: binning by halves */
    public String getTrophicGroup() {
        return String.format("%3.1f", (double) ((int) (trophicLevel * 2.0)) / 2.0);
    }

    /*an attempt to identify "competitor" species by diet and category
     */
    public String getDietGroup() {
        return String.valueOf(categoryId).concat(
                String.valueOf(speciesType.getDietType()));
    }
}
