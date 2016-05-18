/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import rr.core.GameServer;
import java.io.IOException;
import rr.net.response.ResponseKeyboard;
import shared.util.DataReader;
// import race.Race;
import rr.race.RaceManager;
import rr.race.RacePlayer;
// import core.NetworkManager;
import rr.db.RaceDAO;
// import utility.Log;

/**
 *Get keystrokes from Client. 
 * Confirms keystrokes sent from client, then updates 
 * @author Sbc-ComEx
 */
public class RequestKeyboard extends  GameRequest{
    
    private int keytype,key;
    private int opponentPlayerID;
    private  ResponseKeyboard responsekeyboard;
    
    public RequestKeyboard() {
//        responses.add(responsekeyboard = new ResponseKeyboard());
    }
       
    public void parse() throws IOException {
        keytype = DataReader.readInt(dataInput);
        key = DataReader.readInt(dataInput);
    }
    
    public void doBusiness() throws Exception {
        RacePlayer Opponent;
      //  System.out.println("key type:  " +  keytype + "key :  " + key);
        
        responsekeyboard = new ResponseKeyboard();
        responsekeyboard.setKeytype(keytype);
        responsekeyboard.setKey(key);
        
//        RaceManager.getInstance();
//        client.getPlayer().getID();
        
        //Debugging
//        Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
//        Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        //The playerID of the oppenet of the Opponent who sent the request
        opponentPlayerID = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
        //Gets the player object of the opponent of the person who sent the request
        Opponent = RaceManager.manager.getRaceByPlayerID(opponentPlayerID).getPlayers().get(opponentPlayerID);
        
        
        // keytype
        // - 1 = left or right
        // - 2 = spacebar
        //
        // key
        // if keytype = 1
        // - -1 = left, 1 = right, 0 = no press
        //
        // if keytype = 2, 1 = jump, 0 = no press
        
        // left or right
        if (keytype == 1)
        {
            if (key == -1) Opponent.setLeft(true);
            else if (key == 1) Opponent.setRight(true);
            else if (key == 0)
            {
                Opponent.setLeft(false);
                Opponent.setRight(false);
            }
        }
        
        // jump
        else if (keytype == 2)
        {
            if (key == 1) Opponent.setJump(true);
            else if (key == 0) Opponent.setJump(false);
        }
        
        // invalid key?
        else
        {
            Opponent.setLeft(false);
            Opponent.setRight(false);
            Opponent.setJump(false);
        }
        
        
        //NetworkManager.addResponseForUser(opponentPlayerID, responsekeyboard);
        
        //Send Player's location to opponent
        GameServer.getInstance().getThreadByPlayerID(opponentPlayerID).send(responsekeyboard);
        
        //Debug: Could cause problems when retrieving game state from server: 
        //Looks like every player pushes their opponenrs position to the DB, not
        //their own
        RaceDAO.updateRace(Opponent); // write to DB
    }
    
    
    
    
}
