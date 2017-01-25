/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shared.metadata.Constants;

/**
 * PathTable builds strings of paths (predator/prey relationships) between two
 * nodes. Path = Relationship between two nodes as defined by intermediate
 * predator/prey relationships; specific nodes not stored, just whether link is
 * from predator to prey ('d') or prey to predator ('y'). Simple: [5] is prey of
 * [59], path(5,59) = 'y' Simple: [5] is prey of [59], which is predator of [3],
 * path [5,3] = 'yd'.
 *
 * @author justinacotter
 */
public class PathTable {

    private List<Integer> nodeList;

    //what are pred/prey paths (connections, relationships) btwn nodes?
    private PathList[][] pathArray;  //strings of all paths btwn two nodes
    private String[][] minPath;          //closest path
    private Integer[][] minDist;            //minimum distance between two nodes
    private Integer[] linkCnt;               //number of nodes that index node is connected to
    private long timer;
    public static final String IS_CANNIBAL = "c";
    public static final String IS_PREY = "y";
    public static final String IS_PREDATOR = "d";
    public static final String IS_BOTH = "b";
    public static final boolean PP_ONLY = true;
    private static final int DEPTH_THRESHOLD = 4;  //need to restrict due to time complexity

    public PathTable(ConsumeMap consumeMap, int[] nodeArray, boolean ppOnly)
            throws SQLException {
        int nodeCnt = nodeArray.length;
        nodeList = new ArrayList<Integer>();
        pathArray = new PathList[nodeCnt][nodeCnt];
        minPath = new String[nodeCnt][nodeCnt];
        minDist = new Integer[nodeCnt][nodeCnt];
        linkCnt = new Integer[nodeCnt];
        timer = System.currentTimeMillis() / 60000;

        //initialize nodeList and pathArray
        for (int i = 0; i < nodeCnt; i++) {
            nodeList.add(nodeArray[i]);
            linkCnt[i] = 0;
            for (int j = 0; j < nodeCnt; j++) {
                pathArray[i][j] = new PathList();
                minDist[i][j] = 0;
                minPath[i][j] = "";
            }
        }

        //build paths for this node via recursive method findPaths
        for (Integer rootId : nodeList) {
            //if specified, only process primary producer
            if (ppOnly && rootId != Constants.PP_NODE_ID) {
                continue;
            }

            //add current node to list of visited nodes to prevent looping
            List<Integer> visited = new ArrayList<Integer>();
            int rootIdx = nodeList.indexOf(rootId);
            visited.add(rootId);

            findPaths(rootIdx, rootId, visited, consumeMap);

            //adjust cannibal relationship
            if (!pathArray[rootIdx][rootIdx].getPathList().isEmpty()
                    && pathArray[rootIdx][rootIdx].getPathI(0).toString().equals(IS_PREDATOR)) {
                pathArray[rootIdx][rootIdx].getPathI(0).setPath(IS_CANNIBAL);
            }

        }

        //determine link counts, min distances, minPath
        for (int i = 0; i < nodeCnt; i++) {
            for (int j = 0; j < nodeCnt; j++) {
                List<Path> pList = pathArray[i][j].getPathList();
                if (pList.isEmpty()) {
                    continue;
                }
                linkCnt[i]++;
                for (Path path : pList) {
                    //record min distance between the two nodes (i and j) and
                    //minimum path
                    String pathStr = path.toString();
                    int len = pathStr.length();
                    if (len == 0) {
                        continue;
                    }
                    //choose is shorter path than prev
                    if (minDist[i][j] == 0 || minDist[i][j] > len) {
                        minDist[i][j] = len;
                        minPath[i][j] = pathStr;
                        //if both immediate predator AND prey, flag as "BOTH"
                    } else if (len == 1 && minDist[i][j] == 1
                            && !minPath[i][j].equals(pathStr)) {

                        minPath[i][j] = IS_BOTH;
                    }
                    //otherwise: if same size, whichever was selected 1st remains
                    
                } //end for path

                //adjust path length for "self".  Implementation leaves blank
                //entry, which is not used.  (Also adjusts cannibal dist to 0)
                if (i == j) {
                    minDist[i][j] = 0;
                }
            }  //end for j
        } //end for i
    }

    /*recursive function to find all paths - drill down until no pred/prey
     are found (depth-first search).
     */
    private List<Path> findPaths(
            int rootIdx,
            int nodeId,
            List<Integer> visited,
            ConsumeMap consumeMap
    ) {
        //loop through nodes of predators and prey to find all paths
        List<Path> newPaths = new ArrayList<Path>();

        //do not look for paths longer than this threshold
        if (visited.size() > DEPTH_THRESHOLD) {
            return newPaths;
        }

        if ((System.currentTimeMillis() / 60000) != timer) {
            System.out.println("visited: " + visited.toString());
            timer = System.currentTimeMillis() / 60000;
        }

        //discover direct and indirect paths through direct predator and prey 
        //relationships
        if (!consumeMap.getPreyList(nodeId).isEmpty()) {
            newPaths.addAll(setSubPaths(
                    rootIdx,
                    nodeId,
                    visited,
                    consumeMap.getPreyList(nodeId),
                    IS_PREDATOR,
                    consumeMap
            ));
        }
        if (!consumeMap.getPredList(nodeId).isEmpty()) {
            newPaths.addAll(setSubPaths(
                    rootIdx,
                    nodeId,
                    visited,
                    consumeMap.getPredList(nodeId),
                    IS_PREY,
                    consumeMap
            ));
        }

        //Create new path specifying current node as end node in a relationship.
        //Empty string acts as placeholder.
        //As recursion 'retreats', path information will be prepended to this string
        //to build path between this node and rooting node.
        Path newPath = new Path("");
        int nodeIdx = nodeList.indexOf(nodeId);
        pathArray[rootIdx][nodeIdx].addPath(newPath);
        newPaths.add(newPath);
        return (newPaths);
    }

    //find paths from current node via list of directly connected nodes
    private List<Path> setSubPaths(
            int rootIdx,
            int nodeId,
            List<Integer> visited,
            List<Integer> connNodes,
            String relnType,
            ConsumeMap consumeMap
    ) {
        List<Path> subPaths = new ArrayList<Path>();

        //loop through list of connected nodes
        for (Integer connId : connNodes) {
            if (visited.contains(connId)) {  //cycle
                //check for cannibal exception (only do it for relnType = pred,
                //so that it doesn't create dup for redundant relnType = prey
                //only relevant at top level (visited == 1), otherwise a dead end
                //if already visited
                if (visited.size() == 1
                        && nodeId == connId //=rootIdx
                        && relnType.equals(IS_PREDATOR)) {
                    Path newPath = new Path("");
                    int nodeIdx = nodeList.indexOf(nodeId);
                    pathArray[nodeIdx][nodeIdx].addPath(newPath);
                    subPaths.add(newPath);
                }

                continue;
            }

            //identify paths for child node (recursive call)
            visited.add(connId);
            //System.out.println("visited: " + visited.toString());
            subPaths.addAll(
                    findPaths(rootIdx, connId, visited, consumeMap));
            visited.remove(connId);
        }

        //Add relationship from curr node to child node for all paths that
        //have been created so far
        int pathCnt = subPaths.size();
        for (int i = 0; i < pathCnt; i++) {
            subPaths.get(i).setPath(relnType + subPaths.get(i).toString());
        }

        return subPaths;
    }

    public int getLinkCntI(int nodeId) {
        int i = nodeList.indexOf(nodeId);
        return linkCnt[i];
    }

    public PathList getPathArrayIJ(int nodeId1, int nodeId2) {
        int i = nodeList.indexOf(nodeId1);
        int j = nodeList.indexOf(nodeId2);
        return pathArray[i][j];
    }

    @Override
    public String toString() {
        //create headers (2 lines)
        //
        String stringRep = ",,";
        stringRep = stringRep.concat("shortest path");
        for (Integer nodeList1 : nodeList) {
            stringRep = stringRep.concat(",");
        }
        stringRep = stringRep.concat("path length");
        for (Integer nodeList1 : nodeList) {
            stringRep = stringRep.concat(",");
        }
        stringRep = stringRep.concat("# paths");
        for (Integer nodeList1 : nodeList) {
            stringRep = stringRep.concat(",");
        }
        stringRep = stringRep.concat("\n");

        stringRep = stringRep.concat("node,#links,");
        //need nodeList 3x: once for min path, once for min distance, once for # paths
        String nodeListStr = "";
        for (Integer nodeId : nodeList) {
            nodeListStr = nodeListStr.concat(String.format("%d,", nodeId));
        }
        //add 3 instances
        for (int i = 0; i < 3; i++) {
            stringRep = stringRep.concat(nodeListStr);
        }
        //remove trailing comma and add line return
        stringRep = stringRep.substring(0, stringRep.length() - 1).concat("\n");

        //create rows and columns of output table
        for (Integer rowId : nodeList) {
            // root with row node and link count
            stringRep = stringRep.concat(String.format("%d,%d,", rowId,
                    linkCnt[nodeList.indexOf(rowId)]));

            //add shorted paths
            for (Integer colId : nodeList) {
                stringRep = stringRep.concat(String.format("%s,",
                        minPath[nodeList.indexOf(rowId)][nodeList.indexOf(colId)]));
            }

            //add shortest distances
            for (Integer colId : nodeList) {
                stringRep = stringRep.concat(String.format("%d,",
                        minDist[nodeList.indexOf(rowId)][nodeList.indexOf(colId)]));
            }

            //add # of paths
            for (Integer colId : nodeList) {
                int pathCnt = pathArray[nodeList.indexOf(rowId)][nodeList.indexOf(colId)].
                        getPathList().size();
                stringRep = stringRep.concat(String.format("%d,", pathCnt));
            }

            //replace terminal comma with a line return
            stringRep = stringRep.substring(0, stringRep.length() - 1).concat("\n");
        }

        return (stringRep);
    }
}
