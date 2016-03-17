package cos.db;

// Java Imports
import java.sql.*;

// Other Imports
import cos.model.Battle;
import shared.util.Log;
import shared.db.GameDB;

/**
 * Database methods for model.clashgame.Battle
 *
 * Table(s) Required: clash_battle
 */
public final class BattleDAO {

    private static final String INSERT_QUERY = "INSERT INTO `clash_battle`"
        + "(`clash_attack_config_id`, `clash_defense_config_id`, `time_started`)"
        + "VALUES (?, ?, ?)";

    private static final String FIND_ACTIVE_BY_PLAYER = "SELECT `clash_battle`.* FROM `clash_battle` "
        + " INNER JOIN `clash_attack_config`"
        + " WHERE `clash_attack_config`.`player_id` = ?"
        + " ORDER BY `time_started` DESC"
        + " LIMIT 1";

    private static final String UPDATE_QUERY = "UPDATE `clash_battle` SET `outcome` = ?, `time_ended` = ? "
        + " WHERE `clash_battle_id` = ?";

    private BattleDAO() {}

    public static Battle create(Battle battle) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, battle.attackConfigId);
            pstmt.setInt(2, battle.defenseConfigId);
            pstmt.setTimestamp(3, new Timestamp(battle.timeStarted.getTime()));

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                battle.id = rs.getInt(1);
            } else {
                throw new SQLException("Failed to create Battle.");
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return battle;
    }

    public static Battle findActiveByPlayer(int playerId) {
        Battle result = new Battle();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(FIND_ACTIVE_BY_PLAYER);

            pstmt.setInt(1, playerId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result.id = rs.getInt("clash_battle_id");
                result.attackConfigId = rs.getInt("clash_attack_config_id");
                result.defenseConfigId = rs.getInt("clash_defense_config_id");
                result.timeStarted = rs.getDate("time_started");
                result.timeEnded = rs.getTimestamp("time_ended");
            } else {
                throw new SQLException("Failed to create Battle.");
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return result;
    }

    public static Battle save(Battle updated) {
        if (updated.id == null) {
            return null;
        }

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(UPDATE_QUERY);

            pstmt.setInt(1, updated.outcome.getValue());
            pstmt.setTimestamp(2, new Timestamp(updated.timeEnded.getTime()));
            pstmt.setInt(3, updated.id);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return updated;
    }
}
