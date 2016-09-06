/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.response;

import rr.metadata.Constants;
import rr.util.GamePacket;

/**
 *
 * @author markfavis
 */
public class ResponseRRGetMap extends GameResponse {
    
    private int randomNumber;
    
    public ResponseRRGetMap() {
        responseCode = Constants.SMSG_RRGETMAP;
    }
    
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        
        packet.addInt32(randomNumber);
        
        return packet.getBytes();
    }
    
    public int getRandomNumber() {
        return randomNumber;
    }
    
    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }
    
}
