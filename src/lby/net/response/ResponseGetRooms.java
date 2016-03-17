/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.response;

import java.util.Iterator;
import lby.GameRoom;
import lby.GameRoomManager;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author yanxingwang
 */
public class ResponseGetRooms extends GameResponse {
    public ResponseGetRooms() {
        response_id = NetworkCode.GET_ROOMS;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16((short) GameRoomManager.getInstance().getNumRooms());
        
        Iterator<GameRoom> it = GameRoomManager.getInstance().getRoomsIter();
        while(it.hasNext()) {
            GameRoom room = it.next();
            packet.addShort16((short)room.getID());
            packet.addShort16((short)room.getGameID());
            packet.addString(room.getHost());
            packet.addShort16((short)room.getNumClients());
            
            for (int i = 0; i < room.getNumClients(); ++i)  {
                packet.addShort16((short)room.getClient(i).getAccount().getID());
            }
        }

        return packet.getBytes();
    }
}
