package core;

// Java Imports
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.TimerTask;

import metadata.Constants;
import model.Ecosystem;
import model.Player;
import model.Species;
import model.SpeciesType;
import net.response.ResponseEcosystem;
import simulation.SimulationEngine;
import simulation.SimulationException;
import simulation.SimulationIds;
import simulation.SpeciesZoneType;
import util.CSVParser;
import util.GameFunctions;
import util.GameTimer;
import util.Log;
import util.NetworkFunctions;
import atn.ATNEngine;
// Other Imports
import core.lobby.EcosystemLobby;
import core.lobby.LobbyController;
import core.world.Zone;
import db.CSVDAO;
import db.EcoSpeciesDAO;
import db.EcosystemDAO;
import db.LogDAO;
import db.ScoreDAO;
import db.world.WorldZoneDAO;

public class EcosystemController {

    // Singleton Instance
    private static EcosystemController controller;
    // Reference Tables
    private final Map<Integer, Ecosystem> ecosystems = new HashMap<Integer, Ecosystem>(); // Ecosystem ID -> Ecosystem
    private static EcosystemLobby lobby;
    private static List<Zone> zones;
    private static Ecosystem ecosystem;

    private EcosystemController() {
    }

    public static EcosystemController getInstance() {
        return controller == null ? controller = new EcosystemController() : controller;
    }

    public Ecosystem add(Ecosystem ecosystem) {
        return ecosystems.put(ecosystem.getID(), ecosystem);
    }

    public Ecosystem get(int eco_id) {
        return ecosystems.get(eco_id);
    }

    public Ecosystem remove(int eco_id) {
        return ecosystems.remove(eco_id);
    }

    /**
     * Create World. Uses: RequestCreateNewWorld
     *
     * @param world_id
     * @param name
     * @param type
     * @param player_id
     * @return
     */
    public static Ecosystem createEcosystem(int world_id, int player_id, String name, short type) {
        return EcosystemDAO.createEcosystem(world_id, player_id, name, type);
    }

    /**
     * Create Ecosystem Uses: RequestSpeciesAction
     *
     * @param ecosystem
     * @param speciesList
     */
    public static void createEcosystem(Ecosystem ecosystem, Map<Integer, Integer> speciesList) {
        // Map Species IDs to Node IDs
        Map<Integer, Integer> nodeBiomassList = GameFunctions.convertSpeciesToNodes(speciesList);
        if(Constants.useAtnEngine){
        	createATNServices(ecosystem, nodeBiomassList);
        }
    	if(Constants.useSimEngine){
	        // Perform Web Services
	        createWebServices(ecosystem, nodeBiomassList);
    	}
        // Update Environment Score
        double biomass = 0;

        for (Map.Entry<Integer, Integer> entry : speciesList.entrySet()) {
            SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(entry.getKey());
            biomass += speciesType.getBiomass() * Math.pow(entry.getValue() / speciesType.getBiomass(), speciesType.getTrophicLevel());
        }

        if (biomass > 0) {
            biomass = Math.round(Math.log(biomass) / Math.log(2)) * 5;
        }

        int env_score = (int) Math.round(Math.pow(biomass, 2) + Math.pow(speciesList.size(), 2));
        ScoreDAO.updateEnvironmentScore(ecosystem.getID(), env_score, env_score);
        // Generate CSVs from Web Services
        if(Constants.useSimEngine){
//        	createCSVs(ecosystem);
        }
        // Logging Purposes Only
        {
            String tempList = "";
            for (Entry<Integer, Integer> entry : speciesList.entrySet()) {
                tempList += entry.getKey() + ":" + entry.getValue() + ",";
            }
            LogDAO.createInitialSpecies(ecosystem.getPlayerID(), ecosystem.getID(), tempList);
        }
    }
    /**
     * Create ATN Engine Uses: WorldManager, createEcosystem()
     *
     * @param world
     * @param ecosystem
     * @param nodeBiomassList
     * @throws SQLException
     */
    private static void createATNServices(Ecosystem ecosystem, Map<Integer, Integer> nodeBiomassList) {
		Log.println("Creating ATN Engine ...");
		ATNEngine atnEngine = new ATNEngine();
		if(ecosystem.getATNManipulationID() == null){
			String atnManipId = UUID.randomUUID().toString();
			ecosystem.setATNManipulationID(atnManipId);
		}
		
        // Update Zone Database
        EcosystemDAO.updateATNManipulationID(ecosystem.getID(), ecosystem.getATNManipulationID());
        // Initialize Biomass and Additional Parameters
        List<SpeciesZoneType> mSpecies = new ArrayList<SpeciesZoneType>();
        for (Entry<Integer, Integer> entry : nodeBiomassList.entrySet()) {
            int node_id = entry.getKey(), biomass = entry.getValue();
            mSpecies.add(atnEngine.createSpeciesZoneType(node_id, biomass));
        }
        
        // First Month Logic
        for (SpeciesZoneType szt : mSpecies) {
            int species_id = ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(szt.getNodeIndex()).getID();
            //Will write the values into 'eco_species' table
            EcoSpeciesDAO.createSpecies(ecosystem.getID(), species_id, (int) szt.getCurrentBiomass());
        }
    }
    /**
     * Create Web Services Uses: WorldManager, createEcosystem()
     *
     * @param world
     * @param ecosystem
     * @param nodeBiomassList
     * @throws SQLException
     */
    private static void createWebServices(Ecosystem ecosystem, Map<Integer, Integer> nodeBiomassList) {
        Log.println("Creating Web Services...");
        // Prepare Web Services
        SimulationEngine se = new SimulationEngine();
        String networkName = "WoB-" + ecosystem.getID() + "-" + System.currentTimeMillis() % 100000;
        // Create Sub-Foodweb
        int[] nodeList = new int[nodeBiomassList.size()];
        int i = 0;
        for (int node_id : nodeBiomassList.keySet()) {
            nodeList[i++] = node_id;
        }
//        try {
//            //ecosystem.setManipulationID(se.createAndRunSeregenttiSubFoodweb(nodeList, networkName, 0, 0, false));
//        	//HJR
//            SimulationIds simIds = se.createAndRunSeregenttiSubFoodwebForSimJob(nodeList, 
//            		networkName, 0, 0, true);
//            ecosystem.setManipulationID(simIds.getManipId());
//            ecosystem.setNetworkId(simIds.getNetId());
//        } catch (SimulationException ex) {
//            System.err.println(ex.getMessage());
//        }
        // Update Zone Database
        EcosystemDAO.updateManipulationID(ecosystem.getID(), ecosystem.getManipulationID());
        // Initialize Biomass and Additional Parameters
        List<SpeciesZoneType> mSpecies = new ArrayList<SpeciesZoneType>();
        for (Entry<Integer, Integer> entry : nodeBiomassList.entrySet()) {
            int node_id = entry.getKey(), biomass = entry.getValue();
            mSpecies.add(se.createSpeciesZoneType(node_id, biomass));
        }
        se.setParameters2(mSpecies, 1, ecosystem.getManipulationID());
        // First Month Logic
        for (SpeciesZoneType szt : mSpecies) {
            int species_id = ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(szt.getNodeIndex()).getID();
            EcoSpeciesDAO.createSpecies(ecosystem.getID(), species_id, (int) szt.getCurrentBiomass());
        }
    }

    /**
     * Create CSVs Uses: WorldManager, createEcosystem()
     *
     * @param zone
     */
    private static void createCSVs(final Ecosystem ecosystem) {
        new GameTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                String csv = new SimulationEngine().getBiomassCSVString(ecosystem.getManipulationID());
                if (!csv.isEmpty()) {
                    CSVDAO.createBiomassCSV(ecosystem.getManipulationID(), CSVParser.removeNodesFromCSV(csv));
                    // Generate Environment Score CSV
                    List<List<String>> envScoreList = new ArrayList<List<String>>(2);
                    envScoreList.add(new ArrayList<String>(Arrays.asList(new String[]{"", "1"})));
                    envScoreList.add(new ArrayList<String>(Arrays.asList(new String[]{"\"Environment Score\"", "0"})));
                    CSVDAO.createScoreCSV(ecosystem.getID(), CSVParser.createCSV(envScoreList));

                    cancel();
                    Log.printf("CSV [%s] Retrieval Success!", ecosystem.getManipulationID());
                } else {
                    Log.printf_e("Error: CSV [%s] Retrieval Failed!", ecosystem.getManipulationID());
                }
            }
        }, 1000, 3000);
    }

    public static void startEcosystem(Player player) {
        // Get Player Ecosystem
        Ecosystem ecosystem = EcosystemDAO.getEcosystem(player.getWorld().getID(), player.getID());
        if (ecosystem == null) {
            return;
        }else{
        	setEcosystem(ecosystem);
        }
        // Get Ecosystem Zones
        List<Zone> zones = WorldZoneDAO.getZoneList(player.getWorld().getID(), player.getID());
        if (zones.isEmpty()) {
            return;
        }else{
        	setZones(zones);
        }
        // Load Ecosystem Score History
        ecosystem.setScoreCSV(CSVParser.convertCSVtoArrayList(CSVDAO.getScoreCSV(ecosystem.getID())));
        // Ecosystem Reference
        player.setEcosystem(ecosystem);
        // Create Lobby to Contain Ecosystem
        EcosystemLobby lobby = LobbyController.getInstance().createEcosystemLobby(player, ecosystem);
        if (lobby == null) {
            return;
        }else{
        	setLobby(lobby);
        }
        // Send Ecosystem to Player
        if(!Constants.DEBUG_MODE){
	        ResponseEcosystem response = new ResponseEcosystem();
	        response.setEcosystem(ecosystem.getID(), ecosystem.getType(), ecosystem.getScore());
	        response.setPlayer(player);
	        response.setZones(zones);
	        NetworkFunctions.sendToPlayer(response, player.getID());
        }
        // Load Existing Species
        for (Species species : EcoSpeciesDAO.getSpecies(ecosystem.getID())) {
            lobby.getGameEngine().initializeSpecies(species, ecosystem);
        }
        // Recalculate Ecosystem Score
        ecosystem.updateEcosystemScore();

//        zone.setSpeciesChangeList(SpeciesChangeListDAO.getList(zone.getID()));
//        zone.setAddNodeList(ZoneNodeAddDAO.getList(zone.getID()));
        // Update Last Access
        EcosystemDAO.updateTime(ecosystem.getID());
    }

	private static void setLobby(EcosystemLobby l) {
		lobby = l;
	}

	private static void setZones(List<Zone> z) {
		zones = z;
	}
	private static void setEcosystem(Ecosystem e) {
		ecosystem = e;
	}
	
	public static Ecosystem getEcosystem() {
		return ecosystem;
	}
	
	public static EcosystemLobby getLobby() {
		return lobby;
	}

	public static List<Zone> getZones() {
		return zones;
	}


}
