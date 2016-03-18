/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.simulation.simjob;

import java.util.Comparator;
import shared.simulation.SpeciesZoneType;

/**
 *
 * @author justinacotter
 * 
 * Class to compare SpeciesZoneType objects for sorting
 * (comparison based on int nodeIndex)
 */
public class SZTComparator implements Comparator<SpeciesZoneType> {
    
    @Override
    public int compare (SpeciesZoneType szt1, SpeciesZoneType szt2) {
        return (szt1.getNodeIndex()<szt2.getNodeIndex() ? -1 : 
                (szt1.getNodeIndex()==szt2.getNodeIndex() ? 0 : 1));
    }
    
}
