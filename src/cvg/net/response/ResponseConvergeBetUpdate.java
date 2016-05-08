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
 * ResponseConvergeHintCount returns # of hints in database
 * @author justinacotter
 */
public class ResponseConvergeBetUpdate extends GameResponse {

    private short won = 0;
    private int wonAmount = 0;
    
    public ResponseConvergeBetUpdate() {
        response_id = NetworkCode.MC_BET_UPDATE;
    }

    public void setWon (short won) {
        this.won = won;
    }
    
    public void setWonAmount (int wonAmount) {
        this.wonAmount = wonAmount; 
    }
            

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);

        packet.addShort16(won);
        packet.addInt32(wonAmount);

        return packet.getBytes();
    }
}
