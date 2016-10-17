package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseChat extends GameResponse {

  // Status Codes
  public final static short SUCCESS = 0;
  public final static short FAIL = 1;

  // Variables
  private short status;
  private String username;
  private String message;

  public ResponseChat() {
    response_id = NetworkCode.CHAT;
  }

  @Override
  public byte[] getBytes() {
    GamePacket packet = new GamePacket(response_id);
    packet.addShort16(status);

    if (status == SUCCESS) {
      packet.addString(username);
      packet.addString(message);
    }

    return packet.getBytes();
  }

  public void setStatus(short status) {
    this.status = status;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  public void setMessage(String message) {
    this.message = message;
  }
}
