/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.response;

import cos.metadata.NetworkCode;
import cos.util.GamePacket;

/**
 * Sent back after the user has sent a defense configuration for the
 * Clash of Species game
 * @author lev
 */
public class ResponseClashDefenseSetup extends GameResponse{

    /**
     * whether the configuration sent by the user was valid
     */
    private boolean validSetup;

    public void setValidSetup(boolean validSetup) {
        this.validSetup = validSetup;
    }
    
    public ResponseClashDefenseSetup(){
        response_id = NetworkCode.CLASH_DEFENSE_SETUP;
    }

    /**
     * Generates the byte array for sending the data back to the
     * client
     * @return the byte array
     */
    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addBoolean(validSetup);
        return packet.getBytes();
    }
    
}
