package cos.metadata;

// Java Imports
import cos.net.request.GameRequest;
import cos.util.Log;

import java.util.HashMap;
import java.util.Map;

// Other Imports

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
        add(NetworkCode.CLIENT, "RequestClient");
        add(NetworkCode.HEARTBEAT, "RequestHeartbeat");
        add(NetworkCode.LOGIN, "RequestLogin");
        add(NetworkCode.LOGOUT, "RequestLogout");
        add(NetworkCode.MESSAGE, "RequestMessage");

        add(NetworkCode.CHART, "RequestChart");
        add(NetworkCode.PREDICTION, "RequestPrediction");

        add(NetworkCode.PLAYER_SELECT, "RequestPlayerSelect");
        add(NetworkCode.ECOSYSTEM, "RequestEcosystem");


        // Badge
        add(NetworkCode.BADGE_LIST, "badge.RequestBadgeList");
        // Shop
        add(NetworkCode.SHOP_ACTION, "shop.RequestShopAction");
        // World
        add(NetworkCode.WORLD, "world.RequestWorld");

        //Clash of species
        add(NetworkCode.CLASH_ENTRY, "RequestClashEntry");
        add(NetworkCode.CLASH_SPECIES_LIST, "RequestClashSpeciesList");
        add(NetworkCode.CLASH_DEFENSE_SETUP, "RequestClashDefenseSetup");
        add(NetworkCode.CLASH_PLAYER_LIST, "RequestClashPlayerList");
        add(NetworkCode.CLASH_PLAYER_VIEW, "RequestClashPlayerView");
        add(NetworkCode.CLASH_INITIATE_BATTLE, "RequestClashInitiateBattle");
        add(NetworkCode.CLASH_END_BATTLE, "RequestClashEndBattle");

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
                requestTable.put(request_id, Class.forName("cos.net.request." + name));
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
