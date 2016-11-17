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
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import lby.core.badge.BadgeController;
import lby.core.world.WorldController;
import java.nio.file.Paths;
import lby.MiniGameServers;
import shared.metadata.Constants;
import shared.metadata.GameRequestTable;
import shared.model.Account;
import shared.model.Player;
import shared.util.ConfFileParser;
import shared.util.ConfigureException;
import shared.util.ExpTable;
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
            
            MiniGameServers.getInstance().runServers();
            
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
