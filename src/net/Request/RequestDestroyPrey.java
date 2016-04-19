
package net.Request;

import PlayTime.PreySpawning;
import core.GameClient;
import core.GameServer;
import java.io.IOException;
import metadata.Constants;
import model.Player;
import net.Response.ResponseLogin;
import net.Response.ResponsePrey;
import utility.DataReader;
import utility.Log;

/**
 * request from client to destroy a prey in the map on the server.
 * no response will be sent.
 * @author Karl
 */
public class RequestDestroyPrey extends GameRequest{
    
    private int prey_id;
    
    public RequestDestroyPrey(){}
    
    public void parse() throws IOException{
        prey_id = DataReader.readInt(dataInput);
    }
    
    public void doBusiness() throws Exception{
        PreySpawning.getInstance().map.get(prey_id).setIsAlive(false);
    }
    
}
