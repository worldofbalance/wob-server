/*
 * StartGame protocol
 * starts a game with another player
 * request parameters:
 * pid - id of player to start game with
 * response parameters:
 * status - status of request -- 0 if invite failed, 1 if game started successfully
 * id - game id -- so minigame server can place you with the right partner
 */

package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseStartGame extends GameResponse {

    int status;
    int id;
    
    public ResponseStartGame() {
        response_id = NetworkCode.STARTGAME;
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
