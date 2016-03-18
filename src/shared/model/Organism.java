package shared.model;

import shared.core.GameServer;
import shared.metadata.Constants;
import shared.model.SpeciesType;

public abstract class Organism {

    protected int organism_id;
    protected int player_id;
    protected int zone_id;
    protected float[] position;
    protected double biomass;
    protected long biomassStartTime;
    protected double targetBiomass;
    protected int targetBiomassTime;
    protected int waterNeedFrequency;
    protected int noWaterCount;
    protected int health_percent;
    protected int heal_chance;
    protected float[] target_position;
    protected SpeciesType speciesType;
    protected int organism_type;

    public Organism() {
        position = new float[]{0, 0, 0};
        target_position = new float[]{0, 0, 0};
    }

    public int getID() {
        return organism_id;
    }

    public int setID(int organism_id) {
        return this.organism_id = organism_id;
    }

    public int getSpeciesTypeID() {
        return speciesType.getID();
    }

    public SpeciesType setSpeciesTypeID(SpeciesType speciesType) {
        return this.speciesType = speciesType;
    }

    public int getOrganismType() {
        return organism_type;
    }
    
    public int setOrganismType(int organism_type) {
        return this.organism_type = organism_type;
    }

    public void onGetDailyWater() {
        if (noWaterCount > 0) {
            noWaterCount--;
        }
    }

    public void onNoDailyWater() {
        noWaterCount++;
    }

    public void resolveTargetBiomass(long gameScaleTime) {
        float percTimePassed = (float) ((gameScaleTime - biomassStartTime) / 86400);

        if (percTimePassed > 1.0) {
            percTimePassed = (float) 1.0;
        }

        biomass += (int) ((float) (targetBiomass - biomass) * percTimePassed);
        biomassStartTime = gameScaleTime;
    }

    public void setTargetBiomass(double targetBiomass) {
        double maxBiomass = speciesType.getBiomass();

        if (targetBiomass > maxBiomass) {
            this.targetBiomass = maxBiomass;
        } else if (targetBiomass < 0) {
            this.targetBiomass = 0;
        } else {
            this.targetBiomass = targetBiomass;
        }
    }

    public double getTargetBiomass() {
        return targetBiomass;
    }

    /**
     * @return the _biomass
     */
    public double getBiomass() {
        return biomass;
    }

    public double setBiomass(double biomass) {
        return this.biomass = biomass;
    }

    public int getZoneID() {
        return zone_id;
    }

    public int setZoneID(int zone_id) {
        return this.zone_id = zone_id;
    }

    public int getPlayerID() {
        return player_id;
    }

    public int setPlayerID(int player_id) {
        return this.player_id = player_id;
    }

    public float getX() {
        return position[0];
    }

    public void setX(float x) {
        position[0] = x;
    }

    public float getY() {
        return position[1];
    }

    public void setY(float y) {
        position[1] = y;
    }

    public float getZ() {
        return position[2];
    }

    public void setZ(float z) {
        position[2] = z;
    }

    public float[] getPos() {
        return position;
    }

    public float[] setPos(float x, float y, float z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;

        return position;
    }

    public float getTargetX() {
        return target_position[0];
    }

    public void setTargetX(float x) {
        target_position[0] = x;
    }

    public float getTargetY() {
        return target_position[1];
    }

    public void setTargetY(float y) {
        target_position[1] = y;
    }

    public float getTargetZ() {
        return target_position[2];
    }

    public void setTargetZ(float z) {
        target_position[2] = z;
    }

    public float[] getTargetPos() {
        return target_position;
    }

    public float[] setTargetPos(float x, float y, float z) {
        target_position[0] = x;
        target_position[1] = y;
        target_position[2] = z;

        return target_position;
    }

    public int getNoWaterCount() {
        return noWaterCount;
    }

    public int setNoWaterCount(int noWaterCount) {
        return this.noWaterCount = noWaterCount;
    }

    public int getHealthPercent() {
        return health_percent;
    }

    public void setHealthPercent(int health_percent) {
        this.health_percent = health_percent;
    }

    public SpeciesType getSpeciesType() {
        return speciesType;
    }

    public SpeciesType setSpeciesType(SpeciesType speciesType) {
        return this.speciesType = speciesType;
    }
}