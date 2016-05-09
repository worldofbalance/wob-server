package cos.net.request;

// Java Imports

import cos.db.PlayerDAO;
import cos.model.Player;
import cos.util.DataReader;
import cos.util.Log;

import java.io.DataInputStream;
import java.io.IOException;

// Other Imports

public class RequestPlayerSelect extends GameRequest {

    private int player_id;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        player_id = DataReader.readInt(dataInput);
        Log.println("Parse RequestPlayerSelect");
    }

    @Override
    public void process() throws Exception {
        Player player = PlayerDAO.getPlayerByAccount(client.getAccount().getID());

        if (player != null) {
            client.select(player);
        }
    }
}
