package shared.model;

// Java Imports
import java.util.HashMap;
import java.util.Map;

public class Species {

    private int species_id;
    private Map<Integer, SpeciesGroup> groups = new HashMap<Integer, SpeciesGroup>();
    private SpeciesType speciesType;

    public Species(int species_id, SpeciesType speciesType) {
        this.species_id = species_id;
        this.speciesType = speciesType;
    }

    public void add(SpeciesGroup group) {
        groups.put(group.getID(), group);
    }

    public void remove(int group_id) {
        groups.remove(group_id);
    }
    
    public int getID() {
        return species_id;
    }
    
    public SpeciesType getSpeciesType() {
        return speciesType;
    }
    
    public int getTotalBiomass() {
        int total = 0;
        for (SpeciesGroup group : groups.values()) {
            total += group.getBiomass();
        }
        return total;
    }

    public Map<Integer, SpeciesGroup> getGroups() {
        return groups;
    }
}
