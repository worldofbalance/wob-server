/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 * 
 * @author David Hoff
 */

// 2017-1-16 DH Not used anymore. If used in future, it must be updated

public class ResponseConvergeCheckPlayers extends GameResponse {

    // status; 0 => no one removed; 1 => some player(s) removed
    private short status = 0;   
    
    public ResponseConvergeCheckPlayers() {
        response_id = NetworkCode.MC_CHECK_PLAYERS;
    }
    
    public void setStatus(short status) {
        this.status = status;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        packet.addShort16(status);

        return packet.getBytes();
    }
}
