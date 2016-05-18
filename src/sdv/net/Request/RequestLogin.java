/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.net.Request;

import sdv.core.GameClient;
import sdv.core.GameServer;
import java.io.IOException;
import shared.metadata.Constants;
import sdv.model.Player;
import sdv.net.Response.ResponseLogin;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author anu
 *  The RequestLogin class authenticates the user information to log in. Other
 * tasks as part of the login process lies here as well.
 */
public class RequestLogin extends GameRequest{


    // Data
    private String version;
    private String user_id;
    private String password;
    private int account_id;
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
        Log.printf("User '%s' is connecting...", user_id);

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
                if (client.getPlayer() == null || player.getPlayer_id() != client.getPlayer().getPlayer_id()) {
                    GameClient thread = GameServer.getInstance().getThreadByPlayerID(player.getPlayer_id());
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
