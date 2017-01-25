/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.request;

import cos.db.ClashPlayerDAO;
import cos.db.DefenseConfigDAO;
import cos.model.DefenseConfig;
import cos.model.Player;
import cos.net.response.ResponseClashPlayerView;
import cos.util.DataReader;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Request for data on a specific Clash of Species player
 * sent when the user clicks on a player in the main menu of the
 * game to go into the detail view and potentially initiate a battle
 * @author lev
 */
public class RequestClashPlayerView extends GameRequest{

    /**
     * The id of the player for which the data is requested
     */
    private int playerID;

    /**
     * Reads in the id into the instance variable from the input stream
     * @param dataInput the input stream
     * @throws IOException
     */
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerID = DataReader.readInt(dataInput);
    }

    /**
     * Generates a response containing the Clash of Species-related data
     * for the requested player
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        ResponseClashPlayerView response = new ResponseClashPlayerView();

        Player target = ClashPlayerDAO.findById(playerID);
        DefenseConfig defcon = DefenseConfigDAO.findByPlayerId(playerID);

        if (target != null) {
            response.setDefenseConfig(defcon);
            //response.setPlayer(target);
        }
        client.add(response);
    }
    
}
