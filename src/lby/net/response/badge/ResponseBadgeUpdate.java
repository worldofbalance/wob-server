package lby.net.response.badge;

// Other Imports
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

public class ResponseBadgeUpdate extends GameResponse {

    private int badge_id;
    private int amount;
    private int progress;
    private boolean isDone;

    public ResponseBadgeUpdate() {
        response_id = NetworkCode.BADGE_UPDATE;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(badge_id);
        packet.addShort16((short) amount);
        packet.addShort16((short) progress);
        packet.addBoolean(isDone);

        return packet.getBytes();
    }

    public void setBadgeID(int badge_id) {
        this.badge_id = badge_id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
