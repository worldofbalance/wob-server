package cos.simulation;

// Java Imports

import cos.metadata.Constants;
import cos.model.SpeciesType;
import cos.model.ZoneNodes;
import cos.org.datacontract.schemas._2004._07.LINQ2Entities.User;
import cos.org.datacontract.schemas._2004._07.ManipulationParameter.*;
import cos.org.datacontract.schemas._2004._07.WCFService_Portal.*;
import cos.org.foodwebs.www._2009._11.IN3DService;
import cos.org.foodwebs.www._2009._11.IN3DServiceProxy;
import cos.simulation.SpeciesZoneType.SpeciesTypeEnum;
import cos.simulation.config.ManipulatingNodePropertyName;
import cos.simulation.config.ManipulatingParameterName;
import cos.simulation.config.ManipulationActionType;
import cos.simulation.config.ModelType;
import cos.util.Log;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Web Services Imports
// Other Imports

public class SimulationEngine {

    private IN3DService svc;
    private User user;
    private Properties propertiesConfig;

    public static final int SEARCH_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REMOVE_MODE = 2;
    public static final int INSERT_MODE = 3;
    public static final int REMOVE_ALL_MODE = 6;

    public SimulationEngine() {
        IN3DServiceProxy proxy = new IN3DServiceProxy();
        // Read properties file.
        Properties propertiesLogin = new Properties();
        propertiesConfig = new Properties();
        try {
            propertiesLogin.load(new FileInputStream("src/simulation/config/webserviceLogin.properties"));
            user = new User();
            user.setUsername(propertiesLogin.getProperty("username"));
            propertiesConfig.load(new FileInputStream("src/simulation/config/SimulationEngineConfig.properties"));
            proxy.setEndpoint(propertiesConfig.getProperty("wsdlurl"));
//            proxy.setEndpoint(propertiesConfig.getProperty("stagingurl"));            
//            proxy.setEndpoint(propertiesConfig.getProperty("devurl"));                        
            svc = proxy.getIN3DService();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public IN3DService getService() {
        return svc;
    }

    public User getUser() {
        return user;
    }

    public void logTime(String string) {
        if (true) {
            System.out.println(string);
        }
    }

    /**
     * Returns default parameter properties. Used by Sim_Jo classes. JTC
     *
     * @param propName
     * @return string value of requested property
     */
    public String getPropertiesConfig(String propName) {
        return propertiesConfig.getProperty(propName);
    }

    public String createSeregenttiSubFoodweb(String networkName, int nodeList[],
            boolean overwrite) throws SimulationException {
        String netId = null;

        ModelParam[] networkParams = new ModelParam[2];
        networkParams[0] = new ModelParam();
        networkParams[0].setParamName(ManipulatingNodePropertyName.Connectance.name());
        networkParams[0].setParamValue(Double.valueOf(propertiesConfig.getProperty("connectanceDefault")));
        networkParams[1] = new ModelParam();
        networkParams[1].setParamName(ManipulatingNodePropertyName.SpeciesCount.name());
        networkParams[1].setParamValue(Integer.valueOf(propertiesConfig.getProperty("speciesCountDefault")));

        NetworkCreationRequest req = new NetworkCreationRequest();
        req.setUser(user); // Owner of network
        req.setNetworkName(networkName); // Name of network -> username_worldname_zoneid
        req.setModelType(ModelType.CASCADE_MODEL.getModelType());
        req.setModelParams(networkParams);
        req.setCreationType(1); // sub food web
        req.setOriginFoodweb(propertiesConfig.getProperty("serengetiNetworkId")); // Serengeti
        req.setNodeList(nodeList);
        req.setOverwrite(overwrite);

        CreateFoodwebResponse response = null;
        try {
            response = (CreateFoodwebResponse) svc.executeNetworkCreationRequest(req);
            netId = response.getNetworkId();
            //TODO: Write web service call to database
        } catch (RemoteException ex) {
            System.err.println("executeNetworkCreationRequest exception (in createSeregenttiSubFoodweb): " + ex.getMessage());
            System.err.print("StackTrace: ");
            ex.printStackTrace();
            throw new SimulationException(ex.getMessage());
        }

        String errorMsg = response.getMessage();
        if (errorMsg != null) {
            System.err.println("CreateFoodwebResponse getMessage() error " + response.getErrorType()
                    + " (in createSeregenttiSubFoodweb).  Error msg: " + errorMsg);
            throw new SimulationException(errorMsg);
        }

        return netId;
    }

    public Properties getProperties() {
        return propertiesConfig;
    }

    public ManipulationResponse createDefaultSubFoodweb(String networkName) {
        ModelParam[] networkParams = new ModelParam[2];
        networkParams[0] = new ModelParam();
        networkParams[0].setParamName(ManipulatingNodePropertyName.Connectance.name());
        networkParams[0].setParamValue(Double.valueOf(propertiesConfig.getProperty("connectanceDefault")));
        networkParams[1] = new ModelParam();
        networkParams[1].setParamName(ManipulatingNodePropertyName.SpeciesCount.name());
        networkParams[1].setParamValue(Integer.valueOf(propertiesConfig.getProperty("speciesCountDefault")));

        NetworkCreationRequest req = new NetworkCreationRequest();
        req.setUser(user); // Owner of network
        req.setNetworkName(networkName); // Name of network -> username_worldname_zoneid
        req.setModelType(ModelType.CASCADE_MODEL.getModelType());
        req.setModelParams(networkParams);
        req.setCreationType(1); // sub food web
        req.setOriginFoodweb(propertiesConfig.getProperty("serengetiNetworkId")); // Serengeti
        int nodeList[] = {Integer.valueOf(propertiesConfig.getProperty("defaultSpecies1Id")), Integer.valueOf(propertiesConfig.getProperty("defaultSpecies2Id"))}; // Grass & buffalo
        req.setNodeList(nodeList);

        CreateFoodwebResponse response = null;
        try {
            response = (CreateFoodwebResponse) svc.executeNetworkCreationRequest(req);
            //TODO: Write web service call to database
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        String errorMsg = response.getMessage();
        if (errorMsg != null) {
            Log.println_e("Error type: " + response.getErrorType() + "  error msg:" + errorMsg);
            return null;
        } else {
            int timestepIdx = 0;
            List<Integer> lPrey = new ArrayList<Integer>();
            List<Integer> lPredator = new ArrayList<Integer>();
            List<SpeciesZoneType> speciesList = new ArrayList<SpeciesZoneType>();
            SpeciesZoneType szt1 = new SpeciesZoneType(propertiesConfig.getProperty("defaultSpecies1Name"),
                    5, Integer.valueOf(propertiesConfig.getProperty("defaultSpecies1SpeciesCount")),
                    Double.valueOf(propertiesConfig.getProperty("defaultSpecies1PerSpeciesBiomass")), 0.0,
                    SpeciesTypeEnum.PLANT);
            SpeciesZoneType szt2 = new SpeciesZoneType(propertiesConfig.getProperty("defaultSpecies2Name"),
                    88, Integer.valueOf(propertiesConfig.getProperty("defaultSpecies2SpeciesCount")),
                    Double.valueOf(propertiesConfig.getProperty("defaultSpecies2PerSpeciesBiomass")), 0.0,
                    SpeciesTypeEnum.ANIMAL);
            speciesList.add(szt1);
            speciesList.add(szt2);
            //Increasing carrying capacity of grass
            ManipulationResponse mResponse = modifyManipulatingParameters(speciesList, timestepIdx, true, response.getNetworkId());

            if (mResponse == null) {
                return null;
            }
            String manipulationId = mResponse.getManipulationId();
            String oldNetworkId = mResponse.getNetworksId();
//            deleteNetwork(response.getNetworkId()); // deleting old network made by NetworkCreationRequest
            //Increasing carrying capacity of buffalo
//            mResponse = modifyManipulatingParameters(szt2, timestepIdx, false, manipulationId);
//            deleteNetwork(oldNetworkId);  // deleting old network made by previous manipulation

//            if(mResponse == null)
//                return null;
            return mResponse;
        }
    }

    public void deleteManipulation(String manpId) {
        ManipulationDeletion md = new ManipulationDeletion(manpId);
        md.run();
    }

    public void deleteNetwork(String networkId) {
        NetworkDeletion nd = new NetworkDeletion(networkId);
        nd.run();
    }

    public class NetworkDeletion implements Runnable {

        String _netId;

        public NetworkDeletion(String netId) {
            _netId = netId;
        }

        public void run() {
            try {
                NetworkRemoveRequest request = new NetworkRemoveRequest();
                request.setUser(user);
                request.setNetworksIdx(_netId);
                svc.executeRequest(request);
                //if this was current sim Engine object's network, null it.
            } catch (Exception e) {

            }
        }
    }

    public class ManipulationDeletion implements Runnable {

        String _manpId;

        public ManipulationDeletion(String manpId) {
            _manpId = manpId;
        }

        public void run() {
            try {
                ManipulationInfoRequest request = new ManipulationInfoRequest();
                request.setUser(user);
                request.setManipulationId(_manpId);
                request.setMode(SimulationEngine.REMOVE_ALL_MODE);
                svc.executeRequest(request);
            } catch (Exception e) {

            }
        }
    }

    public void setParameters2(List<SpeciesZoneType> species, int timestep, String manipulation_id) {
        List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();

        for (SpeciesZoneType szt : species) {
            ManipulatingNode node = new ManipulatingNode();
            node.setTimestepIdx(timestep);
            node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
            node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
            node.setNodeIdx(szt.getNodeIndex());
            node.setBeginingBiomass(szt.getCurrentBiomass() / Constants.BIOMASS_SCALE);
            node.setHasLinks(false);
            nodes.add(node);

            if (szt.getType() == SpeciesTypeEnum.PLANT) {
                setNodeParameter(szt.getNodeIndex(), ManipulatingParameterName.k.getManipulatingParameterIndex(), szt.getParamK(), sParams);
            } else if (szt.getType() == SpeciesTypeEnum.ANIMAL) {
                setNodeParameter(szt.getNodeIndex(), ManipulatingParameterName.x.getManipulatingParameterIndex(), szt.getParamX(), sParams);
            }
        }

        updateSystemParameters(timestep, false, manipulation_id, sParams, nodes);
    }

    public String setNodeParameter(int nodeIdx, int paramIdx, double paramValue, int timestep, List<ManipulatingParameter> sParams) {
        ManipulatingParameter param = new ManipulatingParameter();

        if (paramIdx == ManipulatingParameterName.k.getManipulatingParameterIndex()) {
            if (paramValue <= 0) {
                return "Carrying capacity should be bigger than 0";
            }
            param.setParamType(ManipulatingParameterName.k.getManipulatingParameterType());
            param.setParamName(ManipulatingParameterName.k.name());
            param.setParamIdx(ManipulatingParameterName.k.getManipulatingParameterIndex());
            param.setNodeIdx(nodeIdx);
            param.setTimestepIdx(timestep);
            param.setParamValue(paramValue);
        } else if (paramIdx == ManipulatingParameterName.x.getManipulatingParameterIndex()) {
            if (paramValue < 0 || paramValue > 1) {
                return "Metabolic rate should be between 0 and 1";
            }
            param.setParamType(ManipulatingParameterName.x.getManipulatingParameterType());
            param.setParamName(ManipulatingParameterName.x.name());
            param.setParamIdx(ManipulatingParameterName.x.getManipulatingParameterIndex());
            param.setNodeIdx(nodeIdx);
            param.setTimestepIdx(timestep);
            param.setParamValue(paramValue);
        } else if (paramIdx == ManipulatingParameterName.r.getManipulatingParameterIndex()) {
            if (paramValue < 0 || paramValue > 1) {
                return "Plant growth rate should be between 0 and 1";
            }
            param.setParamType(ManipulatingParameterName.r.getManipulatingParameterType());
            param.setParamName(ManipulatingParameterName.r.name());
            param.setParamIdx(ManipulatingParameterName.r.getManipulatingParameterIndex());
            param.setNodeIdx(nodeIdx);
            param.setTimestepIdx(timestep);
            param.setParamValue(paramValue);
        } else {
            return "that type of node parameter is not supported yet";
        }

        sParams.add(param);

        return null;
    }

    public String setNodeParameter(int nodeIdx, int paramIdx, double paramValue, List<ManipulatingParameter> sParams) {
//    	System.out.println("SetNodeParameter [nodeIdx]-"+nodeIdx);
        ManipulatingParameter param = new ManipulatingParameter();

        if (paramIdx == ManipulatingParameterName.k.getManipulatingParameterIndex()) {
            if (paramValue <= 0) {
                return "Carrying capacity should be bigger than 0";
            }
            param.setParamType(ManipulatingParameterName.k.getManipulatingParameterType());
            param.setParamName(ManipulatingParameterName.k.name());
            param.setParamIdx(ManipulatingParameterName.k.getManipulatingParameterIndex());
            param.setNodeIdx(nodeIdx);
            param.setParamValue(paramValue);
        } else if (paramIdx == ManipulatingParameterName.x.getManipulatingParameterIndex()) {
            if (paramValue < 0 || paramValue > 1) {
                return "Metabolic rate should be between 0 and 1";
            }
            param.setParamType(ManipulatingParameterName.x.getManipulatingParameterType());
            param.setParamName(ManipulatingParameterName.x.name());
            param.setParamIdx(ManipulatingParameterName.x.getManipulatingParameterIndex());
            param.setNodeIdx(nodeIdx);
            param.setParamValue(paramValue);
        } else if (paramIdx == ManipulatingParameterName.r.getManipulatingParameterIndex()) {
            if (paramValue < 0 || paramValue > 1) {
                return "Plant growth rate should be between 0 and 1";
            }
            param.setParamType(ManipulatingParameterName.r.getManipulatingParameterType());
            param.setParamName(ManipulatingParameterName.r.name());
            param.setParamIdx(ManipulatingParameterName.r.getManipulatingParameterIndex());
            param.setNodeIdx(nodeIdx);
            param.setParamValue(paramValue);
        } else {
            return "that type of node parameter is not supported yet";
        }

        sParams.add(param);

        return null;
    }

    /* adds individual link parameter to list of ManipulatingParameters.
     4/22/14, JTC, pulled out of getSystemParameter. */
    private void setSystemParametersLink(List<ManipulatingParameter> sParams,
            int timestepIdx, int predIdx, int preyIdx, ParamValue pvalue,
            ManipulatingParameterName manipParam, String dfltValProp) {
        ManipulatingParameter param = new ManipulatingParameter();
        param.setParamType(manipParam.getManipulatingParameterType());
        param.setParamName(manipParam.name());
        param.setPredIdx(predIdx);
        param.setPreyIdx(preyIdx);
        param.setParamIdx(manipParam.getManipulatingParameterIndex());
        if (pvalue != null) {
            param.setParamValue(pvalue.getParamValue());
        } else {
            param.setParamValue(Double.valueOf(propertiesConfig.getProperty(dfltValProp)));
        }
        param.setTimestepIdx(timestepIdx);
        sParams.add(param);
    }

    /* adds individual node parameter to list of Manipulating Paramaters.
     4/22/14, JTC, pulled out of getSystemParameter*/
    private void setSystemParametersNode(List<ManipulatingParameter> sParams,
            int timestepIdx, int nodeIdx, double value,
            ManipulatingParameterName manipParam, String dfltValProp) {
        ManipulatingParameter param = new ManipulatingParameter();
        param.setParamType(manipParam.getManipulatingParameterType());
        param.setParamName(manipParam.name());
        param.setNodeIdx(nodeIdx);
        param.setParamIdx(manipParam.getManipulatingParameterIndex());
        /* node parameters can't have negative value. if they have negative value, it means
         that data is not assigned yet. */
        if (value < 0) {
            param.setParamValue(Double.valueOf(propertiesConfig.getProperty(dfltValProp)));
        } else {
            param.setParamValue(value);
        }
        param.setTimestepIdx(timestepIdx);
        sParams.add(param);
    }

    /* Set all system parameters for a node (SpeciesZoneType) for a simulation run.
     4/22/14, JTC, original version of this, getSystemParameter() has some problems 
     with how it submits link parameters.  (1) orig uses call to SZT.getlPreyIndex(), 
     which is not active (set by prior call to SpeciesType.getPreyIndex, which returns 
     empty list) i.e. never actually submits any link params, default or otherwise! */
    private List<ManipulatingParameter> setSystemParameters(SpeciesZoneType species,
            int timestepIdx) {

        SpeciesTypeEnum type = species.getType();
        int nodeIdx = species.getNodeIndex();

        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();

        if (type == SpeciesTypeEnum.PLANT) {
            // Carrying capacity(k) and GrowthRate(r) are only effective when species is plant
            // Higher Carrying capacity means higher biomass
            // for example, if carrying capacity is 10, maximum biomass of species is 10.
            // Higher growth rate means that species with higher growth rate will gain biomass faster.
            // Metabolic rate (x) are effective for both animals and plants
            // higher metabolic rate means that biomass of species will decrease compared to other species

            //YES, need to divide by Constants.BIOMASS_SCALE.
            setSystemParametersNode(sParams, timestepIdx, nodeIdx,
                    species.getParamK() / Constants.BIOMASS_SCALE,
                    ManipulatingParameterName.k, "carryingCapacityDefault");
            setSystemParametersNode(sParams, timestepIdx, nodeIdx, species.getParamR(),
                    ManipulatingParameterName.r, "growthRateDefault");
            setSystemParametersNode(sParams, timestepIdx, nodeIdx, species.getParamX(),
                    ManipulatingParameterName.x, "metabolicRateDefault");

        } else if (type == SpeciesTypeEnum.ANIMAL) {

            // Metabolic rate (x) are effective for both animals and plants
            // higher metabolic rate means that biomass of species will decrease compared to other species
            // Assimilation efficiency (e) is only available for animals.
            // higher assimilation efficiency means that biomass of species will increase.
            setSystemParametersNode(sParams, timestepIdx, nodeIdx, species.getParamX(),
                    ManipulatingParameterName.x, "metabolicRateDefault");

            //loop through prey, adding link parameters
            /* This section is untested.  It is not certain if this strategy will work.
             Does the prey node have to be part of the manipulation already?  If you have a set
             of new nodes, how would you handle it if that is the case?  You would have to add
             all of the species first and then configure their link parameters.
             for (Integer preyIdx : species.getSpeciesType().getPreyNodeIDs()) {
             setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamA(preyIdx),
             ManipulatingParameterName.a, "relativeHalfSaturationDensityDefault");
             setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamD(preyIdx),
             ManipulatingParameterName.d, "predatorInterferenceDefault");
             setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamE(preyIdx),
             ManipulatingParameterName.e, "assimilationEfficiencyDefault");
             setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamQ(preyIdx),
             ManipulatingParameterName.q, "functionalResponseControlParameterDefault");
             setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamY(preyIdx),
             ManipulatingParameterName.y, "maximumIngestionRateDefault");
             }
             */
        }
        return sParams;
    }

    public ManipulatingParameter[] CopySystemParameter(List<ManipulatingParameter> params) {
        if (params == null) {
            return null;
        }

        ManipulatingParameter[] sysParams = new ManipulatingParameter[params.size()];
        int idx = 0;
        for (ManipulatingParameter param : params) {
            sysParams[idx] = param;
            idx++;
        }
        return sysParams;
    }

    /* Create single species manipulation.
     4/11/14, JTC, adding new method to create single species manipulation to replace 
     redundant (and sometimes inconsistent) code from add/reduceSpeciesOfExistingtype, 
     add/removeNewSpeciesType and add/removeSpeciesType, none of which are ever called 
     in existing code */
    private ManipulatingNode createSpeciesTypeManip(SpeciesZoneType species,
            int timestep, List<ManipulatingParameter> sysParamList,
            List<ManipulatingNodeProperty> lManipulatingNodeProperty,
            ManipulationActionType manipActionType) throws SimulationException {

        ManipulatingNode node = new ManipulatingNode();
        node.setTimestepIdx(timestep);
        node.setManipulationActionType(manipActionType.getManipulationActionType());
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(species.getNodeIndex());
        node.setBeginingBiomass(species.getCurrentBiomass() / Constants.BIOMASS_SCALE);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setNodeName(species.getName()); // set node name
        node.setOriginFoodwebId(propertiesConfig.getProperty("serengetiNetworkId"));

        //don't update parameters and properties if removing species
        if (!manipActionType.equals(ManipulationActionType.SPECIES_REMOVAL)) {
            ManipulatingNodeProperty mnp = new ManipulatingNodeProperty();
            //Connectance
            mnp.setNodeIdx(species.getNodeIndex());
            mnp.setNodePropertyName(ManipulatingNodePropertyName.Connectance.name());
            mnp.setNodePropertyValue(Double.valueOf(propertiesConfig.getProperty("connectanceDefault")));
            lManipulatingNodeProperty.add(mnp);
            //Probability
            mnp = new ManipulatingNodeProperty();
            mnp.setNodeIdx(species.getNodeIndex());
            mnp.setNodePropertyName(ManipulatingNodePropertyName.Probability.name());
            mnp.setNodePropertyValue(Double.valueOf(propertiesConfig.getProperty("probabilityDefault"))); // if this value is low, invasion may fail.
            lManipulatingNodeProperty.add(mnp);
            //SpeciesZoneType count
            mnp = new ManipulatingNodeProperty();
            mnp.setNodeIdx(species.getNodeIndex());
            mnp.setNodePropertyName(ManipulatingNodePropertyName.SpeciesCount.name());
            mnp.setNodePropertyValue(species.getSpeciesCount());
            lManipulatingNodeProperty.add(mnp);

            //update parameters
            //sysParamList.addAll(this.getSystemParameter(species, timestep));
            //5/6/14, JTC, note this is an change to getSystemParameter that incorporates link params
            sysParamList.addAll(this.setSystemParameters(species, timestep));
        }

        System.out.println(manipActionType.getManipulationActionDescript() + " [" + species.getNodeIndex() + "] "
                + species.getName() + " " + species.getCurrentBiomass() / Constants.BIOMASS_SCALE);
        return node;
    }

    /* Submit multiple species manipulations with action type provided by calling method.
     Calls createSpeciesTypeManip() for each node.
     4/11/14, JTC, Note: used orig body of addMultipleSpeciesType as basis for this
     change.*/
    private ManipulatingNode[] createMultipleSpeciesTypeManip(
            List<SpeciesZoneType> speciesList, int timestep, ManipulationActionType manipActionType,
            List<ManipulatingParameter> sysParamList,
            List<ManipulatingNodeProperty> lManipulatingNodeProperty)
            throws SimulationException {

        ManipulatingNode[] nodes = new ManipulatingNode[speciesList.size()];
        int i = 0;

        for (SpeciesZoneType species : speciesList) {
            ManipulatingNode node = createSpeciesTypeManip(species, timestep,
                    sysParamList, lManipulatingNodeProperty, manipActionType);
            nodes[i++] = node;
            System.out.printf("In createMultipleSpeciesTypeManip: node [%d], "
                    + "biomass %d, K = %d, R = %6.4f, X = %6.4f\n", species.getNodeIndex(),
                    +(int) species.getCurrentBiomass(), (int) species.getParamK(),
                    species.getParamR(), species.getParamX());
        }

        return nodes;

    }

    /**
     * Submit manipulation request. 4/11/14, JTC, pulled redundant code out of
     * multiple methods
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
    public String submitManipRequest(List<ManipulatingParameter> sysParamList,
            List<ManipulatingNodeProperty> lManipulatingNodeProperty,
            ManipulatingNode[] nodes, int timestep, boolean isFirstManipulation,
            String networkOrManipulationId, String manipDescript)
            throws SimulationException {

        long milliseconds = System.currentTimeMillis();
        ManipulatingNodeProperty[] nps = null;
        if (lManipulatingNodeProperty != null
                && !lManipulatingNodeProperty.isEmpty()) {
            nps = (ManipulatingNodeProperty[]) lManipulatingNodeProperty.toArray(new ManipulatingNodeProperty[0]);
        }
        ManipulatingParameter[] sysParams = CopySystemParameter(sysParamList);

        SimpleManipulationRequest smr = new SimpleManipulationRequest();
        smr.setUser(user);
        smr.setBeginingTimestepIdx(timestep);
        if (isFirstManipulation) {
            smr.setNetworkId(networkOrManipulationId);
        } else {
            smr.setManipulationId(networkOrManipulationId);
        }
        smr.setTimestepsToRun(Integer.valueOf(propertiesConfig.getProperty("timestepsToRunDefault")));
        if (nodes != null) {
            smr.setManipulationModelNodes(nodes);
        }
        if (nps != null) {
            smr.setNodeProperties(nps);
        }
        if (sysParams != null) {
            smr.setSysParams(sysParams);
        }
        smr.setDescription(manipDescript);
        /*the following was used inconsistently by prior code, "true" for
         addMore/reduce/removeSpecies(OfExisting)Type, vs. init to "true" and then 
         changed to "false" before request submitted for addNewSpeciesType. (still 
         the same way in some existing methods in this class.) Setting same as 
         "addMultipleSpeciesType", "false". */
        smr.setSaveLastTimestepOnly(false);

        ManipulationResponse response = new ManipulationResponse();
        try {
            response = (ManipulationResponse) svc.executeManipulationRequest(smr);
            //TODO: Write web service call to database

        } catch (RemoteException ex) {
            Logger.getLogger(SimulationEngine.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        logTime("Total Time (submitManipRequest): "
                + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
        String errMsg = response.getMessage();
        if (errMsg != null) {
            throw new SimulationException("Error (submitManipRequest): " + errMsg);
        }
        return response.getManipulationId();
    }

    /**
     * Add multiple new nodes (SpeciesZoneType objects) to a manipulation and
     * then submit. 4/11/14, JTC, new version to reduce duplicate code
     *
     * @param speciesList
     * @param timestep
     * @param isFirstManipulation
     * @param networkOrManipulationId
     * @return manipulation ID (String)
     * @throws SimulationException
     */
    public String addMultipleSpeciesType(List<SpeciesZoneType> speciesList,
            int timestep, boolean isFirstManipulation, String networkOrManipulationId) throws SimulationException {
        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty = new ArrayList<ManipulatingNodeProperty>();

        ManipulatingNode[] nodes = createMultipleSpeciesTypeManip(speciesList, timestep,
                ManipulationActionType.SPECIES_INVASION, sysParamList, lManipulatingNodeProperty);
        String manipId = submitManipRequest(sysParamList, lManipulatingNodeProperty, nodes,
                timestep, isFirstManipulation, networkOrManipulationId,
                " " + propertiesConfig.getProperty("addNewSpeciesTypeDescription"));
        return manipId;
    }

    /**
     * Add more (biomass) for existing species types to a manipulation and then
     * submit. 4/11/14, JTC, consolidated contents of original
     * "addMoreSpeciesOfExistingType" (which was never called) into this renamed
     * (for standardization) method with calls to
     * createMultipleSpeciesTypeManip()/submitManipRequest().
     *
     * @param speciesList
     * @param timestep
     * @param isFirstManipulation
     * @param networkOrManipulationId
     * @return manipulation ID (string)
     * @throws SimulationException
     */
    public String increaseMultipleSpeciesType(List<SpeciesZoneType> speciesList,
            int timestep, boolean isFirstManipulation, String networkOrManipulationId) throws SimulationException {
        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty = new ArrayList<ManipulatingNodeProperty>();

        ManipulatingNode[] nodes = createMultipleSpeciesTypeManip(speciesList, timestep,
                ManipulationActionType.SPECIES_PROLIFERATION, sysParamList, lManipulatingNodeProperty);
        String manipId = submitManipRequest(sysParamList, lManipulatingNodeProperty, nodes,
                timestep, isFirstManipulation, networkOrManipulationId,
                " " + propertiesConfig.getProperty("addMoreSpeciesToExistingTypeDescription"));
        return manipId;
    }

    /**
     * Reduce ("exploit") single species of existing type in a manipulation and
     * then submit. 4/11/14, JTC, consolidated contents of original
     * "reduceSpeciesOfExistingType" (which was never called) into this renamed
     * (for standardization) method with calls to
     * createMultipleSpeciesTypeManip()/submitManipRequest().
     *
     * @param species
     * @param timestep
     * @param isFirstManipulation
     * @param networkOrManipulationId
     * @return manipulation ID (string)
     * @throws SimulationException
     */
    public String reduceSpeciesType(SpeciesZoneType species, int timestep,
            boolean isFirstManipulation, String networkOrManipulationId)
            throws SimulationException {
        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(species, timestep, sysParamList, lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_EXPLOIT);
        String manipId = submitManipRequest(sysParamList, lManipulatingNodeProperty, nodes,
                timestep, isFirstManipulation, networkOrManipulationId,
                " " + propertiesConfig.getProperty("updateBiomassDescription"));
        return manipId;
    }

    /**
     * Add single new species type to a manipulation and then submit. 4/11/14,
     * JTC, consolidated contents of original "addNewSpeciesType" into this
     * renamed (for standardization) method with calls to
     * createMultipleSpeciesTypeManip()/submitManipRequest()
     *
     * @param species
     * @param timestep
     * @param isFirstManipulation
     * @param networkOrManipulationId
     * @return manipulation ID (String)
     * @throws SimulationException
     */
    public String addSpeciesType(SpeciesZoneType species, int timestep,
            boolean isFirstManipulation, String networkOrManipulationId)
            throws SimulationException {
        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(species, timestep, sysParamList, lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_INVASION);
        String manipId = submitManipRequest(sysParamList, lManipulatingNodeProperty, nodes,
                timestep, isFirstManipulation, networkOrManipulationId,
                " " + propertiesConfig.getProperty("addNewSpeciesTypeDescription"));
        return manipId;
    }

    /**
     * Add more (biomass) for a single node (species) to a manipulation and then
     * submit. 4/11/14, JTC, consolidated contents of original method with calls
     * to createMultipleSpeciesTypeManip()/submitManipRequest()
     *
     * @param species
     * @param timestep
     * @param isFirstManipulation
     * @param networkOrManipulationId
     * @return manipulation ID (String)
     * @throws SimulationException
     */
    public String increaseSpeciesType(SpeciesZoneType species, int timestep,
            boolean isFirstManipulation, String networkOrManipulationId) throws SimulationException {
        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(species, timestep, sysParamList, lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_INVASION);
        String manipId = submitManipRequest(sysParamList, lManipulatingNodeProperty, nodes,
                timestep, isFirstManipulation, networkOrManipulationId,
                " " + propertiesConfig.getProperty("addMoreSpeciesToExistingTypeDescription"));
        return manipId;
    }

    /**
     * remove a single species type from a manipulation and then submit.
     * 4/11/14, JTC, consolidated contents of original method with calls to
     * createMultipleSpeciesTypeManip()/submitManipRequest()
     *
     * @param species
     * @param timestep
     * @param isFirstManipulation
     * @param networkOrManipulationId
     * @return manipulation ID (String)
     * @throws SimulationException
     */
    public String removeSpeciesType(SpeciesZoneType species, int timestep, boolean isFirstManipulation,
            String networkOrManipulationId) throws SimulationException {
        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(species, timestep, sysParamList, lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_REMOVAL);
        String manipId = submitManipRequest(sysParamList, lManipulatingNodeProperty, nodes,
                timestep, isFirstManipulation, networkOrManipulationId,
                " " + propertiesConfig.getProperty("removeSpeciesTypeDescription"));
        return manipId;
    }

    public HashMap<Integer, SpeciesZoneType> getBiomass(String manipulationId,
            int nodeIndex, int timestep) throws SimulationException {
        long milliseconds = System.currentTimeMillis();

        HashMap<Integer, SpeciesZoneType> mSpecies = new HashMap<Integer, SpeciesZoneType>();

        ManipulationTimestepInfoRequest req = new ManipulationTimestepInfoRequest();
        req.setManipulationId(manipulationId);
        req.setIsNodeTimestep(true);
        req.setNodeIdx(nodeIndex);
        req.setTimestep(timestep);

        ManipulationTimestepInfoResponse response = null;
        try {
            response = (ManipulationTimestepInfoResponse) svc.executeRequest(req);
        } catch (RemoteException e) {
            throw new SimulationException("Error on running ManipulationTimestepInfoRequest: " + e.getMessage());
        }
        ManipulationTimestepInfo[] infos = response.getManipulationTimestepInfos();
        //TODO: Write web service call to database

        if (infos.length > 0) {
            SpeciesZoneType szt = null;

            for (ManipulationTimestepInfo speciesInfo : infos) {
                if (speciesInfo.getTimestepIdx() == timestep) {
                    //add new species if not existing

                    double biomass = speciesInfo.getBiomass() * Constants.BIOMASS_SCALE;
                    /*4/20/14, JTC, moved to SpeciesZoneType constructor
                     SpeciesType speciesType = GameServer.getInstance().getSpeciesTypeByNodeID(speciesInfo.getNodeIdx());
                     int count = biomass < 1 ? 0 : (int) Math.round(biomass / speciesType.getAvgBiomass());
                     */
                    if (!mSpecies.containsKey(speciesInfo.getNodeIdx())) {
                        /* 4/20/14, JTC, moved to SpeciesZoneType constructor
                         SpeciesTypeEnum group_type =
                         speciesType.getOrganismType() == Constants.ORGANISM_TYPE_ANIMAL
                         ? SpeciesTypeEnum.ANIMAL : SpeciesTypeEnum.PLANT;
                         */
                        szt = new SpeciesZoneType(speciesInfo.getNodeName(), speciesInfo.getNodeIdx(),
                                0, 0, biomass, null);
                        /* 4/20/14, JTC, moved the following to SpeciesZoneType constructor
                         szt.setTrophicLevel(speciesType.getTrophicLevel());
                         //HJR
                         //Added these two lines to pass the prey and predator index to web services
                         szt.setlPredatorIndex(speciesType.getPredatorIndex());
                         szt.setlPreyIndex(speciesType.getPreyIndex());
                         if (group_type == SpeciesTypeEnum.ANIMAL) {
                         szt.setParamX(((AnimalType) speciesType).getMetabolism());
                         }
                         */
                        mSpecies.put(speciesInfo.getNodeIdx(), szt);
                    } else { //update existing species current biomass
                        szt = mSpecies.get(speciesInfo.getNodeIdx());

                        szt.setCurrentBiomass(biomass);
                        //szt.setSpeciesCount(count);  //JTC, moved to constructor
                    }
                }
            }
        } else {
            throw new SimulationException("No Species Found!");
        }

        Log.printf("Total Time (Get Biomass): %.2f seconds", Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);

        return mSpecies;
    }

    /*sets biomass of multiple species using one call to SimpleManipulationRequest.setNodeBiomasses with array
     of node/biomass*/
    public void updateBiomass(String manipulationId, List<NodeBiomass> lNodeBiomass, int timestep) throws SimulationException {
        long milliseconds = System.currentTimeMillis();

        ManipulatingNode node = new ManipulatingNode();
        node.setTimestepIdx(timestep);
        node.setManipulationActionType(ManipulationActionType.MULTIPLE_BIOMASS_UPDATE.getManipulationActionType());
        ManipulatingNode[] nodes = new ManipulatingNode[1];
        nodes[0] = node;

        SimpleManipulationRequest smr = new SimpleManipulationRequest();
        smr.setUser(user);
        smr.setBeginingTimestepIdx(timestep);
        smr.setManipulationId(manipulationId);
        smr.setTimestepsToRun(Integer.valueOf(propertiesConfig.getProperty("timestepsToRunDefault")));
        smr.setManipulationModelNodes(nodes);
        NodeBiomass nba[] = (NodeBiomass[]) lNodeBiomass.toArray(new NodeBiomass[0]);
        smr.setNodeBiomasses(nba);  //note: lacks divide by BIOMASS_SCALE
        smr.setDescription(propertiesConfig.getProperty("updateBiomassDescription"));
        smr.setSaveLastTimestepOnly(false);

        ManipulationResponse response = null;
        try {
            response = (ManipulationResponse) svc.executeManipulationRequest(smr);
            //TODO: Write web service call to database
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.printf("Total Time (Update Biomass): %.2f seconds", Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
        String errMsg = response.getMessage();
        if (errMsg != null) {
            throw new SimulationException("Error (updateBiomass): " + errMsg);
        }
    }

    /*sets biomass of multiple species using individual calls to to SimpleManipulationRequest.setBeginingBiomass*/
    public void updateBiomass2(String manipulationId, List<NodeBiomass> lNodeBiomass, int timestep) throws SimulationException {
        long milliseconds = System.currentTimeMillis();

        List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();

        for (NodeBiomass nodeBiomass : lNodeBiomass) {
            ManipulatingNode node = new ManipulatingNode();
            node.setTimestepIdx(timestep);
            node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
            node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
            node.setNodeIdx(nodeBiomass.getNodeIdx());
            node.setBeginingBiomass(nodeBiomass.getBiomass());  //note: lacks divide by BIOMASS_SCALE
            node.setHasLinks(false);
            nodes.add(node);
        }

        if (!nodes.isEmpty()) {
            nodes.get(0).setOriginFoodwebId(propertiesConfig.getProperty("serengetiNetworkId"));
        }

        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
        updateSystemParameters(timestep, false, manipulationId, sParams, nodes);

        Log.printf("Total Time (Update Biomass): %.2f seconds", Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
    }

    public SpeciesZoneType createSpeciesZoneType(int node_id, int biomass) {
        SpeciesZoneType szt;

        /*4/14/20, JTC, moved to SpeciesZoneType constructor
         SpeciesType speciesType = GameServer.getInstance().getSpeciesTypeByNodeID(node_id);
         SpeciesTypeEnum group_type = speciesType.getOrganismType() == Constants.ORGANISM_TYPE_ANIMAL
         ? SpeciesTypeEnum.ANIMAL : SpeciesTypeEnum.PLANT;
         //4/15/14, JTC, adding calculation of count (copied from from SimulationEngine.getBiomass())
         int count = biomass < 1 ? 0 : (int) Math.round(biomass
         / (speciesType.getAvgBiomass() * speciesType.getNodeDistribution(node_id)));
         szt = new SpeciesZoneType(speciesType.getName(), node_id, count, speciesType.getAvgBiomass(),
         biomass, group_type);
         */
        szt = new SpeciesZoneType("", node_id, 0, 0, biomass, null);
        /* 4/20/14, JTC, moved to SpeciesZoneType constructor
         szt.setTrophicLevel(speciesType.getTrophicLevel());
         //HJR Added these two lines to pass the prey and predator index to web services
         szt.setlPredatorIndex(speciesType.getPredatorIndex());
         szt.setlPreyIndex(speciesType.getPreyIndex());
         if (group_type == SpeciesTypeEnum.ANIMAL) {
         szt.setParamX(((AnimalType) speciesType).getMetabolism());
         }
         */

        return szt;
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
        //HashMap<Integer, SpeciesZoneType> mSpecies = getBiomass(networkOrManipulationId, 0, startTimestep);
        //JTC, use new HashMap containing all current settings from zoneNodes, masterSpeciesList
        HashMap<Integer, SpeciesZoneType> masterSpeciesList = (HashMap) zoneNodes.getNodes();

        HashMap<Integer, SpeciesZoneType> mNewSpecies = new HashMap<Integer, SpeciesZoneType>();
        //JTC, mUpdateBiomass renamed from mUpdateSpecies
        HashMap<Integer, SpeciesZoneType> mUpdateBiomass = new HashMap<Integer, SpeciesZoneType>();
        //JTC, added new update type, mUpdateParams
        HashMap<Integer, SpeciesZoneType> mUpdateParams = new HashMap<Integer, SpeciesZoneType>();
        //HashMap<Integer, SpeciesZoneType> mExistingSpecies = new HashMap<>(); JTC - not used

        SpeciesZoneType szt;

        for (int node_id : addSpeciesNodeList.keySet()) {
            //JTC, renamed "count" to "addedBiomass" after clarification from Gary
            int addedBiomass = addSpeciesNodeList.get(node_id);

            if (!masterSpeciesList.containsKey(node_id)) {
                szt = createSpeciesZoneType(node_id, addedBiomass);
                mNewSpecies.put(node_id, szt);
            } else {
                szt = masterSpeciesList.get(node_id);
                //mExistingSpecies.put(node_id, szt);  JTC - not used

                //JTC - commented out biomass update using multiple of count,
                //this was incorrect, per Gary, count actually represents added 
                //biomass, not added species individuals
                //szt.setSpeciesCount(Math.max(0, szt.getSpeciesCount() + count));
                //szt.setCurrentBiomass(Math.max(0, szt.getCurrentBiomass() + szt.getPerSpeciesBiomass() * count));
                szt.setCurrentBiomass(Math.max(0, szt.getCurrentBiomass() + addedBiomass));
                szt.setBiomassUpdated(true);
                //mUpdateBiomass.put(node_id, szt);  //moved below
            }
        }

        /*JTC - note that this currently does not do anything.  getlPreyIndex and getlPredatorIndex
         return empty lists; species.setlPrey/Predator never set;*/
        for (SpeciesZoneType species : mNewSpecies.values()) {
            List<Integer> lPreyIndex = species.getlPreyIndex();
            List<Integer> lPredatorIndex = species.getlPredatorIndex();

            if (species.getlPrey() != null) {
                //Convert generic prey and predator list fetched to customized prey and predator list with local food web node indexes
                for (SpeciesType st : species.getlPrey()) {
                    for (int node_id : st.getNodeList()) {
                        if (masterSpeciesList.containsKey(node_id) || mNewSpecies.containsKey(node_id)) {
                            lPreyIndex.add(node_id);
                        }
                    }
                }
            }
            if (species.getlPredator() != null) {
                for (SpeciesType st : species.getlPredator()) {
                    for (int node_id : st.getNodeList()) {
                        if (masterSpeciesList.containsKey(node_id) || mNewSpecies.containsKey(node_id)) {
                            lPredatorIndex.add(node_id);
                        }
                    }
                }
            }
            //masterSpeciesList.put(species.getNodeIndex(), species);  //moved below
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
                species.setBiomassUpdated(false);
            }
        }

        // Insert new species using web services
        if (!mNewSpecies.isEmpty()) {
            try {
                //5/6/14, JTC, changed to new version of addMult; depricated orig addMultipleSpeciesType
                //and setParameters2
                addMultipleSpeciesType(new ArrayList<SpeciesZoneType>(mNewSpecies.values()), startTimestep,
                        false, networkOrManipulationId);
                //this.setParameters2(new ArrayList<>(mNewSpecies.values()), startTimestep, networkOrManipulationId);
            } catch (SimulationException ex) {
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
                updateBiomass(networkOrManipulationId, lNodeBiomass, startTimestep);
            } catch (SimulationException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        // JTC Update changes to existing species parameters using web services (also
        // resubmits biomass, but couldn't find a way to do params w/o biomass
        if (!mUpdateParams.isEmpty()) {
            try {
                increaseMultipleSpeciesType(new ArrayList<SpeciesZoneType>(mUpdateBiomass.values()),
                        startTimestep, false, networkOrManipulationId);
            } catch (SimulationException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        run(startTimestep, runTimestep, networkOrManipulationId);

        // get new predicted biomass
        try {
            //JTC - changed variable from "mSpecies = " to "mUpdateBiomass = "
            mUpdateBiomass = getBiomass(networkOrManipulationId, 0, startTimestep + runTimestep);
        } catch (SimulationException ex) {
            Log.println_e(ex.getMessage());
            return null;
        }
//        getBiomassInfo(networkOrManipulationId);

        //JTC - add loop to update persistent player species biomass information
        SpeciesZoneType updS;
        for (SpeciesZoneType priorS : masterSpeciesList.values()) {
            updS = mUpdateBiomass.get(priorS.nodeIndex);
            if (updS != null && updS.currentBiomass != 0) {
                masterSpeciesList.get(priorS.nodeIndex).setCurrentBiomass(Math.ceil(updS.getCurrentBiomass()));
            } else {
                zoneNodes.removeNode(priorS.nodeIndex);
            }
        }

        Log.printf("Total Time (Get Prediction): %.2f seconds", Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);

        return (HashMap) zoneNodes.getNodes();
    }

    public ManipulationResponse updateSystemParameters(int timestep, boolean isFirstManipulation, String networkOrManipulationId, List<ManipulatingParameter> sysParamList, List<ManipulatingNode> nodes) {
        long milliseconds = System.currentTimeMillis();

        SimpleManipulationRequest smr = new SimpleManipulationRequest();
        smr.setUser(user);
        smr.setBeginingTimestepIdx(timestep);
        if (isFirstManipulation) {
            smr.setNetworkId(networkOrManipulationId);
        } else {
            smr.setManipulationId(networkOrManipulationId);
        }
        smr.setTimestepsToRun(Integer.valueOf(propertiesConfig.getProperty("timestepsToRunDefault")));
        if (sysParamList != null) {
            smr.setSysParams(CopySystemParameter(sysParamList));
        } else {
            System.out.println("Error (updateSystemParameters): " + "System parameter is null.");
        }
        smr.setDescription("updateSystemParameters");
        smr.setSaveLastTimestepOnly(false);
        if (nodes != null) {
            smr.setManipulationModelNodes(nodes.toArray(new ManipulatingNode[]{}));
        }

        ManipulationResponse response = null;
        try {
            response = (ManipulationResponse) svc.executeManipulationRequest(smr);
            //TODO: Write web service call to database
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        String errMsg = response.getMessage();
        if (errMsg != null) {
            System.out.println("Error (updateSystemParameters): " + errMsg);
            return null;
        }

        System.out.println("Total Time (updateSystemParameters): " + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
        return response;
    }

    public ManipulationResponse modifyManipulatingParameters(List<SpeciesZoneType> speciesList, int timestep, boolean isFirstManipulation, String networkOrManipulationId) {

        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        ManipulatingNode[] nodes = new ManipulatingNode[speciesList.size()];
        int i = 0;
        for (SpeciesZoneType species : speciesList) {
            ManipulatingNode node = new ManipulatingNode();
            node.setTimestepIdx(timestep);
            node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
            node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
            node.setNodeIdx(species.getNodeIndex());
            node.setBeginingBiomass(species.getPerSpeciesBiomass() * species.getSpeciesCount());  //note: lacks divide by BIOMASS_SCALE
            node.setHasLinks(false);
            nodes[i++] = node;

            //5/6/14, JTC, replaced with updated version
            List<ManipulatingParameter> params = this.setSystemParameters(species, timestep);
            //List<ManipulatingParameter> params = this.getSystemParameter(species, timestep);
            sysParamList.addAll(params);
        }

        /*
         //carrying capacity
         ManipulatingParameter[] sysParams = new ManipulatingParameter[1];
         sysParams[0] = new ManipulatingParameter();
         sysParams[0].setParamType(ManipulatingParameterName.k.getManipulatingParameterType());
         sysParams[0].setParamName(ManipulatingParameterName.k.name());
         sysParams[0].setNodeIdx(species.getNodeIndex());
         sysParams[0].setParamIdx(ManipulatingParameterName.k.getManipulatingParameterIndex());
         sysParams[0].setParamValue(Double.valueOf(propertiesConfig.getProperty("carryingCapacityDefault")));
         sysParams[0].setTimestepIdx(timestep);
         */
        ManipulatingParameter[] sysParams = CopySystemParameter(sysParamList);

        SimpleManipulationRequest smr = new SimpleManipulationRequest();
        smr.setSaveLastTimestepOnly(true);
        smr.setUser(user);
        smr.setBeginingTimestepIdx(timestep);
        if (isFirstManipulation) {
            smr.setNetworkId(networkOrManipulationId);
        } else {
            smr.setManipulationId(networkOrManipulationId);
        }
        smr.setTimestepsToRun(Integer.valueOf(propertiesConfig.getProperty("timestepsToRunDefault")));
        smr.setManipulationModelNodes(nodes);

        smr.setSysParams(sysParams);
//        smr.setSysParams(this.CopySystemParameter(sParams));
//        smr.setSysParams((ManipulatingParameter[])sParams.toArray());
        smr.setDescription(" " + propertiesConfig.getProperty("increaseCarryingCapacityDescription") + " " + propertiesConfig.getProperty("carryingCapacityDefault"));
        smr.setSaveLastTimestepOnly(false);

        ManipulationResponse response = null;
        try {
            response = (ManipulationResponse) svc.executeManipulationRequest(smr);
            //TODO: Write web service call to database
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        String errMsg = response.getMessage();
        if (errMsg != null) {
            System.out.println("Error (modifyingManipulatingParameters): " + errMsg);
            return null;
        }
        return response;
    }

//    //HJR This is the actual function to be used
//    public void setFunctionalParameters(int timestep, String manipulation_id, HashMap<Short, Float> parametersList, int parameterType, int predatorId) {
//    	for (SpeciesZoneType szt : mSpecies.values()) {
//            List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
//            ManipulatingNode node = new ManipulatingNode();
//            node.setTimestepIdx(timestep);
//            node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
//            node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
//            node.setNodeIdx(szt.getNodeIndex());
//            node.setBeginingBiomass(szt.getCurrentBiomass());  //note: lacks divide by BIOMASS_SCALE
//            node.setHasLinks(false);
//            nodes.add(node);
//
//            List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
//            if (szt.getType() == SpeciesTypeEnum.PLANT) {
//            	if(parameterType == Constants.PARAMETER_X){
//            		Float paramValue = (float) 0;
//            		Short nodeIdx = (short) szt.getNodeIndex();
//            		if(nodeIdx != 0){
//            			paramValue = parametersList.get(nodeIdx);
//            			if(paramValue!=0){
//            				paramValue = paramValue/100;
//            			}
//            		}
//            		setFunctionalNodeParameter(szt.getNodeIndex(), 0,ManipulatingParameterName.x.getManipulatingParameterIndex(), paramValue, sParams,szt);
//	                Log.println("Updating Plant Parameter: [X] " +  szt.getName() + " NI " + szt.getNodeIndex() + " PV " + paramValue + " PT " + Constants.PARAMETER_X);
//            	}
//            }
//            if (szt.getType() == SpeciesTypeEnum.ANIMAL) {
//            		if(parameterType == Constants.PARAMETER_X_A){
//                		Float paramValue = (float) 0;
//                		Short nodeIdx = (short) szt.getNodeIndex();
//                		if(nodeIdx != 0){
//                			paramValue = parametersList.get(nodeIdx);
//                			if(paramValue!=0){
//                				paramValue = paramValue/100;
//                			}
//                		}
//			            setFunctionalNodeParameter(szt.getNodeIndex(), 0, ManipulatingParameterName.ax.getManipulatingParameterIndex(), paramValue, sParams,szt);
//			            System.out.println("Updating Animal Parameter: [X_A] " +  szt.getName() + " NI " + szt.getNodeIndex() + " PV " + paramValue + " PT " +  Constants.PARAMETER_X_A);
//            		}else if(parameterType == Constants.PARAMETER_E){
//            			if( predatorId == szt.getNodeIndex()){
//		            		for(Short prey: parametersList.keySet()){
//		            			Short preyIdx = prey;
//		            			Float paramValue = parametersList.get(preyIdx);
//		            			if(paramValue!=0){
//	                				paramValue = paramValue/100;
//	                			}
//		            			//(int predIdx, int preyIdx, int paramIdx, double paramValue, List<ManipulatingParameter> sParams )
//		            			setLinkParameter(szt.getNodeIndex(), preyIdx, ManipulatingParameterName.e.getManipulatingParameterIndex(), paramValue, sParams);
//				                System.out.println("Updating Animal Parameter: [E] " +  " NI " + szt.getNodeIndex() + " PI " + preyIdx +  " PV " + paramValue + " PT " +  Constants.PARAMETER_E);
//		            		}
//            			}
//            		}else if(parameterType == Constants.PARAMETER_D){
//            			if( predatorId == szt.getNodeIndex()){
//		            		for(Short prey: parametersList.keySet()){
//		            			Short preyIdx = prey;
//		            			Float paramValue = parametersList.get(preyIdx);
//		            			if(paramValue!=0){
//	                				paramValue = paramValue/100;
//	                			}
//		            			setLinkParameter(szt.getNodeIndex(), preyIdx, ManipulatingParameterName.d.getManipulatingParameterIndex(), paramValue, sParams);
//				                System.out.println("Updating Animal Parameter: [D] " +  szt.getName() + " NI " + szt.getNodeIndex() + " PI " + preyIdx +  " PV " + paramValue + " PT " +   Constants.PARAMETER_D);
//		            		}
//            			}
//            		}else if(parameterType == Constants.PARAMETER_Q){
//            			if( predatorId == szt.getNodeIndex()){
//		            		for(Short prey: parametersList.keySet()){
//		            			Short preyIdx = prey;
//		            			Float paramValue = parametersList.get(preyIdx);
//		            			if(paramValue!=0){
//	                				paramValue = paramValue/100;
//	                			}
//		            			setLinkParameter(szt.getNodeIndex(), preyIdx, ManipulatingParameterName.q.getManipulatingParameterIndex(), paramValue, sParams);
//				                System.out.println("Updating Animal Parameter: [Q] " +  szt.getName() + " NI " + szt.getNodeIndex() + " PI " + preyIdx +  " PV " + paramValue + " PT " +   Constants.PARAMETER_Q);
//			            	}
//            			}
//            		}else if(parameterType == Constants.PARAMETER_A){
//            			if( predatorId == szt.getNodeIndex()){
//		            		for(Short prey: parametersList.keySet()){
//		            			Short preyIdx = prey;
//		            			Float paramValue = parametersList.get(preyIdx);
//		            			if(paramValue!=0){
//	                				paramValue = paramValue/100;
//	                			}
//		            			setLinkParameter(szt.getNodeIndex(), prey, ManipulatingParameterName.a.getManipulatingParameterIndex(), paramValue, sParams);
//				                System.out.println("Updating Animal Parameter: [A] " +  szt.getName() + " NI " + szt.getNodeIndex() + " PI " + preyIdx +  " PV " + paramValue + " PT " +   Constants.PARAMETER_A);
//			            	}
//            			}
//            		}
//            	}
//
//            updateSystemParameters(timestep, false, manipulation_id, sParams, nodes);
//        }
//    }
    public void getNetworkInfo() {
        try {
            NetworkInfoRequest request = new NetworkInfoRequest();
            request.setUser(user);

            NetworkInfoResponse response = (NetworkInfoResponse) svc.executeRequest(request);
            //TODO: Write web service call to database
            if (response.getMessage() == null) {
                System.out.println("\nNetwork info:");
                NetworkInfo info[] = response.getNetworkInfo();
                for (int i = 0; i < info.length; i++) {
                    System.out.println(info[i].getNetworkName() + " = " + info[i].getNetworkId());
                }
            } else {
                System.out.println("Error: " + response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUserManipulations() {
        // list manipulations of user
        try {
            ManipulationInfoRequest req = new ManipulationInfoRequest();
            req.setUser(user);

            ManipulationInfoResponse res = (ManipulationInfoResponse) svc.executeRequest(req);
            //TODO: Write web service call to database
            ManipulationInfo[] infos = res.getManipulationInfos();
            for (int i = 0; i < infos.length; i++) {
                System.out.println("\n\nManipulated network: " + infos[i].getNetworkName() + "\nManipulation id: " + infos[i].getManipulationId());
            }
        } catch (Exception e) {
            Log.println_e("Error:" + e.getMessage());
        }
    }

    //10/16/14, gary's CSV method updates
    public void getBiomassInfo(String manipulation_id) {
        long milliseconds = System.currentTimeMillis();

        try {
            int curPage = 1;
            int curTimestep = -1;
            ManipulationTimestepInfoResponse response;

            do {
                ManipulationTimestepInfoRequest req = new ManipulationTimestepInfoRequest();
                req.setManipulationId(manipulation_id);
                req.setIsNodeTimestep(true); // getting node time step
                req.setNodeIdx(0); // set node index to 3
                req.setTimestep(0); // set time step to 5
                req.setPage(curPage);

                response = (ManipulationTimestepInfoResponse) svc.executeRequest(req);
                ManipulationTimestepInfo[] infos = response.getManipulationTimestepInfos();

                for (ManipulationTimestepInfo info : infos) {
                    if (info.getTimestepIdx() != curTimestep) {
                        curTimestep = info.getTimestepIdx();
                        System.out.println("--[" + (curTimestep > 0 ? curTimestep : "Initial") + "]--");
                        System.out.println("[ID] - " + String.format("%-25s", "Node Name") + " Biomass");
                    }

                    String name = info.getNodeName();
                    //1/27/15 - JTC - limit precision of biomass to int (=>1 if not zero)
                    System.out.println("[" + String.format("%2d", info.getNodeIdx())  + "] - " + 
                            String.format("%-25s", name == null ? "Unknown" : name) 
                            + " " + Math.ceil(info.getBiomass()));
                }
            } while (curPage++ < response.getPageAvailable());
        } catch (RemoteException ex) {
            System.err.println("Error (getBiomassInfo): " + ex.getMessage());
        }

        logTime("Total Time (Get Biomass Info): " + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
    }

    public void saveBiomassCSVFile(String manipulation_id, String filename) {
        final String name = filename, extension = ".csv";

        // Determine filename
        String[] files = new File(Constants.CSV_SAVE_PATH).list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(name) && name.endsWith(extension);
            }
        });

        String csvFilename = name;

        if (files.length > 0) {
            int[] temp = new int[files.length];

            for (int i = 0; i < temp.length; i++) {
                String lastFilename = files[i].replaceFirst(name, "").replaceFirst("_", "");

                try {
                    temp[i] = Integer.parseInt(lastFilename.substring(0, lastFilename.indexOf(extension)));
                } catch (NumberFormatException ex) {
                    temp[i] = 0;
                }
            }

            Arrays.sort(temp);

            csvFilename += "_" + (temp[temp.length - 1] + 1);
        }

        csvFilename += extension;

        try {
            String biomassCSV = getBiomassCSVString(manipulation_id);

            if (!biomassCSV.isEmpty()) {
                biomassCSV = "Manipulation ID: " + manipulation_id + "\n\n" + biomassCSV;

                PrintStream p = new PrintStream(new FileOutputStream(Constants.CSV_SAVE_PATH + csvFilename));
                p.println(biomassCSV);
                p.close();

                Log.println("Saved CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
            } else {
                Log.println_e("CSV Not Found!");
            }
        } catch (FileNotFoundException ex) {
            Log.println_e("Failed to save CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
        }
    }

    public void saveBiomassCSVFile(String manipulation_id) {
        saveBiomassCSVFile(manipulation_id, "WoB_Data");
    }

    public String getBiomassCSVString(String manipulation_id) {
        return getBiomassCSVString(manipulation_id, Constants.BIOMASS_SCALE);
    }

    public String getBiomassCSVString(String manipulation_id, float scale) {
        String biomassCSV = "";
        Map<String, List<Double>> biomassData = new HashMap<String, List<Double>>();
        //create interim solution for missing timestep info between subsequent infoRequests
        //NOTE!!  this assumes that no nodes are added in the middle of the simulation!!
        //(see "Zero Pad" notations for example)
        List<Integer> nodeList = new ArrayList<Integer>();
        int curNodeIdx, nodeOffset;
        int missingTimestep = -1, missingNodeIdx = -1;

        try {
            int curPage = 1;
            int curTimestep = -1;
            ManipulationTimestepInfoResponse response;

            do {
                ManipulationTimestepInfoRequest req = new ManipulationTimestepInfoRequest();
                req.setManipulationId(manipulation_id);
                req.setIsNodeTimestep(true); // getting node time step
                req.setNodeIdx(0); // set node index to 3
                req.setTimestep(0); // set time step to 5
                req.setPage(curPage);

                response = (ManipulationTimestepInfoResponse) svc.executeRequest(req);
                ManipulationTimestepInfo[] infos = response.getManipulationTimestepInfos();

                //deal with missing timestep issue
                //if first page, set up missing node/timestep arrays; loop through 
                //infos until all new nodes identified
                if (curPage == 1) {
                    for (ManipulationTimestepInfo info : infos) {
                        curNodeIdx = info.getNodeIdx();
                        if (!nodeList.contains(curNodeIdx)) {
                            nodeList.add(curNodeIdx);
                        } else {
                            break;  //if all found, then break out of for loop
                        }
                    }

                    //on subsequent infoRequests, flag affected node/timestep
                } else {
                    nodeOffset = nodeList.indexOf(infos[0].getNodeIdx());
                    curTimestep = infos[0].getTimestepIdx();
                    //affected node is immediately preceding node; timestep
                    //may be current or previous timestep (if curnode is node 0)
                    if (nodeOffset > 0) {
                        nodeOffset = nodeOffset - 1;
                        missingTimestep = curTimestep;
                    } else {
                        nodeOffset = nodeList.size() - 1;
                        missingTimestep = curTimestep - 1;
                    }
                    missingNodeIdx = nodeList.get(nodeOffset);
                }

                for (ManipulationTimestepInfo info : infos) {
                    curTimestep = info.getTimestepIdx();
                    curNodeIdx = info.getNodeIdx();

                    //add nodes to list in the order that they are received from infos
                    String name = info.getNodeName().replaceAll(",", " ") + " [" + curNodeIdx + "]";

                    //estimate missing timestep info
                    if (missingTimestep != -1 && curNodeIdx == missingNodeIdx) {
                        double newBiomass = info.getBiomass() * scale;
                        double priorBiomass = biomassData.get(name).
                                get(missingTimestep - 1);
                        //add missing data by averaging new and prior
                        biomassData.get(name).add((newBiomass + priorBiomass) / 2.0);
                        System.out.printf("Inserting estimated biomass, node = %d, timestep = %d\n",
                                curNodeIdx, missingTimestep);
                        //reset missingTimestep flag
                        missingTimestep = -1;
                    }

                    if (!biomassData.containsKey(name)) {
                        List<Double> biomassList = new ArrayList<Double>();
                        // Zero Pad Before-Existence Timesteps
                        for (int t = 0; t < curTimestep; t++) {
                            biomassList.add(-1d);
                        }
                        biomassData.put(name, biomassList);
                    }

                    biomassData.get(name).add(info.getBiomass() * scale);
                }
            } while (curPage++ < response.getPageAvailable());

            /* Convert to CSV String */
            int maxTimestep = curTimestep;
            // Create Timestep Labels
            for (int i = 1; i <= maxTimestep; i++) {
                biomassCSV += "," + i;
            }
            // Alphabetize Node Labels
            List<String> nodeLabels = new ArrayList<String>(biomassData.keySet());
            Collections.sort(nodeLabels);
            // Convert Node Data From List to String
            float extinction = 1.E-15f;
            for (String label : nodeLabels) {
                List<Double> biomassList = biomassData.get(label);
                String tempStr = label;

                for (int i = 1; i < maxTimestep; i++) {
                    tempStr += ",";

                    double biomass = biomassList.get(i);

                    if (biomass > 0) {
                        tempStr += biomass > extinction ? Math.ceil(biomass) : 0;
                    }
                }

                biomassCSV += "\n" + tempStr;
            }
        } catch (RemoteException ex) {
            System.err.println("Error (getBiomassCSVString): " + ex.getMessage());
        }

        return biomassCSV;
    }

    /**
     * Takes already created CSV string as parameter and adds additional header
     * information. 4/30/14, JTC.
     *
     * @param manipulation_id
     * @param header
     * @param biomassCSV
     */
    public void saveBiomassCSVFileSimJob(String manipulation_id, String header,
            String biomassCSV) {
        //9/16/14 - jtc - had to change WOB to WoB for replaceFirst to work.
        //cannot figure out why this used to work!
        final String name = "WoB_Data", extension = ".csv";

        // Determine filename
        String[] files = new File(Constants.CSV_SAVE_PATH).list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(name) && name.endsWith(extension);
            }
        });

        String csvFilename = name;

        if (files.length > 0) {
            int[] temp = new int[files.length];

            for (int i = 0; i < temp.length; i++) {
                String lastFilename = files[i].replaceFirst(name, "").replaceFirst("_", "");

                try {
                    temp[i] = Integer.parseInt(lastFilename.substring(0, lastFilename.indexOf(extension)));
                } catch (NumberFormatException ex) {
                    temp[i] = 0;
                }
            }

            Arrays.sort(temp);

            csvFilename += "_" + (temp[temp.length - 1] + 1);
        }

        csvFilename += extension;

        try {

            if (!biomassCSV.isEmpty()) {
                biomassCSV = "Manipulation ID: " + manipulation_id + "\n\n" + biomassCSV;
                if (!header.isEmpty()) {
                    biomassCSV = header + "  " + biomassCSV;
                }

                PrintStream p = new PrintStream(new FileOutputStream(Constants.CSV_SAVE_PATH + csvFilename));
                p.println(biomassCSV);
                p.close();

                Log.println("Saved CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
            } else {
                Log.println_e("CSV Not Found!");
            }
        } catch (FileNotFoundException e) {
            Log.println_e("Failed to save CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
        }
    }
//end of Gary's CSV updates  

    /* replaced with Gary's new code
     public void getBiomassInfo(String MANIPULATION_ID) {
     long milliseconds = System.currentTimeMillis();

     boolean finished = false;
     int curPage = 1;
     int curTimestep = -1;

     try {
     while (!finished) {
     ManipulationTimestepInfoRequest req = new ManipulationTimestepInfoRequest();
     req.setManipulationId(MANIPULATION_ID);
     req.setIsNodeTimestep(true); // getting node time step
     req.setNodeIdx(0); // set node index to 3
     req.setTimestep(0); // set time step to 5
     //                req.setPage(curPage);

     ManipulationTimestepInfoResponse response = (ManipulationTimestepInfoResponse) svc.executeRequest(req);
     ManipulationTimestepInfo[] infos = response.getManipulationTimestepInfos();

     curPage = response.getCurPage();
     if (curPage >= response.getPageAvailable()) {
     finished = true;
     } else {
     curPage++;
     }

     for (int i = 0; i < infos.length; i++) {
     if (infos[i].getTimestepIdx() != curTimestep) {
     System.out.println("--[" + infos[i].getTimestepIdx() + "]--");
     curTimestep = infos[i].getTimestepIdx();
     }

     System.out.println("Node[" + infos[i].getNodeIdx()
     + "] + Node name[" + infos[i].getNodeName() + "]:"
     + infos[i].getBiomass());
     }
     }
     } catch (Exception e) {
     Log.println_e ("Error (getBiomassInfo): " + e.getMessage());
     }

     logTime("Total Time (Get Biomass Info): " + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
     }
     */
    /*
     public void saveBiomassCSVFile(String manipulation_id, String filename) {
     final String name = filename, extension = ".csv";

     // Determine filename
     String[] files = new File(Constants.CSV_SAVE_PATH).list(new FilenameFilter() {
     @Override
     public boolean accept(File dir, String fullname) {
     return fullname.startsWith(name) && fullname.endsWith(extension);
     }
     });

     String csvFilename = name;

     if (files.length > 0) {
     int[] temp = new int[files.length];

     for (int i = 0; i < temp.length; i++) {
     String lastFilename = files[i].replaceFirst(name, "").replaceFirst("_", "");

     try {
     temp[i] = Integer.parseInt(lastFilename.substring(0, lastFilename.indexOf(extension)));
     } catch (NumberFormatException ex) {
     temp[i] = 0;
     }
     }

     Arrays.sort(temp);

     csvFilename += "_" + (temp[temp.length - 1] + 1);
     }

     csvFilename += extension;

     try {
     String biomassCSV = getBiomassCSVString(manipulation_id);

     if (!biomassCSV.isEmpty()) {
     biomassCSV = "Manipulation ID: " + manipulation_id + "\n\n" + biomassCSV;
     PrintStream p = new PrintStream(new FileOutputStream(Constants.CSV_SAVE_PATH + csvFilename));
     p.println(biomassCSV);
     p.close();

     Log.println("Saved CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
     } else {
     Log.println_e("CSV Not Found!");
     }
     } catch (FileNotFoundException e) {
     Log.println_e("Failed to save CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
     }
     }
     */

    /*
     public void saveBiomassCSVFile(String manipulation_id) {
     saveBiomassCSVFile(manipulation_id, "WoB_Data");
     }
     */

    /*4/30/14, JTC, getBiomassCSVString() intermittently did not create
     CSV file - failed on executeRequest.  Due to error in multiple pages config.
     Also returns null name value if > 1 page.  Corrections:
     (1) only call req.setPage(curPage) if curPage > 1
     (2) only call setNodeIdx(0) and setTimestep(0) if curPage == 1
     (3) obtain node name from GameServer rather than infos[]. */
    /*
     public String getBiomassCSVString(String manipulation_id) {
     String biomassCSV = "";
     boolean finished = false;
     int curPage = 1;
     int curTimestep = -1;
     HashMap<String, List<Double>> biomassData = new HashMap<String, List<Double>>();
     int currentDay = 0;

     try {
     while (!finished) {
     ManipulationTimestepInfoRequest req = new ManipulationTimestepInfoRequest();
     req.setManipulationId(manipulation_id);
     req.setIsNodeTimestep(true); // getting node time step
     //9/29/14, JTC, simplified further:  setPage works as long as
     //setNodeIdx(0) and setTimestep(0) are ignored completely.
     req.setPage(curPage);

     if (curPage == 1) {
     req.setPage(curPage);
     }

     ManipulationTimestepInfoResponse response = (ManipulationTimestepInfoResponse) svc.executeRequest(req);
     ManipulationTimestepInfo[] infos = response.getManipulationTimestepInfos();

     curPage = response.getCurPage();
     if (curPage >= response.getPageAvailable()) {
     finished = true;
     } else {
     curPage++;
     }

     for (int i = 0; i < infos.length; i++) {
     if (infos[i].getTimestepIdx() != curTimestep) {
     curTimestep = infos[i].getTimestepIdx();
     currentDay++;
     }

     //JTC, obtaining species name info from SpeciesTable instead of 
     //infos[]; getting null values from infos when > 1 page 
     String name = "\"" + ServerResources.getSpeciesTable().
     getSpeciesTypeByNodeID(infos[i].getNodeIdx()).getName()
     + " [" + infos[i].getNodeIdx() + "]" + "\"";
     //String name = "\"" + infos[i].getNodeName() + " [" + infos[i].getNodeIdx() + "]" + "\"";
     double biomass = infos[i].getBiomass() * Constants.BIOMASS_SCALE;

     if (!biomassData.containsKey(name)) {
     List<Double> biomassList = new ArrayList<Double>();

     for (int day = 0; day < curTimestep; day++) {
     biomassList.add(0.0);
     }

     biomassData.put(name, biomassList);
     }

     List<Double> biomassList = biomassData.get(name);
     biomassList.add(biomass);
     }
                
     //JTC - this does not always create biomassLists of the same length.
     }
     } catch (Exception e) {
     Log.println_e ("Error in getBiomassCSVString getting manipulation Info, page ("
     + curPage + "): " + e.getMessage());
     }

     try {
     currentDay--; // Ignore Last Day

     for (int i = 1; i < currentDay; i++) {
     biomassCSV += "," + i;
     }

     for (String name : biomassData.keySet()) {
     List<Double> biomassList = biomassData.get(name);
     String tempStr = name;

     for (int i = 1; i < currentDay; i++) {
     double biomass = biomassList.get(i);

     if (i > 0 && biomassList.get(i - 1) > 1.E-15 && biomass <= 1.E-15) {
     tempStr += ",0";
     } else if ((i == 0 || (i > 0 && biomassList.get(i - 1) <= 1.E-15)) && biomass <= 1.E-15) {
     tempStr += ",";
     } else {
     tempStr += "," + biomass;
     }
     }

     biomassCSV += "\n" + tempStr;
     }
     } catch (Exception e) {
     Log.println_e ("Error in getBiomassCSVString building CSV: " + e.getMessage());
     }

     return biomassCSV;
     }
     */
    /**
     * Takes already created CSV string as parameter and adds additional header
     * information. 4/30/14, JTC.
     *
//     * @param manipulation_id
//     * @param header
//     * @param biomassCSV
     */
    /*
     public void saveBiomassCSVFileSimJob(String manipulation_id, String header, 
     String biomassCSV) {
     final String name = "WOB_Data", extension = ".csv";

     // Determine filename
     String[] files = new File(Constants.CSV_SAVE_PATH).list(new FilenameFilter() {
     @Override
     public boolean accept(File dir, String name) {
     return name.startsWith(name) && name.endsWith(extension);
     }
     });

     String csvFilename = name;

     if (files.length > 0) {
     int[] temp = new int[files.length];

     for (int i = 0; i < temp.length; i++) {
     String lastFilename = files[i].replaceFirst(name, "").replaceFirst("_", "");

     try {
     temp[i] = Integer.parseInt(lastFilename.substring(0, lastFilename.indexOf(extension)));
     } catch (NumberFormatException ex) {
     temp[i] = 0;
     }
     }

     Arrays.sort(temp);

     csvFilename += "_" + (temp[temp.length - 1] + 1);
     }

     csvFilename += extension;

     try {

     if (!biomassCSV.isEmpty()) {
     biomassCSV = "Manipulation ID: " + manipulation_id + "\n\n" + biomassCSV;
     if (!header.isEmpty()) {
     biomassCSV = header + "  " + biomassCSV;
     }

     PrintStream p = new PrintStream(new FileOutputStream(Constants.CSV_SAVE_PATH + csvFilename));
     p.println(biomassCSV);
     p.close();

     Log.println("Saved CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
     } else {
     Log.println_e("CSV Not Found!");
     }
     } catch (FileNotFoundException e) {
     Log.println_e("Failed to save CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
     }
     }
     */
    public ManipulationResponse run(int beginingTimestep, int timestepsToRun, String netId, boolean isNetwork) {
        long milliseconds = System.currentTimeMillis();

        SimpleManipulationRequest smr = new SimpleManipulationRequest();
        smr.setUser(user);
        smr.setBeginingTimestepIdx(beginingTimestep);
        if (isNetwork) {
            smr.setNetworkId(netId);
        } else {
            smr.setManipulationId(netId);
        }

//        smr.setManipulationModelNodes(nodes);
        smr.setTimestepsToRun(timestepsToRun);
        smr.setDescription("Serengetti sub foodweb stability test - netId:" + netId);
        smr.setSaveLastTimestepOnly(false);
//        smr.setSysParams(sysParams);

        ManipulationResponse response = null;
        try {
            response = (ManipulationResponse) svc.executeManipulationRequest(smr);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        logTime("Total Time (Run): " + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");

        String errMsg = response.getMessage();
        if (errMsg != null) {
            System.out.println("Error (run): " + errMsg);
            return null;
        } else {
            System.out.println("manpId:" + response.getManipulationId());
        }

        return response;
    }

    public void run(int startTimestep, int runTimestep, String manipulationId) {
        long milliseconds = System.currentTimeMillis();

        try {
            SimpleManipulationRequest smr = new SimpleManipulationRequest();
            smr.setSaveLastTimestepOnly(true);
            User user = new User();
            user.setUsername("beast");
            smr.setUser(user);
            smr.setBeginingTimestepIdx(startTimestep);
            smr.setTimestepsToRun(runTimestep);
            smr.setManipulationId(manipulationId);
            smr.setSaveLastTimestepOnly(false);

            ManipulationResponse response = (ManipulationResponse) svc.executeManipulationRequest(smr);
            String errMsg = response.getMessage();
            if (errMsg != null) {
                System.out.println("Error (run): " + errMsg);
            } else {
                System.out.println("Manipulation was successfully operated with manipulation id " + response.getManipulationId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.printf("Total Time (Run %d-%d): %.2f seconds", startTimestep, startTimestep + runTimestep, Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
    }

    public String createAndRunSeregenttiSubFoodweb(int nodeList[], String foodwebName,
            int beginingTimestep, int timestepsToRun, boolean overwrite)
            throws SimulationException {
        long milliseconds = System.currentTimeMillis();

        if (nodeList == null) {
            return "nodeList is null";
        }
        String netId = createSeregenttiSubFoodweb(foodwebName, nodeList, overwrite);
        System.out.println("netId:" + netId);
        ManipulationResponse mr = this.run(beginingTimestep, timestepsToRun, netId, true);

//            getBiomassInfo(mr.getManipulationId());
//            deleteManipulation(mr.getManipulationId());
        if (mr == null || mr.getMessage() != null) {
            return null;
        }
        logTime("Total Time (Create and Run Serengeti Sub-Food Web): " + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
        return mr.getManipulationId();
    }

    //jtc 12/22/14, version to return netId (for later deletion) as well as manip Id
    public SimulationIds createAndRunSeregenttiSubFoodwebForSimJob(int nodeList[], String foodwebName,
            int beginingTimestep, int timestepsToRun, boolean overwrite)
            throws SimulationException {
        long milliseconds = System.currentTimeMillis();

        if (nodeList == null) {
            return null;
        }
        String netId = createSeregenttiSubFoodweb(foodwebName, nodeList, overwrite);
        System.out.println("netId:" + netId);
        ManipulationResponse mr = this.run(beginingTimestep, timestepsToRun, netId, true);

//            getBiomassInfo(mr.getManipulationId());
//            deleteManipulation(mr.getManipulationId());
        if (mr == null || mr.getMessage() != null) {
            return null;
        }
        logTime("Total Time (Create and Run Serengeti Sub-Food Web): " + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
        SimulationIds simIds = new SimulationIds(mr.getManipulationId(), mr.getNetworksId());
        return simIds;
    }

    /*9/29/14, JTC, deprecated
     //HJR
     public String setFunctionalNodeParameter(int nodeIdx, int preyIdx, int paramIdx, double paramValue, List<ManipulatingParameter> sParams, SpeciesZoneType szt) {
     ManipulatingParameter param = new ManipulatingParameter();
     List<Integer> predatorIndex = szt.getlPredatorIndex();
     List<Integer> preyIndex = szt.getlPreyIndex();
     if (paramIdx == ManipulatingParameterName.x.getManipulatingParameterIndex()) {
     if (paramValue <= 0) {
     return "Metabolic Rate for Plants should be bigger than 0";
     }
     param.setParamType(ManipulatingParameterName.x.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.x.name());
     param.setParamIdx(ManipulatingParameterName.x.getManipulatingParameterIndex());
     param.setNodeIdx(nodeIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.ax.getManipulatingParameterIndex()) {
     if (paramValue <= 0) {
     return "Metabolic Rate for Animals should be bigger than 0";
     }
     param.setParamType(ManipulatingParameterName.ax.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.ax.name());
     param.setParamIdx(ManipulatingParameterName.ax.getManipulatingParameterIndex());
     param.setNodeIdx(nodeIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.e.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Assimilation Efficiency should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.e.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.e.name());
     param.setParamIdx(ManipulatingParameterName.e.getManipulatingParameterIndex());
     param.setNodeIdx(nodeIdx);
     param.setPreyIdx(preyIdx);
     param.setPredIdx(nodeIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.d.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Predator Interference should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.d.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.d.name());
     param.setParamIdx(ManipulatingParameterName.d.getManipulatingParameterIndex());
     param.setNodeIdx(nodeIdx);
     param.setPreyIdx(preyIdx);
     param.setPredIdx(nodeIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.q.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Functional Response Control should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.q.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.q.name());
     param.setParamIdx(ManipulatingParameterName.q.getManipulatingParameterIndex());
     param.setNodeIdx(nodeIdx);
     param.setPreyIdx(preyIdx);
     param.setPredIdx(nodeIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.a.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Relative Half Saturation Density should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.a.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.a.name());
     param.setParamIdx(ManipulatingParameterName.a.getManipulatingParameterIndex());
     param.setNodeIdx(nodeIdx);
     param.setPreyIdx(preyIdx);
     param.setPredIdx(nodeIdx);
     param.setParamValue(paramValue);
     } else {
     return "that type of node parameter is not supported yet";
     }

     sParams.add(param);

     return null;
     }

     public String setLinkParameter(int predIdx, int preyIdx, int paramIdx, double paramValue, List<ManipulatingParameter> sParams) {
     ManipulatingParameter param = new ManipulatingParameter();

     if (paramIdx == ManipulatingParameterName.e.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Assimilation efficiency rate should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.e.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.e.name());
     param.setParamIdx(ManipulatingParameterName.e.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.a.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Relative Half Saturation Density should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.a.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.a.name());
     param.setParamIdx(ManipulatingParameterName.a.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.q.getManipulatingParameterIndex()) {
     param.setParamType(ManipulatingParameterName.q.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.q.name());
     param.setParamIdx(ManipulatingParameterName.q.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.d.getManipulatingParameterIndex()) {
     param.setParamType(ManipulatingParameterName.d.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.d.name());
     param.setParamIdx(ManipulatingParameterName.d.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.b0.getManipulatingParameterIndex()) {
     param.setParamType(ManipulatingParameterName.b0.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.b0.name());
     param.setParamIdx(ManipulatingParameterName.b0.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     } else {
     return "that type of link parameter is not supported yet";
     }

     sParams.add(param);

     return null;
     }

     public String setLinkParameter(int predIdx, int preyIdx, int paramIdx, double paramValue, int tsIdx, List<ManipulatingParameter> sParams) {
     ManipulatingParameter param = new ManipulatingParameter();

     if (paramIdx == ManipulatingParameterName.e.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Assimilation efficiency rate should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.e.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.e.name());
     param.setParamIdx(ManipulatingParameterName.e.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setTimestepIdx(tsIdx);
     param.setParamValue(paramValue);
     } else if (paramIdx == ManipulatingParameterName.a.getManipulatingParameterIndex()) {
     if (paramValue < 0 || paramValue > 1) {
     return "Relative Half Saturation Density should be between 0 and 1";
     }
     param.setParamType(ManipulatingParameterName.a.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.a.name());
     param.setParamIdx(ManipulatingParameterName.a.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     param.setTimestepIdx(tsIdx);
     } else if (paramIdx == ManipulatingParameterName.q.getManipulatingParameterIndex()) {
     param.setParamType(ManipulatingParameterName.q.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.q.name());
     param.setParamIdx(ManipulatingParameterName.q.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     param.setTimestepIdx(tsIdx);
     } else if (paramIdx == ManipulatingParameterName.d.getManipulatingParameterIndex()) {
     param.setParamType(ManipulatingParameterName.d.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.d.name());
     param.setParamIdx(ManipulatingParameterName.d.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     param.setTimestepIdx(tsIdx);
     } else if (paramIdx == ManipulatingParameterName.b0.getManipulatingParameterIndex()) {
     param.setParamType(ManipulatingParameterName.b0.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.b0.name());
     param.setParamIdx(ManipulatingParameterName.b0.getManipulatingParameterIndex());
     param.setPredIdx(predIdx);
     param.setPreyIdx(preyIdx);
     param.setParamValue(paramValue);
     param.setTimestepIdx(tsIdx);
     } else {
     return "that type of link parameter is not supported yet";
     }

     sParams.add(param);

     return null;
     }
     */

    /*5/6/14, JTC, deprecated
     private List<ManipulatingParameter> getSystemParameter(SpeciesZoneType species, int timestepIdx) {

     SpeciesTypeEnum type = species.getType();
     int nodeIdx = species.getNodeIndex();

     List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();

     if (type == SpeciesTypeEnum.PLANT) {
     // Carrying capacity(k) and GrowthRate(r) are only effective when species is plant
     // Higher Carrying capacity means higher biomass
     // for example, if carrying capacity is 10, maximum biomass of species is 10.
     // Higher growth rate means that species with higher growth rate will gain biomass faster.
     // Metabolic rate (x) are effective for both animals and plants
     // higher metabolic rate means that biomass of species will decrease compared to other species

     ManipulatingParameter param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.k.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.k.name());
     param.setNodeIdx(nodeIdx);
     param.setParamIdx(ManipulatingParameterName.k.getManipulatingParameterIndex());
     // parameter k, r, x can't have negative value. if they have negative value, it means that data is not assigned yet.)
     double paramKVal = species.getParamK();
     if (paramKVal < 0) {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("carryingCapacityDefault")));
     } else {
     param.setParamValue(paramKVal);
     }

     param.setTimestepIdx(timestepIdx);
     sParams.add(param);

     param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.r.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.r.name());
     param.setNodeIdx(nodeIdx);
     param.setParamIdx(ManipulatingParameterName.r.getManipulatingParameterIndex());
     double paramRVal = species.getParamR();
     if (paramRVal < 0) {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("growthRateDefault")));
     } else {
     param.setParamValue(paramRVal);
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);

     param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.x.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.x.name());
     param.setNodeIdx(nodeIdx);
     param.setParamIdx(ManipulatingParameterName.x.getManipulatingParameterIndex());
     double paramXVal = species.getParamX();  //corrected 4/6/14, JTC: prev getParamR();
     if (paramXVal < 0) {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("metabolicRateDefault")));
     } else {
     param.setParamValue(paramXVal);
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);

     } else if (type == SpeciesTypeEnum.ANIMAL) {

     // Metabolic rate (x) are effective for both animals and plants
     // higher metabolic rate means that biomass of species will decrease compared to other species
     // Assimilation efficiency (e) is only available for animals.
     // higher assimilation efficiency means that biomass of species will increase.
     ManipulatingParameter param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.x.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.x.name());
     param.setNodeIdx(nodeIdx);
     param.setParamIdx(ManipulatingParameterName.x.getManipulatingParameterIndex());
     double paramXVal = species.getParamX();  //corrected 4/6/14, JTC: prev getParamR();
     if (paramXVal < 0) {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("metabolicRateDefault")));
     } else {
     param.setParamValue(paramXVal);
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);

     List<Integer> preys = species.getlPreyIndex();
     if (preys != null) {
     for (Integer prey : preys) {
     param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.e.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.e.name());
     param.setPredIdx(nodeIdx);
     param.setPreyIdx(prey);
     param.setParamIdx(ManipulatingParameterName.e.getManipulatingParameterIndex());
     if (species.getParamE() != null && species.getParamE(prey) != null) {
     param.setParamValue(species.getParamE(prey).getParamValue());
     } else {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("assimilationEfficiencyDefault")));
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);
     }

     for (Integer prey : preys) {
     param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.a.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.a.name());
     param.setPredIdx(nodeIdx);
     param.setPreyIdx(prey);
     param.setParamIdx(ManipulatingParameterName.a.getManipulatingParameterIndex());
     if (species.getParamA() != null && species.getParamA(prey) != null) {
     param.setParamValue(species.getParamA(prey).getParamValue());
     } else {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("relativeHalfSaturationDensityDefault")));
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);
     }

     for (Integer prey : preys) {
     param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.q.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.q.name());
     param.setPredIdx(nodeIdx);
     param.setPreyIdx(prey);
     param.setParamIdx(ManipulatingParameterName.q.getManipulatingParameterIndex());
     if (species.getParamQ() != null && species.getParamQ(prey) != null) {
     param.setParamValue(species.getParamQ(prey).getParamValue());
     } else {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("functionalResponseControlParameterDefault")));
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);
     }

     for (Integer prey : preys) {
     param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.d.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.d.name());
     param.setPredIdx(nodeIdx);
     param.setPreyIdx(prey);
     param.setParamIdx(ManipulatingParameterName.d.getManipulatingParameterIndex());
     if (species.getParamD() != null && species.getParamD(prey) != null) {
     param.setParamValue(species.getParamD(prey).getParamValue());
     } else {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("predatorInterferenceDefault")));
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);
     }

     //4/6/14, JTC, adding paramY
     for (Integer prey : preys) {
     param = new ManipulatingParameter();
     param.setParamType(ManipulatingParameterName.y.getManipulatingParameterType());
     param.setParamName(ManipulatingParameterName.y.name());
     param.setPredIdx(nodeIdx);
     param.setPreyIdx(prey);
     param.setParamIdx(ManipulatingParameterName.y.getManipulatingParameterIndex());
     if (species.getParamY() != null && species.getParamY(prey) != null) {
     param.setParamValue(species.getParamY(prey).getParamValue());
     } else {
     param.setParamValue(Double.valueOf(propertiesConfig.getProperty("maximumIngestionRateDefault")));
     }
     param.setTimestepIdx(timestepIdx);
     sParams.add(param);
     }
     }
     }

     return sParams;
     }
     */

    /* 9/29/14, JTC, deprecated
     public ManipulatingParameter[] getSystemParameterInfos(String manpId) {
     ManipulationParameterInfoRequest request = new ManipulationParameterInfoRequest();
     request.setUser(user);
     request.setManipulationId((manpId));
     request.setMode(SEARCH_MODE);

     ManipulationParameterInfoResponse response = new ManipulationParameterInfoResponse();
     try {
     //            response = (ManipulationTimestepInfoResponse) svc.executeRequest(req);
     response = (ManipulationParameterInfoResponse) svc.executeRequest(request);
     //TODO: Write web service call to database
     } catch (RemoteException e) {
     e.printStackTrace();
     }
     String errMsg = response.getMessage();
     if (errMsg != null) {
     Log.println_e ("Error:" + errMsg);
     return null;
     }
     return response.getManipulationInfos();
     }
     */

    /* 9/29/14, jtc, depricating, no longer called
     public void setParameter(int timestep, SpeciesZoneType species, String manipulation_id, short parameter, double value) {
     List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
     ManipulatingNode node = new ManipulatingNode();
     node.setTimestepIdx(timestep);
     node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
     node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
     node.setNodeIdx(species.getNodeIndex());
     node.setBeginingBiomass(species.getCurrentBiomass() / Constants.BIOMASS_SCALE);
     node.setHasLinks(false);
     nodes.add(node);

     List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();

     if (parameter == Constants.PARAMETER_X) {
     setNodeParameter(species.getNodeIndex(), ManipulatingParameterName.x.getManipulatingParameterIndex(), value, sParams);
     } else if (parameter == Constants.PARAMETER_X_A) {
     setNodeParameter(species.getNodeIndex(), ManipulatingParameterName.ax.getManipulatingParameterIndex(), value, sParams);
     }

     updateSystemParameters(timestep, false, manipulation_id, sParams, nodes);
     }
     */

    /* 9/29/14, jtc, depricating, no longer called
     public void setParameters(HashMap<Integer, SpeciesZoneType> mSpecies, int timestep, String manipulation_id, HashMap<Short, Float> parameterList) {
     for (SpeciesZoneType szt : mSpecies.values()) {
     List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
     ManipulatingNode node = new ManipulatingNode();
     node.setTimestepIdx(timestep);
     node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
     node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
     node.setNodeIdx(szt.getNodeIndex());
     node.setBeginingBiomass(szt.getCurrentBiomass() / Constants.BIOMASS_SCALE);
     node.setHasLinks(false);
     nodes.add(node);

     List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();

     if (szt.getType() == SpeciesTypeEnum.PLANT) {
     setNodeParameter(szt.getNodeIndex(), ManipulatingParameterName.k.getManipulatingParameterIndex(), parameterList.get(Constants.PARAMETER_K), sParams);
     System.out.println("Updating Plant Parameter: [K] " + szt.getName() + parameterList.get(Constants.PARAMETER_K));
     setNodeParameter(szt.getNodeIndex(), ManipulatingParameterName.r.getManipulatingParameterIndex(), parameterList.get(Constants.PARAMETER_R), sParams);
     System.out.println("Updating Plant Parameter: [R] " + szt.getName() + parameterList.get(Constants.PARAMETER_R));
     //                setNodeParameter(szt.getNodeIndex(), ManipulatingParameterName.x.getManipulatingParameterIndex(), parameterList.get(Constants.PARAMETER_X), sParams);
     //                System.out.println("Updating Plant Parameter: [X] " + parameterList.get(Constants.PARAMETER_X));
     } else if (szt.getType() == SpeciesTypeEnum.ANIMAL) {
     //                setNodeParameter(szt.getNodeIndex(), ManipulatingParameterName.x.getManipulatingParameterIndex(), parameterList.get(Constants.PARAMETER_X_A), sParams);
     //                System.out.println("Updating Animal Parameter: [X] " + parameterList.get(Constants.PARAMETER_X_A));
     }

     updateSystemParameters(timestep, false, manipulation_id, sParams, nodes);
     }
     }
     */
    /*
     public void setFunctionalParameters(HashMap<Integer, SpeciesZoneType> mSpecies, int timestep, String manipulation_id, HashMap<Short, Float> parametersList, int parameterType, int predatorId) {
     for (SpeciesZoneType szt : mSpecies.values()) {
     List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
     ManipulatingNode node = new ManipulatingNode();
     node.setTimestepIdx(timestep);
     node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
     node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
     node.setNodeIdx(szt.getNodeIndex());
     node.setBeginingBiomass(szt.getCurrentBiomass());  //note: lacks divide by BIOMASS_SCALE
     node.setHasLinks(false);
     nodes.add(node);

     List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
     if (szt.getType() == SpeciesTypeEnum.PLANT) {
     if (parameterType == Constants.PARAMETER_X) {
     Float paramValue = (float) 0;
     Short nodeIdx = (short) szt.getNodeIndex();
     if (nodeIdx != 0) {
     paramValue = parametersList.get(nodeIdx);
     if (paramValue != 0) {
     paramValue = paramValue / 100;
     }
     }
     setFunctionalNodeParameter(szt.getNodeIndex(), 0, ManipulatingParameterName.x.getManipulatingParameterIndex(), paramValue, sParams, szt);
     System.out.println("Updating Plant Parameter: [X] " + szt.getName() + " NI " + szt.getNodeIndex() + " PV " + paramValue + " PT " + Constants.PARAMETER_X);
     }
     }
     if (szt.getType() == SpeciesTypeEnum.ANIMAL) {
     List<Integer> preys = szt.getlPreyIndex();
     if (preys != null) {
     if (parameterType == Constants.PARAMETER_X_A) {
     Float paramValue = (float) 0;
     Short nodeIdx = (short) szt.getNodeIndex();
     if (nodeIdx != 0) {
     paramValue = parametersList.get(nodeIdx);
     if (paramValue != 0) {
     paramValue = paramValue / 100;
     }
     }
     setFunctionalNodeParameter(szt.getNodeIndex(), 0, ManipulatingParameterName.ax.getManipulatingParameterIndex(), paramValue, sParams, szt);
     System.out.println("Updating Animal Parameter: [X_A] " + szt.getName() + " NI " + szt.getNodeIndex() + " PV " + paramValue + " PT " + Constants.PARAMETER_X_A);
     } else if (parameterType == Constants.PARAMETER_E) {
     for (Integer prey : preys) {
     setFunctionalNodeParameter(szt.getNodeIndex(), prey, ManipulatingParameterName.e.getManipulatingParameterIndex(), Constants.PARAMETER_E, sParams, szt);
     System.out.println("Updating Animal Parameter: [E] " + szt.getName() + Constants.PARAMETER_E);
     }
     } else if (parameterType == Constants.PARAMETER_D) {
     for (Integer prey : preys) {
     setFunctionalNodeParameter(szt.getNodeIndex(), prey, ManipulatingParameterName.d.getManipulatingParameterIndex(), Constants.PARAMETER_D, sParams, szt);
     System.out.println("Updating Animal Parameter: [D] " + szt.getName() + Constants.PARAMETER_D);
     }
     } else if (parameterType == Constants.PARAMETER_Q) {
     for (Integer prey : preys) {
     setFunctionalNodeParameter(szt.getNodeIndex(), prey, ManipulatingParameterName.q.getManipulatingParameterIndex(), Constants.PARAMETER_Q, sParams, szt);
     System.out.println("Updating Animal Parameter: [Q] " + szt.getName() + Constants.PARAMETER_Q);
     }
     } else if (parameterType == Constants.PARAMETER_A) {
     for (Integer prey : preys) {
     setFunctionalNodeParameter(szt.getNodeIndex(), prey, ManipulatingParameterName.a.getManipulatingParameterIndex(), Constants.PARAMETER_A, sParams, szt);
     System.out.println("Updating Animal Parameter: [A] " + szt.getName() + Constants.PARAMETER_A);
     }
     }
     }
     }

     updateSystemParameters(timestep, false, manipulation_id, sParams, nodes);
     }
     }
     */

    /*5/6/14, JTC, depricated
     4/11/14, JTC, active code used by game for players via getPrediction call from PredictionRunnable
     not messing with this right now, but if parameters are incorporated into game, new version
     of addMultipleSpeciesType() would replace this.
     public String addMultipleSpeciesType(List<SpeciesZoneType> speciesList, int timestep,
     boolean isFirstManipulation, String networkOrManipulationId) throws SimulationException {
     long milliseconds = System.currentTimeMillis();
     List<ManipulatingParameter> sysParamList = new ArrayList<>();
     List<ManipulatingNodeProperty> lManipulatingNodeProperty = new ArrayList<>();
     ManipulatingNode[] nodes = new ManipulatingNode[speciesList.size()];
     int i = 0;

     for (SpeciesZoneType species : speciesList) {
     ManipulatingNode node = new ManipulatingNode();
     node.setTimestepIdx(timestep);
     node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
     node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
     node.setNodeIdx(species.getNodeIndex());
     node.setBeginingBiomass(species.getCurrentBiomass() / Constants.BIOMASS_SCALE);
     node.setHasLinks(false);
     node.setGameMode(true);
     node.setNodeName(species.getName()); // set node name
     node.setOriginFoodwebId(propertiesConfig.getProperty("serengetiNetworkId"));
     nodes[i++] = node;

     //Connectance
     ManipulatingNodeProperty mnp = new ManipulatingNodeProperty();
     mnp.setNodeIdx(species.getNodeIndex());
     mnp.setNodePropertyName(ManipulatingNodePropertyName.Connectance.name());
     mnp.setNodePropertyValue(Double.valueOf(propertiesConfig.getProperty("connectanceDefault")));
     lManipulatingNodeProperty.add(mnp);
     //Probability (if this value is low, invasion may fail.)
     mnp = new ManipulatingNodeProperty();
     mnp.setNodeIdx(species.getNodeIndex());
     mnp.setNodePropertyName(ManipulatingNodePropertyName.Probability.name());
     mnp.setNodePropertyValue(Double.valueOf(propertiesConfig.getProperty("probabilityDefault")));
     lManipulatingNodeProperty.add(mnp);
     //SpeciesZoneType count
     mnp = new ManipulatingNodeProperty();
     mnp.setNodeIdx(species.getNodeIndex());
     mnp.setNodePropertyName(ManipulatingNodePropertyName.SpeciesCount.name());
     mnp.setNodePropertyValue(species.getSpeciesCount());
     lManipulatingNodeProperty.add(mnp);

     List<ManipulatingParameter> params = this.getSystemParameter(species, timestep);
     sysParamList.addAll(params);

     Log.println("Adding: [" + species.getNodeIndex() + "] " + species.getName() + " " + species.getCurrentBiomass() / Constants.BIOMASS_SCALE);
     }

     ManipulatingNodeProperty[] nps = (ManipulatingNodeProperty[]) lManipulatingNodeProperty.toArray(new ManipulatingNodeProperty[0]);
     ManipulatingParameter[] sysParams = CopySystemParameter(sysParamList);

     SimpleManipulationRequest smr = new SimpleManipulationRequest();
     smr.setUser(user);
     smr.setBeginingTimestepIdx(timestep);
     if (isFirstManipulation) {
     smr.setNetworkId(networkOrManipulationId);
     } else {
     smr.setManipulationId(networkOrManipulationId);
     }
     smr.setTimestepsToRun(Integer.valueOf(propertiesConfig.getProperty("timestepsToRunDefault")));
     smr.setManipulationModelNodes(nodes);
     smr.setNodeProperties(nps);
     smr.setSysParams(sysParams);
     smr.setDescription(" " + propertiesConfig.getProperty("addNewSpeciesTypeDescription"));
     smr.setSaveLastTimestepOnly(false);
     ;
     ManipulationResponse response = new ManipulationResponse();
     try {
     response = (ManipulationResponse) svc.executeManipulationRequest(smr);
     //TODO: Write web service call to database
     } catch (RemoteException e) {
     e.printStackTrace();
     }
     logTime("Total Time (Add Multiple Species Type): " + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
     String errMsg = response.getMessage();
     if (errMsg != null) {
     throw new SimulationException("Error (addMultipleSpeciesType): " + errMsg);
     }
     return response.getManipulationId();
     }
     */
}
