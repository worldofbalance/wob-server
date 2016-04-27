package atn;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import metadata.Constants;

import simulation.SimulationException;
import simulation.simjob.SimJob;
import simulation.simjob.SimJobManager;

/**
 * ATNEngineBatchRunner is a command-line program for running multiple
 * simulations using ATNEngine.
 * 
 * The first argument is the number of timesteps to run all simulations.
 *
 * The second argument is the name of a text file in which each line is a node
 * config string defining a simulation, using the syntax accepted by
 * simulation.simjob.SimJob.
 *
 * @author Ben Saylor
 */
public class ATNEngineBatchRunner {

    public static void printUsage() {
        System.out.println("Args: <timesteps> <node config input file> [--use-webservices]");
    }

    public static void main(String[] args) {
        if (args.length == 3 && args[2].equals("--use-webservices")) {
            // Disable ATNEngine and enable Web Services simulation engine
            System.out.println("Using Web Services instead of ATNEngine");
            Constants.useAtnEngine = false;
            Constants.useSimEngine = true;
        } else if (args.length != 2) {
            printUsage();
            return;
        }

        int timesteps = Integer.parseInt(args[0]);
        File inputFile = new File(args[1]);
        Scanner input;
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Input file " + args[1] + " not found");
            return;
        }

        // Crashes without this
        ATNEngine.LOAD_SIM_TEST_PARAMS = true;

        SimJobManager jobMgr = null;
        if (Constants.useSimEngine) {
            jobMgr = new SimJobManager();
        }
        ATNEngine atn = new ATNEngine();

        String nodeConfig;
        SimJob job;
        int simulationsRun = 0;

        long startTime = System.currentTimeMillis();
        try {
            while (input.hasNextLine()) {
                nodeConfig = input.nextLine();
                System.out.println("-----------------------------------------");
                System.out.println("Simulation " + simulationsRun);
                System.out.println("NodeConfig: " + nodeConfig);
                job = new SimJob();
                job.setNode_Config(nodeConfig);
                job.setTimesteps(timesteps);

                if (Constants.useSimEngine) {
                    jobMgr.setSimJob(job);
                    jobMgr.runSimJob();
                } else {
                    atn.processSimJob(job);
                }

                simulationsRun++;
            }
        } catch (SQLException ex) {
            System.err.println ("SQLException: " + ex.getMessage() + 
                    ", cause: " + ex.getCause());
        } catch (SimulationException ex) {
            System.err.println ("SimulationException: " + ex.getMessage() + 
                    ", cause: " + ex.getCause());
        } finally {
            long endTime = System.currentTimeMillis();
            double totalSeconds = (endTime - startTime) / 1000.0;
            double averageSeconds = totalSeconds / simulationsRun;
            System.out.printf("Successfully ran %d simulations\n",
                    simulationsRun);
            System.out.printf("Total time: %.3f seconds\n", totalSeconds);
            System.out.printf("Average time: %.3f seconds\n", averageSeconds);
            input.close();
        }
    }
}
