/*
 * WaitStatus protocol
 * Asks server if anyone has invited us to a game yet
 * if invited, client should then connect to the appropriate server
 * response parameters:
 * status - 1 if invited, 0 if not invited, -1 if you're not currently on a wait list
 * id - game id -- so minigame server can place you with the right partner
 */

package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.net.response.ResponseWaitStatus;
import shared.util.DataReader;

public class RequestWaitStatus extends GameRequest {
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        
    }

    @Override
    public void process() throws Exception {
        ResponseWaitStatus response = new ResponseWaitStatus();
        
        response.setStatus(0); //TODO: set actual status
        response.setId(1); //TODO: set actual ID if match is found
        client.add(response);
    }
}