/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metadata;

/**
 *
 * @author anu
 */
public class Constants {
    public static final String CLIENT_VERSION = "1.00";
    public static final int TIMEOUT_SECONDS = 90;
    public static final short MAX_NUMBER_OF_PLAYERS = 2;
    public static final int NUM_PREY = 20;
    public static final int X_MIN = -120;
    public static final int X_MAX = 120;
    public static final int Y_MIN = -75;
    public static final int Y_MAX = 75;
    
    
   
    public final static short CMSG_AUTH = 101;
    public final static short SMSG_AUTH = 201;
    
    public final static short CMSG_RACE_INIT = 501;
    public final static short SMSG_RACE_INIT = 601;
    public final static short CMSG_SDSTARTGAME = 502;
    public final static short SMSG_SDSTARTGAME = 602;
    public final static short CMSG_SDENDGAME = 503;
    public final static short SMSG_SDENDGAME = 603;
    
    
    public final static short CMSG_KEYBOARD = 108;
    public final static short SMSG_KEYBOARD = 208;
 
    public final static short CMSG_POSITION = 110;
    public final static short SMSG_POSITION = 210;
    
    public final static short CMSG_REQ_PREY = 504;
    public final static short SMSG_RES_PREY = 604;
    public final static short CMSG_EAT_PREY = 505;
    public static final short SMSG_EAT_PREY = 605;
    public final static short CMSG_DISCONNECT = 506;
    public final static short SMSG_DISCONNECT = 606;
    public final static short CMSG_SCORE = 507;
    public final static short SMSG_SCORE = 607;

    public final static short SMSG_RECONNECT = 608;
}
