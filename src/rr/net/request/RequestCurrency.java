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
 *
 * @author Jrubin
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
        //Currently no way to get currency 
        //client.getPlayer().GETCURRENCYPL0X
        
        //Hardcoded unil fixed 
        //currency =9000;
        
        currency = PlayerDAO.getPlayerByPlayerId(client.getUserID()).getCredits();
        
        responseCurrency.setCurrency(currency);
        
        p_id = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getID();           
        GameServer.getInstance().getThreadByPlayerID(p_id).send(responseCurrency);
        
        
    }
    
}
