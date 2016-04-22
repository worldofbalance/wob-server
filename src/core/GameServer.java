package core;

import PlayTime.PreySpawning;
import java.util.concurrent.Executors;
import metadata.Constants;
import utility.Log;
import configuration.GameServerConf;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.ConfFileParser;
import metadata.GameRequestTable;
import model.Player;

/**
 *
 * @author anu
 */
public class GameServer {
    
    // Singleton Instance
    private static GameServer gameServer;
    //server variables
     private GameServerConf configuration; // Stores server config. variables
     private ExecutorService clientThreadPool;
     private ServerSocket serverSocket;
     private boolean isDone; // Server Loop Flag
      // Reference Tables
    private Map<String, GameClient> activeThreads = new HashMap<String, GameClient>(); // Session ID -> Client
    private Map<Integer, Player> activePlayers = new HashMap<Integer, Player>(); // Player ID -> Player
     
     public GameServer() {
        // Load configuration file
        configure();
        // Initialize tables for global use
        GameRequestTable.init(); // Contains request codes and classes
        // Initialize database connection
        /*
        if (DAO.getInstance() == null) {
            Log.println_e("Database Connection Failed!");
            System.exit(-1);
        }
        */
        // Preload world-related objects
        // initialize();
        // Thread Pool for Clients
        clientThreadPool = Executors.newCachedThreadPool();
        // generate random spawning points 
        Log.printf("Spawning prey location saved");
        PreySpawning.getInstance().spawn();
        //TODO: Instantiate the PreyTimeManager
    }
     
      public static GameServer getInstance() {
        if (gameServer == null) {
            gameServer = new GameServer();
        }

        return gameServer;
    }
      
       public void deletePlayerThreadOutOfActiveThreads(String session_id) {
        activeThreads.remove(session_id);
    }
       
        public void removeActivePlayer(int player_id) {
        activePlayers.remove(player_id);
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
    
    private void run() throws IOException {
         Log.printf("Sea Divided v%s is starting...\n", Constants.CLIENT_VERSION);
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
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException ex) {
            Log.println_e(ex.getMessage());
        }
        finally{
        serverSocket.close();
        }
    }
    
     public void addToActiveThreads(GameClient client) {
        activeThreads.put(client.getID(), client);
    }
     
      public static String createUniqueID() {
        return UUID.randomUUID().toString();
    }
     
     public GameClient getThreadByPlayerID(int playerID) {
        for (GameClient client : activeThreads.values()) {
            Player player = client.getPlayer();

            if (player != null && player.getPlayer_id() == playerID) {
                return client;
            }
        }

        return null;
    }
     
     public void setActivePlayer(Player player) {
        activePlayers.put(player.getPlayer_id(), player);
    }
     
     public Map<String, GameClient> getActiveThreads() {
        return activeThreads;
    }
    
     public List<Player> getActivePlayers() {
        return new ArrayList<Player>(activePlayers.values());
    }
    
       public Player getActivePlayer(int player_id) {
        return activePlayers.get(player_id);
    }
      public static void main(String[] args) {
        try {
            Log.printf("Sea Divided v%s is starting...\n", Constants.CLIENT_VERSION);

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
        System.exit(0);
    }
}
