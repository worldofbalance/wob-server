package shared.db;

// Java Imports
import shared.core.ServerResources;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shared.model.Consume;

public final class ConsumeDAO {

    private ConsumeDAO() {
    }

    public static Map<Integer, List<Integer>> getPreyToPredatorTable(int eco_type)
            throws SQLException {
        Map<Integer, List<Integer>> mapList = new HashMap<Integer, List<Integer>>();

        /* 4/25/14, JTC, had to add join to prevent hidden species from being read in because it
         can cause problems when parsing predator lists; NOTE: this is preventing the PREDATOR
         from being hidden */
        //String query = "SELECT * FROM `consume` ORDER BY `prey_id`";
        // 10/1/14, JTC, "hidden" functionality replaced by ets table
        String query = "SELECT c.* FROM `consume` c "
                + "JOIN `eco_type_species` ets ON c.`species_id` = ets.`species_id` "
                + "WHERE ets.`eco_type` = " + eco_type + " "
                + " ORDER BY c.`species_id`";
//                + " JOIN `species` s ON c.`species_id` = s.`species_id` "
//                + "WHERE s.`hidden` = " + String.valueOf(NOT_HIDDEN) 
//                + " ORDER BY c.`prey_id`";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            int prey_id = -1, temp_id;
            List<Integer> predatorList = null;

            while (rs.next()) {
                temp_id = rs.getInt("prey_id");

                if (temp_id != prey_id) {
                    prey_id = temp_id;

                    if (!mapList.containsKey(prey_id)) {
                        predatorList = new ArrayList<Integer>();
                        mapList.put(prey_id, predatorList);
                    } else {
                        predatorList = mapList.get(prey_id);
                    }
                }

                predatorList.add(rs.getInt("species_id"));
            }

            rs.close();
            pstmt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return mapList;
    }

    public static Map<Integer, List<Integer>> getPredatorToPreyTable(int eco_type) throws SQLException {
        Map<Integer, List<Integer>> mapList = new HashMap<Integer, List<Integer>>();

        /* 4/25/14, JTC, had to add join to prevent hidden species from being read in
         because it can cause problems when parsing prey lists; NOTE: this is preventing
         the PREY from being hidden */
        // 10/1/14, JTC, "hidden" functionality replaced by ets table

        String query = "SELECT c.* FROM `consume` c "
                + "JOIN `eco_type_species` ets ON c.`prey_id` = ets.`species_id` "
                + "WHERE ets.`eco_type` = " + eco_type + " "
                + " ORDER BY c.`species_id`";
//        String query = "SELECT c.*, s.`hidden` FROM `consume` c JOIN `species` s "
//                + "ON c.`prey_id` = s.`species_id` "
//                + "WHERE s.`hidden` = " + String.valueOf(NOT_HIDDEN) + " ORDER BY c.`species_id`";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            int predator_id = -1, temp_id;
            List<Integer> preyList = null;

            while (rs.next()) {
                temp_id = rs.getInt("species_id");

                if (temp_id != predator_id) {
                    predator_id = temp_id;

                    if (!mapList.containsKey(predator_id)) {
                        preyList = new ArrayList<Integer>();
                        mapList.put(predator_id, preyList);
                    } else {
                        preyList = mapList.get(predator_id);
                    }
                }

                preyList.add(rs.getInt("prey_id"));
            }

            rs.close();
            pstmt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return mapList;
    }

}
