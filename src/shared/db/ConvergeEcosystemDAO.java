package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import cvg.ConvergeEcosystem;
import shared.util.Log;

/**
 * Table(s) Required: converge_ecosystem
 *
 * @author Gary
 */
public final class ConvergeEcosystemDAO {

    private ConvergeEcosystemDAO() {
    }

    public static List<ConvergeEcosystem> getConvergeEcosystems() {
        List<ConvergeEcosystem> ecosystems = new ArrayList<ConvergeEcosystem>();

        String query = ""
                + "SELECT * FROM `converge_ecosystem`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ConvergeEcosystem ecosystem = new ConvergeEcosystem();

                ecosystem.setEcosystemId(rs.getInt("ecosystem_id"));
                ecosystem.setDescription(rs.getString("description"));
                ecosystem.setTimesteps(rs.getInt("timesteps"));
                ecosystem.setConfigDefault(rs.getString("config_default"));
                ecosystem.setConfigTarget(rs.getString("config_target"));
                ecosystem.setCsvDefault(rs.getString("csv_default"));
                ecosystem.setCsvTarget(rs.getString("csv_target"));

                ecosystems.add(ecosystem);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return ecosystems;
    }

    public static ConvergeEcosystem getConvergeEcosystem(int eco_id) {
        ConvergeEcosystem ecosystem = null;

        String query = ""
                + "SELECT * FROM `converge_ecosystem` "
                + "WHERE `ecosystem_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                ecosystem = new ConvergeEcosystem();

                ecosystem.setEcosystemId(rs.getInt("ecosystem_id"));
                ecosystem.setDescription(rs.getString("description"));
                ecosystem.setTimesteps(rs.getInt("timesteps"));
                ecosystem.setConfigDefault(rs.getString("config_default"));
                ecosystem.setConfigTarget(rs.getString("config_target"));
                ecosystem.setCsvDefault(rs.getString("csv_default"));
                ecosystem.setCsvTarget(rs.getString("csv_target"));

            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return ecosystem;
    }

}
