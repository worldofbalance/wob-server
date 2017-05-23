package cvg;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cvg.net.response.ResponseConvergeEcosystems;
import shared.metadata.Constants;
import shared.simulation.simjob.SimJobConverge;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A program to generate the converge-ecosystems file for the Unity game client.
 * The ecosystem data is read from the supplied JSON file.
 * The JSON file must contain an array of objects,
 * each of which contains each of the fields of ConvergeEcosystem except the CSV fields.
 *
 * @author Ben Saylor
 */
public class ConvergeEcosystemsFileGenerator {

    private List<ConvergeEcosystem> ecosystems;

    private ConvergeEcosystemsFileGenerator() {
        ecosystems = new ArrayList<>();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        ConvergeEcosystemsFileGenerator generator = new ConvergeEcosystemsFileGenerator();
        generator.generateConvergeEcosystemsFile(args[0], args[1]);
    }

    private static void printUsage() {
        System.err.println("Required arguments: <JSON input file> <output file>");
    }

    private void generateConvergeEcosystemsFile(String inputFilename, String outputFilename) {
        try {
            loadEcosystemsFromJsonFile(inputFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        regenerateBiomassData();
        writeEcosystemsToFile(outputFilename);
    }

    private void loadEcosystemsFromJsonFile(String inputFilename) throws FileNotFoundException {
        Reader reader = new FileReader(inputFilename);
        Gson gson = new Gson();
        Type ecosystemsListType = new TypeToken<ArrayList<ConvergeEcosystem>>(){}.getType();
        ecosystems = gson.fromJson(reader, ecosystemsListType);
    }

    private void regenerateBiomassData() {
        for (ConvergeEcosystem ecosystem : ecosystems) {
            int timestepsToRun = ecosystem.getTimesteps() + Constants.ADDITIONAL_TIMESTEPS;
            ecosystem.setCsvDefault(simulateToCSV(ecosystem.getConfigDefault(), timestepsToRun));
            ecosystem.setCsvTarget(simulateToCSV(ecosystem.getConfigTarget(), timestepsToRun));
        }
    }

    private String simulateToCSV(String nodeConfig, int timesteps) {
        SimJobConverge job = new SimJobConverge(nodeConfig, timesteps);
        return job.getJobCSV();
    }

    private void writeEcosystemsToFile(String filename) {
        ResponseConvergeEcosystems response = new ResponseConvergeEcosystems();
        response.setConvergeEcosystems(ecosystems);

        File file = new File(filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(response.getBytes());
            fileOutputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
