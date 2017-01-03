package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import shared.core.EcosystemController;
import lby.core.world.World;
import shared.db.ShopDAO;
import shared.model.Ecosystem;
import shared.model.ShopItem;
import lby.net.response.ResponseSpeciesAction;
import shared.model.Species;
import shared.util.DataReader;
import shared.util.Log;

public class RequestSpeciesAction extends GameRequest {

    private short action;
    private short type;
    private Map<Integer, Integer> speciesList;
    private Map<Integer, Species> speciesListFull;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        action = DataReader.readShort(dataInput);

        if (action == 0) {
            type = DataReader.readShort(dataInput);
        } else if (action == 1) {
            short size = DataReader.readShort(dataInput);
            speciesList = new HashMap<Integer, Integer>();

            int species_id, biomass;

            for (int i = 0; i < size; i++) {
                species_id = DataReader.readInt(dataInput);
                biomass = DataReader.readInt(dataInput);

                speciesList.put(species_id, biomass);
            }
        }
    }

    @Override
    public void process() throws Exception {
        short status = 0;

        ResponseSpeciesAction response = new ResponseSpeciesAction();
        response.setAction(action);
        response.setStatus(status);

        if (action == 0) {
            response.setType(type);

            if (type == 0) { // Get Default Species
                speciesList = new HashMap<Integer, Integer>();
                speciesList.put(13, 5000);
                speciesList.put(20, 5000);
                speciesList.put(31, 5000);

                String selectionList = "";

                int index = 0;
                for (Entry<Integer, Integer> entry : speciesList.entrySet()) {
                    selectionList += entry.getKey() + ":" + entry.getValue();

                    if (index++ < speciesList.size() - 1) {
                        selectionList += ",";
                    }
                }

                response.setSelectionList(selectionList);
            } else if (type == 1) { // Get Every Species
                String[] settings = new String[]{"30000", "2,10", "500,1000,2500"};

                List<ShopItem> shopList = ShopDAO.getItems("level:0,99");
                String selectionList = "";

                int index = 0;
                for (ShopItem item : shopList) {
                    selectionList += item.getID();

                    if (index++ < shopList.size() - 1) {
                        selectionList += ",";
                    }
                }

                response.setSettings(settings);
                response.setSelectionList(selectionList);
            }

            client.add(response);
        } else if (action == 1) { // Create Ecosystem Using Species
            Ecosystem ecosystem = client.getPlayer().getEcosystem();

            if (ecosystem != null) {
                EcosystemController.createEcosystem(ecosystem, speciesList);

                String selectionList = "";

                int index = 0;
                for (Entry<Integer, Integer> entry : speciesList.entrySet()) {
                    selectionList += entry.getKey() + ":" + entry.getValue();

                    if (index++ < speciesList.size() - 1) {
                        selectionList += ",";
                    }
                }

                response.setSelectionList(selectionList);
                client.add(response);
            }
        } else if (action == 2) { // Return species_id, biomass pairs for Ecosystem
            speciesListFull = EcosystemController.getInstance().getEcosystem().getSpeciesList();
            int count = speciesListFull.size();
            response.setCount(count);
            // for (String key : selects.keySet())
            for (Integer key : speciesListFull.keySet()) {
                Species species = speciesListFull.get(key);
                int species_id = species.getID();
                int biomass = species.getTotalBiomass();
                response.addSpeciesList(species_id, biomass);
            }
            client.add(response);
        }
    }
}
