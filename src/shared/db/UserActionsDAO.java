package shared.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gary
 */
public final class UserActionsDAO {

    private UserActionsDAO() {
    }

    public static int createAction(String manipulation_id, int timestep, int event, int node_id, double biomass) throws SQLException {
        int action_id = -1;

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            String query = "INSERT INTO `user_actions` (`manipulation_id`, `timestep`, `event`, `node_id`, `biomass`) VALUES (?, ?, ?, ?, ?)";

            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, manipulation_id);
            pstmt.setInt(2, timestep);
            pstmt.setInt(3, event);
            pstmt.setInt(4, node_id);
            pstmt.setDouble(5, biomass);
            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                action_id = rs.getInt(1);
            }

            pstmt.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return action_id;
    }

    public static String getActions(String manipulation_id) throws SQLException {
        String actionsCSV = "";
        
        String query = "SELECT * FROM `user_actions` WHERE `manipulation_id` = ? ORDER BY `timestep` ASC";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, manipulation_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rs.getInt("timestep");
                rs.getShort("event");
                rs.getInt("node_id");
                rs.getDouble("biomass");
            }

            pstmt.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        
        return actionsCSV;
    }
}
