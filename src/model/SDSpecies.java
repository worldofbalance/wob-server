
package model;

/**
 * Model of db entry for PlaySpecies table.  The species' attributes to use in the game.
 * @author Karl
 */
public class SDSpecies {
    int species_id;
    int health_max;
    float speed;
    int stamina_max;
    float power;
    
    public SDSpecies(){}
    
    public SDSpecies(int species_id, int health_max, float speed, int stamina_max, float power){
        this.species_id= species_id;
        this.health_max = health_max;
        this.speed = speed;
        this.stamina_max = stamina_max;
        this.power = power;
    }
    
    public int getSpeciesId(){
        return this.species_id;
    }
    
    public int getHealthMax(){
        return this.health_max;
    }
    
    public float getSpeed(){
        return this.speed;
    }
    
    public int getStaminaMax(){
        return this.stamina_max;
    }
    
    public float getPower(){
        return this.power;
    }
    
}
