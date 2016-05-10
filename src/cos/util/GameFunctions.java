package cos.util;

// Java Imports

import cos.core.ServerResources;
import cos.model.SpeciesType;

import java.util.HashMap;
import java.util.Map;

// Other Imports

/**
 *
 * @author Gary
 */
public class GameFunctions {

    private GameFunctions() {
    }

    public static Map<Integer, Integer> convertSpeciesToNodes(Map<Integer, Integer> speciesBiomassList) {
        Map<Integer, Integer> nodeBiomassList = new HashMap<Integer, Integer>();

        for (Map.Entry<Integer, Integer> entry : speciesBiomassList.entrySet()) {
            int species_id = entry.getKey(), biomass = entry.getValue();

            SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

            if (speciesType != null) {
                for (Map.Entry<Integer, Float> nodeDistribution : speciesType.getNodeDistribution().entrySet()) {
                    int node_id = nodeDistribution.getKey();
                    float distribution = nodeDistribution.getValue();

                    if (nodeBiomassList.containsKey(node_id)) {
                        nodeBiomassList.put(node_id, nodeBiomassList.get(node_id) + Math.round(biomass * distribution));
                    } else {
                        nodeBiomassList.put(node_id, Math.round(biomass * distribution));
                    }
                }
            }
        }

        return nodeBiomassList;
    }
}
