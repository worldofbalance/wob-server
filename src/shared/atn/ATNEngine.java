package shared.atn;
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
import java.io.InputStream;
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
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNode;
import shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNodeProperty;
import shared.org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingParameter;
import shared.org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass;

import shared.metadata.Constants;
import shared.model.Ecosystem;
import shared.model.Species;
import shared.model.SpeciesGroup;
import shared.model.SpeciesType;
import shared.model.ZoneNodes;
import shared.simulation.ParamValue;
import shared.simulation.SimulationException;
import shared.simulation.SpeciesZoneType;
import shared.simulation.SpeciesZoneType.SpeciesTypeEnum;
import shared.simulation.config.ManipulatingParameterName;
import shared.simulation.config.ManipulationActionType;
import shared.simulation.simjob.ConsumeMap;
import shared.simulation.simjob.EcosystemTimesteps;
import shared.simulation.simjob.NodeTimesteps;
import shared.simulation.simjob.PathTable;
import shared.simulation.simjob.SimJob;
import shared.simulation.simjob.SimJobSZT;
import shared.simulation.simjob.SimTestNode;
import shared.util.CSVParser;
import shared.util.Log;
import shared.core.GameServer;
//WOB_Server imports
import shared.db.EcoSpeciesDAO;

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
           InputStream in = this.getClass().getClassLoader()
                            .getResourceAsStream("shared/atn/SimJobConfig.properties");
           propertiesConfig.load(in);
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
   private HashMap<Integer, SpeciesZoneType> genSpeciesDataset(SimJob job,
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
       HashMap<Integer, SpeciesZoneType> mSpecies = new HashMap<Integer, SpeciesZoneType>();
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
               
               if (t == maxTimestep -1) {
            	   SpeciesZoneType szt = null;
                   if (!mSpecies.containsKey(sjSzt.getNodeIndex())) {
                       szt = new SpeciesZoneType(sjSzt.getName(), 
                    		   sjSzt.getNodeIndex(),
                               0, 0, biomass, null);
                       mSpecies.put(sjSzt.getNodeIndex(), szt);

                   } else { //update existing species current biomass
                       szt = mSpecies.get(sjSzt.getNodeIndex());

                       szt.setCurrentBiomass(biomass);
                   }
               }
           }
           biomassCSV += "\n" + tempStr;

       }
       
       biomassCSV += "\n\n";
       
       biomassCSV += job.getConsumeMap().toString() + "\n\n" ;
       
       biomassCSV += job.getPathTable().toString();
       
       job.setBiomassCsv(biomassCSV);
       
       //System.out.println(biomassCSV);
       return mSpecies;
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
 	
	public HashMap<Integer, SpeciesZoneType> processSimJob(SimJob job) throws SQLException, SimulationException {
		
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
       HashMap<Integer, SpeciesZoneType> mSpecies = genSpeciesDataset(job, ecosysTimesteps, ecosysRelationships);

       System.out.printf("\nTime... %d seconds\n\n", (System.nanoTime() - start)
               / (long) Math.pow(10, 9));
       return mSpecies;
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
       //job.setNode_Config("5,[5],2000,1.000,1,K=9431.818,0,[14],1751,20.000,1,X=0.273,0,[31],1415,0.008,1,X=1.000,0,[42],240,0.205,1,X=0.437,0,[70],2494,13.000,1,X=0.155,0");
       job.setNode_Config("5,[5],2000,1.0,1,K=9431.818,0,[14],1752,20.0,1,X=0.273,0,[31],1415,0.008,1,X=1.000,0,[42],240,0.205,1,X=0.437,0,[70],2494,13.0,1,X=0.155,0");
       job.setManip_Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
       job.setTimesteps(200);
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

   /*//HJR Concept orginally borrowed from Simulation engine and modified to 
    * work for ATN Engine
    */
  public HashMap<Integer, SpeciesZoneType> getPrediction(String networkOrManipulationId,
          int startTimestep, int runTimestep, Map<Integer, Integer> addSpeciesNodeList,
          ZoneNodes zoneNodes)
          throws SimulationException {
      long milliseconds = System.currentTimeMillis();

      Log.printf("\nPrediction at %d\n", startTimestep);
      
      //Get previous timestep biomass for all species from web service
      //JTC, use new HashMap containing all current settings from zoneNodes, masterSpeciesList
      //HJR changing to make a deep copy here , I am getting a null while iterating
      HashMap<Integer, SpeciesZoneType> masterSpeciesList = new HashMap<Integer, SpeciesZoneType>(zoneNodes.getNodes());     

      HashMap<Integer, SpeciesZoneType> mNewSpecies = new HashMap<Integer, SpeciesZoneType>();
      //JTC, mUpdateBiomass renamed from mUpdateSpecies
      HashMap<Integer, SpeciesZoneType> mUpdateBiomass = new HashMap<Integer, SpeciesZoneType>();
      //JTC, added new update type, mUpdateParams
      HashMap<Integer, SpeciesZoneType> mUpdateParams = new HashMap<Integer, SpeciesZoneType>();

      SpeciesZoneType szt;
      String nodeConfig = null;

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

//      //JTC, separated this to capture biomass updates made to ZoneNodes that
//      //are not received through addSpeciesNodeList (biomass and param updates)
//      for (SpeciesZoneType species : masterSpeciesList.values()) {
//          //param update also updates biomass, so insert into that list
//          //preferentially; o/w use biomass update list
//          if (species.paramUpdated) {
//              mUpdateParams.put(species.getNodeIndex(), species);
//              species.setParamUpdated(false);
//          } else if (species.biomassUpdated) {
//              mUpdateBiomass.put(species.getNodeIndex(), species);
//              species.setBiomassUpdated(false);
//          }
//      }

      // Insert new species using web services
      if (!mNewSpecies.isEmpty()) {
          zoneNodes.addNodes(mNewSpecies);
      }
      try {
    	  nodeConfig = addMultipleSpeciesType(
                  mNewSpecies,
                  masterSpeciesList,
                  startTimestep,
                  false,
                  networkOrManipulationId
          );
      } catch (Exception ex) {
          Log.println_e(ex.getMessage());
      }
//      // Update biomass changes to existing species using web services
//      if (!mUpdateBiomass.isEmpty()) {
//          List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();
//          for (SpeciesZoneType s : mUpdateBiomass.values()) {
//              Log.printf("Updating Biomass: [%d] %s %f\n", s.getNodeIndex(), s.getName(),
//                      s.getCurrentBiomass() / Constants.BIOMASS_SCALE);
//              lNodeBiomass.add(new NodeBiomass(
//                      s.getCurrentBiomass() / Constants.BIOMASS_SCALE, s.getNodeIndex()));
//          }
//          try {
////              updateBiomass(networkOrManipulationId, lNodeBiomass, startTimestep);
//          } catch (Exception ex) {
//              Log.println_e(ex.getMessage());
//          }
//      }

//      // JTC Update changes to existing species parameters using web services (also
//      // resubmits biomass, but couldn't find a way to do params w/o biomass
//      if (!mUpdateParams.isEmpty()) {
//          try {
////              increaseMultipleSpeciesType(
////                      mUpdateBiomass,
////                      masterSpeciesList,
////                      startTimestep,
////                      false,
////                      networkOrManipulationId
////              );
//          } catch (Exception ex) {
//              Log.println_e(ex.getMessage());
//          }
//      }

//      run(startTimestep, runTimestep, networkOrManipulationId);

      // get new predicted biomass
      try {
    	  if(!masterSpeciesList.isEmpty() || !mNewSpecies.isEmpty()){
    		  mUpdateBiomass = submitManipRequest("ATN", nodeConfig, startTimestep + runTimestep, false, null);
    	  }
      } catch (Exception ex) {
          Log.println_e(ex.getMessage());
          return null;
      }

      SpeciesZoneType updS;
      for (SpeciesZoneType priorS : masterSpeciesList.values()) {
    	  //System.out.println("priorS.nodeIndex " + priorS.nodeIndex);
          updS = mUpdateBiomass.get(priorS.nodeIndex);
          if (updS != null && updS.currentBiomass != 0) {
              masterSpeciesList.get(priorS.nodeIndex).setCurrentBiomass(Math.ceil(updS.getCurrentBiomass()));
          } 
//          else {
//              zoneNodes.removeNode(priorS.nodeIndex);
//          }
      }

      Log.printf("Total Time (Get Prediction): %.2f seconds",
              Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);

      return (HashMap) zoneNodes.getNodes();
  }
  
     public String getExistingNodeConfig(ZoneNodes zoneNodes){
        HashMap<Integer, SpeciesZoneType> masterSpeciesList = new HashMap<Integer, SpeciesZoneType>(zoneNodes.getNodes());
        String nodeConfig = null;
        try {
      	  nodeConfig = addMultipleSpeciesType(
                    null,
                    masterSpeciesList,
                    0,
                    false,
                    null
            );
        } catch (Exception ex) {
            Log.println_e(ex.getMessage());
        }
        return nodeConfig;
     }
	  /**
	   * Add multiple new nodes (SpeciesZoneType objects) to a manipulation and
	   * then submit. HJR
	   *
	   * @param manipSpeciesMap - species being added
	   * @param fullSpeciesMap - full list; for predator/prey info
	   * @param timestep
	   * @param isFirstManipulation
	   * @param networkOrManipulationId
	   * @return manipulation ID (String)
	   * @throws SimulationException
	   */
	  public String addMultipleSpeciesType(
	          HashMap<Integer, SpeciesZoneType> manipSpeciesMap,
	          HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
	          int timestep,
	          boolean isFirstManipulation,
	          String networkOrManipulationId){

		  //job.setNode_Config("5,
		  //[5],2000,1.000,1,K=9431.818,0,
		  //[14],1751,20.000,1,X=0.273,0,
		  //[31],1415,0.008,1,X=1.000,0,
		  //[42],240,0.205,1,X=0.437,0,
		  //[70],2494,13.000,1,X=0.155,0");
		  
//		  		  In addMultipleSpeciesType: node [70], biomass 2494, K = -1, R = -1.0000, X = 0.1233
//				  In addMultipleSpeciesType: node [5], biomass 2000, K = 10000, R = 1.0000, X = 0.5000
//				  In addMultipleSpeciesType: node [42], biomass 240, K = -1, R = -1.0000, X = 0.3478
//				  In addMultipleSpeciesType: node [31], biomass 1415, K = -1, R = -1.0000, X = 0.7953
//				  In addMultipleSpeciesType: node [14], biomass 1752, K = -1, R = -1.0000, X = 0.0010
	        StringBuilder builder = new StringBuilder();
	        builder.append(fullSpeciesMap.size()).append(",");
	        Object[] keys = fullSpeciesMap.keySet().toArray();
	        Arrays.sort(keys);
	        for (Object nodeIndex : keys) {
	        	SpeciesZoneType species = fullSpeciesMap.get(nodeIndex);
	        	Map<Integer, SimTestNode> simTestNodeParams = species.getSpeciesType().getSimTestNodeParams();
	        	SimTestNode nodeParams = simTestNodeParams.get(species.getNodeIndex());
//	            System.out.printf("In addMultipleSpeciesType: node [%d], "
//	                    + "biomass %d, perbiomass = %6.4f, K = %d, R = %6.4f, X = %6.4f\n", species.getNodeIndex(),
//	                    +(int) species.getCurrentBiomass(), roundToThreeDigits(nodeParams.getPerUnitBiomass()), (int) species.getParamK(),
//	                    species.getParamR(), species.getParamX());
	            //System.out.printf("K = %6.4f, R = %6.4f, X = %6.4f\n", nodeParams.getParamK(), nodeParams.getParamR(), nodeParams.getParamX());
	            
	        	builder.append("[").append(species.getNodeIndex()).append("]").append(",");
	        	builder.append((int) species.getCurrentBiomass()).append(",");
	        	builder.append(roundToThreeDigits(nodeParams.getPerUnitBiomass())).append(",");
	        	
	        	String systemParam = this.setSystemParameters(
	                    species, 
	                    fullSpeciesMap,
	                    timestep);
	        	builder.append(systemParam);
	        	//System.out.println(builder);
	        }
	        String node_config = builder.substring(0, builder.length()-1);
	        System.out.println("NodeConfig : "+ node_config);
	        //call processsim job here
	        return node_config;
	  }
	  

	    /**
	     * Submit manipulation request. HJR 
	     *
	     * @param sysParamList
	     * @param lManipulatingNodeProperty
	     * @param nodes
	     * @param timestep
	     * @param isFirstManipulation
	     * @param networkOrManipulationId
	     * @param manipDescript
	     * @return manipulation ID (String)
	     * @throws SimulationException
	     */
	    public HashMap<Integer, SpeciesZoneType> submitManipRequest(
	    		String job_descript,
	            String node_config,
	            int timestep, 
	            boolean isFirstManipulation,
	            String networkOrManipulationId){
	    	HashMap<Integer, SpeciesZoneType> mSpecies = null;
	        SimJob job = new SimJob();
	        job.setJob_Descript("ATN");
	        job.setNode_Config(node_config);
	        job.setManip_Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
	        job.setTimesteps(timestep);
	        String atnManipId = UUID.randomUUID().toString();
	        job.setATNManipulationId(atnManipId);
	        try {
	        	mSpecies = processSimJob(job);
			} catch (Exception e) {
				e.printStackTrace();
			} 
	        return mSpecies;
	    }
  
	    /* Set all system parameters for a node (SpeciesZoneType) for a simulation run.
	    HJR, original version of this, getSystemParameter() has some problems 
	    with how it submits link parameters.  (1) orig uses call to SZT.getlPreyIndex(), 
	    which is not active (set by prior call to SpeciesType.getPreyIndex, which returns 
	    empty list) i.e. never actually submits any link params, default or otherwise! */
	    @SuppressWarnings("unused")
		private String setSystemParameters(
		          SpeciesZoneType species,
		          HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
		          int timestepIdx) {
		
		      SpeciesTypeEnum type = species.getType();
		      int nodeIdx = species.getNodeIndex();
		
		      List<String> sParams = new ArrayList<String>();
	          StringBuilder builder = new StringBuilder();
		      if (type == SpeciesTypeEnum.PLANT) {
		            // Carrying capacity(k) and GrowthRate(r) are only effective when species is plant
		            // Higher Carrying capacity means higher biomass
		            // for example, if carrying capacity is 10, maximum biomass of species is 10.
		            // Higher growth rate means that species with higher growth rate will gain biomass faster.
		            // Metabolic rate (x) are effective for both animals and plants
		            // higher metabolic rate means that biomass of species will decrease compared to other species

		            //YES, need to divide by Constants.BIOMASS_SCALE.	    	  
		            setSystemParametersNode(sParams, timestepIdx, nodeIdx,
		                    species.getParamK(),
		                    ManipulatingParameterName.k, "carryingCapacityDefault");
		            if(false){	//HJR Currently I have turned off R and X
			            setSystemParametersNode(sParams, timestepIdx, nodeIdx, species.getParamR(),
			                    ManipulatingParameterName.r, "growthRateDefault");
			            setSystemParametersNode(sParams, timestepIdx, nodeIdx, species.getParamX(),
			                    ManipulatingParameterName.x, "metabolicRateDefault");
		            }
		            //Pack everything
		    	  	//[5],2000,1.000,1,K=9431.818,0,
		            //[K=10.0, R=1.0, X=0.5]
		            builder.append(sParams.size()).append(",");
		            for(int i = 0; i< sParams.size() ; i++){
		            	 builder.append(sParams.get(i)).append(",");
		            }
		            builder.append("0").append(",");
		      }
		      else if (type == SpeciesTypeEnum.ANIMAL) {
		    	    // Metabolic rate (x) are effective for both animals and plants
		            // higher metabolic rate means that biomass of species will decrease compared to other species
		            // Assimilation efficiency (e) is only available for animals.
		            // higher assimilation efficiency means that biomass of species will increase.
		            setSystemParametersNode(sParams, timestepIdx, nodeIdx, species.getParamX(),
		                    ManipulatingParameterName.x, "metabolicRateDefault");
		            builder.append(sParams.size()).append(",");
		            for(int i = 0; i< sParams.size() ; i++){
		            	builder.append(sParams.get(i));
		            	builder.append(",");
		            }
		            sParams.clear();
		            //loop through prey, adding link parameters
		            if (Integer.valueOf(propertiesConfig.getProperty("submitLinkParameterSettings")) == 1) {
		                int preyCnt = species.getSpeciesType().getPreyNodeIDs().size();
		                for (int preyIdx : species.getSpeciesType().getPreyNodeIDs()) {
		                    if (fullSpeciesMap == null || !fullSpeciesMap.containsKey(preyIdx)) {
		                        continue;
		                    }
		                    /* separate note: there appear to be a limited number of link params
		                    that can be submitted, over which an "axis fault" error will occur.  Varies
		                    with number of species in the ecosystem; on a test of a 15 species ecosystem,
		                    only 3 link params could be used.  Have disabled all, as I am not using them
		                    at this time.  Not fully evaluated, obviously, but these were not implemented
		                    at all previously.
		                    */
	
		                    /* default values that mimic web-services internal defaults are:
		                    predatorInterferenceDefault = 0
		                    assimilationEfficiencyAnimalDefault=1
		                    assimilationEfficiencyPlantDefault=1
		                    functionalResponseControlParameterDefault=0
		                    halfSaturationDensityDefault=0.5
		                    maximumIngestionRateDefault=6
		                    
		                    >consistent default values were not found for the following and there is some
		                    confusion about their role - based on prior code, parameter "a" matches
		                    relativeHalfSaturationDensityDefault.
		                    parameter "a" does not appear in any of the equations that I've seen, (possibly 
		                    omega - consumption rate?), 
		                    but DOES impact the simulation results.  No single value (0-1.0) gives result consistent
		                    to omitting the value, suggesting that species are distinguished somehow.
		                    Animal/Plant division was tested, but did not yield consistent results.
		                    relativeHalfSaturationDensityDefault=1.0
		                    relativeHalfSaturationDensity = 0.01
		                    */
		                    //// sequence is linkParamCnt,[prey_Id0],paramID0=value0,[prey_Id1],paramID1=value1,...[prey_IdN],paramIDN=valueN
		                    //setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamA(preyIdx),
		                    //        ManipulatingParameterName.a, "relativeHalfSaturationDensityDefault", preyCnt);
		                    if (false) {
		                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamB0(preyIdx),
		                                ManipulatingParameterName.b0, "halfSaturationDensityDefault", preyCnt);
		                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamD(preyIdx),
		                                ManipulatingParameterName.d, "predatorInterferenceDefault", preyCnt);
	                            setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamE(preyIdx),
	                                ManipulatingParameterName.e, "assimilationEfficiencyPlantDefault", preyCnt);
		                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamQ(preyIdx),
		                                ManipulatingParameterName.q, "functionalResponseControlParameterDefault", preyCnt);
		                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamY(preyIdx),
		                                ManipulatingParameterName.y, "maximumIngestionRateDefault", preyCnt);
		                    }
		                }
			            builder.append(sParams.size()).append(",");
			            for(int i = 0; i< sParams.size() ; i++){
			            	builder.append(sParams.get(i));
			            	builder.append(",");
			            }
			            //System.out.println(builder);
		            }
		      }
		      return builder.toString();
		  }
	  
	    /* adds individual node parameter to list of Manipulating Paramaters.
	     HJR, pulled out of getSystemParameter*/
	    private void setSystemParametersNode(List<String> sParams,
	            int timestepIdx, int nodeIdx, double value,
	            ManipulatingParameterName manipParam, String dfltValProp) {
	    	String nodeParam = new String();
	    	nodeParam = manipParam.name().toUpperCase() + "=";
	        /* node parameters can't have negative value. if they have negative value, it means
	         that data is not assigned yet. */
	        if (value < 0) {
	        	//sParams.append(Double.valueOf(propertiesConfig.getProperty(dfltValProp)));
	        } else {
	        	nodeParam +=roundToThreeDigits(value);
	        }
	        sParams.add(nodeParam);
	    }
	    
	    /* adds individual link parameter to list of ManipulatingParameters.
	     HJR, pulled out of getSystemParameter. */
	    private void setSystemParametersLink(
	            List<String> sParams,
	            int timestepIdx, 
	            int predIdx, 
	            int preyIdx, 
	            ParamValue pvalue,
	            ManipulatingParameterName manipParam, 
	            String dfltValProp,
	            int preyCnt
	    ) {	 
	    	String linkParam = new String();
	    	linkParam = "[" + preyIdx + "],";
	    	linkParam += manipParam.name().toUpperCase()+"=";
	        /* node parameters can't have negative value. if they have negative value, it means
	         that data is not assigned yet. */
	        if (pvalue != null) {    
	        	linkParam +=pvalue.getParamValue();
	        } else {
	        	linkParam +=Double.valueOf(propertiesConfig.getProperty(dfltValProp));
	        }
	        sParams.add(linkParam);
	    }
	    
	    protected double roundToThreeDigits(double val) {
	        val = Math.round(1000 * val) / 1000.0;
	        if (val == 0) {
	            val = 0.001;
	        }
	        return val;
	    }

		public void updateBiomass(Ecosystem ecosystem, Map<Integer, SpeciesZoneType> nextSpeciesNodeList) {
	        for (Entry<Integer, SpeciesZoneType> entry : nextSpeciesNodeList.entrySet()) {
	            int species_id = entry.getKey();
	            SpeciesZoneType szt = entry.getValue();
				int biomassValue = (int) entry.getValue().getCurrentBiomass();
				Species species = ecosystem.getSpecies(szt.getSpeciesType().getID());
		          for (SpeciesGroup group : species.getGroups().values()) {
		              group.setBiomass(biomassValue);
	
		              EcoSpeciesDAO.updateBiomass(group.getID(), group.getBiomass());
		          }
				}
		}
}
