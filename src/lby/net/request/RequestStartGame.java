/*
 * StartGame protocol
 * starts a game with another player
 * request parameters:
 * pid - id of player to start game with
 * response parameters:
 * status - status of request -- 0 if invite failed, 1 if game started successfully
 * id - game id -- so minigame server can place you with the right partner
 */

package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.net.response.ResponseStartGame;
import shared.util.DataReader;

public class RequestStartGame extends GameRequest {
    
    private int pid;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        pid = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        ResponseStartGame response = new ResponseStartGame();
        
        response.setStatus(0); //TODO: set actual status
        response.setId(1); //TODO: set actual ID if match is successful
        client.add(response);
    }
}