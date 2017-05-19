package shared.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.util.Log;

/**
 * Stores the environment score history for an Ecosystem
 *
 * Table(s) Required: score_history
 *
 * @author Ben Saylor
 */
public class ScoreHistoryDAO {

    private ScoreHistoryDAO() {
    }

    /**
     * Get a history window of raw environment scores for an Ecosystem
     * @param ecosystemID eco_id of Ecosystem of interest
     * @param startDay game day number at which the history window should start
     * @param endDay game day number at which the history window should stop (inclusive)
     * @return the recorded scores from startDay through endDay, inclusive, in chronological order
     */
    public static List<Integer> getRawScoreHistory(int ecosystemID, int startDay, int endDay) {
        List<Integer> scores = new ArrayList<>();

        String query =
                "SELECT raw_score FROM score_history " +
                "WHERE ecosystem_id = ? " +
                "and day_number >= ? " +
                "and day_number <= ? " +
                "ORDER BY day_number";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, ecosystemID);
            pstmt.setInt(2, startDay);
            pstmt.setInt(3, endDay);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                scores.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return scores;
    }

    /**
     * Set the raw environment score for a given Ecosystem and game day.
     */
    public static void setRawScore(int ecosystem_id, int day, int rawScore) {
        String query = "INSERT INTO score_history " +
                "(ecosystem_id, day_number, raw_score) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE raw_score = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, ecosystem_id);
            pstmt.setInt(2, day);
            pstmt.setInt(3, rawScore);
            pstmt.setInt(4, rawScore);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }
    }
}
