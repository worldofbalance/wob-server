package shared.model;

// Java Imports
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

// Other Imports
import shared.core.GameEngine;
import shared.db.*;
import shared.metadata.Constants;
import lby.net.response.ResponseUpdateEnvironmentScore;
import shared.simulation.SpeciesZoneType;

import static shared.util.Functions.log2;
import shared.util.NetworkFunctions;

/**
 * The Ecosystem class is used to store information about a specific section of
 * an Environment such as species residing in this zone.
 */
public class Ecosystem {

    private final int eco_id;
    private int world_id;
    private int player_id;
    private String name;
    private short type;
    private String manip_id;
    private long play_time;
    private short access_type;
    private int score;
    private int highEnvScore;
    private int accumEnvScore;
    private Timestamp last_played;
//    private final Map<Integer, Animal> animals = new HashMap<Integer, Animal>();
//    private final Map<Integer, Plant> plants = new HashMap<Integer, Plant>();
    private GameEngine gameEngine;
//    private Map<Short, Float> parametersList = new HashMap<Short, Float>();
    // Species ID -> Species
    private final Map<Integer, Species> speciesList = new HashMap<Integer, Species>();
    // Node ID -> Count
//    private final Map<Integer, Integer> nodeList = new HashMap<Integer, Integer>();
    // Node ID -> Biomass
    private Map<Integer, Integer> addNodeList = new HashMap<Integer, Integer>();
    private List<List<String>> score_csv;
    private Map<Integer, Integer> speciesChangeList = new HashMap<Integer, Integer>();
    private Random random;
    //5/5/14, JTC, new hashmap to store persistent lists of specieszonetypes for each player
    //in order to store game or player changes to species parameters (I'm certain that there
    //is a lot of unnecessary redundancy here, but I don't have time to look into it)
    private ZoneNodes zoneNodes = new ZoneNodes();
//    private float carryingCapacity;
//    private float perBiomass;
	private String atnManipId;
	private String networkId;

	private int scoreSmoothingWindowSize = 100;
	private Deque<Integer> rawScoreHistory = new ArrayDeque<>();
	private int rawScoreHistoryLastDay = -1;
	private boolean rawScoreHistoryLoaded = false;

    public Ecosystem(int eco_id, int world_id, int player_id, String name, short type) {
        this.eco_id = eco_id;
        this.world_id = world_id;
        this.player_id = player_id;
        this.name = name;
        this.type = type;
    }

    public int getID() {
        return eco_id;
    }

    public int getWorldID() {
        return world_id;
    }

    public int setWorldID(int world_id) {
        return this.world_id = world_id;
    }

    public int getPlayerID() {
        return player_id;
    }

    public int setPlayerID(int player_id) {
        return this.player_id = player_id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public short getType() {
        return type;
    }

    public short setType(short type) {
        return this.type = type;
    }

    public List<List<String>> getScoreCSV() {
        return this.score_csv;
    }

    public List<List<String>> setScoreCSV(List<List<String>> score_csv) {
        return this.score_csv = score_csv;
    }

    public String getNetworkId(){
    	return networkId;
    }
    public void setNetworkId(String networkId){
    	this.networkId = networkId;
    }
    
    public String getManipulationID() {
        return manip_id;
    }

    public String setManipulationID(String manip_id) {
        return this.manip_id = manip_id;
    }

	public void setATNManipulationID(String atnManipId) {
		this.atnManipId = atnManipId;
	}
	public String getATNManipulationID() {
		return this.atnManipId;
	}
	
    public long getPlayTime() {
        return play_time;
    }

    public long setPlayTime(long play_time) {
        return this.play_time = play_time;
    }

    public short getAccessType() {
        return access_type;
    }

    public short setAccessType(short access_type) {
        return this.access_type = access_type;
    }

    public Map<Integer, Integer> getSpeciesChangeList() {
        return speciesChangeList;
    }

    public Map<Integer, Integer> setSpeciesChangeList(Map<Integer, Integer> speciesChangeList) {
        random = new Random();
        return this.speciesChangeList = speciesChangeList;
    }

    public void clearSpeciesChangeList() {
        speciesChangeList.clear();
    }

    public Random getRandom() {
        return random;
    }

    public boolean containsSpecies(int species_id) {
        return speciesList.containsKey(species_id);
    }

    public Species getSpecies(int species_id) {
        return speciesList.get(species_id);
    }

    public Map<Integer, Species> getSpeciesList() {
        return speciesList;
    }

    public Map<Integer, Integer> getAddSpeciesList() {
        return addNodeList;
    }

    public Map<Integer, Integer> removeAddSpeciesList() {
        Map<Integer, Integer> nodeList = addNodeList;
        addNodeList = new HashMap<Integer, Integer>();
        return nodeList;
    }

    public void setAddNodeList(Map<Integer, Integer> addNodeList) {
        this.addNodeList = addNodeList;
    }

    public void setNewSpeciesNode(int node_id, int amount) {
        try {
            System.out.println("setNewSpeciesNode " + node_id + " " + amount);
            if (addNodeList.containsKey(node_id)) {
                addNodeList.put(node_id, addNodeList.get(node_id) + amount);
                ZoneNodeAddDAO.updateAmount(eco_id, node_id, addNodeList.get(node_id));
            } else {
                addNodeList.put(node_id, amount);
                ZoneNodeAddDAO.createEntry(eco_id, node_id, amount);
            }
            System.out.println("addNodeList " + node_id + "-" + addNodeList.get(node_id));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void removeNode(int node_id){
    	Map<Integer, SpeciesZoneType> nodes = zoneNodes.getNodes();
    	if(nodes != null){
    		if(nodes.containsKey(node_id)){
    			//check to see if it contains the node_id
        		//remove the node_id
    			zoneNodes.removeNode(node_id);
    			try {
					ZoneNodeAddDAO.removeEntry(eco_id, node_id);
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
    		}
    	}
    	
    }
    
    public void removeEntry(int species_id){
    	try {
            SpeciesChangeListDAO.removeEntry(eco_id, species_id);
	} catch (SQLException e) {
            System.err.println(e.getMessage());
	}
    }
    
    public void removeNewSpeciesNode(int node_id, int amount) {
        try {
            if (addNodeList.containsKey(node_id)) {
                int temp = Math.max(0, addNodeList.get(node_id) - amount);

                if (temp > 0) {
                    addNodeList.put(node_id, temp);
                    ZoneNodeAddDAO.updateAmount(eco_id, node_id, addNodeList.get(node_id));
                } else {
                    addNodeList.remove(node_id);
                    ZoneNodeAddDAO.removeEntry(eco_id, node_id);
                }
            }
            System.out.println("removeNewSpeciesNode " + node_id + " " + amount);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

//    public boolean containsOrganism(int organism_id) {
//        return plants.containsKey(organism_id) || animals.containsKey(organism_id);
//    }
//
//    public List<Animal> getAnimals() {
//        return new ArrayList<Animal>(animals.values());
//    }
//
//    public List<Organism> getOrganisms() {
//        List<Organism> organismList = new ArrayList<Organism>();
//        organismList.addAll(plants.values());
//        organismList.addAll(animals.values());
//
//        return organismList;
//    }
//
//    public List<Organism> getOrganismsBySpecies(int species_id) {
//        List<Organism> organismList = new ArrayList<Organism>();
//
//        for (Animal animal : animals.values()) {
//            if (animal.getSpeciesTypeID() == species_id) {
//                organismList.add(animal);
//            }
//        }
//
//        for (Plant plant : plants.values()) {
//            if (plant.getSpeciesTypeID() == species_id) {
//                organismList.add(plant);
//            }
//        }
//
//        return organismList;
//    }
    
    public void setSpecies(Species species) {
        speciesList.put(species.getID(), species);
    }

    public void addSpecies(Species species) {
        speciesList.put(species.getID(), species);

        updateEnvironmentScore();
    }
    
    public void removeSpecies(int species_id) {
        speciesList.remove(species_id);

        updateEnvironmentScore();
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public GameEngine setGameEngine(GameEngine gameEngine) {
        return this.gameEngine = gameEngine;
    }

    /**
     * Calculate a version of the smoothed environment score
     * with numbers in a smaller range more interpretable by players.
     * @return the scaled smoothed environment score
     */
    public int scaledSmoothedEnvironmentScore() {
        return (int) Math.round(Math.sqrt(smoothedEnvironmentScore()) * Constants.SCORE_MULTIPLIER);
    }

    /**
     * Calculate a simple moving average of the rawEnvironmentScore
     * based on the history of raw environment scores
     * over the past [scoreSmoothingWindowSize] game days.
     * @return the smoothed environment score
     */
    public double smoothedEnvironmentScore() {
        updateRawScoreHistory();
        double sum = 0;
        for (int rawScore : rawScoreHistory) {
            sum += rawScore;
        }
        return sum / rawScoreHistory.size();
    }

    public void setScoreSmoothingWindowSize(int scoreSmoothingWindowSize) {
        this.scoreSmoothingWindowSize = scoreSmoothingWindowSize;
    }

    /**
     * Update the in-memory raw environment score history window.
     * Does not update the database.
     */
    private void updateRawScoreHistory() {
        int currentRawScore = rawEnvironmentScore();
        int currentDay = getCurrentDay();
        if (currentDay == rawScoreHistoryLastDay && rawScoreHistory.size() > 0) {
            rawScoreHistory.removeLast();
        }
        rawScoreHistory.addLast(currentRawScore);
        rawScoreHistoryLastDay = currentDay;
        while (rawScoreHistory.size() > scoreSmoothingWindowSize) {
            rawScoreHistory.removeFirst();
        }
    }

    /**
     * @return the current game day
     */
    public int getCurrentDay() {
        return SpeciesChangeListDAO.getDay();
    }

    /**
     * Set the current game day.
     * Intended for testing purposes, not general usage.
     */
    void setCurrentDay(int day) {
        SpeciesChangeListDAO.setDay(day);
    }

    /**
     * Calculate the environment score for the current point in time, without smoothing.
     * @see https://bensfoodwebs.wordpress.com/2017/02/25/alternative-environment-score-for-world-of-balance/
     * @return the current environment score
     */
    public int rawEnvironmentScore() {
        int biomassScore = (int) Math.round(trophicLevelWeightedTotalBiomass());
        int diversityScore = (int) Math.round(biomassScore * shannonIndex());
        int totalScore = biomassScore + diversityScore;
        return totalScore;
    }

    /**
     * Calculate a weighted total biomass of all species in the ecosystem,
     * where the weight for a species is its trophic level.
     * @return the weighted total biomass
     */
    public double trophicLevelWeightedTotalBiomass() {
        double total = 0;
        for (Species species : speciesList.values()) {
            total += species.getTotalBiomass() * species.getSpeciesType().getTrophicLevel();
        }
        return total;
    }

    /**
     * Calculate the biomass-based Shannon index of the ecosystem.
     * The Shannon index is a standard measure of ecological diversity.
     * This version uses biomass to represent population, rather than the number of individuals,
     * and uses base-2 logarithms.
     * @return the Shannon index
     */
    public double shannonIndex() {
        int totalBiomass = this.totalBiomass();
        double shannon = 0;
        for (Species species : speciesList.values()) {
            if (species.getTotalBiomass() <= 0)
                continue;
            double proportion = (double) species.getTotalBiomass() / Math.max(1, totalBiomass);
            shannon -= proportion * log2(proportion);
        }
        return shannon;
    }

    /**
     * @return the total biomass of all species in the ecosystem
     */
    public int totalBiomass() {
        int total = 0;
        for (Species species : speciesList.values()) {
            total += species.getTotalBiomass();
        }
        return total;
    }

    public void updateScore() {
        updateEnvironmentScore();

        /* It seems that only web services simulation supports score_csv
        Log.println("score_csv.size() " + (score_csv.size()));
        
        List<String> rowFirst = score_csv.get(0), rowSecond = score_csv.get(1);
        Log.println("after score_csv");

        int lastMonth = Integer.valueOf(rowFirst.get(rowFirst.size() - 1));
        int currentMonth = gameEngine.getCurrentMonth();
        Log.println("after currentMonth");

        for (int i = lastMonth; i <= currentMonth; i++) {
            if (i == lastMonth && i == currentMonth) {
                rowSecond.set(i, Integer.toString(score));
            } else if (i != lastMonth) {
                rowFirst.add(Integer.toString(i));
                rowSecond.add(Integer.toString(score));
            }
        }
        Log.println("after for loop");

        String csv = CSVParser.createCSV(score_csv);
        Log.println("after CSVParser");
        CSVDAO.createScoreCSV(eco_id, csv);
        
        Log.println("In EcoSystem, before ResponseChart()");
        Log.println("csv = " + csv);
        if(!Constants.DEBUG_MODE){
	        ResponseChart response = new ResponseChart();
	        response.setType(2);
	        response.setCSV(csv);
	        NetworkFunctions.sendToWorld(response, world_id);
        }
        Log.println("In EcoSystem, after ResponseChart()");

        */
        updateAccumEnvScore();
    }

    /**
     * Calculate the current and high environment scores,
     * updating the database and sending the new score values to the client.
     */
    public void updateEnvironmentScore() {

        if (!rawScoreHistoryLoaded) {
            loadRawScoreHistory();
        }
        score = scaledSmoothedEnvironmentScore();
        ScoreHistoryDAO.setRawScore(eco_id, rawScoreHistoryLastDay, rawScoreHistory.getLast());

        if (score > highEnvScore) {
            highEnvScore = score;
        }

        ScoreDAO.updateEnvironmentScore(eco_id, score, highEnvScore);

        if(!Constants.DEBUG_MODE){
            
            ResponseUpdateEnvironmentScore response = 
                    new ResponseUpdateEnvironmentScore(eco_id, score, highEnvScore);
            
            NetworkFunctions.sendToPlayer(response, player_id);
        }
    }

    /**
     * Populate the raw score history from the database.
     */
    void loadRawScoreHistory() {
        int startDay = getCurrentDay() - scoreSmoothingWindowSize;
        System.err.println("startDay = " + startDay);
        for (int rawScore : ScoreHistoryDAO.getRawScoreHistory(eco_id, startDay)) {
            rawScoreHistory.addLast(rawScore);
        }
        System.err.println("rawScoreHistory = " + rawScoreHistory);
        rawScoreHistoryLoaded = true;
    }

    public void updateAccumEnvScore() {
        accumEnvScore += score;
        ScoreDAO.updateAccumEnvScore(eco_id, accumEnvScore);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighEnvScore() {
        return highEnvScore;
    }

    public int setHighEnvScore(int score) {
        return this.highEnvScore = score;
    }

    public int getAccumulatedEnvScore() {
        return accumEnvScore;
    }

    public int setAccumulatedEnvScore(int score) {
        return this.accumEnvScore = score;
    }

    public Timestamp getLastPlayed() {
        return last_played;
    }

    public Timestamp setLastPlayed(Timestamp last_played) {
        return this.last_played = last_played;
    }

//    public int getTotalBiomass() {
//        int total = 0;
//
//        for (Species species : speciesList.values()) {
//            total += species.getTotalBiomass();
//        }
//
//        return total;
//    }
//
//    public Animal findPredator(Organism organism) {
//        Animal predator = null;
//
//        int species_id = organism.getSpeciesType().getID();
//        double distance = -1;
//
//        List<Animal> predatorList = getAnimals();
//        Collections.shuffle(predatorList);
//
//        for (Animal animal : predatorList) {
//            if (animal.getID() != organism.getID() && (organism.getOrganismType() == Constants.ORGANISM_TYPE_PLANT || animal.getHungerLevel() < 1.0f)) {
//                int[] preyList = animal.getSpeciesType().getPreyIDs();
//
//                if (preyList != null) {
//                    for (int prey_id : preyList) {
//                        if (prey_id == species_id) {
//                            double temp = Math.sqrt(Math.pow(animal.getX() - organism.getX(), 2) + Math.pow(animal.getY() - organism.getY(), 2));
//
//                            if (distance == -1 || temp < distance) {
//                                distance = temp;
//                                predator = animal;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return predator;
//    }
//
//    public Map<Short, Float> getParameters() {
//        return parametersList;
//    }
//
//    public Map<Short, Float> setParameters(HashMap<Short, Float> parametersList) {
//        return this.parametersList = parametersList;
//    }
//
//    public void updateAnimalTarget(int animalID, int xTarg, int yTarg) {
//        Animal animal = animals.get(animalID);
//
//        if (animal != null) {
//            animal.setTargetPos(xTarg, yTarg, 0);
//        }
//    }
//
//    public void updateAnimalCoors(int animalID, int xCoor, int yCoor) {
//        Animal animal = animals.get(animalID);
//
//        if (animal != null) {
//            animal.setPos(xCoor, yCoor, 0);
//        }
//    }
//
//
//    /**
//     * Set indicated parameter value for all Animal species in zone. JTC
//     *
//     * @param world
//     * @param param
//     * @param value
//     */
//    public void setAnimalNodeParam(World world, int param, double value) {
//        zoneNodes.setNodeParam(param, value, SpeciesZoneType.SpeciesTypeEnum.ANIMAL);
//    }
//
//    /**
//     * set indicated parameter value for all Plant species in zone. JTC
//     *
//     * @param world
//     * @param param
//     * @param value
//     */
//    public void setPlantNodeParam(World world, int param, double value) {
//        zoneNodes.setNodeParam(param, value, SpeciesZoneType.SpeciesTypeEnum.PLANT);
//    }
//
//    /**
//     * set indicated parameter value for single species in zone. JTC
//     *
//     * @param world
//     * @param param
//     * @param value
//     * @param species_id
//     */
//    public void setNodeParam(World world, int param, double value, int species_id) {
//        zoneNodes.setNodeParam(param, value, species_id);
//    }
//
//    /**
//     * get indicated parameter value for single species in zone. JTC
//     *
//     * @param world
//     * @param param
//     * @param species_id
//     * @return
//     */
//    public double getNodeParam(World world, int param, int species_id) {
//        return zoneNodes.getNodeParam(param, species_id);
//    }
//
//    /**
//     * reset indicated parameter value for all Animal species in zone. JTC
//     *
//     * @param world
//     * @param param
//     */
//    public void resetAnimalNodeParam(World world, int param) {
//        zoneNodes.resetNodeParam(param, SpeciesZoneType.SpeciesTypeEnum.ANIMAL);
//    }
//
//    /**
//     * reset indicated parameter value for all Plant species in zone
//     *
//     * @param world
//     * @param param
//     */
//    public void resetPlantNodeParam(World world, int param) {
//        zoneNodes.resetNodeParam(param, SpeciesZoneType.SpeciesTypeEnum.PLANT);
//    }
//
//    /**
//     * reset indicated parameter value for single species in zone. JTC
//     *
//     * @param world
//     * @param param
//     * @param species_id
//     */
//    public void resetNodeParam(World world, int param, int species_id) {
//        zoneNodes.resetNodeParam(param, species_id);
//    }
//
//    /**
//     * modify current biomass for all Animal species in zone by specified amt
//     * (+/- fraction). JTC
//     *
//     * @param world
//     * @param fraction
//     */
//    public void modifyAnimalNodeBiomassByFraction(World world, double fraction) {
//        zoneNodes.modifyNodeBiomassByFraction(fraction, SpeciesZoneType.SpeciesTypeEnum.ANIMAL);
//    }
//
//    /**
//     * modify current biomass for all Plant species in zone by specified amt
//     * (+/- fraction). JTC
//     *
//     * @param world
//     * @param fraction
//     */
//    public void modifyPlantNodeBiomassByFraction(World world, double fraction) {
//        zoneNodes.modifyNodeBiomassByFraction(fraction, SpeciesZoneType.SpeciesTypeEnum.PLANT);
//    }
//
//    /**
//     * modify current biomass for single species in zone by specified amt ( +/-
//     * fraction). JTC
//     *
//     * @param world
//     * @param fraction
//     * @param species_id
//     */
//    public void modifyNodeBiomassByFraction(World world, double fraction, int species_id) {
//        zoneNodes.modifyNodeBiomassByFraction(fraction, species_id);
//    }
//
//    /**
//     * modify current biomass for all Animal species in zone by specified amt
//     * (+/- ). JTC
//     *
//     * @param world
//     * @param amount
//     */
//    public void modifyAnimalNodeBiomassByAmount(World world, double amount) {
//        zoneNodes.modifyNodeBiomassByAmount(amount, SpeciesZoneType.SpeciesTypeEnum.ANIMAL);
//    }
//
//    /**
//     * modify current biomass for all Plant species in zone by specified amt
//     * (+/- ). JTC
//     *
//     * @param world
//     * @param amount
//     */
//    public void modifyPlantNodeBiomassByAmount(World world, double amount) {
//        zoneNodes.modifyNodeBiomassByAmount(amount, SpeciesZoneType.SpeciesTypeEnum.PLANT);
//    }
//
//    /**
//     * modify current biomass for single species in zone by specified amount
//     * (+/- ). JTC
//     *
//     * @param world
//     * @param amount
//     * @param species_id
//     */
//    public void modifyNodeBiomassByAmount(World world, double amount, int species_id) {
//        zoneNodes.modifyNodeBiomassByAmount(amount, species_id);
//    }

    /**
     * getter for zoneNodes
     *
     * @return zoneNodes
     */
    public ZoneNodes getZoneNodes() {
        return zoneNodes;
    }

//    public float getCarryingCapacity() {
//        return carryingCapacity;
//    }
//
//    public void setCarryingCapacity(float carryingCapacity) {
//        this.carryingCapacity = carryingCapacity;
//        //set carrying capacity for individ plant nodes. JTC
//        zoneNodes.setCarryingCapacity(carryingCapacity);
//    }
//
//    public float getPerBiomass() {
//        return perBiomass;
//    }
//
//    public void setPerBiomass(float perBiomass) {
//        this.perBiomass = perBiomass;
//    }

	public void updateTimeSteps(int timesteps) {
		EcosystemDAO.updateTimeStep(this.eco_id, timesteps);
	}
}
