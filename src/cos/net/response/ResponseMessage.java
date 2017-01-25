package cos.net.response;

// Other Imports
import cos.metadata.NetworkCode;
import cos.util.GamePacket;

/**
 * The ResponseMessage class is used to sent chat messages to the client.
 */
public class ResponseMessage extends GameResponse {

    private short status;
    private String name;
    private String message;
    private short type;

    public ResponseMessage() {
        response_id = NetworkCode.MESSAGE;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        packet.addShort16(type);

        if (type == 0) {
            packet.addString(name);
        }

        packet.addString(message);

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public String setMessage(String message) {
        return this.message = message;
    }

    public void setType(int type) {
        this.type = (short) type;
    }
}
