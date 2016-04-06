/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rr.net.request;

import rr.race.Race;
import rr.race.RaceManager;
import rr.core.NetworkManager;
import java.io.IOException;
import rr.net.response.ResponseRaceInit;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author Lowell Milliken
 */
public class RequestRaceInit extends GameRequest {

    private int player_id;
    private int room_id;
    
    @Override
    public void parse() throws IOException {
        player_id = DataReader.readInt(dataInput);
        room_id = DataReader.readInt(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        Race race = RaceManager.getInstance().createRace(player_id, room_id);
        
        Log.println("Trying to start Race: PlayerID[" +
                player_id + "], RoomID[" + room_id + "]");
        
        if(race != null) {
            ResponseRaceInit response = new ResponseRaceInit();
            for(int p_id : race.getPlayers().keySet()) {
                NetworkManager.addResponseForUser(p_id, response);
            }
            
            Log.println("Race created with players: " + race.getPlayers().keySet().toString());
        }
    }
    
}
