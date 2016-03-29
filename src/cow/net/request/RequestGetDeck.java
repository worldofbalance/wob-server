package cow.net.request;

// Java Imports
import java.io.IOException;
import java.io.DataInputStream;
// Other Imports


import cow.core.match.*;
import shared.metadata.Constants;
import cow.net.response.ResponseGetDeck;
import lby.net.request.GameRequest;
import shared.util.DataReader;
import shared.util.Log;
import cow.model.CardDeck;

/**
 *  The RequestGetDeck class sends attack data to player2
*/

public class RequestGetDeck extends GameRequest {
    private int playerID;
	
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerID = DataReader.readInt(dataInput);
    }
    
    @Override
    public void process() throws Exception {
    	MatchManager manager = MatchManager.getInstance();
        Match match = manager.getMatchByPlayer(playerID);
        
        ResponseGetDeck opponentResponse = new ResponseGetDeck();    
        Log.printf("RequestGetDeck Player '%d' is gettting Deck for match %d", playerID,match.getMatchID());
        
        CardDeck cardDeck = match.getPlayer(playerID).getDeck();
        opponentResponse.setNumCards(cardDeck.getDeckSize());
        //TODO: This field is not used on client side 
        opponentResponse.setNumFields(7);
        opponentResponse.setDeck(cardDeck);
        
        client.add(opponentResponse);
   }
    
}

