package cos.db.world;

// Java Imports

import cos.core.world.Zone;
import cos.db.GameDB;
import cos.model.Player;
import cos.util.Color;
import cos.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Other Imports

/**
 * The WorldZoneDAO class hold methods that can execute a variety of different
 * queries for very specific purposes. For use with queries utilizing the "tile"
 * table.
 */
public final class WorldZoneDAO {

    private WorldZoneDAO() {
    }

    /**
     *
     * @param world_id
     * @return a list of tiles in the database
     */
    public static Zone[][] getZoneList(int world_id) {
        Zone[][] zones = new Zone[40][40];

        String query = "SELECT * FROM `world_zone` WHERE `world_id` = ? ORDER BY `zone_id`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, world_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Zone zone = new Zone(rs.getInt("zone_id"), rs.getShort("row"), rs.getShort("column"));
                zone.setTerrainType(rs.getShort("terrain_type"));
                zone.setVegetationCapacity(rs.getInt("vegetation_capacity"));
                zone.setOwner(rs.getInt("player_id"));

                zones[zone.getRow()][zone.getColumn()] = zone;
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return zones;
    }

    public static List<Zone> getZoneList(int world_id, int player_id) {
        List<Zone> zones = new ArrayList<Zone>();

        String query = "SELECT * FROM `world_zone` WHERE `world_id` = ? AND `player_id` = ? ORDER BY `zone_id`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, world_id);
            pstmt.setInt(2, player_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Zone zone = new Zone(rs.getInt("zone_id"), rs.getShort("row"), rs.getShort("column"));
                zone.setTerrainType(rs.getShort("terrain_type"));
                zone.setVegetationCapacity(rs.getInt("vegetation_capacity"));
                zone.setOwner(rs.getInt("player_id"));

                zones.add(zone);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return zones;
    }

    public static List<Player> getZonePlayers(int world_id) {
        List<Player> players = new ArrayList<Player>();

        String query = "SELECT DISTINCT p.`player_id`, p.`name`, p.`color` FROM `world_zone` wz INNER JOIN `player` p ON wz.`player_id` = p.`player_id` WHERE `world_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, world_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                try {
                    Player player = new Player(rs.getInt("player_id"));
                    player.setName(rs.getString("name"));
                    player.setColor(Color.parseColor(rs.getString("color")));

                    players.add(player);
                } catch (NumberFormatException ex) {
                    Log.println_e(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return players;
    }

    public static boolean updateOwner(int player_id, int zone_id) {
        boolean status = false;

        String query = "UPDATE `world_zone` SET `player_id` = ? WHERE `zone_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, zone_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateVegetationCapacity(int vegetation_capacity, int zone_id) {
        boolean status = false;

        String query = "UPDATE `world_zone` SET `vegetation_capacity` = ? WHERE `zone_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, vegetation_capacity);
            pstmt.setInt(2, zone_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static boolean updateTerrainType(int zone_id, short terrain_type) {
        boolean status = false;

        String query = "UPDATE `world_zone` SET `terrain_type` = ? WHERE `zone_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setShort(1, terrain_type);
            pstmt.setInt(2, zone_id);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static int getOwner(int zone_id) {
        int player_id = -1;

        String query = "SELECT `player_id` FROM `world_zone` WHERE `zone_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, zone_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                player_id = rs.getInt("player_id");
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return player_id;
    }
}
