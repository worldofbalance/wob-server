package shared.model;

// Other Imports
import shared.metadata.Constants;

public class Animal extends Organism {

    private float hungerLevel;

    public Animal(int animal_id) {
        organism_type = Constants.ORGANISM_TYPE_ANIMAL;
        organism_id = animal_id;

        hungerLevel = 0.0f;
    }

    public float getHungerLevel() {
        return hungerLevel;
    }

    public float setHungerLevel(float hungerLevel) {
        return this.hungerLevel = hungerLevel;
    }
}
