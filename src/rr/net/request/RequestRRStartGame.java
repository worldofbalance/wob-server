/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import shared.core.GameServer;
import java.io.IOException;
import rr.net.response.ResponseRRStartGame;
import rr.race.Race;
import rr.race.RaceManager;
import rr.race.RaceManager;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author markfavis
 */
public class RequestRRStartGame extends GameRequest {

    private int p_id1;
    private int p_id2;

    private ResponseRRStartGame responseRRStartGame;

    @Override
    public void parse() throws IOException {
//        p_id1 = DataReader.readInt(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        Log.println("request start game from user: '" + client.getUserID() + "' recieved");
        
        Log.println("The race the user belongs to is '" +  RaceManager.manager.getRaceByPlayerID(client.getUserID()).getID() + "'");
        
        RaceManager.manager.getRaceByPlayerID(client.getUserID()).startRace(client.getUserID());
        
        // WAIT FOR OPPONENT TO SEND THE SAME PACKET
//
//        p_id2 = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
//                .getOpponent(client.getPlayer()).getID();
//
//        if (RaceManager.manager.readyToRace.containsKey(p_id1)) {
//            if (RaceManager.manager.readyToRace.containsKey(p_id2)) {
//                // send responses to both players
//                GameServer.getInstance().getThreadByPlayerID(p_id1).send(responseRRStartGame);
//                GameServer.getInstance().getThreadByPlayerID(p_id2).send(responseRRStartGame);
//
//                // remove players off the stack
//                RaceManager.manager.readyToRace.remove(p_id1);
//                RaceManager.manager.readyToRace.remove(p_id2);
//            } else {
//                RaceManager.manager.readyToRace.put(p_id1, p_id1);
//            }
//        }
    }
}
