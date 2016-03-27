/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.response;

import rr.util.GamePacket;

/**
 *
 * @author markfavis
 */
public class ResponseRRBoost extends GameResponse {

    private int boostItemID;

    public ResponseRRBoost() {
        this.boostItemID = 0;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);

        packet.addInt32(getBoostItemID());
        // nothing to response
        return packet.getBytes();
    }

    /**
     * @return the boostItemID
     */
    public int getBoostItemID() {
        return boostItemID;
    }

    /**
     * @param boostItemID the boostItemID to set
     */
    public void setBoostItemID(int boostItemID) {
        this.boostItemID = boostItemID;
    }

}
