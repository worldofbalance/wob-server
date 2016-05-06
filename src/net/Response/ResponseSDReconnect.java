/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;

import PlayTime.PlayTimePlayer;
import java.util.Map;
import metadata.Constants;
import metadata.NetworkCode;
import model.Player;
import model.Prey;
import utility.GamePacket;

/**
 *
 * @author Karl
 */
public class ResponseSDReconnect extends GameResponse{

    short status;
    Map<Integer, Prey> npcMap;
    Map<Integer, PlayTimePlayer> playerMap;
    
    
    public ResponseSDReconnect(){
        this.responseCode = NetworkCode.SD_RECONNECT;
    }
    
    
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
                
        packet.addShort16(status);        
        //other npc info
        Prey prey;
        packet.addInt32(npcMap.size());
        for(Map.Entry<Integer, Prey> entry : npcMap.entrySet()){
            prey = entry.getValue();
            packet.addFloat(prey.getX());
            packet.addFloat(prey.getY());
            packet.addInt32(prey.getSpecies_id());
        }
        //player info
        packet.addInt32(playerMap.size());
        PlayTimePlayer player;
        for(Map.Entry<Integer, PlayTimePlayer> entry : playerMap.entrySet()){
            player = entry.getValue();
            packet.addFloat(player.getX());
            packet.addFloat(player.getY());
            packet.addInt32(player.getNumber());
            packet.addFloat(player.getScore());
            packet.addInt32(player.getStomach());
            packet.addInt32(player.getTime());
        }
        
        return packet.getBytes();
    }
    
    public void setMapNpc(Map<Integer, Prey> map){
        this.npcMap = map;
    }
    
    public void setMapPlayer(Map<Integer, PlayTimePlayer> map){
        this.playerMap = map;
    }
    
}
