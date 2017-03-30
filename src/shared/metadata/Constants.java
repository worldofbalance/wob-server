package shared.metadata;

/**
 * The Constants class stores important variables as constants for later use.
 */
public class Constants {

    // Game Types
    public final static short GAME_TYPE_PVE = 0;
    public final static short GAME_TYPE_PVP = 1;
    // Privacy Type
    public final static short PRIVACY_TYPE_PRIVATE = 0;
    public final static short PRIVACY_TYPE_PUBLIC = 1;
    // Diet Type
    public final static short DIET_TYPE_OMNIVORE = 0;
    public final static short DIET_TYPE_CARNIVORE = 1;
    public final static short DIET_TYPE_HERBIVORE = 2;
    // Avatar Type
    public final static short AVATAR_TYPE_PLANTER = 1;
    public final static short AVATAR_TYPE_BREEDER = 2;
    public final static short AVATAR_TYPE_WEATHER_MAN = 3;
    // Organism Type
    public final static short ORGANISM_TYPE_ANIMAL = 0;
    public final static short ORGANISM_TYPE_PLANT = 1;
    // Parameter Type
    public final static short PARAMETER_K = 0;	//Plants Carrying capacity >0
    public final static short PARAMETER_R = 1;	//Plants Growth rate 0-1
    public final static short PARAMETER_X = 2;	//Plants Metabolic rate 0-1
    public final static short PARAMETER_X_A = 3;	//Animals
    public final static short PARAMETER_E = 4; //Animals assimilationEfficiency
    public final static short PARAMETER_D = 5; //Animals predatorInterference
    public final static short PARAMETER_Q = 6; //Animals functionalResponseControl
    public final static short PARAMETER_A = 7; //Animals relativeHalfSaturationDensity
    // 5/6/14, JTC, parameter constants for player manipulation
    public static final short PARAM_MET_RATE = 0;
    public static final short PARAM_GROWTH_RATE = 1;
    // Create Organism Status
    public final static short CREATE_STATUS_DEFAULT = 0;
    public final static short CREATE_STATUS_BIRTH = 1;
    public final static short CREATE_STATUS_PURCHASE = 2;
    // Remove Organism Status
    public final static short REMOVE_STATUS_DEFAULT = 0;
    public final static short REMOVE_STATUS_DEATH = 1;
    // Activity Type
    public final static short ACTIVITY_MOUSE = 0;
    // Game Resource Type
    public final static short RESOURCE_XP = 0;
    public final static short RESOURCE_COINS = 1;
    public final static short RESOURCE_CREDITS = 2;
    public final static short RESOURCE_ENV_SCORE = 3;
    // Game Constants
    public final static int INITIAL_COINS = 100;
    public final static int MAX_COINS = 1000;
    public final static int INITIAL_CREDITS = 1500;
    public final static int MAX_CREDITS = 1000000;
    public final static int MAX_LEVEL = 10;
    public final static int STARTING_NEEDED_EXP = 1000;
    public final static float MULTIPLIER_EXP = 1f;
    public final static int MAX_WORLDS = 5;
    public final static int SCORE_MULTIPLIER = 10;  // Environment score is multiplied by this number
    // Other
    public final static float TIME_MODIFIER = 1f;
    public final static int SAVE_INTERVAL = 60000;
    public final static int SHOP_PROCESS_DELAY = 0;//20000;
    public final static float BIOMASS_SCALE = 1000;
    public final static boolean ROUND_BIOMASS = true;  // If true, simulated biomass will be rounded to integer values.
    public final static String CLIENT_VERSION = "1.00";
    //public final static int TIMEOUT_MILLISECONDS = 900000000;  //90000; - jtc, using breakpoints, need more time
    public final static int TIMEOUT_MILLISECONDS = 180000;
    public final static int DAY_DURATION = 6;
    public final static int MONTH_DURATION = 180;
    public final static int MAX_SPECIES_SIZE = 10;
    public final static String CSV_SAVE_PATH = "src/shared/log/sim/";
    public final static String ATN_CSV_SAVE_PATH = "src/shared/log/atn/";
    public final static String ATN_GENERATED_CSV_SAVE_PATH = "src/shared/log/atn/generated_atn/";
    public final static String ATN_ACCEPTED_CSV_SAVE_PATH = "src/shared/log/atn/accepted_atn_files/";
    public final static int MAX_CLIENT_THREADS = 10;
    public final static int ECOSYSTEM_TYPE = 1;
    public final static int TICK_RATE = 30;
    public final static int TICK_NANOSECOND = 1000000000 / TICK_RATE;

    //Tile
    public final static int TOTAL_TILE_NUM = 42 * 42;
    public final static int TILE_NO_OWNER = 0;

    //Natural Environment Constants
    public static final int NATURE_EVENT_CHANCE = 40; //40% chance every month
    public static final int TOTAL_GAME_TILES = 1681;
    public static final int NATURE_EVENT_TILES_AMNT = 250; //2500 out of 16807 tiles get affected, need to be multiple of 100 or division will have problems
    public static final int NATURE_EVENT_DAY_LENGTH = 5; //5 secs
    public static final int NATURE_EVENT_MONTH_LENGTH = NATURE_EVENT_DAY_LENGTH * 30; //EVERY month is 30 days
    public static final int NATURE_EVENT_SEASON_LENGTH = NATURE_EVENT_MONTH_LENGTH * 3; //season is 3 months
    public static final int NATURE_EVENT_YEAR_LENGTH = NATURE_EVENT_SEASON_LENGTH * 4; //year is 4 seasons
    public static final int NATURE_EVENT_DURATION = 120; // 2min
    
    // Battle
    public static final short ACTION_ATTACK = 1;
    public static final short ACTION_DEFEND = 2;
    public static final short ACTION_EXTERMINATE = 3;
    public static final short ACTION_PROTECT = 4;
    public static final short ACTION_DISASTER = 5;
    public static final short DISASTER_BLIZZARD = 1;
    public static final short DISASTER_TORNADO = 2;
    public static final short DISASTER_FIRE = 3;
    public static final short DISASTER_RAIN = 4;    
    // Number of months per turn in battle
    public static final short BATTLE_STEP = 1;
    // Ratio of starting score/biomass to defeat score/biomass
    public static final double BATTLE_END_RATIO = 0.4;
    public static final double BATTLE_TURN_LIMIT = 10;
    public static final double DEFEND_AMOUNT = 0.80;
    
    public static final double PARAM_INITVALUE = -1.0;  //4/8/14, JTC
    
    //4/15/14, JTC
    //PP = PRIMARY PRODUCER (AKA GRASS)
    public static final int PP_SPECIES_ID = 1005;   //grass species id (not node id)
    public static final int PP_NODE_ID = 5;
    
   //4/20/14, JTC, settings used by node_simtest table
    public static final int CATID_RESOURCE = 0;
    public static final int CATID_PLANT = 1;
    public static final int CATID_SMALL_ANIMAL = 2;
    public static final int CATID_LARGE_ANIMAL = 3;
    public static final int CATID_BIRD = 4;
    public static final int CATID_INSECT = 5;
    
    //4/20/14, JTC, settings used by node_simtest table
    public static final int MET_ENDO = 0;
    public static final int MET_ECTO = 1;
    public static final int MET_INVERT = 2;
    public static final int MET_PLANT = 3;
    /* components of link parameter assimiliation rate and metrate; indices are MET_*
    constants, above */
    public static final double[] A_T = {55.0, 2.3, 0.5, -1.0};
    public static final double[] A_J = {89.0, 8.9, 9.7, -1.0};
    public static final double[] F_J = {1.0, 0.2, 0.3, -1.0};
    public static final double F_R = 0.1;
    /* primary producer constant A_R required to generate valid metabolic rates */
    public static final double A_R = 1.0;  //valid 0.1 - 1.0
    
    public static final int ID_NOT_SET = -1;
    //for use in SimJob
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILURE = 1;
    
    public static final double INIT_BSI_STEPSIZE = 0.1;
    public static final double MAX_BSI_ERR = 0.5;
    public static final int ADDITIONAL_TIMESTEPS = 2;	//Needed so that it aligns with Simulationengine, otherwise client will be in processing state
	public static boolean useSimEngine = false;		//To run both the simulation engine and the atn engine set useSimEngine =true && useAtnEngine == true
	public static boolean useAtnEngine = true;

    // Set to true to run simulations using GraggBulirschStoerIntegrator from Apache Commons Math
    // Set to false to run simulations using BulirschStoerIntegration instead
    public static boolean useCommonsMathIntegrator = true;

	public static final boolean DEBUG_MODE = false;

    // DH change
    // Multiplayer Convergence
    public static boolean SINGLE_PLAYER = false;
    //for use in Cards of the Wild and Convergence
    //public static final boolean SINGLE_PLAYER = true;
    public static final short  STATUS_NO_MATCH = 2;
    
    public static final short STATUS_SUCCESS_CARDS = 0;
    public static final short STATUS_FAILURE_CARDS = 1;
    
    
    // for SeaDivided
    public static final int TIMEOUT_SECONDS = 90;
    public static final short MAX_NUMBER_OF_PLAYERS = 2;
    public static final int NUM_PREY = 40;
    public static final int X_MIN = -120;
    public static final int X_MAX = 120;
    public static final int Y_MIN = -75;
    public static final int Y_MAX = 75;
    public static final int MIN_PREY = 20;
    public static final int CREDIT_REWARD = 50;
    
}
