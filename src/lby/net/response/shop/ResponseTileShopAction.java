/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.response.shop;

// Other Imports
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;
/**
 *
 * @author guanmingpan
 */


public class ResponseTileShopAction extends GameResponse {

    private short action;
    private short status;
    private int amount;
//    private String itemList;
    private int zone_id;

    public ResponseTileShopAction() {
        response_id = NetworkCode.TILE_SHOP_ACTION;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(action);
        packet.addShort16(status);
        packet.addInt32(zone_id);
//        if (status == 0) {
//            packet.addInt32(amount);
//        } else if (status == 2) {
//            packet.addString(itemList);
//        }

        return packet.getBytes();
    }

    public void setAction(short action) {
        this.action = action;
    }

    public void setStatus(int status) {
        this.status = (short) status;
    }

    public void setTotalSpent(int amount) {
        this.amount = amount;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }
}
