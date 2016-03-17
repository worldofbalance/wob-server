/*
 * WaitForGame protocol
 * Lets the server know that the client wants to play a mini game
 * request parameters:
 * gameType - which game to wait for
 * response parameters:
 * status - 1 for succeed 0 for fail
 */

package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseWaitForGame extends GameResponse {

    int status;

    public ResponseWaitForGame() {
        response_id = NetworkCode.WAITFORGAME;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(status);

        return packet.getBytes();
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
