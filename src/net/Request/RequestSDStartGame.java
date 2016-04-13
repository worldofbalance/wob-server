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

    private int p_id1;
    private int p_id2;
    
        // Responses
    private ResponseSDStartGame responseStart;

    public RequestSDStartGame() {
        responses.add(responseStart = new ResponseSDStartGame());
    }

    @Override
    public void parse() throws IOException {
             p_id1 = DataReader.readInt(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        Log.println("request start game from user: '" + client.getUserID() + "' received");
        
        Log.println("The race the user belongs to is '" +  PlayManager.manager.getRaceByPlayerID(client.getUserID()).getID() + "'");
        
        PlayManager.manager.getRaceByPlayerID(client.getUserID()).startRace(client.getUserID());
        /*
        p_id2 = PlayManager.manager.getRaceByPlayerID(client.getPlayer().getPlayer_id())
                .getOpponent(client.getPlayer()).getPlayer_id();
         GameServer.getInstance().getThreadByPlayerID(p_id1).send(responseStart);
         GameServer.getInstance().getThreadByPlayerID(p_id2).send(responseStart);*/
    }




}
