package shared.util;

// Java Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Other Imports
import shared.core.GameServer;
import lby.core.Lobby;
import lby.core.LobbyController;
import lby.core.world.World;
import lby.core.world.WorldController;
import shared.model.Account;
import shared.model.Player;
import lby.net.response.GameResponse;

public class NetworkFunctions {

    private NetworkFunctions() {
    }

    /**
     * Push a pending response to a user's queue.
     *
     * @param account_id holds the player ID
     * @param response is the instance containing the response information
     */
    public static void sendToUser(GameResponse response, int account_id) {
        Account account = GameServer.getInstance().getActiveAccount(account_id);

        if (account != null) {
            account.getClient().add(response);
        } else {
            Log.printf_e("Failed to create response for user, %d.", account_id);
        }
    }

    public static void sendToPlayer(GameResponse response, int player_id) {
        Player player = GameServer.getInstance().getActivePlayer(player_id);

        if ((player != null) && (player.getClient() != null)) {
            player.getClient().add(response);
        } else {
            // Log.printf_e("Failed to create response for player, %d.", player_id);
        }
    }

    /**
     * Push a pending response to all users' queue except one user.
     *
     * @param response is the instance containing the response information
     * @param exclude_id holds the excluding player ID
     */
    public static void sendToGlobal(GameResponse response, Integer... exclude_id) {
        List<Integer> exclude = Arrays.asList(exclude_id);

        for (Player player : new ArrayList<Player>(GameServer.getInstance().getActivePlayers())) {
            if ((player != null) && (player.getClient() != null) && !exclude.contains(player.getID())) {
                player.getClient().add(response);
            }
        }
    }

    /**
     * Push a pending response to all users' queue in the same world.
     *
     * @param response is the instance containing the response information
     * @param world_id holds the world ID
     * @param exclude_id
     */
    public static void sendToWorld(GameResponse response, int world_id, Integer... exclude_id) {
        World world = WorldController.getInstance().get(world_id);

        if (world != null) {
            List<Integer> exclude = Arrays.asList(exclude_id);

            for (Player player : new ArrayList<Player>(world.getPlayers().values())) {
                if ((player != null) && (player.getClient() != null) && !exclude.contains(player.getID())) {
                    player.getClient().add(response);
                }
            }
        }
    }

    /**
     * Push a response to all user's queue in the given lobby.
     *
     * @param response
     * @param lobby_id
     * @param exclude_id
     */
    public static void sendToLobby(GameResponse response, int lobby_id, Integer... exclude_id) {
        Lobby lobby = LobbyController.getInstance().get(lobby_id);

        if (lobby != null) {
            List<Integer> exclude = Arrays.asList(exclude_id);

            for (Player player : new ArrayList<Player>(lobby.getPlayers())) {
                if ((player.getClient() != null) && !exclude.contains(player.getID())) {
                    player.getClient().add(response);
                }
            }
        }
    }
}
