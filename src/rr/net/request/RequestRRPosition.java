/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import rr.core.GameServer;
import rr.db.RaceDAO;
import java.io.IOException;
import rr.net.response.ResponseRRPosition;
import rr.race.RaceManager;
import rr.race.RacePlayer;
import shared.util.DataReader;

/**
 *
 * @author markfavis
 */
public class RequestRRPosition extends GameRequest {
    
//    private int x, y;
    private float x, y;
    private int p_id;
    private ResponseRRPosition responseRRPosition;
    
    @Override
    public void parse() throws IOException {

//        x = DataReader.readInt(dataInput);
//        y = DataReader.readInt(dataInput);

        x = Float.parseFloat(DataReader.readString(dataInput));
        y = Float.parseFloat(DataReader.readString(dataInput));
    }

    @Override
    public void doBusiness() throws Exception {
        RacePlayer player; // the RacePlayer sending the request
        System.out.println("X:  " +  x + "Y :  " + y);
//<<<<<<< HEAD:SpeedServer/src/networking/request/RRRequestPosition.java
//
//        rrResponsePosition = new RRResponsePosition();
//        rrResponsePosition.setX(x);
//        rrResponsePosition.setY(y);
////=======
//        
        responseRRPosition = new ResponseRRPosition();
        responseRRPosition.setX(x);
        responseRRPosition.setY(y);
//>>>>>>> start:SpeedServer/src/networking/request/RequestRRPosition.java
        
//        RaceManager.getInstance();
//        client.getPlayer().getID();
        
        //Debugging
//        Log.println(Integer.toString(RaceManager.getInstance().getRaceByPlayerID(client.getPlayer().getID()).getID()));        
//        Log.println(Integer.toString(RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID()).getOpponent(client.getPlayer()).getID()));
   
        //The playerID of the oppenet of the player who sent the request
        p_id = RaceManager.manager.getRaceByPlayerID(client.getPlayer().getID())
                .getOpponent(client.getPlayer()).getID();
                
        //NetworkManager.addResponseForUser(p_id, responsekeyboard);
        
        // get the player and define x and y at the time of request
        player = RaceManager.manager.getRaceByPlayerID(p_id).getPlayers().get(p_id);
        player.setX(x);
        player.setY(y);
        
        GameServer.getInstance().getThreadByPlayerID(p_id).send(responseRRPosition);
        
        RaceDAO.updateRace(player); // write to DB
    }
    
}
