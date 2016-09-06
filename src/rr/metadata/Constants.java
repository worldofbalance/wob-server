package rr.metadata;

/**
 * The Constants class stores important variables as constants for later use.
 */
public class Constants {

    // Request (1xx) + Response (2xx)
    public final static short CMSG_AUTH = 101;
    public final static short SMSG_AUTH = 201;
    public final static short CMSG_HEARTBEAT = 102;
    public final static short SMSG_HEARTBEAT = 202;

    public final static short CMSG_GAMEOVER = 103;
    public final static short SMSG_GAMEOVER = 203;
    public final static short CMSG_OPPONENTDATA = 104;
    public final static short SMSG_OPPONENTDATA = 204;
    public final static short CMSG_LOOKING_FOR_OPPONENT = 105;
    public final static short SMSG_LOOKING_FOR_OPPONENT = 205;
    public final static short CMSG_IN_GAME_HEARTBEAT = 106;
    public final static short SMSG_IN_GAME_HEARTBEAT = 206;
    public final static short CMSG_GAME_STATE = 107;
    public final static short SMSG_GAME_STATE = 207;

    public final static short CMSG_RACE_INIT = 109;
    public final static short SMSG_RACE_INIT = 209;
    
    public final static short CMSG_KEYBOARD = 108;
    public final static short SMSG_KEYBOARD  = 208;

    public final static short CMSG_RRPOSITION= 110;
    public final static short SMSG_RRPOSITION = 210;
    
    public final static short CMSG_RRSPECIES= 111;
    public final static short SMSG_RRSPECIES = 211;
    
    public final static short CMSG_RRENDGAME = 112;
    public final static short SMSG_RRENDGAME = 212;
    public final static short CMSG_RRSTARTGAME = 113;
    public final static short SMSG_RRSTARTGAME = 213;
    public final static short CMSG_RRBOOST = 114;
    public final static short SMSG_RRBOOST = 214;
    public final static short CMSG_RRGETMAP = 115;
    public final static short SMSG_RRGETMAP = 215;
    public final static short CMSG_RRENDSESSION = 116;
    public final static short SMSG_RRENDSESSION = 216;
    
    public final static short CMSG_RRGETCURRENCY = 117;
    public final static short SMSG_RRGETCURRENCY = 217;
    
    // Organism Type
    public static final short ORGANISM_TYPE_ANIMAL = 0;
    public static final short ORGANISM_TYPE_PLANT = 1;
    // Parameter Type
    public static final short PARAMETER_K = 0;	//Plants Carrying capacity >0
    public static final short PARAMETER_R = 1;	//Plants Growth rate 0-1
    public static final short PARAMETER_X = 2;	//Plants Metabolic rate 0-1
    public static final short PARAMETER_X_A = 3;	//Animals
    public static final short PARAMETER_E = 4; //Animals assimilationEfficiency
    public static final short PARAMETER_D = 5; //Animals predatorInterference
    public static final short PARAMETER_Q = 6; //Animals functionalResponseControl
    public static final short PARAMETER_A = 7; //Animals relativeHalfSaturationDensity
    // Other
    public static final float BIOMASS_SCALE = 1000;
    public static final String CLIENT_VERSION = "1.00";
    public static final int TIMEOUT_SECONDS = 90;
    public static final String CSV_SAVE_PATH = "src/log/";
    public static final short MAX_NUMBER_OF_PLAYERS = 2;
}
