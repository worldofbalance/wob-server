/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.request;

import cvg.match.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lby.net.request.GameRequest;
import cvg.net.response.ResponseConvergeGetFinalScores;
import shared.util.Log;

/**
 *
 * @author David Hoff
 */
public class RequestConvergeGetFinalScores extends GameRequest {
    
    int [] playerId = new int[5];
    int [] playerWinnings = new int[5];
    int [] playerLastImprove = new int[5];
    int index;
    MCMatchPlayer MCPlayer;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    	Log.println("Parsing RequestConvergeGetFinalScores");
    }

    @Override
    public void process() throws Exception {
        ResponseConvergeGetFinalScores response = new ResponseConvergeGetFinalScores();
        int player_id = client.getPlayerID();
        Log.println("RequestConvergeGetFinalScores for player ID: " + player_id);
        MCMatchManager manager = MCMatchManager.getInstance();
        MCMatch match = manager.getMatchByPlayer(player_id);
        Map<Integer, MCMatchPlayer> playersList = match.getPlayers();
        Log.println("RequestConvergeGetFinalScores, player id/winnings/last improve");
        index = 0;
        for (Map.Entry<Integer, MCMatchPlayer> entry : playersList.entrySet()) {
            Integer key = entry.getKey();
            MCPlayer = entry.getValue();
            if (MCPlayer.getLeftGame()) {
                continue;
            }
            playerId[index] = key;
            playerWinnings[index] = MCPlayer.getWinnings();
            playerLastImprove[index] = MCPlayer.getImproveAmount(match.getNumRounds());
            Log.println(key + " " + playerWinnings[index] + " " + playerLastImprove[index]);
            index++;
        }           
        while (index < 5) {
            playerId[index] = -1;
            playerWinnings[index] = 0;
            playerLastImprove[index] = 0;
            index++;
        } 
        
        response.setPlayerId(playerId);
        response.setPlayerWinnings(playerWinnings);
        response.setPlayerLastImprove(playerLastImprove);
        client.add(response);
        Log.println("Processed RequestConvergeGetFinalScores");
    }
}
