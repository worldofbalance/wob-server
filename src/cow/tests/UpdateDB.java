package cow.tests;

import shared.db.GameDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hduan on 3/29/2016.
 */
public class UpdateDB {

    public static void main(String[] args) {
        String speciesQuerySQL = "SELECT * FROM `species` WHERE `species_id`<=88";
        String updateCardSQL = "UPDATE `card` SET `card_type`=? WHERE `species_id`=?";


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet results = null;
        String[] dietType = new String[89];
        try {
            conn = GameDB.getConnection();
            ps = conn.prepareStatement(speciesQuerySQL);
            results = ps.executeQuery();
            int i=1;
            while (results.next()) {
                switch (results.getInt("diet_type")) {
                    case 0:
                        dietType[i] = "o";
                        break;
                    case 1:
                        dietType[i] = "c";
                        break;
                    case 2:
                        dietType[i] = "h";
                        break;
                }
                i++;
            }

            for (int j=1; j<=88; j++) {
                ps = conn.prepareStatement(updateCardSQL);
                ps.setString(1, dietType[j]);
                ps.setInt(2, j);
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            GameDB.closeConnection(conn, ps, results);
        }

    }
}
