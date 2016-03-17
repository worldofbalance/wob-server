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
 * @author justinacotter
 */
public class ConsumeTable {

    int nodeCnt;
    List<Integer> nodeList;  //want list separate from map to maintain order
    boolean[] hasLink;
    Map<Integer, List<Integer>> preyIds;

    public ConsumeTable(int[] nodeArray, int eco_type) throws SQLException {
        this.nodeList = new ArrayList<Integer>();
        this.hasLink = new boolean[nodeArray.length];
        for (int i=0; i < nodeArray.length; i++) {
            nodeList.add(nodeArray[i]);
            hasLink[i] = false;
        }
        this.preyIds = new HashMap<Integer, List<Integer>>();
        //get full predator/prey table with node IDs
        Map<Integer, List<Integer>> predToPreyNodeTable = convertSpeciesToNodes(
                ConsumeDAO.getPredatorToPreyTable(eco_type));

        List<Integer> preyList;
        //loop through nodes in list and add to create map of preyIds
        for (Integer predId : nodeList) {
            preyList = new ArrayList<Integer>();
            
            //if node is a predator, loop through it's prey
            if ((predToPreyNodeTable.get(predId)) != null) {
                List<Integer> fullPreyList = predToPreyNodeTable.get(predId);
                for (Integer preyId : fullPreyList) {
                    //identify prey that are in the current node list
                    if (nodeList.contains(preyId)) {
                        preyList.add(preyId);
                        hasLink[nodeList.indexOf(predId)] = true;
                        hasLink[nodeList.indexOf(preyId)] = true;
                    }
                }
            }
            preyIds.put(predId, preyList);

        }
    }

    private Map<Integer, List<Integer>> convertSpeciesToNodes(Map<Integer, List<Integer>> predToPreySpeciesTable) {
        Map<Integer, List<Integer>> predToPreyNodeTable = new HashMap<Integer, List<Integer>>();
        //loop through all predator species
        for (Map.Entry<Integer, List<Integer>> predEntry : predToPreySpeciesTable.entrySet()) {
            SpeciesType predST = ServerResources.getSpeciesTable().getSpecies(predEntry.getKey());

            //loop through all nodes for predator (in reality, should only be one node)
            for (Integer predNodeId : predST.getNodeList()) {
                //loop through all prey species for current predator
                List<Integer> preyNodeList = new ArrayList<Integer>();
                for (Integer preySpeciesId : predEntry.getValue()) {
                    SpeciesType preyST = ServerResources.getSpeciesTable().getSpecies(preySpeciesId);

                    //loop through all nodes for prey
                    for (Integer preyNodeId : preyST.getNodeList()) {
                        if (!preyNodeList.contains(preyNodeId)) {
                            preyNodeList.add(preyNodeId);
                        }
                    }
                }
                Collections.sort(preyNodeList);
                if (!predToPreyNodeTable.containsKey(predNodeId)) {
                    predToPreyNodeTable.put(predNodeId, preyNodeList);
                }
            }
        }

        return predToPreyNodeTable;
    }

    public List<Integer> getPreyList (int nodeId) {
        return preyIds.get(nodeId);
    }
    public String toString() {
        String stringRep = "node,link?,prey list\n";
        for (Integer nodeId : nodeList) {
            stringRep = stringRep.concat(String.format("%4d,%s,%s\n", nodeId, 
                    hasLink[nodeList.indexOf(nodeId)] ? "x" : "-", 
                    preyIds.get(nodeId).toString().replace("[", "").replace("]", "")));
        }
        return (stringRep);
    }
}
