/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cow.net.response;

import cow.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author rujuraj
 */
public class ResponseWeatherCard extends GameResponse {
    private short status;
	
    public ResponseWeatherCard() {
        response_id = NetworkCode.APPLY_WEATHER;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
       

        return packet.getBytes();
    }

    public void setStatus(short status){
    	this.status = status;
    }
    
}
