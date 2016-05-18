/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.net.Response;

import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author anu
 */
public class ResponseScore extends GameResponse{
    
     private int Score;
    
    
    public ResponseScore(){
      //responseCode = Constants.SMSG_SCORE;
      responseCode = NetworkCode.SD_SCORE;
    }
    
@Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(Score);
        return packet.getBytes();
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    
}
