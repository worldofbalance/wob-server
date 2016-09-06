package cos.core;

// Java Imports

import cos.core.lobby.Lobby;
import cos.core.world.World;
import cos.db.EcoSpeciesDAO;
import cos.db.SpeciesChangeListDAO;
import cos.db.StatsDAO;
import cos.metadata.Constants;
import cos.model.*;
import cos.net.response.ResponsePrediction;
import cos.net.response.ResponseSpeciesCreate;
import cos.simulation.PredictionRunnable;
import cos.simulation.SimulationEngine;
import cos.simulation.SpeciesZoneType;
import cos.util.EventListener;
import cos.util.*;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Other Imports

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
    private final SimulationEngine simEngine;
    private boolean isActive;
    private final ExecutorService predictionThreadPool = Executors.newCachedThreadPool();
    private long lastSimulationTime;
    private final Queue<PredictionRunnable> waitList = new LinkedList<PredictionRunnable>();

    public GameEngine(Lobby lobby, World world, Ecosystem ecosystem) {
        this.lobby = lobby;
        this.world = world;
        this.ecosystem = ecosystem;

        this.simEngine = new SimulationEngine();

        createClockEvents();
    }

    private void createClockEvents() {
        // Monthly Rewards
        world.getClock().createEvent(EventType.NEW_MONTH, new EventListener() {
            public void run(Object... args) {
                int month = (Integer) args[0];

                for (Player player : lobby.getPlayers()) {
                    if (month % 2 == 0) {
                        GameResources.updateCredits(player, 400);
                    } else {
                        GameResources.updateCredits(player, 350);
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
//     * @param world
     * @param currentTimeStep 
     */
    private void runSimulation(Ecosystem ecosystem, int currentTimeStep) {
        ecosystem.updateScore();

        Map<Integer, Species> speciesList = ecosystem.getSpeciesList();
        Map<Integer, Integer> newSpeciesNodeList = ecosystem.getAddSpeciesList();

        PredictionRunnable runnable = new PredictionRunnable(this, ecosystem, simEngine, ecosystem.getManipulationID(), currentTimeStep,
                speciesList, newSpeciesNodeList, ecosystem.getZoneNodes());
        waitList.add(runnable);

        if (waitList.size() == 1) {
            lastSimulationTime = runnable.initialize();
            predictionThreadPool.submit(runnable);
        }
    }

    /**
     * Run a simulation at the same timestep.
     */
    public void forceSimulation() {
        runSimulation(ecosystem, getCurrentMonth());
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
                    SpeciesChangeListDAO.createEntry(zone.getID(), species_id, gDiff + rDiff);
                }
            }

            zone.setSpeciesChangeList(speciesChangeList);
            
            ResponsePrediction response = new ResponsePrediction();
            response.setResults(speciesChangeList);
            NetworkFunctions.sendToLobby(response, lobby.getID());

            zone.updateEcosystemScore();
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
            ex.printStackTrace();
        }

        Log.printf("Total Time (Prediction Step): %.2f seconds", Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
    }

    public void initializeSpecies(Species species, Ecosystem ecosystem) {
        for (SpeciesGroup group : species.getGroups().values()) {
            ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, ecosystem.getID(), group);
            NetworkFunctions.sendToLobby(response, lobby.getID());
        }

        ecosystem.setSpecies(species);
    }

    public void createSpeciesByPurchase(Player player, Map<Integer, Integer> speciesList, Ecosystem zone) {
        for (Entry<Integer, Integer> entry : speciesList.entrySet()) {
            int species_id = entry.getKey(), biomass = entry.getValue();
            SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

            for (int node_id : speciesType.getNodeList()) {
                zone.setNewSpeciesNode(node_id, biomass);
            }

            Species species = null;

            if (zone.containsSpecies(species_id)) {
                species = zone.getSpecies(species_id);

                for (SpeciesGroup group : species.getGroups().values()) {
                    group.setBiomass(group.getBiomass() + biomass / species.getGroups().size());

                    EcoSpeciesDAO.updateBiomass(group.getID(), group.getBiomass());

                    ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, zone.getID(), group);
                    NetworkFunctions.sendToLobby(response, lobby.getID());
                }
                
            } else {
                    int group_id = EcoSpeciesDAO.createSpecies(zone.getID(), species_id, biomass);

                    species = new Species(species_id, speciesType);
                    SpeciesGroup group = new SpeciesGroup(species, group_id, biomass, Vector3.zero);
                    species.add(group);

                    ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, zone.getID(), group);
                    NetworkFunctions.sendToLobby(response, lobby.getID());
            }

            zone.addSpecies(species);

            // Logging Purposes
            int player_id = player.getID(), zone_id = zone.getID();

            try {
                StatsDAO.createStat(species_id, getCurrentMonth(), "Purchase", biomass, player_id, zone_id);
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }
    }
}
