/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import shared.core.GameServer;
import java.io.IOException;
import rr.net.response.ResponseRRSpecies;
// import dataAccessLayer.RaceDAO;
// import race.RacePlayer;
import rr.race.RaceManager;
import shared.util.DataReader;

/**
 *
 * @author Sbc-ComEx
 */
public class RequestRRSpecies extends GameRequest {
    
    private int id;
    private int p_id;
    private ResponseRRSpecies responseRRSpecies;
    
    
     public void parse() throws IOException {
        id = DataReader.readInt(dataInput);
 
    }

    @Override
    public void doBusiness() throws Exception {
        // RacePlayer player;
        
        responseRRSpecies = new ResponseRRSpecies();
        responseRRSpecies.setId(id);
       System.out.println("speciec: " + id);
//        RaceManager.getInstance();
//        client.getPlayer().getID();
        
        //Debugging
//        Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
//        Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        //The playerID of the oppenet of the player who sent the request
        p_id = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
        
        // get the player and set species
        // player = RaceManager.manager.getRaceByPlayerID(p_id).getPlayers().get(p_id);
        // player.setRunnerSpeciesID(id);
        
        //NetworkManager.addResponseForUser(p_id, responsekeyboard);
        
        GameServer.getInstance().getThreadByPlayerID(p_id).send(responseRRSpecies);
        
        // RaceDAO.setPlayerSpecies(player);
    }
    
    
}
