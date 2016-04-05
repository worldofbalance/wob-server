package cow.model;

public class Card {
	
	private final int card_id;
    private int species_id;
    private int health;
    private int attack;
    private int level;
    private String species_name;
    private String diet_type;
    private CardType cardType;
    private String description;
    
    public Card(int card_id) {
        this.card_id = card_id;
    }

    public Card(int card_id, 
    		    int species_id, 
    		    int health, 
    		    int attack, 
    		    int level, CardType cardType) {
        this.card_id = card_id;
        this.species_id = species_id;
        this.health = health;
        this.attack = attack;
        this.level = level;
        this.cardType = cardType;
    }

    public int getCardID() {
        return this.card_id;
    }
    
    public int getSpeciesID() {
        return this.species_id;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public int getAttack() {
        return this.attack;
    }

    public int getLevel() {
        return this.level;
    }
    
    public String getSpeciesName() {
    	return this.species_name;
    }
    
    public String getDietType() {
    	return this.diet_type;
    }

    

    public String getDescription() {
    	return this.description;
    }
    
    public void setOtherSpeciesData(String species_name, String diet_type, String description) {
    	this.species_name = species_name;
    	this.diet_type = diet_type;
    	this.description = description;
    }
   
    public void setSpeciesID(int species_id) {
        this.species_id = species_id;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public void setSpeciesName(String species_name) {
        this.species_name = species_name;
    }
    
    public void setDietType(String diet_type) {
        this.diet_type = diet_type;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setLevel(String description) {
        this.description = description;
    }

    public String toString(){
    	return "ID:" + this.card_id + 
    		   " | Name:" + this.species_name +
    		   " | Health:" + this.health + 
    		   " | Attack:" + this.attack + 
    		   " | Level:" + this.level +
    		   " | Diet Type:" + this.diet_type +
               " | Card Type:" + this.cardType +
    		   " | Description:" + this.description;
    }
}


