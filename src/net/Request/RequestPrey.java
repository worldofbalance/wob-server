
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
 *
 * @author anu
 */
public class RequestPrey extends GameRequest{
    
    // Data
    private int prey_id;
 
    // Responses
    private ResponsePrey responsePrey;

    public RequestPrey() {
        responses.add(responsePrey = new ResponsePrey());
    }

    @Override
    public void parse() throws IOException {
        prey_id = DataReader.readInt(dataInput);
     
    }

    @Override
    public void doBusiness() throws Exception {
        
        responsePrey.setPrey_id(prey_id);
        responsePrey.setX(PreySpawning.getInstance().map.get(prey_id).getX());
        responsePrey.setY(PreySpawning.getInstance().map.get(prey_id).getY());
        responsePrey.setIsAlive(PreySpawning.getInstance().map.get(prey_id).isIsAlive());
        
    }

}
