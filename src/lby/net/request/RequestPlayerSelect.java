package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.db.PlayerDAO;
import shared.model.Player;
import shared.util.DataReader;

public class RequestPlayerSelect extends GameRequest {

    private int player_id;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        player_id = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        Player player = PlayerDAO.getPlayerByAccount(client.getAccount().getID());

        if (player != null) {
            client.select(player);
        }
    }
}
