package cow.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;
import lby.net.response.GameResponse;

/**
 *  Sets other player's status to inactive
 */
public class ResponseQuitMatch extends GameResponse {
    private short status;

    public ResponseQuitMatch() {
        response_id = NetworkCode.QUIT_MATCH;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
