/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;


import metadata.Constants;

import utility.GamePacket;
import utility.Log;
/**
 *
 * @author anu
 */
public class ResponseSDStartGame extends GameResponse{

    
    private short status;

//  Ignore comment below, 0 by convention is success.
    /*
    status:
        0 = opponent not ready
        1 = opponent ready
        2 = opponent quit
    */
    
    public ResponseSDStartGame(){
        responseCode = Constants.SMSG_SDSTARTGAME;
        status = 0;
        Log.println("A ResponseSDStartGame has been sent out.");
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        
        packet.addShort16(status);
        
        return packet.getBytes();
    }
    



}
