package rr.net.request;

// Java Imports
import java.io.IOException;

// Other Imports
import rr.core.GameClient;
import rr.core.GameServer;
import rr.metadata.Constants;
import rr.model.Player;
import rr.net.response.ResponseLogin;
import shared.util.DataReader;
import shared.util.Log;

/**
 * The RequestLogin class authenticates the user information to log in. Other
 * tasks as part of the login process lies here as well.
 */
public class RequestLogin extends GameRequest {

    // Data
    private String version;
    private String user_id;
    private String password;
    // Responses
    private ResponseLogin responseLogin;

    public RequestLogin() {
        responses.add(responseLogin = new ResponseLogin());
    }

    @Override
    public void parse() throws IOException {
        version = DataReader.readString(dataInput).trim();
        user_id = DataReader.readString(dataInput).trim();
        password = DataReader.readString(dataInput).trim();
    }

    @Override
    public void doBusiness() throws Exception {
        Log.printf("User '%s' is loggin into Running Rhino Server...", user_id);

        Player player = null;
        // Checks if the connecting client meets the minimum version required
        if (version.compareTo(Constants.CLIENT_VERSION) >= 0) {
            if (!user_id.isEmpty()) {
                player = new Player(user_id.hashCode());
            	player.setUsername(user_id);
            	player.setPassword(password);
            }

            if (player == null) {
                responseLogin.setStatus((short) 1); // User info is incorrect
                Log.printf("User '%s' has failed to log in.", user_id);
            } else {
                if (client.getPlayer() == null || player.getID() != client.getPlayer().getID()) {
                    GameClient thread = GameServer.getInstance().getThreadByPlayerID(player.getID());
                    // If account is already in use, remove and disconnect the client
                    if (thread != null) {
                        responseLogin.setStatus((short) 2); // Account is in use
                        thread.removePlayerData();
                        thread.newSession();
                        Log.printf("User '%s' account is already in use.", user_id);
                    } else {
                        // Continue with the login process
                        GameServer.getInstance().setActivePlayer(player);
                        player.setClient(client);
                        // Pass Player reference into thread
                        client.setPlayer(player);
                        // Set response information
                        responseLogin.setStatus((short) 0); // Login is a success
                        responseLogin.setPlayer(player);

                        Log.printf("User '%s' has successfully logged in.", player.getUsername());
                    }
                }
            }
        } else {
            responseLogin.setStatus((short) 3); // Client version not compatible
            Log.printf("User '%s' has failed to log in. (v%s)", player.getUsername(), version);
        }
    }
}
