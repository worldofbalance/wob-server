package cow.net.response;

import lby.net.response.GameResponse;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;


public class ResponseFoodCard extends GameResponse{
    private short status;

    public ResponseFoodCard() {
        response_id = NetworkCode.APPLY_FOOD;
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
