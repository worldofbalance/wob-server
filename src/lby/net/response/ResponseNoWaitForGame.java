/*
 * NoWaitForGame protocol
 * Lets the server know that the client no longer wants to play a mini game
 * response parameters:
 * status - 1 for succeed 0 for fail
 */
package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseNoWaitForGame extends GameResponse {

    int status;

    public ResponseNoWaitForGame() {
        response_id = NetworkCode.NOWAITFORGAME;
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
