/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import java.util.ArrayList;
import java.util.HashMap;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 * 
 * @author David Hoff
 */
public class ResponseConvergeGetTime extends GameResponse {

    private short betTime = 0;
    private HashMap<Integer, Integer> betStatusList = new HashMap<Integer, Integer>();
    
    public ResponseConvergeGetTime() {
        response_id = NetworkCode.MC_GET_TIME;
    }

    public void setTime (short betTime) {
        this.betTime = betTime;
    }
    
    public void setBetStatusList(HashMap<Integer, Integer> betStatusList) {
        this.betStatusList = betStatusList;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        packet.addShort16(betTime);
        
        for (HashMap.Entry<Integer, Integer> entry : betStatusList.entrySet()) {
            int key = entry.getKey();
            short value = entry.getValue().shortValue();
            packet.addInt32(key);
            packet.addShort16(value);            
        }
        return packet.getBytes();
    }
}
