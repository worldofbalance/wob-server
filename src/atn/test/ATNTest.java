package atn.test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.response.ResponseSpeciesCreate;

import org.jfree.ui.RefineryUtilities;

import metadata.Constants;
import model.Account;
import model.Ecosystem;
import model.Player;
import model.Species;
import model.SpeciesGroup;
import model.SpeciesType;
import simulation.SimulationException;
import simulation.extrapolation.GameEngineTest;
import simulation.simjob.EcosystemTimesteps;
import simulation.simjob.NodeTimesteps;
import simulation.simjob.SimJob;
import simulation.simjob.SimJobConverge;
import simulation.simjob.SimJobManager;
import simulation.simjob.SimJobSZT;
import util.CSVParser;
import util.ConfigureException;
import util.ExpTable;
import util.GameTimer;
import util.Log;
import util.NetworkFunctions;
import util.Vector3;
import atn.ATN;
import atn.ATNEngine;
import atn.Functions;
import core.EcosystemController;
import core.GameEngine;
import core.ServerResources;
import core.lobby.EcosystemLobby;
import core.lobby.Lobby;
import core.lobby.LobbyController;
import core.world.World;
import core.world.WorldController;
import core.world.Zone;
import db.AccountDAO;
import db.CSVDAO;
import db.EcoSpeciesDAO;
import db.EcosystemDAO;
import db.PlayerDAO;
import db.ScoreDAO;
import db.SimJobDAO;
import db.StatsDAO;
import db.UserLogDAO;
import db.world.WorldZoneDAO;
/*
 * To run the SimJobManager class set the 'config' and 'timesteps' parameter and run test2().
 * To test the SimJobConverge class by providing the 'config' and 'timesteps' paramter run test3(). 
 * The 'config' should adhere to the format received in the RequestConvergeNewAttempt. 
 * For Margin of error calculation for all the simulation jobs in the database whose include = 1
 * run test4()
 * 
 */
public class ATNTest {
	private static ATNEngine atn;
	private static SimJob simJob;
	private static int jobId;
	private static int status;
	private static int numberOdJobsToProcess = 0;
	private static PrintStream psATN = null;
	
	//For test5
    final static Logger logger = Logger.getLogger(GameEngineTest.class.getName());
    private static Player player;
    private static EcosystemLobby lobby;
    private static World world;
    private static Ecosystem ecosystem;
	private static Account account;
    private static GameEngine gameEngine;
    private Map<Integer, Integer> speciesList;
    private static long lastSave = System.currentTimeMillis(); // Last time saved to the database
    private static long lastActivity = System.currentTimeMillis();

	   public static void main(String args[]) throws FileNotFoundException, SQLException, SimulationException {
		   ATNEngine.LOAD_SIM_TEST_PARAMS = true;
	       //get output directory
	       atn = new ATNEngine();

	       //test1();		//Simple simulation job example, does not save to DB.
	       //test2();		//SimJobManager testing
	       //test3();		//SimJobConverge testing
	       //test4();		//Tests Margin of error calculation for all simulation jobs whose include = 1 
	       //test7();	//Root mean square error
	       //test8(); 	//Root mean square percentage error
	       
	       //test6();  //Testing biomass chart display for 1 job and saving the chart to a jpeg file
	       
	       //Going forward we set Constants.useAtnEngine = false
	       test5(); //Testing EcosystemController
	       //test9(); //Node Config testing
	       //test10();
	       
	   }
	   
	   public static void test10(){
		   //String node_config = "5,[70],2631,13.0,1,X=0.123,0,[5],1563,40.0,1,K=10000.0,0,[42],283,0.205,1,X=0.348,0,[31],2044,0.007,1,X=0.795,0,[14],1327,20.0,1,X=0.001,0";
		   String node_config = "5,[70],2494,13.0,1,X=0.123,0,[5],2000,40.0,1,K=10000.0,0,[42],240,0.205,1,X=0.348,0,[31],1415,0.007,1,X=0.795,0,[14],1752,20.0,1,X=0.001,0";
	       SimJob job = new SimJob();
	       job.setJob_Descript("atn1");
	       job.setNode_Config(node_config);
	       job.setManip_Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
	       job.setTimesteps(401);	//Atleast at the minimum start with 2 
	       String atnManipId = UUID.randomUUID().toString();
	       job.setATNManipulationId(atnManipId);
	       try {
			atn.processSimJob(job);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	       System.out.println("Processing complete.");
	   }
	   public static void test9(){
		   String node_config = "5,[70],2494,13.0,1,X=0.123,10,[31],B0=0.5,[31],D=0.0,[31],E=1.0,[31],Q=0.0,[31],Y=6.0,[42],B0=0.5,[42],D=0.0,[42],E=1.0,[42],Q=0.0,[42],Y=6.0,[5],2000,40.0,1,K=10000.0,0,[42],240,0.205,1,X=0.348,10,[14],B0=0.5,[14],D=0.0,[14],E=1.0,[14],Q=0.0,[14],Y=6.0,[5],B0=0.5,[5],D=0.0,[5],E=1.0,[5],Q=0.0,[5],Y=6.0,[31],1415,0.007,1,X=0.795,15,[14],B0=0.5,[14],D=0.0,[14],E=1.0,[14],Q=0.0,[14],Y=6.0,[31],B0=0.5,[31],D=0.0,[31],E=1.0,[31],Q=0.0,[31],Y=6.0,[5],B0=0.5,[5],D=0.0,[5],E=1.0,[5],Q=0.0,[5],Y=6.0,[14],1752,20.0,1,X=0.001,10,[14],B0=0.5,[14],D=0.0,[14],E=1.0,[14],Q=0.0,[14],Y=6.0,[5],B0=0.5,[5],D=0.0,[5],E=1.0,[5],Q=0.0,[5],Y=6.0";
	   }
	   
	   public static void test5(){
		   //RequestSpeciesAction class
		   //action = 0 , follows a type = 0 (get default sepcies), type = 1 (get every species); 
		   //action = 1 (create ecosystem using species), follows a size, species list( species_id, biomass)
		   	        
		    //Server intitializationa and startup
	        logger.info("Initialize tables for global use");
	        try {
	        	// Initialize tables for global use
	            ServerResources.init();			//Commented out the temporary override of biomass value in initialize method
				WorldController.getInstance().init();
		        ExpTable.init(); // Contains experience required per level
			} catch (ConfigureException e) {
				e.printStackTrace();
			}
	        
	        //Client login
		    logger.info("------RequestLogin------");
		    logger.info("Initialize player and login simulation");
		    account = AccountDAO.getAccount("hjr", "hjr");
		    if(account == null){
		    	logger.info("Player not registered");
		    	return;
		    }
		    Player player = PlayerDAO.getPlayerByAccount(account.getID());
		   
	        AccountDAO.updateLogin(account.getID(), "127.0.0.1");
	        startSaveTimer(); 
	        
	        //Populate world, player, zone and ecosystem info
	        logger.info("Getting world");
	        World world = WorldController.getInstance().get(1);
	        
	        logger.info("Verify World ID");
	        if (world == null) {
	            Log.println("Invalid world id.");
	            return;
	        }
	        
	        logger.info("Enter World");
	        if (!world.hasPlayer(player.getID())) {
	            world.add(player);
	        }
	        player.setWorld(world);
	        
	        //RequestZone by player = zone_id + player_id
	        //RequestZoneUpdate
	        //SELECT `player_id` FROM `world_zone` WHERE `zone_id` = 1 , player_id = 1  - Player assigns himself some zone_id one at a time
	        //UPDATE `world_zone` SET `player_id` = 162 WHERE `zone_id` = 1
	        logger.info("Let us assign zone_id = 1 to player_id = 1");
	        int zone_id = 1;
            if (WorldController.getInstance().isOwned(zone_id) == true) {
            	 logger.info("Player id already owns zone_id " + zone_id);
            } else {
                WorldController.getInstance().ownZone(player.getID(), zone_id);
            }
	        
            //EcosystemController.startEcosystem(Player player) does the job
	        // Retrieve Ecosystem, if haven't already
	        if (player.getEcosystem() == null) {
		        //RequestPlayerSelect where a check is made if ecosystem is created based on the last played time of the player
		        logger.info("------RequestPlayerSelect------");
		        int type = 3;
		        PlayerDAO.updateLastPlayed(player.getID());
		        if (player.getLastPlayed() == null) {
		            int world_id = WorldController.getInstance().first().getID();
		            EcosystemController.createEcosystem(world_id, player.getID(), player.getName() + "'s Ecosystem", (short) type);
		        }
		        
		        logger.info("------RequestWorld------");
		        // Get Player Ecosystem
		        logger.info("Getting Player ecosystem");
		        ecosystem = EcosystemDAO.getEcosystem(player.getWorld().getID(), player.getID());
		        if (ecosystem == null) {
		            return;
		        }
		        
		        if(true){
		            logger.info("------RequestSpeciesAction------");
			        HashMap<Integer, Integer> speciesList = new HashMap<Integer, Integer>();
//		            speciesList.put(13, 5000);	//species_id = 13, node_id = 13, biomass = 5000 , per unit biomass = 0.0000115 	(both in simtest_node_params && species tables)
//		            speciesList.put(20, 5000);  //species_id = 20, node_id = 20, biomass = 5000 , per unit biomass = 0.04 		(both in simtest_node_params && species tables)
//		            speciesList.put(31, 5000);  //species_id = 31, node_id = 31, biomass = 5000 , per unit biomass = 0.0075 	(both in simtest_node_params && species tables)
		            logger.info("Adding nodes to ecosystem");
//			        speciesList.put(1005, 2000); //Grass and herbs with 2000 biomass
			        //speciesList.put(2, 1000);		//African Clawless Otter
			        EcosystemController.createEcosystem(ecosystem, speciesList);
		        }
	        } 
	        
	        logger.info("EcosystemController.startEcosystem() functionality");
	        EcosystemController.startEcosystem(player);
			lobby = EcosystemController.getLobby();
			ecosystem = EcosystemController.getEcosystem();
	        
	        //We need to map the speciesList in the ecosystem to the zoneNodes in the ecosystem
	        lobby.getGameEngine().forceSimulation();
	        
	        addDelay();
	        
	        HashMap<Integer, Integer> speciesList = new HashMap<Integer, Integer>();	//SpeciesID, Biomass
	        speciesList.put(1005, 2000);	//To start we start with 1000 and add another 1000 to keep it consistent
	        speciesList.put(2, 2494);		//African Clawless Otter
	        speciesList.put(42, 240);		//African Grey Hornbill
	        speciesList.put(31, 1415);		//Tree Mouse 	
	        speciesList.put(14, 1752);		//Crickets
//	        speciesList.put(1, 1000);
	        
	        //lobby.getGameEngine().createSpeciesByPurchase(player,speciesList,ecosystem);		// We need to add to the ecosystem's addNodeList variable via the ecosystem.setNewSpeciesNode
	        logger.info("lobby.getGameEngine().createSpeciesByPurchase(player,speciesList,ecosystem) functionality");
	        for (Entry<Integer, Integer> entry : speciesList.entrySet()) {
	            int species_id = entry.getKey(), biomassValue = entry.getValue();
	            SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

	            for (int node_id : speciesType.getNodeList()) {
	            	ecosystem.setNewSpeciesNode(node_id, biomassValue);
	            }

	            Species species = null;

	            if (ecosystem.containsSpecies(species_id)) {
	                species = ecosystem.getSpecies(species_id);

	                for (SpeciesGroup group : species.getGroups().values()) {
	                	//HJR below line needs to be tested
	                    group.setBiomass(group.getBiomass() + biomassValue / species.getGroups().size());
	                    EcoSpeciesDAO.updateBiomass(group.getID(), group.getBiomass());
	                }	                
	            } else {
	                    int group_id = EcoSpeciesDAO.createSpecies(ecosystem.getID(), species_id, biomassValue);

	                    species = new Species(species_id, speciesType);
	                    SpeciesGroup group = new SpeciesGroup(species, group_id, biomassValue, Vector3.zero);
	                    species.add(group);
	            }

	            ecosystem.addSpecies(species);

	            // Logging Purposes
	            int player_id = player.getID(), ecosystem_id = ecosystem.getID();

	            try {
	                StatsDAO.createStat(species_id, lobby.getGameEngine().getCurrentMonth(), "Purchase", biomassValue, player_id, ecosystem_id);
	            } catch (SQLException ex) {
	                Log.println_e(ex.getMessage());
	            }
	        }
	        
	        lobby.getGameEngine().forceSimulation();
	        if(Constants.useSimEngine){
		        lobby.getGameEngine().deleteSimulationIds();
	        }
	       	        
	   }
	   
	   public static void addDelay(){
		   try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	   }
	   public static void test8() throws FileNotFoundException, SQLException, SimulationException {
	        List<Integer> simJobs = null;
	        SimJob job = null;
	        SimJobManager jobMgr = new SimJobManager();
	        initOutputStreams();
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
	           
	            EcosystemTimesteps ecosysTimesteps = new EcosystemTimesteps();
	            
	            int timesteps = 0;
	            int speciesCnt = 0;
	            int[] speciesID = null;
	            SimJobSZT[] sztArray;
	            double[][] webServicesData = null;
		         //Biomass generated via Web Services
	            if(job.getCsv() != null && !job.getCsv().isEmpty()){
		            Functions.extractCSVBiomassData(job.getCsv(), ecosysTimesteps);
		            speciesCnt = ecosysTimesteps.getNodeList().size();
		            timesteps = ecosysTimesteps.getTimesteps();
		            //loop through node values and assemble summary data
		            speciesID = new int[speciesCnt];
		            sztArray = new SimJobSZT[speciesCnt];
		            int spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
		                sztArray[spNum] = sjSzt;
		                speciesID[spNum] = sjSzt.getNodeIndex();
		                spNum++;
		            }
		            webServicesData = new double[speciesCnt][timesteps];
		            
		            spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
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
	            }
	            
	            //Biomass generated via ATN
	            double[][] calcBiomass = null;
	            if(job.getBiomassCSV() != null && !job.getBiomassCSV().isEmpty()){
		            Functions.extractCSVBiomassData(job.getBiomassCSV(), ecosysTimesteps);
		            speciesCnt = ecosysTimesteps.getNodeList().size();
		            timesteps = ecosysTimesteps.getTimesteps();
		            speciesID = new int[speciesCnt];
		            sztArray = new SimJobSZT[speciesCnt];
		            int spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
		                sztArray[spNum] = sjSzt;
		                speciesID[spNum] = sjSzt.getNodeIndex();
		                spNum++;
		            }
		            calcBiomass = new double[speciesCnt][timesteps];
		            spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                //copy nodetimestep data to local array for easier access
		                System.arraycopy(
		                        nodeTimesteps.getBiomassArray(),
		                        0,
		                        calcBiomass[spNum],
		                        0,
		                        timesteps
		                );
	
		                spNum++;
		            }
	            }
	            //output data
	            //A. print header
	            psATN.printf("timesteps JobID" + jobId);
	            for (int i = 0; i < timesteps; i++) {
	                psATN.printf(",%d", i);
	            }
	            psATN.println();
	            
	            //loop through each species
	            for (int i = 0; i < speciesCnt; i++) {
	            	if(job.getCsv() != null && !job.getCsv().isEmpty()){
	                    psATN.printf("i.%d.sim", speciesID[i]);
	     	           //B. print WebServices simulation data for species
	     	           for (int t = 0; t < timesteps; t++) {
	     	               psATN.printf(",%9.0f", webServicesData[i][t]);
	     	           }
	     	           psATN.println();
	                }
	                
	                //B. print combined biomass contributions (i.e. locally calculated biomass)
	                //for current species.
	                psATN.printf("i.%d.calc", speciesID[i]);
	                for (int t = 0; t < timesteps; t++) {
	                    psATN.printf(",%9.0f", calcBiomass[i][t]);
	                }
	                psATN.println();
	                
	                //calculate the difference between 
	                if(job.getCsv() != null && !job.getCsv().isEmpty() && job.getBiomassCSV() != null && !job.getBiomassCSV().isEmpty()){
	                	psATN.printf("i.%d.(calc-sim)/sim", speciesID[i]);
	                	double[] error = new double[timesteps];
		                for (int t = 0; t < timesteps; t++) {
		                	error[t] = (webServicesData[i][t] - calcBiomass[i][t])/ webServicesData[i][t];
		                    psATN.printf(",%9.6f", error[t]);
		                }
		                psATN.println();

//		                //population mean of a finite population of size N with values xi is given by mu
//		                //population variance of a finite population of size N with values xi is given by sigmaSquare
		                double[] squareOfError = new double[timesteps];
		                psATN.printf(",");
		                for (int t = 1; t < timesteps; t++) {
		                	squareOfError[t] = Math.pow(error[t],2);
		                    psATN.printf(",%9.6f", squareOfError[t]);
		                }
		                psATN.println();
		                
		                double sumOfErrorSquared = 0;
		                for (int t = 1; t < timesteps; t++) {
		                	sumOfErrorSquared += squareOfError[t];
		                }

		                double meanSquarePercentageError = sumOfErrorSquared/timesteps;
		                psATN.printf("Mean Squared Percentage Error ,%9.6f", meanSquarePercentageError);
		                psATN.println();
		                
		                double rootMeanSquarePercentageError = Math.sqrt(meanSquarePercentageError);;
		                psATN.printf("Root Mean Square Percentage Error ,%9.6f", rootMeanSquarePercentageError);
		                psATN.println();

	                }
	            }
	        }
	        
	        System.out.println("Processing complete.");
	   }
	   
	   public static void test7() throws FileNotFoundException, SQLException, SimulationException {
	        List<Integer> simJobs = null;
	        SimJob job = null;
	        SimJobManager jobMgr = new SimJobManager();
	        initOutputStreams();
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
	           
	            EcosystemTimesteps ecosysTimesteps = new EcosystemTimesteps();
	            
	            int timesteps = 0;
	            int speciesCnt = 0;
	            int[] speciesID = null;
	            SimJobSZT[] sztArray;
	            double[][] webServicesData = null;
		         //Biomass generated via Web Services
	            if(job.getCsv() != null && !job.getCsv().isEmpty()){
		            Functions.extractCSVBiomassData(job.getCsv(), ecosysTimesteps);
		            speciesCnt = ecosysTimesteps.getNodeList().size();
		            timesteps = ecosysTimesteps.getTimesteps();
		            //loop through node values and assemble summary data
		            speciesID = new int[speciesCnt];
		            sztArray = new SimJobSZT[speciesCnt];
		            int spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
		                sztArray[spNum] = sjSzt;
		                speciesID[spNum] = sjSzt.getNodeIndex();
		                spNum++;
		            }
		            webServicesData = new double[speciesCnt][timesteps];
		            
		            spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
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
	            }
	            
	            //Biomass generated via ATN
	            double[][] calcBiomass = null;
	            if(job.getBiomassCSV() != null && !job.getBiomassCSV().isEmpty()){
		            Functions.extractCSVBiomassData(job.getBiomassCSV(), ecosysTimesteps);
		            speciesCnt = ecosysTimesteps.getNodeList().size();
		            timesteps = ecosysTimesteps.getTimesteps();
		            speciesID = new int[speciesCnt];
		            sztArray = new SimJobSZT[speciesCnt];
		            int spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
		                sztArray[spNum] = sjSzt;
		                speciesID[spNum] = sjSzt.getNodeIndex();
		                spNum++;
		            }
		            calcBiomass = new double[speciesCnt][timesteps];
		            spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                //copy nodetimestep data to local array for easier access
		                System.arraycopy(
		                        nodeTimesteps.getBiomassArray(),
		                        0,
		                        calcBiomass[spNum],
		                        0,
		                        timesteps
		                );
	
		                spNum++;
		            }
	            }
	            //output data
	            //A. print header
	            psATN.printf("timesteps JobID" + jobId);
	            for (int i = 0; i < timesteps; i++) {
	                psATN.printf(",%d", i);
	            }
	            psATN.println();
	            
	            //loop through each species
	            for (int i = 0; i < speciesCnt; i++) {
	            	if(job.getCsv() != null && !job.getCsv().isEmpty()){
	                    psATN.printf("i.%d.sim", speciesID[i]);
	     	           //B. print WebServices simulation data for species
	     	           for (int t = 0; t < timesteps; t++) {
	     	               psATN.printf(",%9.0f", webServicesData[i][t]);
	     	           }
	     	           psATN.println();
	                }
	                
	                //B. print combined biomass contributions (i.e. locally calculated biomass)
	                //for current species.
	                psATN.printf("i.%d.calc", speciesID[i]);
	                for (int t = 0; t < timesteps; t++) {
	                    psATN.printf(",%9.0f", calcBiomass[i][t]);
	                }
	                psATN.println();
	                
	                //calculate the difference between 
	                if(job.getCsv() != null && !job.getCsv().isEmpty() && job.getBiomassCSV() != null && !job.getBiomassCSV().isEmpty()){
	                	psATN.printf("i.%d.(calc-sim)/sim", speciesID[i]);
	                	double[] error = new double[timesteps];
		                for (int t = 0; t < timesteps; t++) {
		                	error[t] = (calcBiomass[i][t] - webServicesData[i][t]);
		                    psATN.printf(",%9.6f", error[t]);
		                }
		                psATN.println();
		                
//		                //population mean of a finite population of size N with values xi is given by mu
//		                //population variance of a finite population of size N with values xi is given by sigmaSquare
		                double[] squareOfError = new double[timesteps];
		                psATN.printf(",");
		                for (int t = 1; t < timesteps; t++) {
		                	squareOfError[t] = Math.pow(error[t],2);
		                    psATN.printf(",%9.6f", squareOfError[t]);
		                }
		                psATN.println();
		                
		                double sumOfErrorSquared = 0;
		                for (int t = 1; t < timesteps; t++) {
		                	sumOfErrorSquared += squareOfError[t];
		                }

		                double meanSquareError = sumOfErrorSquared/timesteps;
		                psATN.printf("Mean Squared Error ,%9.6f", meanSquareError);
		                psATN.println();
		                
		                double rootMeanSquareError = Math.sqrt(meanSquareError);;
		                psATN.printf("Root Mean Square Error ,%9.6f", rootMeanSquareError);
		                psATN.println();
	                }
	            }
	        }
	        
	        System.out.println("Processing complete.");
	   }

  	   
   public static void test6() throws FileNotFoundException, SQLException, SimulationException {
        List<Integer> simJobs = null;
        SimJob job = null;
        SimJobManager jobMgr = new SimJobManager();

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

        numberOdJobsToProcess = 0;
        //loop through all identified jobs; load and process
        for (Integer jobId : simJobs) {
	        initOutputStreams();
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
           
            EcosystemTimesteps ecosysTimesteps = new EcosystemTimesteps();
            
            int timesteps = 0;
            int speciesCnt = 0;
            int[] speciesID = null;
            SimJobSZT[] sztArray;
            double[][] webServicesData = null;
	         //Biomass generated via Web Services
            if(job.getCsv() != null && !job.getCsv().isEmpty()){
	            Functions.extractCSVBiomassData(job.getCsv(), ecosysTimesteps);
	            speciesCnt = ecosysTimesteps.getNodeList().size();
	            timesteps = ecosysTimesteps.getTimesteps();
	            //loop through node values and assemble summary data
	            speciesID = new int[speciesCnt];
	            sztArray = new SimJobSZT[speciesCnt];
	            int spNum = 0;
	            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
	                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
	                sztArray[spNum] = sjSzt;
	                speciesID[spNum] = sjSzt.getNodeIndex();
	                spNum++;
	            }
	            webServicesData = new double[speciesCnt][timesteps];
	            
	            spNum = 0;
	            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
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
            }
            
            //Biomass generated via ATN
            double[][] calcBiomass = null;
            if(job.getBiomassCSV() != null && !job.getBiomassCSV().isEmpty()){
	            Functions.extractCSVBiomassData(job.getBiomassCSV(), ecosysTimesteps);
	            speciesCnt = ecosysTimesteps.getNodeList().size();
	            timesteps = ecosysTimesteps.getTimesteps();
	            speciesID = new int[speciesCnt];
	            sztArray = new SimJobSZT[speciesCnt];
	            int spNum = 0;
	            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
	                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
	                sztArray[spNum] = sjSzt;
	                speciesID[spNum] = sjSzt.getNodeIndex();
	                spNum++;
	            }
	            calcBiomass = new double[speciesCnt][timesteps];
	            spNum = 0;
	            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
	                //copy nodetimestep data to local array for easier access
	                System.arraycopy(
	                        nodeTimesteps.getBiomassArray(),
	                        0,
	                        calcBiomass[spNum],
	                        0,
	                        timesteps
	                );

	                spNum++;
	            }
            }
            
            //output data
            //A. print header
            psATN.printf("timesteps JobID" + jobId);
            for (int i = 0; i < timesteps; i++) {
                psATN.printf(",%d", i);
            }
            psATN.println();
            
            //loop through each species
            for (int i = 0; i < speciesCnt; i++) {
            	if(job.getCsv() != null && !job.getCsv().isEmpty()){
                    psATN.printf("i.%d.sim", speciesID[i]);
     	           //B. print WebServices simulation data for species
     	           for (int t = 0; t < timesteps; t++) {
     	               psATN.printf(",%9.0f", webServicesData[i][t]);
     	           }
     	           psATN.println();
                }
                
                //B. print combined biomass contributions (i.e. locally calculated biomass)
                //for current species.
                psATN.printf("i.%d.calc", speciesID[i]);
                for (int t = 0; t < timesteps; t++) {
                    psATN.printf(",%9.0f", calcBiomass[i][t]);
                }
                psATN.println();
	       }
            System.out.println("numberOdJobsToProcess-"+numberOdJobsToProcess++);
//	        if(numberOdJobsToProcess == 1){
//	        	break;
//	        }
	        String filePath = Functions.getLastCSVFilePath();
	        final BiomassChart demo = new BiomassChart("Biomass Chart", filePath);
	        //Render chart on screen
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);
	        //save chart which is rendered on screen
	        demo.saveChart(filePath);
        }
        
//        String filePath = Functions.getLastCSVFilePath();
//        final BiomassChart demo = new BiomassChart("Biomass Chart", filePath);
//        //Render chart on screen
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//        //save chart which is rendered on screen
//        demo.saveChart(filePath);
        System.out.println("Processing complete.");
   }
   
	   public static void test4() throws FileNotFoundException, SQLException, SimulationException {
	        List<Integer> simJobs = null;
	        SimJob job = null;
	        SimJobManager jobMgr = new SimJobManager();
	        initOutputStreams();
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
	           
	            EcosystemTimesteps ecosysTimesteps = new EcosystemTimesteps();
	            
	            int timesteps = 0;
	            int speciesCnt = 0;
	            int[] speciesID = null;
	            SimJobSZT[] sztArray;
	            double[][] webServicesData = null;
		         //Biomass generated via Web Services
	            if(job.getCsv() != null && !job.getCsv().isEmpty()){
		            Functions.extractCSVBiomassData(job.getCsv(), ecosysTimesteps);
		            speciesCnt = ecosysTimesteps.getNodeList().size();
		            timesteps = ecosysTimesteps.getTimesteps();
		            //loop through node values and assemble summary data
		            speciesID = new int[speciesCnt];
		            sztArray = new SimJobSZT[speciesCnt];
		            int spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
		                sztArray[spNum] = sjSzt;
		                speciesID[spNum] = sjSzt.getNodeIndex();
		                spNum++;
		            }
		            webServicesData = new double[speciesCnt][timesteps];
		            
		            spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
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
	            }
	            
	            //Biomass generated via ATN
	            double[][] calcBiomass = null;
	            if(job.getBiomassCSV() != null && !job.getBiomassCSV().isEmpty()){
		            Functions.extractCSVBiomassData(job.getBiomassCSV(), ecosysTimesteps);
		            speciesCnt = ecosysTimesteps.getNodeList().size();
		            timesteps = ecosysTimesteps.getTimesteps();
		            speciesID = new int[speciesCnt];
		            sztArray = new SimJobSZT[speciesCnt];
		            int spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
		                sztArray[spNum] = sjSzt;
		                speciesID[spNum] = sjSzt.getNodeIndex();
		                spNum++;
		            }
		            calcBiomass = new double[speciesCnt][timesteps];
		            spNum = 0;
		            for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
		                //copy nodetimestep data to local array for easier access
		                System.arraycopy(
		                        nodeTimesteps.getBiomassArray(),
		                        0,
		                        calcBiomass[spNum],
		                        0,
		                        timesteps
		                );
	
		                spNum++;
		            }
	            }
	            //output data
	            //A. print header
	            psATN.printf("timesteps JobID" + jobId);
	            for (int i = 0; i < timesteps; i++) {
	                psATN.printf(",%d", i);
	            }
	            psATN.println();
	            
	            //loop through each species
	            for (int i = 0; i < speciesCnt; i++) {
	            	if(job.getCsv() != null && !job.getCsv().isEmpty()){
	                    psATN.printf("i.%d.sim", speciesID[i]);
	     	           //B. print WebServices simulation data for species
	     	           for (int t = 0; t < timesteps; t++) {
	     	               psATN.printf(",%9.0f", webServicesData[i][t]);
	     	           }
	     	           psATN.println();
	                }
	                
	                //B. print combined biomass contributions (i.e. locally calculated biomass)
	                //for current species.
	                psATN.printf("i.%d.calc", speciesID[i]);
	                for (int t = 0; t < timesteps; t++) {
	                    psATN.printf(",%9.0f", calcBiomass[i][t]);
	                }
	                psATN.println();
	                
	                //calculate the difference between 
	                if(job.getCsv() != null && !job.getCsv().isEmpty() && job.getBiomassCSV() != null && !job.getBiomassCSV().isEmpty()){
	                	psATN.printf("i.%d.(calc-sim)/sim", speciesID[i]);
	                	double[] percentError = new double[timesteps];
		                for (int t = 0; t < timesteps; t++) {
		                	percentError[t] = (Math.abs(calcBiomass[i][t] - webServicesData[i][t]) / webServicesData[i][t]) * 100;
		                    psATN.printf(",%9.6f", percentError[t]);
		                }
		                psATN.println();
		                double totalPercentError = 0.0;
		                for (int t = 0; t < timesteps; t++) {
		                	totalPercentError += percentError[t];
		                }
		                double meanPercentError = totalPercentError / timesteps;
		                
//		                //population mean of a finite population of size N with values xi is given by mu
//		                //population variance of a finite population of size N with values xi is given by sigmaSquare
		                double[] differenceInErrorAndMean = new double[timesteps];
		                psATN.printf(",");
		                for (int t = 1; t < timesteps; t++) {
		                	differenceInErrorAndMean[t] = percentError[t] - meanPercentError;
		                    psATN.printf(",%9.6f", differenceInErrorAndMean[t]);
		                }
		                psATN.println();
		                
		                double[] differenceInErrorAndMeanSqr = new double[timesteps];
		                psATN.printf(",");
		                for (int t = 1; t < timesteps; t++) {
		                	differenceInErrorAndMeanSqr[t] = Math.pow(differenceInErrorAndMean[t],2);
		                    psATN.printf(",%9.6f", differenceInErrorAndMeanSqr[t]);
		                }

		                
		                double differenceInErrorAndMeanSqrTotal = 0.0;
		                for (int t = 0; t < timesteps; t++) {
		                	differenceInErrorAndMeanSqrTotal += differenceInErrorAndMeanSqr[t];
		                }
		                double differenceInErrorAndMeanSqrAverage = differenceInErrorAndMeanSqrTotal/timesteps;
		                psATN.printf(",%9.6f", differenceInErrorAndMeanSqrAverage);
		                
		                double standardDeviation = Math.sqrt(differenceInErrorAndMeanSqrAverage);
		                psATN.printf(",%9.6f", standardDeviation);
		                
		                //the z*-value is 1.96 if you want to be about 95% confident.
		                double standardError = 1.96 * (standardDeviation / Math.sqrt(timesteps));
		                psATN.printf(",%9.6f", standardError);
		                psATN.println();
	                }
	            }
	        }
	        
	        System.out.println("Processing complete.");
	   }
	   
	   private static void initOutputStreams() {
	       System.out.println("Ecosystem output will be written to:");
	       System.out.println("Network output will be written to:");
	       //psATN = Functions.getPrintStream("ATN", userInput.destDir);
	       psATN = Functions.getPrintStream("ATN", Constants.ATN_CSV_SAVE_PATH);
	   }
	   
	   public static void test3() throws FileNotFoundException, SQLException, SimulationException {
		   String config = "5,[5],2000,1.000,1,K=9431.818,0,[14],1751,20.000,1,X=0.273,0,[31],1415,0.008,1,X=1.000,0,[42],240,0.205,1,X=0.437,0,[70],2494,13.000,1,X=0.155,0";
		   int timesteps = 401;
		   SimJobConverge converge = new SimJobConverge(config,timesteps);			   
	   }
	   
	   /*
	    * If you want only the ATN Engine to run make sure the Constants.useAtnEngine = true and Constants.useSimEngine = false
	    * 
	    */
	   public static void test2() throws FileNotFoundException, SQLException, SimulationException {
		   SimJobManager jobMgr = new SimJobManager();
		   String config = "5,[5],2000,1.000,1,K=9431.818,0,[14],1751,20.000,1,X=0.273,0,[31],1415,0.008,1,X=1.000,0,[42],240,0.205,1,X=0.437,0,[70],2494,13.000,1,X=0.155,0";
		   int timesteps = 401;	//2 timesteps are enough at the minimum
	       simJob = new SimJob(config, timesteps);
	       jobMgr.setSimJob(simJob);
	       
	       try {
	            jobId = jobMgr.runSimJob();
	        } catch (Exception ex) {
	            Logger.getLogger(SimJobConverge.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            status = jobMgr.getStatus();            
	        }
	   }
	   
	   public static void test1() throws FileNotFoundException, SQLException, SimulationException {
	       SimJob job = new SimJob();
	       job.setJob_Descript("atn1");
	       //job.setNode_Config("2,[5],2000,1.000,0,0,[70],2494,13.000,1,X=0.155,0");	//Info comes from client
	       job.setNode_Config("5,[5],2000,1.000,1,K=9431.818,0,[14],1751,20.000,1,X=0.273,0,[31],1415,0.008,1,X=1.000,0,[42],240,0.205,1,X=0.437,0,[70],2494,13.000,1,X=0.155,0");
	       job.setManip_Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
	       job.setTimesteps(401);	//Atleast at the minimum start with 2 
	       String atnManipId = UUID.randomUUID().toString();
	       job.setATNManipulationId(atnManipId);
	       atn.processSimJob(job);
	       System.out.println("Processing complete.");
	   }
	   
	    public static void startSaveTimer() {
	        GameTimer saveTimer = new GameTimer();
	        saveTimer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                long current = System.currentTimeMillis();
	                long seconds = (current - lastSave) / 1000;

	                account.setPlayTime(account.getPlayTime() + seconds);
	                lastSave = current;

	                AccountDAO.updatePlayTime(account.getID(), account.getPlayTime(), account.getActiveTime());
	                UserLogDAO.updateTimeLog(account.getID(), (int) seconds);
	            }
	        }, Constants.SAVE_INTERVAL, Constants.SAVE_INTERVAL);
	    }
	   
}
