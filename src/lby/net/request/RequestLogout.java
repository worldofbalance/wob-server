package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.net.response.ResponseLogout;
import shared.util.DataReader;
import shared.util.Log;

/**
 * The RequestLogout class is used to alert the server that the user wants to
 * disconnect.
 */
public class RequestLogout extends GameRequest {

    private short type;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        type = DataReader.readShort(dataInput);
    }

    @Override
    public void process() throws Exception {
        short status = ResponseLogout.SUCCESS;

        if (status == ResponseLogout.SUCCESS) {
            if (client != null) {
            if (type == 0) { // Exit (+ Logout)
                client.end();
            } else if (type == 1) { // Logout
                client.logout();
            }
            } else {
                Log.consoleln("No client found during RequestLogout.");
            }
        }

        ResponseLogout response = new ResponseLogout();
        response.setType(type);
        response.setStatus(status);
        client.add(response);
    }
}
