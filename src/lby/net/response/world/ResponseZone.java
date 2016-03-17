package lby.net.response.world;

// Other Imports
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 *
 * @author Ari
 */
public class ResponseZone extends GameResponse {

    private short status;
    private int user_id;

    public ResponseZone() {
        response_id = NetworkCode.ZONE;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == 0) {
            packet.addString("Player's tile request successful.");
        } else {
            packet.addString("Player's tile request failed.");
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
