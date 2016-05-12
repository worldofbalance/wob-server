
package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import lby.core.world.Zone;

// Other Imports
import shared.core.GameServer;
//DAOS used 
import shared.db.PlayerDAO;
import lby.db.world.WorldZoneDAO;

import shared.model.Player;
import shared.model.Account;
import lby.net.response.ResponseLogin;
import shared.util.DataReader;
import shared.util.Log;

//response for price
import lby.net.response.ResponseTilePrice;
import lby.net.response.ResponseTilePurchase;
/**
 *
 * @author Paul Broestl
 */
public class RequestTilePurchase extends GameRequest{
    
    //hardcoded 
    private int world_id = 1;
    private int zone_id;
    private int zone_capacity;
    private int price;
    
    private List<Zone> playerZones = null;
    
        @Override
    public void parse(DataInputStream dataInput) throws IOException {
        
        zone_id = DataReader.readInt(dataInput);
        
    }
        @Override
    public void process() throws Exception {

        ResponseTilePurchase response = new ResponseTilePurchase();
        
        //check if the player owns any tiles
        playerZones = WorldZoneDAO.getZoneList(world_id,client.getPlayer().getID());
        if(playerZones.isEmpty())
        {
            Log.println("The Player owns no tiles, get a Freebie!");
            price = 0 ; 
            
        } else {
          
        // first calculate the price of the tile 
        Log.println("Carrying capacity " + WorldZoneDAO.getCarryingCapacity(zone_id));
        zone_capacity = (int)WorldZoneDAO.getCarryingCapacity(zone_id);
        //price is 10x the capacity 
        price = 10* zone_capacity;
        
        }
        
        Log.println("Player's current credits" + client.getPlayer().getCredits());

        //Deduct the price from the player credits
        PlayerDAO.changeCredits(client.getPlayer().getID(), -price);
        // update the zone database
        WorldZoneDAO.updateOwner(client.getPlayer().getID(),zone_id);
        
        //setting all the information 
        response.setPrice(price);
        response.setZoneId(zone_id);
        
        response.setStatus(ResponseTilePrice.SUCCESS);
        
        client.add(response);
        
    }
    
}
