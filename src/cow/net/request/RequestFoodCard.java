
package cow.net.request;

import cow.core.match.Match;
import cow.core.match.MatchAction;
import cow.core.match.MatchManager;
import cow.net.response.ResponseCardAttack;
import cow.net.response.ResponseFoodCard;
import java.io.DataInputStream;
import java.io.IOException;
import cow.net.request.GameRequest;
import shared.metadata.Constants;
import cow.metadata.NetworkCode;
import shared.util.DataReader;
import shared.util.Log;


public class RequestFoodCard extends GameRequest{
    private int playerID;   // match ID number
    private int cardId;   
    private int targetCardId;  

    
    @Override
	public void parse(DataInputStream dataInput) throws IOException {
	   playerID = DataReader.readInt(dataInput);
            cardId = DataReader.readInt(dataInput);
            targetCardId = DataReader.readInt(dataInput);
	}
    
    @Override
    public void process() throws Exception {
        Log.printf("RequestFoodCard with playerId:%d,attackersPosition:%d,attackedPosition:%d", playerID,cardId,targetCardId);
        ResponseFoodCard response = new ResponseFoodCard();
        MatchManager manager = MatchManager.getInstance();
        Match match = manager.getMatchByPlayer(playerID);
        MatchAction action = new MatchAction();
        action.setActionID(NetworkCode.APPLY_FOOD); 
        action.setIntCount(2);
        action.addInt(cardId);
        action.addInt(targetCardId);
        
        
        // TODO: update log
        Log.printf("Player '%d' is attacking '%d'", 1, 2);
    
        response.setStatus((short)0);
    	
        if(!Constants.SINGLE_PLAYER){
        	match.addMatchAction(playerID, action);
        }
        client.add(response);
       
    }
}
