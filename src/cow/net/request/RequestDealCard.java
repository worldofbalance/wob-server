package cow.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import shared.util.DataReader;
import shared.util.Log;
import cow.core.match.Match;
import cow.core.match.MatchAction;
import cow.core.match.MatchManager;
import cow.core.match.MatchPlayer;
import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import lby.net.request.GameRequest;
import cow.net.response.ResponseDealCard;

public class RequestDealCard extends GameRequest {

	private int playerID;   
    private int handPosition;
 
    
    @Override
	public void parse(DataInputStream dataInput) throws IOException {
       playerID = DataReader.readInt(dataInput);
       handPosition = DataReader.readInt(dataInput);

    }
   

    @Override
    public void process() throws Exception {
        Log.printf("RequestDealCard: playerID,handposition = %d, %d\n", playerID,handPosition);
        ResponseDealCard response = new ResponseDealCard();   
        MatchManager manager = MatchManager.getInstance();
        Match match = manager.getMatchByPlayer(playerID);
        if(match == null){
        	Log.printf("Match is null, playerID = %d\n", playerID);
        }
        MatchAction action = new MatchAction();
        action.setActionID(NetworkCode.DEAL_CARD); 
        action.setIntCount(1);
        action.addInt(handPosition);
       
        response.setStatus((short)0);
        
        client.add(response);
        if(!Constants.SINGLE_PLAYER){
        	match.addMatchAction(playerID, action);
        }
    }
}