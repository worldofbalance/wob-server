/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.request;

import java.io.DataInputStream;
import java.io.IOException;
import lby.GameRoom;
import lby.GameRoomManager;
import lby.net.response.ResponseQuitRoom;
import shared.util.Log;

/**
 *
 * @author yanxing wang
 */
public class RequestQuitRoom extends GameRequest {
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        Log.printf("Client ID: %s", client.getID());
        GameRoom room = GameRoomManager.getInstance().getRoom(client.getID());
        
        Log.printf("Client requests to quit a game room[%d]", room.getID());
        
        ResponseQuitRoom response = new ResponseQuitRoom();     
        response.setStatus((short)1);
        room.sendResponse(response);
        
        GameRoomManager.getInstance().clientQuit(client);        
    }
}
