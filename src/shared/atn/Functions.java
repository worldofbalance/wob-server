/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.atn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.simulation.simjob.EcosystemTimesteps;
import shared.simulation.simjob.NodeTimesteps;
import shared.util.CSVParser;

/**
 *
 * @author justinacotter
 */
public class Functions {
    
    private static String lastCSVFilePath;

	//extract basic biomass information
    public static void extractCSVDataBasic (String csv,
            EcosystemTimesteps ecosysTimesteps
    ) {
        int nodeId, timesteps;
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
            return;
        }

        //have problem with mismatched csvLine.size(); they SHOULD all be 
        //the same; therefore normalizing to use that of the first species 
        //listed (note: probably due to bug later found in createAndRumSim)
        timesteps = dataSet.get(0).size() - 1;  //first entry is node name/id        

        //loop through dataset
        //3 charts: 0: biomass/time, 1: preylist, 2: relationship/distance
        int chart = 0;
        boolean empty = false;
        for (List<String> csvLine : dataSet) {
            //end chart when first blank line is reached
            if (csvLine.get(0).isEmpty()) {
                //if empty already flagged, keep looping
                if (empty == true) {
                    continue;
                }
                //if this is first empty, increment chart#
                empty = true;
                chart++;
                if (chart > 2) {
                    break;
                }
                continue;
            }
            empty = false;

            switch (chart) {
                case 0:    //biomass/time chart
                    spNameNode = csvLine.get(0);
                    nodeId = Integer.valueOf(spNameNode.substring(
                            spNameNode.lastIndexOf("[") + 1,
                            spNameNode.lastIndexOf("]")));
                    //System.out.printf("\n%s ", spNameNode);
                    nodeTimesteps = new NodeTimesteps(nodeId, timesteps);
                    for (int i = 0; i < timesteps; i++) {
                        //make sure there are values for this timestep, o/w enter 0
                        if ((i + 1) < csvLine.size()) {
                            //have to eliminate special characters (Java does not like
                            //return chars)
                            csvLine.set(i + 1, csvLine.get(i + 1).
                                    replaceAll("\\r|\\n", ""));

                            if (csvLine.get(i + 1).isEmpty()) {
                                nodeTimesteps.setBiomass(i, 0);
                            } else {
                                nodeTimesteps.setBiomass(i,
                                        Double.valueOf(csvLine.get(i + 1)));
                            }
                        } else {
                            nodeTimesteps.setBiomass(i, 0);
                        }
                        //System.out.printf(">%d %s ", i, nodeTimesteps.getBiomass(i));
                    }
                    ecosysTimesteps.putNodeTimesteps(nodeId, nodeTimesteps);
                    break;
                case 1:  //preylist chart (not used)
                    break;
                case 2:  //relationship/distance chart (not used)
                    break;
                default:
                    break;
            }
        }
    }

    //extract biomass and relationships
    public static void extractCSVDataRelns(String csv,
            EcosystemTimesteps ecosysTimesteps,
            Map<Integer, NodeRelationships> ecosysRelationships
    ) {
        int nodeId, timesteps;
        String spNameNode;
        NodeTimesteps nodeTimesteps;

        csv = csv.replaceAll("Grains, seeds", "Grains and seeds");
        List<List<String>> dataSet = CSVParser.convertCSVtoArrayList(csv);
        //remove header lines
        while (!dataSet.isEmpty()) {
            //exit when first line of species data is found
        	Object obj = dataSet.get(0).get(0);
            if (dataSet.get(0).get(0).contains("[")) {
                break;
            }
            dataSet.remove(0);
        }
        if (dataSet.isEmpty()) {
            return;
        }

        //have problem with mismatched csvLine.size(); they SHOULD all be 
        //the same; therefore normalizing to use that of the first species 
        //listed (note: probably due to bug later found in createAndRumSim)
        timesteps = dataSet.get(0).size() - 1;  //first entry is node name/id        

        //loop through dataset
        //3 charts: 0: biomass/time, 1: preylist, 2: relationship/distance
        int chart = 0, nodes = 0, relnOffset = 0, distOffset = 0, pathCntOffset = 0;
        List<Integer> sortedNodeList = null;
        boolean empty = false;
        boolean newChart = true;
        for (List<String> csvLine : dataSet) {
            //end chart when first blank line is reached
            if (csvLine.get(0).isEmpty()) {
                //if empty already flagged, keep looping
                if (empty == true) {
                    continue;
                }
                //if this is first empty, increment chart#
                empty = true;
                newChart = true;
                chart++;
                if (chart > 2) {
                    break;
                }
                continue;
            }
            empty = false;

            switch (chart) {
                case 0:    //biomass/time chart
                    spNameNode = csvLine.get(0);
                    nodeId = Integer.valueOf(spNameNode.substring(
                            spNameNode.lastIndexOf("[") + 1,
                            spNameNode.lastIndexOf("]")));
                    //System.out.printf("\n%s ", spNameNode);
                    nodeTimesteps = new NodeTimesteps(nodeId, timesteps);
                    for (int i = 0; i < timesteps; i++) {
                        //make sure there are values for this timestep, o/w enter 0
                        if ((i + 1) < csvLine.size()) {
                            //have to eliminate special characters (Java does not like
                            //return chars)
                            csvLine.set(i + 1, csvLine.get(i + 1).
                                    replaceAll("\\r|\\n", ""));

                            if (csvLine.get(i + 1).isEmpty()) {
                                nodeTimesteps.setBiomass(i, 0);
                            } else {
                                nodeTimesteps.setBiomass(i,
                                        Double.valueOf(csvLine.get(i + 1)));
                            }
                        } else {
                            nodeTimesteps.setBiomass(i, 0);
                        }
                        //System.out.printf(">%d %s ", i, nodeTimesteps.getBiomass(i));
                    }
                    ecosysTimesteps.putNodeTimesteps(nodeId, nodeTimesteps);
                    break;
                case 1:  //preylist chart (not used)
                    break;
                case 2:  //relationship/distance chart
                    //bypass first - header - line
                    if (newChart) {
                        sortedNodeList = new ArrayList<>(ecosysTimesteps.getNodeList());
                        Collections.sort(sortedNodeList);
                        nodes = sortedNodeList.size();
                        relnOffset = 2;  //offset in csvLine to 1st reln
                        distOffset = relnOffset + nodes;  //offset in csvLine to distance info
                        pathCntOffset = distOffset + nodes; //offset in csvLine to pathCnt info
                        newChart = false;
                        break;
                    }
                    int nodeA = Integer.valueOf(csvLine.get(0));
                    NodeRelationships nodeRelns = new NodeRelationships(nodeA);
                    for (int i = 0; i < nodes; i++) {
                        int nodeB = sortedNodeList.get(i);
                        String relnStr = csvLine.get(relnOffset + i);
                        int dist = Integer.valueOf(csvLine.get(distOffset + i));
                        int pathCnt = Integer.valueOf(csvLine.get(pathCntOffset + i));

                        nodeRelns.addRelationship(nodeB, relnStr, dist, pathCnt);
                    }
                    ecosysRelationships.put(nodeA, nodeRelns);
                    break;
                default:
                    break;
            }
        }
    }

    //open printstream
    public static PrintStream getPrintStream(
            String filename, 
            String destDir
    ) {
        final String name = filename, extension = ".csv";
        PrintStream ps = null;

        // Determine filename
        File dir = new File(destDir);
        dir.mkdirs();
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String fullname) {
                return fullname.startsWith(name)
                        && fullname.endsWith(extension);
            }
        });

        //check current files to find next number that has not been used
        String csvFilename = name;

        if (files.length > 0) {
            int[] temp = new int[files.length];

            for (int i = 0; i < temp.length; i++) {
                String lastFilename = files[i].replaceFirst(name, "").
                        replaceFirst("_", "");

                try {
                    temp[i] = Integer.parseInt(lastFilename.substring(
                            0, lastFilename.indexOf(extension)));
                } catch (NumberFormatException ex) {
                    temp[i] = 0;
                }
            }

            Arrays.sort(temp);

            csvFilename += "_" + (temp[temp.length - 1] + 1);
        }
        setLastCSVFilePath(destDir + csvFilename);
        csvFilename += extension;
        try {
            System.out.println(destDir + csvFilename);
            ps = new PrintStream(new FileOutputStream(
                    destDir + csvFilename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ATN.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return ps;
    }
    
    public static void setLastCSVFilePath(String filePath){
    	lastCSVFilePath = filePath;
    }
    
    public static String getLastCSVFilePath(){
    	return lastCSVFilePath;
    }

    public static void extractCSVBiomassData(String csv, EcosystemTimesteps ecosysTimesteps){
        int nodeId, timesteps;
        String spNameNode;
        NodeTimesteps nodeTimesteps;

        csv = csv.replaceAll("Grains, seeds", "Grains and seeds");
        List<List<String>> dataSet = CSVParser.convertCSVtoArrayList(csv);
        //remove header lines
        while (!dataSet.isEmpty()) {
            //exit when first line of species data is found
        	Object obj = dataSet.get(0).get(0);
            if (dataSet.get(0).get(0).contains("[")) {
                break;
            }
            dataSet.remove(0);
        }
        if (dataSet.isEmpty()) {
            return;
        }

        //have problem with mismatched csvLine.size(); they SHOULD all be 
        //the same; therefore normalizing to use that of the first species 
        //listed (note: probably due to bug later found in createAndRumSim)
        timesteps = dataSet.get(0).size() - 1;  //first entry is node name/id        

        //loop through dataset
        //3 charts: 0: biomass/time, 1: preylist, 2: relationship/distance
        int chart = 0, nodes = 0, relnOffset = 0, distOffset = 0, pathCntOffset = 0;
        List<Integer> sortedNodeList = null;
        boolean empty = false;
        boolean newChart = true;
        for (List<String> csvLine : dataSet) {
            //end chart when first blank line is reached
            if (csvLine.get(0).isEmpty()) {
                //if empty already flagged, keep looping
                if (empty == true) {
                    continue;
                }
                //if this is first empty, increment chart#
                empty = true;
                newChart = true;
                chart++;
                if (chart > 2) {
                    break;
                }
                continue;
            }
            empty = false;

            switch (chart) {
                case 0:    //biomass/time chart
                    spNameNode = csvLine.get(0);
                    nodeId = Integer.valueOf(spNameNode.substring(
                            spNameNode.lastIndexOf("[") + 1,
                            spNameNode.lastIndexOf("]")));
                    //System.out.printf("\n%s ", spNameNode);
                    nodeTimesteps = new NodeTimesteps(nodeId, timesteps);
                    for (int i = 0; i < timesteps; i++) {
                        //make sure there are values for this timestep, o/w enter 0
                        if ((i + 1) < csvLine.size()) {
                            //have to eliminate special characters (Java does not like
                            //return chars)
                            csvLine.set(i + 1, csvLine.get(i + 1).
                                    replaceAll("\\r|\\n", ""));

                            if (csvLine.get(i + 1).isEmpty()) {
                                nodeTimesteps.setBiomass(i, 0);
                            } else {
                                nodeTimesteps.setBiomass(i,
                                        Double.valueOf(csvLine.get(i + 1)));
                            }
                        } else {
                            nodeTimesteps.setBiomass(i, 0);
                        }
                        //System.out.printf(">%d %s ", i, nodeTimesteps.getBiomass(i));
                    }
                    ecosysTimesteps.putNodeTimesteps(nodeId, nodeTimesteps);
                    break;
                default:
                    break;
            }
        }
    
    }
}
