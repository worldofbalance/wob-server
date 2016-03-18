/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import java.util.List;
import shared.util.CSVParser;

/**
 *
 * @author justinacotter
 */
public class ExtractCSVData {
    
    public ExtractCSVData () {
        
    }
    
    public static EcosystemTimesteps extractCSVData(String csv) {
        EcosystemTimesteps ecosysTimesteps = new EcosystemTimesteps();
        int nodeId, steps;
        String spNameNode;
        NodeTimesteps nodeTimesteps;

        csv = csv.replaceAll("Grains, seeds", "Grains and seeds");
        List<List<String>> dataSet = CSVParser.convertCSVtoArrayList(csv);
        //remove header lines
        while (!dataSet.isEmpty()) {
            //exit when first line of species data is found
            if (dataSet.get(0).get(0).contains("[")) {
                break;
            }
            dataSet.remove(0);
        }
        if (dataSet.isEmpty()) {
            return ecosysTimesteps;
        }

        //have problem with mismatched speciesInfo.size(); they SHOULD all be 
        //the same; therefore normalizing to use that of the first species 
        //listed (note: probably due to bug later found in createAndRumSim)
        steps = dataSet.get(0).size() - 1;  //first entry is node name/id        

        //loop through dataset
        for (List<String> speciesInfo : dataSet) {
            //exit after last line of species data
            if (!speciesInfo.get(0).contains("[")) {
                break;
            }
            spNameNode = speciesInfo.get(0);
            nodeId = Integer.valueOf(spNameNode.substring(
                    spNameNode.lastIndexOf("[") + 1,
                    spNameNode.lastIndexOf("]")));
            //System.out.printf("\n%s ", spNameNode);
            nodeTimesteps = new NodeTimesteps(nodeId, steps);
            ecosysTimesteps.putNodeTimesteps(nodeId, nodeTimesteps);
            for (int i = 0; i < steps; i++) {
                //make sure there are values for this timestep, o/w enter 0
                if ((i + 1) < speciesInfo.size()) {
                    //have to eliminate special characters (Java does not like
                    //return chars)
                    speciesInfo.set(i + 1, speciesInfo.get(i + 1).
                            replaceAll("\\r|\\n", ""));

                    if (speciesInfo.get(i + 1).isEmpty()) {
                        nodeTimesteps.setBiomass(i, 0);
                    } else {
                        nodeTimesteps.setBiomass(i,
                                Double.valueOf(speciesInfo.get(i + 1)));
                    }
                } else {
                    nodeTimesteps.setBiomass(i, 0);
                }
                //System.out.printf(">%d %s ", i, nodeTimesteps.getBiomass(i));
            }
        }
        return ecosysTimesteps;
    }    
}
