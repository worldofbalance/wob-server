/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import shared.core.ServerResources;
import shared.db.ConsumeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shared.model.SpeciesType;

/**
 * Limits predator/prey information to current ecosystem
 *
 * @author justinacotter
 */
public class ConsumeMap {

    List<Integer> ecosysNodes; //used as index into Maps
    boolean[] hasLink;
    Map<Integer, List<Integer>> preyList;
    Map<Integer, List<Integer>> predList;

    public ConsumeMap(int[] nodeArray, int eco_type) throws SQLException {
        this.ecosysNodes = new ArrayList<Integer>();
        this.hasLink = new boolean[nodeArray.length];
        for (int i = 0; i < nodeArray.length; i++) {
            ecosysNodes.add(nodeArray[i]);
            hasLink[i] = false;
        }

        //get full predator/prey table with node IDs
        Map<Integer, List<Integer>> predToPreyNodeTable = convertSpeciesToNodes(
                ConsumeDAO.getPredatorToPreyTable(eco_type));
        this.preyList = populateMap(predToPreyNodeTable);

        //get full predator/prey table with node IDs
        Map<Integer, List<Integer>> preyToPredNodeTable = convertSpeciesToNodes(
                ConsumeDAO.getPreyToPredatorTable(eco_type));
        this.predList = populateMap(preyToPredNodeTable);

    }

    //parse list of connected nodes (pred or prey, depending) to extract
    //those in current ecosystem
    private Map<Integer, List<Integer>> populateMap(Map<Integer, List<Integer>> nodeTable) {
        Map<Integer, List<Integer>> connectedMap = new HashMap<Integer, List<Integer>>();

        //loop through nodes in list and add to map of nodes and their connections
        for (Integer nodeId : ecosysNodes) {
            //if node is in ecosystem, loop through connected nodes
            if (!ecosysNodes.contains(nodeId)) {
                continue;
            }
            List<Integer> connectedList = new ArrayList<Integer>();

            List<Integer> fullList = nodeTable.get(nodeId);
            //if node has any connections, process if those connections are in 
            //current ecosystem
            if (fullList != null) {
                for (Integer connectedId : fullList) {
                    //identify connected nodes that are in the current node list
                    if (ecosysNodes.contains(connectedId)) {
                        connectedList.add(connectedId);
                        hasLink[ecosysNodes.indexOf(nodeId)] = true;
                        hasLink[ecosysNodes.indexOf(connectedId)] = true;
                    }
                }
            }
            //stored connectedList (may be empty list)
            connectedMap.put(nodeId, connectedList);
        }  //end node list loop

        return connectedMap;
    }

    /*
     Given map of species and list of connected species, convert to map of nodes
     and connected nodes.  In actuality, although some plant species have multiple 
     species/nodes (e.g. species 1009, 1008, 1007 ALL "expand" to species 
     1002, 1003, 1004, 1007, which convert to nodes 2, 3, 4, 7; there is no 
     equivalent unique node for 1009, 1008, 1007), the consume table that is being used 
     to populate the species Table only uses the derivative (1-to-1) species/node 
     IDs, so SpeciesType.getNodeList() should return a single value.  
     Note that the node ID may differ from the species ID for any species, 
     including animals.
     */
    private Map<Integer, List<Integer>> convertSpeciesToNodes(
            Map<Integer, List<Integer>> speciesTable) {
        Map<Integer, List<Integer>> nodeTable = new HashMap<Integer, List<Integer>>();

        //loop through map of species
        for (Map.Entry<Integer, List<Integer>> speciesEntry : speciesTable.entrySet()) {
            SpeciesType idxST = ServerResources.getSpeciesTable().getSpecies(speciesEntry.getKey());

            //build list of connected nodes from list of connected species
            //loop through all connected species for index species
            List<Integer> connNodeList = new ArrayList<Integer>();
            for (Integer connSpeciesId : speciesEntry.getValue()) {
                SpeciesType connST = ServerResources.getSpeciesTable().getSpecies(connSpeciesId);

                //loop through all nodes for this connected species
                //(getNodeList should return a single value)
                for (Integer nodeId : connST.getNodeList()) {
                    connNodeList.add(nodeId);
                }
            }
            Collections.sort(connNodeList);

            //loop through all nodes that are part of index species, build map
            //of connected nodes
            //(getNodeList should return a single value.)
            for (Integer nodeId : idxST.getNodeList()) {
                nodeTable.put(nodeId, connNodeList);
            }
        }

        return nodeTable;
    }

    public List<Integer> getPreyList(int nodeId) {
        return preyList.get(nodeId);
    }

    public List<Integer> getPredList(int nodeId) {
        return predList.get(nodeId);
    }
    
    //note: toString currently only prints getPreyList
    @Override
    public String toString() {
        String stringRep = "node,link?,prey list\n";
        for (Integer nodeId : ecosysNodes) {
            stringRep = stringRep.concat(String.format("%4d,%s,%s\n", nodeId,
                    hasLink[ecosysNodes.indexOf(nodeId)] ? "x" : "-",
                    preyList.get(nodeId).toString().replace("[", "").replace("]", "")));
        }
        return (stringRep);
    }
}
