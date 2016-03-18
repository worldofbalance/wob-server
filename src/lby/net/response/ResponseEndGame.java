// tells the server that we've finished playing a game (and won),
// so server can update credits

// request parameters:
// game_id - the game to be played
//		0 - converge
//		1 - don't eat me
//		2 - clash of species
//		3 - running rhino
//		4 - cards of wild
// credits - if don't eat me, # of credits won
//
// response parameters:
// creditDiff - number of credits that were added

package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseEndGame extends GameResponse {

    private int creditDiff;

    public ResponseEndGame() {
        response_id = NetworkCode.END_GAME;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        packet.addInt32(creditDiff);

        return packet.getBytes();
    }

    public void setCreditDiff(int creditDiff) {
        this.creditDiff = creditDiff;
    }
}
