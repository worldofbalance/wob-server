package cow.tests;

import shared.util.Log;
import cow.db.CardWinsDAO;
import cow.model.WinsLosses;

public class CardWinsTests {
	
	WinsLosses wins = null;

	public CardWinsTests() {
		this.wins = new WinsLosses();
	}
	
	public WinsLosses getPlayersWinsLosses(int player_id) {
		return CardWinsDAO.getPlayersWinsLosses(player_id);
	}
	
	public void showWinsLosses(WinsLosses playerWins) {
		Log.println("Wins:   " + playerWins.getPlayerWins());
		Log.println("Losses: " + playerWins.getPlayerLosses());
	}
	
	public void run() {
		CardWinsDAO.playerWon(1, false);           // Set if player with ID won/lost
		this.wins = this.getPlayersWinsLosses(1);  // Get players with ID wins/losses => returned an object
		showWinsLosses(this.wins);
	}

	public static void main(String[] args) {
		CardWinsTests tests = new CardWinsTests();
		tests.run();
		
		System.exit(0);

	}

}
