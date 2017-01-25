/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 * ResponseConvergeHintCount returns # of hints in database
 * @author David Hoff
 */
public class ResponseConvergeGetOtherScore extends GameResponse {

    private int score0;
    private int score1;
    private int score2;
    private int score3;
    private int score4;        
    
    public ResponseConvergeGetOtherScore() {
        response_id = NetworkCode.MC_GET_OTHER_SCORE;
    }

    public void setScore0 (int score0) {
        this.score0 = score0;
    }
    public void setScore1 (int score1) {
        this.score1 = score1;
    }
    public void setScore2 (int score2) {
        this.score2 = score2;
    }
    public void setScore3 (int score3) {
        this.score3 = score3;
    }
    public void setScore4 (int score4) {
        this.score4 = score4;
    }
    

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        packet.addInt32(score0);
        packet.addInt32(score1);
        packet.addInt32(score2);
        packet.addInt32(score3);
        packet.addInt32(score4);
        
        return packet.getBytes();
    }
}
