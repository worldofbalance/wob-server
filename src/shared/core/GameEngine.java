package shared.core;

// Java Imports
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import shared.metadata.Constants;
import shared.model.Ecosystem;
import shared.model.Player;
import shared.model.Species;
import shared.model.SpeciesGroup;
import shared.model.SpeciesType;
import lby.net.response.ResponsePrediction;
import lby.net.response.ResponseSpeciesCreate;
import shared.simulation.PredictionRunnable;
import shared.simulation.SimulationEngine;
import shared.simulation.SpeciesZoneType;
import shared.util.EventListener;
import shared.util.EventType;
import shared.util.Log;
import shared.util.NetworkFunctions;
import shared.util.Vector3;
import shared.atn.ATNEngine;
import shared.atn.ATNPredictionRunnable;
import shared.atn.test.ATNResultModel;
import shared.atn.test.UpdatePredictionListener;
import lby.core.Lobby;
import lby.core.world.World;
import shared.db.EcoSpeciesDAO;
import shared.db.SpeciesChangeListDAO;
import shared.db.StatsDAO;

/**
 * The GameEngine class is used to control the in-game time as well as
 * performing certain actions at specific time intervals for its assigned World.
 * Actions such as performing predictions and species interpolation. Other
 * methods contained in this class decides how an organism of a particular
 * species gets created and handled.
 */
public class GameEngine {

    private Lobby lobby;
    private final World world;
    private final Ecosystem ecosystem;
    private SimulationEngine simEngine = null;
    private boolean isActive;
    private final ExecutorService predictionThreadPool = Executors.newCachedThreadPool();
    private long lastSimulationTime;
    private final Queue<PredictionRunnable> waitList = new LinkedList<PredictionRunnable>();
    
	private ATNEngine atnEngine = null;
	private final Queue<ATNPredictionRunnable> atnWaitList = new LinkedList<ATNPredictionRunnable>();
	public UpdatePredictionListener updatePredictionListener;

    public GameEngine(Lobby lobby, World world, Ecosystem ecosystem) {
        this.lobby = lobby;
        this.world = world;
        this.ecosystem = ecosystem;
        this.ecosystem.setGameEngine(this);

        if(Constants.useSimEngine){
        	this.simEngine = new SimulationEngine();
        }
        if(Constants.useAtnEngine){
        	this.atnEngine = new ATNEngine();
        }
        // createClockEvents();
    }

    private void createClockEvents() {
        // Monthly Rewards
        world.getClock().createEvent(EventType.NEW_MONTH, new EventListener() {
            @Override
            public void run(Object... args) {
                int month = (Integer) args[0];

                for (Player player : lobby.getPlayers()) {
                    if (month % 2 == 0) {
                        // GameResources.updateCredits(player, 400);
                    } else {
                        // GameResources.updateCredits(player, 350);
                    }
                }
            }
        });
    }

    public World getWorld() {
        return world;
    }

    public Ecosystem getEcosystem() {
        return ecosystem;
    }
    
    public int getCurrentMonth() {
        return world.getDay() / 30 + 1;
    }
    
    // Presently this is disabled in World. 
    // The day = simulation number
    public int setCurrentMonth(int month) {
        return world.setDay(month * 30 + 1);
    }

    public void start() {
        isActive = true;
    }

    /**
     * Driven by RequestHeartbeat
     */
    public void run() {
    }

    /**
     * Run a simulation for a given zone at the specific timestep.
     * 
     * @param world
     * @param currentTimeStep 
     */
    private void runSimulation(Ecosystem ecosystem, int currentTimeStep) {        
        ecosystem.updateScore();
         
        Map<Integer, Species> speciesList = ecosystem.getSpeciesList();        
        Map<Integer, Integer> newSpeciesNodeList = ecosystem.getAddSpeciesList();

        if(Constants.useSimEngine){
	        PredictionRunnable runnable = new PredictionRunnable(this, ecosystem, simEngine, ecosystem.getManipulationID(), currentTimeStep,
	                speciesList, newSpeciesNodeList, ecosystem.getZoneNodes());
	        waitList.add(runnable);
	
	        if (waitList.size() == 1) {
	            lastSimulationTime = runnable.initialize();
	            predictionThreadPool.submit(runnable);
	        }
        }
        
        if(Constants.useAtnEngine){
	    ATNPredictionRunnable atnRunnable = new ATNPredictionRunnable(this, ecosystem, atnEngine, ecosystem.getATNManipulationID(), currentTimeStep,
	            speciesList, newSpeciesNodeList, ecosystem.getZoneNodes()); 
	    atnWaitList.add(atnRunnable);
	    	
	    if (atnWaitList.size() == 1) {
	        lastSimulationTime = atnRunnable.initialize();
	        predictionThreadPool.submit(atnRunnable);
	    }
        }
    }

    /**
     * Run a simulation at the same time step.
     */
    public void forceSimulation() {
        // getCurrentMonth() is designed to do 1 time step
        int timeStepsDefault = 1;
        runSimulation(ecosystem, timeStepsDefault);
        // world.updateClock();
    }
    
    /**
     * Run a simulation at the same time step.
     */
    public void forceSimulation(int timestep) {
        runSimulation(ecosystem, timestep);
        ecosystem.updateTimeSteps(timestep);
        // world.updateClock();
    }
    
    public void deleteSimulationIds() {
    	simEngine.saveBiomassCSVFileSimJob(ecosystem.getManipulationID(), "Simulation Job ", simEngine.getBiomassCSVString(ecosystem.getManipulationID()));
    	simEngine.deleteManipulation(ecosystem.getManipulationID());
    	simEngine.deleteNetwork(ecosystem.getNetworkId());
    	System.out.printf("Deleted net ID: %s\n", ecosystem.getNetworkId());
    }
    
    /**
     * 
     * @param runnable 
     */
    public void updatePrediction(PredictionRunnable runnable) {
        long milliseconds = System.currentTimeMillis();

        Ecosystem zone = runnable.getZone();
        // Remove species nodes that were just used
        for (Entry<Integer, Integer> entry : runnable.getNewSpeciesNodeList().entrySet()) {
            int node_id = entry.getKey(), biomass = entry.getValue();
            zone.removeNewSpeciesNode(node_id, biomass);
        }
        // Execute the most recent Prediction request; drop all before it.
        waitList.remove(runnable);
        if (!waitList.isEmpty()) {
            for (int i = 0; i < waitList.size() - 1; i++) {
                PredictionRunnable r = waitList.poll();
                Log.printf("Dropped Prediction Step [%d]", r.getID());
            }

            PredictionRunnable nextRunnable = waitList.poll();
            lastSimulationTime = nextRunnable.initialize();
            predictionThreadPool.submit(nextRunnable);
        }

        Log.printf("Running Prediction Step...");

        Map<Integer, Integer> nodeDifference = new HashMap<Integer, Integer>();
        Map<Integer, SpeciesZoneType> nextSpeciesNodeList = runnable.getNextSpeciesNodeList();

        runnable.createCSVs();

        try {
            Log.println("Interpreting Biomass Results...");
            // Determine the positive and negative change in biomass of species.
            Map<Integer, Integer> currentSpeciesNodeList = runnable.getCurrentSpeciesNodeList();

            for (SpeciesZoneType species : nextSpeciesNodeList.values()) {
                int node_id = species.getNodeIndex();
                int nextBiomass = (int) species.getCurrentBiomass();

                if (currentSpeciesNodeList.containsKey(node_id)) {
                    int currentBiomass = currentSpeciesNodeList.get(node_id);
                    nodeDifference.put(node_id, nextBiomass - currentBiomass);
                } else {
                    nodeDifference.put(node_id, nextBiomass);
                }
            }

            Map<Integer, Integer> speciesChangeList = new HashMap<Integer, Integer>();
            
            // Shuffle the order at when each species get processed.
            List<Integer> speciesList = new ArrayList<Integer>(runnable.getCurrentSpeciesList().keySet());
            Collections.shuffle(speciesList);
            // Adjust the number of species by creating or reducing the existing amount
            int day = SpeciesChangeListDAO.getDay();
            for (int species_id : speciesList) {
                SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

                int gDiff = 0, rDiff = 0;
                boolean hasGrowth = true, hasReduced = true;

                for (int node_id : speciesType.getNodeList()) {
                    int diff = nodeDifference.get(node_id);

                    // Check Growth
                    if (diff > 0) {
                        gDiff = gDiff == 0 ? diff : Math.min(diff, gDiff);
                    } else {
                        hasGrowth = false;
                    }

                    // Check Reduction
                    if (diff < 0) {
                        rDiff = rDiff == 0 ? diff : Math.max(diff, rDiff);
                    } else {
                        hasReduced = false;
                    }
                }

                if (hasGrowth) {
                    Log.printf("  %s Species[%d] increased by %d", speciesType.getName(), speciesType.getID(), gDiff);

                    for (Entry<Integer, Float> entry : speciesType.getNodeDistribution().entrySet()) {
                        int node_id = entry.getKey();
                        float distribution = entry.getValue();

                        int biomass = (int) (gDiff * distribution);
                        nodeDifference.put(node_id, nodeDifference.get(node_id) - biomass);

                        Log.printf("    Node[%d] increased by %d", node_id, biomass);
                    }
                } else if (hasReduced) {
                    Log.printf("  %s Species[%d] decreased by %d", speciesType.getName(), speciesType.getID(), Math.abs(rDiff));

                    for (Entry<Integer, Float> entry : speciesType.getNodeDistribution().entrySet()) {
                        int node_id = entry.getKey();
                        float distribution = entry.getValue();

                        int biomass = (int) (rDiff * distribution);
                        nodeDifference.put(node_id, nodeDifference.get(node_id) - biomass);

                        Log.printf("    Node[%d] decreased by %d", node_id, Math.abs(biomass));
                    }
                }

                if (gDiff + rDiff != 0) {
                    speciesChangeList.put(species_id, gDiff + rDiff);
                    SpeciesChangeListDAO.createEntry(zone.getID(), species_id, gDiff + rDiff, day);
                }
            }

            zone.setSpeciesChangeList(speciesChangeList);
            
            if(!Constants.DEBUG_MODE){
	        ResponsePrediction response = new ResponsePrediction();
                response.setStatus((short) 0);
	        response.setResults(speciesChangeList);
	        NetworkFunctions.sendToLobby(response, lobby.getID());
            }

            int scoreBefore = zone.getScore();
            zone.updateEnvironmentScore();
            int scoreAfter = zone.getScore();
            Log.println("GameEngine: before / after environment score: " + scoreBefore + " " + scoreAfter);
            int delta = scoreAfter - scoreBefore;
            if (delta != 0) {
                SpeciesChangeListDAO.createEntry(zone.getID(), -1, delta, day);
            }  
        } catch (Exception ex) {
            Log.println_e(ex.getMessage());
            ex.printStackTrace();
        }

        Log.printf("Total Time (Prediction Step): %.2f seconds", Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
    }

    public void initializeSpecies(Species species, Ecosystem ecosystem) {
    	if(!Constants.DEBUG_MODE){
	        for (SpeciesGroup group : species.getGroups().values()) {
	            ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, ecosystem.getID(), group);
	            NetworkFunctions.sendToLobby(response, lobby.getID());
	        }
    	}
        ecosystem.setSpecies(species);
        //10/12/2015, HJR Not sure if we need to add this logic for the simulation engine,
        //because web services default food web does this
//        if(Constants.useSimEngine){
//	        SpeciesType type = species.getSpeciesType();
//	        for (Entry<Integer, Float> entry : type.getNodeDistribution().entrySet()) {
//	            int node_id = entry.getKey(), biomass = (int) (species.getTotalBiomass() * entry.getValue());
//	
//	            SpeciesZoneType szt = simEngine.createSpeciesZoneType(node_id, biomass);
//	            ecosystem.getZoneNodes().addNode(node_id, szt);
//	        }
//        }
        if(Constants.useAtnEngine){
	        SpeciesType type = species.getSpeciesType();
	        for (Entry<Integer, Float> entry : type.getNodeDistribution().entrySet()) {
	            int node_id = entry.getKey(), biomass = (int) (species.getTotalBiomass() * entry.getValue());
	
	            SpeciesZoneType szt = atnEngine.createSpeciesZoneType(node_id, biomass);
	            ecosystem.getZoneNodes().addNode(node_id, szt);
	        }
        }
    }
    
    public HashMap<Integer, Integer> getSpeciesIdFromNodeIds(Map<Integer, Integer> nodeIdSpeciesList){
    	HashMap<Integer, Integer> speciesIdList = new HashMap<Integer, Integer>();
    	for (Entry<Integer, Integer> entry : nodeIdSpeciesList.entrySet()) {
	    	int node_id = entry.getKey(), biomass = entry.getValue();
	        SpeciesType speciesType = ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(node_id);
	        int species_id = speciesType.getID();
	        speciesIdList.put(species_id, biomass);
    	}
    	return speciesIdList;
    }
    
    public void createSpeciesByPurchase(Player player, Map<Integer, Integer> speciesList, Ecosystem ecosystem) {
        for (Entry<Integer, Integer> entry : speciesList.entrySet()) {
            int species_id = entry.getKey(), biomass = entry.getValue();
            SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

            for (int node_id : speciesType.getNodeList()) {
            	ecosystem.setNewSpeciesNode(node_id, biomass);
            }

            Species species = null;

            int day = SpeciesChangeListDAO.getDay();
            if (ecosystem.containsSpecies(species_id)) {
                species = ecosystem.getSpecies(species_id);
                int eco_id = ecosystem.getID();
                int biomassPrev = EcoSpeciesDAO.getSpeciesBiomass(eco_id, species_id);

                // (biomassPrev + biomass)/size
                for (SpeciesGroup group : species.getGroups().values()) {
                    group.setBiomass((biomassPrev + biomass) / species.getGroups().size());
                    EcoSpeciesDAO.updateBiomass(eco_id, group.getID(), species_id, (biomassPrev + biomass) / species.getGroups().size()); 
                    if(!Constants.DEBUG_MODE){
	                    ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, ecosystem.getID(), group);
	                    NetworkFunctions.sendToLobby(response, lobby.getID());
                    }
                }
                SpeciesChangeListDAO.createEntry(eco_id, species_id, biomass, day);                
            } else {
                    int group_id = EcoSpeciesDAO.createSpecies(ecosystem.getID(), species_id, biomass);
                    SpeciesChangeListDAO.createEntry(ecosystem.getID(), species_id, biomass, day); 
                    species = new Species(species_id, speciesType);
                    SpeciesGroup group = new SpeciesGroup(species, group_id, biomass, Vector3.zero);
                    species.add(group);
                    if(!Constants.DEBUG_MODE){
	                    ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, ecosystem.getID(), group);
	                    NetworkFunctions.sendToLobby(response, lobby.getID());
                    }
            }

            ecosystem.addSpecies(species);

            // Logging Purposes
            int player_id = player.getID(), zone_id = ecosystem.getID();

            try {
                StatsDAO.createStat(species_id, getCurrentMonth(), "Purchase", biomass, player_id, zone_id);
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }
    }
    
    public void removeSpeciesFromZone(Player player, Map<Integer, Integer> speciesListForRemoval, Ecosystem ecosystem){
        for (Entry<Integer, Integer> entry : speciesListForRemoval.entrySet()) {
            int species_id = entry.getKey(), biomass = entry.getValue();

            SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

            for (int node_id : speciesType.getNodeList()) {
            	ecosystem.removeNode(node_id);
            }
            
            Species species = null;

            if (ecosystem.containsSpecies(species_id)) {
                species = ecosystem.getSpecies(species_id);

                for (SpeciesGroup group : species.getGroups().values()) {

                    EcoSpeciesDAO.removeSpecies(group.getID());
                    group.setBiomass(0);
                    if(!Constants.DEBUG_MODE){
	                    ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.REMOVE_STATUS_DEFAULT, ecosystem.getID(), group);
	                    NetworkFunctions.sendToLobby(response, lobby.getID());
                    }
                }
                
            } 
            ecosystem.removeSpecies(species_id);
            ecosystem.removeEntry(species_id);

            // Logging Purposes
            int player_id = player.getID(), zone_id = ecosystem.getID();

            try {
                StatsDAO.createStat(species_id, getCurrentMonth(), "Remove", biomass, player_id, zone_id);
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }    	
    }
    /**
     * 
     * @param runnable 
     */
    public void updateATNPrediction(ATNPredictionRunnable runnable) {
        int group_id = 0;
        long milliseconds = System.currentTimeMillis();

        Ecosystem zone = runnable.getZone();
        Map<Integer, Species> ecoSpeciesList = zone.getSpeciesList();
        
        // Remove species nodes that were just used
        for (Entry<Integer, Integer> entry : runnable.getNewSpeciesNodeList().entrySet()) {
            int node_id = entry.getKey(), biomass = entry.getValue();
            zone.removeNewSpeciesNode(node_id, biomass);
        }
        // Execute the most recent Prediction request; drop all before it.
        atnWaitList.remove(runnable);
        if (!atnWaitList.isEmpty()) {
            for (int i = 0; i < atnWaitList.size() - 1; i++) {
                ATNPredictionRunnable r = atnWaitList.poll();
                Log.printf("Dropped Prediction Step [%d]", r.getID());
            }

            ATNPredictionRunnable nextRunnable = atnWaitList.poll();
            lastSimulationTime = nextRunnable.initialize();
            predictionThreadPool.submit(nextRunnable);
        }

        Log.printf("Running Prediction Step...");

        Map<Integer, Integer> nodeDifference = new HashMap<Integer, Integer>();
        Map<Integer, SpeciesZoneType> nextSpeciesNodeList = runnable.getNextSpeciesNodeList();

        //runnable.createCSVs();

        try {
            Log.println("Interpreting Biomass Results...");
            // Determine the positive and negative change in biomass of species.
            Map<Integer, Integer> currentSpeciesNodeList = runnable.getCurrentSpeciesNodeList();

            for (SpeciesZoneType species : nextSpeciesNodeList.values()) {
                int node_id = species.getNodeIndex();
                int nextBiomass = (int) species.getCurrentBiomass();

                if (currentSpeciesNodeList.containsKey(node_id)) {
                    int currentBiomass = currentSpeciesNodeList.get(node_id);
                    nodeDifference.put(node_id, nextBiomass - currentBiomass);
                } else {
                    nodeDifference.put(node_id, nextBiomass);
                }
            }

            Map<Integer, Integer> speciesChangeList = new HashMap<Integer, Integer>();
            
            // Shuffle the order at when each species get processed.
            List<Integer> speciesList = new ArrayList<Integer>(runnable.getCurrentSpeciesList().keySet());
            Collections.shuffle(speciesList);
            int day = SpeciesChangeListDAO.getDay();
            // Adjust the number of species by creating or reducing the existing amount
            for (int species_id : speciesList) {
                SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

                int gDiff = 0, rDiff = 0;
                boolean hasGrowth = true, hasReduced = true;

                for (int node_id : speciesType.getNodeList()) {
                    int diff = nodeDifference.get(node_id);

                    // Check Growth
                    if (diff > 0) {
                        gDiff = gDiff == 0 ? diff : Math.min(diff, gDiff);
                    } else {
                        hasGrowth = false;
                    }

                    // Check Reduction
                    if (diff < 0) {
                        rDiff = rDiff == 0 ? diff : Math.max(diff, rDiff);
                    } else {
                        hasReduced = false;
                    }
                }

                if (hasGrowth) {
                    Log.printf("  %s Species[%d] increased by %d", speciesType.getName(), speciesType.getID(), gDiff);

                    for (Entry<Integer, Float> entry : speciesType.getNodeDistribution().entrySet()) {
                        int node_id = entry.getKey();
                        float distribution = entry.getValue();

                        int biomass = (int) (gDiff * distribution);
                        nodeDifference.put(node_id, nodeDifference.get(node_id) - biomass);

                        Log.printf("    Node[%d] increased by %d", node_id, biomass);
                    }
                } else if (hasReduced) {
                    Log.printf("  %s Species[%d] decreased by %d", speciesType.getName(), speciesType.getID(), Math.abs(rDiff));

                    for (Entry<Integer, Float> entry : speciesType.getNodeDistribution().entrySet()) {
                        int node_id = entry.getKey();
                        float distribution = entry.getValue();

                        int biomass = (int) (rDiff * distribution);
                        nodeDifference.put(node_id, nodeDifference.get(node_id) - biomass);

                        Log.printf("    Node[%d] decreased by %d", node_id, Math.abs(biomass));
                    }
                }

                if (gDiff + rDiff != 0) {
                    speciesChangeList.put(species_id, gDiff + rDiff);   
                    int biomassOld = zone.getSpeciesList().get(species_id).getTotalBiomass();
                    if (biomassOld <= 0) {
                        biomassOld = EcoSpeciesDAO.getSpeciesBiomass(group_id, species_id);
                        Log.println("GameEngine, updateATNPrediction: biomassOld <= 0, DB has: " + biomassOld);
                    }
                    Log.println("GameEngine, updateATNPrediction: original biomass: " + biomassOld);
                    EcoSpeciesDAO.updateBiomass(zone.getID(), group_id, species_id, biomassOld + gDiff + rDiff);
                    SpeciesChangeListDAO.createEntry(zone.getID(), species_id, gDiff + rDiff, day);
                    // Update the in memory biomass
                    SpeciesGroup speciesGroup = ecoSpeciesList.get(species_id).getGroups().get(group_id);
                    speciesGroup.setBiomass(speciesGroup.getBiomass() + gDiff + rDiff); 
                }
            }
 
            zone.setSpeciesChangeList(speciesChangeList);
            
            if(!Constants.DEBUG_MODE){
	            ResponsePrediction response = new ResponsePrediction();
                    response.setStatus((short) 0);
	            response.setResults(speciesChangeList);
	            NetworkFunctions.sendToLobby(response, lobby.getID());
            }
            
            int scoreBefore = zone.getScore();
            zone.updateEnvironmentScore();
            int scoreAfter = zone.getScore();
            Log.println("GameEngine: before / after environment score: " + scoreBefore + " " + scoreAfter);
            int delta = scoreAfter - scoreBefore;
            if (delta != 0) {
                SpeciesChangeListDAO.createEntry(zone.getID(), -1, delta, day);
            }                        
            if(updatePredictionListener != null){
            	updatePredictionListener.updatePredictionComplete();
            }
        } catch (Exception ex) {
            Log.println_e(ex.getMessage());
            ex.printStackTrace();
        }

        Log.printf("Total Time (Prediction Step): %.2f seconds", Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
    }

    public void setUpdatePredictionlistener(UpdatePredictionListener listener){
    	this.updatePredictionListener = listener;
    }
	
	public ATNResultModel getSpeciesInEcosystem(){
		ATNResultModel result = new ATNResultModel();
		result.setNodeConfig(atnEngine.getExistingNodeConfig(ecosystem.getZoneNodes()));
		result.setZoneNodes(ecosystem.getZoneNodes());
		return result;
	}
	
	public HashMap<Integer, Integer> getBiomassOfSpeciesInEcosystem(){	
		HashMap<Integer, Integer> map = EcoSpeciesDAO.getSpeciesWithNodeIdAndBiomass(ecosystem.getID());	
		return map;
	}
}
