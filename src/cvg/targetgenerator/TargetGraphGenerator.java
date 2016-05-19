/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.targetgenerator;

import cvg.ATNConverter;
import cvg.net.response.ResponseConvergeEcosystems;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import shared.atn.ATNEngine;
import shared.atn.Functions;
import shared.db.ConvergeEcosystemDAO;
import shared.db.GameDB;
import shared.metadata.Constants;
import shared.simulation.simjob.SimJob;

/**
 *
 * @author Harjit Class to automate the view of target Graphs. Also to generate
 * "converge-ecosystems-mc.txt"
 */
public class TargetGraphGenerator {

    public TargetGraphGenerator() {

    }

    public static void main(String[] args) {
        TargetGeneratorCache.init();
        writeToMultiplayerDatabase();
        generateNewMultiplayerFile();
    }

    public static void generateNewMultiplayerFile() {
        ResponseConvergeEcosystems response = new ResponseConvergeEcosystems();
        response.setConvergeEcosystems(ConvergeEcosystemDAO.getMultiplayerConvergeEcosystems());
        File file = new File(Constants.ATN_CSV_SAVE_PATH+"converge-ecosystems-mc.txt");
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
           /* PrintStream ps = new PrintStream(new FileOutputStream(Constants.ATN_CSV_SAVE_PATH + "converge-ecosystems-mc.txt"));
            ps.write(response.getBytes(), 0, response.getBytes().length);
            ps.close();*/
            //Harjit writing temporary code to generate "converge-ecosystems-mc.txt" in local computer
            FileOutputStream tempStream = new FileOutputStream(Constants.ATN_CSV_SAVE_PATH + "converge-ecosystems-mc.txt");
            tempStream.write(response.getBytes());
            tempStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToMultiplayerDatabase() {
        File file = new File(Constants.ATN_ACCEPTED_CSV_SAVE_PATH);
        BufferedReader reader;
        String[] files = file.list();

        int check = 0;
        
        for (int i = 0; i < files.length; i++) {
            File temp = new File(Constants.ATN_ACCEPTED_CSV_SAVE_PATH + files[i]);
            //get all subdirectories
            if (temp.isDirectory()) {
                String[] allFiles = temp.list();
                //for each file in the subdirectory, add to database
                for (int j = 0; j < allFiles.length; j++) {
                    if (allFiles[j].endsWith("_node_params")) {
                        //get target parameters file
                        String targetParamFileName = Constants.ATN_ACCEPTED_CSV_SAVE_PATH + files[i] + "/" + allFiles[j];
                        String defaultParams = "";
                        String targetParams = "";
                        String description = "";
                        double initialbiomass = 0, totalconfigured = 0;
                        boolean changeable = false;
                        try {
                            reader = new BufferedReader(new FileReader(targetParamFileName));
                            String line = reader.readLine();
                            targetParams = line;
                            String[] args = line.split(",");
                            check = Integer.parseInt(args[0]);
                            defaultParams = args[0] + ",";
                            description = "Ecosystem (" + args[0] + " species)";
                            for (int arg = 1; arg < args.length; arg++) {

                                String s = args[arg];
                                if (!changeable) {
                                    defaultParams = defaultParams.concat(s + ",");

                                    //if this node is a species node id, next is changeable
                                    if (s.startsWith("[")) {
                                        changeable = true;
                                    }
                                    //if this node is 3 ahead of species node id, it is the number that specifies how many parameters are changed
                                    //if this is greater than 0, next is changeable
                                    int checkIfNextIsChangeable = arg - 3;
                                    if (checkIfNextIsChangeable > 0) {
                                        if (args[checkIfNextIsChangeable].startsWith("[")) {
                                            totalconfigured = Integer.parseInt(s);
                                            if (totalconfigured >= 0) {
                                                changeable = true;
                                            }
                                        }
                                    }
                                } else if (initialbiomass == 0) {
                                    initialbiomass = Double.parseDouble(s);
                                    //change it randomly
                                    if (Math.random() < .5) {
                                        initialbiomass -= Math.random() * (initialbiomass / 2);
                                    } else {
                                        initialbiomass += Math.random() * (initialbiomass / 2);
                                    }

                                    defaultParams = defaultParams.concat(initialbiomass + ",");

                                    //after initial biomass, we have per unit which is not changeable
                                    changeable = false;
                                } else if (totalconfigured > 0) {
                                    System.out.println("double: " + s.substring(2, s.length() - 1));
                                    double old = Double.parseDouble(s.substring(2, s.length() - 1));
                                    double newNumber = old;
                                    if (s.startsWith("K=")) {
                                        if (Math.random() < .5) {
                                            newNumber -= Math.random() * (newNumber / 2);
                                        } else {
                                            newNumber += Math.random() * (newNumber / 2);
                                        }

                                        s = "K=" + newNumber;
                                    } else if (s.startsWith("X=")) {
                                        newNumber = Math.random();
                                        s = "X=" + newNumber;
                                    }

                                    defaultParams = defaultParams.concat(s + ",");
                                    totalconfigured--;

                                } else if (totalconfigured == 0) {
                                    initialbiomass = 0;
                                    changeable = false;
                                    defaultParams = defaultParams.concat("0,");
                                }

                            }
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        defaultParams = defaultParams.substring(0, defaultParams.length() - 1);
                        System.out.println("Default Params: " + defaultParams);

                        //just for Ben's stuff
                        if(check == 11)
                            defaultParams = "11,[2],645.546,528.0,2,K=1000.0,R=1.0,0,[3],599.66,528.0,1,K=3623.1,0,[4],595.662,528.0,1,K=2077.99,0,[5],1426.75,1.0,1,K=1482.07,0,[7],639.183,816.0,1,K=5848.08,0,[49],1511.23,0.355,1,X=0.791329,0,[55],739.104,0.213,1,X=0.281552,0,[61],392.821,54.0,1,X=0.0128268,0,[74],924.06,50.0,1,X=0.149428,0,[82],525.34,50.0,1,X=0.194981,0,[83],233.019,103.0,1,X=0.183021,0";
                        else if(check == 5){
                            defaultParams = "5,[5],1555.63,1.0,1,K=15000.0,0,[14],1071.01,20.0,1,X=0.322425,0,[31],1844.15,0.0075,1,X=0.532847,0,[42],133.96,0.205,1,X=0.885162,0,[70],2110.84,13.0,1,X=0.28642,0";
                        }
                        runATNEngine(defaultParams);

                        //at this point, we have targetParams, defaultParams, and defaultCSV (Functions.getLastCSVFilePath())
                        //get targetCSV contents
                        String csvFile = targetParamFileName.replace("_node_params", ".csv");
                        String targetCSV = "";
                        try {
                            reader = new BufferedReader(new FileReader(csvFile));
                            String line = "";

                            while ((line = reader.readLine()) != null) {
                                String newline = "";
                                String[] steps = line.split(",");
                                for(int k = 0; k < steps.length; k++){
                                    newline = newline.concat(steps[k].trim() + ",");
                                }
                                line = newline.substring(0, newline.length() - 1);
                                targetCSV = targetCSV.concat(line +"\n");
                            }

                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String defaultCSV = "";
                        try {
                            reader = new BufferedReader(new FileReader(Functions.getLastCSVFilePath() + ".csv"));
                            String line = "";
                            while ((line = reader.readLine()) != null) {
                                String newline = "";
                                String[] steps = line.split(",");
                                for(int k = 0; k < steps.length; k++){
                                    newline = newline.concat(steps[k].trim() + ",");
                                }
                                
                                line = newline.substring(0, newline.length() - 1);
                                defaultCSV = defaultCSV.concat(line +"\n");
                            }
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //now we have defaultParams, targetParams, defaultCSV, targetCSV, timesteps = 200
                        addToMultiplayerDB(description, defaultParams, ATNConverter.ConvertCSV(defaultCSV, 200), targetParams, ATNConverter.ConvertCSV(targetCSV, 200), 200);
                    }
                }
            }
        }
    }

    //method to record the node_config that corresponds with the last CSV file
    static void writeTextFile(String node_config) {
        String lastCSVfile = Functions.getLastCSVFilePath();
        String fileName;
        if (lastCSVfile.endsWith(".csv")) {
            fileName = lastCSVfile.substring(0, lastCSVfile.indexOf(".csv")) + "_node_params";
        } else {
            fileName = lastCSVfile.concat("default_node_params");
        }

        try {
            PrintStream ps = new PrintStream(new FileOutputStream(fileName));
            ps.printf(node_config);
            ps.close();
        } catch (Exception e) {

        }
    }

    private static void addToMultiplayerDB(String description, String defaultParams, String defaultCSV, String targetParams, String targetCSV, int steps) {
        try {
            Connection conn = GameDB.getConnection();
            /*Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://smurf.sfsu.edu/wob?"
                 + "user=wob&password=asd123");*/
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("INSERT INTO `csc63101`.`converge_ecosystem_mc` (`description`, `timesteps`, `config_default`, `config_target`, `csv_default`, `csv_target`) VALUES ('" + description + "', '200', '" + defaultParams + "', '" + targetParams + "', '" + defaultCSV + "', '" + targetCSV + "')");
                stmt.execute();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void runATNEngine(String node_config) {
        try {
            ATNEngine.LOAD_SIM_TEST_PARAMS = true;
            ATNEngine atn = new ATNEngine();
            SimJob job = new SimJob();
            job.setJob_Descript("atn1");
            job.setNode_Config(node_config);
            job.setManip_Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            job.setTimesteps(400);
            String atnManipId = UUID.randomUUID().toString();
            job.setATNManipulationId(atnManipId);
            atn.processSimJob(job);
            ATNEngine.LOAD_SIM_TEST_PARAMS = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
