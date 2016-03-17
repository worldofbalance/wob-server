/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import cos.db.SpeciesDAO;
import lby.net.request.GameRequest;
import cos.net.response.ResponseClashSpeciesList;

/**
 * Sent immediately after entry into the Clash of Species game
 * Returns a list of species available for use as defending or
 * attacking elements in the game
 * @author lev
 */
public class RequestClashSpeciesList extends GameRequest{
        
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    /**
     * Generates a response containing a list of species available
     * for Clash of Species
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        //
        ResponseClashSpeciesList response = new ResponseClashSpeciesList();
        response.setSpeciesList(SpeciesDAO.getList());
        client.add(response);
    }
    
}
