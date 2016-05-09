/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;

import metadata.Constants;
import metadata.NetworkCode;
import utility.GamePacket;

/**
 * holds the prey that was eaten and the other player in the game.
 * @author Karl
 */
public class ResponseDestroyPrey extends GameResponse{

    private short status;
    private int prey_id;
    
    public ResponseDestroyPrey(){
        responseCode = NetworkCode.SD_EAT_PREY;
    }
    
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);
        if(status ==0){
      
            packet.addInt32(prey_id);
        }
        return packet.getBytes();
    }

    public void setPreyId(int prey_id){
        this.prey_id = prey_id;
    }
    
    
}
