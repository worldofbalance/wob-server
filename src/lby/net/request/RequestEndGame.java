// tells the server that we've finished playing a game (and won),
// so server can update credits

// request parameters:
// game_id - the game to be played
//		0 - converge
//		1 - don't eat me
//		2 - clash of species
//		3 - running rhino
//		4 - cards of wild
//              5 - Multiplayer Convergence
// 
// credits - if don't eat me, # of credits won
//
// response parameters:
// creditDiff - number of credits that were added

package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.util.DataReader;
import shared.db.PlayerDAO;
import lby.net.response.ResponseEndGame;

public class RequestEndGame extends GameRequest {

    private short game_id;
    private int credits = 0;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        game_id = DataReader.readShort(dataInput);
        
        if (game_id == 1) {
            credits = DataReader.readInt(dataInput);
        }
        
        if (game_id == 5) {
            credits = DataReader.readInt(dataInput);
        }
    }

    @Override
    public void process() throws Exception {
        int creditDiff = 0;
        int oldCredits = client.getPlayer().getCredits();
        
        switch (game_id) {
            case 1: //don't eat me - use credits variable
                creditDiff = credits;
                break;
            
            case 5: //Multiplayer Convergence - sends final credits
                creditDiff = credits - oldCredits;   // This is credit difference
                break;        
                
            default: //all other games give 20 credits
                creditDiff = 20;
                break;
        }
        
        PlayerDAO.updateCredits(client.getPlayer().getID(), oldCredits+creditDiff);
        client.getPlayer().setCredits(oldCredits+creditDiff);
        
        ResponseEndGame response = new ResponseEndGame();
        response.setCreditDiff(creditDiff);
        client.add(response);
    }
}
