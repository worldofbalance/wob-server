package shared.metadata;

// Java Imports
import java.util.HashMap;
import java.util.Map;

// Other Imports
import lby.net.request.GameRequest;
import lby.net.request.badge.RequestBadgeList;
import lby.net.request.shop.RequestShop;
import lby.net.request.shop.RequestShopAction;
import lby.net.request.world.RequestWorld;
import lby.net.request.world.RequestZone;
import lby.net.request.world.RequestZoneList;
import lby.net.request.world.RequestZoneUpdate;
import shared.util.Log;
import lby.net.request.*;
import cvg.net.request.*;


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
        // Populate the table using request codes and class variables
        add(NetworkCode.CLIENT, RequestClient.class);
        add(NetworkCode.HEARTBEAT, RequestHeartbeat.class);
        add(NetworkCode.ACTIVITY, RequestActivity.class);
        add(NetworkCode.LOGIN, RequestLogin.class);
        add(NetworkCode.LOGOUT, RequestLogout.class);
        add(NetworkCode.REGISTER, RequestRegister.class);
        add(NetworkCode.ERROR_LOG, RequestErrorLog.class);
        add(NetworkCode.MESSAGE, RequestMessage.class);
        add(NetworkCode.CHAT, RequestChat.class);
        add(NetworkCode.PLAYERS, RequestPlayers.class);

        add(NetworkCode.STATISTICS, RequestStats.class);
        add(NetworkCode.HIGH_SCORE, RequestHighScore.class);
        add(NetworkCode.CHART, RequestChart.class);
        add(NetworkCode.SPECIES_LIST, RequestSpeciesList.class);
        add(NetworkCode.SPECIES_ACTION, RequestSpeciesAction.class);
        add(NetworkCode.PREDICTION, RequestPrediction.class);
        
        add(NetworkCode.PLAYER_SELECT, RequestPlayerSelect.class);
        add(NetworkCode.ECOSYSTEM, RequestEcosystem.class);

        add(NetworkCode.UPDATE_TIME, RequestUpdateTime.class);

        //MENU
        add(NetworkCode.STATUS, RequestStatus.class);
        
        // Badge
        add(NetworkCode.BADGE_LIST, RequestBadgeList.class);
        // Shop
        add(NetworkCode.SHOP, RequestShop.class);
        add(NetworkCode.SHOP_ACTION, RequestShopAction.class);
        add(NetworkCode.TILE_PRICE, RequestTilePrice.class);
        add(NetworkCode.TILE_PURCHASE, RequestTilePurchase.class);
        // World
        add(NetworkCode.WORLD, RequestWorld.class);
        add(NetworkCode.ZONE_LIST, RequestZoneList.class);
        add(NetworkCode.ZONE, RequestZone.class);
        add(NetworkCode.ZONE_UPDATE, RequestZoneUpdate.class);
        //Convergence Game
        add(NetworkCode.CONVERGE_ECOSYSTEMS, RequestConvergeEcosystems.class);
        add(NetworkCode.CONVERGE_NEW_ATTEMPT, RequestConvergeNewAttempt.class);
        add(NetworkCode.CONVERGE_PRIOR_ATTEMPT, RequestConvergePriorAttempt.class);
        add(NetworkCode.CONVERGE_PRIOR_ATTEMPT_COUNT, RequestConvergePriorAttemptCount.class);
        add(NetworkCode.CONVERGE_HINT, RequestConvergeHint.class);
        add(NetworkCode.CONVERGE_HINT_COUNT, RequestConvergeHintCount.class);
        add(NetworkCode.CONVERGE_NEW_ATTEMPT_SCORE, RequestConvergeNewAttemptScore.class);

        // Multiplayer Convergence
        add(NetworkCode.MC_MATCH_INIT, RequestMCMatchInit.class);
        add(NetworkCode.MC_GET_TIME, RequestConvergeGetTime.class);
        add(NetworkCode.MC_BET_UPDATE, RequestConvergeBetUpdate.class);
        add(NetworkCode.MC_GET_NAMES, RequestConvergeGetNames.class);
        add(NetworkCode.MC_GET_OTHER_SCORE, RequestConvergeGetOtherScore.class);
        add(NetworkCode.MC_CHECK_PLAYERS, RequestConvergeCheckPlayers.class);
        add(NetworkCode.MC_HOST_CONFIG, RequestConvergeSpecifyParams.class);
        add(NetworkCode.MC_NONHOST_CONFIG, RequestConvergeNonhostConfig.class);
        add(NetworkCode.MC_SETUP, RequestMCSetup.class);
        add(NetworkCode.MC_GET_FINAL_SCORES, RequestConvergeGetFinalScores.class);
        
        add(NetworkCode.TOPLIST, RequestTopList.class);
        
        add(NetworkCode.WAITFORGAME, RequestWaitForGame.class);
        add(NetworkCode.NOWAITFORGAME, RequestNoWaitForGame.class);
        add(NetworkCode.WAITLIST, RequestWaitList.class);
        add(NetworkCode.WAITSTATUS, RequestWaitStatus.class);
        add(NetworkCode.STARTGAME, RequestStartGame.class);
        
        add(NetworkCode.PAIR, RequestPair.class);
        add(NetworkCode.QUIT_ROOM, RequestQuitRoom.class);
        add(NetworkCode.GET_ROOMS, RequestGetRooms.class);
        add(NetworkCode.BACK_TO_LOBBY, RequestBackToLobby.class);
        add(NetworkCode.PLAY_GAME, RequestPlayGame.class);
        add(NetworkCode.END_GAME, RequestEndGame.class);
        //
        //        //Clash of species
        //	add(NetworkCode.CLASH_ENTRY, "cos.net.request.RequestClashEntry");
        //	add(NetworkCode.CLASH_SPECIES_LIST, "cos.net.request.RequestClashSpeciesList");
        //	add(NetworkCode.CLASH_DEFENSE_SETUP, "cos.net.request.RequestClashDefenseSetup");
        //	add(NetworkCode.CLASH_PLAYER_LIST, "cos.net.request.RequestClashPlayerList");
        //	add(NetworkCode.CLASH_PLAYER_VIEW, "cos.net.request.RequestClashPlayerView");
        //	add(NetworkCode.CLASH_INITIATE_BATTLE, "cos.net.request.RequestClashInitiateBattle");
        //	add(NetworkCode.CLASH_END_BATTLE, "cos.net.request.RequestClashEndBattle");

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
    public static void add(short request_id, Class<?> cls) {
        if (!requestTable.containsKey(request_id)) {
            requestTable.put(request_id, cls);
        }   else {
            Log.printf_e("Request ID [%d] already exists! Ignored '%s'\n", request_id, cls.getName());
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
