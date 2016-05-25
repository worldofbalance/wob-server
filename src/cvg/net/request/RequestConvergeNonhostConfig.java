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
import shared.core.GameClient;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author ivanG
 */
public class RequestConvergeNonhostConfig extends GameRequest {
    private int player_id;
    private int status; 
   
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
       //clients send no args
        Log.consoleln("In NonhostConfig, no args to parse. ");
    }
    
     @Override
    public void process() throws Exception {
        ResponseConvergeNonHostConfig response = new ResponseConvergeNonHostConfig();
        
        
        
        MCMatchManager manager = MCMatchManager.getInstance();
        Log.consoleln("Check if manager is null: " + manager.toString());
        MCMatch match = manager.getMatchByPlayer(client.getPlayerID());
        Log.consoleln("match is: " + match);
        short numRoundsRes = match.getNumRounds();
        short timeWindowRes = match.getTimeWindow();
        short betAmountRes = match.getBetAmount();
        short ecoNumberRes = match.getEcoNumber();
        short allowSlidersRes = match.getSliders();
       
        
        if( numRoundsRes == 0 || timeWindowRes == 0 || betAmountRes == 0){
            numRoundsRes = -1; //match.getNumRounds();
            timeWindowRes = 0; //match.getTimeWindow();
            betAmountRes = 0; //match.getBetAmount();
            ecoNumberRes = 0; //match.getEcoNumber(); 
            allowSlidersRes = 0;
        }
        
        Log.consoleln("Num Rounds is: " + numRoundsRes);
        Log.consoleln("Time window is: " + timeWindowRes);
        Log.consoleln("bet amount is: " + betAmountRes);
        Log.consoleln("Eco number is: " + ecoNumberRes);
        Log.consoleln("allow sliders number is: " + allowSlidersRes);
            response.setNumRounds(numRoundsRes);
            response.setTimeWindow(timeWindowRes);
            response.setBetAmount(betAmountRes);
            response.setEcoNumber(ecoNumberRes);
            response.setSliders(allowSlidersRes);
            client.setBet(betAmountRes);
            client.setBetTime(timeWindowRes);
            client.add(response);
            
        long timeValue = System.currentTimeMillis();
        match.setStartTime(timeValue);
        
    }
}
