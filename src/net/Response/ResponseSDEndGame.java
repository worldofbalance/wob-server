/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;

import metadata.Constants;
import metadata.NetworkCode;
import utility.GamePacket;

/**
 *
 * @author anu
 */
public class ResponseSDEndGame extends GameResponse{

    private String winningPlayer;
    private float highestScore;    
    private int status;
 
    

    public ResponseSDEndGame() {
        //responseCode = Constants.SMSG_SDENDGAME;
        responseCode = NetworkCode.SD_END_GAME;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);

        packet.addInt32(getStatus());
        packet.addFloat(getHighestScore());
        packet.addString(getWinningPlayer());

        return packet.getBytes();
    }

    public String getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(String winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public float getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(float highestScore) {
        this.highestScore = highestScore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
  

}
