package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 * The ResponseLogin class contains information about the authentication
 * process.
 */
public class ResponseHeartbeat extends GameResponse {
    // Variables
    private short status;
    
    public final static short LOST_CONNECTION = 0;

    public ResponseHeartbeat() {
        response_id = NetworkCode.HEARTBEAT;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
