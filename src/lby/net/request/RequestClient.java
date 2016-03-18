package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.metadata.Constants;
import lby.net.response.ResponseClient;
import shared.util.DataReader;

public class RequestClient extends GameRequest {

    private String version;
    private String session_id;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        version = DataReader.readString(dataInput).trim();
        session_id = DataReader.readString(dataInput).trim();
    }

    @Override
    public void process() throws Exception {
        if (!session_id.isEmpty()) {
            
        }

        client.setVersion(version);

        ResponseClient response = new ResponseClient();

        if (version.compareTo(Constants.CLIENT_VERSION) >= 0) {
            response.setStatus(ResponseClient.SUCCESS);
            response.setSessionID(client.getID());
        } else {
            response.setStatus(ResponseClient.FAIL);
        }

        client.add(response);
    }
}
