/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.targetgenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.HashMap;
import java.util.ArrayList;
import shared.db.GameDB;

/**
 *
 * @author Harjit
 */
public class FoodWebGenerator {

    static HashMap<Integer, Table> consumptionTable;
    static int maxTries = 30;

    public static void init() {
        TargetGeneratorCache.init();
        if (consumptionTable == null) {
            consumptionTable = new HashMap<Integer, Table>();
            
            for(int j = 0; j < TargetGeneratorCache.consumes.size(); j++){
                int prey = TargetGeneratorCache.consumes.get(j).prey;
                int species = TargetGeneratorCache.consumes.get(j).species_id;
                if (!consumptionTable.containsKey(species)) {
                        consumptionTable.put(species, new Table());
                    }
                    if (!consumptionTable.containsKey(prey)) {
                        consumptionTable.put(prey, new Table());
                    }

                    //update tables
                    Table table = consumptionTable.get(species);
                    table.prey.add(prey);

                    table = consumptionTable.get(prey);
                    table.consumedBy.add(species);
            }
            /*
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Connection conn = DriverManager.getConnection("jdbc:mysql://smurf.sfsu.edu/deBuggerWeb?"
                // + "user=deBuggerWeb&password=deBugger");
                Connection conn = GameDB.getConnection();

                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consume");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int species = rs.getInt("species_id");
                    int prey = rs.getInt("prey_id");

                    if (!consumptionTable.containsKey(species)) {
                        consumptionTable.put(species, new Table());
                    }
                    if (!consumptionTable.containsKey(prey)) {
                        consumptionTable.put(prey, new Table());
                    }

                    //update tables
                    Table table = consumptionTable.get(species);
                    table.prey.add(prey);

                    table = consumptionTable.get(prey);
                    table.consumedBy.add(species);
                }
                stmt.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Failed to retrieve table");
                e.printStackTrace();
            }*/
        }
    }

    public static String[] translateFoodWeb(int[] web) {
        String[] speciesNames = new String[web.length];
        
        for(int i = 0; i < web.length; i++){
            for(int j = 0; j < TargetGeneratorCache.species.size(); j++){
                if(web[i] == TargetGeneratorCache.species.get(j).species_id){
                    speciesNames[i] = TargetGeneratorCache.species.get(j).name;
                }
            }
            
        }
        /*
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = GameDB.getConnection();
            for (int i = 0; i < web.length; i++) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `species` WHERE `species_id` = " + web[i]);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    speciesNames[i] = rs.getString("name");
                }
                stmt.close();
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve table");
            e.printStackTrace();
        }
        */
        return speciesNames;
    }

    public static int[] generateFoodWeb(int number) {
        int[] web = new int[number];
        int index = 0;
        java.util.Set<Integer> keys = consumptionTable.keySet();

        //generate random number of producers while keeping in mind not to have too many
        int maxProducers = number / 4;
        int numOfProducers = (int) (Math.random() * maxProducers);
        if (numOfProducers == 0) {
            numOfProducers = 1;
        }

        web = addProducersToWeb(web, keys, numOfProducers);
        index += numOfProducers;

        web = addAdditionalSpecies(web, keys, index);

        return web;
    }

    static int[] addAdditionalSpecies(int[] web, java.util.Set<Integer> keys, int index) {

        int length = web.length;
        if (index < length) {
            //get random animal in the web and find a predator for it
            int randomSeed = (int) (Math.random() * index);
            ArrayList<Integer> predators = getPredators(keys, web[randomSeed]);
            if (predators.size() > 0) {
                try {
                    int speciesID = getRandomSpeciesNotInWeb(predators, web);
                    if (speciesID > 0) {
                        web[index] = speciesID;
                        index++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("Most likely took too long trying to find one for this one so try another one");
                }
            }

            addAdditionalSpecies(web, keys, index);

        }
        return web;
    }

    static int[] addProducersToWeb(int[] web, java.util.Set<Integer> keys, int num) {
        int currIndex = 0;
        ArrayList<Integer> producers = getProducers(keys);
        for (int i = 0; i < num; i++) {
            try {
                web[i] = getRandomSpeciesNotInWeb(producers, web);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("Most likely took too long trying to find one for this one so try another one");
            }
        }
        return web;
    }

    static int getRandomSpeciesNotInWeb(ArrayList<Integer> set, int[] web) throws Exception {
        return getRandomSpeciesNotInWeb(set, web, maxTries);
    }

    static int getRandomSpeciesNotInWeb(ArrayList<Integer> set, int[] web, int triesLeft) {
        if (triesLeft > 0) {
            int randomSeed = (int) (Math.random() * set.size());
            int randomSpecies = set.get(randomSeed);
            if (!webContains(web, randomSpecies)) {
                return randomSpecies;
            } else {
                return getRandomSpeciesNotInWeb(set, web, triesLeft - 1);
            }
        }
        return -1;
    }

    static boolean webContains(int[] web, int species) {
        for (int i = 0; i < web.length; i++) {
            if (web[i] == species) {
                return true;
            }
        }

        return false;
    }

    static ArrayList<Integer> getProducers(java.util.Set<Integer> keys) {
        ArrayList<Integer> producers = new ArrayList<Integer>();
        for (Integer current : keys) {
            //check if they prey on anything
            if (consumptionTable.get(current).prey.isEmpty()) {
                producers.add(current);
            }
        }
        return producers;
    }

    static ArrayList<Integer> getPredators(java.util.Set<Integer> keys, int species) {
        ArrayList<Integer> predators = new ArrayList<Integer>();
        for (Integer current : keys) {
            if (consumptionTable.get(current).prey.contains(species)) {
                predators.add(current);
            }
        }
        return predators;
    }

    public static HashMap<Integer, Table> getFoodWebHash() {
        return consumptionTable;
    }

    public static int[] getNodeIds(int[] web) {
        int[] speciesNodes = new int[web.length];
        for(int i = 0; i < web.length; i++){
            for(int j = 0; j < TargetGeneratorCache.species.size(); j++){
                if(web[i] == TargetGeneratorCache.species.get(j).species_id){
                    speciesNodes[i] = TargetGeneratorCache.species.get(j).node_id;
                }
            }
            
        }
        /*
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = GameDB.getConnection();
            //Connection conn = GameDB.getConnection();
            for (int i = 0; i < web.length; i++) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `species_nodes` WHERE `species_id` = " + web[i]);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    speciesNames[i] = rs.getInt("node_id");
                }
                stmt.close();
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve table");
            e.printStackTrace();
        }*/
        return speciesNodes;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FoodWebGenerator analyzer = new FoodWebGenerator();
        int[] web = analyzer.generateFoodWeb(30);
        String[] translations = FoodWebGenerator.translateFoodWeb(web);
        for (int i = 0; i < web.length; i++) {
            System.out.println(translations[i]);
        }
    }

    public static class Table {

        ArrayList<Integer> prey = new ArrayList<Integer>();
        ArrayList<Integer> consumedBy = new ArrayList<Integer>();

        public Table() {

        }
    }

}
