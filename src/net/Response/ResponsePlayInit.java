/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;

import metadata.Constants;
import metadata.NetworkCode;
import model.Player;
import utility.GamePacket;

/**
 *
 * @author anu
 */
public class ResponsePlayInit extends GameResponse{

 
    private short status;
    private Player player;
    private int number;//determines starting position of player.
    
    public ResponsePlayInit(){
        responseCode = NetworkCode.SD_PLAY_INIT;
        status = 0;
    }
    @Override
    public byte[] constructResponseInBytes() {        
        GamePacket packet = new GamePacket(responseCode);
                
        packet.addShort16(status);        
        if(player != null){
            packet.addInt32(player.getPlayer_id());
            packet.addInt32(number);
            if(player.getUsername()!=null)
                packet.addString(player.getUsername());
        }
        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }    

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setNumber(int number){
        this.number=number;
    }
    
  
}
