
package sdv.net.Request;

import sdv.PlayTime.PreySpawning;
import sdv.core.GameClient;
import sdv.core.GameServer;
import java.io.IOException;
import shared.metadata.Constants;
import sdv.model.Player;
import sdv.net.Response.ResponseLogin;
import sdv.net.Response.ResponsePrey;
import shared.util.DataReader;
import shared.util.Log;

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
        
        responsePrey.setSpecies_id(PreySpawning.getInstance().map.get(prey_id).getSpecies_id());
        responsePrey.setPrey_id(prey_id);
        responsePrey.setX(PreySpawning.getInstance().map.get(prey_id).getX());
        responsePrey.setY(PreySpawning.getInstance().map.get(prey_id).getY());
        responsePrey.setRotation(PreySpawning.getInstance().map.get(prey_id).getRotation());
        
    }

}
