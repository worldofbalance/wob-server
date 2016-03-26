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
public class RRResponseSpecies extends GameResponse {
    private int id;

    public RRResponseSpecies() {
         responseCode = Constants.SMSG_RRSPECIES;
    }

    
    
    @Override
    public byte[] constructResponseInBytes() {
         GamePacket packet = new GamePacket(responseCode);
         packet.addInt32(id);
         return packet.getBytes();
       
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
