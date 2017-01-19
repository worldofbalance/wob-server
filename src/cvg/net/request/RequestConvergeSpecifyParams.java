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
    private short allowSliders;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        numRounds = DataReader.readShort(dataInput);
        timeWindow = DataReader.readShort(dataInput);
        betAmount = DataReader.readShort(dataInput);
        ecoNumber = DataReader.readShort(dataInput);
        allowSliders = DataReader.readShort(dataInput);
    }
    
    @Override
    public void process() throws Exception {
        System.out.println("Inside RequestConvergeSpecifyParams");
        System.out.println("1st: eco#, allowSliders, #Rounds, timeWindow, bet");
        System.out.println("" + ecoNumber + " " + allowSliders + " " + numRounds + " " + timeWindow + " " + betAmount);
        int player_id = client.getPlayerID();
        ResponseConvergeSpecifyParams response = new ResponseConvergeSpecifyParams();
        MCMatchManager manager = MCMatchManager.getInstance();
        MCMatch match = manager.getMatchByPlayer(player_id);
        int matchID = match.getMatchID();
        match.setEcoNumber(ecoNumber);
        match.setSliders(allowSliders);
        match.setTimeWindow(timeWindow);
        match.setBetAmount(betAmount);    
        // match.setCurRound((short) 1);
        match.setNumRounds(numRounds);
        System.out.println("2nd: eco#, allowSliders, #Rounds, timeWindow, bet");
        System.out.println("" + ecoNumber + " " + allowSliders + " " + numRounds + " " + timeWindow + " " + betAmount);
        short status = 0;     //0 indicates success
        response.setStatus(status);
        client.add(response);
        //changes to rest time
        long timeValue = System.currentTimeMillis();
        match.setStartTime(timeValue);
    }

    
}
