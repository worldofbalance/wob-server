/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.net.Response;

import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author anu
 */
public class ResponseSDKeyboard extends GameResponse{
      private int keyCode,keyCombination;
    
    
    public ResponseSDKeyboard(){
      //responseCode = Constants.SMSG_KEYBOARD;
      responseCode = NetworkCode.SD_KEYBOARD;
    }
    
@Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(keyCode);
        packet.addInt32(keyCombination);
        return packet.getBytes();
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCombination() {
        return keyCombination;
    }

    public void setKeyCombination(int keyCombination) {
        this.keyCombination = keyCombination;
    }

 
    
}
