/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import shared.metadata.Constants;
import lby.net.request.GameRequest;
import cvg.net.response.ResponseConvergeNewAttempt;
import shared.simulation.simjob.SimJobConverge;
import shared.util.DataReader;
import shared.util.Log;
import shared.db.ConvergeAttemptDAO;
import shared.db.PlayerDAO;

/**
 *
 * @author justinacotter
 */
public class RequestConvergeNewAttempt extends GameRequest {

    private int playerId;
    private int ecosystemId;
    private int attemptId;
    private boolean allowHints;
    private int hintId;
    private int timesteps;
    private String config;
    private long startTime;
	private long endTime;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerId = DataReader.readInt(dataInput);
        ecosystemId = DataReader.readInt(dataInput);
        attemptId = DataReader.readInt(dataInput);
        allowHints = DataReader.readBoolean(dataInput);
        hintId = DataReader.readInt(dataInput);
        timesteps = DataReader.readInt(dataInput);
        config = DataReader.readString(dataInput);
        Log.consoleln("Parsing RequestConvergeNewAttempt, config = " + config);  
        startTime = System.nanoTime();
    }

    @Override
    public void process() throws Exception {
        String user_id = PlayerDAO.getPlayerByPlayerId(playerId).getName();
        Log.printf("User '%s' has submitted a simulation request...", user_id);
        Log.consoleln("The timesteps is : " + timesteps);
        //run simulation
        Log.consoleln("no null pointer yet");
        int steps = timesteps+Constants.ADDITIONAL_TIMESTEPS;
        Log.consoleln("this is steps: " + steps);
        SimJobConverge convergeJob = new SimJobConverge(config, steps);
        Log.consoleln("ecosystemID: " + ecosystemId);
        Log.consoleln("allowHints: " + allowHints);
        Log.consoleln("hintId: " + hintId);
        Log.consoleln("config: " + config);
        //create response
        
        ResponseConvergeNewAttempt response = new ResponseConvergeNewAttempt(
                playerId, ecosystemId, allowHints, hintId, config);
        //if sim was successful, save to db and return results to client
        if (convergeJob.getStatus() == Constants.STATUS_SUCCESS) {
            //process sim output
            String csv = convergeJob.getJobCSV();
            response.setCSV(csv);
            //store results to database
            if (!csv.isEmpty()) {
                attemptId = ConvergeAttemptDAO.createAttempt(playerId,
                        ecosystemId,
                        attemptId,
                        allowHints,
                        hintId,
                        config,
                        csv
                );
            }
            response.setAttemptId(attemptId);
        }

        client.add(response);
        endTime = System.nanoTime();
        double difference = (startTime - endTime)/1e6;
        Log.consoleln("Processing RequestConvergeNewAttempt time taken:"+difference); 
    }
}
