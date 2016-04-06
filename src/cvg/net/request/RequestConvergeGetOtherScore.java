/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.request;

import cvg.match.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import lby.net.request.GameRequest;
import cvg.net.response.ResponseConvergeGetOtherScore;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author David Hoff
 */
public class RequestConvergeGetOtherScore extends GameRequest {
    
    private int otherPlayerID;
    private Map<Integer, MCMatchPlayer> playerList;
    private MCMatchPlayer otherPlayer;
    private int[] scores = new int[5];  // stores the 5 most recent score values
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    	Log.consoleln("Parsing RequestConvergeGetOtherScore");
        otherPlayerID = DataReader.readInt(dataInput);
    }
    

    @Override
    public void process() throws Exception {
        ResponseConvergeGetOtherScore response = new ResponseConvergeGetOtherScore();
        
        MCMatchManager manager = MCMatchManager.getInstance();
        MCMatch match = manager.getMatchByPlayer(otherPlayerID);
        playerList = match.getPlayers();
        otherPlayer = playerList.getPlayer(otherPlayerID);
        scores = otherPlayer.getScores();
        Log.println("This player is: " + client.getPlayerID());
        Log.println("The other player is: " + otherPlayerID);
        
        response.setScore0(scores[0]);
        response.setScore1(scores[1]);
        response.setScore2(scores[2]);
        response.setScore3(scores[3]);
        response.setScore4(scores[4]);        

        Log.consoleln("Processed RequestConvergeGetOtherScore. The Scores are:");         
        for (int i = 0; i < 5; i++) {
            Log.println(" " + i + " " + scores[i]);
            }

        client.add(response);        
    }
}
