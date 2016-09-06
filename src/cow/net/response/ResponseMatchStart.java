package cow.net.response;

import cow.metadata.NetworkCode;
import cow.net.response.GameResponse;
import shared.util.GamePacket;


public class ResponseMatchStart extends GameResponse {

	private int matchID;
	private short status;
	
	public ResponseMatchStart() {
		response_id = NetworkCode.MATCH_START;
	}
	

	@Override
	public byte[] getBytes() {

        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        packet.addInt32(matchID);
        
		return packet.getBytes();
	}

	public void setMatchID(int matchID){
		this.matchID = matchID;
	}
	
	public void setStatus(short status){
		this.status = status;
	}
}
