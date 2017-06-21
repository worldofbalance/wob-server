package shared.core;

// Java Imports
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Other Imports
import conf.Configuration;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import lby.core.badge.BadgeController;
import lby.core.world.WorldController;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimerTask;
import lby.MiniGameServers;
import shared.db.PlayerDAO;
import shared.db.EcosystemDAO;
import shared.db.SpeciesChangeListDAO;
import shared.metadata.Constants;
import shared.metadata.GameRequestTable;
import shared.model.Account;
import shared.model.Ecosystem;
import shared.model.Player;
import shared.util.ConfFileParser;
import shared.util.ConfigureException;
import shared.util.ExpTable;
import shared.util.GameTimer;
import shared.util.Log;

/**
 * The GameServer class serves as the main module that runs the server. Incoming
 * connection requests are established and redirected to be managed by another
 * class called the GameClient. Several specialized methods are also stored here
 * to perform other specific needs.
 */
public class GameServer {

    // Singleton Instance
    private static GameServer server;
    // Configuration Variables
    private final int port;
    private final int num_threads;
    // Objects
    private final ServerSocket serverSocket;
    private final List<ClientHandler> clientHandlerThreads = Collections.synchronizedList(new ArrayList<ClientHandler>());
    // Lookup Tables
    private final Map<String, GameClient> activeClients = new HashMap<String, GameClient>(); // Session ID -> Client
    private final Map<Integer, Account> activeAccounts = new HashMap<Integer, Account>(); // Account ID -> Account
    private final Map<Integer, Player> activePlayers = new HashMap<Integer, Player>(); // Player ID -> Player
    // Other
    private boolean isActive = true; // Server Loop Flag    
    private int mCount;
    private final GameTimer ecoUpdateTimer = new GameTimer();
    private static int world_id;
    private final static int ECC_UPDATE_CYCLE_DEFAULT = 24;   // Default update all ecosystems once per day, every 24 hours
    private final static int ECC_UPDATE_STAGGER = 1000 * 20;   // Stagger ecosystem updates by 20 seconds
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    /* This is the path name in the server. Must be updated if a new server path is used  */
    public final static String SERVER_PATH = "/project/wob_server";
    /* This is food web image block size to send to client. 
     * Must match value in client, WorldController class
     * Must be under 32K limit
     */
    public final static int FOOD_WEB_BLOCK_SIZE = 32000;
    
    

    /**
     * Create the GameServer by setting up the request types and creating a
     * connection with the database.
     *
     * @param port
     * @param num_threads
     * @throws IOException
     */
    public GameServer(int port, int num_threads) throws IOException {
        this.port = port;
        this.num_threads = num_threads;

        serverSocket = new ServerSocket(port);
    }

    public static GameServer getInstance() {
        return server;
    }

    /**
     * Configure tables.
     * @throws ConfigureException
     */
    public void configure() throws ConfigureException {
        // Initialize tables for global use
        ServerResources.init();
        GameRequestTable.init(); // Contains request codes and classes
        ExpTable.init(); // Contains experience required per level
        // Update Badge Thresholds
        WorldController.getInstance().init();
        BadgeController.setBadgeScores();
    }

    /**
     * Run the game server by waiting for incoming connection requests.
     * Establishes each connection and stores it into a GameClient to manage
     * incoming and outgoing activity.
     */
    private void run() {
        Log.consoleln("Now accepting connections...");
        /*
        try {
            String cmd, s;
            Process p;
            
            cmd = "atn-generate-food-web.py --parent-dir /home/wob_server from-node-ids 4 5 7 73 74 87";
            System.out.println("Executing: " + cmd);
            
            p = Runtime.getRuntime().exec(cmd); 
            System.out.println("Executed");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = stdInput.readLine()) != null) {
                System.out.println("Out:" + s);
            }

            int exitVal = p.waitFor();
            System.out.println("ExitValue: " + exitVal);
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
        */
        
        // Loop indefinitely to establish multiple connections
        while (isActive) {
            try {
                // Accept the incoming connection from client
                Socket clientSocket = serverSocket.accept();
                Log.printf("%s is connecting...", clientSocket.getInetAddress().getHostAddress());
                // "Random" ID
                String session_id = UUID.randomUUID().toString();
                // Create a runnable instance to represent a client that holds the client socket
                GameClient client = new GameClient(session_id, clientSocket);
                activeClients.put(client.getID(), client);
                // Keep track of the new client thread
                if (clientHandlerThreads.size() > num_threads) {
                    Collections.sort(clientHandlerThreads, ClientHandler.SizeComparator);
                    clientHandlerThreads.get(0).add(client);
                } else {
                    ClientHandler handler = new ClientHandler(client);
                    handler.start();

                    clientHandlerThreads.add(handler);
                }
            } catch (IOException ex) {
                Log.println_e(ex.getMessage());
            }
        }
    }

    public void shutdown() {
        synchronized (this) {
            isActive = false;

            for (GameClient client : activeClients.values()) {
                client.end();
            }
        }
    }

    public int getPort() {
        return port;
    }

    public int getNumThreads() {
        return num_threads;
    }

    public void removeClientHandler(ClientHandler handler) {
        synchronized (clientHandlerThreads) {
            clientHandlerThreads.remove(handler);
        }
    }

    public GameClient getActiveClient(String session_id) {
        return activeClients.get(session_id);
    }

    public void setActiveClient(GameClient client) {
        activeClients.put(client.getID(), client);
    }

    public List<GameClient> getActiveClients() {
        return new ArrayList<GameClient>(activeClients.values());
    }

    public void removeActiveClient(String session_id) {
        activeClients.remove(session_id);
    }

    public boolean hasClient(String session_id) {
        return activeClients.containsKey(session_id);
    }

    public Account getActiveAccount(int account_id) {
        return activeAccounts.get(account_id);
    }

    public void setActiveAccount(Account account) {
        activeAccounts.put(account.getID(), account);
    }

    public List<Account> getActiveAccounts() {
        return new ArrayList<Account>(activeAccounts.values());
    }

    public void removeActiveAccount(int account_id) {
        activeAccounts.remove(account_id);
    }

    public boolean hasAccount(int account_id) {
        return activeAccounts.containsKey(account_id);
    }

    public Player getActivePlayer(int player_id) {
        return activePlayers.get(player_id);
    }

    public void setActivePlayer(Player player) {
        activePlayers.put(player.getID(), player);
    }

    public List<Player> getActivePlayers() {
        return new ArrayList<Player>(activePlayers.values());
    }

    public void removeActivePlayer(int player_id) {
        activePlayers.remove(player_id);
    }

    public boolean hasPlayer(int player_id) {
        return activePlayers.containsKey(player_id);
    }
    
    void startEcosystemUpdate() {    
        mCount = 1;
        ecoUpdateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mCount--;
                System.out.println("Hour(s) remaining until next simulation = " + mCount);             
                if (mCount <= 0) {
                    mCount = getCycle();
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println("Simulations starting at: " + dtf.format(now));
                    ecosystemUpdate();    
                    System.out.println("Hour(s) remaining until next simulation = " + mCount);
                } 
           }
        }, 1000 * 30, 1000 * 60 * 60);
    }
   
    void ecosystemUpdate() {
        Log.println("GameServer, ecosystemUpdate()");
        SpeciesChangeListDAO.setDay(SpeciesChangeListDAO.getDay() + 1);
        ArrayList<Integer> playerIds = EcosystemDAO.getPlayerIds(world_id);
        GameTimer ecoSimTimer = new GameTimer();
        for (int i = 0; i < playerIds.size(); i++) {
            int player_id = playerIds.get(i);
            ecoSimTimer.schedule(createEcosystemUpdateTask(player_id), ECC_UPDATE_STAGGER * i);
        }
    }

    TimerTask createEcosystemUpdateTask(int player_id) {
        TimerTask ecosystemUpdateTask = new TimerTask() {
            @Override
            public void run() {
                Log.println("GameServer: calling createEcosystemUpdateTask for player_id = " + player_id);
                Player player = PlayerDAO.getPlayer(player_id);
                WorldController.enterWorld(player, world_id);
                Ecosystem ecosystem = player.getEcosystem();
                if (ecosystem != null) {                    
                    ecosystem.getGameEngine().forceSimulation();
                } else {
                    Log.println("GameServer, createEcosystemUpdateTask: null ecosystem for player_id = " + player_id);                    
                }
           }
        };
        return ecosystemUpdateTask;
    }
    
    int getCycle() {        
        int count = ECC_UPDATE_CYCLE_DEFAULT;
        Properties prop = new Properties();
	InputStream input = null;
        String sep = System.getProperty("file.separator"); 
        String filePath = "src" + sep + "conf" + sep + "simulation" + sep + "timer.properties";
        try {            
            input = new FileInputStream(filePath);
            // load a properties file
            prop.load(input);            
            String cycle = prop.getProperty("cycle"); 

            // Value is in minutes
            System.out.println("eccUpdateCycle value read from timer.properties = " + cycle);            
            count = Integer.parseInt(cycle);
        } catch (Exception e) {
            Log.println_e("Failed to read eccUpdateCycle from properties: " + e.toString());
        }
        return count; 
    }
    

    /**
     * Initiates the Game Server by configuring and running it. Restarts
     * whenever it crashes.
     *
     * @param args contains additional launching parameters
     */
    public static void main(String[] args) {
        Log.printf("World of Balance Lobby Server is starting on port: %d", Configuration.lobbyPortNumber);
        try {
            server = new GameServer(Configuration.lobbyPortNumber, Constants.MAX_CLIENT_THREADS);
            server.configure();            
            Log.println("WoB current day is " + SpeciesChangeListDAO.fetchDay());
            MiniGameServers.getInstance().runServers();
            world_id = WorldController.getInstance().first().getID();
            server.startEcosystemUpdate();
            Log.println("Start Ecosystem periodic update");            
            server.run();
        } catch (IOException ex) {
            Log.printf_e("Failed to start server. Port %d is already in use", Configuration.lobbyPortNumber);
        } catch (ConfigureException ex) {
            Log.printf_e(ex.getMessage());
        } catch (Exception ex) {
            Log.println_e("Server Crashed!");
            Log.println_e(ex.getMessage());
        }

        System.exit(0);
    }    
}
