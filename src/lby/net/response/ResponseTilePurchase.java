/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author pbroe_000
 */
public class ResponseTilePurchase extends GameResponse {
    
     public final static short SUCCESS = 0;
    public final static short FAILED = 1;
        // Variables
    private short status;
    private int zone_id;
    private int price;
    private int credits;
    
    
    public ResponseTilePurchase(){
        response_id = NetworkCode.TILE_PURCHASE;
    }
    
        @Override
    public byte[] getBytes() {
        
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        
        packet.addInt32(zone_id);
        packet.addInt32(credits);
        packet.addInt32(price);
        
        
        return packet.getBytes();
    }
    
    public void setStatus(short status) {
        this.status = status;
    }
    
    public void setZoneId(int id) {
        this.zone_id = id;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
    
    public void setCredits(int newCredits){
        this.credits = newCredits;
    }
}
