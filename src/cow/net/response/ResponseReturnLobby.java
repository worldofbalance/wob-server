package cow.net.response;

import shared.util.GamePacket;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;

public class ResponseReturnLobby extends GameResponse {
	short status;
	public ResponseReturnLobby(){
		response_id = NetworkCode.RETURN_LOBBY;
	}
	
	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		GamePacket packet = new GamePacket(response_id);
		packet.addShort16(status);
		
		return null;
	}
	   
	public void setStatus(short status) {
        this.status = status;
    }

}
