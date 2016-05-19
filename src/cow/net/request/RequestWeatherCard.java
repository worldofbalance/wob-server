/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cow.net.request;

import cow.core.match.Match;
import cow.core.match.MatchAction;
import cow.core.match.MatchManager;
import cow.metadata.NetworkCode;
import cow.net.response.ResponseSummonCard;
import cow.net.response.ResponseWeatherCard;
import java.io.DataInputStream;
import java.io.IOException;
import shared.metadata.Constants;
import shared.util.DataReader;

/**
 *
 * @author rujuraj
 */
public class RequestWeatherCard extends GameRequest{
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
        //type  =DataReader.readString(dataInput);
    }
   

    @Override
    public void process() throws Exception {
     
        ResponseWeatherCard response = new ResponseWeatherCard();
        MatchAction action = new MatchAction();
        action.setActionID(NetworkCode.APPLY_WEATHER);
        action.setIntCount(2);
        action.setStringCount(0);
        
        
        action.addInt(playerID);
        action.addInt(cardID);
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
