package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.core.world.World;
import lby.net.response.ResponseUpdateTime;

public class RequestUpdateTime extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {

    }

    @Override
    public void process() throws Exception {
        if (client.getPlayer() != null) {
            World world = client.getPlayer().getWorld();

            if (world != null) {
                ResponseUpdateTime response = new ResponseUpdateTime(world.getDay(), world.getClock().getTime(), world.getTimeRate());
                client.add(response);
            }
        }
    }
}
