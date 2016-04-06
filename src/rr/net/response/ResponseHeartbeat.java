package rr.net.response;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package networking.response;
//
//// Other Imports
//import metadata.Constants;
//import model.Player;
//import utility.GamePacket;
//
///**
// *
// * @author markfavis
// */
//public class ResponseHeartbeat extends GameResponse{
//    
//    private int opponentX;
//    private int opponentY;
//    private int opponentDistanceTraveled;
//    private short gameover;
//    
//    public ResponseHeartbeat(){
//        responseCode = Constants.SMSG_HEARTBEAT;
//    }
//
//    @Override
//    public byte[] constructResponseInBytes() {
//        GamePacket packet = new GamePacket(responseCode);
//        packet.addInt32(opponentX);
//        packet.addInt32(opponentY);
//        packet.addInt32(opponentDistanceTraveled);
//        packet.addShort16(gameover);
//
//        return packet.getBytes();
//    }
//
//    /**
//     * @param opponentX the opponentX to set
//     */
//    public void setOpponentX(int opponentX) {
//        this.opponentX = opponentX;
//    }
//
//    /**
//     * @param opponentY the opponentY to set
//     */
//    public void setOpponentY(int opponentY) {
//        this.opponentY = opponentY;
//    }
//
//    /**
//     * @param opponentDistanceTraveled the opponentDistanceTraveled to set
//     */
//    public void setOpponentDistanceTraveled(int opponentDistanceTraveled) {
//        this.opponentDistanceTraveled = opponentDistanceTraveled;
//    }
//    
//    public void setOpponentGameover(short gameover) {
//        this.gameover = gameover;
//    }
//}
