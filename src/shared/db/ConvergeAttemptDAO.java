package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Other Imports
import cvg.ConvergeAttempt;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shared.metadata.Constants;
import shared.util.Log;

/**
 * Table(s) Required: converge_attempt
 *
 * @author Gary
 */
public final class ConvergeAttemptDAO {

    private ConvergeAttemptDAO() {
    }

    public static int createAttempt(
            int player_id,
            int ecosystem_id,
            int attempt_id,
            boolean allow_hints,
            int hint_id,
            String config,
            String csv            
    ) throws SQLException {

        String query = "INSERT INTO `converge_attempt`(`player_id`, "
                + "`ecosystem_id`, `attempt_id`, `allow_hints`, `hint_id`, "
                + "`config`, `csv`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        //make sure attempt_id is unique
        while (getConvergeAttempt (player_id, ecosystem_id, attempt_id) != null) {
            attempt_id++;
        }

        try {
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, ecosystem_id);
            pstmt.setInt(3, attempt_id);
            pstmt.setBoolean(4, allow_hints);
            pstmt.setInt(5, hint_id);
            pstmt.setString(6, config);
            pstmt.setString(7, csv);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException ex) {
            attempt_id = Constants.ID_NOT_SET;
            System.err.println ("SQL exception: " + ex.getMessage() + 
                    ", cause: " + ex.getCause());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return attempt_id;
    }
    
    //get count of converge attempts and ecosystem_id for most recent ecosystem for specified player; 
    public static int[] getMostRecentConvergeAttemptCount(int player_id) {
        int[] ecoInfo = {Constants.ID_NOT_SET, 0};

        //complex query finds most recent ecosystem-id used by player (i.e. the last attempt
        //for the most recent ecosystem) and then gets first record for that ecosystem
        //note: use of (SELECT * FROM (...) temp_tab) gets around a limitation that precludes
        //use of LIMIT in a subquery
        //returns ecosystem id and count of records
        String query = ""
                + "SELECT ecosystem_id, count(*) as count FROM `converge_attempt` "
                + "WHERE `player_id` = ? AND `ecosystem_id` = ANY "
                + "(SELECT * FROM ( "
                + "SELECT `ecosystem_id` FROM `converge_attempt` "
                + "WHERE `player_id` = ? "
                + "ORDER BY `time` DESC LIMIT 1) " //note descending order to get most recent ecosystem
                + "temp_tab) " 
				+ "GROUP BY `ecosystem_id` "
                + "ORDER BY `attempt_id` "
                ;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, player_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                ecoInfo[0] = rs.getInt("ecosystem_id");
                ecoInfo[1] = rs.getInt("count");
                //even if count is zero, returns a row because it is a summary
                //need to check count and reinit ecoInfo[0]
                if (ecoInfo[1] == 0) {
                    ecoInfo[0] = Constants.ID_NOT_SET;
                }
            }
            
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return ecoInfo;
    }

    //get next converge attempt for specified ecosystem, starting
    //with the specified attempt offset
    public static ConvergeAttempt getNextConvergeAttempt(int player_id, 
            int ecosystem_id, int attempt_id_offset) {

        ConvergeAttempt attempt = null;

        //limit based on specified offset and only return single record
        String query = ""
                + "SELECT * FROM `converge_attempt` "
                + "WHERE `player_id` = ? AND `ecosystem_id` = ?  "
                + "ORDER BY `time` ASC "  //note - ascending order here
                + "LIMIT ?,1";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, ecosystem_id);
            pstmt.setInt(3, attempt_id_offset);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                attempt = new ConvergeAttempt();

                attempt.setPlayerId(rs.getInt("player_id"));
                attempt.setEcosystemId(rs.getInt("ecosystem_id"));
                attempt.setAttemptId(rs.getInt("attempt_id"));
                attempt.setAllowHints(rs.getBoolean("allow_hints"));
                attempt.setHintId(rs.getInt("hint_id"));
                attempt.setTime(rs.getTimestamp("time"));
                attempt.setConfig(rs.getString("config"));
                attempt.setCsv(rs.getString("csv"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return attempt;
    }

    public static int getConvergeAttemptCount(int player_id, 
            int ecosystem_id) {

        ConvergeAttempt attempt = null;
        int count = 0;

        String query = ""
                + "SELECT COUNT(*) FROM `converge_attempt` "
                + "WHERE `player_id` = ? AND `ecosystem_id` = ?  ";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, ecosystem_id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return count;
    }

    //get specific converge attempt for specified ecosystem
    public static ConvergeAttempt getConvergeAttempt(int player_id, 
            int ecosystem_id, int attempt_id) {
        ConvergeAttempt attempt = null;

        String query = ""
                + "SELECT * FROM `converge_attempt` "
                + "WHERE `player_id` = ? AND `ecosystem_id` = ? AND `attempt_id` = ? ";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, ecosystem_id);
            pstmt.setInt(3, attempt_id);

            rs = pstmt.executeQuery();

            //if record found, prepare new object
            if (rs.first()) {
                attempt = new ConvergeAttempt();

                attempt.setPlayerId(rs.getInt("player_id"));
                attempt.setEcosystemId(rs.getInt("ecosystem_id"));
                attempt.setAttemptId(rs.getInt("attempt_id"));
                attempt.setAllowHints(rs.getBoolean("allow_hints"));
                attempt.setHintId(rs.getInt("hint_id"));
                attempt.setTime(rs.getTimestamp("time"));
                attempt.setScore(rs.getInt("score"));
                attempt.setConfig(rs.getString("config"));
                attempt.setCsv(rs.getString("csv"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return attempt;
    }

    //get specific converge attempt for specified ecosystem
    public static List<ConvergeAttempt> getConvergeAttempts() {
        List<ConvergeAttempt> attempts = new ArrayList <ConvergeAttempt>();

        //not loading config or csv file (use individual get..Attempt for that)
        String query = ""
                + "SELECT `player_id`, `ecosystem_id`, `attempt_id`,"
                + "`allow_hints`, `hint_id`, `time`, `score`  "
                + "FROM `converge_attempt` "
                + "ORDER BY `player_id`, `ecosystem_id` ASC";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            //if record found, prepare new object
            while (rs.next()) {
                ConvergeAttempt attempt = new ConvergeAttempt();

                attempt.setPlayerId(rs.getInt("player_id"));
                attempt.setEcosystemId(rs.getInt("ecosystem_id"));
                attempt.setAttemptId(rs.getInt("attempt_id"));
                attempt.setAllowHints(rs.getBoolean("allow_hints"));
                attempt.setHintId(rs.getInt("hint_id"));
                attempt.setTime(rs.getTimestamp("time"));
                attempt.setScore(rs.getInt("score"));
                
                attempts.add(attempt);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return attempts;
    }

    public static int updateConvergeAttemptScore(int player_id, 
            int ecosystem_id, int attempt_id, int score) {

        boolean success = false;

        String query = ""
                + "UPDATE `converge_attempt` SET `score`= ? "
                + "WHERE `player_id` = ? AND "
                + "`ecosystem_id` = ? AND "
                + "`attempt_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, score);
            pstmt.setInt(2, player_id);
            pstmt.setInt(3, ecosystem_id);
            pstmt.setInt(4, attempt_id);

            success = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return success ? 0 : 1;
    }

}
