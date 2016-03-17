package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.db.LogDAO;
import shared.util.DataReader;

public class RequestErrorLog extends GameRequest {

    private String message;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        message = DataReader.readString(dataInput);
    }

    @Override
    public void process() throws Exception {
        if (client.getAccount() == null) {
            LogDAO.createError(message);
        } else {
            LogDAO.createError(client.getAccount().getID(), message);
        }
    }
}
