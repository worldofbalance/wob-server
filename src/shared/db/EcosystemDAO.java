package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Other Imports
import shared.model.Ecosystem;
import shared.util.Log;

/**
 * Table(s) Required: ecosystem
 *
 * @author Gary
 */
public final class EcosystemDAO {

    private EcosystemDAO() {
    }

    public static Ecosystem createEcosystem(int world_id, int player_id, String name, short type) {
        Ecosystem ecosystem = null;

        String query = "INSERT INTO `ecosystem` (`world_id`, `player_id`, `name`, `type`, `last_played`) VALUES (?, ?, ?, ?, NOW())";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, world_id);
            pstmt.setInt(2, player_id);
            pstmt.setString(3, name);
            pstmt.setShort(4, type);
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                int eco_id = rs.getInt(1);
                ecosystem = new Ecosystem(eco_id, world_id, player_id, name, type);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return ecosystem;
    }

    public static Ecosystem getEcosystem(int world_id, int player_id) {
        Ecosystem ecosystem = null;

        String query = "SELECT * FROM `ecosystem` WHERE `world_id` = ? AND `player_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, world_id);
            pstmt.setInt(2, player_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                ecosystem = new Ecosystem(rs.getInt("eco_id"), world_id, player_id, rs.getString("name"), rs.getShort("type"));
                ecosystem.setManipulationID(rs.getString("manipulation_id"));
                ecosystem.setATNManipulationID(rs.getString("atn_manipulation_id"));
                ecosystem.setScore(rs.getInt("score"));
                ecosystem.setHighEnvScore(rs.getInt("high_score"));
                ecosystem.setAccumulatedEnvScore(rs.getInt("accumulated_score"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return ecosystem;
    }

    public static boolean updateManipulationID(int eco_id, String manipulation_id) {
        boolean status = false;

        String query = "UPDATE `ecosystem` SET `manipulation_id` = ? WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, manipulation_id);
            pstmt.setInt(2, eco_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateATNManipulationID(int eco_id, String atn_manipulation_id) {
        boolean status = false;

        String query = "UPDATE `ecosystem` SET `atn_manipulation_id` = ? WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, atn_manipulation_id);
            pstmt.setInt(2, eco_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }
    
    public static int getTimeStep(int eco_id){
    	int timestep = 0;
    	String query = "Select `current_time_step` from  `ecosystem` WHERE `eco_id` = ?";
    	//SELECT current_time_step FROM `ecosystem` WHERE eco_id = 239 
    	
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
            	timestep = rs.getInt("current_time_step");
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }
        System.out.println("EcosystemDAO.getTimeStep() timestep " + timestep);
        return timestep;
    }
    public static boolean updateTimeStep(int eco_id, int time_step) {
        boolean status = false;

        String query = "UPDATE `ecosystem` SET `current_time_step` = ? WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, time_step);
            pstmt.setInt(2, eco_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateTime(int eco_id) {
        boolean status = false;

        String query = "UPDATE `ecosystem` SET `play_time` = `play_time` + NOW() - `last_played`, `last_played` = NOW() WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }
}
