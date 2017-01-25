/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;
import java.util.HashMap;
import lby.net.response.GameResponse;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author ivanG
 */
public class ResponseConvergeSpecifyParams extends GameResponse { 
    private short status  = 1;//set as errors until request changes it
    
    public ResponseConvergeSpecifyParams(){
        response_id = NetworkCode.MC_HOST_CONFIG;
    }
    public void setStatus (short status) {
        this.status = status;
    }
    
    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        return packet.getBytes();
    }
}
