package lby.db.badge;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import lby.core.badge.Badge;
import shared.db.GameDB;
import shared.util.Log;

public final class BadgeDAO {

    private BadgeDAO() {
    }

    public static boolean createEntry(int account_id, int badge_id, int amount, int progress) {
        boolean status = false;

        String query = "INSERT INTO `badge_data` (`account_id`, `badge_id`, `amount`, `progress`) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `amount` = ?, `progress` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, account_id);
            pstmt.setInt(2, badge_id);
            pstmt.setInt(3, amount);
            pstmt.setInt(4, progress);
            pstmt.setInt(5, amount);
            pstmt.setInt(6, progress);

            status = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt);
        }

        return status;
    }

    public static List<Badge> getBadgeData(int account_id) {
        List<Badge> badgeList = new ArrayList<Badge>();

        String query = "SELECT * FROM `badge_data` WHERE `account_id` = ? ORDER BY `badge_id`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, account_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Badge badge = new Badge(rs.getInt("badge_id"), rs.getInt("amount"), rs.getInt("progress"));
                badgeList.add(badge);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return badgeList;
    }
}
