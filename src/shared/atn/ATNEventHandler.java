package shared.atn;

import org.apache.commons.math3.ode.events.EventHandler;

import java.util.ArrayList;

/**
 * Handler for discrete events encountered during integration of ATNEquations.
 * It stops the integration when the system reaches a steady state, such as:
 * - All species are extinct
 * - Consumers are extinct and producers have reached carrying capacity
 * - All derivatives are effectively 0
 *
 * @author Ben Saylor
 */
public class ATNEventHandler implements EventHandler {

    public final double ABS_RELATIVE_DERIVATIVE_THRESHOLD = 1e-10;

    private ATNEquations ode;              // The equations being integrated
    private int numSpecies;                // Number of species
    private ArrayList<Integer> producers;  // i/j indices of producers in parameter arrays
    private ArrayList<Integer> consumers;  // i/j indices of consumers in parameter arrays

    private double[] BDot;                 // Derivative of biomass of each species at time t
    private double maxBiomass;             // Maximum biomass of a species at time t
    private double maxAbsRelDerivative;    // Maximum absolute value of a derivative relative to biomass at time t

    private double timeStopped = -1;       // Time at which the integration was stopped
    public enum EventType {
        NONE,
        UNKNOWN_EVENT,
        TOTAL_EXTINCTION,
        CONSTANT_BIOMASS_PRODUCERS_ONLY,
        CONSTANT_BIOMASS_WITH_CONSUMERS,
        OSCILLATING_STEADY_STATE
    }
    private EventType stopEvent = EventType.NONE;

    public ATNEventHandler(ATNEquations ode) {
        this.ode = ode;
        producers = ode.getProducers();
        consumers = ode.getConsumers();
        BDot = new double[ode.getDimension()];
    }

    @Override
    public void init(double t0, double[] y0, double t) {
        timeStopped = -1;
    }

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
        // When the derivatives are effectively 0, the system is in a steady state
        maxBiomass = 0;
        maxAbsRelDerivative = 0;
        ode.computeDerivatives(t, Bt, BDot);  // FIXME: This must be a recomputation - how to avoid?
        for (int i = 0; i < Bt.length; i++) {
            maxBiomass = Math.max(maxBiomass, Bt[i]);
            double absRelDerivative = Bt[i] == 0 ? 0 : Math.abs(BDot[i] / Bt[i]);
            maxAbsRelDerivative = Math.max(maxAbsRelDerivative, Math.abs(absRelDerivative));
        }

        return Math.min(maxBiomass - ATNEquations.EXTINCT, maxAbsRelDerivative - ABS_RELATIVE_DERIVATIVE_THRESHOLD);
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
        if (maxBiomass <= ATNEquations.EXTINCT) {
            stopEvent = EventType.TOTAL_EXTINCTION;
        } else if (maxAbsRelDerivative <= ABS_RELATIVE_DERIVATIVE_THRESHOLD) {
            // Check if any consumers are still alive
            boolean consumerAlive = false;
            for (int i : consumers) {
                if (Bt[i] > ATNEquations.EXTINCT) {
                    consumerAlive = true;
                    break;
                }
            }
            if (consumerAlive) {
                stopEvent = EventType.CONSTANT_BIOMASS_WITH_CONSUMERS;
            } else {
                stopEvent = EventType.CONSTANT_BIOMASS_PRODUCERS_ONLY;
            }
        } else {
            stopEvent = EventType.UNKNOWN_EVENT;
        }
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

    /**
     * Check whether the integration was stopped due to an event.
     * @return true if the integration was stopped
     */
    public boolean integrationWasStopped() {
        return timeStopped != -1;
    }

    public EventType getStopEvent() {
        return stopEvent;
    }
}
