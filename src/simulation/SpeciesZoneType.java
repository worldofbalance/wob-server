package simulation;

// Java Imports
import java.util.ArrayList;
import java.util.List;


import simulation.simjob.SimTestNode;
// Other Imports
import core.ServerResources;
import metadata.Constants;
import model.SpeciesType;

/**
 *
 * @author Sonal
 */
public class SpeciesZoneType {

    public enum action {

        NEW, ADDED, REDUCE, EXTINCT, NO_ACTION
    };

    public enum SpeciesTypeEnum {

        ANIMAL, PLANT, UNKNOWN
    };

    protected String name;
    public int nodeIndex;
    protected List<Integer> lPreyIndex;
    protected List<Integer> lPredatorIndex;
    protected List<SpeciesType> lPrey;
    protected List<SpeciesType> lPredator;
    protected int speciesCount;
    protected double perSpeciesBiomass;
    public double currentBiomass;
    protected SpeciesTypeEnum type;
    protected double trophicLevel;
    public boolean biomassUpdated = false;
    public boolean paramUpdated = false;  //5/6/14, JTC, added
    protected SpeciesType speciesType;  //4/20/14, JTC, added

    //4/14, JTC, replaced hard-coded "-1" assignments with Constants.PARAM_INITVALUE.
    // parameter k, r, x can't have negative value. if they have negative value, 
    //it means that data is not assigned yet.
    protected double paramK = Constants.PARAM_INITVALUE;  // carrying capacity (plants only)
    protected double paramR = Constants.PARAM_INITVALUE;  // growth rate (plants only)
    protected double paramX = Constants.PARAM_INITVALUE;  // metabolic rate      
    //link parameters are animal only
    protected List<ParamValue> paramE = new ArrayList<ParamValue>();
    ;  // assimilation efficiency
    protected List<ParamValue> paramD = new ArrayList<ParamValue>();
    ;  // predator interference     
    protected List<ParamValue> paramQ = new ArrayList<ParamValue>();
    ;  // functional response control parameter      
    protected List<ParamValue> paramA = new ArrayList<ParamValue>();
    ;  // functional response control parameter      
    protected List<ParamValue> paramB0 = new ArrayList<ParamValue>();
    ;  // relative half saturation density
    //Y, added by JTC
    protected List<ParamValue> paramY = new ArrayList<ParamValue>();
    ;  // max ingestion rate

    //10/28/14, jtc, added default node param fields to simplify retrieval
    protected double dfltK;
    protected double dfltR;
    protected double dfltX;
    
    /**
     * @param name
     * @param nodeIndex
     * @param speciesCount
     * @param perSpeciesBiomass
     * @param currentBiomass
     * @param type
     */
    public SpeciesZoneType(String name, int nodeIndex, int speciesCount,
            double perSpeciesBiomass, double currentBiomass,
            SpeciesTypeEnum type) {
        super();
        this.name = name;
        this.nodeIndex = nodeIndex;
        this.speciesCount = speciesCount;
        this.perSpeciesBiomass = perSpeciesBiomass;
        this.currentBiomass = currentBiomass;
        this.type = type;

        //4/20/14, JTC, moved the following lines from SimulationEngine.getBiomass()
        this.speciesType = ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(nodeIndex);
        
        if (perSpeciesBiomass == 0) {
            this.perSpeciesBiomass = speciesType.getBiomass();
        }
        if (name.isEmpty()) {
            this.name = speciesType.getName();
        }
        this.trophicLevel = speciesType.getTrophicLevel();
        this.lPredatorIndex = speciesType.getPredatorIndex();
        this.lPreyIndex = speciesType.getPreyIndex();
        this.type = speciesType.getOrganismType() == Constants.ORGANISM_TYPE_ANIMAL
                ? SpeciesTypeEnum.ANIMAL : SpeciesTypeEnum.PLANT;
        //5/6/14, JTC, previously only set for animals, but used by plants too
        //if (this.type == SpeciesTypeEnum.ANIMAL) {
        this.paramX = speciesType.getMetabolism();
        //}
        //5/6/14, JTC, added
        if (this.type == SpeciesTypeEnum.PLANT) {
            //JTC, not valid in current game environment as paramK controlled by 
            //zone carrying capacity.
            //JTC, 12/1/14, need this set for simulation experiments!!
            this.paramK = speciesType.getCarryingCapacity();
            this.paramR = speciesType.getGrowthRate();
        }
        this.dfltK = paramK;
        this.dfltR = paramR;
        this.dfltX = paramX;

        this.speciesCount = calcSpeciesCount();
    }

    //10/27/14, jtc, create copy of SZT
    public SpeciesZoneType(SpeciesZoneType sourceSZT) {
        this.name = sourceSZT.name;
        this.nodeIndex = sourceSZT.nodeIndex;
        /* not currently used
         this.lPreyIndex;
         this.lPredatorIndex;
         this.lPrey;
         this.lPredator;
         */
        this.speciesCount = sourceSZT.speciesCount;
        this.perSpeciesBiomass = sourceSZT.perSpeciesBiomass;
        this.currentBiomass = sourceSZT.currentBiomass;
        this.type = sourceSZT.type;
        this.trophicLevel = sourceSZT.trophicLevel;
        this.biomassUpdated = sourceSZT.biomassUpdated;
        this.paramUpdated = sourceSZT.paramUpdated;
        this.speciesType = sourceSZT.speciesType;
        this.paramK = sourceSZT.paramK;  // carrying capacity (plants only)
        this.paramR = sourceSZT.paramR;  // growth rate (plants only)
        this.paramX = sourceSZT.paramX;  // metabolic rate   
    /* not currently used
         this.paramE = new ArrayList<ParamValue>();;  // assimilation efficiency
         this.paramD = new ArrayList<ParamValue>();;  // predator interference     
         this.paramQ = new ArrayList<ParamValue>();;  // functional response control parameter      
         this.paramA = new ArrayList<ParamValue>();;  // relative half saturation density
         this.paramY = new ArrayList<ParamValue>();;  // max ingestion rate
         */
        this.dfltK = sourceSZT.dfltK;
        this.dfltR = sourceSZT.dfltR;
        this.dfltX = sourceSZT.dfltX;
    }

    /**
     *
     */
    public SpeciesZoneType() {
        super();
    }

    /**
     * getter for speciesType. 4/20/14, JTC.
     *
     * @return SpeciesType
     */
    public SpeciesType getSpeciesType() {
        return speciesType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nodeIndex
     */
    public int getNodeIndex() {
        return nodeIndex;
    }

    /**
     * @param nodeIndex the nodeIndex to set
     */
    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    /**
     * @return the lPreyIndex
     */
    public List<Integer> getlPreyIndex() {
        return lPreyIndex;
    }

    /**
     * @param lPreyIndex the lPreyIndex to set
     */
    public void setlPreyIndex(List<Integer> lPreyIndex) {
        this.lPreyIndex = lPreyIndex;
    }

    /**
     * @return the lPredatorIndex
     */
    public List<Integer> getlPredatorIndex() {
        return lPredatorIndex;
    }

    /**
     * @param lPredatorIndex the lPredatorIndex to set
     */
    public void setlPredatorIndex(List<Integer> lPredatorIndex) {
        this.lPredatorIndex = lPredatorIndex;
    }

    /**
     * @return the speciesCount
     */
    public int getSpeciesCount() {
        return speciesCount;
    }

    /**
     * @param speciesCount the speciesCount to set
     */
    public void setSpeciesCount(int speciesCount) {
        this.speciesCount = speciesCount;
    }

    /**
     * @return the perSpeciesBiomass
     */
    public double getPerSpeciesBiomass() {
        return perSpeciesBiomass;
    }

    /**
     * @param perSpeciesBiomass the perSpeciesBiomass to set
     */
    public void setPerSpeciesBiomass(double perSpeciesBiomass) {
        this.perSpeciesBiomass = perSpeciesBiomass;
        speciesCount = calcSpeciesCount();
    }

    /**
     * @return the currentBiomass
     */
    public double getCurrentBiomass() {
        return currentBiomass;
    }

    /**
     * @param currentBiomass the currentBiomass to set
     */
    public void setCurrentBiomass(double currentBiomass) {
        this.currentBiomass = Math.ceil(currentBiomass);
        speciesCount = calcSpeciesCount();  //JTC
        //TODO (JTC): error checking for plants that currentBiomass <= 
        //param K (carrying capacity)
    }

    public boolean isBiomassUpdated() {
        return this.biomassUpdated;

    }

    public void setBiomassUpdated(boolean val) {
        this.biomassUpdated = val;
    }

    public void setParamUpdated(boolean val) {
        this.paramUpdated = val;
    }

    /**
     * @return the lPrey
     */
    public List<SpeciesType> getlPrey() {
        return lPrey;
    }

    /**
     * @param lPrey the lPrey to set
     */
    public void setlPrey(List<SpeciesType> lPrey) {
        this.lPrey = lPrey;
    }

    /**
     * @return the lPredator
     */
    public List<SpeciesType> getlPredator() {
        return lPredator;
    }

    /**
     * @param lPredator the lPredator to set
     */
    public void setlPredator(List<SpeciesType> lPredator) {
        this.lPredator = lPredator;
    }

    /**
     * @return the type
     */
    public SpeciesTypeEnum getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(SpeciesTypeEnum type) {
        this.type = type;
    }

    public double getTrophicLevel() {
        return trophicLevel;
    }

    public double setTrophicLevel(double trophicLevel) {
        return this.trophicLevel = trophicLevel;
    }

    public double getParamK() {
        return paramK;
    }

    public void setParamK(double paramK) {
        this.paramK = paramK;
    }

    /**
     * Do not use in current game environment. paramK controlled by zone
     * carrying capacity. May still be useful in simulation environment. Resets
     * ParamK to value stored in Species table.
     */
    public void resetParamK() {
        if (type == SpeciesTypeEnum.PLANT) {
            paramK = speciesType.getCarryingCapacity();
        } else {
            paramK = Constants.PARAM_INITVALUE;
        }
    }

    public double getParamR() {
        return paramR;
    }

    //5/6/14, JTC, added check for validity
    public void setParamR(double paramR) {
//        if (paramR <= 1.0) {   //9/29/14, JTC, commented out; not normalized
        this.paramR = paramR;
//        }
    }

    /**
     * Resets paramR to value stored in Species table. 5/6/14, JTC
     */
    public void resetParamR() {
        if (type == SpeciesTypeEnum.PLANT) {
            paramR = speciesType.getGrowthRate();
        } else {
            paramR = Constants.PARAM_INITVALUE;
        }
    }

    public double getParamX() {
        return paramX;
    }

    //5/6/14, JTC, added check for validity
    public void setParamX(double paramX) {
//        if (paramX <= 1.0) {   //9/29/14, JTC, commented out; not normalized??
        this.paramX = paramX;
//        }
    }

    /**
     * Resets paramX to value stored in Species table. 5/6/14, JTC
     */
    public void resetParamX() {
        paramX = speciesType.getMetabolism();
    }

    public List<ParamValue> getParamA() {
        return paramA;
    }

    public void setParamA(List<ParamValue> paramA) {
        this.paramA = paramA;
    }

    public ParamValue getParamA(int preyIdx) {
        if (paramA == null) {
            return null;
        }

        for (ParamValue val : paramA) {
            if (val.getPreyIdx() == preyIdx) {
                return val;
            }
        }
        return null;
    }

    public List<ParamValue> getParamD() {
        return paramD;
    }

    public void setParamD(List<ParamValue> paramD) {
        this.paramD = paramD;
    }

    public ParamValue getParamD(int preyIdx) {
        if (paramD == null) {
            return null;
        }

        for (ParamValue val : paramD) {
            if (val.getPreyIdx() == preyIdx) {
                return val;
            }
        }
        return null;
    }
    
    public List<ParamValue> getParamB0() {
        return paramB0;
    }

    public void setParamB0(List<ParamValue> paramB0) {
        this.paramB0 = paramB0;
    }
    
    public ParamValue getParamB0(int preyIdx) {
        if (paramB0 == null) {
            return null;
        }

        for (ParamValue val : paramB0) {
            if (val.getPreyIdx() == preyIdx) {
                return val;
            }
        }
        return null;
    }

    public List<ParamValue> getParamE() {
        return paramE;
    }

    public ParamValue getParamE(int preyIdx) {
        if (paramE == null) {
            return null;
        }

        for (ParamValue val : paramE) {
            if (val.getPreyIdx() == preyIdx) {
                return val;
            }
        }
        return null;
    }

    public void setParamE(List<ParamValue> paramE) {
        this.paramE = paramE;
    }

    public List<ParamValue> getParamQ() {
        return paramQ;
    }

    public void setParamQ(List<ParamValue> paramQ) {
        this.paramQ = paramQ;
    }

    public ParamValue getParamQ(int preyIdx) {
        if (paramQ == null) {
            return null;
        }

        for (ParamValue val : paramQ) {
            if (val.getPreyIdx() == preyIdx) {
                return val;
            }
        }
        return null;
    }

    /**
     * Add individual element to paramE list. 4/5/2014, JTC.
     *
     * @param pv
     */
    public void setParamE(ParamValue pv) {
        this.paramE.add(pv);
    }

    /**
     * Add individual element to paramD list. 4/5/2014, JTC.
     *
     * @param pv
     */
    public void setParamD(ParamValue pv) {
        this.paramD.add(pv);
    }

    /**
     * Add individual element to paramB0 list. HJR
     *
     * @param pv
     */
    public void setParamB0(ParamValue pv) {
        this.paramB0.add(pv);
    }
    
    /**
     * Add individual element to paramQ list. 4/5/2014, JTC.
     *
     * @param pv
     */
    public void setParamQ(ParamValue pv) {
        this.paramQ.add(pv);
    }

    /**
     * Add individual element to paramA list. 4/5/2014, JTC.
     *
     * @param pv
     */
    public void setParamA(ParamValue pv) {
        this.paramA.add(pv);
    }

    /**
     * Add individual element to paramY list. 4/5/2014, JTC.
     *
     * @param pv
     */
    public void setParamY(ParamValue pv) {
        this.paramY.add(pv);
    }

    /**
     * Getter for ParamY. 4/5/2014, JTC
     *
     * @return list of ParamValue objects
     */
    public List<ParamValue> getParamY() {
        return paramY;
    }

    /**
     * Getter for ParamY for particular prey species. 4/5/2014, JTC
     *
     * @param preyIdx
     * @return
     */
    public ParamValue getParamY(int preyIdx) {
        if (paramY == null) {
            return null;
        }

        for (ParamValue val : paramY) {
            if (val.getPreyIdx() == preyIdx) {
                return val;
            }
        }
        return null;
    }

    /**
     * calculate speciesCount. Derived from SimulationEngine.getBiomass() code.
     * 4/17/14, JTC
     *
     * @return
     */
    public int calcSpeciesCount() {
        if (perSpeciesBiomass > 0) {
            return currentBiomass < 1 ? 0
                    : (int) Math.round(currentBiomass / (perSpeciesBiomass));
        } else {
            return 0;
        }
    }

    /**
     * get Species ID for current node. 5/6/14, JTC.
     *
     * @return
     */
    public int getSpeciesIndex() {
        return speciesType.getID();
    }
    
    /**
     * get parameter value for specified node parameter field. Created to
     * facilitate getting of multiple node parameter types. 4/22/14, JTC Used in
     * Sim_Job environment.
     *
     * @param fldName
     * @return parameter value (double)
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public double getParamValue(String fldName)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return (Double) SpeciesZoneType.class.getDeclaredField(fldName).get(this);
    }

    /**
     * set parameter value for specified node parameter field. Created to
     * facilitate setting of multiple node parameter types. 4/22/14, JTC Used in
     * Sim_Job environment.
     *
     * @param fldName
     * @param value
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void setParamValue(String fldName, double value)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        SpeciesZoneType.class.getDeclaredField(fldName).set(this, value);
    }

    /**
     * get parameter value list for specified link parameter field. Created to
     * facilitate getting of multiple link parameter types. 4/22/14, JTC Used in
     * Sim_Job environment.
     *
     * @param fldName
     * @return List of ParamValue objects
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public List<ParamValue> getParamList(String fldName)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return (List<ParamValue>) SpeciesZoneType.class.getDeclaredField(fldName).get(this);
    }

    /**
     * add parameter list entry to specified link parameter field. Created to
     * facilitate setting of multiple link parameter types. 4/22/14, JTC Used in
     * Sim_Job environment.
     *
     * @param fldName
     * @param paramValue
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void addParamListEntry(String fldName, ParamValue paramValue)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        List<ParamValue> listPtr;
        listPtr = (List<ParamValue>) SpeciesZoneType.class.getDeclaredField(fldName).get(this);
        boolean add = listPtr.add(paramValue);
    }

    //getter for dfltK
    public double getDfltK() {
        return dfltK;
    }

    //getter for dfltR
    public double getDfltR() {
        return dfltR;
    }

    //getter for dfltX
    public double getDfltX() {
        return dfltX;
    }
}
