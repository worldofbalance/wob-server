/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import rr.core.GameServer;
import java.io.IOException;
import rr.net.response.ResponseRRSpecies;
// import dataAccessLayer.RaceDAO;
// import race.RacePlayer;
import rr.race.RaceManager;
import shared.util.DataReader;

/**
 * Parses the species ID from request, sends this to opponent.
 * Parses an int representing species ID
 * @author Sbc-ComEx
 */
public class RequestRRSpecies extends GameRequest {
    
    private int speciesID;
    private int opponentID;
    private ResponseRRSpecies responseRRSpecies;
    
    
     public void parse() throws IOException {
        speciesID = DataReader.readInt(dataInput);
 
    }

    @Override
    public void doBusiness() throws Exception {
        // RacePlayer player;
        
        responseRRSpecies = new ResponseRRSpecies();
        responseRRSpecies.setId(speciesID);
       System.out.println("species: " + speciesID);
//        RaceManager.getInstance();
//        client.getPlayer().getID();
        
        //Debugging
//        Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
//        Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        //The playerID of the oppenet of the player who sent the request
        opponentID = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
        
        // get the player and set species
        // player = RaceManager.manager.getRaceByPlayerID(opponentID).getPlayers().get(opponentID);
        // player.setRunnerSpeciesID(speciesID);
        
        //NetworkManager.addResponseForUser(opponentID, responsekeyboard);
        
        GameServer.getInstance().getThreadByPlayerID(opponentID).send(responseRRSpecies);
        
        // RaceDAO.setPlayerSpecies(player);
    }
    
    
}
