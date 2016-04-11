package cow.net.request;

import java.io.DataInputStream;
import java.io.IOException;
import shared.util.DataReader;
import shared.util.Log;
import cow.core.match.Match;
import cow.core.match.MatchManager;
import cow.core.match.MatchPlayer;
import shared.metadata.Constants;
import cow.net.request.GameRequest;
import cow.net.response.ResponseMatchAction;
import cow.core.match.MatchAction;

public class RequestMatchAction extends GameRequest {
	
	private int playerID;
	
	@Override
	public void parse(DataInputStream dataInput) throws IOException {
		// TODO Auto-generated method stub
		playerID = DataReader.readInt(dataInput);
	}

	@Override
	public void process() throws Exception {
		//Log.printf("Getting request match action, playerID:%d" , playerID);
		 
		ResponseMatchAction response= new ResponseMatchAction();
		 MatchManager manager = MatchManager.getInstance();
	     Match match = manager.getMatchByPlayer(playerID);
	     if (match != null){
		     if (match.actionWaiting(playerID)){
		    	 MatchAction action = match.getMatchAction(playerID);
		
		    	 //Log.printf("Action waiting ID: %d", action.getActionID());
		    	 
		    	 response.setCode(action.getActionID());
		    	 response.setIntCount(action.getIntCount());
		    	 response.setStringCount(action.getStringCount());
		    	 response.setIntList(action.getIntList());
		    	 response.setStringList(action.getStringList());
		     } else {
	    	// TODO: Constants code for no response
		    	 response.setCode((short) 0);
		     }
	     } else {
	    	 // Match is null ... player is without a match and
	    	 // should return to lobby
	    	 response.setCode(Constants.STATUS_NO_MATCH);
	     }
	     
	    client.add(response); 
	}

}
