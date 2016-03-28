package cow.model;

public class WinsLosses {
	
	private int id;
	private int player_id;
	private int wins;
	private int losses;
	
	public WinsLosses() {
	}
	
	public WinsLosses(int player_id) {
		this.player_id = player_id;
	}

	public WinsLosses(int id, int player_id, int wins, int losses) {
		this.id = id;
		this.player_id = player_id;
		this.wins = wins;
		this.losses = losses;
	}
	
	public int getPlayerWins() {
		return this.wins;
	}
	
	public int getPlayerLosses() {
		return this.losses;
	}
}
