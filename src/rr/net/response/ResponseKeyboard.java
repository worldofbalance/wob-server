/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.response;

import shared.metadata.Constants;
import shared.util.GamePacket;

/**
 *
 * @author Sbc-ComEx
 */
public class ResponseKeyboard extends GameResponse{
    
    private int key,keytype;
    
    
    public ResponseKeyboard(){
        responseCode = Constants.SMSG_KEYBOARD;
    }
    
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(keytype);
        packet.addInt32(key);
        return packet.getBytes();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKeytype() {
        return keytype;
    }

    public void setKeytype(int keytype) {
        this.keytype = keytype;
    }
    
    
    
}
