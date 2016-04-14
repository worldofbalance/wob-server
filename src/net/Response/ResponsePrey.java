/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;

import metadata.Constants;
import utility.GamePacket;

/**
 *
 * @author anu
 */
public class ResponsePrey extends GameResponse {
   
   private int prey_id;
   private float x ;
   private float y;
   private boolean isAlive;
   
    public ResponsePrey() {
        responseCode = Constants.SMSG_RES_PREY;
    }
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(prey_id);
        packet.addFloat(x);
        packet.addFloat(y);
        packet.addBoolean(isAlive);
        return packet.getBytes();
    }

    public void setPrey_id(int prey_id) {
        this.prey_id = prey_id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
}
