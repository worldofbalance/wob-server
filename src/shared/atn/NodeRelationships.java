/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.atn;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author justinacotter
 */
class Relationship {

    private int nodeA;
    private int nodeB;
    private String reln;
    private int distance;
    private int pathCnt;

    protected Relationship(int nodeA, int nodeB, String reln, int distance, int pathCnt) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.reln = reln;
        this.distance = distance;
        this.pathCnt = pathCnt;
    }

    protected String getReln() {
        return reln;
    }

    protected int getDistance() {
        return distance;
    }

    protected int getPathCnt() {
        return pathCnt;
    }
}

public class NodeRelationships {

    private int nodeA;
    private int preyCnt = 0;
    private Map<Integer, Relationship> relationships;  //key = nodeB

    public NodeRelationships(int nodeA) {
        this.nodeA = nodeA;
        relationships = new HashMap<>();
    }

    public void addRelationship(int nodeB, String reln, int distance, int pathCnt) {
        Relationship rs = new Relationship(this.nodeA, nodeB, reln, distance, pathCnt);
        relationships.put(nodeB, rs);
        switch (reln) {
            case "d":  //a predator of b
            case "b":  //species both predate each other
            case "c":  //cannibal
                preyCnt++;
                break;
            case "y":
            default:
                break;
        }

    }

    public String getReln(int nodeB) {
        return relationships.get(nodeB).getReln();
    }

    public int getDistance(int nodeB) {
        return relationships.get(nodeB).getDistance();
    }

    public int getPathCnt(int nodeB) {
        return relationships.get(nodeB).getPathCnt();
    }

    public int getPreyCnt() {
        return preyCnt;
    }
}
