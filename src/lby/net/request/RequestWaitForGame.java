/*
 * WaitForGame protocol
 * Lets the server know that the client wants to play a mini game
 * request parameters:
 * gameType - which game to wait for
 * response parameters:
 * status - 1 for succeed 0 for fail
 */

package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.util.DataReader;
import lby.net.response.ResponseWaitForGame;

public class RequestWaitForGame extends GameRequest {

    private int gameType;
    /* which game the client wants to play
     * 0 - Don't eat me! (no server required)
     * 1 - Cards of the Wild
     * 2 - Running Rhino
     * 3 - Clash of Species
     */

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        gameType = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        ResponseWaitForGame response = new ResponseWaitForGame();
        //TODO: add player to waiting list for game
        response.setStatus(1); //TODO: reply with status of request
        client.add(response);
    }
}
