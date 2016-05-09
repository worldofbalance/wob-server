package cos.net.request;

// Java Imports

import cos.core.world.World;
import cos.db.CSVDAO;
import cos.db.EcosystemDAO;
import cos.model.Ecosystem;
import cos.net.response.ResponseChart;
import cos.util.DataReader;

import java.io.DataInputStream;
import java.io.IOException;

// Other Imports

public class RequestChart extends GameRequest {

    private short type;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        type = DataReader.readShort(dataInput);
    }

    @Override
    public void process() throws Exception {
        World world = client.getPlayer().getWorld();

        if (world != null) {
            Ecosystem ecosystem = EcosystemDAO.getEcosystem(world.getID(), client.getPlayer().getID());

            String csv;
            if (type == 0) {
                csv = CSVDAO.getBiomassCSV(ecosystem.getManipulationID());
            } else {
                csv = CSVDAO.getScoreCSV(ecosystem.getID());
            }

            if (csv != null) {
                ResponseChart response = new ResponseChart();
                response.setType(type);
                response.setCSV(csv);
                client.add(response);
            }
        }
    }
}
