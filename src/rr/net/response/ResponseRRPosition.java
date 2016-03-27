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
public class ResponseRRPosition extends GameResponse {
    
//    private int x, y;
    private float x, y;
    
    public ResponseRRPosition(){
        responseCode = Constants.SMSG_RRPOSITION;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
//
//        packet.addInt32(x);
//        packet.addInt32(y);
//        return packet.getBytes();
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }

        packet.addFloat(x);
        packet.addFloat(y);
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
    
}
