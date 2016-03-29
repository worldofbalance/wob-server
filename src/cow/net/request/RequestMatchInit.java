package cow.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import cow.core.match.MatchManager;
import shared.util.DataReader;
import shared.util.Log;
import lby.net.request.GameRequest;
import cow.net.response.ResponseMatchInit;
import cow.core.match.*;

public class RequestMatchInit extends GameRequest {

    private int playerID;
    private int matchID;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerID = DataReader.readInt(dataInput);
        matchID = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        ResponseMatchInit response = new ResponseMatchInit();
        MatchManager manager = MatchManager.getInstance();
        short status;

        Log.printf("RequestMatchInit matchID = %d,playerid=%d", matchID,playerID);

		// Assume player is in DB otherwise 
        //Match match = manager.createMatch(playerID1, playerID2);
        Match match = manager.matchPlayerTo(this.matchID, playerID);
        if (match != null) {
            // TODO: add response success constant
            status = 0;
            matchID = match.getMatchID();
        } else {
            // status !=0 means failure
            status = 1;
            Log.printf("Failed to create Match");
        }
        Log.printf("Initializing match for players '%d' and '%d' in match %d",
                playerID, this.matchID, matchID);

        response.setStatus(status);
        response.setMatchID(matchID);
        client.add(response);
    }
}
