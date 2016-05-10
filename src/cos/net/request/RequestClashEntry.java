/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.request;

import cos.db.DefenseConfigDAO;
import cos.model.DefenseConfig;
import cos.net.response.ResponseClashEntry;
import cos.util.Vector2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author lev
 * Network request called when user enters the 'Clash of Species'
 * game from the lobby
 * Responds with the user's current defense configuration, if he or
 * she has one
 */

public class RequestClashEntry extends GameRequest{
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    /**
     * Generates an appropriate NetworkResponse
     * Determines if the user already has a Clash of Species defense
     * set up
     * Adds data about the defense setup to the response if it exists
     * Puts the response into the queue to be sent back to the client
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        DefenseConfig defense = DefenseConfigDAO.findByPlayerId(this.client.getPlayer().getID());
        boolean isNewClashPlayer;
        if(defense == null){
            isNewClashPlayer = true;
        }else{
            isNewClashPlayer = false;
        }

        ResponseClashEntry response = new ResponseClashEntry();
        response.setNewClashPlayer(isNewClashPlayer);
        if(!isNewClashPlayer){
            //add existing defense setup
            response.setDefenseTerrain(defense.terrain);
            for (Map.Entry<Integer, ArrayList<Vector2<Float>>> en : defense.layout.entrySet()) {
                response.addSpecies(en.getKey(), en.getValue());
            }
        }
        client.add(response);        
    }
    
}
