package rr.model;

// Other Imports
import rr.core.GameClient;

/**
 * The Player class holds important information about the player including, most
 * importantly, the account. Such information includes the username, password,
 * email, and the player ID.
 */
public class Player {

    protected int player_id;
    protected String username;
    protected String password;
    protected GameClient client; // References GameClient instance
    protected String opponentClientSessionID;

    public Player(int player_id) {
        this.player_id = player_id;
        opponentClientSessionID = "No opponent set";
    }

    public int getID() {
        return player_id;
    }

    public int setID(int player_id) {
        return this.player_id = player_id;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        return this.username = username;
    }

    public GameClient getClient() {
        return client;
    }

    public GameClient setClient(GameClient client) {
        return this.client = client;
    }

    /**
     * @return the opponentClientSessionID
     */
    public String getOpponentClientSessionID() {
        return opponentClientSessionID;
    }

    /**
     * @param opponentClientSessionID the opponentClientSessionID to set
     */
    public void setOpponentClientSessionID(String opponentClientSessionID) {
        this.opponentClientSessionID = opponentClientSessionID;
    }
}
