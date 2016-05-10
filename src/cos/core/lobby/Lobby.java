package cos.core.lobby;

// Java Imports

import cos.model.Player;

import java.util.*;

// Other Imports

public class Lobby {

    // Variables
    protected int lobby_id;
    protected int capacity = 10;
    // Other
    protected final List<Player> playerList = new ArrayList<Player>();
    protected final Map<Integer, Boolean> readyList = new HashMap<Integer, Boolean>();

    public Lobby(int lobby_id, Player host) {
        this.lobby_id = lobby_id;
        add(host);
    }

    public int getID() {
        return lobby_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Player getHost() {
        return playerList.get(0);
    }

    public void setHost(int player_id) {
        playerList.add(0, remove(player_id));
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public boolean hasPlayer(int player_id) {
        return get(player_id) != null;
    }

    public final void add(Player player) {
        playerList.add(player);
        setReady(player.getID(), true);
    }

    public Player get(int player_id) {
        for (Player player : playerList) {
            if (player.getID() == player_id) {
                return player;
            }
        }

        return null;
    }

    public Player remove(int player_id) {
        Iterator<Player> itr = playerList.iterator();

        while (itr.hasNext()) {
            Player player = itr.next();

            if (player.getID() == player_id) {
                playerList.remove(player);
                return player;
            }
        }

        return null;
    }

    public void setReady(int player_id, boolean status) {
        if (status) {
            readyList.put(player_id, status);
        } else {
            readyList.remove(player_id);
        }
    }

    public boolean isReady() {
        return readyList.size() == playerList.size();
    }
}
