/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import java.io.IOException;
import rr.net.response.ResponseRREndGame;
import rr.race.Race;
import rr.race.RaceManager;
// import race.RacePlayer;
// import dataAccessLayer.RaceDAO;
import shared.util.DataReader;

/**
 *
 * @author markfavis
 */
public class RequestRREndGame extends GameRequest {

    private boolean gameCompleted;
    private String finalTime;
    private int p_id;
    private ResponseRREndGame responseRREndGame;

    public RequestRREndGame() {
        gameCompleted = false;
        finalTime = "";
    }

    @Override
    public void parse() throws IOException {
        gameCompleted = DataReader.readBoolean(dataInput);
        finalTime = DataReader.readString(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        // RacePlayer player; // the RacePlayer sending the request
        int thisPlayerID = this.client.getPlayer().getID();
        // end race
        Race race = RaceManager.manager.getRaceByPlayerID(thisPlayerID);
        if (race != null) {
            RaceManager.manager.endRace(race.getID(), thisPlayerID, finalTime);
        }
        
        // get player, and load final time
        // player = RaceManager.manager.getRaceByPlayerID(p_id).getPlayers().get(p_id);
        // player.setFinalTime(finalTime);
    }
}