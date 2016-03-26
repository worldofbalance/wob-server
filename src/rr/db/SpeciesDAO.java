package rr.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Other Imports
import rr.model.AnimalType;
import rr.model.PlantType;

public final class SpeciesDAO {

    private SpeciesDAO() {
    }

    public static List<AnimalType> getAnimals() throws SQLException {
        List<AnimalType> animalList = new ArrayList<AnimalType>();

        String query = ""
                + "SELECT * "
                + "FROM `species` s JOIN `species_nodes` sn ON s.`species_id` = sn.`species_id` "
                + "WHERE `organism_type` = 0 AND `hidden` = 0 "
                + "ORDER BY s.`species_id`, sn.`node_id`";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DAO.getDataSource().getConnection();
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            int species_id = -1;
            AnimalType animalType = null;
            Map<Integer, Float> nodeDistribution = null;

            while (rs.next()) {
                if (rs.getInt("species_id") != species_id) {
                    species_id = rs.getInt("species_id");

                    animalType = new AnimalType(species_id);
                    animalType.setName(rs.getString("name"));
                    animalType.setOrganismType(rs.getInt("organism_type"));
                    animalType.setCost(rs.getInt("cost"));
                    animalType.setDescription(rs.getString("description"));
                    animalType.setCategory(rs.getString("category"));
                    animalType.setAvgBiomass(rs.getInt("max_biomass"));
                    animalType.setWaterBiomassLoss(rs.getInt("water_biomass_loss"));
                    animalType.setDietType(rs.getShort("diet_type"));
                    animalType.setWaterNeedFrequency(rs.getInt("water_need_frequency"));
                    animalType.setMetabolism(rs.getFloat("metabolism"));
                    animalType.setTrophicLevel(rs.getFloat("trophic_level"));
                    animalType.setModelID(rs.getInt("model_id"));
                    animalType.setHealChance(rs.getFloat("heal_chance"));
                    animalType.setGroupCapacity(rs.getInt("group_capacity"));

                    animalList.add(animalType);

                    nodeDistribution = animalType.getNodeDistribution();
                }

                nodeDistribution.put(rs.getInt("node_id"), rs.getFloat("distribution"));
            }

            rs.close();
            pstmt.close();

            Map<Integer, List<Integer>> predatorMap = ConsumeDAO.getPreyToPredatorTable();
            Map<Integer, List<Integer>> preyMap = ConsumeDAO.getPredatorToPreyTable();

            for (AnimalType animal : animalList) {
                int animal_id = animal.getID();

                if (predatorMap.containsKey(animal_id)) {
                    for (int predator_id : predatorMap.get(animal_id)) {
                        animal.addPredatorID(predator_id);
                    }
                }

                if (preyMap.containsKey(animal_id)) {
                    for (int prey_id : preyMap.get(animal_id)) {
                        animal.addPreyID(prey_id);
                    }
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return animalList;
    }

    public static List<PlantType> getPlants() throws SQLException {
        List<PlantType> plantList = new ArrayList<PlantType>();

        String query = ""
                + "SELECT * "
                + "FROM `species` s JOIN `species_nodes` sn ON s.`species_id` = sn.`species_id` "
                + "WHERE `organism_type` = 1 AND `hidden` = 0 "
                + "ORDER BY s.`species_id`, sn.`node_id`";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DAO.getDataSource().getConnection();
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            int species_id = -1;
            PlantType plantType = null;
            Map<Integer, Float> nodeDistribution = null;

            while (rs.next()) {
                if (rs.getInt("species_id") != species_id) {
                    species_id = rs.getInt("species_id");

                    plantType = new PlantType(species_id);
                    plantType.setName(rs.getString("name"));
                    plantType.setOrganismType(rs.getInt("organism_type"));
                    plantType.setCost(rs.getInt("cost"));
                    plantType.setDescription(rs.getString("description"));
                    plantType.setCategory(rs.getString("category"));
                    plantType.setAvgBiomass(rs.getInt("max_biomass"));
                    plantType.setWaterNeedFrequency(rs.getInt("water_need_frequency"));
                    plantType.setLightNeedFrequency(rs.getInt("light_need_frequency"));
                    plantType.setGrowRadius(rs.getFloat("grow_radius"));
                    plantType.setCarryingCapacity(rs.getFloat("carrying_capacity"));
                    plantType.setTrophicLevel(rs.getFloat("trophic_level"));
                    plantType.setGrowthRate(rs.getFloat("growth_rate"));
                    plantType.setModelID(rs.getInt("model_id"));
                    plantType.setHealChance(rs.getFloat("heal_chance"));
                    plantType.setGroupCapacity(rs.getInt("group_capacity"));

                    plantList.add(plantType);

                    nodeDistribution = plantType.getNodeDistribution();
                }

                nodeDistribution.put(rs.getInt("node_id"), rs.getFloat("distribution"));
            }

            rs.close();
            pstmt.close();

            Map<Integer, List<Integer>> predatorMap = ConsumeDAO.getPreyToPredatorTable();

            for (PlantType plant : plantList) {
                int plant_id = plant.getID();

                if (plant_id >= 1007 && plant_id <= 1009) {
                    for (int some_id : new int[]{1001, 1002, 1003, 1004}) {
                        if (predatorMap.containsKey(some_id)) {
                            for (int predator_id : predatorMap.get(some_id)) {
                                plant.addPredatorID(predator_id);
                            }
                        }
                    }
                } else {
                    if (predatorMap.containsKey(plant_id)) {
                        for (int predator_id : predatorMap.get(plant_id)) {
                            plant.addPredatorID(predator_id);
                        }
                    }
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return plantList;
    }
}
