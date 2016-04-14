package cow.net.response;

// Other Imports
import cow.metadata.NetworkCode;
import shared.util.GamePacket;
import cow.net.response.GameResponse;

public class ResponseSummonCard extends GameResponse {
   
	private short status;
	
    public ResponseSummonCard() {
        response_id = NetworkCode.SUMMON_CARD;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
       

        return packet.getBytes();
    }

    public void setStatus(short status){
    	this.status = status;
    }
}
