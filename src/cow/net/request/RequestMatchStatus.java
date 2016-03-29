package cow.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import cow.core.match.*;
import shared.util.DataReader;
import shared.util.Log;
import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import lby.net.request.GameRequest;
import cow.net.response.ResponseMatchStatus;

public class RequestMatchStatus extends GameRequest {
	private int playerID;
	private Boolean isActive;
	private Boolean opponentIsReady;
	private String playerName;
	private short status;
	
	@Override
	public void parse(DataInputStream dataInput) throws IOException {
		playerID = DataReader.readInt(dataInput);
		playerName = DataReader.readString(dataInput);
	}

	@Override
	public void process() throws Exception {
		Log.printf("RequestMatchStatus Setting match status for player '%d, %s'", playerID, playerName);
		
		MatchManager manager = MatchManager.getInstance();
		Match match = manager.getMatchByPlayer(playerID);
		ResponseMatchStatus response = new ResponseMatchStatus();
		
		if(match == null){
			response.setStatus(Constants.STATUS_NO_MATCH);
			response.setMatchID(0);
			response.setIsActive(false);
			response.setIsReady(false);
		} else {
			// Set ready true	
			match.getPlayer(playerID).setDeck();
			match.getPlayer(playerID).setReady(true);
			// Adds name to opponent's player2
			
			MatchAction action = new MatchAction();
		    action.setActionID(NetworkCode.MATCH_STATUS);
			action.setIntCount(0);
			action.setStringCount(1);
		    action.addString(playerName); 
		    int matchID = match.getMatchID();
	    
			if(Constants.SINGLE_PLAYER){
				opponentIsReady = true;
				isActive = true;
			} else {
				match.addMatchAction(playerID, action);
				opponentIsReady = match.isOpponentReady(playerID);
				
				// If opponent is ready they should be active but double checking
				if (match.isOpponentActive(playerID)){
					Log.printf("Player: playerID '%d' is not Active", playerID);
					isActive = false;
					match.getPlayer(playerID).setActive(isActive);
				} else {
					Log.printf("Setting playerID '%d' active", playerID);	
					isActive = true;
					match.getPlayer(playerID).setActive(isActive);
				}
			}
			response.setStatus(Constants.STATUS_SUCCESS_CARDS);
			response.setMatchID(matchID);
			response.setIsActive(isActive);
			response.setIsReady(opponentIsReady);
		}
		
		client.add(response);
	}
	
}
