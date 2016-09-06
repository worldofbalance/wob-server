package cos.net.request;

// Java Imports

import cos.model.Ecosystem;

import java.io.DataInputStream;
import java.io.IOException;

// Other Imports

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
