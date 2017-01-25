package cos.model;

import cos.metadata.Constants;

public class AnimalType extends SpeciesType {

    public AnimalType(int species_id) {
        organism_type = Constants.ORGANISM_TYPE_ANIMAL;
        this.species_id = species_id;
    }
}
