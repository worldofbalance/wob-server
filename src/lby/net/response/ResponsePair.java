/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author yanxingwang
 */
public class ResponsePair extends GameResponse {
    private short status = 0;
    private int id = 0;
    private int gameID = 0;

    public ResponsePair() {
        response_id = NetworkCode.PAIR;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        packet.addInt32(id);
        packet.addInt32(gameID);

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setGameID(int id) {
        this.gameID = id;
    }
}
