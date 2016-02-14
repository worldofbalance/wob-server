package atn;

//Java Imports
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//Other Imports
import model.Species;
import model.SpeciesType;
import net.response.ResponseChart;
import simulation.SpeciesZoneType;
import util.CSVParser;
import util.Log;
import core.GameEngine;
import model.ZoneNodes;
import db.CSVDAO;
import model.Ecosystem;  //9/25/14, integration with Gary's version zone->ecosystem
import util.NetworkFunctions;

/**
* The PredictionRunnable class is used to store data for use by the Prediction
* process. Contains a method to create CSVs from the Prediction results and
* delivers it to the client.
*/
public class ATNPredictionRunnable implements Runnable {

 private long executionTime;
 private GameEngine gameEngine;
 private Ecosystem zone;
 private ATNEngine atnEngine;
 private Map<Integer, Species> speciesList;
 private Map<Integer, Integer> newSpeciesNodeList;
 private Map<Integer, Integer> currentSpeciesList = new HashMap<Integer, Integer>();
 private Map<Integer, Integer> currentSpeciesNodeList = new HashMap<Integer, Integer>();
 private String manipulation_id;
 private int startTimestep;
 private int runTimestep;
 private Map<Integer, SpeciesZoneType> nextSpeciesNodeList;
 private boolean isReady;
 //4/9/14, JTC, persistent zone nodes
 private ZoneNodes zoneNodes;

 public ATNPredictionRunnable(GameEngine gameEngine, Ecosystem zone, ATNEngine atnEngine, 
         String manipulation_id, int startTimestep,
         Map<Integer, Species> speciesList, Map<Integer, Integer> newSpeciesNodeList,
         ZoneNodes zoneNodes) {

     this.gameEngine = gameEngine;
     this.zone = zone;
     this.atnEngine = atnEngine;
     this.manipulation_id = manipulation_id;
     this.startTimestep = startTimestep;
     // Store Map References
     this.speciesList = speciesList;
     this.newSpeciesNodeList = newSpeciesNodeList;
     this.zoneNodes = zoneNodes;
 }

 public long initialize() {
     // Adjust for delays
     runTimestep = gameEngine.getCurrentMonth() - startTimestep + 1;
     // Store the most recent data
     newSpeciesNodeList = new HashMap<Integer, Integer>(newSpeciesNodeList);
     // Convert Species to Nodes
     for (Species species : speciesList.values()) {
         currentSpeciesList.put(species.getID(), species.getTotalBiomass());

         SpeciesType type = species.getSpeciesType();

         for (Entry<Integer, Float> entry : type.getNodeDistribution().entrySet()) {
             int node_id = entry.getKey(), biomass = (int) (species.getTotalBiomass() * entry.getValue());

             if (currentSpeciesNodeList.containsKey(node_id)) {
                 currentSpeciesNodeList.put(node_id, currentSpeciesNodeList.get(node_id) + biomass);
             } else {
                 currentSpeciesNodeList.put(node_id, biomass);
             }
         }
     }

     isReady = true;
     return executionTime = System.currentTimeMillis();
 }

 @Override
 public void run() {
     if (isReady) {
         try {
             //JTC, zoneNodes passed as parameter to getPrediction
             nextSpeciesNodeList = atnEngine.getPrediction(manipulation_id, 
                     startTimestep, runTimestep, newSpeciesNodeList, zoneNodes);
             atnEngine.updateBiomass(zone, nextSpeciesNodeList);
             gameEngine.updateATNPrediction(this);

             Log.printf("Total Time (Simulation): %.2f seconds", 
                     Math.round((System.currentTimeMillis() - executionTime) / 10.0) / 100.0);
         } catch (Exception ex) {
             Log.println_e(ex.getMessage());
         }
     }
 }
 
 public int getID() {
     return (int) (executionTime % 100000);
 }

 public long getExecutionTime() {
     return executionTime;
 }

 public Ecosystem getZone() {
     return zone;
 }

 public Map<Integer, Integer> getCurrentSpeciesList() {
     return currentSpeciesList;
 }

 public Map<Integer, Integer> getCurrentSpeciesNodeList() {
     return currentSpeciesNodeList;
 }

 public Map<Integer, Integer> getNewSpeciesNodeList() {
     return newSpeciesNodeList;
 }

 public Map<Integer, SpeciesZoneType> getNextSpeciesNodeList() {
     return nextSpeciesNodeList;
 }

// //9/25/14, JTC, copied from newer (Gary's) version
// public void createCSVs() {
//     String csv = atnEngine.getBiomassCSVString(manipulation_id);
//
//     try {
//         String biomass_csv = CSVParser.removeNodesFromCSV(csv);
//         CSVDAO.createBiomassCSV(manipulation_id, biomass_csv);
//
//         ResponseChart response = new ResponseChart();
//         response.setType(0);
//         response.setCSV(biomass_csv);
//
//         NetworkFunctions.sendToPlayer(response, zone.getPlayerID());
//     } catch (Exception ex) {
//         Log.println_e(ex.getMessage());
//     }
// }
}
