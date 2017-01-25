package lby.core.world;

public class Zone {

    private final int zone_id;
    private final short row;
    private final short column;
    private short terrain_type;
    private int vegetation_capacity;
    private int player_id;

    public Zone(int zone_id, short row, short column) {
        this.zone_id = zone_id;
        this.row = row;
        this.column = column;
    }

    public int getID() {
        return zone_id;
    }

    public short getRow() {
        return row;
    }
    
    public short getColumn() {
        return column;
    }

    public short getTerrainType() {
        return terrain_type;
    }

    public void setTerrainType(short terrain_type) {
        this.terrain_type = terrain_type;
    }

    public int getVegetationCapacity() {
        return vegetation_capacity;
    }

    public void setVegetationCapacity(int vegetation_capacity) {
        this.vegetation_capacity = vegetation_capacity;
    }

    public int getOwner() {
        return this.player_id;
    }

    public void setOwner(int player_id) {
        this.player_id = player_id;
    }
}
