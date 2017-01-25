/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.response;

import java.util.List;
import shared.metadata.NetworkCode;
import shared.model.Daily;
import shared.model.Stat;
import shared.util.GamePacket;

/**
 *
 * @author guanmingpan
 */
public class ResponseStatus extends GameResponse {

    private String p1, p2, p3;
    private int s1, s2, s3;
    private List<Daily> dailyList;

    public ResponseStatus() {
        response_id = NetworkCode.STATUS;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addString(p1);
        packet.addInt32(s1);
        packet.addString(p2);
        packet.addInt32(s2);
        packet.addString(p3);
        packet.addInt32(s3);

//        packet.addShort16((short) statList.size());
//
//        for (Stat stat : statList) {
//            packet.addShort16((short) stat.getMonth());
//            packet.addString(stat.getSpeciesName());
//            packet.addString(stat.getType());
//            packet.addShort16((short) stat.getAmount());
//        }
        return packet.getBytes();
    }
    
    public void setData(String p1, int s1, String p2, int s2, String p3, int s3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }
    public void setDailyList(List<Daily> dailyList) {
        this.dailyList = dailyList;
    }
}