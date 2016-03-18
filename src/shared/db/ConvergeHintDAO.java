package shared.db;

// Java Imports
import cvg.ConvergeHint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import shared.util.Log;

/**
 * Table(s) Required: converge_ecosystem
 *
 * @author Gary
 */
public final class ConvergeHintDAO {

    private ConvergeHintDAO() {
    }

    public static List<ConvergeHint> getConvergeHints() {
        List<ConvergeHint> hints = new ArrayList<ConvergeHint>();

        String query = ""
                + "SELECT * FROM `converge_hint`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ConvergeHint hint = new ConvergeHint();

                hint.setHintId(rs.getInt("hint_id"));
                hint.setText(rs.getString("text"));

                hints.add(hint);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return hints;
    }

    //get count of converge hints in table 
    public static int getConvergeHintCount() {
        int hintCount = 0;
        //HJR - SELECT COUNT(*) FROM `converge_hint` 
        String query = ""
                + "SELECT * FROM `converge_hint` "
                + "WHERE 1";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                hintCount++;
            }
            
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return hintCount;
    }

    //get next converge hint, starting with the specified hint offset
    public static ConvergeHint getNextConvergeHint(int hint_id_offset) {

        ConvergeHint hint = null;

        //limit based on specified offset and only return single record
        String query = ""
                + "SELECT * FROM `converge_hint` "
                + "ORDER BY `hint_id` ASC "
                + "LIMIT ?,1";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, hint_id_offset);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                hint = new ConvergeHint();

                hint.setHintId(rs.getInt("hint_id"));
                hint.setText(rs.getString("text"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return hint;
    }
}
