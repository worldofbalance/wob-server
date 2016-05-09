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
import shared.core.ServerResources;
import shared.model.AnimalType;
import shared.model.PlantType;
import shared.model.SpeciesType;
import shared.model.Consume;
import shared.simulation.simjob.SimTestNode;
import shared.util.Log;

/**
 * Table(s) Required: species, species_nodes, consume
 *
 * @author Gary
 */
public final class SpeciesDAO {

    private SpeciesDAO() {
    }

    public static List<SpeciesType> getSpecies() {
        List<SpeciesType> types = new ArrayList<SpeciesType>();

        String query = ""
                + "SELECT *, "
                + "GROUP_CONCAT(`node_id`, ':', `distribution`) AS node_list, "
                + "(SELECT GROUP_CONCAT(`prey_id`) FROM `consume` WHERE `species_id` = s.`species_id`) AS prey_list, "
                + "(SELECT GROUP_CONCAT(`species_id`) FROM `consume` WHERE `prey_id` = s.`species_id`) AS predator_list "
                + "FROM `species` s "
                + "INNER JOIN `species_nodes` sn ON s.`species_id` = sn.`species_id` "
                + "GROUP BY s.`species_id` "
                + "ORDER BY s.`species_id`, sn.`node_id`";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                SpeciesType type = null;

                switch (rs.getInt("organism_type")) {
                    case 0:
                        type = new AnimalType(rs.getInt("species_id"));
                        break;
                    case 1:
                        type = new PlantType(rs.getInt("species_id"));
                        type.setCarryingCapacity(rs.getFloat("carrying_capacity"));
                        break;
                }

                if (type == null) {
                    continue;
                }

                
                type.setName(rs.getString("name"));
                type.setOrganismType(rs.getInt("organism_type"));
                type.setCost(rs.getInt("cost"));
                type.setDescription(rs.getString("description"));
                type.setCategory(rs.getString("category"));
                type.setBiomass(rs.getFloat("biomass"));
                type.setDietType(rs.getShort("diet_type"));
                type.setMetabolism(rs.getFloat("metabolism"));
                type.setTrophicLevel(rs.getFloat("trophic_level"));
                type.setGrowthRate(rs.getFloat("growth_rate"));
                type.setModelID(rs.getInt("model_id"));

                // Columns at smurf.sfsu.edu server
                // type.setName(rs.getString("name"));
                // type.setOrganismType(rs.getInt("organism_type"));
                // type.setCost(rs.getInt("cost"));
                // type.setDescription(rs.getString("description"));
                // type.setCategory(rs.getString("category"));
                // type.setAvgBiomass(rs.getInt("max_biomass"));
                // type.setWaterNeedFrequency(rs.getInt("water_need_frequency"));
                // type.setLightNeedFrequency(rs.getInt("light_need_frequency"));
                // type.setGrowRadius(rs.getFloat("grow_radius"));
                // type.setCarryingCapacity(rs.getFloat("carrying_capacity"));
                // type.setTrophicLevel(rs.getFloat("trophic_level"));
                // type.setGrowthRate(rs.getFloat("growth_rate"));
                // type.setModelID(rs.getInt("model_id"));
                // type.setHealChance(rs.getFloat("heal_chance"));
                // type.setGroupCapacity(rs.getInt("group_capacity"));

                // Node Distribution
                Map<Integer, Float> nodeDistribution = new HashMap<Integer, Float>();
                String[] nodeList = rs.getString("node_list").split(",");
                for (String node : nodeList) {
                    String[] pair = node.split(":");
                    nodeDistribution.put(Integer.parseInt(pair[0]), Float.parseFloat(pair[1]));
                }
                type.setNodeDistribution(nodeDistribution);

                // Prey List
                String[] preyStr = rs.getString("prey_list") == null ? new String[0] : rs.getString("prey_list").split(",");
                int[] preyList = new int[preyStr.length];
                for (int i = 0; i < preyStr.length; i++) {
                    preyList[i] = Integer.parseInt(preyStr[i]);
                }
                type.setPreyIDs(preyList);

                // Predator List
                String[] predatorStr = rs.getString("predator_list") == null ? new String[0] : rs.getString("predator_list").split(",");
                int[] predatorList = new int[predatorStr.length];
                for (int i = 0; i < predatorStr.length; i++) {
                    predatorList[i] = Integer.parseInt(predatorStr[i]);
                }
                type.setPredatorIDs(predatorList);

                types.add(type);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
            ex.printStackTrace(System.out);
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return types;
    }

    /**
     * Reads in simtest_node_params table. This table contains parameters being
     * used in SimulationEngine sim_job environment to test new param and
     * per-unit- biomass values. It is imported here because its relationship to
     * Species objects is similar to nodeDistribution objects, which are
     * imported (species_nodes table) and configured in this class. 4/21/14, JTC
     * 
     * 9/13/14 - JTC - changed SQL stmt to base inclusion on eco_type rather than
     * field "hidden" (functionality replaced by Gary Ng).  Added parameter "eco_type".
     * 
     * @param eco_type
     */
    public static void loadSimTestNodeParams(int eco_type) {
        String query = ""
                + "SELECT * "
                + "FROM `species` s "
                + "JOIN `simtest_node_params` ns ON s.`species_id` = ns.`species_id` "
                + "JOIN `eco_type_species` ets ON s.`species_id` = ets.`species_id` "
                + "WHERE ets.`eco_type` = " + eco_type + " "
                + "ORDER BY s.`species_id`, ns.`node_id`";
        
                //+ "WHERE s.`hidden` = 0 ;


        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            int species_id = -1;
            SimTestNode nodeSimTest = null;
            Map<Integer, SimTestNode> nodeList = null;
            while (rs.next()) {
                if (rs.getInt("species_id") != species_id) {
                    species_id = rs.getInt("species_id");
                    nodeList = new HashMap<Integer, SimTestNode>();
                    ServerResources.getSpeciesTable().getSpecies(species_id).setSimTestNodeParams(nodeList);
                }
                nodeSimTest = new SimTestNode(rs.getInt("node_id"));
                nodeSimTest.setMetType(rs.getInt("met_type"));
                nodeSimTest.setCategoryId(rs.getInt("category_id"));
                nodeSimTest.setPerUnitBiomass(rs.getDouble("per_unit_biomass"));
                nodeSimTest.setParamK(rs.getDouble("paramK"));
                nodeSimTest.setParamR(rs.getDouble("paramR"));
                nodeSimTest.setParamX(rs.getDouble("paramX"));
                nodeSimTest.setAR(rs.getDouble("a_r"));

                nodeList.put(rs.getInt("node_id"), nodeSimTest);
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }
    }

    /**
     * Read in link parameters stored in Consume table. Currently only used for
     * testing purposes in Simulation Engine SimJob environment. 4/20/14, JTC.
     *
     * 9/13/14 - JTC - changed SQL stmt to base inclusion on eco_type rather than
     * field "hidden" (functionality replaced by Gary Ng).  Added argument "eco_type".
     * 
     * @param eco_type
     */
    public static void loadSimTestLinkParams(int eco_type) {
        Map<Integer, Consume> linkList = null;

        String query = "SELECT * FROM `consume` c "
                + "JOIN `species` s ON c.`species_id` = s.`species_id` "
                + "JOIN `eco_type_species` ets ON c.`species_id` = ets.`species_id` "
                + "WHERE ets.`eco_type` = " + eco_type + " "
                + "ORDER BY c.`species_id`";

                //+ "WHERE s.`hidden` = 0 ;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            int predator_id = -1, temp_id, prey_id;
            while (rs.next()) {
                temp_id = rs.getInt("species_id");

                if (temp_id != predator_id) {
                    predator_id = temp_id;
                    linkList = new HashMap<Integer, Consume>();
                    ServerResources.getSpeciesTable().getSpecies(predator_id).setSimTestLinkParams(linkList);
                }

                prey_id = rs.getInt("prey_id");
                if (ServerResources.getSpeciesTable().getSpecies(prey_id) != null) {
                    linkList.put(prey_id, new Consume(predator_id, prey_id,
                            rs.getDouble("param_a"), rs.getDouble("param_d"), rs.getDouble("param_e"),
                            rs.getDouble("param_q"), rs.getDouble("param_y")));
                }
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }
    }

    /**
     * Get list of species IDs for species fitting specified "WHERE" clause.
     * Used only in Sim_Job environment. 4/21/14, JTC
     *
     * 9/13/14 - JTC - changed SQL stmt to base inclusion on eco_type rather than
     * field "hidden" (functionality replaced by Gary Ng).  Added argument "eco_type".

* @param whereClause
     * @param eco_type
     * @return List<Integer> species IDs
     */
    public static List<Integer> getSpeciesIdList(String whereClause, int eco_type) {
        List<Integer> speciesIdList = new ArrayList<Integer>();
        
        String query = ""
                + "SELECT s.`species_id` FROM `species` s "
                + "JOIN `eco_type_species` ets ON s.`species_id` = ets.`species_id` "
                + "WHERE ets.`eco_type` = " + eco_type + " "
                + (whereClause.isEmpty() ? "" : "AND " + whereClause + " ")
                + "ORDER BY s.`species_id`";

        /*
        String query = ""
                + "SELECT `species_id` FROM `species` WHERE `hidden` = 0 "
                + (whereClause.isEmpty() ? "" : "AND " + whereClause + " ")
                + "ORDER BY `species_id`";
        */
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                speciesIdList.add(rs.getInt("species_id"));
            }
        } catch (SQLException ex) {
            Log.println_e(ex.getMessage());
        } finally {
            GameDB.closeConnection(con, pstmt, rs);
        }

        return speciesIdList;
    }
}
