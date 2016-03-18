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

package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.util.DataReader;
import shared.db.PlayerDAO;
import lby.net.response.ResponsePlayGame;

public class RequestPlayGame extends GameRequest {

    private short game_id;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        game_id = DataReader.readShort(dataInput);
    }

    @Override
    public void process() throws Exception {
        short status = 0;
        int creditDiff = 0;
        int oldCredits = client.getPlayer().getCredits();
        
        switch (game_id) {
            case 0: //converge - 0 credits
                status = 1;
                creditDiff = 0;
                break;
            
            default: //all other games cost 10 credits
                if (oldCredits >= 10) {
                    creditDiff = 10;
                    status = 1;
                    PlayerDAO.updateCredits(client.getPlayer().getID(), oldCredits-creditDiff);
                    client.getPlayer().setCredits(oldCredits-creditDiff);
                }
                break;
        }
        
        ResponsePlayGame response = new ResponsePlayGame();
        response.setStatus(status);
        response.setCreditDiff(creditDiff);
        client.add(response);
    }
}
