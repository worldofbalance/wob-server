/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import cvg.ATNConverter;
import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 *
 * @author justinacotter
 */
public class ResponseConvergeNewAttempt extends GameResponse {

    private int playerId;
    private int ecosystemId;
    private int attemptId = Constants.ID_NOT_SET;
    private boolean allowHints;
    private int hintId;
    private String config = "";
    private String csv = "";

    public ResponseConvergeNewAttempt(
            int playerId, 
            int ecosystemId, 
            boolean allowHints,
            int hintId,
            String config
    ) {
        response_id = NetworkCode.CONVERGE_NEW_ATTEMPT;
        this.playerId = playerId;
        this.ecosystemId = ecosystemId;
        this.allowHints = allowHints;
        this.hintId = hintId;
        this.config = config;
    }

    public void setAttemptId (int attemptId) {
        this.attemptId = attemptId;
    }
    
    public void setCSV (String csv) {
        this.csv = ATNConverter.ConvertCSV(csv, 200);
    }
    
    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);

        packet.addInt32(playerId);
        packet.addInt32(ecosystemId);
        packet.addInt32(attemptId);
        packet.addBoolean(allowHints);
        packet.addInt32(hintId);
        packet.addString(config);
        packet.addString(csv);
        return packet.getBytes();
    }
}
