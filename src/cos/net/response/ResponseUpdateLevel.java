package cos.net.response;

import cos.metadata.NetworkCode;
import cos.util.GamePacket;

/**
 *
 * @author Gary
 */
public class ResponseUpdateLevel extends GameResponse {

    private int amount;
    private int level;
    private String range;

    public ResponseUpdateLevel() {
        response_id = NetworkCode.UPDATE_LEVEL;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16((short) amount);
        packet.addShort16((short) level);
        packet.addString(range);
        return packet.getBytes();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
