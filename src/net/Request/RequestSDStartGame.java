/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Request;

import java.io.IOException;
import utility.Log;
import net.Response.ResponseSDStartGame;
import PlayTime.Play;
import PlayTime.PlayManager;
import core.GameServer;
import utility.DataReader;
import utility.Log;

/**
 *
 * @author anu
 */
public class RequestSDStartGame extends GameRequest {

    @Override
    public void parse() throws IOException {

    }

    @Override
    public void doBusiness() throws Exception {
        Log.println("request start game from user: '" + client.getUserID() + "' received");
        
        Log.println("The race the user belongs to is '" +  PlayManager.manager.getRaceByPlayerID(client.getUserID()).getID() + "'");
        
        PlayManager.manager.getRaceByPlayerID(client.getUserID()).startRace(client.getUserID());
      
    }




}
