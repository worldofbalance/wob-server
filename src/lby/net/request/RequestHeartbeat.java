package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.core.GameEngine;
import lby.core.EcosystemLobby;
import shared.util.Log;

/**
 * The RequestHeartbeat class is mainly used to release all pending responses to
 * the client. Also used to keep the connection alive.
 */
public class RequestHeartbeat extends GameRequest {
    static int count = 0;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        client.send();

        if (client.getPlayer() != null) {
            EcosystemLobby lobby = (EcosystemLobby) client.getPlayer().getLobby();

            if (lobby != null) {
                GameEngine gameEngine = lobby.getGameEngine();

                if (gameEngine != null) {
                    gameEngine.run();
                }
            }
        }
    }
}
