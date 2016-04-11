package cow.net.response;

// Other Imports
import cow.metadata.NetworkCode;
import shared.util.GamePacket;
import cow.net.response.GameResponse;

/**
 *
 */
public class ResponseCardAttack extends GameResponse {
    private short status;

    public ResponseCardAttack() {
        response_id = NetworkCode.CARD_ATTACK;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        return packet.getBytes();
    }

    public void setStatus(short status){
    	this.status = status;
    }
}
