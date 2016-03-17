package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import shared.core.ServerResources;
import shared.model.Stat;

public final class StatsDAO {

    private StatsDAO() {
    }

    public static int createStat(int species_id, int month, String type, int amount, int player_id, int ecosystem_id) throws SQLException {
        int stat_id = -1;

        String query = "INSERT INTO `stats` (`species_id`, `month`, `type`, `amount`, `player_id`, `ecosystem_id`) VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, species_id);
            pstmt.setInt(2, month);
            pstmt.setString(3, type);
            pstmt.setInt(4, amount);
            pstmt.setInt(5, player_id);
            pstmt.setInt(6, ecosystem_id);
            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                stat_id = rs.getInt(1);
            }

            rs.close();
            pstmt.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return stat_id;
    }

    public static List<Stat> getStats(int month_start, int month_end, int player_id, int zone_id) throws SQLException {
        List<Stat> statsList = new ArrayList<Stat>();

//        String query = "SELECT * FROM (SELECT `month`, `species_id`, `type`, SUM(`amount`) AS `amount` FROM `stats` WHERE `player_id` = ? AND `zone_id` = ? AND (`month` BETWEEN ? AND ?) GROUP BY `month`, `species_id`, `type`) AS `stats` ORDER BY `month` DESC LIMIT 30";
        String query = "SELECT * FROM (SELECT `month`, `species_id`, `type`, SUM(`amount`) AS `amount` FROM `stats` WHERE `player_id` = ? AND `zone_id` = ? GROUP BY `month`, `species_id`, `type`) AS `stats` ORDER BY `month` DESC LIMIT 30";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, zone_id);
//            pstmt.setInt(3, month_start);
//            pstmt.setInt(4, month_end);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Stat stats = new Stat();
                stats.setMonth(rs.getInt("month"));

                int species_id = rs.getInt("species_id");
                stats.setSpeciesID(species_id);

                String species_name = ServerResources.getSpeciesTable().getSpecies(species_id).getName();
                stats.setSpeciesName(species_name);

                stats.setType(rs.getString("type"));
                stats.setAmount(rs.getInt("amount"));

                statsList.add(stats);
            }

            rs.close();
            pstmt.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return statsList;
    }
}
