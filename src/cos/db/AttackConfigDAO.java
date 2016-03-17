package cos.db;

// Java Imports
import java.sql.*;
import java.util.Date;

import shared.db.GameDB;

// Other Imports
import cos.model.AttackConfig;
import shared.util.Log;

/**
 * Database methods for model.clashgame.AttackConfig
 *
 * Table(s) Required: clash_attack_config
 */

public final class AttackConfigDAO {

    private static final String INSERT_QUERY = "INSERT INTO `clash_attack_config`"
            + "(`species1`, `species2`, `species3`, `species4`, `species5`, `player_id`, `created_at`) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private AttackConfigDAO() {}

    public static AttackConfig create(AttackConfig ac) {
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < 5; i++) {
                pstmt.setInt(i + 1, ac.speciesIds.get(i));
            }
            pstmt.setInt(6, ac.playerId);
            pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                ac.id = rs.getInt(1);
            } else {
                throw new SQLException("Failed to create AttackConfig.");
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return ac;
    }
}