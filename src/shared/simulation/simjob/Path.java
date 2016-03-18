/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

/**
 *class Path is created rather than using string so that updates by 
 * reference (pointer) can work through the recursion.  Allows paths to be
 * stored in end-use PathList Array while recursion passes the paths back
 * as non-pair-matched Lists.  Updates to List entries update the Array.

 * @author justinacotter
 */
public class Path {
    
    String path;
    
    public Path () {
        path = "";
    }
    
    public Path (String pth) {
        path = pth;
    }
    
    public void setPath (String pth) {
        path = pth;
    }
    
    public String getPath () {
        return path;
    }

    @Override
    public String toString () {
        return path;
    }
}
