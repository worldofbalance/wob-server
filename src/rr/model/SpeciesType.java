package rr.model;

// Java Imports
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Other Imports
import rr.metadata.Constants;

/**
 * The SpeciesType class is an abstract class that is used to represent a single
 * class of species belonging in one of the animal or plant groups.
 */
public  class SpeciesType {

    protected int species_id;
    protected String name;
    protected int cost;
    protected String description;
    protected double biomass;
    protected int waterBiomassLoss;
    protected short diet_type;
    protected int waterNeedFrequency;
    protected float growthRate;
    protected float healChance;
    protected int model_id;
    protected float carrying_capacity;
    protected String category;
    protected float trophic_level;
    protected int organism_type; // Animal or Plant
    protected int group_capacity;
    protected Map<Integer, SpeciesType> predatorList;
    protected Map<Integer, SpeciesType> preyList;
    protected Map<Integer, Float> nodeDistribution;

    public SpeciesType() {
        organism_type = -1;

        predatorList = new HashMap<Integer, SpeciesType>();
        preyList = new HashMap<Integer, SpeciesType>();
        nodeDistribution = new HashMap<Integer, Float>();
    }
    
    public SpeciesType(String name) {
        this();
        this.name = name;
    }

    public int getID() {
        return species_id;
    }

    public int setID(int species_id) {
        return this.species_id = species_id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public int setCost(int cost) {
        return this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        return this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String setCategory(String category) {
        return this.category = category;
    }

    public double getAvgBiomass() {
        return biomass;
    }

    public double setAvgBiomass(double biomass) {
        return this.biomass = biomass;
    }

    public int getWaterBiomassLoss() {
        return waterBiomassLoss;
    }

    public int setWaterBiomassLoss(int waterBiomassLoss) {
        return this.waterBiomassLoss = waterBiomassLoss;
    }

    public short getDietType() {
        return diet_type;
    }

    public void setDietType(short diet_type) {
        this.diet_type = diet_type;
    }

    public int getWaterNeedFrequency() {
        return waterNeedFrequency;
    }

    public int setWaterNeedFrequency(int waterNeedFrequency) {
        return this.waterNeedFrequency = waterNeedFrequency;
    }

    public float getGrowthRate() {
        return growthRate;
    }

    public float setGrowthRate(float growthRate) {
        return this.growthRate = growthRate;
    }

    public float getHealChance() {
        return healChance;
    }

    public float setHealChance(float healChance) {
        return this.healChance = healChance;
    }

    public int getModelID() {
        return model_id;
    }

    public int setModelID(int model_id) {
        return this.model_id = model_id;
    }

    public float getCarryingCapacity() {
        return carrying_capacity;
    }

    public float setCarryingCapacity(float carrying_capacity) {
        return this.carrying_capacity = carrying_capacity;
    }

    public List<Integer> getNodeList() {
        return new ArrayList<Integer>(nodeDistribution.keySet());
    }

    public boolean hasNodeID(int node_id) {
        return nodeDistribution.containsKey(node_id);
    }

    public boolean equalsNodeList(int[] nodeList) {
        if (nodeList.length == nodeDistribution.size()) {
            for (int node_id : nodeList) {
                if (!nodeDistribution.containsKey(node_id)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public float getNodeDistribution(int node_id) {
        return nodeDistribution.get(node_id);
    }

    public Map<Integer, Float> getNodeDistribution() {
        return nodeDistribution;
    }
    
    public Map<Integer, Float> setNodeDistribution(Map<Integer, Float> nodeDistribution) {
        return this.nodeDistribution = nodeDistribution;
    }

    public float getTrophicLevel() {
        return trophic_level;
    }

    public float setTrophicLevel(float trophic_level) {
        return this.trophic_level = trophic_level;
    }

    public int getOrganismType() {
        return organism_type;
    }

    public int setOrganismType(int organism_type) {
        return this.organism_type = organism_type;
    }

    public int getGroupCapacity() {
        return group_capacity;
    }

    public int setGroupCapacity(int group_capacity) {
        return this.group_capacity = group_capacity;
    }

    public List<Integer> getPredatorIDs() {
        return new ArrayList<Integer>(predatorList.keySet());
    }

    public void addPredatorID(int predator_id) {
        predatorList.put(predator_id, null);
    }

    public List<Integer> getPredatorIndex() {
        List<Integer> typeList = new ArrayList<Integer>();
//        for (HashMap<Integer, SpeciesType> subPredatorList : predatorList.values()) {
//        	for(SpeciesType predator :subPredatorList.values()){
//        		int[] nodeList = predator.getNodeList();  
//        		System.out.print(nodeList[0] + " ");
//        	}
//        }
        return typeList;
    }

    public List<Integer> getPreyIndex() {
        List<Integer> typeList = new ArrayList<Integer>();
//        for (HashMap<Integer, SpeciesType> subPreyList : preyList.values()) {
//        	for(SpeciesType predator :subPreyList.values()){
//        		int[] nodeList = predator.getNodeList();  
//        		System.out.print(nodeList[0] + " ");
//        	}
//        }
        return typeList;
    }

    public List<SpeciesType> getPredatorList() {
        List<SpeciesType> typeList = new ArrayList<SpeciesType>();

        for (SpeciesType predator : predatorList.values()) {
            if (predator != null) {
                typeList.add(predator);
            }
        }

        return typeList;
    }

    public String getPredatorListAsString() {
        String result = "";

        for (SpeciesType predator : getPredatorList()) {
            result += predator.getID() + ",";
        }

        if (result.endsWith(",")) {
            result = result.substring(0, result.lastIndexOf(","));
        }

        return result;
    }

    public void resolvePredator(SpeciesType predator) {
        predatorList.put(predator.getID(), predator);
    }

    public List<Integer> getPreyIDs() {
        return new ArrayList<Integer>(preyList.keySet());
    }

    public void addPreyID(int prey_id) {
        preyList.put(prey_id, null);
    }

    public List<SpeciesType> getPreyList() {
        List<SpeciesType> typeList = new ArrayList<SpeciesType>();

        for (SpeciesType species : preyList.values()) {
            if (species != null) {
                typeList.add(species);
            }
        }

        return typeList;
    }

    public String getPreyListAsString() {
        String result = "";

        for (SpeciesType prey : getPreyList()) {
            result += prey.getID() + ",";
        }

        if (result.endsWith(",")) {
            result = result.substring(0, result.lastIndexOf(","));
        }

        return result;
    }

    public void resolvePrey(SpeciesType prey) {
        preyList.put(prey.getID(), prey);
    }

    @Override
    public String toString() {
        String str = "";

        str += "-----" + "\n";
        str += getClass().getName() + "\n";
        str += "\n";

        for (Field field : getClass().getDeclaredFields()) {
            try {
                str += field.getName() + " - " + field.get(this) + "\n";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        str += "-----";

        return str;
    }
}
