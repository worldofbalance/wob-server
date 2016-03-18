/*
 * NoWaitForGame protocol
 * Lets the server know that the client no longer wants to play a mini game
 * response parameters:
 * status - 1 for succeed 0 for fail
 */

package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.net.response.ResponseWaitForGame;

public class RequestNoWaitForGame extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        
    }

    @Override
    public void process() throws Exception {
        ResponseWaitForGame response = new ResponseWaitForGame();
        //TODO: remove player from waitlist
        response.setStatus(1); //TODO: reply with status of request
        client.add(response);
    }
}