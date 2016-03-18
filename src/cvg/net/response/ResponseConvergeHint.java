/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import cvg.ConvergeHint;
import shared.metadata.Constants;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 * ResponseConvergeHint returns all hints in database table
 * @author justinacotter
 */
public class ResponseConvergeHint extends GameResponse {

    private int hintId = Constants.ID_NOT_SET;
    private String text = "";
    
    ConvergeHint hint = null;

    public ResponseConvergeHint() {
        response_id = NetworkCode.CONVERGE_HINT;
    }

    public void setConvergeHint(ConvergeHint hint) {
        this.hint = hint;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);

        //if hint was found, send 
        if (hint != null) {
            packet.addInt32(hint.getHintId());
            packet.addString(hint.getText());
        } else {
            packet.addInt32(hintId);
            packet.addString(text);
        }

        return packet.getBytes();
    }
}
