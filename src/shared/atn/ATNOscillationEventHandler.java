package shared.atn;

import org.apache.commons.math3.ode.events.EventHandler;

import java.util.Arrays;

/**
 * Detects an oscillating steady state and stops the integration when it occurs.
 * This steady state is identified based on the observation that
 * if the biomass state vector returns a value it held previously,
 * then it will return to that state again and again in a periodic pattern.
 *
 * At the start of integration, we take a snapshot of the state vector.
 * If the current biomass reaches that state again a minimum number of times,
 * and the biomasses are exhibiting an oscillating pattern,
 * then an oscillating steady state has been reached and we stop the integration.
 *
 * @author Ben Saylor
 */
public class ATNOscillationEventHandler implements EventHandler {

    public final double RELATIVE_ERROR_TOLERANCE = 0.01;    // Maximum relative error to accept matching biomass state
    public final double REQUIRED_MATCHING_STATE_COUNT = 3;  // Number of state matches required before stopping integration

    private ATNEquations ode;  // Needed for obtaining current derivatives

    private double[] biomassSnapshot;   // Snapshot of biomass state at start of current integration
    private double biomassSnapshotSum;  // Sum of the biomass snapshot
    private double biomassSum;          // Sum of the current biomass state

    private double[] absoluteError;  // Error between current state and snapshot state
    private double[] relativeError;  // Relative error between current state and snapshot state

    private double[] minDerivative;  // Minimum derivatives computed since start of current integration
    private double[] maxDerivative;  // Maximum derivatives computed since start of current integration

    private int numOscillating;  // Number of species with oscillating biomass

    private int matchingStateCount = 0;  // Number of times the current biomass state has matched the snapshot

    private double timeStopped = -1;  // Time at which the integration was stopped

    private ATNEventHandler.EventType stopEvent = ATNEventHandler.EventType.NONE;

    public ATNOscillationEventHandler(ATNEquations ode) {
        this.ode = ode;
    }

    /**
     * Called at the start of an integration.
     * Note that this is not necessarily the start of a simulation.
     *
     * @param t0 Integration start time (not 0 unless this is the start of the simulation)
     * @param B0 Biomass of each species at time t0
     * @param t Target time for the integration
     */
    @Override
    public void init(double t0, double[] B0, double t) {

        // Take a snapshot of the biomass at the start of integration
        biomassSnapshot = Arrays.copyOf(B0, B0.length);
        biomassSnapshotSum = 0;
        for (double b : B0) {
            biomassSnapshotSum += b;
        }

        absoluteError = new double[B0.length];
        relativeError = new double[B0.length];

        minDerivative = new double[B0.length];
        maxDerivative = new double[B0.length];
        for (int i = 0; i < B0.length; i++) {
            minDerivative[i] = Double.POSITIVE_INFINITY;
            maxDerivative[i] = Double.NEGATIVE_INFINITY;
        }

        numOscillating = 0;
        matchingStateCount = 0;
    }

    /**
     * Compute the switching function.
     * When the sign of this continuous function changes,
     * an event is triggered and handled by eventOccurred().
     *
     * This switching function crosses zero when the current total biomass
     * equals the total biomass at the start of integration (the biomass "snapshot").
     * g() always crosses zero where the current biomass state equals the snapshot;
     * however, it can cross zero at other times, too,
     * so we check for a true match in eventOccurred().
     *
     * @param t  Time
     * @param Bt Biomass of each species at time t
     * @return the value of the switching function
     */
    @Override
    public double g(double t, double[] Bt) {

        // Compute the current total biomass
        // and the min and max derivatives since the start of the integration, which we'll use later.
        double[] currentDerivatives = ode.getCurrentDerivatives();
        biomassSum = 0;
        for (int i = 0; i < Bt.length; i++) {
            minDerivative[i] = Math.min(minDerivative[i], currentDerivatives[i]);
            maxDerivative[i] = Math.max(maxDerivative[i], currentDerivatives[i]);
            biomassSum += Bt[i];
        }

        return biomassSnapshotSum - biomassSum;
    }

    /**
     * Called when the switching function crosses zero.
     * Stops the integration if:
     * - the current biomass state equals the snapshot within a tolerance, and
     * - at least one species is oscillating, and
     * - those two conditions have been met a minimum number of times since the start of the integration.
     *
     * @param t Time
     * @param Bt Biomass of each species at time t
     * @param increasing If true, g() is increasing at time t
     * @return Action.STOP if the integration should be stopped; Action.CONTINUE otherwise
     */
    @Override
    public Action eventOccurred(double t, double[] Bt, boolean increasing) {

        numOscillating = 0;

        for (int i = 0; i < Bt.length; i++) {

            // If the biomass of species i is not within the error tolerance,
            // don't stop the integration.
            absoluteError[i] = Math.abs(Bt[i] - biomassSnapshot[i]);
            relativeError[i] = absoluteError[i] / biomassSnapshot[i];
            if (relativeError[i] > RELATIVE_ERROR_TOLERANCE) {
                return Action.CONTINUE;
            }

            // Count the number of oscillating species,
            // "oscillating" defined very generally as having had both a negative and a positive derivative
            // since the start of the current integration.
            if (minDerivative[i] < 0 && maxDerivative[i] > 0) {
                numOscillating++;
            }
        }

        // At this point, all biomasses match the snapshot within RELATIVE_ERROR_TOLERANCE.

        if (numOscillating > 0) {
            // At least one species is oscillating; count this as a matching state
            matchingStateCount++;

            if (matchingStateCount == REQUIRED_MATCHING_STATE_COUNT) {
                // We've seen enough repeated matching states to be sure a steady state has been reached,
                // so stop the integration
                timeStopped = t;
                stopEvent = ATNEventHandler.EventType.OSCILLATING_STEADY_STATE;
                //System.err.println("\nATNOscillationEventHandler: stopping integration at t = " + t);
                return Action.STOP;
            } else {
                // We need to keep watching for more matching states
                return Action.CONTINUE;
            }
        } else {
            // No species are oscillating; do not count this as a matching state
            return Action.CONTINUE;
        }
    }

    @Override
    public void resetState(double t, double[] y) {
    }

    /**
     * Get the time at which the integration was stopped due to reaching an oscillating steady state.
     *
     * @return the time at which the integration was stopped
     */
    public double getTimeStopped() {
        return timeStopped;
    }

    /**
     * Check whether the integration was stopped due to detection of an oscillating steady state.
     *
     * @return true if the integration was stopped
     */
    public boolean integrationWasStopped() {
        return timeStopped != -1;
    }

    public ATNEventHandler.EventType getStopEvent() {
        return stopEvent;
    }

    /**
     * Get the number of species that have had both positive and negative derivatives
     * since the start of the integration. The value is only meaningful
     * if this event handler has just stopped the integration.
     *
     * @return the number of species with oscillating biomass
     */
    public int getNumOscillating() {
        return numOscillating;
    }

}