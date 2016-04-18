/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;

import metadata.Constants;
import utility.GamePacket;

/**
 *
 * @author anu
 */
public class ResponseSDEndGame extends GameResponse{

    private String winningPlayer;
    private float highestScore;    
    private boolean win;
 
    

    public ResponseSDEndGame() {
        responseCode = Constants.SMSG_SDENDGAME;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);

        packet.addBoolean(isWin());
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
    
    /**
     * @return the win
     */
    public boolean isWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(boolean win) {
        this.win = win;
    }

}
