/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import cvg.ConvergeAttempt;

import java.util.List;

import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;
import shared.util.Log;

/**
 * ResponseConvergePriorAttemptCount returns # of prior attempts for a specific
 player-ecosystem, returning most recent ecosystem if one is not specified
 * @author justinacotter
 */
public class ResponseConvergePriorAttemptCount extends GameResponse {

    private int playerId = Constants.ID_NOT_SET;
    private int ecosystemId = Constants.ID_NOT_SET;
    private int count = 0;
    
    public ResponseConvergePriorAttemptCount(int playerId, int ecosystemId) {
        response_id = NetworkCode.CONVERGE_PRIOR_ATTEMPT_COUNT;
        this.playerId = playerId;
        this.ecosystemId = ecosystemId;
    }

    public void setConvergePriorAttemptInfo(int[] ecoInfo) {
        ecosystemId = ecoInfo[0];
        count = ecoInfo[1];
    }
    
    public void setCount (int count) {
        this.count = count;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);

        packet.addInt32(playerId);
        packet.addInt32(ecosystemId);
        packet.addInt32(count);
        Log.consoleln("--------ResponseConvergePriorAttemptCount" + " playerId=" + playerId + " ecosystemId " + ecosystemId + " count " + count);
        return packet.getBytes();
    }
}
