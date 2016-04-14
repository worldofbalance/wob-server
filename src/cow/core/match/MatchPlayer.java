package cow.core.match;

import java.util.ArrayList;

import shared.util.Log;

import cow.model.Player;

import cow.model.CardDeck;

/*
 * @author Nathanael Aff
 */

public class MatchPlayer{
	
	private Player player;
	// TODO: which status variables are needed?
	private boolean isActive = false;

	// Client initialization is finished
	private boolean isReady = false;
	private int matchID;
	private String playerName; 
	
	// Copy of players deck (added to allow for design change)
	private CardDeck deck;
	private Boolean isBuilt;
	
	// ActionQueue records opponents turn based actions
	ActionQueue actionQueue;
	// inactive is just set when player leaves or quits so no more 
	// actions are added to his queue
	private boolean hasDisconnected = false;
	
	
	
	
	// TODO: register client? or access elsewhere
	public MatchPlayer(int playerID, int matchID){
		this.setPlayer(cow.core.GameServer.getInstance().getActivePlayer(playerID));
		actionQueue = new ActionQueue();
		this.matchID = matchID;
	}
	
	// Hack constructor for single player version
	public MatchPlayer(int playerID, int matchID, Boolean setDeck){
		this.setPlayer(cow.core.GameServer.getInstance().getActivePlayer(playerID));
		actionQueue = new ActionQueue();
		this.matchID = matchID;
	}
	
	public int addMatchAction(MatchAction action){
		actionQueue.push(action);
		return actionQueue.getActionCount();
	}
	
	public MatchAction getMatchAction(){
		return actionQueue.pop();
	}

	public void setDeck(){
		// hardcoding player list to avoid extra query
		// to get player list before getting cards
		Log.consoleln("setDeck()");
		ArrayList<Integer> card_ids = new ArrayList<Integer>();
                
		card_ids.add(13); // herbivores
		card_ids.add(13);
                card_ids.add(1004);
		card_ids.add(14);
		card_ids.add(14);
		card_ids.add(15);
                card_ids.add(1004);
		card_ids.add(70);
		card_ids.add(40);
		card_ids.add(31);
		card_ids.add(31);
		card_ids.add(12);
		card_ids.add(12); 
		card_ids.add(13);
		card_ids.add(12);
		card_ids.add(12);
		card_ids.add(7);
		card_ids.add(7);
		card_ids.add(21); // omnivores
		card_ids.add(21);
		card_ids.add(40);
		card_ids.add(83);
		card_ids.add(85);
		card_ids.add(77);
		card_ids.add(72);
		card_ids.add(75);
		card_ids.add(73);
		card_ids.add(42); 
		card_ids.add(45);
		card_ids.add(28);
		card_ids.add(34);
		card_ids.add(22);
		card_ids.add(22);
		card_ids.add(20);
		card_ids.add(13);
		card_ids.add(13);
		card_ids.add(21);	// carnivores
		card_ids.add(17);
		card_ids.add(18);
		card_ids.add(18);
		card_ids.add(86);
		card_ids.add(86);
		card_ids.add(17);
		card_ids.add(35);
		card_ids.add(38);
		card_ids.add(40);
		
		deck = new CardDeck(card_ids);
		deckIsBuilt(true);
	}
	
	public void setHasDisconnected(boolean hasDisconnected){
		this.hasDisconnected = hasDisconnected;
	}
	
	public boolean getHasDisconnected(){
		return hasDisconnected;
	}
	
	public void deckIsBuilt(Boolean isBuilt){
		Log.consoleln("Deck is Built");
		this.isBuilt = isBuilt;
	}
	
	public CardDeck getDeck(){
		return deck;
	}
	
	public int getID(){
		return getPlayer().getID();
	}
	
	/*
	 * @param response : Response to set
	 */
	public void addResponse(cow.net.response.GameResponse response){
		getPlayer().getClient().add(response);
	}
	

	/**
	 * @return the isReady
	 */
	public boolean isReady() {
		return isReady;
	}


	/**
	 * @return the matchID
	 */
	public int getMatchID() {
		return matchID;
	}


	/**
	 * @param isReady the isReady to set
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}


	/**
	 * @param matchID the matchID to set
	 */
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public static void  main(){
		MatchPlayer player = new MatchPlayer(10, 20);
		MatchAction action = new MatchAction();
		action.setActionID((short)209);
		action.setIntCount(1);
		action.addInt(2);
		player.addMatchAction(action);
		MatchAction action2 = player.getMatchAction();
		Log.printf("Poppped action", action2.getActionID());
		
		System.exit(0);
	}
	
}
