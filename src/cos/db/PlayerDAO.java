package cos.db;

// Java Imports

import cos.model.Player;
import cos.util.Color;
import cos.util.Log;

import java.sql.*;

// Other Imports

/**
 * Table(s) Required: player
 *
 * @author Gary
 */
public final class PlayerDAO {

    private PlayerDAO() {
    }

    public static Player createPlayer(int account_id, String name, int credits, Color color) {
        Player player = null;

        String query = "INSERT INTO `player` (`account_id`, `name`, `credits`, `color`) VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, account_id);
            pstmt.setString(2, name);
            pstmt.setInt(3, credits);
            pstmt.setString(4, color.toRGB());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                int player_id = rs.getInt(1);
                player = new Player(player_id, account_id, name, credits, color);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return player;
    }

    public static Player getPlayer(int player_id) {
        Player player = null;

        String query = "SELECT * FROM `player` WHERE `player_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                try {
                    player = new Player(rs.getInt("player_id"), rs.getInt("account_id"), rs.getString("name"), rs.getInt("credits"), Color.parseColor(rs.getString("color")));
                    player.setLevel(rs.getShort("level"));
                    player.setExperience(rs.getInt("experience"));
                    player.setLastPlayed(rs.getString("last_played"));
                } catch (NumberFormatException ex) {
                    Log.println_e(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return player;
    }

    public static Player getPlayerByAccount(int account_id) {
        Player player = null;

        String query = "SELECT * FROM `player` WHERE `account_id` = ? ORDER BY `last_played` DESC";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, account_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                try {
                    player = new Player(rs.getInt("player_id"), rs.getInt("account_id"), rs.getString("name"), rs.getInt("credits"), Color.parseColor(rs.getString("color")));
                    player.setLevel(rs.getShort("level"));
                    player.setExperience(rs.getInt("experience"));
                    player.setLastPlayed(rs.getString("last_played"));
                } catch (NumberFormatException ex) {
                    Log.println_e(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return player;
    }

    public static boolean updateLevel(int player_id, int level) {
        boolean status = false;

        String query = "UPDATE `player` SET `level` = ? WHERE `player_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, level);
            pstmt.setInt(2, player_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateExperience(int player_id, int experience) {
        boolean status = false;

        String query = "UPDATE `player` SET `experience` = ? WHERE `player_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, experience);
            pstmt.setInt(2, player_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateCredits(int player_id, int credits) {
        boolean status = false;

        String query = "UPDATE `player` SET `credits` = ? WHERE `player_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, credits);
            pstmt.setInt(2, player_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateColor(int player_id, Color color) {
        boolean status = false;

        String query = "UPDATE `player` SET `color` = ? WHERE `player_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, color.toRGB());
            pstmt.setInt(2, player_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateLastPlayed(int player_id) {
        boolean status = false;

        String query = "UPDATE `player` SET `last_played` = NOW() WHERE `player_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }
}
