/*
 * WaitStatus protocol
 * Asks server if anyone has invited us to a game yet
 * if invited, client should then connect to the appropriate server
 * response parameters:
 * status - 1 if invited, 0 if not invited, -1 if you're not currently on a wait list
 * id - game id -- so minigame server can place you with the right partner
 */

package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseWaitStatus extends GameResponse {

    int status;
    int id;
    
    public ResponseWaitStatus() {
        response_id = NetworkCode.WAITFORGAME;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        packet.addInt32(status);
        packet.addInt32(id);

        return packet.getBytes();
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
