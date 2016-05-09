package cos.net.response;

// Other Imports
import cos.metadata.NetworkCode;
import cos.util.GamePacket;

public class ResponseClient extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short FAIL = 1;
    // Variables
    private short status;
    private String session_id;

    public ResponseClient() {
        response_id = NetworkCode.CLIENT;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == SUCCESS) {
            packet.addString(session_id);
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setSessionID(String session_id) {
        this.session_id = session_id;
    }
}
