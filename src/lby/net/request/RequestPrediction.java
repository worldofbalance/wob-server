package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import shared.db.EcoSpeciesDAO;

// Other Imports
import shared.model.Ecosystem;
import shared.model.Species;
import shared.util.Log;

public class RequestPrediction extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process()  throws Exception {
        Ecosystem ecosystem = client.getPlayer().getEcosystem();

        if (ecosystem != null) {
            // This is required to have the latest biomass values 
            List<Species> speciesList = EcoSpeciesDAO.getSpecies(ecosystem.getID());
            for (Species species : speciesList){
                ecosystem.setSpecies(species);                
            }

            ecosystem.getGameEngine().forceSimulation();
        }
    }
}
