
package net.Request;

/**
 *
 * @author anu
 */

import PlayTime.Play;
import PlayTime.PlayManager;
import core.GameServer;
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
        
        Log.println("Trying to start Play: PlayerID[" +
                player_id + "], RoomID[" + room_id + "]");
        
        Play play = PlayManager.getInstance().createPlay(player_id, room_id);
        play.getPlayer(player_id).setNumber(play.getPlayers().size());
        
        Log.println("Trying to start Play: PlayerID[" +
                player_id + "], RoomID[" + room_id + "]");
        
        if(play != null) {
            ResponsePlayInit response = new ResponsePlayInit();
            response.setPlayer(play.getPlayer(player_id));
            response.setNumber(play.getPlayer(player_id).getNumber());
            //add response for self
            responses.add(response);
            //get opponent id
            int opp_id= PlayManager.manager.getPlayByPlayerID(client.getPlayer().getPlayer_id())
                .getOpponent(client.getPlayer()).getPlayer_id();
            
            /* this is meant for adding a response for all players in a match.
            for(int p_id : play.getPlayers().keySet()) {
                NetworkManager.addResponseForUser(p_id, response);
            }*/
            //send response to opponent
            GameServer.getInstance().getThreadByPlayerID(opp_id).send(response);
            Log.println("Play created with players: " + play.getPlayers().keySet().toString());
        }
        
        
        
    }
    


}
