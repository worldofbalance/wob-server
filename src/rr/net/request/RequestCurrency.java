package rr.net.request;

// Java Imports
import java.io.IOException;
import rr.core.GameServer;

// Other Imports
import rr.net.response.ResponseCurrency;
import rr.race.RaceManager;
import shared.util.Log;
import shared.db.*;

/**
 *Client Will request their Currency balance upon entering the RR game so
 * it can be displayed for them.
 * Needs to be implemented in client 
 * Relies on the currency DAO being correct 
 * @author Joshua Rubin
 */
public class RequestCurrency extends GameRequest {
    
    private int currency; 
    private int p_id;
    private ResponseCurrency responseCurrency;
    
    void RequestCurrency(){
    }
    
    @Override
    public void parse() throws IOException {
    }
    
    
    @Override
    public void doBusiness() throws Exception {
        responseCurrency = new ResponseCurrency();

        //Hardcoded unil fixed 
        //currency =9000;
        
        currency = PlayerDAO.getPlayerByPlayerId(client.getUserID()).getCredits();
        
        responseCurrency.setCurrency(currency);
        
        //Debugging
        //Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
        //Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        
        //Get player ID from rage manager
        p_id = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getID();  
        //Send client their currency value
        GameServer.getInstance().getThreadByPlayerID(p_id).send(responseCurrency);
        
        
    }
    
}
