package cos.model;

// Java Imports
import java.util.HashMap;
import java.util.Map;

public class Species {

    private int species_id;
    private Map<Integer, SpeciesGroup> groups = new HashMap<Integer, SpeciesGroup>();
    private SpeciesType speciesType;

    public Species(){}
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

    //enumeration of species type for easy identification
    public static enum Type {
        PLANT(0),
        CARNIVORE(1),
        HERBIVORE(2),
        OMNIVORE(3);

        //encapsulation of private species type variables
        private final int value;
        private Type(int value) {
            this.value = value;
        }

        //getter for the Type value
        public int getValue() {
            return this.value;
        }
    }

    //integer ID for each species
    public int speciesId;

    //string for each species nomenclature
    public String name;

    //integer cost for each species
    public int price;

    //type of species
    public Type type;

    //string for each species individual description
    public String description;

    //integer value of species attack strength
    public int attackPoints;

    //integer value of species health points
    public int hitPoints;

    //integer value of species movement speed
    public int movementSpeed;

    //integer value of the number of attacks per second/cycle
    public int attackSpeed;
}
