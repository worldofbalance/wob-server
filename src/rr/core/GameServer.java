package rr.core;

// Java Imports
import rr.config.GameServerConf;
import rr.db.DAO;
import rr.db.RunnerItemsDAO;
import rr.db.RunnerSpeciesDAO;
import rr.db.SpeciesDAO;
import rr.metadata.Constants;
import rr.metadata.GameRequestTable;
import rr.model.*;
import rr.race.RaceManager;
import shared.util.ConfFileParser;
import shared.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

// Other Imports

/**
 * The GameServer class serves as the main module that runs the server.
 * Incoming connection requests are established and redirected to be managed
 * by another class called the GameClient. Several specialized methods are also
 * stored here to perform other specific needs.
 */
public class GameServer {

    // Singleton Instance
    private static GameServer gameServer;
    // Server Variables
    private boolean isDone; // Server Loop Flag
    private GameServerConf configuration; // Stores server config. variables
    private ServerSocket serverSocket;
    private ExecutorService clientThreadPool;
    // Reference Tables
    private Map<String, GameClient> activeThreads = new HashMap<String, GameClient>(); // Session ID -> Client
    private Map<Integer, Player> activePlayers = new HashMap<Integer, Player>(); // Player ID -> Player
    private Map<Integer, RunnerSpecies> runnerSpeciesMap = new HashMap<Integer, RunnerSpecies>(); // RunnerItems ID -> RunnerItems
    private Map<Integer, RunnerItems> runnerItemsMap = new HashMap<Integer, RunnerItems>(); // RunnerItems ID -> RunnerItems
    private Map<Integer, AnimalType> animalTypes = new HashMap<Integer, AnimalType>(); // Species ID -> Animal
    private Map<Integer, PlantType> plantTypes = new HashMap<Integer, PlantType>(); // Species ID -> Plant
    // RaceManager that is responsible for setting up a single game.
    RaceManager gameManager;
    
    /**
     * Create the GameServer by setting up the request types and creating a
     * connection with the database.
     */
    public GameServer() {
        // Load configuration file
        configure();
        // Initialize tables for global use
        GameRequestTable.init(); // Contains request codes and classes
        Log.console("Initialized Requests");
        // Initialize database connection
        if (DAO.getInstance() == null) {
            Log.println_e("Database Connection Failed!");
            System.exit(-1);
        }
        // Preload world-related objects
        initialize();
        // Thread Pool for Clients
        clientThreadPool = Executors.newCachedThreadPool();
        
        // Instantiate the RaceManager
        gameManager = new RaceManager();
        
        //Hardcoded for testing a protype of a game
        //game = new Race();
    }

    public static GameServer getInstance() {
        if (gameServer == null) {
            gameServer = new GameServer();
        }

        return gameServer;
    }

    /**
     * Load values from a configuration file.
     */
    public final void configure() {
        String serverConf = "conf/gameServer.conf";
        File f = new File(serverConf);
        
        if (!f.exists()) {
            // get current absolute path
            CodeSource codeSource = GameServer.class.getProtectionDomain().getCodeSource();
            File jarFile;
            try {
                jarFile = new File(codeSource.getLocation().toURI().getPath());
                serverConf = jarFile.getParentFile().getPath() + "/../conf/gameServer.conf";
            } catch (URISyntaxException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        configuration = new GameServerConf();
        configuration.setConfRecords(new ConfFileParser(serverConf).parse());
    }

    /**
     * Initialize the GameServer by loading a few things into memory.
     */
    public final void initialize() {
        setupSpeciesTypes();
        
        // load runner species
        try
        {
            Log.println("Now loading list of RunnerSpecies to memory...");
            runnerSpeciesMap = RunnerSpeciesDAO.getRunnerSpecies();
        }
        catch (SQLException e)
        {
            Log.println("Critical error. Failed to load list of RunnerSpecies.");
        }
        Log.println("RunnerSpecies list successfully loaded.");
        
        // load runner items
        try
        {
            Log.println("Now loading list of RunnerItems to memory...");
            runnerItemsMap = RunnerItemsDAO.getRunnerItems();
        }
        catch (SQLException e)
        {
            Log.println("Critical error. Failed to load list of RunnerItems.");
        }
        
        Log.println("RunnerItems list successfully loaded.");
    }

    /**
     * Retrieve species from the database and load data into memory.
     */
    public void setupSpeciesTypes() {
        try {
            Log.println("Loading Plant Types...");

            for (PlantType plant : SpeciesDAO.getPlants()) {
                plantTypes.put(plant.getID(), plant);
                plant.setAvgBiomass(500); // !!!
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Log.println_e("Failed to retrieve Plant species.");
        }

        try {
            Log.println("Loading Animal Types...");

            for (AnimalType animal : SpeciesDAO.getAnimals()) {
                animalTypes.put(animal.getID(), animal);
                animal.setAvgBiomass(500); // !!!
            }
        } catch (SQLException ex) {
            Log.println_e("Failed to retrieve Animal species.");
        }

        // Replace predator identifiers with actual instances
        Log.println("Resolving Predators...");
        for (PlantType plant : plantTypes.values()) {
            for (int predator_id : plant.getPredatorIDs()) {
                if (animalTypes.containsKey(predator_id)) {
                    plant.resolvePredator(animalTypes.get(predator_id));
                }
            }
        }

        for (AnimalType animal : animalTypes.values()) {
            for (int predator_id : animal.getPredatorIDs()) {
                if (animalTypes.containsKey(predator_id)) {
                    animal.resolvePredator(animalTypes.get(predator_id));
                }
            }
        }

        // Replace prey identifiers with actual instances
        Log.println("Resolving Preys...");
        for (AnimalType animal : animalTypes.values()) {
            for (int prey_id : animal.getPreyIDs()) {
                if (animalTypes.containsKey(prey_id)) {
                    animal.resolvePrey(animalTypes.get(prey_id));
                } else if (plantTypes.containsKey(prey_id)) {
                    animal.resolvePrey(plantTypes.get(prey_id));
                }
            }
        }
    }

    /**
     * Run the game server by waiting for incoming connection requests.
     * Establishes each connection and stores it into a GameClient to manage
     * incoming and outgoing activity.
     */
    private void run() {
        try {
            // Open a connection using the given port to accept incoming connections
            serverSocket = new ServerSocket(configuration.getPortNumber());
            Log.printf("Server has started on port: %d", serverSocket.getLocalPort());
            Log.println("Waiting for clients...");
            // Loop indefinitely to establish multiple connections
            while (!isDone) {
                try {
                    // Accept the incoming connection from client
                    Socket clientSocket = serverSocket.accept();
                    Log.printf("%s is connecting...", clientSocket.getInetAddress().getHostAddress());
                    // Create a runnable instance to represent a client that holds the client socket
                    String session_id = createUniqueID();
                    GameClient client = new GameClient(session_id, clientSocket);
                    // Keep track of the new client thread
                    addToActiveThreads(client);
                    // Initiate the client
                    clientThreadPool.submit(client);
                    
                    
//                     Add client to the GamesManager
//                     gameManager.addClientToGame(client);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException ex) {
            Log.println_e(ex.getMessage());
        }
    }
    
    public RunnerSpecies getRunnerSpecies(int runnerSpeciesID) {
        RunnerSpecies rs = null;

        if (runnerSpeciesMap.containsKey(runnerSpeciesID))
        {
            rs = runnerSpeciesMap.get(runnerSpeciesID);
        }

        return rs;
    }
    
    public RunnerItems getRunnerItems(int runnerItemsID)
    {
        RunnerItems ri = null;
        
        if (runnerItemsMap.containsKey(runnerItemsID))
        {
            ri = runnerItemsMap.get(runnerItemsID);
        }

        return ri;
    }

    public static String createUniqueID() {
        return UUID.randomUUID().toString();
    }

    public SpeciesType getSpecies(int species_id) {
        SpeciesType species = null;

        if (plantTypes.containsKey(species_id)) {
            species = plantTypes.get(species_id);
        } else if (animalTypes.containsKey(species_id)) {
            species = animalTypes.get(species_id);
        }

        return species;
    }

    public SpeciesType getSpeciesTypeByNodeID(int node_id) {
        return getSpeciesTypeByNodeList(new int[]{node_id});
    }

    public SpeciesType getSpeciesTypeByNodeList(int[] nodeList) {
        for (AnimalType animal : animalTypes.values()) {
            if (animal.equalsNodeList(nodeList)) {
                return (SpeciesType) animal;
            }
        }

        for (PlantType plant : plantTypes.values()) {
            if (plant.equalsNodeList(nodeList)) {
                return (SpeciesType) plant;
            }
        }

        return null;
    }

    public Map<String, GameClient> getActiveThreads() {
        return activeThreads;
    }

    /**
     * Get the GameClient thread for the player using the player ID.
     *
     * @param playerID holds the player ID
     * @return the GameClient thread
     */
    public GameClient getThreadByPlayerID(int playerID) {
        for (GameClient client : activeThreads.values()) {
            Player player = client.getPlayer();

            if (player != null && player.getID() == playerID) {
                return client;
            }
        }

        return null;
    }

    public void addToActiveThreads(GameClient client) {
        activeThreads.put(client.getID(), client);
    }

    public List<Player> getActivePlayers() {
        return new ArrayList<Player>(activePlayers.values());
    }

    public Player getActivePlayer(int player_id) {
        return activePlayers.get(player_id);
    }

    public void setActivePlayer(Player player) {
        activePlayers.put(player.getID(), player);
    }
    
    public void removeActivePlayer(int player_id) {
        activePlayers.remove(player_id);
    }

    /**
     * Delete a player's GameClient thread out of the activeThreads
     *
     * @param session_id              The id of the thread.
     */
    public void deletePlayerThreadOutOfActiveThreads(String session_id) {
        activeThreads.remove(session_id);
    }

    /**
     * Initiates the Race Server by configuring and running it. Restarts
     * whenever it crashes.
     *
     * @param args contains additional launching parameters
     */
    public static void main(String[] args) {
        try {
            Log.printf("World of Balance Server v%s is starting...\n", Constants.CLIENT_VERSION);

            gameServer = new GameServer();
            gameServer.run();
        } catch (Exception ex) {
            Log.println_e("Server Crashed!");
            Log.println_e(ex.getMessage());

            try {
                Thread.sleep(10000);
                Log.println_e("Server is now restarting...");
                GameServer.main(args);
            } catch (InterruptedException ex1) {
                Log.println_e(ex1.getMessage());
            }
        }
    }
}
