package cos.net.request;

// Java Imports

import cos.core.GameEngine;
import cos.core.lobby.EcosystemLobby;

import java.io.DataInputStream;
import java.io.IOException;

// Other Imports

/**
 * The RequestHeartbeat class is mainly used to release all pending responses to
 * the client. Also used to keep the connection alive.
 */
public class RequestHeartbeat extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        client.send();
        //System.out.println("I'm alive at ResponseMatchInit()");

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
