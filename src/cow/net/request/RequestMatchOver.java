package cow.net.request;

//Java Imports
import java.io.IOException;
import java.io.DataInputStream;


//Other Imports
import cow.core.match.*;
import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import cow.net.response.ResponseMatchOver;
import lby.net.request.GameRequest;
import shared.util.DataReader;
import shared.util.Log;

// for testing
import cow.tests.CardWinsTests;
import cow.model.WinsLosses;
import cow.db.CardWinsDAO;
import shared.db.PlayerDAO;

public class RequestMatchOver extends GameRequest {
	private int playerID;
	private int wonGame;
	private int wonAmount = 100;
	private int lossAmount = 25;
	
	@Override
    public void parse(DataInputStream dataInput) throws IOException {
    	playerID = DataReader.readInt(dataInput);
    	wonGame = DataReader.readInt(dataInput);
    }
	
	@Override
    public void process() throws Exception {
        Log.printf("RequestMatchOver with playerid:%d,wonGame:%d", playerID,wonGame);
        ResponseMatchOver response = new ResponseMatchOver();
        MatchAction action = new MatchAction();
        
        // ADDED
        MatchPlayer winner, loser;
        int winnerCredits, loserCredits;
        
        action.setActionID(NetworkCode.MATCH_OVER);
        action.addInt(wonGame);
        
        MatchManager manager = MatchManager.getInstance();
        Match match = manager.getMatchByPlayer(playerID);

        
        //testing
        CardWinsTests test = new CardWinsTests();
        WinsLosses wins = test.getPlayersWinsLosses(playerID);
        test.showWinsLosses(wins);
        
        // Update player's win table and add credits 
        if (wonGame == 1) {
        	CardWinsDAO.playerWon(playerID, true);

        } else {
        	CardWinsDAO.playerWon(playerID, false);

        }

        
        /*testing
        Log.printf("%d wins; credits: %d",winner.getID(), winnerCredits);
        Log.printf("%d loses; credits: %d",loser.getID(), loserCredits);
        
        PlayerDAO.updateCredits(winner.getID(), winnerCredits);
        PlayerDAO.updateCredits(loser.getID(), loserCredits);
       	*/
        Log.printf("End of match");
        
                
        // TODO:
        response.setStatus((short)0);
    	
        client.add(response);
        if (!Constants.SINGLE_PLAYER){
        	match.addMatchAction(playerID, action);
        }
    }
	
}
