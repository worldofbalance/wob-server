package cow.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cow.model.Card;
import cow.model.WinsLosses;
import cow.db.GameDB;
import shared.util.Log;

public class CardWinsDAO {

	public CardWinsDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static WinsLosses createNewPlayerWinsLossesRow(int player_id, int wins, int losses) throws SQLException {
		
		WinsLosses winsLosses = null;
		int id = 0;
		
		String query1 = "INSERT INTO `cards_wins` (`player_id`, `wins`, `losses`) VALUES (?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement sql = null;
		ResultSet results = null;
		
		try {
			connection = GameDB.getConnection();
			sql = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			sql.setInt(1, player_id);
			sql.setInt(2, wins);
			sql.setInt(3, losses);
			sql.executeUpdate();
		
			results = sql.getGeneratedKeys();
		
			if (results.next()) {
				id = results.getInt(1);
				winsLosses = new WinsLosses(id, player_id, wins, losses);
			}
		} catch (SQLException e) {
			Log.println_e(e.getMessage());
		} finally {
			GameDB.closeConnection(connection, sql, results);
		}
		
		return winsLosses;
	}
	
	public static WinsLosses getPlayersWinsLosses(int player_id) {
		
		WinsLosses winsLosses = null;
        
        String query = "SELECT * FROM `cards_wins` WHERE `player_id` = ?";
        
        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;

        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query);
        	sql.setInt(1, player_id);

        	results = sql.executeQuery();

        	if (results.next()) {
                try {
                	winsLosses = new WinsLosses(results.getInt("id"), results.getInt("player_id"), results.getInt("wins"), results.getInt("losses"));
                } catch (NumberFormatException e) {
                    Log.println_e(e.getMessage());
                }
            }
        } catch (SQLException e) {
            Log.println_e(e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        return winsLosses;
    }
	
	private static boolean submitResult(int player_id, boolean won) {
		
		int id = 0;
		boolean status = false;
        
		String query;
        
		if(won){
			query = "UPDATE cards_wins SET wins = wins + 1 WHERE player_id = ?";
		}
		else {
			query = "UPDATE `cards_wins` SET losses = losses + 1 WHERE `player_id` = ?";
		}
        
        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;

        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query);
        	sql.setInt(1, player_id);
        	
        	status = sql.executeUpdate() > 0;

        	if (status) {
        		Log.println("Updating...");
            }
        	else {
        		Log.println("Error updating data");
        	}
        } catch (SQLException e) {
            Log.println_e("Data update error: "+e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        
        return status;
	}
	
	public static void playerWon(int player_id, boolean won) {
		
		WinsLosses winsLosses = null;
		boolean status = false;
        
        String query = "SELECT * FROM `cards_wins` WHERE `player_id` = ?";
        
        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;
        

        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query);
        	sql.setInt(1, player_id);
        	
        	results = sql.executeQuery();

        	if (results.next()) {
        		Log.println("Results found");
        		status = CardWinsDAO.submitResult(player_id, won);
        		if(status){
        			Log.println("Data updated");
        		}
            }
        	else {
        		Log.println("No results found");
        		if(won){
        			CardWinsDAO.createNewPlayerWinsLossesRow(player_id, 1, 0);	
        		}
        		else{
        			CardWinsDAO.createNewPlayerWinsLossesRow(player_id, 0, 1);
        		}
        		Log.println("Created new data row");
        	}
        } catch (SQLException e) {
            Log.println_e("Won error: " + e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
	}
	
}
