/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import java.util.Map;

/**
 *
 * @author justinacotter
 */
public class NodeTimesteps {

    protected int nodeId;
    protected double[] biomass;

    public NodeTimesteps(int nodeId, int count) {
        this.nodeId = nodeId;
        biomass = new double[count];
        for (int i = 0; i < count; i++) {
            biomass[i] = 0;
        }
    }

    public int getTimesteps() {
        return biomass.length;
    }

    public void setBiomass(int idx, double val) {
        biomass[idx] = val;
    }

    public double getBiomass(int idx) {
        return biomass[idx];
    }

    public int getNodeId() {
        return nodeId;
    }

    public double getAvgBiomass() {
        double avg = 0;
        for (double bm : biomass) {
            avg += bm;
        }
        if (biomass.length != 0) {
            return avg / (double) biomass.length;
        } else {
            return 0;
        }
    }

    //get average difference between current CSV object values and object "target" 
    //(using root mean square distrib)
    public double AvgDiffTimesteps(NodeTimesteps target) {
        double score = 0.0f;

        //for each timestep, calc different and add squared val to score total
        for (int i = 0; i < biomass.length; i++) {
            double diff = biomass[i] - target.getBiomass(i);
            score += diff * diff;
        }

        return Math.sqrt (score / (double) biomass.length);
    }

    public double[] getBiomassArray(){
    	return biomass;
    }
}
