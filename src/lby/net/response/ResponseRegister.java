package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 * The ResponseRegister class is used to inform the client whether the
 * registration was successful.
 */
public class ResponseRegister extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short EMAIL_IN_USE = 1;
    public final static short HANDLE_IN_USE = 2;
    // Variables
    private short status;

    public ResponseRegister() {
        response_id = NetworkCode.REGISTER;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        return packet.getBytes();
    }
}