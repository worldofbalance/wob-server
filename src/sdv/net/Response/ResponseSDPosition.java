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
public class ResponseSDPosition extends GameResponse{
    
    private float x, y, rotation;
    
    public ResponseSDPosition(){
        //responseCode = Constants.SMSG_POSITION;
        responseCode = NetworkCode.SD_PLAYER_POSITION;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addFloat(x);
        packet.addFloat(y);
        packet.addFloat(rotation);
        return packet.getBytes();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    
    
}
