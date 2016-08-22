package shared.metadata;

// Java Imports
import java.util.HashMap;
import java.util.Map;

// Other Imports
import lby.net.request.GameRequest;
import shared.util.Log;

/**
 * The GameRequestTable class stores a mapping of unique request code numbers
 * with its corresponding request class.
 */
public class GameRequestTable {

    private static final Map<Short, Class> requestTable = new HashMap<Short, Class>(); // Request Code -> Class

    /**
     * Initialize the hash map by populating it with request codes and classes.
     */
    public static void init() {
        Log.console("Loading Requests...");

        NetworkCode.check();
        // Populate the table using request codes and class names
        add(NetworkCode.CLIENT, "lby.net.request.RequestClient");
        add(NetworkCode.HEARTBEAT, "lby.net.request.RequestHeartbeat");
        add(NetworkCode.ACTIVITY, "lby.net.request.RequestActivity");
        add(NetworkCode.LOGIN, "lby.net.request.RequestLogin");
        add(NetworkCode.LOGOUT, "lby.net.request.RequestLogout");
        add(NetworkCode.REGISTER, "lby.net.request.RequestRegister");
        add(NetworkCode.ERROR_LOG, "lby.net.request.RequestErrorLog");
        add(NetworkCode.MESSAGE, "lby.net.request.RequestMessage");

        add(NetworkCode.PLAYERS, "lby.net.request.RequestPlayers");
        add(NetworkCode.STATISTICS, "lby.net.request.RequestStats");
        add(NetworkCode.HIGH_SCORE, "lby.net.request.RequestHighScore");
        add(NetworkCode.CHART, "lby.net.request.RequestChart");
        add(NetworkCode.SPECIES_LIST, "lby.net.request.RequestSpeciesList");
        add(NetworkCode.SPECIES_ACTION, "lby.net.request.RequestSpeciesAction");
        add(NetworkCode.PREDICTION, "lby.net.request.RequestPrediction");
        
        add(NetworkCode.PLAYER_SELECT, "lby.net.request.RequestPlayerSelect");
        add(NetworkCode.ECOSYSTEM, "lby.net.request.RequestEcosystem");

        add(NetworkCode.UPDATE_TIME, "lby.net.request.RequestUpdateTime");

        // Badge
        add(NetworkCode.BADGE_LIST, "lby.net.request.badge.RequestBadgeList");
        // Shop
        add(NetworkCode.SHOP, "lby.net.request.shop.RequestShop");
        add(NetworkCode.SHOP_ACTION, "lby.net.request.shop.RequestShopAction");
        // World
        add(NetworkCode.WORLD, "lby.net.request.world.RequestWorld");
        add(NetworkCode.ZONE_LIST, "lby.net.request.world.RequestZoneList");
        add(NetworkCode.ZONE, "lby.net.request.world.RequestZone");
        add(NetworkCode.ZONE_UPDATE, "lby.net.request.world.RequestZoneUpdate");
        //Convergence Game
        add(NetworkCode.CONVERGE_ECOSYSTEMS, "cvg.net.request.RequestConvergeEcosystems");
        add(NetworkCode.CONVERGE_NEW_ATTEMPT, "cvg.net.request.RequestConvergeNewAttempt");
        add(NetworkCode.CONVERGE_PRIOR_ATTEMPT, "cvg.net.request.RequestConvergePriorAttempt");
        add(NetworkCode.CONVERGE_PRIOR_ATTEMPT_COUNT, "cvg.net.request.RequestConvergePriorAttemptCount");
        add(NetworkCode.CONVERGE_HINT, "cvg.net.request.RequestConvergeHint");
        add(NetworkCode.CONVERGE_HINT_COUNT, "cvg.net.request.RequestConvergeHintCount");
        add(NetworkCode.CONVERGE_NEW_ATTEMPT_SCORE, "cvg.net.request.RequestConvergeNewAttemptScore");

        // Multiplayer Convergence
        add(NetworkCode.MC_MATCH_INIT, "cvg.net.request.RequestMCMatchInit");   
        add(NetworkCode.MC_GET_TIME, "cvg.net.request.RequestConvergeGetTime");
        add(NetworkCode.MC_BET_UPDATE, "cvg.net.request.RequestConvergeBetUpdate");
        add(NetworkCode.MC_GET_NAMES, "cvg.net.request.RequestConvergeGetNames");
        add(NetworkCode.MC_GET_OTHER_SCORE, "cvg.net.request.RequestConvergeGetOtherScore");
        add(NetworkCode.MC_CHECK_PLAYERS, "cvg.net.request.RequestConvergeCheckPlayers");
        add(NetworkCode.MC_HOST_CONFIG, "cvg.net.request.RequestConvergeSpecifyParams");
        add(NetworkCode.MC_NONHOST_CONFIG, "cvg.net.request.RequestConvergeNonhostConfig");
        add(NetworkCode.MC_SETUP, "lby.net.request.RequestMCSetup");
        
        add(NetworkCode.TOPLIST, "lby.net.request.RequestTopList");
        
        add(NetworkCode.WAITFORGAME, "lby.net.request.RequestWaitForGame");
        add(NetworkCode.NOWAITFORGAME, "lby.net.request.RequestNoWaitForGame");
        add(NetworkCode.WAITLIST, "lby.net.request.RequestWaitList");
        add(NetworkCode.WAITSTATUS, "lby.net.request.RequestWaitStatus");
        add(NetworkCode.STARTGAME, "lby.net.request.RequestStartGame");
        
        add(NetworkCode.PAIR, "lby.net.request.RequestPair");
        add(NetworkCode.QUIT_ROOM, "lby.net.request.RequestQuitRoom");
        add(NetworkCode.GET_ROOMS, "lby.net.request.RequestGetRooms");
        add(NetworkCode.BACK_TO_LOBBY, "lby.net.request.RequestBackToLobby");
        add(NetworkCode.PLAY_GAME, "lby.net.request.RequestPlayGame");
        add(NetworkCode.END_GAME, "lby.net.request.RequestEndGame");
        
        //Clash of species
	add(NetworkCode.CLASH_ENTRY, "cos.net.request.RequestClashEntry");
	add(NetworkCode.CLASH_SPECIES_LIST, "cos.net.request.RequestClashSpeciesList");
	add(NetworkCode.CLASH_DEFENSE_SETUP, "cos.net.request.RequestClashDefenseSetup");
	add(NetworkCode.CLASH_PLAYER_LIST, "cos.net.request.RequestClashPlayerList");
	add(NetworkCode.CLASH_PLAYER_VIEW, "cos.net.request.RequestClashPlayerView");
	add(NetworkCode.CLASH_INITIATE_BATTLE, "cos.net.request.RequestClashInitiateBattle");
	add(NetworkCode.CLASH_END_BATTLE, "cos.net.request.RequestClashEndBattle");

        //Cards of the wild
        /*
        add(NetworkCode.MATCH_INIT, "cow.net.request.RequestMatchInit");
        add(NetworkCode.MATCH_STATUS, "cow.net.request.RequestMatchStatus");
        add(NetworkCode.GET_DECK, "cow.net.request.RequestGetDeck");
        add(NetworkCode.SUMMON_CARD, "cow.net.request.RequestSummonCard");
        add(NetworkCode.CARD_ATTACK, "cow.net.request.RequestCardAttack");
        add(NetworkCode.QUIT_MATCH, "cow.net.request.RequestQuitMatch");
        add(NetworkCode.END_TURN, "cow.net.request.RequestEndTurn");
        add(NetworkCode.MATCH_OVER, "cow.net.request.RequestMatchOver");
        add(NetworkCode.DEAL_CARD, "cow.net.request.RequestDealCard");
        add(NetworkCode.TREE_ATTACK, "cow.net.request.RequestTreeAttack");
        add(NetworkCode.MATCH_ACTION, "cow.net.request.RequestMatchAction");
        add(NetworkCode.MATCH_START, "cow.net.request.RequestMatchStart");
        add(NetworkCode.RETURN_LOBBY, "cow.net.request.RequestReturnLobby");
        add(NetworkCode.APPLY_FOOD, "cow.net.request.RequestFoodCard");
        */



        Log.println("Done!");
    }

    /**
     * Map the request code number with its corresponding request class, derived
     * from its class name using reflection, by inserting the pair into the
     * table.
     *
     * @param request_id a value that uniquely identifies the request type
     * @param name a string value that holds the name of the request class
     */
    public static void add(short request_id, String name) {
        try {
            if (!requestTable.containsKey(request_id)) {
                requestTable.put(request_id, Class.forName(name));
            } else {
                Log.printf_e("Request ID [%d] already exists! Ignored '%s'\n", request_id, name);
            }
        } catch (ClassNotFoundException ex) {
            Log.printf_e("%s not found", ex.getMessage());
        }
    }

    /**
     * Get the instance of the request class by the given request code.
     *
     * @param request_id a value that uniquely identifies the request type
     * @return the instance of the request class
     */
    public static GameRequest get(short request_id) {
        GameRequest request = null;

        try {
            Class name = requestTable.get(request_id);

            if (name != null) {
                request = (GameRequest) name.getDeclaredConstructor().newInstance();
            } else {
                Log.printf_e("Request ID [%d] does not exist!\n", request_id);
            }
        } catch (Exception ex) {
            Log.println_e(ex.getMessage());
        }

        return request;
    }
}
