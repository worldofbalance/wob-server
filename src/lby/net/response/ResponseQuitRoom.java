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
public class ResponseQuitRoom extends GameResponse {
    private short status = 0;
    
    public ResponseQuitRoom() {
        response_id = NetworkCode.QUIT_ROOM;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
