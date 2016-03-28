package cow.tests;

import java.sql.SQLException;

import cow.db.CardDAO;
import cow.model.Card;
import shared.util.Log;


public class CardTest {
	
	public CardTest() {
		
	}
	
	public Card createCard(int species_id, int health, int attack, int level) {
		Card card = null;
		
		try {
			card = CardDAO.createCard(species_id, health, attack, level);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return card;
	}
	
	public Card getCard(int card_id) {
		Card card = null;
		
		card = CardDAO.getCard(card_id);
		
		return card;
	}
	
	public void run(boolean insert) {
		Log.printf("Running database tests...");
		if(insert){
			this.loadDatabase();
		}
		
		Log.printf("Displaying all species cards:");
		for(int i=1; i<89; i++){
			Card card = this.getCard(i);
			Log.printf("Card #" + i + ": " + card.toString());
		}
		
	}
	
	public void loadDatabase(){
		for(int i=5; i<89; i++){
			this.createCard(i, 1, 1, 1);
		}
	}
	
	
	public static void main(String[] args) {

        CardTest dbt = new CardTest();
        dbt.run(false);
        
        System.exit(0);
    }

}
