package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Other Imports
import shared.util.Log;

/**
 * Table(s) Required: csv_biomass, csv_score, csv_user_actions
 *
 * @author Gary
 */
public final class CSVDAO {

    private CSVDAO() {
    }

    public static boolean createBiomassCSV(String manipulation_id, String csv) {
        boolean status = false;

        String query = "INSERT INTO `csv_biomass` (`manipulation_id`, `csv`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `csv` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, manipulation_id);
            pstmt.setString(2, csv);
            pstmt.setString(3, csv);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static String getBiomassCSV(String manipulation_id) {
        String csv = null;

        String query = "SELECT * FROM `csv_biomass` WHERE `manipulation_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, manipulation_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                csv = rs.getString("csv");
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return csv;
    }

    public static boolean createScoreCSV(int eco_id, String csv) {
        boolean status = false;

        String query = "INSERT INTO `csv_score` (`eco_id`, `csv`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `csv` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);
            pstmt.setString(2, csv);
            pstmt.setString(3, csv);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static String getScoreCSV(int eco_id) {
        String csv = null;

        String query = "SELECT * FROM `csv_score` WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                csv = rs.getString("csv");
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return csv;
    }

    public static boolean createUserActionsCSV(String manipulation_id, String csv) {
        boolean status = false;

        String query = "INSERT INTO `csv_user_actions` (`manipulation_id`, `csv`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `csv` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, manipulation_id);
            pstmt.setString(2, csv);
            pstmt.setString(3, csv);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }
}
