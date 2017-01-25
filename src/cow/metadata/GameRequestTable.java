package cow.metadata;

// Java Imports
import java.util.HashMap;
import java.util.Map;

// Other Imports
import cow.net.request.GameRequest;
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
        /*add(NetworkCode.CLIENT, "RequestClient");
        add(NetworkCode.HEARTBEAT, "RequestHeartbeat");
        add(NetworkCode.ACTIVITY, "RequestActivity");
        add(NetworkCode.LOGIN, "RequestLogin");
        add(NetworkCode.LOGOUT, "RequestLogout");
        add(NetworkCode.REGISTER, "RequestRegister");
        add(NetworkCode.ERROR_LOG, "RequestErrorLog");
        add(NetworkCode.MESSAGE, "RequestMessage");

        add(NetworkCode.PLAYERS, "RequestPlayers");
        add(NetworkCode.STATISTICS, "RequestStats");
        add(NetworkCode.HIGH_SCORE, "RequestHighScore");
        add(NetworkCode.CHART, "RequestChart");
        add(NetworkCode.SPECIES_LIST, "RequestSpeciesList");
        add(NetworkCode.SPECIES_ACTION, "RequestSpeciesAction");
        add(NetworkCode.PREDICTION, "RequestPrediction");
        
        add(NetworkCode.PLAYER_SELECT, "RequestPlayerSelect");
        add(NetworkCode.ECOSYSTEM, "RequestEcosystem");

        add(NetworkCode.UPDATE_TIME, "RequestUpdateTime");

        // Badge
        add(NetworkCode.BADGE_LIST, "badge.RequestBadgeList");
        // Shop
        add(NetworkCode.SHOP, "shop.RequestShop");
        add(NetworkCode.SHOP_ACTION, "shop.RequestShopAction");
        // World
        add(NetworkCode.WORLD, "world.RequestWorld");
        add(NetworkCode.ZONE_LIST, "world.RequestZoneList");
        add(NetworkCode.ZONE, "world.RequestZone");
        add(NetworkCode.ZONE_UPDATE, "world.RequestZoneUpdate");
        //Convergence Game
        add(NetworkCode.CONVERGE_ECOSYSTEMS, "convergegame.RequestConvergeEcosystems");
        add(NetworkCode.CONVERGE_NEW_ATTEMPT, "convergegame.RequestConvergeNewAttempt");
        add(NetworkCode.CONVERGE_PRIOR_ATTEMPT, "convergegame.RequestConvergePriorAttempt");
        add(NetworkCode.CONVERGE_PRIOR_ATTEMPT_COUNT, "convergegame.RequestConvergePriorAttemptCount");
        add(NetworkCode.CONVERGE_HINT, "convergegame.RequestConvergeHint");
        add(NetworkCode.CONVERGE_HINT_COUNT, "convergegame.RequestConvergeHintCount");
        add(NetworkCode.CONVERGE_NEW_ATTEMPT_SCORE, "convergegame.RequestConvergeNewAttemptScore");*/
        
        //Cards of the wild
        
        add(NetworkCode.MATCH_INIT, "RequestMatchInit"); 
        add(NetworkCode.MATCH_STATUS, "RequestMatchStatus"); 
        add(NetworkCode.GET_DECK, "RequestGetDeck"); 
        add(NetworkCode.SUMMON_CARD, "RequestSummonCard"); 
        add(NetworkCode.CARD_ATTACK, "RequestCardAttack"); 
        add(NetworkCode.QUIT_MATCH, "RequestQuitMatch"); 
        add(NetworkCode.END_TURN, "RequestEndTurn");
        add(NetworkCode.MATCH_OVER, "RequestMatchOver"); 
        add(NetworkCode.DEAL_CARD, "RequestDealCard");
        add(NetworkCode.TREE_ATTACK, "RequestTreeAttack"); 
        add(NetworkCode.MATCH_ACTION, "RequestMatchAction");
        add(NetworkCode.MATCH_START, "RequestMatchStart");
        add(NetworkCode.RETURN_LOBBY, "RequestReturnLobby");
        add(NetworkCode.APPLY_FOOD, "RequestFoodCard");
         add(NetworkCode.APPLY_WEATHER, "RequestWeatherCard");
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
                requestTable.put(request_id, Class.forName("cow.net.request." + name));
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
