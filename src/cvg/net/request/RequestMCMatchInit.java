package cvg.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import shared.util.DataReader;
import shared.util.Log;
import lby.net.request.GameRequest;
import cvg.net.response.ResponseMCMatchInit;
import cvg.match.*;
import java.util.Map;

// DH change - copied + modified from CW

public class RequestMCMatchInit extends GameRequest {

    private int playerID;
    private int matchID;
    private short host;
    private String playerName;
    private int[] scores = new int[5];
    Map<Integer, MCMatchPlayer> playerList;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerID = DataReader.readInt(dataInput);
        matchID = DataReader.readInt(dataInput);
        host = DataReader.readShort(dataInput);
        playerName = DataReader.readString(dataInput).trim();
    }

    @Override
    public void process() throws Exception {
        ResponseMCMatchInit response = new ResponseMCMatchInit();
        MCMatchManager manager = MCMatchManager.getInstance();
        short status;

        Log.printf("MC matchID = %d", matchID);

	// Assume player is in DB otherwise 
        //Match match = manager.createMatch(playerID1, playerID2);
        MCMatch match = manager.matchPlayerTo(this.matchID, playerID);
        playerList = match.playerList;
        if (match != null) {
            // TODO: add response success constant
            status = 0;
            matchID = match.getMatchID();
        } else {
            // status !=0 means failure
            status = 1;
            Log.printf("Failed to create Match");
        }
        Log.printf("Initializing match for player '%d' in match %d",
                playerID, matchID);
        Log.println("Player name: " + playerName);
        
        Map<Integer, Integer> matchIDList = manager.getMatchIDList();
        Log.println("Player ID /   Match ID");
        for (Integer key: matchIDList.keySet()) {
            System.out.println(key + "         /    " + matchIDList.get(key));
        }

        response.setStatus(status);
        response.setMatchID(matchID);
        // playerList.get(playerID).setBetStatus(0);
        playerList.get(playerID).setWinnings(0);
        playerList.get(playerID).setPlayerName(playerName);
        // Initialize past scores to all -1 (no play yet) 
        for (int i = 0; i < 5; i++) {
            scores[i] = -1;
        }
        playerList.get(playerID).setScores(scores);
        client.add(response);
        client.setPlayerID(playerID);
        client.setMatchID(matchID);
        client.setHost(host);
        Log.println("This client's host value is: " + host);
        long timeValue = System.currentTimeMillis();
        Log.println("Current time is: " + timeValue);
        
        if (host == 1) {
            manager.getMatch(matchID).setStartTime(timeValue);
        }
    }
}
