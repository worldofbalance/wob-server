package shared.model;

import shared.util.Vector3;

public class SpeciesGroup {

    private Species species;
    private int group_id;
    private int biomass;
    private Vector3<Integer> position;
    private int user_id;

    public SpeciesGroup(Species species, int group_id, int biomass, Vector3<Integer> position) {
        this.species = species;
        this.group_id = group_id;
        this.biomass = biomass;
        this.position = position;
    }

    public Species getSpecies() {
        return species;
    }

    public int getID() {
        return group_id;
    }

    public int getBiomass() {
        return biomass;
    }
    
    public int setBiomass(int biomass) {
        return this.biomass = biomass;
    }

    public Vector3<Integer> getPosition() {
        return position;
    }
    
    public Vector3<Integer> setPosition(Vector3<Integer> position) {
        return this.position = position;
    }
    
    public int getUserID() {
        return user_id;
    }
    
    public int setUserID(int user_id) {
        return this.user_id = user_id;
    }
}
