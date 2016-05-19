package cos.net.response.world;

// Other Imports
import cos.metadata.NetworkCode;
import cos.net.response.GameResponse;
import cos.util.GamePacket;

public class ResponseWorld extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short FAIL = 1;
    // Variables
    private short status;
    private int world_id;
    private String name;
    private short type;
    private float time_rate;
    private short day;

    public ResponseWorld() {
        response_id = NetworkCode.WORLD;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == SUCCESS) {
            packet.addInt32(world_id);
            packet.addString(name);
            packet.addShort16(type);
            packet.addFloat(time_rate);
            packet.addShort16(day);
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setWorld(int world_id, String name, short type, float time_rate, int day) {
        this.world_id = world_id;
        this.name = name;
        this.type = type;
        this.time_rate = time_rate;
        this.day = (short) day;
    }
}
