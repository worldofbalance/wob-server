/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;
import java.util.HashMap;

/**
 * ResponseConvergeHintCount returns # of hints in database
 * @author justinacotter
 */
public class ResponseConvergeGetNames extends GameResponse {

    private HashMap<Integer, String> playerNames = new HashMap<Integer, String>();
    
    public ResponseConvergeGetNames() {
        response_id = NetworkCode.MC_GET_NAMES;
    }

    public void setPlayers (HashMap<Integer, String> playerNames) {
        this.playerNames = playerNames;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        for (HashMap.Entry<Integer, String> entry : playerNames.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            packet.addInt32(key);
            packet.addString(value);
        }
        return packet.getBytes();
    }
}
