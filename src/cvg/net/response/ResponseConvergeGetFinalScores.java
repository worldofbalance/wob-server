/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;
import java.util.HashMap;
import java.util.Map;

/**
 * ResponseConvergeHintCount returns # of hints in database
 * @author justinacotter
 */
public class ResponseConvergeGetFinalScores extends GameResponse {
    
    int [] playerId = new int[5];
    int [] playerWinnings = new int[5];
    int [] playerLastImprove = new int[5];
    
    public ResponseConvergeGetFinalScores() {
        response_id = NetworkCode.MC_GET_FINAL_SCORES;
    }

    public void setPlayerId(int[] playerId) {
        this.playerId = playerId;
    }
    
    public void setPlayerWinnings(int[] playerWinnings) {
        this.playerWinnings = playerWinnings;
    }
    
    public void setPlayerLastImprove(int[] playerLastImprove) {
        this.playerLastImprove = playerLastImprove;
    }
    

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        for (int i = 0; i < 5; i++) {
            packet.addInt32(playerId[i]);
            packet.addInt32(playerWinnings[i]);
            packet.addInt32(playerLastImprove[i]);                       
        }
        
        return packet.getBytes();
    }
}
