package rr.model;

// Other Imports
import rr.metadata.Constants;

/**
 * The PlantType class extends the SpeciesType class as well as including
 * additional variables that further describes the plant.
 */
public class PlantType extends SpeciesType {

    private int lightNeedFrequency;
    private float grow_radius;

    public PlantType(int species_id) {
        organism_type = Constants.ORGANISM_TYPE_PLANT;
        this.species_id = species_id;
    }

    public float getGrowRadius() {
        return grow_radius;
    }

    public void setGrowRadius(float grow_radius) {
        this.grow_radius = grow_radius;
    }

    public int getLightNeedFrequency() {
        return lightNeedFrequency;
    }

    public int setLightNeedFrequency(int lightNeedFrequency) {
        return this.lightNeedFrequency = lightNeedFrequency;
    }
}
