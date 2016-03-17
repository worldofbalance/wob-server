package shared.model;

// Java Imports
import java.sql.SQLException;


// Other Imports
import shared.db.TileDAO;

/**
 * The Tile model class on the server side which represents a tile object
 * through the information that is provide in the database.
 */
public class Tile {
    //data fields

    private int tile_owner;
    private int tile_id;
    private int vegetation_capacity;
    private int terrain_type;
    private int x_position;
    private int y_position;
    private int z_position;
    private int zone_id;
    private int event_id;
    private String username;

    public int getZoneID() {
        return zone_id;
    }

    public void setZoneID(int zone_id) {
        this.zone_id = zone_id;
    }

    public int getEventID() {
        return event_id;
    }

    public void setEventID(int event_id) {
        this.event_id = event_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        try {
            this.username = TileDAO.getTileUsername(tile_id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //constructor
    public Tile(int tile_id) {
        this.tile_id = tile_id;
    }

    public Tile(int tile_id, int x_position, int y_position, int z_position) {
        this.tile_id = tile_id;
        this.x_position = x_position;
        this.y_position = y_position;
        this.z_position = z_position;
        this.vegetation_capacity = 0;
        this.terrain_type = 0;
    }

    public int getVegetationCapacity() {
        return vegetation_capacity;
    }

    public void setVegetationCapacity(int vegetation_capacity) {
        this.vegetation_capacity = vegetation_capacity;
    }

    public int getXPosition() {
        return x_position;
    }   

    public void setXPosition(int x_position) {
        this.x_position = x_position;
    }

    public int getYPosition() {
        return y_position;
    }

    public void setYPosition(int y_position) {
        this.y_position  = y_position;
    }

    public int getZPosition() {
        return z_position;
    }

    public void setZPosition(int z_position) {
        this.z_position = z_position;
    }

    public int getOwner() {
        return this.tile_owner;
    }

    public void setOwner(int tile_owner) {
        this.tile_owner = tile_owner;
    }

    public int getTileId() {
        return tile_id ;
    }
    
    public void setTileId(int tile_id) {
        this.tile_id = tile_id;
    }

    public int getTerrainType() {
        return terrain_type;
    }

    public void setTerrainType(int terrain_type) {
        this.terrain_type  = terrain_type;
    }

    public void setZoneID() {
        // TODO Auto-generated method stub
    }
}