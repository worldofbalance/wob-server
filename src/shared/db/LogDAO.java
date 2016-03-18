package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Other Imports
import shared.util.Log;

/**
 * Table(s) Required: log_chat, log_error, log_initial_species
 *
 * @author Gary
 */
public class LogDAO {

    private LogDAO() {
    }

    public static boolean createMessage(int player_id, String message) {
        boolean status = false;

        String query = "INSERT INTO `log_chat` (`player_id`, `message`) VALUES (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setString(2, message);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean createInitialSpecies(int player_id, int zone_id, String species) {
        boolean status = false;

        String query = "INSERT INTO `log_initial_species` (`player_id`, `eco_id`, `species`) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, zone_id);
            pstmt.setString(3, species);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean createError(String message) {
        boolean status = false;

        String query = "INSERT INTO `log_error` (`message`) VALUES (?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, message);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean createError(int player_id, String message) {
        boolean status = false;

        String query = "INSERT INTO `log_error` (`player_id`, `message`) VALUES (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setString(2, message);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }
}
