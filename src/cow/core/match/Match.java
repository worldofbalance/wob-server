package cow.core.match;

import shared.metadata.Constants;
import cow.model.Player;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import shared.util.Log;

/**
 * 
 * @author nathanael
 * The match class holds instances of both payers and the matchID and turn
 * count. Provides getters and setters along with methods to access 
 * opponents status. 
 */
public class Match {
	// Leaving in separate players and player map for convenience
	MatchPlayer player1, player2;
	Map<Integer, MatchPlayer> playerList = new HashMap<Integer, MatchPlayer>();
	
	// set my matchManager
	private int matchID;
	// Number of match turns
	private int turnCount = 0;
	// sessionID set by Lobby -- Not currently used
	private String sessionId;
	
	public Match (List<Player> players, int matchID){
		this.matchID = matchID;
		
                if (players != null) {
                    player1 = new MatchPlayer(players.get(0).getID(), matchID);
                    playerList.put(player1.getID(), player1);

                    if (!Constants.SINGLE_PLAYER){
                            player2 = new MatchPlayer(players.get(1).getID(), matchID);
                            playerList.put(player2.getID(), player2);		
                    }
                }
	}
	
	public Match (List<Player> players, int matchID, String sessionID){
		this.matchID = matchID;
		this.sessionId = sessionID;
		
		player1 = new MatchPlayer(players.get(0).getID(), matchID);
		playerList.put(player1.getID(), player1);
		
		if (!Constants.SINGLE_PLAYER){
			player2 = new MatchPlayer(players.get(1).getID(), matchID);
			playerList.put(player2.getID(), player2);		
		
		}
	}
	
        public boolean addPlayer(Player p) {
            if (p == null) {
                Log.println("Null player!");
                return false;
            }
            if (player1 != null && player2 != null) {
                Log.println("The match is full, can't add new players");
                return false;
            } else if (player1 == null) {
                player1 = new MatchPlayer(p.getID(), matchID);
		playerList.put(player1.getID(), player1);
                return true;
            } else if (player2 == null) {
                player2 = new MatchPlayer(p.getID(), matchID);
		playerList.put(player2.getID(), player2);
                return true;
            }
            return false;
        }
        
	/**
	 * Returns inactive playerID
	 * @return
	 */
	public int getInactivePlayerID(){
		int playerID;
		if(player1.isActive()){
			playerID = player1.getID();
		} else {
			playerID = player2.getID();
		}
		return playerID;
	}
	
	
	/*
	 * Set player quit and disable adding Actions
	 */
	public void setPlayerHasDisconnected(int playerID){
		MatchPlayer player = getPlayer(playerID);
		player.setHasDisconnected(true);
	}
	
	
	
	/**
	 * Returns status of opponent -- true
	 * if opponent is ready
	 * @param playerID
	 * @return
	 */
	public Boolean isOpponentReady(int playerID){
		if (player1 == null || player2 == null)
			return false;
		Boolean isReady;
		if(playerID == player1.getID()){
			isReady = player2.isReady();
		} else{
			isReady = player1.isReady();
		}
		return isReady;
	}
	
	
	
	/**
	 * Returns opponents activity status --
	 * true if opponent is currently the active
	 * player
	 * @param playerID
	 * @return
	 */
	public Boolean isOpponentActive(int playerID){
		if (player1 == null || player2 == null)
			return false;
		Boolean isActive;
		if(playerID == player1.getID()){
			isActive = player2.isActive();
		} else{
			isActive = player1.isActive();
		}
		return isActive;
	}	

	/**
	 * Returns MatchPlayer of inactive player
	 * @return
	 */
	private MatchPlayer getInactiveMatchPlayer(){
		int playerID = getInactivePlayerID();
		MatchPlayer player = getPlayer(playerID);
		return player;
	}
	
	/**
	 * @return the matchID
	 */
	public int getMatchID() {
		return matchID;
	}


	/**
	 * @return the turnCount
	 */
	public int getTurnCount() {
		return turnCount;
	}


	/**
	 * @param matchID the matchID to set
	 */
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}


	/**
	 * @param turnCount the turnCount to set
	 */
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}
	
	
	public MatchPlayer getPlayer(int playerID){
		return playerList.get(playerID);
	}
	
	
	
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	// Add
	public void addMatchAction(int playerID, MatchAction action){
		MatchPlayer player = getPlayer(playerID);
		if (!player.getHasDisconnected()){
			// could player i
			//player.addMatchAction(action);
			if(playerID == player1.getID()){
				player2.addMatchAction(action);
			} else {
				player1.addMatchAction(action);
			}		
		}
	}
	
	
	
	public boolean actionWaiting(int playerID){
		if(getPlayer(playerID).actionQueue.isEmpty()){
			return false;
		}
		return true;
	}
	
	
	
	public MatchAction getMatchAction(int playerID){
		
		MatchAction action = getPlayer(playerID).getMatchAction();
		Log.printf("Get action for player %d, actionID %d", 
				player2.getID(), action.getActionID());
		return action;
		//return getPlayer(playerID).getMatchAction();
	}
	
    /**
     * added by howard
     * switches active player to inactive, and vice-versa
    */	
    public void switchActive() {
        if (player1.isActive()) {
            player1.setActive(false);
            player2.setActive(true);
        } else {
            player1.setActive(true);
            player2.setActive(false);
        }
    }
    
    public MatchPlayer getOpponent(MatchPlayer player) {
        return (player == player1) ? player2 : player1;
    }

	public boolean isBothPlayersReady () {
		return player1 != null && player2 != null;
	}
}

