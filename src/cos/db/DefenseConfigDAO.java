package cos.db;
import shared.db.GameDB;

// Java Imports
import java.sql.*;
import java.util.*;
import java.util.Date;


// Other Imports

import cos.model.DefenseConfig;
import shared.util.Log;
import shared.util.Vector2;

/**
 * Table(s) Required: defence_config
 *
 *
 * @author Abhijit
 */

public final class DefenseConfigDAO {

    private static final String INSERT_QUERY = "INSERT INTO `clash_defense_config`"
        + " (`player_id`, `terrain`, `created_at`) "
        + "VALUES (?, ?, ?)";

    private static final String INSERT_UNIT_QUERY = "INSERT INTO clash_defense_unit"
            + " (clash_defense_config_id, species_id, x, y)"
            + " VALUES (?, ?, ?, ?)";

    private static final String SELECT_UNITS_QUERY = "SELECT * FROM " +
            "clash_defense_unit where clash_defense_config_id = ?";

    private static final String FIND_BY_PLAYER_QUERY = "SELECT * FROM `clash_defense_config`"
        + " WHERE `player_id` = ?"
        + " ORDER BY `created_at` DESC"
        + " LIMIT 1";

    private static final String FIND_BY_DEFENSE_CONFIG_ID_QUERY = "SELECT * FROM `clash_defense_config`"
            + " WHERE `clash_defense_config_id` = ?"
            + " ORDER BY `created_at` DESC"
            + " LIMIT 1";

    private DefenseConfigDAO() {}

    public static DefenseConfig create(DefenseConfig dc) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, dc.playerId);
            pstmt.setString(2, dc.terrain);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                dc.id = rs.getInt(1);
            } else {
                throw new SQLException("Failed to create DefenseConfiguration.");
            }

            // Create the records in clash_defense_units
            for (Map.Entry<Integer, ArrayList<Vector2<Float>>> entry : dc.layout.entrySet()) {
                int speciesId = entry.getKey();
                ArrayList<Vector2<Float>> positions = entry.getValue();
                for (Vector2<Float> position : positions) {
                    pstmt = con.prepareStatement(INSERT_UNIT_QUERY);
                    pstmt.setInt(1, dc.id);
                    pstmt.setInt(2, speciesId);
                    pstmt.setFloat(3, position.getX());
                    pstmt.setFloat(4, position.getY());
                    pstmt.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }
        return dc;
    }

    public static DefenseConfig findByPlayerId(int playerId) {
        DefenseConfig config = new DefenseConfig();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(FIND_BY_PLAYER_QUERY);
            pstmt.setInt(1, playerId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Timestamp ts = rs.getTimestamp("created_at");
                config.createdAt = new Date(ts.getTime());
                config.id = rs.getInt("clash_defense_config_id");
                config.playerId = rs.getInt("player_id");
                config.terrain = rs.getString("terrain");
                getLayout(con, config);

            } else {
                return null;
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }
        return config;
    }

    public static DefenseConfig findByDefenseConfigId(int defenseConfigID) {
        DefenseConfig config = new DefenseConfig();

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(FIND_BY_DEFENSE_CONFIG_ID_QUERY);
            pstmt.setInt(1, defenseConfigID);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Timestamp ts = rs.getTimestamp("created_at");
                config.createdAt = new Date(ts.getTime());
                config.id = rs.getInt("clash_defense_config_id");
                config.playerId = rs.getInt("player_id");
                config.terrain = rs.getString("terrain");

                // Retrieve the unit layout
                getLayout(con, config);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }
        return config;
    }

    static void getLayout(Connection con, DefenseConfig config) throws  SQLException {
        PreparedStatement pstmt = con.prepareStatement(SELECT_UNITS_QUERY);
        pstmt.setInt(1, config.id);
        ResultSet rs = pstmt.executeQuery();
        config.layout = new HashMap<Integer, ArrayList<Vector2<Float>>>();
        while (rs.next()) {
            int speciesId = rs.getInt("species_id");
            if (!config.layout.containsKey(speciesId)) {
                config.layout.put(speciesId, new ArrayList<Vector2<Float>>());
            }
            config.layout.get(speciesId).add(new Vector2<Float>(rs.getFloat ("x"), rs.getFloat("y")));
        }
    }
}

