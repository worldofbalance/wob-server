/* By Justina Cotter

 WoB ATN implements the ATN model locally using Bulirsch-Stroer integration
 and outputs original WebServices data and locally generated data at the ecosystem  
 and species level to evaluate the results.

 Relies on WoB_Server source code for objects that store simulation timesteps and
 species information.
 */
package shared.atn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


//WOB_Server imports
import shared.db.SimJobDAO;
import shared.metadata.Constants;
import shared.simulation.simjob.SimJob;
import shared.model.SpeciesType;
import shared.core.GameServer;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import shared.simulation.simjob.EcosystemTimesteps;
import shared.simulation.simjob.NodeTimesteps;
import shared.simulation.simjob.SimJobSZT;

/**
 *
 * @author Justina
 */
public class ATN {

    private static UserInput userInput;
    public static Properties propertiesConfig;
    private PrintStream psATN = null;
    /*
     The first two timesteps values produced by WebServices do not
     fit the local solution well.  Therefore, these values have been excluded
     for comparison purposes
     */
    private int initTimeIdx = 2;
    private double maxBSIErr = 1.0E-3;
    private double timeIntvl = 0.1;
    private static final int biomassScale = 1000;
    private static int equationSet = 0;  //0=ATN; 1=ODE 1; 2=ODE 2
    private double initTime = 0.0;
    private double initVal = 0.0;  //for non-ATN test
	private String atnManipulationId;

    public ATN() {
        //load properties file containing ATN model parameter values
        propertiesConfig = new Properties();
        try {
            propertiesConfig.load(new FileInputStream("/src/shared/atn/SimJobConfig.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ATN.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ATN.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        /* 
         Read in non-std variables used for running sim jobs
         */
        GameServer.getInstance();
        SpeciesType.loadSimTestNodeParams(Constants.ECOSYSTEM_TYPE);
        SpeciesType.loadSimTestLinkParams(Constants.ECOSYSTEM_TYPE);
    }

    //loop through current job/results, assembling dataset
    private void genSpeciesDataset(SimJob job,
            EcosystemTimesteps ecosysTimesteps,
            Map<Integer, NodeRelationships> ecosysRelationships
    ) {
        //calc information relevant to entire ecosystem
        int speciesCnt = ecosysTimesteps.getNodeList().size();
        int timesteps = ecosysTimesteps.getTimesteps();

        //read in link parameters; this was explicitly configured to allow
        //manipulation of link parameter values, but no manipulation is 
        //performed in this version
        LinkParams lPs = new LinkParams(propertiesConfig);

        //loop through node values and assemble summary data
        int[] speciesID = new int[speciesCnt];
        SimJobSZT[] sztArray = new SimJobSZT[speciesCnt];
        double[][] webServicesData = new double[speciesCnt][timesteps];
        int spNum = 0;
        for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
            SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
            sztArray[spNum] = sjSzt;
            speciesID[spNum] = sjSzt.getNodeIndex();
            //copy nodetimestep data to local array for easier access
            System.arraycopy(
                    nodeTimesteps.getBiomassArray(),
                    0,
                    webServicesData[spNum],
                    0,
                    timesteps
            );

            spNum++;
        }

        //define objects to track species' contributions
        double[][][] contribs = new double[timesteps][speciesCnt][speciesCnt];
        double[][] calcBiomass = new double[timesteps][speciesCnt];
        double[][] contribsT; //current timestep

        //note: WebServices ATN Model uses B0 with default = 0.5.  This presumes
        //that biomasses are small, i.e. < 1.0.  Division by biomassScale
        //here is consistent with usage in WoB_Server.SimulationEngine to 
        //normalize biomasses.
        //need to store bm as it varies over time through integration; 
        //start with initial bm for each species
        double[] currBiomass = new double[speciesCnt];
        double[] hjrCurrBiomass = new double[speciesCnt];
        for (int i = 0; i < speciesCnt; i++) {
            //manually set biomass vals for excluded initial timesteps; this
            //includes the first value to be used as input 
            for (int t = 0; t <= initTimeIdx; t++) {
                calcBiomass[t][i] = webServicesData[i][t] / biomassScale;
            }
            //set first value to be used as input
            currBiomass[i] = calcBiomass[initTimeIdx][i];
            //currBiomass[i] = calcBiomass[0][i]; //HJR
        }

        //create integration object
        boolean isTest = false;
        BulirschStoerIntegration bsi = new BulirschStoerIntegration(
                timeIntvl,
                speciesID,
                sztArray,
                ecosysRelationships,
                lPs,
                maxBSIErr,
                equationSet
        );

        //calculate delta-biomass and biomass "contributions" from each related
        //species
        for (int t = initTimeIdx + 1; t < timesteps; t++) {
            boolean success = bsi.performIntegration(time(initTime, t), currBiomass);
            if (!success) {
                System.out.printf("Integration failed to converge, t = %d\n", t);
                System.out.print(bsi.extrapArrayToString(biomassScale));
                break;
            }
            currBiomass = bsi.getYNew();
            System.arraycopy(currBiomass, 0, calcBiomass[t], 0, speciesCnt);

            contribsT = bsi.getContribs();
            for (int i = 0; i < speciesCnt; i++) {
                System.arraycopy(contribsT[i], 0, contribs[t - 1][i], 0, speciesCnt);
            }

        }  //timestep loop

        //output data
        //A. print header
        psATN.printf("timesteps");
        for (int i = 0; i < timesteps; i++) {
            psATN.printf(",%d", i);
        }
        psATN.println();

        //loop through each species
        for (int i = 0; i < speciesCnt; i++) {
            psATN.printf("i.%d.sim", speciesID[i]);

            //B. print WebServices simulation data for species
            for (int t = 0; t < timesteps; t++) {
                psATN.printf(",%9.0f", webServicesData[i][t]);
            }
            psATN.println();

            //C. print combined biomass contributions (i.e. locally calculated biomass)
            //for current species.
            psATN.printf("i.%d.calc", speciesID[i]);
            for (int t = 0; t < timesteps; t++) {
                psATN.printf(",%9.0f", calcBiomass[t][i] * biomassScale);
            }
            psATN.println();

            //D. print individual biomass contributions from other species
            for (int j = 0; j < speciesCnt; j++) {
                psATN.printf("i.%d.j.%d.", speciesID[i], speciesID[j]);
                for (int t = 0; t < timesteps; t++) {
                    psATN.printf(",%9.0f", contribs[t][i][j] * biomassScale);
                }
                psATN.println();
            }
        }

    }

    /*
    Test run for integration using problem with known solution:
    equationSet 1:
    y' = (-y sin x + 2 tan x) y
    y(pi/6) = 2 / sqrt(3)
    exact solution: y(x) = 1 / cos x
    
    equationSet 2:
    y' = -200x * y^2
    y(0) = 1
    y(x) = 1 / (1 + 100x^2)
    
    */
    private void genODETestDataset() {
        int timesteps = 20;
        //setup values depending on ODE selected
        switch (equationSet) {
            case 1:
                initTime = Math.PI / 6.0;
                initVal = 2.0 / Math.sqrt(3.0);
                timeIntvl = 0.2;
                break;
            case 2:
                initTime = 0.0;
                initVal = 1.0;
                timeIntvl = 0.02;
                break;
            default:
                break;
        }
        
        double[][] bsiSoln = new double[timesteps][1];
        bsiSoln[0][0] = initVal;

        initTimeIdx = 0;
        maxBSIErr = 1.0E-3;

        initOutputStreams();

        //create integration object
        BulirschStoerIntegration bsi = new BulirschStoerIntegration(
                timeIntvl,
                new int[1],  //needed to calc number of elements
                null,
                null,
                null,
                maxBSIErr,
                equationSet
        );

        //calculate integration solution
        double[] currVal = new double[1];
        currVal[0] = bsiSoln[0][0];
        for (int t = initTimeIdx + 1; t < timesteps; t++) {
            boolean success = bsi.performIntegration(time(initTime, t - 1), currVal);
            if (!success) {
                System.out.printf("Integration failed to converge, t = %d\n", t);
                System.out.print(bsi.extrapArrayToString(1));
                break;
            }
            currVal[0] = bsi.getYNew()[0];
            bsiSoln[t][0] = currVal[0];
        }  //timestep loop

        //output data
        //A. print header
        psATN.printf("timesteps");
        for (int t = 0; t < timesteps; t++) {
            psATN.printf(",% 9.2f", time(initTime, t));
        }
        psATN.println();

        //B. print true solution: y(x) = 1 / cos x
        for (int t = 0; t < timesteps; t++) {
            //psATN.printf(",%9.2f", 1.0 / Math.cos (time(initTime, t))); //needs t++
            psATN.printf(",% 9.2f", 1.0 / 
                    (1.0 + 100.0 * Math.pow (time(initTime, t), 2.0))
            );
            
        }
        psATN.println();

        //C. print bsi solution
        for (int t = 0; t < timesteps; t++) {
            psATN.printf(",% 9.2f", bsiSoln[t][0]);
        }
        psATN.println();

    }
    
    private double time (double initTime, int t) {
        return initTime + (double) t * timeIntvl;
    }

    //loop through jobs/results, assembling dataset
    private void processJobs() {
        List<Integer> simJobs = null;
        SimJob job = null;

        System.out.println("Reading job IDs and initializing output files...");
        try {
            //inclusion is dictated by "include" field in table
            //typically, I would only include one or two jobs
            simJobs = SimJobDAO.getJobIdsToInclude("");

        } catch (SQLException ex) {
            Logger.getLogger(ATN.class
                    .getName()).log(Level.SEVERE,
                            null, ex);
        }

        initOutputStreams();

        //loop through all identified jobs; load and process
        for (Integer jobId : simJobs) {
            System.out.printf("Processing job ID %d\n", jobId);
            try {
                job = SimJobDAO.loadCompletedJob(jobId);

            } catch (SQLException ex) {
                Logger.getLogger(ATN.class
                        .getName()).
                        log(Level.SEVERE, null, ex);
            }
            if (job == null) {
                continue;
            }

            //init ecosystem data sets
            EcosystemTimesteps ecosysTimesteps = new EcosystemTimesteps();
            Map<Integer, NodeRelationships> ecosysRelationships = new HashMap<>();

            //extract timestep data from CSV
            Functions.extractCSVDataRelns(job.getCsv(), ecosysTimesteps, ecosysRelationships);
            if (ecosysTimesteps.getTimestepMap().isEmpty()) {
                continue;
            }

            long start = System.nanoTime();

            //generate data for current job
            genSpeciesDataset(job, ecosysTimesteps, ecosysRelationships);

            System.out.printf("\nTime... %d seconds\n\n", (System.nanoTime() - start)
                    / (long) Math.pow(10, 9));

            System.out.printf("Completed Processing job ID %d\n", jobId);
        }   //job loop

    }

    private void initOutputStreams() {
        System.out.println("Ecosystem output will be written to:");
        System.out.println("Network output will be written to:");
        psATN = Functions.getPrintStream("ATN", Constants.ATN_CSV_SAVE_PATH);
    }
    

    public static void main(String args[]) throws FileNotFoundException {

        //get output directory
        JFrame parent = null;
        userInput = new UserInput(parent);
        userInput.destDir = System.getProperty("user.dir");
        userInput.setVisible(true);
        if (userInput.destDir.isEmpty()) {
            System.out.println("Destination directory not specified or user "
                    + "selected cancel.  Aborting run.");
            System.exit(0);
        }

        ATN atn = new ATN();

        if (equationSet == 0) {
            //process simulation jobs
            atn.processJobs();
        } else {
            //test ODE w/o ATN model
            atn.genODETestDataset();
        }

        System.out.println("Processing complete.");

    }

	public Properties getPropertiesConfig() {
		return this.propertiesConfig;
	}  
}
