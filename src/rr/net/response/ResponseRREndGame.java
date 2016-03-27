/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.response;

import rr.metadata.Constants;
import rr.util.GamePacket;

/**
 *
 * @author markfavis
 */
public class ResponseRREndGame extends GameResponse {

    private String fastestPlayer;
    private String highestPoint;    
    private boolean win;
    private String winningTime;
    

    public ResponseRREndGame() {
        responseCode = Constants.SMSG_RRENDGAME;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);

        packet.addBoolean(isWin());
        packet.addString(getWinningTime());
        //packet.addString(getFastestPlayer());
        //packet.addString(getHighestPoint());

        return packet.getBytes();
    }

    /**
     * @return the fastestPlayer
     */
    public String getFastestPlayer() {
        return fastestPlayer;
    }

    /**
     * @param fastestPlayer the fastestPlayer to set
     */
    public void setFastestPlayer(String fastestPlayer) {
        this.fastestPlayer = fastestPlayer;
    }

    /**
     * @return the highestPoint
     */
    public String getHighestPoint() {
        return highestPoint;
    }

    /**
     * @param highestPoint the highestPoint to set
     */
    public void setHighestPoint(String highestPoint) {
        this.highestPoint = highestPoint;
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

    /**
     * @return the winningTime
     */
    public String getWinningTime() {
        return winningTime;
    }

    /**
     * @param winningTime the winningTime to set
     */
    public void setWinningTime(String winningTime) {
        this.winningTime = winningTime;
    }

}
