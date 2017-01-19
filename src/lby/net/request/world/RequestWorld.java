package lby.net.request.world;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.core.world.WorldController;
import lby.net.request.GameRequest;
import shared.util.Log;

public class RequestWorld extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        if (client.getPlayer().getWorld() == null) {
            WorldController.enterWorld(client.getPlayer(), WorldController.getInstance().first().getID());
        }
    }
}
