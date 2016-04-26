/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.request;

import cvg.match.MCMatch;
import cvg.match.MCMatchManager;
import cvg.net.response.ResponseConvergeNonHostConfig;
import java.io.DataInputStream;
import java.io.IOException;
import lby.net.request.GameRequest;
import shared.util.Log;

/**
 *
 * @author ivanG
 */
public class RequestConvergeNonhostConfig extends GameRequest {
    private int player_id;
    
   
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
       //clients send no args
        Log.consoleln("Nonhost has no parameters to parse, but it does send a response.");
    }
    
     @Override
    public void process() throws Exception {
        ResponseConvergeNonHostConfig response = new ResponseConvergeNonHostConfig();
        
        MCMatchManager manager = MCMatchManager.getInstance();
        MCMatch match = manager.getMatchByPlayer(player_id);
        
        response.setNumRounds(match.getNumRounds());
        response.setTimeWindow(match.getTimeWindow());
        response.setBetAmount(match.getBetAmount());
        response.setEcoNumber(match.getEcoNumber());
        client.add(response);
    }
}
