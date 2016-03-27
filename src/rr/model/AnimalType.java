package rr.model;

import java.lang.reflect.Field;
import rr.metadata.Constants;

public class AnimalType extends SpeciesType {

    private float metabolism;

    public AnimalType(int species_id) {
        organism_type = Constants.ORGANISM_TYPE_ANIMAL;
        this.species_id = species_id;
    }

    public float getMetabolism() {
        return metabolism;
    }

    public void setMetabolism(float metabolism) {
        this.metabolism = metabolism;
    }

    @Override
    public String toString() {
        String str = "";

        str += "-----" + "\n";
        str += getClass().getName() + "\n";
        str += "\n";

        for (Field field : getClass().getDeclaredFields()) {
            try {
                str += field.getName() + " - " + field.get(this) + "\n";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        str += "-----";

        return str;
    }
}
