package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.metadata.Constants;
import shared.util.DataReader;

public class RequestActivity extends GameRequest {

    private short type;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        type = DataReader.readShort(dataInput);
    }

    @Override
    public void process() throws Exception {
        if (type == Constants.ACTIVITY_MOUSE) {
            client.updateActiveTime();
        }
    }
}
