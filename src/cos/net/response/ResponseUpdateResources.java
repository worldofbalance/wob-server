package cos.net.response;

// Other Imports
import cos.metadata.NetworkCode;
import cos.util.GamePacket;

public class ResponseUpdateResources extends GameResponse {

    private short type;
    private int amount;
    private int target;

    public ResponseUpdateResources(short type, int amount, int target) {
        response_id = NetworkCode.UPDATE_RESOURCES;

        this.type = type;
        this.amount = amount;
        this.target = target;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(type);
        packet.addInt32(amount);
        packet.addInt32(target);

        return packet.getBytes();
    }
}
