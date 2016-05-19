/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.response;

import cos.metadata.NetworkCode;
import cos.util.GamePacket;

/**
 * Stores a flag to sent back to the client for whether
 * the attack configuration with which the player tried to initiate
 * a battle was valid
 * @author lev
 */
public class ResponseClashInitiateBattle extends GameResponse{

    /**
     * Whether the attack config was valid
     */
    private boolean valid;

    /**
     * Sets the validity flag
     * @param valid the valididity value
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public ResponseClashInitiateBattle(){
        response_id = NetworkCode.CLASH_INITIATE_BATTLE;
    }

    /**
     * Generates a byte array containing the validity flag
     * @return the byte array
     */
    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addBoolean(valid);
        return packet.getBytes();
    }
    
}
