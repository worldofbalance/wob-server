package cow.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cow.db.CardDAO;
import cow.model.Card;
import cow.model.CardType;
import shared.db.GameDB;
import shared.db.SpeciesDAO;
import shared.model.SpeciesType;
import shared.util.Log;


public class CardTest {
	
	public CardTest() {
		
	}
	
	public Card createCard(int species_id, int health, int attack, int level, CardType cardType) {
		Card card = null;
		
		try {
			//card = CardDAO.createCard(species_id, health, attack, level, cardType);
		} 
                catch (Exception e) {
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

	public void createWeatherCard() {
        try {
            //Card weatherCard = CardDAO.createCard(1009, 0, 0, 1, CardType.WEATHER);
            //System.out.println(weatherCard.toString());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printCard(int cardId) {
        Card card = CardDAO.getCard(cardId);
        System.out.println(card.toString());
    }
	
	public void loadDatabase(){
        List<SpeciesType> speciesType = SpeciesDAO.getSpecies();
        CardType type = null;
		for(int i=5; i<89; i++){
            switch(speciesType.get(i).getDietType()) {
                case 0:
                    type = CardType.OMNIVORE;
                    break;
                case 1:
                    type = CardType.CARNIVORE;
                    break;
                case 2:
                    type = CardType.HERBIVORE;
                    break;
            }
			this.createCard(i, 1, 1, 1, type);
		}
	}

	public void testInsert() {
        String sql[] = {
            "INSERT INTO `test`(`number`, `string`) VALUES(1, 'test')",
            "INSERT INTO `test`(`number`, `string`) VALUES(2, 'good')",
            "UPDATE `test` SET `string`='hello' WHERE `number`=2",
            "DELETE FROM `test` WHERE `number`=2"
        };
        String select = "SELECT * FROM `test`";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = GameDB.getConnection();
            for (String str : sql) {
                stmt = conn.prepareStatement(str);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public static void main(String[] args) {

        CardTest dbt = new CardTest();
//        dbt.run(false);
//        dbt.createWeatherCard();
        dbt.testInsert();
        System.exit(0);
    }

}
