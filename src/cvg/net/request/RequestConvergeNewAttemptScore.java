/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.request;

import shared.db.ConvergeAttemptDAO;

import java.io.DataInputStream;
import java.io.IOException;

import lby.net.request.GameRequest;
import cvg.net.response.ResponseConvergeNewAttemptScore;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author justinacotter
 */
public class RequestConvergeNewAttemptScore extends GameRequest {

    private int playerId;
    private int ecosystemId;
    private int attemptId;
    private int score;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerId = DataReader.readInt(dataInput);
        ecosystemId = DataReader.readInt(dataInput);
        attemptId = DataReader.readInt(dataInput);
        score = DataReader.readInt(dataInput);
        Log.consoleln("Parsing RequestConvergeNewAttemptScore"); 
    }

    @Override
    public void process() throws Exception {
        ResponseConvergeNewAttemptScore response = new ResponseConvergeNewAttemptScore();
        response.setStatus(
                ConvergeAttemptDAO.updateConvergeAttemptScore(
                        playerId, ecosystemId, attemptId, score));

        client.add(response);
        Log.consoleln("Processing RequestConvergeNewAttemptScore"); 
    }
}
