package cos.metadata;

// Java Imports

import cos.db.SpeciesDAO;
import cos.model.AnimalType;
import cos.model.PlantType;
import cos.model.SpeciesType;
import cos.util.Comparators;
import cos.util.ConfigureException;
import cos.util.Log;

import java.util.*;


// Other Imports

public class SpeciesTable {

    private final Map<Integer, SpeciesType> speciesTypes = new HashMap<Integer, SpeciesType>();
    private final Map<Integer, AnimalType> animalTypes = new HashMap<Integer, AnimalType>(); // Species ID -> Animal
    private final Map<Integer, PlantType> plantTypes = new HashMap<Integer, PlantType>(); // Species ID -> Plant

    public SpeciesTable() {
    }

    public void initialize() throws ConfigureException {
        Log.console("Loading Species...");

        List<SpeciesType> speciesList = SpeciesDAO.getSpecies();
        if (speciesList.isEmpty()) {
            throw new ConfigureException("Species retrieval failure");
        }

        for (SpeciesType type : speciesList) {
            speciesTypes.put(type.getID(), type);

            switch (type.getOrganismType()) {
                case Constants.ORGANISM_TYPE_ANIMAL:
                    animalTypes.put(type.getID(), (AnimalType) type);
                    break;
                case Constants.ORGANISM_TYPE_PLANT:
                    plantTypes.put(type.getID(), (PlantType) type);
                    break;
            }

            type.setBiomass(500); // Temporary Override
        }

        Log.println("Done!");
    }

    public List<SpeciesType> getSpecies() {
        return new ArrayList<SpeciesType>(speciesTypes.values());
    }

    public SpeciesType getSpecies(int species_id) {
        return speciesTypes.containsKey(species_id) ? speciesTypes.get(species_id) : null;
    }

    public SpeciesType getSpecies(String name) {
        List<SpeciesType> speciesList = new ArrayList<SpeciesType>(speciesTypes.values());
        int index = Collections.binarySearch(speciesList, new SpeciesType(name), Comparators.SpeciesNameComparator);

        return index < 0 ? null : speciesList.get(index);
    }

    public SpeciesType getSpeciesTypeByNodeID(int node_id) {
        return getSpeciesTypeByNodeList(new int[]{node_id});
    }

    public SpeciesType getSpeciesTypeByNodeList(int[] nodeList) {
        for (SpeciesType speciesType : speciesTypes.values()) {
            if (speciesType.equalsNodeList(nodeList)) {
                return speciesType;
            }
        }

        return null;
    }

    public List<AnimalType> getAnimals() {
        return new ArrayList<AnimalType>(animalTypes.values());
    }

    public List<PlantType> getPlants() {
        return new ArrayList<PlantType>(plantTypes.values());
    }
}
