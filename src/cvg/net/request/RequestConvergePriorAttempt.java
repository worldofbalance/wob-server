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
import cvg.net.response.ResponseConvergePriorAttempt;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author justinacotter
 */
public class RequestConvergePriorAttempt extends GameRequest {
    
    private int playerId;
    private int ecosystemId;
    private int attemptIdOffset;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerId = DataReader.readInt(dataInput);
        ecosystemId = DataReader.readInt(dataInput);
        attemptIdOffset = DataReader.readInt(dataInput);
        Log.consoleln("Parsing RequestConvergePriorAttempt " + " playerId=" + playerId + " ecosystemId=" + ecosystemId + " attemptIdOffset="+ attemptIdOffset);
    }

    @Override
    public void process() throws Exception {
        ResponseConvergePriorAttempt response = new ResponseConvergePriorAttempt(
        playerId, ecosystemId);
        response.setConvergePriorAttempt(
                    ConvergeAttemptDAO.getNextConvergeAttempt(
                    playerId, ecosystemId, attemptIdOffset));
        client.add(response);
        Log.consoleln("Processed RequestConvergePriorAttempt");
    }
}
