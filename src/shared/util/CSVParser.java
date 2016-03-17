package shared.util;

// Java Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Other Imports
import shared.core.ServerResources;
import shared.model.SpeciesType;

/**
 * The CSVParser class contain methods used to process and format any given
 * biomass data in CSV form.
 */
public class CSVParser {

    /**
     * Removes Node IDs found within biomass CSV output.
     *
     * @param csv contains data in CSV format
     * @return formatted CSV string
     */
    public static String removeNodesFromCSV(String csv) {
    	System.out.println("------removeNodesFromCSV csv " +csv);
        String[] csvList = csv.split("\n");
        
        for (int i = 1; i < csvList.length; i++) {
            String[] nextLine = csvList[i].split(",");
            
            String name = nextLine[0];
            int node_id = Integer.valueOf(name.substring(name.lastIndexOf("[") + 1, name.lastIndexOf("]")));

            if(nextLine.length == 1){
	            SpeciesType species = ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(node_id);
	            csvList[i] = "\"" + species.getName() + "\"," + nextLine[1];
            }
        }

        return createCSV(csvList);
    }

    /**
     * Converts biomass data in CSV format to display the number of species
     * instead.
     *
     * @param csv contains data in CSV format
     * @return formatted CSV string
     */
    public static String convertBiomassCSVtoSpeciesCSV(String csv) {
        String[] csvList = csv.split("\n");

        for (int i = 1; i < csvList.length; i++) {
            String[] nextLine = csvList[i].split("\",");

            String name = nextLine[0];
            int node_id = Integer.valueOf(name.substring(name.lastIndexOf("[") + 1, name.lastIndexOf("]")));

            SpeciesType species = ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(node_id);
            csvList[i] = "\"" + species.getName() + "\"";

            for (String value : nextLine[1].split(",")) {
                csvList[i] += ",";

                if (!value.isEmpty()) {
                    double biomass = Double.valueOf(value);
                    String amount = String.valueOf(biomass < 1 ? 0 : (int) Math.round(biomass / species.getBiomass()));

                    csvList[i] += amount;
                }
            }
        }

        return createCSV(csvList);
    }
    
    public static String createCSV(String[] csvList) {
        String csv = "";

        for (int i = 0; i < csvList.length; i++) {
            csv += csvList[i];

            if (i < csvList.length - 1) {
                csv += "\n";
            }
        }

        return csv;
    }

    public static String createCSV(List<List<String>> csvList) {
        String csv = "";

        for (int i = 0; i < csvList.size(); i++) {
            List<String> row = csvList.get(i);

            for (int j = 0; j < row.size(); j++) {
                csv += row.get(j);

                if (j < row.size() - 1) {
                    csv += ",";
                }
            }

            if (i < csvList.size() - 1) {
                csv += "\n";
            }
        }

        return csv;
    }
    
    public static List<List<String>> convertCSVtoArrayList(String csv) {
        List<List<String>> csvList = new ArrayList<List<String>>();
        
        if (csv != null) {
            String[] tempList = csv.split(("\n"));

            for (String row : tempList) {
                csvList.add(new ArrayList<String>(Arrays.asList(row.split(","))));
            }
        }
        return csvList;
    }
}
