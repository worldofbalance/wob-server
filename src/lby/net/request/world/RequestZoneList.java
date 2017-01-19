package lby.net.request.world;

// Java Imports
import cos.util.Log;
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.core.world.World;
import lby.db.world.WorldZoneDAO;
import lby.net.request.GameRequest;
import lby.net.response.world.ResponseZoneList;

public class RequestZoneList extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        ResponseZoneList response = new ResponseZoneList();

        World world = client.getPlayer().getWorld();

        if (world != null) {
            response.setStatus(ResponseZoneList.SUCCESS);
            response.setZonePlayers(WorldZoneDAO.getZonePlayers(world.getID()));
            response.setZoneList((short) 40, (short) 40, WorldZoneDAO.getZoneList(world.getID()));
        } else {
            response.setStatus(ResponseZoneList.FAILED);
        }
        client.add(response);
    }
}
