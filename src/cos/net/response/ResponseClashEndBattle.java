/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.response;

import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 * Container for the new virtual currency balance to be sent back
 * to the client upon finishing a battle
 * @author lev
 */
public class ResponseClashEndBattle extends GameResponse{
    
    
    public ResponseClashEndBattle(){
        response_id = NetworkCode.CLASH_END_BATTLE;
    }

    /**
     * Sets the balance to be sent
     * @param credits the new balance
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Stores the balance to be sent
     */
    private int credits;

    /**
     * Generates a byte array containing the new virtual currency
     * balance
     * @return the byte array
     */
    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(credits);
        return packet.getBytes();
    }
    
}
