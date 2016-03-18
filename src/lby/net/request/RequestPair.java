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
import lby.net.response.ResponsePair;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author yanxing wang
 */
public class RequestPair extends GameRequest {
    int gameID = 0;
    int pairParam = 0;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        gameID = DataReader.readInt(dataInput);
        
        // if pairParam is -1, just create a room
        // otherwise join the room with id = pairParam
        pairParam = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        GameRoom room;
        if (pairParam == -1) {
            room = GameRoomManager.getInstance().createRoomWithClient(client);
        } else {
            // Start pair
            room = GameRoomManager.getInstance().pairClient(client, pairParam);        
        }        
        
        room.setGameID(gameID);
        
        ResponsePair response = new ResponsePair();     
        response.setStatus(room.isFull() ? (short)0 : (short)1);
        response.setID(room.getID());
        response.setGameID(gameID);
        
        room.sendResponse(response);
    }
}
