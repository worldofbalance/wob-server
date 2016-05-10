package cos.model;

// Java Imports

import cos.db.SpeciesDAO;
import cos.simulation.simjob.SimTestNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Other Imports

/**
 * The SpeciesType class is an abstract class that is used to represent a single
 * class of species belonging in one of the animal or plant groups.
 */
public class SpeciesType {

    protected int species_id;
    protected String name;
    protected int cost;
    protected String description;
    protected float biomass;
    protected short diet_type;
    protected int model_id;
    protected float carrying_capacity;
    protected float metabolism;
    protected String category;
    protected float trophic_level;
    protected float growth_rate;
    protected int organism_type = -1; // Animal or Plant
    protected int[] preyList = new int[0];
    protected int[] predatorList = new int[0];
    protected Map<Integer, Float> nodeDistribution = new HashMap<Integer, Float>();
    /* 4/21/14, JTC, new parameter lists needed for testing simulations
     only loaded for simulations run from SimJobMenu; added here due to
     similarity to nodeDistribution setup */
    protected Map<Integer, SimTestNode> simTestNodeParams;
    protected Map<Integer, Consume> simTestLinkParams;

    public SpeciesType() {
    }

    public SpeciesType(String name) {
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

    public float getBiomass() {
        return biomass;
    }

    public float setBiomass(float biomass) {
        return this.biomass = biomass;
    }

    public short getDietType() {
        return diet_type;
    }

    public void setDietType(short diet_type) {
        this.diet_type = diet_type;
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

    public float getMetabolism() {
        return metabolism;
    }

    public void setMetabolism(float metabolism) {
        this.metabolism = metabolism;
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

    public float getGrowthRate() {
        return growth_rate;
    }

    public float setGrowthRate(float growth_rate) {
        return this.growth_rate = growth_rate;
    }

    public int getOrganismType() {
        return organism_type;
    }

    public int setOrganismType(int organism_type) {
        return this.organism_type = organism_type;
    }

    public int[] getPreyIDs() {
        return preyList;
    }

    public void setPreyIDs(int[] preyList) {
        this.preyList = preyList;
    }

    public int[] getPredatorIDs() {
        return predatorList;
    }

    public void setPredatorIDs(int[] predatorList) {
        this.predatorList = predatorList;
    }

    /**
     * getPredatorNodeIDs duplicates intent of getPredatorIndex, but currently
     * that methods contents are commented out and it is still actively called
     * by SimEngine. Do not want to disrupt. Creating getPredatorNodeIDs in its
     * place. 4/22/14, JTC
     *
     * @return List<Integer> predator node IDs
     */
    public List<Integer> getPredatorNodeIDs() {
        List<Integer> nodeList = new ArrayList<Integer>();

        for (Integer nodeId : predatorList) {
            if (nodeList.indexOf(nodeId) == -1) {
                nodeList.add(nodeId);
            }
        }

        return nodeList;
    }

    /**
     * getPreyNodeIDs duplicates intent of getPreyIndex, but currently that
     * methods contents are commented out and it is still actively called by
     * SimEngine. Do not want to disrupt. Creating getPreyNodeIDs in its place.
     * 4/22/14, JTC
     *
     * @return List<Integer> prey node IDs
     */
    public List<Integer> getPreyNodeIDs() {
        List<Integer> nodeList = new ArrayList<Integer>();

        for (Integer nodeId : preyList) {
            if (nodeList.indexOf(nodeId) == -1) {
                nodeList.add(nodeId);
            }
        }

        return nodeList;
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

    /**
     * setter for simTestNodeParams.
     *
     * @param simTestNodeParams
     */
    public void setSimTestNodeParams(Map<Integer, SimTestNode> simTestNodeParams) {
        this.simTestNodeParams = simTestNodeParams;
    }

    /**
     * getter for simTestNodeParams.
     *
     * @return
     */
    public Map<Integer, SimTestNode> getSimTestNodeParams() {
        return simTestNodeParams;
    }

    /**
     * getter for simTestNodeParams value for single species.
     *
     * @param nodeId
     * @return
     */
    public SimTestNode getSimTestNode(int nodeId) {
        return simTestNodeParams.get(nodeId);
    }

    /**
     * Load sim test node parameters from database.  JTC 4/2014
     * 9/13/14 - JTC - added param eco_type.
     */
    public static void loadSimTestNodeParams(int eco_type) {
        SpeciesDAO.loadSimTestNodeParams(eco_type);
    }

    /**
     * setter for simTestLinkParams.  JTC 4/2014
     *
     * @param simTestLinkParams
     */
    public void setSimTestLinkParams(Map<Integer, Consume> simTestLinkParams) {
        this.simTestLinkParams = simTestLinkParams;
    }

    /**
     * getter for simTestLinkParams for individual species.  JTC 4/2014
     *
     * @param speciesId
     * @return Consume
     */
    public Consume getSimTestLinks(int speciesId) {
        return simTestLinkParams.get(speciesId);
    }

    /**
     * Load simtest_nod_params table from database into simTestLinkParams.
     * 9/13/14 - JTC - added param eco_type.
     */
    public static void loadSimTestLinkParams(int eco_type) {
        SpeciesDAO.loadSimTestLinkParams(eco_type);
    }

    /**
     * Load list of species IDs from database; static call - data is not stored
     * in object. Specifies SQL "WHERE" clause to get specific species. Only
     * used in Sim_Job environment.  JTC 4/2014
     * 9/13/14 - JTC - added param eco_type.
     *
     * @param whereClause
     * @return
     */
    public static List<Integer> getSpeciesIdList(String whereClause, int eco_type) {
        return SpeciesDAO.getSpeciesIdList(whereClause, eco_type);
    }
}
