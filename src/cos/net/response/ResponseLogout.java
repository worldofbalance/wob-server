package cos.net.response;

// Other Imports
import cos.metadata.NetworkCode;
import cos.util.GamePacket;

/**
 * The ResponseLogout class is used to inform the client whether it can allow
 * the user to exit the game safely.
 */
public class ResponseLogout extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short FAIL = 1;
    // Variables
    private short type;
    private short status;
    private int player_id;

    public ResponseLogout() {
        response_id = NetworkCode.LOGOUT;
    }

    public void setType(short type) {
        this.type = type;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setPlayerID(int player_id) {
        this.player_id = player_id;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(type);
        packet.addShort16(status);
        packet.addInt32(player_id);

        return packet.getBytes();
    }
}
