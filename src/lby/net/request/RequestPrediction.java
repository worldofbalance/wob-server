package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.model.Ecosystem;
import shared.util.Log;

public class RequestPrediction extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        Ecosystem ecosystem = client.getPlayer().getEcosystem();

        if (ecosystem != null) {
            ecosystem.getGameEngine().forceSimulation();
        }
    }
}
