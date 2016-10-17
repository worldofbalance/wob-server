package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import lby.net.response.ResponseChart;
import lby.net.response.ResponseChat;
import shared.metadata.Constants;
import lby.net.response.ResponseClient;
import shared.util.DataReader;
import shared.util.NetworkFunctions;

public class RequestChat extends GameRequest {

  public String username;
  public String message;

  @Override
  public void parse(DataInputStream dataInput) throws IOException {
    username = DataReader.readString(dataInput).trim();
    message  = DataReader.readString(dataInput).trim();
  }

  @Override
  public void process() throws Exception {
    ResponseChat response = new ResponseChat();
    response.setMessage(message);
    response.setUsername(username);
    NetworkFunctions.sendToGlobal(response);
  }
}
