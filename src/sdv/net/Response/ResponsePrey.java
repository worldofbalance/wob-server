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
public class ResponsePrey extends GameResponse {
   
   private int prey_id;
   private int Species_id;
   private float x ;
   private float y;
   private float rotation;
   
   
    public ResponsePrey() {
        responseCode = NetworkCode.SD_PREY;
    }
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(Species_id);
        packet.addInt32(prey_id);
        packet.addFloat(x);
        packet.addFloat(y);
        packet.addFloat(rotation);
        return packet.getBytes();
    }


    public void setRotation(float rotation) {
        this.rotation = rotation;
    }


    public void setSpecies_id(int Species_id) {
        this.Species_id = Species_id;
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

   
    
}
