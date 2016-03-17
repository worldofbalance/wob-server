package lby.net.request.world;

// Java Imports
import java.io.IOException;
import java.io.DataInputStream;

// Other Imports
import lby.db.world.WorldZoneDAO;
import lby.net.request.GameRequest;
import lby.net.response.world.ResponseZoneUpdate;
import shared.util.DataReader;
/*
 * Not sure how the zone_id and tile_id are related. 
 * I am assuming a zone is divided into tiles- HJR
 * 
 */
public class RequestZoneUpdate extends GameRequest {

    private int tile_id, owner_id, vegetation_capacity, zone_id, natural_event;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        tile_id = DataReader.readInt(dataInput);
        owner_id = DataReader.readInt(dataInput);
        vegetation_capacity = DataReader.readInt(dataInput);
        zone_id = DataReader.readInt(dataInput);
        natural_event = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        WorldZoneDAO.updateOwner(owner_id, tile_id);
        WorldZoneDAO.updateVegetationCapacity(vegetation_capacity, tile_id);

        ResponseZoneUpdate response = new ResponseZoneUpdate();
        response.setStatus((short) 0);
        client.add(response);
    }
}
