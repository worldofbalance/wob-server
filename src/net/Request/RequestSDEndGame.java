/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Request;

import java.io.IOException;
import utility.DataReader;
import net.Response.ResponseSDEndGame;
import PlayTime.Play;
import PlayTime.PlayManager;

/**
 *
 * @author anu
 */
public class RequestSDEndGame extends GameRequest{


    private boolean gameCompleted;
    private String finalScore;
    private int p_id;
    private ResponseSDEndGame responseSDEndGame;

    public RequestSDEndGame() {
        gameCompleted = false;
        finalScore = "";
    }

    @Override
    public void parse() throws IOException {
        gameCompleted = DataReader.readBoolean(dataInput);
        finalScore = DataReader.readString(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        // RacePlayer player; // the RacePlayer sending the request
        int thisPlayerID = this.client.getPlayer().getPlayer_id();
        // end race
        Play play = PlayManager.manager.getRaceByPlayerID(thisPlayerID);
        if (play != null) {
            PlayManager.manager.endRace(play.getID(), thisPlayerID, finalScore);
        }
        
    }

}
