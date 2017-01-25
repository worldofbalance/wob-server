/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import rr.core.GameServer;
import java.io.IOException;
import rr.net.response.RRResponseSpecies;
import rr.race.RaceManager;
import shared.util.DataReader;

/**
 * Player sends their Species ID. 
 * Server responds to player’s opponent with the ID.
 * @author Sbc-ComEx
 */
public class RequestSpecies extends GameRequest {
    
    private int id;
    private int opponentID;
    private RRResponseSpecies rrResponseSpecies;
    
    
     public void parse() throws IOException {
        id = DataReader.readInt(dataInput);
 
    }

    @Override
    public void doBusiness() throws Exception {
        
        rrResponseSpecies = new RRResponseSpecies();
        rrResponseSpecies.setId(id);
       System.out.println("specie: " + id);
//        RaceManager.getInstance();
//        client.getPlayer().getID();
        
        //Debugging
//        Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
//        Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        //The playerID of the oppenet of the player who sent the request
        opponentID = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
                
        //NetworkManager.addResponseForUser(opponentID, responsekeyboard);
        
        GameServer.getInstance().getThreadByPlayerID(opponentID).send(rrResponseSpecies);
    }
    
    
}
