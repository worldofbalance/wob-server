package atn;
/* By Justina Cotter
Modified By Hunvil Rodrigues

WoB ATN implements the ATN model locally using Bulirsch-Stroer integration
and outputs original WebServices data and locally generated data at the ecosystem  
and species level to evaluate the results.

Relies on WoB_Server source code for objects that store simulation timesteps and
species information.
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass;

import metadata.Constants;
import model.SpeciesType;
import model.ZoneNodes;
import simulation.SimulationException;
import simulation.SpeciesZoneType;
import simulation.simjob.ConsumeMap;
import simulation.simjob.EcosystemTimesteps;
import simulation.simjob.NodeTimesteps;
import simulation.simjob.PathTable;
import simulation.simjob.SimJob;
import simulation.simjob.SimJobSZT;
import util.CSVParser;
import util.Log;
import core.GameServer;
//WOB_Server imports

/**
*
* @author Justina
*/
public class ATNEngine {

   private static UserInput userInput;
   public static Properties propertiesConfig;
   private PrintStream psATN = null;
   /*
    The first two timesteps values produced by WebServices do not
    fit the local solution well.  Therefore, these values have been excluded
    for comparison purposes
    */
   private int initTimeIdx = 0;
   private double maxBSIErr = 1.0E-3;
   private double timeIntvl = 0.1;
   private static final int biomassScale = 1000;
   public static boolean LOAD_SIM_TEST_PARAMS = false;
   private static int equationSet = 0;  //0=ATN; 1=ODE 1; 2=ODE 2
   private double initTime = 0.0;
   private double initVal = 0.0;  //for non-ATN test
	private SimJob currentSimJob;
	private int status = Constants.STATUS_FAILURE;

   public ATNEngine() {
       //load properties file containing ATN model parameter values
       propertiesConfig = new Properties();
       try {
           propertiesConfig.load(new FileInputStream(
                   "src/atn/SimJobConfig.properties"));
       } catch (FileNotFoundException ex) {
           Logger.getLogger(ATNEngine.class.getName()).log(
                   Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(ATNEngine.class.getName()).log(
                   Level.SEVERE, null, ex);
       }

       /* 
        Read in non-std variables used for running sim jobs
        */
       if(LOAD_SIM_TEST_PARAMS){	//False by default, set to true only in main of this class
	       GameServer.getInstance();
	       SpeciesType.loadSimTestNodeParams(Constants.ECOSYSTEM_TYPE);
	       SpeciesType.loadSimTestLinkParams(Constants.ECOSYSTEM_TYPE);
       }
       //Above is not needed SimJobManager does this
   }
   
	public void setSimJob(SimJob job) {
		this.currentSimJob = job;
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
       int spNum = 0;
       for (NodeTimesteps nodeTimesteps : ecosysTimesteps.getTimestepMapValues()) {
           SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(nodeTimesteps.getNodeId());
           sztArray[spNum] = sjSzt;
           speciesID[spNum] = sjSzt.getNodeIndex();
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
       for (int i = 0; i < speciesCnt; i++) {
    	   NodeTimesteps nodeTimeSteps = ecosysTimesteps.getTimestepMap().get(speciesID[i]);
           //manually set biomass vals for excluded initial timesteps; this
           //includes the first value to be used as input 
           currBiomass[i] = nodeTimeSteps.getBiomass(initTimeIdx)/biomassScale;
    	   calcBiomass[0][i] =  currBiomass[i];
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
               //System.out.printf("Integration failed to converge, t = %d\n", t);
               //System.out.print(bsi.extrapArrayToString(biomassScale));
               break;
           }
           currBiomass = bsi.getYNew();
           System.arraycopy(currBiomass, 0, calcBiomass[t], 0, speciesCnt);

           contribsT = bsi.getContribs();
           for (int i = 0; i < speciesCnt; i++) {
               System.arraycopy(contribsT[i], 0, contribs[t - 1][i], 0, speciesCnt);
           }

       }  //timestep loop

	   double[][] webServicesData = new double[speciesCnt][timesteps];
       if(Constants.useSimEngine){		//We need the webServicesData only for marginOfErrorCalculation
           //extract timestep data from CSV
           Functions.extractCSVDataRelns(job.getCsv(), ecosysTimesteps, ecosysRelationships);
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
       //output data
       //A. print header
       psATN.printf("timesteps");
       for (int i = 0; i < timesteps; i++) {
           psATN.printf(",%d", i);
       }
       psATN.println();
       
       /* Convert to CSV String */
       String biomassCSV = "";
       biomassCSV = "Manipulation_id: " + job.getATNManipulationId() +"\n\n";
       
       int maxTimestep = job.getTimesteps();
       // Create Timestep Labels
       for (int j = 1; j <= maxTimestep; j++) {
           biomassCSV += "," + j;
       }
       
       //loop through each species
       for (int i = 0; i < speciesCnt; i++) {
           if(Constants.useSimEngine){
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
               psATN.printf(",%9.0f", calcBiomass[t][i] * biomassScale);
           }
           psATN.println();

//           //C. print individual biomass contributions from other species
//           for (int j = 0; j < speciesCnt; j++) {
//               psATN.printf("i.%d.j.%d.", speciesID[i], speciesID[j]);
//               for (int t = 0; t < timesteps; t++) {
//                   psATN.printf(",%9.0f", contribs[t][i][j] * biomassScale);
//               }
//               psATN.println();
//           }
           
           float extinction = 1.E-15f;          
       	   SimJobSZT sjSzt = job.getSpeciesZoneByNodeId(speciesID[i]);
           //add nodes to list in the order that they are received from infos
           String name = sjSzt.getName().replaceAll(",", " ") + " [" + sjSzt.getNodeIndex() + "]";
           String tempStr = name;
           for (int t = 0; t < maxTimestep; t++) {
               tempStr += ",";

               double biomass = calcBiomass[t][i] * biomassScale;

               if (biomass > 0) {
                   tempStr += biomass > extinction ? Math.ceil(biomass) : 0;
               }
           }
           biomassCSV += "\n" + tempStr;

       }

       // Append node config to the ATN CSV
       psATN.println();
       psATN.println("\"node-config: " + job.getNode_Config() + "\"");
       
       biomassCSV += "\n\n";
       
       biomassCSV += job.getConsumeMap().toString() + "\n\n" ;
       
       biomassCSV += job.getPathTable().toString();
       
       job.setBiomassCsv(biomassCSV);
       
       //System.out.println(biomassCSV);
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
               //System.out.printf("Integration failed to converge, t = %d\n", t);
               //System.out.print(bsi.extrapArrayToString(1));
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


   private void initOutputStreams() {
       System.out.println("Ecosystem output will be written to:");
       System.out.println("Network output will be written to:");
       //psATN = Functions.getPrintStream("ATN", userInput.destDir);
       psATN = Functions.getPrintStream("ATN", Constants.ATN_CSV_SAVE_PATH);
   }
 	
	public void processSimJob(SimJob job) throws SQLException, SimulationException {
		
	  this.setSimJob(job);
       //init ecosystem data sets
       EcosystemTimesteps ecosysTimesteps = new EcosystemTimesteps();
       Map<Integer, NodeRelationships> ecosysRelationships = new HashMap<>();
       NodeTimesteps nodeTimesteps;
       initOutputStreams();
       int[] nodeListArray = job.getSpeciesNodeList();
       List<SpeciesZoneType> speciesZoneList = job.getSpeciesZoneList();
       
       int timesteps = job.getTimesteps();
       for(int i = 0; i < nodeListArray.length; i++){
	       	int nodeId = nodeListArray[i];
	       	nodeTimesteps = new NodeTimesteps(nodeId, timesteps);
	       	nodeTimesteps.setBiomass(0, speciesZoneList.get(i).getCurrentBiomass());
	       	for (int j = 1; j < timesteps; j++) {
	       		nodeTimesteps.setBiomass(j, 0);
	       	}
	       	ecosysTimesteps.putNodeTimesteps(nodeId, nodeTimesteps);
       }
       
       ConsumeMap consumeMap = new ConsumeMap(job.getSpeciesNodeList(),
               Constants.ECOSYSTEM_TYPE);
       PathTable pathTable = new PathTable(consumeMap, 
               job.getSpeciesNodeList(), !PathTable.PP_ONLY);
//       Log.consoleln("consumeMap " + consumeMap.toString());
//       Log.consoleln("pathTable " + pathTable.toString());
       status = Constants.STATUS_SUCCESS;
       job.setConsumeMap(consumeMap);
       job.setPathTable(pathTable);
       
       createEcoSysRelationships(ecosysTimesteps, ecosysRelationships, pathTable.toString());
       
       long start = System.nanoTime();

       //generate data for current job
       genSpeciesDataset(job, ecosysTimesteps, ecosysRelationships);

       System.out.printf("\nTime... %d seconds\n\n", (System.nanoTime() - start)
               / (long) Math.pow(10, 9));
	}
	
   public void createEcoSysRelationships(
		   EcosystemTimesteps ecosysTimesteps,
           Map<Integer, NodeRelationships> ecosysRelationships,
           String csv){
	    //extract relationships
	        int nodeId, timesteps;
	        String spNameNode;
	        NodeTimesteps nodeTimesteps;

	        List<List<String>> dataSet = CSVParser.convertCSVtoArrayList(csv);  

	        //loop through dataset
	        //1 chart: 0: relationship/distance
	        int chart = 0, nodes = 0, relnOffset = 0, distOffset = 0, pathCntOffset = 0;
	        List<Integer> sortedNodeList = null;
	        boolean empty = false;
	        boolean newChart = true;
	        for (List<String> csvLine : dataSet) {
	            //end chart when first blank line is reached
	            if (csvLine.get(0).isEmpty()) {
	                //if empty already flagged, keep looping
	                if (empty == true) {
	                    continue;
	                }
	                //if this is first empty, increment chart#
	                empty = true;
	                newChart = true;
	                chart++;
	                if (chart > 2) {
	                    break;
	                }
	                continue;
	            }
	            empty = false;

	            switch (chart) {
	                case 1:  //relationship/distance chart
	                    //bypass first - header - line
	                    if (newChart) {
	                        sortedNodeList = new ArrayList<>(ecosysTimesteps.getNodeList());
	                        Collections.sort(sortedNodeList);
	                        nodes = sortedNodeList.size();
	                        relnOffset = 2;  //offset in csvLine to 1st reln
	                        distOffset = relnOffset + nodes;  //offset in csvLine to distance info
	                        pathCntOffset = distOffset + nodes; //offset in csvLine to pathCnt info
	                        newChart = false;
	                        break;
	                    }
	                    int nodeA = Integer.valueOf(csvLine.get(0));
	                    NodeRelationships nodeRelns = new NodeRelationships(nodeA);
	                    for (int i = 0; i < nodes; i++) {
	                        int nodeB = sortedNodeList.get(i);
	                        String relnStr = csvLine.get(relnOffset + i);
	                        int dist = Integer.valueOf(csvLine.get(distOffset + i));
	                        int pathCnt = Integer.valueOf(csvLine.get(pathCntOffset + i));

	                        nodeRelns.addRelationship(nodeB, relnStr, dist, pathCnt);
	                    }
	                    ecosysRelationships.put(nodeA, nodeRelns);
	                    break;
	                default:
	                    break;
	            }
	        }
   }
   
   public static void main(String args[]) throws FileNotFoundException, SQLException, SimulationException {
	   LOAD_SIM_TEST_PARAMS = true;
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

       ATNEngine atn = new ATNEngine();

       SimJob job = new SimJob();
       job.setJob_Descript("atn1");
       //job.setNode_Config("2,[5],2000,1.000,0,0,[70],2494,13.000,1,X=0.155,0");	//Info comes from client
       job.setNode_Config("5,[5],2000,1.000,1,K=9431.818,0,[14],1751,20.000,1,X=0.273,0,[31],1415,0.008,1,X=1.000,0,[42],240,0.205,1,X=0.437,0,[70],2494,13.000,1,X=0.155,0");
       job.setManip_Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
       job.setTimesteps(401);
       String atnManipId = UUID.randomUUID().toString();
       job.setATNManipulationId(atnManipId);
       atn.processSimJob(job);
       System.out.println("Processing complete.");

   }
   
   //Wrapper functions
   public SpeciesZoneType createSpeciesZoneType(int node_id, int biomass) {
       SpeciesZoneType szt;

       szt = new SpeciesZoneType("", node_id, 0, 0, biomass, null);

       return szt;
   }
   
   public void setSpeciesBiomass(SpeciesZoneType szt, double perSpeciesBiomass, String ecosystemManipulationId){
	   //int node_id, int perSpeciesBiomass
	   //If first time the ecosystemManipulationId may be null
	   if(szt != null){
		   szt.setPerSpeciesBiomass(perSpeciesBiomass);
	   }
   }
   
   public void setParameters(List<SpeciesZoneType> mSpecies, String ecosystemManipulationId){
	   
   }
   
   /*5/5/14, JTC, added persistent species data for players; system parameter masterSpeciesList,
   replaces mSpecies.  
   Get previous timestep biomass for all species from web service*/
  public HashMap<Integer, SpeciesZoneType> getPrediction(String networkOrManipulationId,
          int startTimestep, int runTimestep, Map<Integer, Integer> addSpeciesNodeList,
          ZoneNodes zoneNodes)
          throws SimulationException {
      long milliseconds = System.currentTimeMillis();

      Log.printf("\nPrediction at %d\n", startTimestep);

      //Get previous timestep biomass for all species from web service
      //JTC, use new HashMap containing all current settings from zoneNodes, masterSpeciesList
      HashMap<Integer, SpeciesZoneType> masterSpeciesList = (HashMap) zoneNodes.getNodes();

      HashMap<Integer, SpeciesZoneType> mNewSpecies = new HashMap<Integer, SpeciesZoneType>();
      //JTC, mUpdateBiomass renamed from mUpdateSpecies
      HashMap<Integer, SpeciesZoneType> mUpdateBiomass = new HashMap<Integer, SpeciesZoneType>();
      //JTC, added new update type, mUpdateParams
      HashMap<Integer, SpeciesZoneType> mUpdateParams = new HashMap<Integer, SpeciesZoneType>();

      SpeciesZoneType szt;
      SimJob job = new SimJob();

      for (int node_id : addSpeciesNodeList.keySet()) {
          int addedBiomass = addSpeciesNodeList.get(node_id);

          if (!masterSpeciesList.containsKey(node_id)) {
              szt = createSpeciesZoneType(node_id, addedBiomass);
              mNewSpecies.put(node_id, szt);
              //jtc - 04/19/15
              masterSpeciesList.put(node_id, szt);
          } else {
              szt = masterSpeciesList.get(node_id);

              szt.setCurrentBiomass(Math.max(0, szt.getCurrentBiomass() + addedBiomass));
              szt.setBiomassUpdated(true);
          }
      }

      //JTC, separated this to capture biomass updates made to ZoneNodes that
      //are not received through addSpeciesNodeList (biomass and param updates)
      for (SpeciesZoneType species : masterSpeciesList.values()) {
          //param update also updates biomass, so insert into that list
          //preferentially; o/w use biomass update list
          if (species.paramUpdated) {
              mUpdateParams.put(species.getNodeIndex(), species);
              species.setParamUpdated(false);
          } else if (species.biomassUpdated) {
              mUpdateBiomass.put(species.getNodeIndex(), species);
              //species.setBiomassUpdated(false);
          }
      }

      // Insert new species using web services
      if (!mNewSpecies.isEmpty()) {
          try {
//              addMultipleSpeciesType(
//                      mNewSpecies,
//                      masterSpeciesList,
//                      startTimestep,
//                      false,
//                      networkOrManipulationId
//              );
        	  
        	  List<SpeciesZoneType> tempList= new ArrayList<SpeciesZoneType>();
        	  for(SpeciesZoneType speciesZoneType : mNewSpecies.values()){
        		  tempList.add(speciesZoneType);
        	  }
        	  job.setSpeciesZoneList(tempList);
          } catch (Exception ex) {
              Log.println_e(ex.getMessage());
          }
          zoneNodes.addNodes(mNewSpecies);
      }
      // Update biomass changes to existing species using web services
      if (!mUpdateBiomass.isEmpty()) {
          List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();
          for (SpeciesZoneType s : mUpdateBiomass.values()) {
              Log.printf("Updating Biomass: [%d] %s %f\n", s.getNodeIndex(), s.getName(),
                      s.getCurrentBiomass() / Constants.BIOMASS_SCALE);
              lNodeBiomass.add(new NodeBiomass(
                      s.getCurrentBiomass() / Constants.BIOMASS_SCALE, s.getNodeIndex()));
          }
          try {
//              updateBiomass(networkOrManipulationId, lNodeBiomass, startTimestep);
          } catch (Exception ex) {
              Log.println_e(ex.getMessage());
          }
      }

      // JTC Update changes to existing species parameters using web services (also
      // resubmits biomass, but couldn't find a way to do params w/o biomass
      if (!mUpdateParams.isEmpty()) {
          try {
//              increaseMultipleSpeciesType(
//                      mUpdateBiomass,
//                      masterSpeciesList,
//                      startTimestep,
//                      false,
//                      networkOrManipulationId
//              );
          } catch (Exception ex) {
              Log.println_e(ex.getMessage());
          }
      }

//      run(startTimestep, runTimestep, networkOrManipulationId);

      // get new predicted biomass
      try {
          //JTC - changed variable from "mSpecies = " to "mUpdateBiomass = "
//          mUpdateBiomass = getBiomass(networkOrManipulationId, 0, startTimestep + runTimestep);
      } catch (Exception ex) {
          Log.println_e(ex.getMessage());
          return null;
      }
//      getBiomassInfo(networkOrManipulationId);

      //JTC - add loop to update persistent player species biomass information
      SpeciesZoneType updS;
      for (SpeciesZoneType priorS : masterSpeciesList.values()) {
//          updS = mUpdateBiomass.get(priorS.nodeIndex);
//          if (updS != null && updS.currentBiomass != 0) {
//              masterSpeciesList.get(priorS.nodeIndex).
//                      setCurrentBiomass(Math.ceil(updS.getCurrentBiomass()));
//          } else {
//              zoneNodes.removeNode(priorS.nodeIndex);
//          }
      }

      Log.printf("Total Time (Get Prediction): %.2f seconds",
              Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);

      return (HashMap) zoneNodes.getNodes();
  }

}
