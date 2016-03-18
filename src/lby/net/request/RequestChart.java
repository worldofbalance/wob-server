package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports
import shared.db.CSVDAO;
import lby.core.world.World;
import shared.db.EcosystemDAO;
import shared.metadata.Constants;
import shared.model.Ecosystem;
import lby.net.response.ResponseChart;
import shared.util.DataReader;

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
            	if(Constants.useSimEngine){
            		csv = CSVDAO.getBiomassCSV(ecosystem.getManipulationID());
            	}else{
            		csv = CSVDAO.getBiomassCSV(ecosystem.getATNManipulationID());
            	}
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
