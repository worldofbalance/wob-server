// @author Joseph Fernandez
// RunnerSpecies (playable species) models created from RunnerSpeciesDAO. Extends SpeciesType.

package rr.model;

public class RunnerSpecies extends SpeciesType {
    private final int health; // starting health of playable species
    private final float speed,
            jump, // jumping ability
            power; // "armor" ability
    private final boolean canFly;
    
    public RunnerSpecies(int speciesID, int health, float speed, float jump, float power, boolean canFly)
    {
        super();
        this.species_id = speciesID;
        this.health = health;
        this.speed = speed;
        this.jump = jump;
        this.power = power;
        this.canFly = canFly;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
    public float getJump()
    {
        return jump;
    }
    
    public float getPower()
    {
        return power;
    }
    
    public boolean canFly()
    {
        return canFly;
    }
}
