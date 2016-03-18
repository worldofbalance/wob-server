/*
 * WaitingList protocol
 * Fetch the waiting list for a specific game
 * request parameters:
 * gameType - which game
 * response parameters:
 * series of strings (player name) and ints (player id) terminated by an empty string and id of -1
 */

package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.net.response.ResponseWaitList;
import shared.util.DataReader;

public class RequestWaitList extends GameRequest {

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
        ResponseWaitList response = new ResponseWaitList();
        
        //TODO: populate response with players on waiting list for selected game
        response.addName("Player1");
        response.addId(1);
        client.add(response);
    }
}