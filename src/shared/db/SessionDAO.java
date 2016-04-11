package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Other Imports
import shared.model.Session;
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

}

