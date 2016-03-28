package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import shared.model.Player;

// Other Imports
import shared.model.Session;
import shared.util.Color;
import shared.util.Log;


/**
 * Table(s) Required: session
 *
 */
public final class SessionDAO {

    private SessionDAO() {
    }

    public static Session createSession(String session_id, int player_id) {
        Session session = null;

        String query = "INSERT INTO `session` (`session_id`, `player_id`) VALUES (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, session_id);
            pstmt.setInt(2, player_id);
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                session = new Session(id, session_id, player_id);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return session;
    }

    public static Session getSession(int id) {
        Session session = null;

        String query = "SELECT * FROM `session` WHERE `id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                try {
                    session = new Session(rs.getInt("id"), rs.getString("session_id"), rs.getInt("player_id"));
                } catch (NumberFormatException ex) {
                    Log.println_e(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
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
}

