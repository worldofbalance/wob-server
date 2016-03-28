package cow.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cow.model.Card;
import shared.db.GameDB;
import shared.util.Log;

public final class CardDAO {
	
	private CardDAO() {
    	
    }

	public static Card createCard(int species_id, 
			                      int health, 
			                      int attack, 
			                      int level) throws SQLException {
		Card card = null;
		int card_id = 0;

        String query1 = "INSERT INTO `card` (`species_id`, `health`, `attack`, `level`) VALUES (?, ?, ?, ?)";
        String query2 = "SELECT * FROM `species` WHERE `species_id` = ?";

        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;
        
        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
        	sql.setInt(1, species_id);
        	sql.setInt(2, health);
        	sql.setInt(3, attack);
        	sql.setInt(4, level);
        	sql.executeUpdate();

        	results = sql.getGeneratedKeys();

            if (results.next()) {
                card_id = results.getInt(1);
                card = new Card(card_id, species_id, health, attack, level);
            }
        } catch (SQLException e) {
            Log.println_e(e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        
        // Add extra data to the Card
        if(card_id!=0) {
        	String name;
        	int diet_type;
        	String description;
	        try {
	        	connection = GameDB.getConnection();
	        	sql = connection.prepareStatement(query2);
	        	sql.setInt(1, card_id);
	
	        	results = sql.executeQuery();
	
	            if (results.next()) {
	                try {
	                    name = results.getString("name");
	                    diet_type = results.getInt("diet_type");
	                    description = results.getString("description");
	                    card.setOtherSpeciesData(name, diet_type, description);
	                } catch (NumberFormatException e) {
	                    Log.println_e(e.getMessage());
	                } catch (Exception e) {
	                    Log.println_e(e.getMessage());
	                }
	            }
	        } catch (SQLException e) {
	            Log.println_e(e.getMessage());
	        } finally {
	            GameDB.closeConnection(connection, sql, results);
	        }
        }

        return card;
    }
	
	public static Card getCard(int card_id) {
        Card card = null;
        int species_id = 0;

        String query = "SELECT * FROM `card` WHERE `card_id` = ?";
        String query2 = "SELECT * FROM `species` WHERE `species_id` = ?";
        
        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;

        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query);
        	sql.setInt(1, card_id);

        	results = sql.executeQuery();

            if (results.next()) {
                try {
                    card = new Card(results.getInt("card_id"), results.getInt("species_id"), results.getInt("health"), results.getInt("attack"), results.getInt("level"));
                    species_id = results.getInt("species_id");
                } catch (NumberFormatException e) {
                    Log.println_e(e.getMessage());
                }
            }
        } catch (SQLException e) {
            Log.println_e(e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        
        // Add extra data to the Card
        if(species_id!=0) {
        	String name;
        	int diet_type;
        	String description;
	        try {
	        	connection = GameDB.getConnection();
	        	sql = connection.prepareStatement(query2);
	        	sql.setInt(1, species_id);
	
	        	results = sql.executeQuery();
	
	            if (results.next()) {
	                try {
	                    name = results.getString("name");
	                    diet_type = results.getInt("diet_type");
	                    description = results.getString("description");
	                    card.setOtherSpeciesData(name, diet_type, description);
	                } catch (NumberFormatException e) {
	                    Log.println_e(e.getMessage());
	                } catch (Exception e) {
	                    Log.println_e(e.getMessage());
	                }
	            }
	        } catch (SQLException e) {
	            Log.println_e(e.getMessage());
	        } finally {
	            GameDB.closeConnection(connection, sql, results);
	        }
        }

        return card;
    }
	
	public static ArrayList<Integer> getCardIdList() {
        ArrayList<Integer> cardIdList = new ArrayList<Integer>();
        
        String query = "SELECT `card_id` FROM `card`";
        
        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;

        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query);

        	results = sql.executeQuery();

        	while (results.next()) {
                cardIdList.add(results.getInt("card_id"));
            }
        } catch (SQLException e) {
            Log.println_e(e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        return cardIdList;
    }
	
	public static ArrayList<Integer> getPlayerCardIdList() {
        ArrayList<Integer> cardIdList = new ArrayList<Integer>();
        
        String query = "SELECT `card_id` FROM `card`";
        
        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;

        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query);

        	results = sql.executeQuery();

        	while (results.next()) {
                cardIdList.add(results.getInt("card_id"));
            }
        } catch (SQLException e) {
            Log.println_e(e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        return cardIdList;
    }

}
