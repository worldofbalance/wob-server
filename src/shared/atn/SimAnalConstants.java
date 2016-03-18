package shared.atn;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author justinacotter
 */
public class SimAnalConstants {

    public static final int TROPHIC_GRPS = 5;
    public static final String[] TROPHIC_GRP_STR
            = {"1.0", "2.0", "2.5", "3.0", "3.5"};
    public static final int DIET_GRPS = 18;
    public static final String[] DIET_GRP_STR = {
        "00", "01", "02",
        "10", "11", "12",
        "20", "21", "22",
        "30", "31", "32",
        "40", "41", "42",
        "50", "51", "52"};
    public static final String ENDO = "endo";
    public static final String ECTO = "ecto";
    public static final String INVERT = "invert";
    public static final String PLANT = "plant";
    public static final int DIET_TYPES = 3;
    public static final String[] DIET_TYPE_STR = {"omni", "carni", "herbi"};
    public static final String[] MET_TYPE_STR = {ENDO, ECTO, INVERT, PLANT};
    public static final String[] CAT_ID_STR = {
        "resource", PLANT, "sm-animal", "lg-animal", "bird", "insect"};
    public static final String[] ORG_TYPE_STR = {"animal", PLANT};

    
    public static final int MAX_NODES = 33;
    public static final int MAX_NODE_ID = 95;
    
    public static final int MAX_TIMESTEPS = 201;
    public static final double AVG_TIME = 101.5;
    public static final double AVG_TIME_SQUARED = 13635.5;
    
    public static final int LINE_SLOPE_IDX = 0;
    public static final int LINE_YINTERCEPT_IDX = 1;
    public static final int LINE_XINTERCEPT_IDX = 2;
    
    protected static final int MAX_PATH_LENGTH = 4;
    protected static final double NORM_MAX = 1.0;
    protected static final double NORM_MIN = 0.0;
    
    protected static final double D_T = 0.01; //0.001f;  //timestep integration
    
}
