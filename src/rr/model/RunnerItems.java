// @author Joseph Fernandez
// RunnerItems (collectable items/boosts) models created from RunnerItemsDAO.

package rr.model;

// Other Imports

public class RunnerItems {
    private final int runnerItemID, // ID of item
            healthBonus, // + or - health when obtaining item
            specialBonus; // specific bonuses
    private final float speedBonus, // + or - speed
            jumpBonus, // + or - jumping ability
            powerBonus; // + or - power
    private final String runnerItemName; // name of item
    
    public RunnerItems(int runnerItemID, String runnerItemName, int healthBonus, float speedBonus, float jumpBonus, float powerBonus, int specialBonus)
    {
        this.runnerItemID = runnerItemID;
        this.runnerItemName = runnerItemName;
        this.healthBonus = healthBonus;
        this.speedBonus = speedBonus;
        this.jumpBonus = jumpBonus;
        this.powerBonus = powerBonus;
        this.specialBonus = specialBonus;
    }
    
    // data retrieval
    public int getID()
    {
        return runnerItemID;
    }
    
    public String getItemName()
    {
        return runnerItemName;
    }
    
    public int getHealthBonus()
    {
        return healthBonus;
    }
    
    public float getSpeedBonus()
    {
        return speedBonus;
    }
    
    public float getJumpBonus()
    {
        return jumpBonus;
    }
    
    public float getPowerBonus()
    {
        return powerBonus;
    }
    
    public int getSpecialBonus()
    {
        return specialBonus;
    }
}
