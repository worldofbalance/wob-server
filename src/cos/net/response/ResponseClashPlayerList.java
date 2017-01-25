/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.response;

import cos.metadata.NetworkCode;
import cos.model.Player;
import cos.util.GamePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for the list of players of Clash of Species game
 * @author lev
 */
public class ResponseClashPlayerList extends GameResponse{
    /**
     * List of players
     */
    private List<Player> players = new ArrayList<Player>();

    public ResponseClashPlayerList(){
        response_id = NetworkCode.CLASH_PLAYER_LIST;
    }

    /**
     * Adds a player to the stored list
     * @param pl the player to add
     */
    public void addPlayer(Player pl) {
        players.add(pl);
    }

    /**
     * Generates a byte array in the following format:
     *  id of this response (short)
     *  # of players returned (int)
     *  for each player: id (int)
     *                  name (string)
     * @return the byte array
     */
    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(players.size());
        for(Player pl : players) {
            packet.addInt32(pl.player_id);
            packet.addString(pl.name);
            // Also add level?
        }
        return packet.getBytes();
    }
}
