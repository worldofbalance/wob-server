package cow.model;

public class Session {
	
	private String session_id;
	private int player_id;

	public Session(String session_id, int player_id) {
		this.session_id = session_id;
		this.player_id = player_id;
	}

    
	
	public String getSessionID() {
		return this.session_id;
	}
	
	public int getPlayerID() {
		return this.player_id;
	}
	
	public String toString() {
		return this.session_id;
	}
}
