/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import rr.core.GameServer;
import java.io.IOException;
import rr.net.response.ResponseRRBoost;
import rr.race.RaceManager;
// import race.RacePlayer;
// import dataAccessLayer.RaceDAO;
import shared.util.DataReader;

/**
 * Request sent by Client when they run over a boost.
 * Responds with Boost Item ID
 * @author markfavis
 */
public class RequestRRBoost extends GameRequest {

    private int boostItemID;
    private int opponentID;
    private ResponseRRBoost responseRRBoost;

    @Override
    public void parse() throws IOException {
        this.boostItemID = DataReader.readInt(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        // RacePlayer player;
        // int numItems;
        
        responseRRBoost = new ResponseRRBoost();
        responseRRBoost.setBoostItemID(boostItemID);

        // inform opponent
        opponentID = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
        
        // get the player and update with the item
        /*
        player = RaceManager.manager.getRaceByPlayerID(opponentID).getPlayers().get(opponentID);
        player.setItemID(boostItemID);
        numItems = player.getNumItems();
        player.setNumItems(numItems + 1);
        */
        
        //NetworkManager.addResponseForUser(opponentID, responsekeyboard);
        GameServer.getInstance().getThreadByPlayerID(opponentID).send(responseRRBoost);
        
        // RaceDAO.updateRace(player);
    }

}
