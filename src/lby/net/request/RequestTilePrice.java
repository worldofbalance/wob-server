
package lby.net.request;
/**
 *
 * @author Paul Broestl */

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

//Clase the returns the price of a tile, and whether or not the user 
//Can purchase the tile 

public class RequestTilePrice extends GameRequest {
    
    //hardcoded 
    private int world_id = 1;
    private boolean canBuy;
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
        ResponseTilePrice response = new ResponseTilePrice();
        
        boolean isOwned = true;
        //check if the player owns any tiles or if tile is owned 
        int ownerId = WorldZoneDAO.getOwner(zone_id);
        //default NULL owner = 0
        if(ownerId == 0 )
            isOwned = false;

        playerZones = WorldZoneDAO.getZoneList(world_id,client.getPlayer().getID());
        if(playerZones.isEmpty())
        {
            Log.println("The Player owns no tiles, get a Freebie!");
            price = 0 ; 
            
        } else {
          
        // first calculate the price of the tile 
        Log.println("Carrying capacity: " + WorldZoneDAO.getCarryingCapacity(zone_id));
        zone_capacity = (int)WorldZoneDAO.getCarryingCapacity(zone_id);
        //price is 10x the capacity 
        price = 10* zone_capacity;
        
        }
        Log.println("Player's current credits: " + PlayerDAO.getCredits(client.getPlayer().getID()));
       //compare the price with the player's credits  AND check if tile is owned
        if (client.getPlayer().getCredits() >= price  && !isOwned){
            
            Log.println("Player can purchase. . .");
            canBuy = true;
            
        } else {
            Log.println("Player can't purchase. . .");
            canBuy = false;
        }
        
        //setting all the information 
        response.setCanBuy(canBuy);
        response.setPrice(price);
        response.setZoneId(zone_id);
        
        response.setStatus(ResponseTilePrice.SUCCESS);
        
        client.add(response);
        
    }
}
