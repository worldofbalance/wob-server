/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import lby.net.response.GameResponse;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author ivanG
 */
public class ResponseConvergeNonHostConfig extends GameResponse {
    private short numRounds;
    private short timeWindow;
    private short betAmount;
    private short ecoNumber;
    private short sliders;

    public ResponseConvergeNonHostConfig(){
        response_id = NetworkCode.MC_NONHOST_CONFIG;
    }
    public void setNumRounds(short numRounds) {
        this.numRounds = numRounds;
    }

    public void setTimeWindow(short timeWindow) {
        this.timeWindow = timeWindow;
    }

    public void setBetAmount(short betAmount) {
        this.betAmount = betAmount;
    }

    public void setEcoNumber(short ecoNumber) {
        this.ecoNumber = ecoNumber;
    }
    
    public void setSliders(short sliders){
        this.sliders = sliders;
    }
    
     @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        packet.addShort16(numRounds);
        packet.addShort16(timeWindow);
        packet.addShort16(betAmount);
        packet.addShort16(ecoNumber);
        packet.addShort16(sliders);
        return packet.getBytes();
    }
    
}


