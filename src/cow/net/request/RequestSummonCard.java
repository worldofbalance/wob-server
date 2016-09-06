	package cow.net.request;

// Java Imports
import java.io.IOException;
import java.io.DataInputStream;

// Other Imports


import cow.core.match.*;
import shared.metadata.Constants;
import cow.metadata.NetworkCode;
import cow.net.response.ResponseSummonCard;
import cow.net.request.GameRequest;
import shared.util.DataReader;
import shared.util.Log;

/**
 *  The RequestSummonCard class sends card selected to other client 
*/

public class RequestSummonCard extends GameRequest {
    

	private int playerID;
	private int cardID;
    private String diet;
    private int level;
    private int attack;
    private int health;
    private String species_name;
    private String type;
    private String description;
    
    @Override
	public void parse(DataInputStream dataInput) throws IOException {
        playerID = DataReader.readInt(dataInput);
        cardID = DataReader.readInt(dataInput);
        diet = DataReader.readString(dataInput);
        level= DataReader.readInt(dataInput);
        attack = DataReader.readInt(dataInput);
        health = DataReader.readInt(dataInput);
        species_name =DataReader.readString(dataInput);
        type  =DataReader.readString(dataInput);
        description =DataReader.readString(dataInput);
    }
   

    @Override
    public void process() throws Exception {
     
        ResponseSummonCard response = new ResponseSummonCard();
        MatchAction action = new MatchAction();
        action.setActionID(NetworkCode.SUMMON_CARD);
        action.setIntCount(4);
        action.setStringCount(4);
        action.addInt(cardID);
        action.addString(diet);
        action.addInt(level);
        action.addInt(attack);
        action.addInt(health);
        action.addString(species_name);
        action.addString(type);
        action.addString(description);
        MatchManager manager = MatchManager.getInstance();
        Match match = manager.getMatchByPlayer(playerID);
       
       //Log.printf("Player '%d' is sending card '%d' to '%d', %d, %d", matchID, 
        //    		   handPosition, fieldPosition, cardID);
         
    	response.setStatus((short)0);
    	client.add(response);
    	
    	 if(!Constants.SINGLE_PLAYER){
         	match.addMatchAction(playerID, action);
         }
       
    }
}