package cow.net.request;

import java.io.DataInputStream;
import java.io.IOException;


import cow.net.request.GameRequest;
import cow.net.response.ResponseReturnLobby;
import shared.util.Log;

public class RequestReturnLobby extends GameRequest {

	@Override
	public void parse(DataInputStream dataInput) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void process() throws Exception {
		// TODO Auto-generated method stub
                Log.printf("RequestReturnToLobby");
		ResponseReturnLobby response = new ResponseReturnLobby();
		short status = 0;
		response.setStatus(status);
		//client.add(response);
	
		
		/*
		 * Leaving client connected. TODO: Lobby
		// ha: clean up player info from server 
        GameServer server = GameServer.getInstance();
        
        server.removeActiveClient(client.getID());
        server.removeActivePlayer(client.getPlayer().getID());
    	*/
	
	}
	
	

}
