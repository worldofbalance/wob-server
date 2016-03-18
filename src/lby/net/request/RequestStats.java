package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.db.StatsDAO;
import lby.net.response.ResponseStats;
import shared.util.DataReader;

public class RequestStats extends GameRequest {

    private short month_start;
    private short month_end;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        month_start = DataReader.readShort(dataInput);
        month_end = DataReader.readShort(dataInput);
    }

    @Override
    public void process() throws Exception {
        if (client.getPlayer().getWorld() != null) {
            int player_id = client.getAccount().getID();
            int eco_id = client.getPlayer().getEcosystem().getID();

            ResponseStats response = new ResponseStats();
            response.setStats(StatsDAO.getStats(month_start, month_end, player_id, eco_id));
            client.add(response);
        }
    }
}
