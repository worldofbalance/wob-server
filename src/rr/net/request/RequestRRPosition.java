/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import rr.core.GameServer;
import rr.db.RaceDAO;
import java.io.IOException;
import rr.net.response.ResponseRRPosition;
import rr.race.RaceManager;
import rr.race.RacePlayer;
import shared.util.DataReader;

/**
 * Receive the current position of the player and send to opponent.
 * Receives 2 Strings in the packet, and parses them to floats 
 * representing player's x,y coordinates. Sends responds to opponent
 * @author markfavis
 */
public class RequestRRPosition extends GameRequest {
    
    private float x, y;
    private int opponentID;
    private ResponseRRPosition responseRRPosition;
    
    @Override
    public void parse() throws IOException {

        x = Float.parseFloat(DataReader.readString(dataInput));
        y = Float.parseFloat(DataReader.readString(dataInput));
    }

    @Override
    public void doBusiness() throws Exception {
        RacePlayer player; // the RacePlayer sending the request
        System.out.println("X:  " +  x + "Y :  " + y);
    
        responseRRPosition = new ResponseRRPosition();
        responseRRPosition.setX(x);
        responseRRPosition.setY(y);
        
//        RaceManager.getInstance();
//        client.getPlayer().getID();
        
        //Debugging
//        Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
//        Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        //The playerID of the oppenet of the player who sent the request
        opponentID = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
                
        //NetworkManager.addResponseForUser(opponentID, responsekeyboard);
        
        // get the player and define x and y at the time of request
        player = RaceManager.manager.getRaceByPlayerID(opponentID).getPlayers().get(opponentID);
        player.setX(x);
        player.setY(y);
        
        GameServer.getInstance().getThreadByPlayerID(opponentID).send(responseRRPosition);
        
        RaceDAO.updateRace(player); // write to DB
    }
    
}
