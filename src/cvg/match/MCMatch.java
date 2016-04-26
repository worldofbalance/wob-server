package cvg.match;

import shared.metadata.Constants;
import shared.model.Player;
import lby.net.response.GameResponse;
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
public class MCMatch {
	
	// Leaving in separate players and player map for convenience
	MCMatchPlayer player1, player2;
	public Map<Integer, MCMatchPlayer> playerList = new HashMap<Integer, MCMatchPlayer>();
	
	// set my matchManager
	private int matchID;
	// Number of match turns
	private int turnCount = 0;
	// sessionID set by Lobby -- Not currently used
	private String sessionId;
        // Time from start of bidding
        private long startTime;
        private short numRounds;
        private short timeWindow;
        private short betAmount;
        private short ecoNumber;
        
	
	public MCMatch (List<Player> players, int matchID){
		this.matchID = matchID;
		
                if (players != null) {
                    player1 = new MCMatchPlayer(players.get(0).getID(), matchID);
                    playerList.put(player1.getID(), player1);

                    if (!Constants.SINGLE_PLAYER){
                            player2 = new MCMatchPlayer(players.get(1).getID(), matchID);
                            playerList.put(player2.getID(), player2);		
                    }
                }
	}
	
	public MCMatch (List<Player> players, int matchID, String sessionID){
		this.matchID = matchID;
		this.sessionId = sessionID;
		
		player1 = new MCMatchPlayer(players.get(0).getID(), matchID);
		playerList.put(player1.getID(), player1);
		
		if (!Constants.SINGLE_PLAYER){
			player2 = new MCMatchPlayer(players.get(1).getID(), matchID);
			playerList.put(player2.getID(), player2);		
		
		}
	}
       
        
        // DH changed - added method
        public Map<Integer, MCMatchPlayer> getPlayers() {
            return playerList;
        }
	
        // DH change - modified 
        public boolean addPlayer(Player p) {
            if (p == null) {
                Log.println("Null player!");
                return false;
            }
            player1 = new MCMatchPlayer(p.getID(), matchID);
            playerList.put(player1.getID(), player1);
            return true;
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
		MCMatchPlayer player = getPlayer(playerID);
		player.setHasDisconnected(true);
	}
	
	
	
	/**
	 * Returns status of opponent -- true
	 * if opponent is ready
	 * @param playerID
	 * @return
	 */
	public Boolean isOpponentReady(int playerID){
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
	private MCMatchPlayer getInactiveMatchPlayer(){
		int playerID = getInactivePlayerID();
		MCMatchPlayer player = getPlayer(playerID);
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
	
	
	public MCMatchPlayer getPlayer(int playerID){
		return playerList.get(playerID);
	}
        /* NetBeans problem methods
        public Map<Integer, MCMatchPlayer> removePlayer(int PlayerID) {
            return playerList.remove(PlayerID);
        }
        
        public Map.Entry<Integer, MCMatchPlayer> getEntrySet() {
            return playerList.entrySet();
        }
	*/
	
	
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
	public void addMatchAction(int playerID, MCMatchAction action){
		MCMatchPlayer player = getPlayer(playerID);
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
	
	
	
	public MCMatchAction getMatchAction(int playerID){
		
		MCMatchAction action = getPlayer(playerID).getMatchAction();
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
    
    public MCMatchPlayer getOpponent(MCMatchPlayer player) {
        return (player == player1) ? player2 : player1;
    }
    
    // DH change
    public long getStartTime() {
        return startTime;
    }
    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setTimeWindow(short timeWindow) {
        this.timeWindow = timeWindow;
    }

    public void setNumRounds(short numRounds) {
        this.numRounds = numRounds;
    }

    public void setBetAmount(short betAmount) {
        this.betAmount = betAmount;
    }

    public void setEcoNumber(short ecoNumber) {
        this.ecoNumber = ecoNumber;
    }
    
    public short getNumRounds(){
        return numRounds;
    }
    
    public short getTimeWindow(){
        return timeWindow;
    }
    
    public short getBetAmount(){
        return betAmount;
    }
    
    public short getEcoNumber(){
        return ecoNumber;
    }
    
}

