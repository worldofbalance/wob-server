package cos.db;

// Java Imports

import shared.db.GameDB;
import cos.model.Species;
import shared.util.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Table(s) Required: clash_battle
 *
 * The BattleDAO class hold methods that can execute a variety of different
 * queries for very specific purposes.
 *
 * @author Abhijit
 */
public final class SpeciesDAO {

    private static final String LIST_QUERY = "SELECT * FROM `clash_species` "
        + "INNER JOIN `species` ON `clash_species`.`species_id` = `species`.`species_id`";

    private SpeciesDAO() {}

    public static List<Species> getList() {
        List<Species> result = new ArrayList<Species>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(LIST_QUERY);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Species sp = new Species();
                sp.speciesId = rs.getInt("species_id");
                // NOTE: This is the existing cost from the existing species table.
                sp.price = rs.getInt("cost");
                sp.movementSpeed = rs.getInt("movement_speed");
                sp.attackSpeed = rs.getInt("attack_speed");
                sp.attackPoints = rs.getInt("attack_points");
                sp.hitPoints = rs.getInt("hit_points");

                int org = rs.getInt("organism_type");
                if (org == 1) {
                    sp.type = Species.Type.PLANT;
                } else {
                    switch(rs.getInt("diet_type")) {
                        case 0:
                            sp.type = Species.Type.OMNIVORE;
                            break;
                        case 1:
                            sp.type = Species.Type.CARNIVORE;
                            break;
                        case 2:
                            sp.type = Species.Type.HERBIVORE;
                            break;
                        default: break;
                    }
                }

                sp.name = rs.getString("name");
                sp.description = rs.getString("description");
                result.add(sp);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }
        return result;
    }
}
