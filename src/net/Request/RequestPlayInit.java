/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Request;

/**
 *
 * @author anu
 */

import PlayTime.Play;
import PlayTime.PlayManager;
import core.NetworkManager;
import java.io.IOException;
import net.Response.ResponsePlayInit;
import utility.DataReader;
import utility.Log;

public class RequestPlayInit extends GameRequest {

    private int player_id;
    private int room_id;
    
    @Override
    public void parse() throws IOException {
        player_id = DataReader.readInt(dataInput);
        room_id = DataReader.readInt(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        Play play = PlayManager.getInstance().createRace(player_id, room_id);
        
        Log.println("Trying to start Race: PlayerID[" +
                player_id + "], RoomID[" + room_id + "]");
        
        if(play != null) {
            ResponsePlayInit response = new ResponsePlayInit();
            for(int p_id : play.getPlayers().keySet()) {
                NetworkManager.addResponseForUser(p_id, response);
            }
            
            Log.println("Race created with players: " + play.getPlayers().keySet().toString());
        }
    }
    


}
