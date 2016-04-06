package rr.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ConsumeDAO {

    private ConsumeDAO() {
    }

    public static Map<Integer, List<Integer>> getPreyToPredatorTable() throws SQLException {
        Map<Integer, List<Integer>> mapList = new HashMap<Integer, List<Integer>>();

        String query = "SELECT * FROM `consume` ORDER BY `prey_id`";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DAO.getDataSource().getConnection();
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

    public static Map<Integer, List<Integer>> getPredatorToPreyTable() throws SQLException {
        Map<Integer, List<Integer>> mapList = new HashMap<Integer, List<Integer>>();

        String query = "SELECT * FROM `consume` ORDER BY `species_id`";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DAO.getDataSource().getConnection();
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
