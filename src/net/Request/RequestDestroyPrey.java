
package net.Request;

import PlayTime.PlayManager;
import PlayTime.PreySpawning;
import core.GameServer;
import java.io.IOException;
import net.Response.ResponseDestroyPrey;
import utility.DataReader;
import utility.Log;

/**
 * request from client to destroy a prey in the map on the server.
 * a response will be sent to the opponent to let them know this change.
 * @author Karl
 */
public class RequestDestroyPrey extends GameRequest{
    
    private int prey_id; //the prey being destroyed.
    private int player_id; //the opponent player to send an update to.
    private ResponseDestroyPrey response = new ResponseDestroyPrey();

    public RequestDestroyPrey(){}
    
    public void parse() throws IOException{
        prey_id = DataReader.readInt(dataInput);
    }
    
    public void doBusiness() throws Exception{
        PreySpawning.getInstance().map.get(prey_id).setIsAlive(false);
        response.setPreyId(prey_id);
        
        //The playerID of the opponent of the player who sent the request
        player_id = PlayManager.manager.getPlayByPlayerID(client.getPlayer().getPlayer_id())
                .getOpponent(client.getPlayer()).getPlayer_id();
        //sending the eaten preyID to the opponent, for them to update their view.
        GameServer.getInstance().getThreadByPlayerID(player_id).send(response);
        
    }
    
}
