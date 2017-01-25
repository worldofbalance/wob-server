package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Other Imports
import shared.util.Log;

/**
 * Table(s) Required: eco_type, eco_species
 *
 * @author Gary
 */
public final class EcoDAO {

    private EcoDAO() {
    }

    public static Map<Integer, String> getEcoTypes() {
        Map<Integer, String> types = new HashMap<Integer, String>();

        String query = "SELECT * FROM `eco_type`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                types.put(rs.getInt("eco_type"), rs.getString("name"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return types;
    }

    public static List<Integer> getSpecies(int eco_type) {
        List<Integer> species = new ArrayList<Integer>();

        String query = "SELECT * FROM `eco_species` WHERE `eco_type` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                species.add(rs.getInt("species_id"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return species;
    }
}
