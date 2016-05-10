package cos.db.world;

// Java Imports

import cos.core.world.World;
import cos.db.GameDB;
import cos.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Other Imports

/**
 * Table(s) Required: world
 *
 * @author Gary
 */
public final class WorldDAO {

    private WorldDAO() {
    }

    public static Map<Integer, World> getWorlds() {
        Map<Integer, World> worlds = new HashMap<Integer, World>();

        String query = "SELECT * FROM `world`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                World world = new World(rs.getInt("world_id"), rs.getString("name"), rs.getShort("type"), rs.getFloat("time_rate"), rs.getInt("day"));
                worlds.put(world.getID(), world);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return worlds;
    }

    public static World getWorld(int world_id) {
        World world = null;

        String query = "SELECT * FROM `world` WHERE `world_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, world_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                world = new World(rs.getInt("world_id"), rs.getString("name"), rs.getShort("type"), rs.getFloat("time_rate"), rs.getInt("day"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return world;
    }

    public static boolean updateDay(int world_id, int day) {
        boolean status = false;

        String query = "UPDATE `world` SET `day` = ? WHERE `world_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, day);
            pstmt.setInt(2, world_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }
}
