package atn;

import org.apache.commons.math3.ode.sampling.FixedStepHandler;

/**
 * Fixed step handler for integrating ATNEquations.
 * Copies the integrator output into the biomass output array at a fixed time step.
 *
 * @author Ben Saylor
 */
public class ATNStepHandler implements FixedStepHandler {
    private int timestep;            // Current time step
    private double[][] outputArray;  // Biomass output array
    private double timeIntvl;        // Step size

    /**
     * Constructor.
     * @param outputArray biomass array with dimensions #timesteps x #species
     * @param timeIntvl step size
     */
    public ATNStepHandler(double[][] outputArray, double timeIntvl) {
        timestep = -1;
        this.outputArray = outputArray;
        this.timeIntvl = timeIntvl;
    }

    @Override
    public void init(double t0, double[] y0, double t) {
    }

    @Override
    public void handleStep (double t, double[] y, double[] yDot, boolean isLast) {
        timestep = (int) Math.round(t / timeIntvl);
        // Ensure we don't go past the last time step due to rounding error
        if (timestep < outputArray.length) {
            System.arraycopy(y, 0, outputArray[timestep], 0, outputArray[timestep].length);
        }
    }

    /**
     * Get the last time step for which handleStep() was called.
     * @return the last handled time step
     */
    public int getLastHandledTimestep() {
        return timestep;
    }
}
