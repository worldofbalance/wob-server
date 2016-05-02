package cow.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cow.model.Session;
import cow.model.Player;
import shared.db.GameDB;
import shared.util.Color;
import shared.util.Log;

public final class SessionDAO {
	
	private SessionDAO() {
    	
    }
	
	public static Session playerSession(int player_id) {
		
		Session session = null;
        
        String query = "SELECT * FROM `session` WHERE `player_id` = ?";
        
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
                    session = new Session(results.getString("session_id"), results.getInt("player_id"));
                } catch (NumberFormatException e) {
                    Log.println_e(e.getMessage());
                }
            }
        } catch (SQLException e) {
            Log.println_e(e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        
        return session;
	}
	
	public static Player sessionPlayer(String session_id) {
		
		Player player = null;
		
		String query = "SELECT * FROM `session` LEFT JOIN `player` ON session.player_id=player.player_id WHERE `session_id` = ?";
        
        Connection connection = null;
        PreparedStatement sql = null;
        ResultSet results = null;
        

        try {
        	connection = GameDB.getConnection();
        	sql = connection.prepareStatement(query);
        	sql.setString(1, session_id);

        	results = sql.executeQuery();

            if (results.next()) {
                try {
                    player = new Player(results.getInt("player_id"), 
                    					results.getInt("account_id"), 
                    					results.getString("name"),
                    					results.getInt("credits"),
                    					Color.parseColor(results.getString("color")));
                } catch (NumberFormatException e) {
                    Log.println_e(e.getMessage());
                }
            }
        } catch (SQLException e) {
            Log.println_e(e.getMessage());
        } finally {
            GameDB.closeConnection(connection, sql, results);
        }
        
        return player;
	}
}

