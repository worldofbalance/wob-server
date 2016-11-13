/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atn;

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
import simulation.simjob.EcosystemTimesteps;
import simulation.simjob.NodeTimesteps;
import util.CSVParser;

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

    /**
     * Generate new filename in the given directory with the given prefix and
     * extension. The file will be named "prefix.extension" if a file with that
     * name doesn't already exist; otherwise, it will be named
     * "prefix_num.extension", where num is an integer chosen sequentially to
     * avoid clobbering existing files.
     *
     * @param dir The directory in which the output file will be stored (will be created if it doesn't exist)
     * @param prefix The beginning of the filename
     * @param extension The file extension, including the "."
     * @return the new output file's full path
     */
    public static File getNewOutputFile(File dir, String prefix, String extension) {
        dir.mkdirs();
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String fullname) {
                return fullname.startsWith(prefix)
                        && fullname.endsWith(extension);
            }
        });

        //check current files to find next number that has not been used
        String filename = prefix;

        if (files.length > 0) {
            int[] temp = new int[files.length];

            for (int i = 0; i < temp.length; i++) {
                String lastFilename = files[i].replaceFirst(prefix, "").
                        replaceFirst("_", "");

                try {
                    temp[i] = Integer.parseInt(lastFilename.substring(
                            0, lastFilename.indexOf(extension)));
                } catch (NumberFormatException ex) {
                    temp[i] = 0;
                }
            }

            Arrays.sort(temp);

            filename += "_" + (temp[temp.length - 1] + 1);
        }
        if (extension.equals(".csv")) {
            setLastCSVFilePath(new File(dir, filename).toString());
        }
        filename += extension;
        return new File(dir, filename);
    }

    //open printstream
    public static PrintStream getPrintStream(
            String prefix,
            String destDir
    ) {
        final String extension = ".csv";
        PrintStream ps = null;

        // Determine filename
        File dir = new File(destDir);
        File outputFile = getNewOutputFile(dir, prefix, extension);
        try {
            System.out.println(outputFile.toString());
            ps = new PrintStream(new FileOutputStream(outputFile));
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
