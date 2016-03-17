/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.request;

import shared.core.GameServer;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cos.db.ClashPlayerDAO;
import cos.db.DefenseConfigDAO;
import lby.net.request.GameRequest;
import cos.net.response.ResponseClashPlayerList;
import cos.model.Player;

/**
 * Request for a list of players of Clash of Species
 * Sent when the user has been confirmed to possess a defense config
 * and is entering the Clash game main menu, where a list of players
 * to challenge is displayed
 * @author lev
 */
public class RequestClashPlayerList extends GameRequest{

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    /**
     * Generates a response containing a list of players of
     * Clash of Species
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        ResponseClashPlayerList response = new ResponseClashPlayerList();

        List<Player> eligible = ClashPlayerDAO.findEligiblePlayers();
        for(Player pl : eligible){
            // Players don't need to be active to initialize a battle.
            // Don't display yourself in the list of potential opponents
            if(pl.id != client.getPlayer().getID()) {
                response.addPlayer(pl);
            }
        }
        client.add(response);
    }
    
}
