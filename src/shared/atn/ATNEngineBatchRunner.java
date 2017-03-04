package shared.atn;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import shared.metadata.Constants;

import shared.simulation.SimulationException;
import shared.simulation.simjob.SimJob;
import shared.simulation.simjob.SimJobManager;

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

    private static int timesteps;
    private static File inputFile;
    private static String outputDir;
    private static int threadCount;

    private static void printUsage() {
        System.out.println("Args: <timesteps> <node config input file> [--use-webservices] [--use-csv] [--output-dir <dir>] [--threads <thread count>]");
    }

    public static void main(String[] args) {

        // Parse command-line arguments
        if (args.length < 2) {
            printUsage();
            return;
        }
        timesteps = Integer.parseInt(args[0]);
        inputFile = new File(args[1]);
        outputDir = null;
        threadCount = 1;
        ATNEngine.useHDF5 = true;
        ATNEngine.stopOnSteadyState = true;

        for (int i = 2; i < args.length; i++) {
            switch (args[i]) {
                case "--use-webservices":
                    System.out.println("Using Web Services instead of ATNEngine");
                    Constants.useAtnEngine = false;
                    Constants.useSimEngine = true;
                    break;
                case "--use-csv":
                    System.out.println("Disabling HDF5 output and saving CSV files");
                    ATNEngine.useHDF5 = false;
                    break;
                case "--output-dir":
                    i++;
                    if (i >= args.length) {
                        System.err.println("Error: no output directory specified");
                        return;
                    }
                    outputDir = args[i];
                    System.out.println("Saving output to " + outputDir);
                    break;
                case "--threads":
                    i++;
                    if (i >= args.length) {
                        System.err.println("No thread count specified");
                        return;
                    }
                    try {
                        threadCount = Integer.parseInt(args[i]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid thread count: " + args[i]);
                        return;
                    }
                    if (threadCount < 1) {
                        System.err.println("Invalid thread count: " + args[i]);
                        return;
                    }
                    break;
                default:
                    System.err.println("Error: unrecognized argument " + args[i]);
                    return;
            }
        }

        if (Constants.useSimEngine && threadCount > 1) {
            System.err.println("Can't use web services with more than 1 thread");
            return;
        }

        // Crashes without this
        ATNEngine.LOAD_SIM_TEST_PARAMS = true;

        if (threadCount == 1) {
            runSingleThreaded();
        } else {
            runMultiThreaded();
        }
    }

    private static void runSingleThreaded() {

        Scanner input;
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Input file " + inputFile + " not found");
            return;
        }

        SimJobManager jobMgr = null;
        if (Constants.useSimEngine) {
            jobMgr = new SimJobManager();
            if (outputDir != null) {
                jobMgr.getSimEngine().setCsvSavePath(outputDir);
            }
        }
        ATNEngine atn = new ATNEngine();
        atn.setRoundBiomass(false);  // Save full-precision output
        if (outputDir != null) {
            atn.setOutputDir(outputDir);
        }

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

    private static void runMultiThreaded() {

        System.err.println("runMultiThreaded: " + threadCount);

        Scanner input;
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Input file " + inputFile + " not found");
            return;
        }

        // Add all node configs from the file along with their sim numbers to a queue
        ConcurrentLinkedQueue<SimulationTask> queue = new ConcurrentLinkedQueue<>();
        int simNumber = 0;
        while (input.hasNextLine()) {
            SimulationTask task = new SimulationTask();
            task.simNumber = simNumber++;
            task.nodeConfig = input.nextLine();
            queue.add(task);
        }

        // Instantiate <threadCount> simulation threads that pull tasks from the queue
        for (int i = 0; i < threadCount; i++) {
            (new SimulationThread(queue, timesteps, outputDir)).start();
        }
    }
}


class SimulationTask {
    public int simNumber;
    public String nodeConfig;
}


/**
 * Contains one ATNEngine object that pulls tasks off the queue and simulates them,
 * saving output in outputDir.
 */
class SimulationThread extends Thread {

    private ConcurrentLinkedQueue<SimulationTask> queue;  // The queue that feeds tasks to all threads
    private int timesteps;
    String outputDir;
    ATNEngine atn;

    public SimulationThread(
            ConcurrentLinkedQueue<SimulationTask> queue,
            int timesteps,
            String outputDir) {

        this.queue = queue;
        this.timesteps = timesteps;
        this.outputDir = outputDir;

        atn = new ATNEngine();
        atn.setRoundBiomass(false);  // Save full-precision output
        if (outputDir != null) {
            atn.setOutputDir(outputDir);
        }
    }

    public void run() {
        SimulationTask task;
        try {
            while ((task = queue.poll()) != null) {

                System.out.println("-----------------------------------------");
                System.out.println("Simulation " + task.simNumber);
                System.out.println("NodeConfig: " + task.nodeConfig);
                SimJob job = new SimJob();
                job.setNode_Config(task.nodeConfig);
                job.setTimesteps(timesteps);

                atn.setOutputFileSequenceNumber(task.simNumber);
                atn.processSimJob(job);
            }

        } catch (SQLException ex) {
            System.err.println ("SQLException: " + ex.getMessage() + ", cause: " + ex.getCause());
        } catch (SimulationException ex) {
            System.err.println("SimulationException: " + ex.getMessage() + ", cause: " + ex.getCause());
        }
    }
}