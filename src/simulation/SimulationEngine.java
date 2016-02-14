package simulation;

// Java Imports

import core.ServerResources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FilenameFilter;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

// Web Services Imports
import org.datacontract.schemas._2004._07.LINQ2Entities.User;
import org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNode;
import org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNodeProperty;
import org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingParameter;
import org.datacontract.schemas._2004._07.ManipulationParameter.ModelParam;
import org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass;
import org.datacontract.schemas._2004._07.WCFService_Portal.CreateFoodwebResponse;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationInfo;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationInfoRequest;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationInfoResponse;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationResponse;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationParameterInfoRequest;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationParameterInfoResponse;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfo;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfoRequest;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfoResponse;
import org.datacontract.schemas._2004._07.WCFService_Portal.NetworkCreationRequest;
import org.datacontract.schemas._2004._07.WCFService_Portal.NetworkRemoveRequest;
import org.datacontract.schemas._2004._07.WCFService_Portal.NetworkInfo;
import org.datacontract.schemas._2004._07.WCFService_Portal.NetworkInfoRequest;
import org.datacontract.schemas._2004._07.WCFService_Portal.NetworkInfoResponse;
import org.datacontract.schemas._2004._07.WCFService_Portal.SimpleManipulationRequest;
import org.foodwebs.www._2009._11.IN3DService;
import org.foodwebs.www._2009._11.IN3DServiceProxy;

// Other Imports
import metadata.Constants;
import model.SpeciesType;
import simulation.SpeciesZoneType.SpeciesTypeEnum;
import simulation.config.ManipulatingNodePropertyName;
import simulation.config.ManipulatingParameterName;
import simulation.config.ManipulationActionType;
import simulation.config.ModelType;
import util.Log;
import model.ZoneNodes;

public class SimulationEngine{

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
            propertiesLogin.load(new FileInputStream(
                    "conf/simulation/webserviceLogin.properties"));
            user = new User();
            user.setUsername(propertiesLogin.getProperty("username"));
            propertiesConfig.load(new FileInputStream(
                    "conf/simulation/SimulationEngineConfig.properties"));
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
        networkParams[0].setParamValue(Double.valueOf(
                propertiesConfig.getProperty("connectanceDefault")));
        networkParams[1] = new ModelParam();
        networkParams[1].setParamName(ManipulatingNodePropertyName.SpeciesCount.name());
        networkParams[1].setParamValue(Integer.valueOf(
                propertiesConfig.getProperty("speciesCountDefault")));

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
            System.err.println("executeNetworkCreationRequest exception "
                    + "(in createSeregenttiSubFoodweb): " + ex.getMessage());
            System.err.print("StackTrace: ");
            ex.printStackTrace();
            throw new SimulationException(ex.getMessage());
        }

        String errorMsg = response.getMessage();
        if (errorMsg != null) {
            System.err.println("CreateFoodwebResponse getMessage() error "
                    + response.getErrorType()
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
        networkParams[0].setParamValue(Double.valueOf(propertiesConfig.
                getProperty("connectanceDefault")));
        networkParams[1] = new ModelParam();
        networkParams[1].setParamName(ManipulatingNodePropertyName.SpeciesCount.name());
        networkParams[1].setParamValue(Integer.valueOf(propertiesConfig.
                getProperty("speciesCountDefault")));

        NetworkCreationRequest req = new NetworkCreationRequest();
        req.setUser(user); // Owner of network
        req.setNetworkName(networkName); // Name of network -> username_worldname_zoneid
        req.setModelType(ModelType.CASCADE_MODEL.getModelType());
        req.setModelParams(networkParams);
        req.setCreationType(1); // sub food web
        req.setOriginFoodweb(propertiesConfig.getProperty("serengetiNetworkId")); // Serengeti
        int nodeList[] = {
            Integer.valueOf(propertiesConfig.getProperty("defaultSpecies1Id")),
            Integer.valueOf(propertiesConfig.getProperty("defaultSpecies2Id"))
        }; // Grass & buffalo
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
            Log.println_e("Error type: " + response.getErrorType()
                    + "  error msg:" + errorMsg);
            return null;
        } else {
            int timestepIdx = 0;
            HashMap<Integer, SpeciesZoneType> speciesList = new HashMap<Integer, SpeciesZoneType>();
            SpeciesZoneType szt1 = new SpeciesZoneType(
                    propertiesConfig.getProperty("defaultSpecies1Name"),
                    nodeList[0], Integer.valueOf(
                            propertiesConfig.getProperty("defaultSpecies1SpeciesCount")),
                    Double.valueOf(
                            propertiesConfig.getProperty("defaultSpecies1PerSpeciesBiomass")), 0.0,
                    SpeciesTypeEnum.PLANT);
            SpeciesZoneType szt2 = new SpeciesZoneType(
                    propertiesConfig.getProperty("defaultSpecies2Name"),
                    nodeList[1], Integer.valueOf(
                            propertiesConfig.getProperty("defaultSpecies2SpeciesCount")),
                    Double.valueOf(
                            propertiesConfig.getProperty("defaultSpecies2PerSpeciesBiomass")), 0.0,
                    SpeciesTypeEnum.ANIMAL);
            speciesList.put(nodeList[0], szt1);
            speciesList.put(nodeList[1], szt2);
            //Increasing carrying capacity of grass
            ManipulationResponse mResponse = modifyManipulatingParameters(
                    speciesList,
                    speciesList,
                    timestepIdx,
                    true,
                    response.getNetworkId()
            );

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

        @Override
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

        @Override
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

    public String setNodeParameter(
            int nodeIdx, 
            int paramIdx, 
            double paramValue, 
            int timestep, 
            List<ManipulatingParameter> sParams
    ) {
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

    public String setNodeParameter(
            int nodeIdx, 
            int paramIdx, 
            double paramValue, 
            List<ManipulatingParameter> sParams
    ) {
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
    private void setSystemParametersLink(
            List<ManipulatingParameter> sParams,
            int timestepIdx, 
            int predIdx, 
            int preyIdx, 
            ParamValue pvalue,
            ManipulatingParameterName manipParam, 
            String dfltValProp,
            int preyCnt
    ) {
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
    private List<ManipulatingParameter> setSystemParameters(
            SpeciesZoneType species,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestepIdx
    ) {

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
                    
                    //setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamA(preyIdx),
                    //        ManipulatingParameterName.a, "relativeHalfSaturationDensityDefault", preyCnt);
                    if (false) {
                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamA(preyIdx),
                                ManipulatingParameterName.b0, "halfSaturationDensityDefault", preyCnt);
                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamD(preyIdx),
                                ManipulatingParameterName.d, "predatorInterferenceDefault", preyCnt);
                        if (fullSpeciesMap.get(preyIdx).getType() == SpeciesZoneType.SpeciesTypeEnum.ANIMAL) {
                            setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamE(preyIdx),
                                ManipulatingParameterName.e, "assimilationEfficiencyAnimalDefault", preyCnt);
                        } else {
                            setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamE(preyIdx),
                                ManipulatingParameterName.e, "assimilationEfficiencyPlantDefault", preyCnt);
                        }
                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamQ(preyIdx),
                                ManipulatingParameterName.q, "functionalResponseControlParameterDefault", preyCnt);
                        setSystemParametersLink(sParams, timestepIdx, nodeIdx, preyIdx, species.getParamY(preyIdx),
                                ManipulatingParameterName.y, "maximumIngestionRateDefault", preyCnt);
                    }
                }
            }
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
    private ManipulatingNode createSpeciesTypeManip(
            SpeciesZoneType species,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            List<ManipulatingParameter> sysParamList,
            List<ManipulatingNodeProperty> lManipulatingNodeProperty,
            ManipulationActionType manipActionType
    ) throws SimulationException {

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
            mnp.setNodePropertyValue(Double.valueOf(
                    propertiesConfig.getProperty("connectanceDefault")));
            lManipulatingNodeProperty.add(mnp);
            //Probability
            mnp = new ManipulatingNodeProperty();
            mnp.setNodeIdx(species.getNodeIndex());
            mnp.setNodePropertyName(ManipulatingNodePropertyName.Probability.name());
            // if this value is low, invasion may fail.
            mnp.setNodePropertyValue(Double.valueOf(
                    propertiesConfig.getProperty("probabilityDefault"))); 
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
            sysParamList.addAll(this.setSystemParameters(
                    species, 
                    fullSpeciesMap,
                    timestep)
            );
        }

        System.out.println(manipActionType.getManipulationActionDescript() + 
                " [" + species.getNodeIndex() + "] " + species.getName() + " " + 
                species.getCurrentBiomass() / Constants.BIOMASS_SCALE);
        return node;
    }

    /* Submit multiple species manipulations with action type provided by calling method.
     Calls createSpeciesTypeManip() for each node.
     4/11/14, JTC, Note: used orig body of addMultipleSpeciesType as basis for this
     change.*/
    private ManipulatingNode[] createMultipleSpeciesTypeManip(
            HashMap<Integer, SpeciesZoneType> manipSpeciesMap,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            ManipulationActionType manipActionType,
            List<ManipulatingParameter> sysParamList,
            List<ManipulatingNodeProperty> lManipulatingNodeProperty
    )
            throws SimulationException {

        ManipulatingNode[] nodes = new ManipulatingNode[manipSpeciesMap.size()];
        int i = 0;
        List<Integer> cannibal = new ArrayList<Integer>();

        for (SpeciesZoneType species : manipSpeciesMap.values()) {
            ManipulatingNode node = createSpeciesTypeManip(
                    species,
                    fullSpeciesMap,
                    timestep,
                    sysParamList,
                    lManipulatingNodeProperty,
                    manipActionType);
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
    public String submitManipRequest(
            List<ManipulatingParameter> sysParamList,
            List<ManipulatingNodeProperty> lManipulatingNodeProperty,
            ManipulatingNode[] nodes, 
            int timestep, 
            boolean isFirstManipulation,
            String networkOrManipulationId, 
            String manipDescript
    )
            throws SimulationException {

        long milliseconds = System.currentTimeMillis();
        ManipulatingNodeProperty[] nps = null;
        if (lManipulatingNodeProperty != null
                && !lManipulatingNodeProperty.isEmpty()) {
            nps = (ManipulatingNodeProperty[]) 
                    lManipulatingNodeProperty.toArray(new ManipulatingNodeProperty[0]);
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
        smr.setTimestepsToRun(Integer.valueOf(propertiesConfig.
                getProperty("timestepsToRunDefault")));
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
            String networkOrManipulationId
    ) throws SimulationException {
        List<ManipulatingParameter> sysParamList
                = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty
                = new ArrayList<ManipulatingNodeProperty>();

        ManipulatingNode[] nodes = createMultipleSpeciesTypeManip(
                manipSpeciesMap,
                fullSpeciesMap,
                timestep,
                ManipulationActionType.SPECIES_INVASION,
                sysParamList,
                lManipulatingNodeProperty
        );
        String manipId = submitManipRequest(
                sysParamList,
                lManipulatingNodeProperty,
                nodes,
                timestep,
                isFirstManipulation,
                networkOrManipulationId,
                " " + propertiesConfig.getProperty("addNewSpeciesTypeDescription")
        );
        return manipId;
    }

    /**
     * Add more (biomass) for existing species types to a manipulation and then
     * submit. 4/11/14, JTC, consolidated contents of original
     * "addMoreSpeciesOfExistingType" (which was never called) into this renamed
     * (for standardization) method with calls to
     * createMultipleSpeciesTypeManip()/submitManipRequest().
     *
     * @param manipSpeciesMap - species being added
     * @param fullSpeciesMap - full list; for predator/prey info
     * @param timestep
     * @param isFirstManipulation
     * @param networkOrManipulationId
     * @return manipulation ID (string)
     * @throws SimulationException
     */
    public String increaseMultipleSpeciesType(
            HashMap<Integer, SpeciesZoneType> manipSpeciesMap,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            boolean isFirstManipulation,
            String networkOrManipulationId
    ) throws SimulationException {
        List<ManipulatingParameter> sysParamList
                = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty
                = new ArrayList<ManipulatingNodeProperty>();

        ManipulatingNode[] nodes = createMultipleSpeciesTypeManip(
                manipSpeciesMap,
                fullSpeciesMap,
                timestep,
                ManipulationActionType.SPECIES_PROLIFERATION,
                sysParamList,
                lManipulatingNodeProperty
        );
        String manipId = submitManipRequest(
                sysParamList,
                lManipulatingNodeProperty,
                nodes,
                timestep,
                isFirstManipulation,
                networkOrManipulationId,
                " " + propertiesConfig.
                getProperty("addMoreSpeciesToExistingTypeDescription")
        );
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
    public String reduceSpeciesType(
            SpeciesZoneType species,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            boolean isFirstManipulation,
            String networkOrManipulationId
    )
            throws SimulationException {
        List<ManipulatingParameter> sysParamList
                = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty
                = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(
                species,
                fullSpeciesMap,
                timestep,
                sysParamList,
                lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_EXPLOIT
        );
        String manipId = submitManipRequest(
                sysParamList,
                lManipulatingNodeProperty,
                nodes,
                timestep,
                isFirstManipulation,
                networkOrManipulationId,
                " " + propertiesConfig.getProperty("updateBiomassDescription")
        );
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
    public String addSpeciesType(
            SpeciesZoneType species,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            boolean isFirstManipulation,
            String networkOrManipulationId
    )
            throws SimulationException {
        List<ManipulatingParameter> sysParamList
                = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty
                = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(
                species,
                fullSpeciesMap,
                timestep,
                sysParamList,
                lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_INVASION
        );
        String manipId = submitManipRequest(
                sysParamList,
                lManipulatingNodeProperty,
                nodes,
                timestep,
                isFirstManipulation,
                networkOrManipulationId,
                " " + propertiesConfig.getProperty("addNewSpeciesTypeDescription")
        );
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
    public String increaseSpeciesType(
            SpeciesZoneType species,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            boolean isFirstManipulation,
            String networkOrManipulationId
    ) throws SimulationException {
        List<ManipulatingParameter> sysParamList
                = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty
                = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(
                species,
                fullSpeciesMap,
                timestep,
                sysParamList,
                lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_INVASION
        );
        String manipId = submitManipRequest(
                sysParamList,
                lManipulatingNodeProperty,
                nodes,
                timestep,
                isFirstManipulation,
                networkOrManipulationId,
                " " + propertiesConfig.
                getProperty("addMoreSpeciesToExistingTypeDescription")
        );
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
    public String removeSpeciesType(
            SpeciesZoneType species,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            boolean isFirstManipulation,
            String networkOrManipulationId
    ) throws SimulationException {
        List<ManipulatingParameter> sysParamList
                = new ArrayList<ManipulatingParameter>();
        List<ManipulatingNodeProperty> lManipulatingNodeProperty
                = new ArrayList<ManipulatingNodeProperty>();
        ManipulatingNode[] nodes = new ManipulatingNode[1];  //single species

        nodes[0] = createSpeciesTypeManip(
                species,
                fullSpeciesMap,
                timestep,
                sysParamList,
                lManipulatingNodeProperty,
                ManipulationActionType.SPECIES_REMOVAL
        );
        String manipId = submitManipRequest(
                sysParamList,
                lManipulatingNodeProperty,
                nodes,
                timestep,
                isFirstManipulation,
                networkOrManipulationId,
                " " + propertiesConfig.
                getProperty("removeSpeciesTypeDescription")
        );
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
            throw new SimulationException("Error on running ManipulationTimestepInfoRequest: " 
                    + e.getMessage());
        }
        ManipulationTimestepInfo[] infos = response.getManipulationTimestepInfos();
        //TODO: Write web service call to database

        if (infos.length > 0) {
            SpeciesZoneType szt = null;

            for (ManipulationTimestepInfo speciesInfo : infos) {
                if (speciesInfo.getTimestepIdx() == timestep) {
                    //add new species if not existing

                    double biomass = speciesInfo.getBiomass() * Constants.BIOMASS_SCALE;
                    if (!mSpecies.containsKey(speciesInfo.getNodeIdx())) {
                        szt = new SpeciesZoneType(speciesInfo.getNodeName(), 
                                speciesInfo.getNodeIdx(),
                                0, 0, biomass, null);
                        mSpecies.put(speciesInfo.getNodeIdx(), szt);

                    } else { //update existing species current biomass
                        szt = mSpecies.get(speciesInfo.getNodeIdx());

                        szt.setCurrentBiomass(biomass);
                    }
                }
            }
        } else {
            throw new SimulationException("No Species Found!");
        }

        Log.printf("Total Time (Get Biomass): %.2f seconds", 
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);

        return mSpecies;
    }

    /*sets biomass of multiple species using one call to 
    SimpleManipulationRequest.setNodeBiomasses with array
     of node/biomass*/
    public void updateBiomass(
            String manipulationId, 
            List<NodeBiomass> lNodeBiomass, 
            int timestep
    ) throws SimulationException {
        long milliseconds = System.currentTimeMillis();

        ManipulatingNode node = new ManipulatingNode();
        node.setTimestepIdx(timestep);
        node.setManipulationActionType(ManipulationActionType.
                MULTIPLE_BIOMASS_UPDATE.getManipulationActionType());
        ManipulatingNode[] nodes = new ManipulatingNode[1];
        nodes[0] = node;

        SimpleManipulationRequest smr = new SimpleManipulationRequest();
        smr.setUser(user);
        smr.setBeginingTimestepIdx(timestep);
        smr.setManipulationId(manipulationId);
        smr.setTimestepsToRun(Integer.valueOf(
                propertiesConfig.getProperty("timestepsToRunDefault")));
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

        Log.printf("Total Time (Update Biomass): %.2f seconds", 
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
        String errMsg = response.getMessage();
        if (errMsg != null) {
            throw new SimulationException("Error (updateBiomass): " + errMsg);
        }
    }

    /*sets biomass of multiple species using individual calls to to 
    SimpleManipulationRequest.setBeginingBiomass*/
    public void updateBiomass2(
            String manipulationId, 
            List<NodeBiomass> lNodeBiomass, 
            int timestep
    ) throws SimulationException {
        long milliseconds = System.currentTimeMillis();

        List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();

        for (NodeBiomass nodeBiomass : lNodeBiomass) {
            ManipulatingNode node = new ManipulatingNode();
            node.setTimestepIdx(timestep);
            node.setManipulationActionType(ManipulationActionType.
                    SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
            node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
            node.setNodeIdx(nodeBiomass.getNodeIdx());
            //note: lacks divide by BIOMASS_SCALE
            node.setBeginingBiomass(nodeBiomass.getBiomass());  
            node.setHasLinks(false);
            nodes.add(node);
        }

        if (!nodes.isEmpty()) {
            nodes.get(0).setOriginFoodwebId(
                    propertiesConfig.getProperty("serengetiNetworkId"));
        }

        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
        updateSystemParameters(timestep, false, manipulationId, sParams, nodes);

        Log.printf("Total Time (Update Biomass): %.2f seconds", 
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
    }

    public SpeciesZoneType createSpeciesZoneType(int node_id, int biomass) {
        SpeciesZoneType szt;

        szt = new SpeciesZoneType("", node_id, 0, 0, biomass, null);

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
        //JTC, use new HashMap containing all current settings from zoneNodes, masterSpeciesList
        HashMap<Integer, SpeciesZoneType> masterSpeciesList = (HashMap) zoneNodes.getNodes();

        HashMap<Integer, SpeciesZoneType> mNewSpecies = new HashMap<Integer, SpeciesZoneType>();
        //JTC, mUpdateBiomass renamed from mUpdateSpecies
        HashMap<Integer, SpeciesZoneType> mUpdateBiomass = new HashMap<Integer, SpeciesZoneType>();
        //JTC, added new update type, mUpdateParams
        HashMap<Integer, SpeciesZoneType> mUpdateParams = new HashMap<Integer, SpeciesZoneType>();

        SpeciesZoneType szt;

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
                species.setBiomassUpdated(false);
            }
        }

        // Insert new species using web services
        if (!mNewSpecies.isEmpty()) {
            try {
                addMultipleSpeciesType(
                        mNewSpecies,
                        masterSpeciesList,
                        startTimestep,
                        false,
                        networkOrManipulationId
                );
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
                increaseMultipleSpeciesType(
                        mUpdateBiomass,
                        masterSpeciesList,
                        startTimestep,
                        false,
                        networkOrManipulationId
                );
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
///        getBiomassInfo(networkOrManipulationId);

        //JTC - add loop to update persistent player species biomass information
        SpeciesZoneType updS;
        for (SpeciesZoneType priorS : masterSpeciesList.values()) {
            updS = mUpdateBiomass.get(priorS.nodeIndex);
            if (updS != null && updS.currentBiomass != 0) {
                masterSpeciesList.get(priorS.nodeIndex).
                        setCurrentBiomass(Math.ceil(updS.getCurrentBiomass()));
            } 
            else {
                zoneNodes.removeNode(priorS.nodeIndex);
            }
        }

        Log.printf("Total Time (Get Prediction): %.2f seconds",
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);

        return (HashMap) zoneNodes.getNodes();
    }

    public ManipulationResponse updateSystemParameters(
            int timestep, 
            boolean isFirstManipulation, 
            String networkOrManipulationId, 
            List<ManipulatingParameter> sysParamList, 
            List<ManipulatingNode> nodes
    ) {
        long milliseconds = System.currentTimeMillis();

        SimpleManipulationRequest smr = new SimpleManipulationRequest();
        smr.setUser(user);
        smr.setBeginingTimestepIdx(timestep);
        if (isFirstManipulation) {
            smr.setNetworkId(networkOrManipulationId);
        } else {
            smr.setManipulationId(networkOrManipulationId);
        }
        smr.setTimestepsToRun(Integer.valueOf(
                propertiesConfig.getProperty("timestepsToRunDefault")));
        if (sysParamList != null) {
            smr.setSysParams(CopySystemParameter(sysParamList));
        } else {
            System.out.println("Error (updateSystemParameters): " + 
                    "System parameter is null.");
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

        System.out.println("Total Time (updateSystemParameters): " + 
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
        return response;
    }

    public ManipulationResponse modifyManipulatingParameters(
            HashMap<Integer, SpeciesZoneType> manipSpeciesMap,
            HashMap<Integer, SpeciesZoneType> fullSpeciesMap,
            int timestep,
            boolean isFirstManipulation,
            String networkOrManipulationId
    ) {

        List<ManipulatingParameter> sysParamList = new ArrayList<ManipulatingParameter>();
        ManipulatingNode[] nodes = new ManipulatingNode[manipSpeciesMap.size()];
        int i = 0;
        for (SpeciesZoneType species : manipSpeciesMap.values()) {
            ManipulatingNode node = new ManipulatingNode();
            node.setTimestepIdx(timestep);
            node.setManipulationActionType(ManipulationActionType.
                    SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
            node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
            node.setNodeIdx(species.getNodeIndex());
            node.setBeginingBiomass(species.getPerSpeciesBiomass()
                    * species.getSpeciesCount());  //note: lacks divide by BIOMASS_SCALE
            node.setHasLinks(false);
            nodes[i++] = node;

            //5/6/14, JTC, replaced with updated version
            List<ManipulatingParameter> params
                    = this.setSystemParameters(
                            species, 
                            fullSpeciesMap,
                            timestep);
            //List<ManipulatingParameter> params = this.getSystemParameter(species, timestep);
            sysParamList.addAll(params);
        }

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
        smr.setTimestepsToRun(Integer.valueOf(
                propertiesConfig.getProperty("timestepsToRunDefault")));
        smr.setManipulationModelNodes(nodes);

        smr.setSysParams(sysParams);
//        smr.setSysParams(this.CopySystemParameter(sParams));
//        smr.setSysParams((ManipulatingParameter[])sParams.toArray());
        smr.setDescription(" "
                + propertiesConfig.getProperty("increaseCarryingCapacityDescription")
                + " " + propertiesConfig.getProperty("carryingCapacityDefault"));
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

    public void getNetworkInfo() {
        try {
            NetworkInfoRequest request = new NetworkInfoRequest();
            request.setUser(user);

            NetworkInfoResponse response
                    = (NetworkInfoResponse) svc.executeRequest(request);
            //TODO: Write web service call to database
            if (response.getMessage() == null) {
                System.out.println("\nNetwork info:");
                NetworkInfo info[] = response.getNetworkInfo();
                for (int i = 0; i < info.length; i++) {
                    System.out.println(info[i].getNetworkName() + " = "
                            + info[i].getNetworkId());
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

            ManipulationInfoResponse res
                    = (ManipulationInfoResponse) svc.executeRequest(req);
            //TODO: Write web service call to database
            ManipulationInfo[] infos = res.getManipulationInfos();
            for (int i = 0; i < infos.length; i++) {
                System.out.println("\n\nManipulated network: "
                        + infos[i].getNetworkName() + "\nManipulation id: "
                        + infos[i].getManipulationId());
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
                    System.out.println("[" + String.format("%2d", info.getNodeIdx()) + "] - "
                            + String.format("%-25s", name == null ? "Unknown" : name)
                            + " " + Math.ceil(info.getBiomass()));
                }
            } while (curPage++ < response.getPageAvailable());
        } catch (RemoteException ex) {
            System.err.println("Error (getBiomassInfo): " + ex.getMessage());
        }

        logTime("Total Time (Get Biomass Info): "
                + Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
    }

    public void saveBiomassCSVFile(String manipulation_id, String filename) {
        final String name = filename, extension = ".csv";

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
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(name) && name.endsWith(extension);
            }
        });

        String csvFilename = name;

        if (files.length > 0) {
            int[] temp = new int[files.length];

            for (int i = 0; i < temp.length; i++) {
                String lastFilename = files[i].replaceFirst(name, "").
                        replaceFirst("_", "");

                try {
                    temp[i] = Integer.parseInt(lastFilename.substring(0, 
                            lastFilename.indexOf(extension)));
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

                PrintStream p = new PrintStream(
                        
                        new FileOutputStream(Constants.CSV_SAVE_PATH + csvFilename));
                p.println(biomassCSV);
                p.close();

                Log.println("Saved CSV to: " + Constants.CSV_SAVE_PATH + csvFilename);
            } else {
                Log.println_e("CSV Not Found!");
            }
        } catch (FileNotFoundException e) {
            Log.println_e("Failed to save CSV to: " + 
                    Constants.CSV_SAVE_PATH + csvFilename);
        }
    }

    public ManipulationResponse run(
            int beginingTimestep, 
            int timestepsToRun, 
            String netId, 
            boolean isNetwork
    ) {
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

            ManipulationResponse response = (ManipulationResponse) 
                    svc.executeManipulationRequest(smr);
            String errMsg = response.getMessage();
            if (errMsg != null) {
                System.out.println("Error (run): " + errMsg);
            } else {
                System.out.println("Manipulation was successfully operated with "
                        + "manipulation id " + response.getManipulationId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.printf(
                "Total Time (Run %d-%d): %.2f seconds", 
                startTimestep, startTimestep + runTimestep, 
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0);
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
        logTime("Total Time (Create and Run Serengeti Sub-Food Web): " + 
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
        return mr.getManipulationId();
    }

    //jtc 12/22/14, version to return netId (for later deletion) as well as manip Id
    public SimulationIds createAndRunSeregenttiSubFoodwebForSimJob(
            int nodeList[], 
            String foodwebName,
            int beginingTimestep, 
            int timestepsToRun, 
            boolean overwrite
    ) throws SimulationException {
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
        logTime("Total Time (Create and Run Serengeti Sub-Food Web): " + 
                Math.round((System.currentTimeMillis() - milliseconds) / 10.0) / 100.0 + " seconds");
        SimulationIds simIds = new SimulationIds(mr.getManipulationId(), mr.getNetworksId());
        return simIds;
    }
}
