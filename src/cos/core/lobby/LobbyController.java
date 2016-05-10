package cos.core.lobby;

// Java Imports

import cos.core.GameEngine;
import cos.model.Ecosystem;
import cos.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// Other Imports

public class LobbyController {

    // Singleton Instance
    private static LobbyController controller;
    // Reference Tables
    private final Map<Integer, Lobby> lobbyList = new HashMap<Integer, Lobby>();

    private LobbyController() {
    }

    public static LobbyController getInstance() {
        return controller == null ? controller = new LobbyController() : controller;
    }

    public Lobby add(Lobby lobby) {
        return lobbyList.put(lobby.getID(), lobby);
    }

    public Lobby get(int lobby_id) {
        return lobbyList.get(lobby_id);
    }

    public Lobby remove(Lobby lobby) {
        return lobbyList.remove(lobby.getID());
    }

    public Lobby createLobby(Player player) {
        Lobby lobby = new Lobby(-1, player);

        return add(lobby);
    }
    
    public EcosystemLobby createEcosystemLobby(Player player, Ecosystem ecosystem) {
        EcosystemLobby lobby = new EcosystemLobby(-1, player, ecosystem);
        lobby.setGameEngine(new GameEngine(lobby, player.getWorld(), ecosystem));

        add(lobby);
        enter(player, lobby.getID());

        return lobby;
    }
    
    public void enter(Player player, int lobby_id) {
        Lobby lobby = get(lobby_id);
        if (lobby == null) {
            return;
        }

        if (lobby.getPlayers().size() < lobby.getCapacity()) {
            if (!lobby.hasPlayer(player.getID())) {
                lobby.add(player);
                player.setLobby(lobby);
            }
        }
    }

    public void leave(Lobby lobby, int player_id) {
        lobby.remove(player_id);

        List<Player> playerList = lobby.getPlayers();

        if (!playerList.isEmpty()) {
            if (player_id == lobby.getHost().getID()) {
                int index = new Random().nextInt(playerList.size());
                lobby.setHost(playerList.get(index).getID());
            }
        } else {
            remove(lobby);
        }
    }

    public void close(int lobby_id) {
        Lobby lobby = get(lobby_id);
        if (lobby == null) {
            return;
        }

        for (Player player : lobby.getPlayers()) {
            lobby.remove(player.getID());
        }

        remove(lobby);
    }
}
