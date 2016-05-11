/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.request;

import cvg.match.MCMatch;
import cvg.match.MCMatchManager;
import cvg.net.response.ResponseConvergeSpecifyParams;
import java.io.DataInputStream;
import java.io.IOException;
//import jdk.nashorn.internal.runtime.Debug;
import lby.net.request.GameRequest;
import shared.util.DataReader;

/**
 *
 * @author ivanG
 */
public class RequestConvergeSpecifyParams extends GameRequest{
    private short numRounds;
    private short timeWindow;
    private short betAmount;
    private short ecoNumber;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        numRounds = DataReader.readShort(dataInput);
        timeWindow = DataReader.readShort(dataInput);
        betAmount = DataReader.readShort(dataInput);
        ecoNumber = DataReader.readShort(dataInput);
    }
    
    @Override
    public void process() throws Exception {
        System.out.println("Inside RequestConvergeSpecifyParams");
        int player_id = client.getPlayerID();
        ResponseConvergeSpecifyParams response = new ResponseConvergeSpecifyParams();
        MCMatchManager manager = MCMatchManager.getInstance();
        MCMatch match = manager.getMatchByPlayer(player_id);
        int matchID = match.getMatchID();
        match.setNumRounds(numRounds);
        match.setTimeWindow(timeWindow);
        match.setBetAmount(betAmount);
        match.setEcoNumber(ecoNumber);
        
        short status = 0;//0 indicates success
        response.setStatus(status);
        client.setBetTime(timeWindow);
        client.setBet(betAmount);//updates bet to custom value rather than hardcoded value
        client.add(response);
        //changes to rest time
        long timeValue = System.currentTimeMillis();
        match.setStartTime(timeValue);
    }

    
}
