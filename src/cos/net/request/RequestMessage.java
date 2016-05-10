package cos.net.request;

// Java Imports

import cos.db.LogDAO;
import cos.net.response.ResponseMessage;
import cos.util.DataReader;
import cos.util.NetworkFunctions;

import java.io.DataInputStream;
import java.io.IOException;

// Other Imports

/**
 * The RequestMessage class handles all incoming chat messages and redirect those
 * messages, if needed, to other users.
 */
public class RequestMessage extends GameRequest {

    private short type;
    private String message;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        type = DataReader.readShort(dataInput);
        message = DataReader.readString(dataInput).trim();

        if (message.isEmpty()) {
            throw new IOException();
        }
    }

    @Override
    public void process() throws Exception {
        LogDAO.createMessage(client.getAccount().getID(), message);

        ResponseMessage response = new ResponseMessage();
        response.setMessage(message);
        response.setName(client.getPlayer().getName());
        response.setType(type);
        NetworkFunctions.sendToGlobal(response);
    }
}
