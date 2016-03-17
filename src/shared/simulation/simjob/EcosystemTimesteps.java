/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author justinacotter
 */
public class EcosystemTimesteps {

    Map<Integer, NodeTimesteps> timestepMap;
    List<Integer> nodeList;

    public EcosystemTimesteps() {
        timestepMap = new HashMap<Integer, NodeTimesteps>();
        nodeList = new ArrayList<Integer>();
    }

    public Map<Integer, NodeTimesteps> getTimestepMap() {
        return timestepMap;
    }

    public Collection<NodeTimesteps> getTimestepMapValues() {
        return timestepMap.values();
    }

    public void putNodeTimesteps(int node, NodeTimesteps timesteps) {
        timestepMap.put(node, timesteps);
        if (!nodeList.contains(node)) {
            nodeList.add(node);
        }
    }

    public NodeTimesteps getNodeTimesteps(int node) {
        return timestepMap.get(node);
    }
    
    public List<Integer> getNodeList() {
        return nodeList;
    }

    public int getTimesteps() {
        return timestepMap.values().iterator().next().getTimesteps();
    }

    //get count of active species at specified timestep
    public int countActiveSpecies(int timestep) {
        int cnt = 0;
        for (NodeTimesteps nts : timestepMap.values()) {
            if (nts.getBiomass(timestep) > 0) {
                cnt++;
            }
        }
        return cnt;
    }
    
    //sum biomass' of all nodes in list at specified timestep
    public double getNodeListTotalBiomass(int timestep,
            List<Integer> nodeList) {
        double totalBM = 0;

        for (Integer nodeId : nodeList) {
            if (timestepMap.get(nodeId) == null) {
                continue;
            }
            totalBM += timestepMap.get(nodeId).getBiomass(timestep);
        }
        return totalBM;
    }

    //avg biomass' of all nodes in list
    public double getNodeListAvgBiomass(List<Integer> nList) {
        double avgBM = 0;
        int nodeCnt = 0;

        for (Integer nodeId : nList) {
            if (timestepMap.get(nodeId) == null) {
                continue;
            }
            avgBM += timestepMap.get(nodeId).getAvgBiomass();
            nodeCnt++;
        }
        if (nodeCnt > 0) {
            return avgBM / (double) nodeCnt;
        } else {
            return 0;
        }
    }

    //determine avg biomass percentile rank of requested node
    public double avgBiomassPercentile (int node) {
        List<Double> sortedBMs = new ArrayList<Double>();
        
        //created ordered list of avg Biomasses
        for (NodeTimesteps nts : timestepMap.values()) {
            sortedBMs.add(nts.getAvgBiomass());
        }
        //sortedBMs.sort(null);
        Collections.sort(sortedBMs);
        int order = 0;
        double targetBM = timestepMap.get(node).getAvgBiomass();
        for (Double bm : sortedBMs) {
            if (bm >= targetBM) {
                break;
            }
            order++;
        }
        
        if (sortedBMs.size() > 0) {
            order++;  //adjust for 0 start
            return (double) order / (double) sortedBMs.size();
        } else {
            return 0;
        }
    }

    //calculate score for converge game against the target ecosystem
    public int calculateConvergeScore(EcosystemTimesteps target) {
        double score = 0.0f;
        
        for (Integer nodeId : nodeList) {
            score += timestepMap.get(nodeId).
                    AvgDiffTimesteps (target.getTimestepMap().get(nodeId));
        }
        return (int) Math.round(score);
    }

}
