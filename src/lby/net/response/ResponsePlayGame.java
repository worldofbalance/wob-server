// sends a request to play a game
// server checks if the player has enough currency,
// then either deducts that currency and sends a success message
// or if they don't have enough, sends a fail message

// request parameters:
// game_id - the game to be played
//		0 - converge
//		1 - don't eat me
//		2 - clash of species
//		3 - running rhino
//		4 - cards of wild
//
// response parameters:
// status - the status of your request
//		0 - failed - not enough currency
//		1 - success
// creditDiff - if status==1, number of credits that were subtracted (always positive)

package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponsePlayGame extends GameResponse {

    private short status;
    private int creditDiff;

    public ResponsePlayGame() {
        response_id = NetworkCode.PLAY_GAME;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        if (status == 1) {
            packet.addInt32(creditDiff);
        }

        return packet.getBytes();
    }

    public void setStatus(int status) {
        this.status = (short)status;
    }

    public void setCreditDiff(int creditDiff) {
        this.creditDiff = creditDiff;
    }
}
