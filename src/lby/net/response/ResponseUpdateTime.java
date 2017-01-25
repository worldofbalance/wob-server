package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseUpdateTime extends GameResponse {

    private int day;
    private long time;
    private float rate;

    public ResponseUpdateTime(int day, long time, float rate) {
        response_id = NetworkCode.UPDATE_TIME;
        
        this.day = day;
        this.time = time;
        this.rate = rate;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(day);
        packet.addInt32((int) time);
        packet.addFloat(rate);

        return packet.getBytes();
    }
}
