package shared.model;

// Other Imports
import shared.metadata.Constants;

/**
 * The PlantType class extends the SpeciesType class as well as including
 * additional variables that further describes the plant.
 */
public class PlantType extends SpeciesType {

    public PlantType(int species_id) {
        organism_type = Constants.ORGANISM_TYPE_PLANT;
        this.species_id = species_id;
    }
}
