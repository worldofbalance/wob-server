/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.simulation;

/**
 *
 * @author justinacotter
 * 
 * 1/25/15, JTC SimJob needs access to both of these IDs to delete after 
 * simulation complete; current SimulationEngine does not allow this.
 * 
 */
public class SimulationIds {
    protected String manipId;
    protected String netId;
    
    public SimulationIds() {
        
    }
    
    public SimulationIds(String manipId, String netId) {
        this.manipId = manipId;
        this.netId = netId;
    }
    
    public String getManipId () {
        return manipId;
    }
    
    public void setManipId (String manipId) {
        this.manipId = manipId;
    }

    public String getNetId () {
        return netId;
    }
    
    public void setNetId (String netId) {
        this.netId = netId;
    }

}
