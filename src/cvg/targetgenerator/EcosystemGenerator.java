/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.targetgenerator;

import cvg.targetgenerator.DBStructs.SpeciesStruct;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import shared.atn.ATNEngine;
import shared.atn.Functions;
import shared.db.GameDB;
import shared.metadata.Constants;
import shared.simulation.simjob.SimJob;

/**
 *
 * @author Harjit Class meant to automate the ATNEngine and store the results
 * somewhere for AtnAnalyzer to analyze them
 */
public class EcosystemGenerator {

    //static HashMap<Integer, HashMap<String, Object>> savedSpecies;

    public static void GenerateEcosystems(int numOfSpecies, int numOfEcosystems) {
       /* if (savedSpecies == null) {
            savedSpecies = new HashMap<Integer, HashMap<String, Object>>();
        }*/
        int[] currentNodeWeb, currentSpeciesWeb;
        FoodWebGenerator.init();
        while (numOfEcosystems > 0) {
            currentSpeciesWeb = FoodWebGenerator.generateFoodWeb(numOfSpecies);
            currentNodeWeb = FoodWebGenerator.getNodeIds(currentSpeciesWeb);
            runATN(currentSpeciesWeb, currentNodeWeb, numOfEcosystems);

            numOfEcosystems--;
        }
    }

    static void runATN(int[] currentSpeciesWeb, int[] currentNodeWeb, int index) {
        try {
            ATNEngine.LOAD_SIM_TEST_PARAMS = true;
            String node_config = generateAtnString(currentSpeciesWeb, currentNodeWeb);
            //System.out.println("Node: " + node_config);
            ATNEngine atn = new ATNEngine(true);

            SimJob job = new SimJob();
            job.setJob_Descript("atn"+index);
            job.setNode_Config(node_config);
            job.setManip_Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            job.setTimesteps(200);
            String atnManipId = UUID.randomUUID().toString();
            job.setATNManipulationId(atnManipId);
            atn.processSimJob(job);

            writeTextFile(node_config);
            ATNEngine.LOAD_SIM_TEST_PARAMS = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method to record the node_config that corresponds with the last CSV file
    static void writeTextFile(String node_config) {
        String lastCSVfile = Functions.getLastCSVFilePath();
        String fileName;
        if (lastCSVfile.endsWith(".csv")) {
            fileName = lastCSVfile.substring(0, lastCSVfile.indexOf(".csv")) + "_node_params";
        } else {
            fileName = lastCSVfile.concat("_node_params");
        }

        try {
            PrintStream ps = new PrintStream(new FileOutputStream(fileName));
            ps.printf(node_config);
            ps.close();
        } catch (Exception e) {

        }
    }

    static String generateAtnString(int[] currentSpeciesWeb, int[] currentNodeWeb) {
        //5,[5],2000,1.0,1,K=9431.818,0,[14],1752,20.0,1,X=0.273,0,[31],1415,0.008,1,X=1.000,0,[42],240,0.205,1,X=0.437,0,[70],2494,13.0,1,X=0.155,0"
        //5,[1003],1257,20.0,0,0,[27],1439,0.009,1,X=0.2757947531683158,0,[44],1468,0.028,1,X=0.24639969335136758,0,[55],1604,0.213,0,0,[4],1586,3.35,1,X=0.17588834988551083,0
        String run = currentNodeWeb.length + ",";
        int initialbiomass, totalconfigured = 0;
        double perUnitBiomass = 0;
        boolean producer = false;

        for (int i = 0; i < currentNodeWeb.length; i++) {
            SpeciesStruct species = null;
            
            for(int j = 0; j < TargetGeneratorCache.species.size(); j++){
                species = TargetGeneratorCache.species.get(j);
                if(species.species_id == currentSpeciesWeb[i]){
                    perUnitBiomass = species.biomass;
                    producer = (species.organism_type == Constants.ORGANISM_TYPE_PLANT);
                }
            }
           /* if (savedSpecies.containsKey(currentSpeciesWeb[i])) {
                double mass = (double) savedSpecies.get(currentSpeciesWeb[i]).get("biomass");
                boolean pr = (boolean) savedSpecies.get(currentSpeciesWeb[i]).get("producer");
                perUnitBiomass = mass;
                producer = pr;
            } else {
                try {
                    Connection conn = GameDB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `species` WHERE `species_id` = " + currentSpeciesWeb[i]);
                    ResultSet results = stmt.executeQuery();
                    //check if plant to know whether we use K,R or X
                    while (results.next()) {
                        if (results.getInt("organism_type") == Constants.ORGANISM_TYPE_PLANT) {
                            producer = true;
                        }

                        perUnitBiomass = results.getDouble("biomass");
                    }
                    HashMap<String, Object> params = new HashMap<String, Object>();
                    params.put("producer", producer);
                    params.put("biomass", perUnitBiomass);
                    savedSpecies.put(currentSpeciesWeb[i], params);
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            //add species node in brackets
            run = run.concat("[" + currentNodeWeb[i] + "],");

            //add initial biomass, random between 1000 and 2000 for the sake of scatter searching, more possibilities for producers so more ecosystems
            //are possible
            if(!producer)
                initialbiomass = 2000 - (int) (Math.random() * 1000);
            else
                initialbiomass = 8000 - (int) (Math.random() * 4000);
            
            run = run.concat(initialbiomass + ",");

            //add per unit biomass
            run = run.concat(perUnitBiomass + ",");

            //figure out if you want to configure and make sure they are valid for producer or animal
            if (producer) {
                totalconfigured = (int) (Math.random() * 2);
            } else if (Math.random() > .5) {
                totalconfigured = 1;
            }
            //make the changes in correct format for K, R, or X
            boolean kUsed = false;
            boolean rUsed = false;
            run = run.concat(totalconfigured + ",");
            while (totalconfigured > 0) {
                if (producer) {
                    if ((Math.random() > .5 && !kUsed) || rUsed) {
                        run = run.concat("K=" + (10000 - (int) (Math.random() * 7000)) + ",");
                        kUsed = true;
                    } else {
                        run = run.concat("R=" + Math.random() * 2 + ",");
                        rUsed = true;
                    }
                } else {
                    run = run.concat("X=" + Math.random() + ",");
                }
                totalconfigured--;
            }

            //terminating line
            run = run.concat("0");

            //if not done yet, add another comma
            if (i < currentNodeWeb.length - 1) {
                run = run.concat(",");
            }

        }

        return run;
    }
}
