package cos.model;

/**
 * Species class holds all pertinent information of species variables in the
 * database.
 */
public class Species {
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

