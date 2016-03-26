/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import shared.core.GameServer;
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
 *
 * @author Sbc-ComEx
 */
public class RequestKeyboard extends  GameRequest{
    
    private int keytype,key;
    private int p_id;
    private  ResponseKeyboard responsekeyboard;
    
    public RequestKeyboard() {
//        responses.add(responsekeyboard = new ResponseKeyboard());
    }
       
    public void parse() throws IOException {
        keytype = DataReader.readInt(dataInput);
        key = DataReader.readInt(dataInput);
    }
    
    public void doBusiness() throws Exception {
        RacePlayer player;
      //  System.out.println("key type:  " +  keytype + "key :  " + key);
        
        responsekeyboard = new ResponseKeyboard();
        responsekeyboard.setKeytype(keytype);
        responsekeyboard.setKey(key);
        
//        RaceManager.getInstance();
//        client.getPlayer().getID();
        
        //Debugging
//        Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
//        Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        //The playerID of the oppenet of the player who sent the request
        p_id = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
        
        player = RaceManager.manager.getRaceByPlayerID(p_id).getPlayers().get(p_id);
        
        
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
            if (key == -1) player.setLeft(true);
            else if (key == 1) player.setRight(true);
            else if (key == 0)
            {
                player.setLeft(false);
                player.setRight(false);
            }
        }
        
        // jump
        else if (keytype == 2)
        {
            if (key == 1) player.setJump(true);
            else if (key == 0) player.setJump(false);
        }
        
        // invalid key?
        else
        {
            player.setLeft(false);
            player.setRight(false);
            player.setJump(false);
        }
        
        
        //NetworkManager.addResponseForUser(p_id, responsekeyboard);
        
        GameServer.getInstance().getThreadByPlayerID(p_id).send(responsekeyboard);
        RaceDAO.updateRace(player); // write to DB
    }
    
    
    
    
}
