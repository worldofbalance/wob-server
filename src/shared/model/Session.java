package shared.model;

// Other Imports
import shared.core.GameClient;

/**
 *
 * @author daisychow
 */
public class Session {

    private final int id;
    private String session_id;
    private int player_id;

    public Session(int id) {
        this.id = id;
    }

    public Session(int id, String session_id, int player_id) {
        this.id = player_id;
        this.session_id = session_id;
        this.player_id = player_id;
    }

    public Session(String session_id, int player_id) {
                this.id = player_id;
		this.session_id = session_id;
		this.player_id = player_id;
    }
    public int getID() {
        return id;
    }

    public String getSessionID() {
        return session_id;
    }
        
    public String setSessionID(String session_id) {
        return this.session_id = session_id;
    }

    public int getPlayerID() {
        return player_id;
    }

}
