/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import static java.lang.Math.ceil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import shared.metadata.Constants;
import shared.simulation.ParamValue;
import shared.simulation.SpeciesZoneType;
import shared.util.Log;
import shared.db.SimJobDAO;

/**
 * SimJob is class that is used to manage a single simulation job. It is closely
 * related to the table sim_job and jobs are written to and read from that table
 * into an object of this class. The table stores simulation startup information
 * in a field called node_config. This class contains a parser
 * (parseNodeConfig()) to extract the node configuration information and create
 * a list of SpeciesZoneType objects that are the objects used by
 * SimulationEngine.getPrediction() and other SimulationEngine methods to set up
 * and run simulations. The class also contains a method to build the
 * node_config field from the list of SpeciesZoneTypes (for instance, a list of
 * SpeciesZoneTypes is generated for "random" simulations in class
 * SimJobManager.
 *
 * node_config syntax is as follows (no carriage returns): 1. #, //#=number of
 * nodes 2. [#], //[#]=next node ID (species) 3. #, //#=total biomass 4. #,
 * //#=per-unit-biomass 5. #, //#=number of node parameters configured (exclude
 * next line if = 0) 6. p=#, //p=node parameter ID (K, R, X) for (carrying
 * capacity, growth rate, met-rate) {repeat 6 based on number given in 5} 7. #,
 * //#=number of link parameters configured (exclude next two lines if = 0) 8.
 * [#], //[#]=link node ID (linked species) 9. p=#, //p=link parameter ID (A, E,
 * D, Q, Y) {repeat 8-9 based on number given in 7} {repeat 2-9 based on number
 * given in 1}
 *
 * @author Justina
 */
public class SimJob {

    // 4/5/2014 these default values currently duplicate SimulationEngineConfig.properties
    //storing locally to provide more flexibility for simulation experimentation
    static final String FLD_PREFIX = "param", FLD_PREFIX_DFLT = "dflt";
    //PP = PRIMARY PRODUCER (AKA GRASS)
    public static final double DFLT_PP_TOTAL_BIOMASS = 1000.0;   //2000.0;
    public static final double DFLT_PP_PER_UNIT_BIOMASS = 1;
    public static final double DFLT_PP_PARAMK = 2000.0;  //10000.0;  //k=carrying capacity  (SEConfig properties dflt = 1.0)
    public static double DFLT_PP_PARAMX = 0.5;  //x=metabolic rate (init 0.5, from SEConfig properties)
    public static double DFLT_PP_PARAMR = 1.0;  //r=growth rate (init 1.0, from SEConfig properties)
    //not currently used...
//    public static final double DFLT_PP_AR = 1.0;
//    public static final double DFLT_PP_FR = 0.1;

    public static final int DFLT_TIMESTEPS = 20;
    public static final int NO_ID = -1;
    public static final boolean DFLT_USE_SIMTESTNODE_VALS = true;

    enum ParamType {

        NODE, LINK
    };

    enum DfltParams {

        paramR("R", -1.0, ParamType.NODE), //no single valid dflt for r (prev 1.0)
        paramK("K", -1.0, ParamType.NODE), //no single valid dflt for k (prev 10000)
        paramX("X", -1.0, ParamType.NODE), //no single valid dflt for x (prev 0.5)
        paramA("A", 0.01, ParamType.LINK),
        paramE("E", 0.85, ParamType.LINK),
        paramD("D", 0.0, ParamType.LINK),
        paramQ("Q", 0.0, ParamType.LINK),
        paramY("Y", 6.0, ParamType.LINK);
        protected final String paramID;
        protected final double dfltValue;
        protected final ParamType ptype;

        private DfltParams(String paramID, double dfltValue,
                ParamType ptype) {
            this.paramID = paramID;
            this.dfltValue = dfltValue;
            this.ptype = ptype;
        }

        String getParamID() {
            return paramID;
        }

        double getDfltValue() {
            return dfltValue;
        }

        //10/28/14, jtc, revised method to acquire new dfltK/R/X field value
        //prev was just using static defaults entered above - inaccurate.
        double getDfltValue(SimJobSZT szt) throws NoSuchFieldException, 
                IllegalArgumentException, IllegalAccessException {
            double dflt;
            if (ptype == ParamType.NODE) {
                dflt = szt.getParamValue(FLD_PREFIX_DFLT + this.paramID);
            } else {
                dflt = dfltValue;
            }
            return dflt;
        }

        ParamType getParamType() {
            return ptype;
        }

        static int getLinkParamCnt() {
            int cnt = 0;
            for (DfltParams p : DfltParams.values()) {
                if (p.getParamType() == ParamType.LINK) {
                    cnt++;
                }
            }
            return cnt;
        }

        //see if actual value equals default value - NODE
        boolean equalsDefault(SimJobSZT sjSzt)
                throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
            double actualVal = getActualValue(sjSzt);
            if (actualVal == Constants.PARAM_INITVALUE) {
                return true;
            }
            return (double) getActualValue(sjSzt) == this.getDfltValue(sjSzt);
        }

        double getActualValue(SimJobSZT sjSzt)
                throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
            if (this.ptype != ParamType.NODE) {
                return 0.0;  //only used for node parameters
            }
            return sjSzt.getParamValue(FLD_PREFIX + this.paramID);
        }
    }

    protected int job_Id = NO_ID;
    protected String job_Descript = "";
    protected int timesteps = 0;
    protected String node_Config = "";
    protected String manipulation_Id = null;  //9/25/14, JTC, had to change to null
    protected String manip_Timestamp = "";
    protected String csv = "";
    protected boolean include = true;
    //11/9/14, jtc, add new option to use simtest node param values or not:
    protected boolean useSimTestNodeVals = DFLT_USE_SIMTESTNODE_VALS;
    protected List<SpeciesZoneType> speciesZoneList = null;
	private ConsumeMap consumeMap;
	private PathTable pathTable;
	private String biomassCSV;

    //adding the following parameters to SZT:
    //double paramY;  //link level
    //the following parameters are already stored by SZT:
    //double paramK, paramR, paramX;          //node level
    //double paramE, paramD, paramQ, paramA;  //link level (note: paramA = B0 per Rich Williams)
    //??do I need to be able to vary "constants": at, aj, ar, fr, fj??
    public SimJob() {
        speciesZoneList = new ArrayList<SpeciesZoneType>();
    }

    //1/29/15, JTC, create a simjob from a config string/timesteps
    public SimJob(String config, int timesteps) {
        this.job_Id = NO_ID;
        this.timesteps = timesteps;
        setNode_Config (config);
    }

    //10/27/14, JTC, copy a SimJob
    public SimJob(SimJob srcJob) {
        //don't copy unique or output fields
        //(job_Id, manipulation_Id, manip_Timestamp, csv, include)

        //copy configuration fields
        this.useSimTestNodeVals = srcJob.useSimTestNodeVals;
        this.job_Descript = srcJob.job_Descript;
        this.timesteps = srcJob.timesteps;
        this.node_Config = srcJob.node_Config;

        //copy SZTs
        this.speciesZoneList = new ArrayList<SpeciesZoneType>();
        for (SpeciesZoneType szt : srcJob.speciesZoneList) {
            SimJobSZT srcSJSZT = (SimJobSZT) szt;
            SimJobSZT sjSZT = new SimJobSZT(srcSJSZT);
            this.speciesZoneList.add(sjSZT);
        }        
    }

    public void setJob_Id(int job_Id) {
        this.job_Id = job_Id;
    }

    public void setManipulation_Id(String manipulation_Id) {
        this.manipulation_Id = manipulation_Id;
    }

    public void setManip_Timestamp(String manip_Timestamp) {
        this.manip_Timestamp = manip_Timestamp;
    }

    public void setJob_Descript(String job_Descript) {
        this.job_Descript = job_Descript;
    }

    public void setTimesteps(int timesteps) {
        this.timesteps = timesteps;
    }

    public void setNode_Config(String node_Config) {
        this.node_Config = node_Config;
        parseNodeConfig();
    }

    public void setCsv(String csv) {
        this.csv = csv;
        String str = String.format("csv for %d %s", job_Id, csv);
        Log.consoleln(str);
    }

    public void setInclude(boolean incl) {
        this.include = incl;
    }

    public void setUseSimTestNodeVals(boolean useSTN) {
        this.useSimTestNodeVals = useSTN;
    }

    public int getJob_Id() {
        return job_Id;
    }

    public String getManipulation_Id() {
        return manipulation_Id;
    }

    public String getManip_Timestamp() {
        return manip_Timestamp;
    }

    public String getJob_Descript() {
        return job_Descript;
    }

    public int getTimesteps() {
        return timesteps;
    }

    public String getNode_Config() {
        return node_Config;
    }

    public String getCsv() {
        return csv;
    }

    public boolean getInclude() {
        return include;
    }

    public List<SpeciesZoneType> getSpeciesZoneList() {
        return speciesZoneList;
    }

    public HashMap<Integer, SpeciesZoneType> getSpeciesZoneMap() {
        HashMap<Integer, SpeciesZoneType> speciesZoneMap = 
                new HashMap<Integer, SpeciesZoneType>();
        for (SpeciesZoneType szt : speciesZoneList) {
            speciesZoneMap.put(szt.getNodeIndex(), szt);
        }
        return speciesZoneMap;
    }

    public int[] getSpeciesNodeList() {
        int[] nodeList = new int[speciesZoneList.size()];
        int i = 0;
        for (SpeciesZoneType szt : speciesZoneList) {
            nodeList[i++] = szt.getNodeIndex();
        }
        return nodeList;
    }

    public boolean getUseSimTestNodeVals() {
        return this.useSimTestNodeVals;
    }

    protected void parseNodeConfig() {
        //reset species list
        speciesZoneList = new ArrayList<SpeciesZoneType>();
        SimJobSZT sjSzt = null;
        String remainder = node_Config, paramID;
        int nodeCnt = 0, paramCnt, nextNode_Id;
        double biomass, perUnitBiomass, value;

        //sequence is nodeCnt,[node0],biomass0,perunitbiomass0,paramCnt0,(if any)paramID0,value0,paramID1,value1,...
        //[node1],biomass1,perunitbiomass1,paramCnt1,...,[nodeN],biomassN,...
        if (!remainder.isEmpty()) {
            //"nodeCnt,"
            nodeCnt = Integer.valueOf(remainder.substring(0, remainder.indexOf(",")));
        }
        for (int i = 0; i < nodeCnt; i++) {
            //"[node_Id(i)],biomass(i),"
            remainder = trim(remainder, "[");
            nextNode_Id = Integer.valueOf(remainder.substring(0, remainder.indexOf("]")));
            remainder = trim(remainder, ",");
            biomass = Double.valueOf(remainder.substring(0, endIndex(remainder, ",")));
            remainder = trim(remainder, ",");
            perUnitBiomass = Double.valueOf(remainder.substring(0, endIndex(remainder, ",")));
            remainder = trim(remainder, ",");
            //create entry in szt list
            sjSzt = new SimJobSZT("", nextNode_Id, 0, perUnitBiomass, biomass, null, useSimTestNodeVals);
            speciesZoneList.add(sjSzt);

            if (remainder.isEmpty()) {
                break;
            }
            //get counts of node parameters for current node
            paramCnt = Integer.valueOf(remainder.substring(0, remainder.indexOf(",")));

            //get node parameters
            for (int j = 0; j < paramCnt; j++) {
                //"paramID(j)=value(j),"
                remainder = trim(remainder, ",");
                paramID = remainder.substring(0, remainder.indexOf("=")).toUpperCase();
                remainder = trim(remainder, "=");
                value = Double.valueOf(remainder.substring(0, endIndex(remainder, ",")));
                try {
                    //set node parameter for species object
                    sjSzt.setParamValue(FLD_PREFIX + paramID, value);
                } catch (Exception ex) {
                    Logger.getLogger(SimJob.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //get link parameters
            remainder = trim(remainder, ",");
            remainder = parseLinkParams(remainder, sjSzt);
        }
    }

    protected String parseLinkParams(String remainder, SimJobSZT sjSzt) {
        String paramID;
        int linkParamCnt = 0, prey_Id;
        double value;
        ParamValue paramValue;
        List<ParamValue> linkParam;

        // sequence is linkParamCnt,[prey_Id0],paramID0=value0,[prey_Id1],paramID1=value1,...[prey_IdN],paramIDN=valueN
        if (remainder.indexOf(",") != -1) {
            //get count of link params
            //"linkParamCnt,"
            linkParamCnt = Integer.valueOf(remainder.substring(0, remainder.indexOf(",")));
        } else {
            linkParamCnt = 0;
        }
        //get link parameters
        for (int i = 0; i < linkParamCnt; i++) {
            //"[prey_Id(i)],paramID(i)=value(i),"
            remainder = trim(remainder, "[");
            prey_Id = Integer.valueOf(remainder.substring(0, remainder.indexOf("]")));
            remainder = trim(remainder, ",");
            paramID = remainder.substring(0, remainder.indexOf("=")).toUpperCase();
            remainder = trim(remainder, "=");
            int commaIdx = remainder.indexOf(",");
            value = Double.valueOf(remainder.substring(0,
                    (commaIdx == -1 ? remainder.length() : commaIdx)));
            paramValue = new ParamValue(prey_Id, value);
            try {
                //set link parameter for species object
                sjSzt.addParamListEntry(FLD_PREFIX + paramID, paramValue);
            } catch (Exception ex) {
                Logger.getLogger(SimJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return remainder.indexOf(",") == -1 ? "" : trim(remainder, ",");
    }

    protected String trim(String superString, String cutoff) {
        int idx = superString.indexOf(cutoff);
        if (idx < 0) {
            return "";
        }
        return superString.substring(idx + 1);
    }

    //get endindex for substring where may be a comma or may be end of string
    //note: assumes that startindex is 0
    protected int endIndex(String superString, String cutoff) {
        if (superString.indexOf(cutoff) < 0) {
            return superString.length();
        } else {
            return superString.indexOf(cutoff);
        }
    }

    public void setSpeciesZoneList(List<SpeciesZoneType> list){
        //copy SZTs
        this.speciesZoneList = new ArrayList<SpeciesZoneType>();
//        for (SpeciesZoneType szt : list) {
//            SimJobSZT srcSJSZT = (SimJobSZT) szt;
//            SimJobSZT sjSZT = new SimJobSZT(srcSJSZT);
            this.speciesZoneList.addAll(list);
//        }  
    }
    /* 4/21/14, JTC, added per species biomass */
    public String buildNodeConfig() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        String configStr;
        SimJobSZT sjSzt;
        int paramCnt;

        //sequence is nodeCnt,[node0],biomass0,per-unit-biomass0,paramCnt0,(if any)paramID0,value0,paramID1,value1,...
        //"nodeCnt,"
        configStr = String.format("%d,", speciesZoneList.size());
        //sort for easier (human) review of configuration
        Collections.sort(speciesZoneList, new SZTComparator());
        for (SpeciesZoneType szt : speciesZoneList) {
        	sjSzt = (SimJobSZT) szt;
            //"[node_Id],biomass,"
            configStr = configStr.concat(String.format("[%d],", sjSzt.getNodeIndex()));

            configStr = configStr.concat(String.format("%.0f,", ceil(sjSzt.getCurrentBiomass())));
            configStr = configStr.concat(String.format("%.3f,",
                    roundToThreeDigits(sjSzt.getPerSpeciesBiomass())));
            try {
                //paramCnt,
                paramCnt = 0;
                for (DfltParams p : DfltParams.values()) {
                    if (p.getParamType() != ParamType.NODE) {
                        continue;
                    }
                    if (!p.equalsDefault(sjSzt)) {
                        paramCnt++;
                    }
                }
                configStr = configStr.concat(String.format("%d,", paramCnt));
                //"paramID(p)=value(p),"
                for (DfltParams p : DfltParams.values()) {
                    if (p.getParamType() != ParamType.NODE) {
                        continue;
                    }
                    if (!p.equalsDefault(sjSzt)) {
                        configStr = configStr.concat(p.getParamID() + "=");
                        try {
                            configStr = configStr.concat(String.format("%.3f,",
                                    roundToThreeDigits(p.getActualValue(sjSzt))));
                        } catch (Exception ex) {
                            Logger.getLogger(SimJob.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(SimJob.class.getName()).log(Level.SEVERE, null, ex);
            }
            configStr = configStr.concat(buildLinkParams(sjSzt));
        }
        //set node_Config field and return string; exclude trailing comma
        return (node_Config = configStr.substring(0, configStr.length() - 1));
    }

    protected String buildLinkParams(SimJobSZT sjSzt)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String paramStr;
        int paramCnt = 0, paramNum;
        List<ParamValue> fullList, changedList, lParamValue;
        List<List<ParamValue>> llParamValue = new ArrayList<List<ParamValue>>();

        //create list of parameter lists that differ from defaults; keep count of # of entries
        //loop through parameters (which for links are lists)
        for (DfltParams p : DfltParams.values()) {
            if (p.getParamType() != ParamType.LINK) {
                continue;
            }
            fullList = sjSzt.getParamList(FLD_PREFIX + p.paramID);
            changedList = new ArrayList<ParamValue>();
            //loop through each list, checking for non default values; add to changedList
            //and increment count
            for (ParamValue paramValue : fullList) {
                if (p.getDfltValue() != paramValue.getParamValue()) {
                    changedList.add(paramValue);
                    paramCnt++;
                }
            }
            //this should be added even if it's empty; need one entry per parameter
            llParamValue.add(changedList);
        }
        //"paramCnt,"
        paramStr = String.format("%d,", paramCnt);
        //loop through list of parameter lists; adding prey id and param value to string
        if (paramCnt > 0) {
            paramNum = 0;
            for (DfltParams p : DfltParams.values()) {
                if (p.getParamType() != ParamType.LINK) {
                    continue;
                }
                lParamValue = llParamValue.get(paramNum++);
                //loop through list of non-default values for this param type
                for (ParamValue paramValue : lParamValue) {
                    //"[prey_Id(i)],param_Id(i)=param_val(i),"
                    paramStr = paramStr.concat(String.format("[%d],", paramValue.getPreyIdx()));
                    paramStr = paramStr.concat(p.getParamID() + "=");
                    paramStr = paramStr.concat(
                            String.format("%.3f,",
                                    roundToThreeDigits(paramValue.getParamValue())));
                }
            }
        }

        return paramStr;
    }

    public void addSimJobSZT(SimJobSZT sjSzt) {
        speciesZoneList.add(sjSzt);
    }

    public void removeSimJobSZT(SimJobSZT sjSzt) {
        speciesZoneList.remove(sjSzt);
    }

    public SimJobSZT getSpeciesZoneByNodeId(int node_Id) {
        SimJobSZT sjSzt = null;

        for (SpeciesZoneType szt : speciesZoneList) {
            if (szt.getNodeIndex() == node_Id) {
                sjSzt = (SimJobSZT) szt;
                break;
            }
        }
        return sjSzt;
    }

    public SimJobSZT getSpeciesZoneBySpeciesId(int species_Id) {
        SimJobSZT sjSzt;
        for (SpeciesZoneType szt : speciesZoneList) {
            sjSzt = (SimJobSZT) szt;
            if (sjSzt.getSpeciesIndex() == species_Id) {
                return sjSzt;
            }
        }
        return null;
    }

    //set parameters k, r and x for plant species
    public void setSpeciesZoneListParamKRX(double k, double r, double x) {
        SimJobSZT sjSzt;
        for (SpeciesZoneType szt : speciesZoneList) {
            sjSzt = (SimJobSZT) szt;
            //don't want to update the primary producer through this method!
            if (sjSzt.getNodeIndex() == Constants.PP_NODE_ID) {
                continue;
            }
            if (sjSzt.getSpeciesType().getOrganismType() == Constants.ORGANISM_TYPE_PLANT) {
                if (k != Constants.PARAM_INITVALUE) {
                    sjSzt.setParamK(k);
                }
                if (r != Constants.PARAM_INITVALUE) {
                    sjSzt.setParamR(r);
                }
                if (x != Constants.PARAM_INITVALUE) {
                    sjSzt.setParamX(x);
                }
            }
        }
    }

    //calculate metabolic rate for animals
    public void setSpeciesZoneListAnimalParamX() {
        SimJobSZT sjSzt;
        double a_Ti, M_i, x_i;
        double f_rk = Constants.F_R;
        double a_rk = this.getSpeciesZoneByNodeId(Constants.PP_NODE_ID).getAR();
        double M_k = this.getSpeciesZoneByNodeId(Constants.PP_NODE_ID).
                getPerSpeciesBiomass();
        double normFactor = 0;
        double[] nonNormX = new double[speciesZoneList.size()];
        int sp = 0;
        for (SpeciesZoneType szt : speciesZoneList) {
            sjSzt = (SimJobSZT) szt;
            if (sjSzt.getSpeciesType().getOrganismType() == Constants.ORGANISM_TYPE_ANIMAL) {
                a_Ti = Constants.A_T[sjSzt.getMetType()];
                M_i = sjSzt.getPerSpeciesBiomass();
                x_i = (a_Ti / (f_rk * a_rk)) * Math.pow(M_k / M_i, 0.25);
                nonNormX[sp] = x_i;    //sjSzt.setParamX(x_i);
                normFactor = Math.max(normFactor, x_i);
            }
            sp++;
        }

        /* metabolic rate only works if <= 1.0; normalizing against largest rate
         identified above ( as of 5/2/4 this method has not been validated by the
         ecologists) */
        if (normFactor > 0) {
            sp = 0;
            for (SpeciesZoneType szt : speciesZoneList) {
                sjSzt = (SimJobSZT) szt;
                if (sjSzt.getSpeciesType().getOrganismType() == Constants.ORGANISM_TYPE_ANIMAL) {
                    sjSzt.setParamX(nonNormX[sp] / normFactor);
                }
                sp++;
            }
        }
    }

    //set parameter y for all active predator-prey links (i.e. active species)
    public void setSpeciesZoneListParamY() {
        SimJobSZT sjSzt;
        for (SpeciesZoneType szt : speciesZoneList) {
            sjSzt = (SimJobSZT) szt;
            //this parameter animal specific
            if (sjSzt.getSpeciesType().getOrganismType() == Constants.ORGANISM_TYPE_PLANT) {
                continue;
            }
            sjSzt.setAllParamY(this);
        }
    }

    //save job to table; if ID not set, new record, otherwise, update existing
    public int saveJob() throws SQLException {
        if (job_Id == NO_ID) {
            job_Id = SimJobDAO.createJob(this);
        } else {
            job_Id = SimJobDAO.updateJob(this);
        }

        return job_Id;
    }

    /*the following two methods are used by separate SimAnalyzer
     (getTrophicGroupNodeList(), getDietGroupNodeList(), getPredShareNodeList(),
     getPreyShareNodeList()*/
    public List<Integer> getTrophicGroupNodeList(String trophGrp) {
        List<Integer> nodeList = new ArrayList<Integer>();
        SimJobSZT sjSzt;

        for (SpeciesZoneType szt : this.speciesZoneList) {
            sjSzt = (SimJobSZT) szt;
            if (trophGrp.equals(sjSzt.getTrophicGroup())) {
                nodeList.add(szt.getNodeIndex());
            }
        }

        return nodeList;
    }

    public List<Integer> getDietGroupNodeList(int srcNode) {
        List<Integer> nodeList = new ArrayList<Integer>();
        String dietGrp = getSpeciesZoneByNodeId(srcNode).getDietGroup();
        SimJobSZT sjSzt;

        for (SpeciesZoneType szt : this.speciesZoneList) {
            sjSzt = (SimJobSZT) szt;
            if (szt.getNodeIndex() == srcNode) {
                continue;
            }
            if (dietGrp.equals(sjSzt.getDietGroup())) {
                nodeList.add(szt.getNodeIndex());
            }
        }

        return nodeList;
    }

    //build list of species with shared predators (more than pcnt% of source node)
    public List<Integer> getPredShareNodeList(int srcNode, int pcnt) {
        List<Integer> srcPredList = getSpeciesZoneByNodeId(srcNode).
                getSpeciesType().getPredatorNodeIDs();
        List<Integer> activeList = new ArrayList<Integer>();
        List<Integer> nodeList = new ArrayList<Integer>();
        int sharedCnt = 0;

        //first reduce list to those active in this ecosystem
        for (Integer predId : srcPredList) {
            if (getSpeciesZoneByNodeId(predId) != null) {
                activeList.add(predId);
            }
        }
        if (activeList.isEmpty()) {
            return nodeList;
        }

        //look for species that have shared predators.
        for (SpeciesZoneType szt : this.speciesZoneList) {
            if (szt.getNodeIndex() == srcNode
                    || szt.getSpeciesType().getPredatorNodeIDs() == null) {
                continue;
            }
            //loop through this species' predators and count # in common
            sharedCnt = 0;
            for (Integer predId : szt.getSpeciesType().getPredatorNodeIDs()) {
                if (activeList.contains(predId)) {
                    sharedCnt++;
                }
            }
            if ((sharedCnt * 100) / activeList.size() >= pcnt) {
                nodeList.add(szt.getNodeIndex());
            }
        }

        return nodeList;
    }

    //build list of species with shared prey (more than pcnt% of source node)
    public List<Integer> getPreyShareNodeList(int srcNode, int pcnt) {
        List<Integer> srcPreyList = getSpeciesZoneByNodeId(srcNode).
                getSpeciesType().getPreyNodeIDs();
        List<Integer> activeList = new ArrayList<Integer>();
        List<Integer> nodeList = new ArrayList<Integer>();
        int sharedCnt;

        //first reduce list to those active in this ecosystem
        for (Integer preyId : srcPreyList) {
            if (getSpeciesZoneByNodeId(preyId) != null) {
                activeList.add(preyId);
            }
        }
        if (activeList.isEmpty()) {
            return activeList;
        }

        //look for species that have shared prey.
        for (SpeciesZoneType szt : this.speciesZoneList) {
            if (szt.getNodeIndex() == srcNode
                    || szt.getSpeciesType().getPreyNodeIDs() == null) {
                continue;
            }
            //loop through this species' prey and count # in common
            sharedCnt = 0;
            for (Integer preyId : szt.getSpeciesType().getPreyNodeIDs()) {
                if (activeList.contains(preyId)) {
                    sharedCnt++;
                }
            }
            if ((sharedCnt * 100) / activeList.size() >= pcnt) {
                nodeList.add(szt.getNodeIndex());
            }
        }

        return nodeList;
    }

    //10/27/14, jtc, previous use of ceil created inaccuracies
    protected double roundToThreeDigits(double val) {
        val = Math.round(1000 * val) / 1000.0;
        if (val == 0) {
            val = 0.001;
        }
        return val;
    }

	public void setConsumeMap(ConsumeMap consumeMap) {
		this.consumeMap = consumeMap;
	}

	public void setPathTable(PathTable pathTable) {
		this.pathTable = pathTable;
	}
 
	public ConsumeMap getConsumeMap(){
		return this.consumeMap;
	}
	
	public PathTable getPathTable(){
		return this.pathTable;
	}

	public void setBiomassCsv(String biomassCSV) {
		this.biomassCSV = biomassCSV;
        String str = String.format("biomassCSV for %d %s", job_Id, biomassCSV);
        Log.consoleln(str);
	}
	
	public String getBiomassCSV(){
		return biomassCSV;
	}

	public void setATNManipulationId(String atnManipId) {
		this.manipulation_Id = atnManipId;
	}

	public String getATNManipulationId(){
		return manipulation_Id;
	}
}
