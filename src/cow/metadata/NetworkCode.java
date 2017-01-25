package cow.metadata;

// Java Imports
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// Other Imports
import shared.util.Log;

public class NetworkCode {

    // Request + Response
    /*public final static short CLIENT = 100;
    public final static short HEARTBEAT = 101;
    public final static short ACTIVITY = 102;
    public final static short LOGIN = 103;
    public final static short LOGOUT = 104;
    public final static short REGISTER = 105;
    public final static short ERROR_LOG = 106;
    public final static short MESSAGE = 107;

    public final static short PLAYERS = 108;
    public final static short SPECIES_LIST = 109;
    public final static short WORLD = 110;
    public final static short ZONE_LIST = 111;
    public final static short ZONE = 112;
    public final static short ZONE_UPDATE = 113;
    public final static short ECOSYSTEM = 114;
    public final static short PREDICTION = 115;
    
    public final static short SHOP = 116;
    public final static short SHOP_ACTION = 117;
    public final static short PARAMS = 118;
    public final static short CHANGE_PARAMETERS = 119;
    public final static short GET_FUNCTIONAL_PARAMETERS = 120;
    public final static short CHANGE_FUNCTIONAL_PARAMETERS = 121;
    public final static short STATISTICS = 122;
    public final static short HIGH_SCORE = 123;
    public final static short CHART = 124;
    public final static short SPECIES_ACTION = 125;
    public final static short BADGE_LIST = 126;

    public final static short BATTLE_REQ = 127;
    public final static short BATTLE_PREP = 128;
    public final static short SEASON_CHANGE = 129;
    public final static short BATTLE_CON = 130;
    public final static short BATTLE_ACTION = 131;
    public final static short BATTLE_TURN = 132;
    public final static short BATTLE_START = 133;

    public final static short UPDATE_RESOURCES = 134;
    public final static short SPECIES_KILL = 135;
    public final static short UPDATE_TIME = 136;
    public final static short SPECIES_CREATE = 137;
    public final static short OBJECTIVE_ACTION = 138;
    public final static short UPDATE_ENV_SCORE = 139;
    public final static short UPDATE_LEVEL = 140;
    public final static short BADGE_UPDATE = 141;
    public final static short UPDATE_SEASON = 142;
    public final static short UPDATE_CURRENT_EVENT = 143;
    public final static short BATTLE_END = 144;
    
    public final static short PLAYER_SELECT = 145;

    public final static short CONVERGE_ECOSYSTEMS = 146;
    public final static short CONVERGE_NEW_ATTEMPT = 147;
    public final static short CONVERGE_PRIOR_ATTEMPT = 148;
    public final static short CONVERGE_PRIOR_ATTEMPT_COUNT = 149;
    public final static short CONVERGE_HINT = 150;
    public final static short CONVERGE_HINT_COUNT = 151;
    public final static short CONVERGE_NEW_ATTEMPT_SCORE = 152;*/
    
    // Cards of the wild 
    public final static short MATCH_INIT= 201;
    public final static short MATCH_STATUS = 202;
    public final static short GET_DECK = 203;
    public final static short SUMMON_CARD = 204;
    public final static short CARD_ATTACK = 205;
    public final static short QUIT_MATCH = 206;
    public final static short MATCH_OVER = 207;
    public final static short END_TURN = 208;
    public final static short DEAL_CARD = 209;
    public final static short TREE_ATTACK = 210;
    public final static short MATCH_ACTION = 211;
    public final static short MATCH_START = 212;
    public final static short RETURN_LOBBY = 213;
    public final static short APPLY_FOOD = 214;
    public final static short APPLY_WEATHER=215;
    
    /**
     * Check for duplicate values, if any.
     */
    public static void check() {
        NetworkCode nCodes = new NetworkCode();
        Map<Short, String> nCodeMap = new HashMap<Short, String>();

        for (Field field : NetworkCode.class.getDeclaredFields()) {
            try {
                Short value = (Short) field.get(nCodes);

                if (nCodeMap.containsKey(value)) {
                    Log.println_e(field.getName() + " is conflicting with " + nCodeMap.get(value));
                } else {
                    nCodeMap.put(value, field.getName());
                }
            } catch (IllegalArgumentException ex) {
                Log.println_e(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Log.println_e(ex.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        String str = "";

        str += "-----" + "\n";
        str += getClass().getName() + "\n";
        str += "\n";

        for (Field field : getClass().getDeclaredFields()) {
            try {
                str += field.getName() + " - " + field.get(this) + "\n";
            } catch (IllegalArgumentException ex) {
                Log.println_e(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        str += "-----";

        return str;
    }
}
