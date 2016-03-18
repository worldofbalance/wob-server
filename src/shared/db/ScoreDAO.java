package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import shared.util.Log;

/**
 * Table(s) Required: zone
 *
 * @author Gary
 */
public final class ScoreDAO {

    private ScoreDAO() {
    }

    public static List<String[]> getBestEnvScore(int min_range, int max_range, List<String> patternList) {
        List<String[]> scoreList = new ArrayList<String[]>();

        String query = "SELECT * FROM `ecosystem` z INNER JOIN `player` p ON z.`player_id` = p.`player_id`";

        if (!patternList.isEmpty()) {
            query += " WHERE p.`username` REGEXP '";

            for (int i = 0; i < patternList.size(); i++) {
                query += patternList.get(i);

                if (i < patternList.size() - 1) {
                    query += "|";
                }
            }

            query += "'";
        }

        query += " GROUP BY z.`player_id` ORDER BY z.`high_score` DESC LIMIT ?, ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, min_range);
            pstmt.setInt(2, max_range);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] score = new String[]{rs.getString("username"), rs.getString("high_score")};
                scoreList.add(score);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return scoreList;
    }
    
    
    /* Jacob:
     * The getBestEnvScore method didn't work for me.
     * Since whoever wrote it was so kind as to document it fabulously (as in not at all),
     * I had to change it to get it to work for the toplist. But I didn't want to break anything else that used it,
     * so I made this version.
     */
    public static List<String[]> getBestEnvScore_2(int min_range, int max_range) {
        List<String[]> scoreList = new ArrayList<String[]>();

        String query = "SELECT * FROM `ecosystem` z INNER JOIN `player` p ON z.`player_id` = p.`player_id`";

        query += " GROUP BY p.`account_id` ORDER BY z.`high_score` DESC LIMIT ?, ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, min_range);
            pstmt.setInt(2, max_range);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] score = new String[]{rs.getString("player_id"), rs.getString("high_score")};
                scoreList.add(score);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return scoreList;
    }

    public static List<String[]> getBestTotalEnvScore(int min_range, int max_range, List<String> patternList) {
        List<String[]> scoreList = new ArrayList<String[]>();

        String query = "SELECT * FROM `ecosystem` z INNER JOIN `player` p ON z.`player_id` = p.`player_id`";

        if (!patternList.isEmpty()) {
            query += " WHERE p.`username` REGEXP '";

            for (int i = 0; i < patternList.size(); i++) {
                query += patternList.get(i);

                if (i < patternList.size() - 1) {
                    query += "|";
                }
            }

            query += "'";
        }

        query += " GROUP BY z.`player_id` ORDER BY z.`accumulated_score` DESC LIMIT ?, ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, min_range);
            pstmt.setInt(2, max_range);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] score = new String[]{rs.getString("username"), rs.getString("accumulated_score")};
                scoreList.add(score);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return scoreList;
    }

    public static List<String[]> getBestCurrentEnvScore(int min_range, int max_range, List<String> patternList) {
        List<String[]> scoreList = new ArrayList<String[]>();

        String query = "SELECT * FROM `ecosystem` z JOIN `player` p ON z.`player_id` = p.`player_id`";

        if (!patternList.isEmpty()) {
            query += " WHERE p.`username` REGEXP '";

            for (int i = 0; i < patternList.size(); i++) {
                query += patternList.get(i);

                if (i < patternList.size() - 1) {
                    query += "|";
                }
            }

            query += "'";
        }

        query += " GROUP BY z.`player_id` ORDER BY z.`score` DESC LIMIT ?, ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, min_range);
            pstmt.setInt(2, max_range);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] score = new String[]{rs.getString("username"), rs.getString("score")};
                scoreList.add(score);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return scoreList;
    }

    public static boolean updateEnvironmentScore(int eco_id, int score, int highEnvScore) {
        boolean status = false;

        String query = "UPDATE `ecosystem` SET `score` = ?, `high_score` = ? WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, score);
            pstmt.setInt(2, highEnvScore);
            pstmt.setInt(3, eco_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateAccumEnvScore(int eco_id, int score) {
        boolean status = false;

        String query = "UPDATE `ecosystem` SET `accumulated_score` = ? WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, score);
            pstmt.setInt(2, eco_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static List<Integer> getEnvironmentScores() {
        List<Integer> scoreList = new ArrayList<Integer>();

        String query = "SELECT * FROM `ecosystem` ORDER BY `high_score` DESC";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                scoreList.add(rs.getInt("high_score"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return scoreList;
    }
}
