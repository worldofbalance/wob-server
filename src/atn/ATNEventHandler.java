package atn;

import org.apache.commons.math3.ode.events.EventHandler;

import java.util.ArrayList;

/**
 * Handler for discrete events encountered during integration of ATNEquations.
 * It stops the integration when the system reaches a steady state, such as:
 * - All species are extinct
 * - Consumers are extinct and producers have reached carrying capacity
 *
 * @author Ben Saylor
 */
public class ATNEventHandler implements EventHandler {

    public final double MAX_ABS_DERIVATIVE_THRESHOLD = 1e-20;

    private ATNEquations ode;              // The equations being integrated
    private int numSpecies;                // Number of species
    private ArrayList<Integer> producers;  // i/j indices of producers in parameter arrays
    private ArrayList<Integer> consumers;  // i/j indices of consumers in parameter arrays

    private double[] BDot;                 // Derivative of biomass of each species at time t
    private double maxBiomass;             // Maximum biomass of a species at time t
    private double maxAbsDerivative;       // Maximum absolute value of a derivative at time t

    private double timeStopped = -1;       // Time at which the integration was stopped

    public ATNEventHandler(ATNEquations ode) {
        this.ode = ode;
        producers = ode.getProducers();
        consumers = ode.getConsumers();
        BDot = new double[ode.getDimension()];
    }

    @Override
    public void init(double t0, double[] y0, double t) { }

    /**
     * Compute the switching function.
     * When the sign of this continuous function changes,
     * an event is triggered and handled by eventOccurred().
     *
     * @param t Time
     * @param Bt Biomass of each species at time t
     * @return the value of the switching function
     */
    @Override
    public double g(double t, double[] Bt) {

        // When maxBiomass goes below extinction threshold, all species are extinct
        // When the maximum absolute value of the derivative goes below a threshold, the system is in a steady state
        maxBiomass = 0;
        maxAbsDerivative = 0;
        ode.computeDerivatives(t, Bt, BDot);  // FIXME: This must be a recomputation - how to avoid?
        for (int i = 0; i < Bt.length; i++) {
            maxBiomass = Math.max(maxBiomass, Bt[i]);
            maxAbsDerivative = Math.max(maxAbsDerivative, Math.abs(BDot[i]));
        }

        return Math.min(maxBiomass - ATNEquations.EXTINCT, maxAbsDerivative - MAX_ABS_DERIVATIVE_THRESHOLD);
    }

    /**
     * Handle an event and stop the integration.
     * @param t Time
     * @param Bt Biomass of each species at time t
     * @param increasing Must be set to false using an EventFilter
     * @return
     */
    @Override
    public EventHandler.Action eventOccurred(double t, double[] Bt, boolean increasing) {
        timeStopped = t;
        return Action.STOP;
    }

    @Override
    public void resetState(double t, double[] y) { }

    /**
     * Get the time at which the integration was stopped due to reaching a steady state.
     * @return the time at which the integration was stopped
     */
    public double getTimeStopped() {
        return timeStopped;
    }
}
