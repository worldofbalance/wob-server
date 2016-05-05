/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import metadata.Constants;
import metadata.NetworkCode;

import utility.GamePacket;
import utility.Log;
/**
 *
 * @author anu
 */
public class ResponseSDStartGame extends GameResponse{

    
    private short status;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  Calendar cal = Calendar.getInstance();
    private String newTime;
//  Ignore comment below, 0 by convention is success.
    /*
    status:
        0 = opponent not ready
        1 = opponent ready
        2 = opponent quit
    */
    
    public ResponseSDStartGame() throws ParseException{
        responseCode = NetworkCode.SD_START_GAME;
        status = 0;
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());
        Log.println("current time in UTC "+ utcTime);
       
        cal.setTime(sdf.parse(utcTime));
        cal.add(Calendar.SECOND, 5);
        newTime = sdf.format(cal.getTime());
        Log.println("current time in UTC with offset of 5 seconds "+ newTime);
        Log.println("A ResponseSDStartGame has been sent out. With the time to start the timer");
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        
        packet.addShort16(status);
        packet.addString(newTime);
        
        return packet.getBytes();
    }
    



}
