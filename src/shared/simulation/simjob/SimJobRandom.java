/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import shared.core.ServerResources;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import shared.metadata.Constants;
import shared.model.SpeciesType;
import shared.simulation.SpeciesZoneType;
import shared.util.Log;

/**
 * Contains the methods to generate a random simulation based on some underlying
 * rules to make the success of the simulation more likely, such as including
 * grass (amount specified by user), and random amounts of 3 additional species:
 * decaying material, a random herbivorous animal, and a random insect. User may
 * specify multiple random simulations, in which case a loop is run until the
 * specified number of simulations has been submitted. (Only one job is active
 * at any time.) Note: SpeciesZoneType container was set up as an ArrayList but
 * in retrospect should really be a HashMap.
 *
 * @author justinacotter
 */
public class SimJobRandom extends SimJob {

    private class RandomGen {

        private Random random;

        public RandomGen() {
            this(System.currentTimeMillis());
        }

        public RandomGen(long seed) {
            this.random = new Random(seed);
        }

        public synchronized double getNextDouble() {
            return random.nextDouble();
        }

        public synchronized double getNextDouble(double min, double max) {
            //convert 0.0:1.0 to min:max
            double scale = (max - min) / 1.0;  //range on java solution is 1 (1.0 - (0.0))
            //scale random value and add min
            return random.nextDouble() * scale + min;
        }

        public synchronized int getNextInt(int n) {
            return random.nextInt(n);
        }

        //get random normal value between min:max
        public synchronized double getGaussian(double min, double max, double mu) {
            //convert (approx) -3:+3 to min:max
            double scale = (max - min) / 6.0;  //range on java solution is 6 (3.0 - (-3.0)).
            //scale and shift from symmetry about 0 to symmetry about mu
            double gauss;
            do {
                gauss = random.nextGaussian() * scale + mu;
                //check for out-of-bounds as Gaussian can be outside of -3:+3 approx
            } while (gauss > max || gauss < min);

            return gauss;
        }

        //generate random gaussian value and convert to a skewed value centered
        //at variable min<=mu<=max
        public synchronized double getSkewedGaussian(double min,
                double max, double skewedMu, boolean allowMin) {
            double skewed;
            double stdMu = (max + min) / 2.0;
            double deltaMu = stdMu - skewedMu;
            do {
                //generate value and shift in direction of skewed mu
                skewed = getGaussian(min, max, stdMu) - deltaMu;
                //round to 2 digits
                skewed = Math.round(skewed * 100) / 100.0;

            } while ( //ignore anything outside of range due to skew
                    (skewed > max || skewed < min)
                    || //params cannot allow a zero value, so discard.
                    (!allowMin && skewed == min));

            return skewed;
        }

        //generate random value and convert to a skewed value centered at 
        //variable mu, where absMin<=mu<=absMax; only alow to range +/- .25
        public synchronized double getSkewedDouble50Pcnt(double absMin,
                double absMax, double skewedMu, boolean allowMin) {
            double skewed;
            double range = absMax - absMin;
            double min50Pcnt = Math.max(absMin, skewedMu - (range * 0.25));
            double max50Pcnt = Math.min(absMax, skewedMu + (range * 0.25));
            do {
                //generate value
                skewed = getNextDouble(min50Pcnt, max50Pcnt);
                //round to 2 digits
                skewed = Math.round(skewed * 100) / 100.0;

                //params cannot allow a zero value, so discard.
            } while (!allowMin && skewed == absMin);
            return skewed;
        }
    }
    
    private class SubNodes {
        List<Integer> nodeIds;
        List<Double> biomasses;
        
        public SubNodes () {
            nodeIds = new ArrayList<Integer>();
            biomasses = new ArrayList<Double>();
        }
        
        public void addNode (int nodeId, double biomass) {
            nodeIds.add(nodeId);
            biomasses.add(biomass);
        }
        
        public double getBiomass (int nodeIdx) {
            return biomasses.get(nodeIdx);
        }
        
        public int getNodeId (int nodeIdx) {
            return nodeIds.get(nodeIdx);
        }
    }

    enum RandomRule {

        HERBIVORE, INSECT, RANDOM
    }

    public static final double DFLT_PP_TOTAL_BIOMASS = 0;   //2000.0;
    public static final double DFLT_PP_PER_UNIT_BIOMASS = 1;
    public static final double DFLT_PP_PARAMK = 2000.0;  //10000.0;  //k=carrying capacity  (SEConfig properties dflt = 1.0)
    public static double DFLT_PP_PARAMX = 0.5;  //x=metabolic rate (init 0.5, from SEConfig properties)
    public static double DFLT_PP_PARAMR = 0.5;  //r=growth rate (init 0.5, reduced from SEConfig properties - 1.0)
    public static final int DFLT_TIMESTEPS = 400;
    
    //minimal biomass value
    public static final double MIN_BIOMASS = 25.0f;
    public static final double DFLT_MAX_BIOMASS = 4000;
    
    //PP = PRIMARY PRODUCER (AKA GRASS)
    public static final int DFLT_MIN_SPECIES = 10;
    public static final int DFLT_MAX_SPECIES = 30;

    public static final int[] PLANT_SPECIES_ID = {1007, 1008, 1009};
    public static final int DECAY_SPECIES_ID = 89;

    //normalized parameter range
    public static final double PARAM_MIN = 0.0;
    public static final double PARAM_MAX = 1.0;
    public static final boolean ALLOW_MIN = true;
    public static final boolean GAUSSIAN_PARAM_VAL = false;
    public static final boolean RANDOM_PARAM_VAL = false;

    public static final boolean DFLT_CREATEBASE = false;
    public static final boolean DFLT_RANDOMGRASS = true;
    public static final boolean DFLT_PRUNE = true;

    private double ppTotalBiomass, ppPerUnitBiomass;
    private double ppAR, ppFR, ppParamX, ppParamK, ppParamR;
    private double maxBiomass;
    private int minSpecies, maxSpecies;
    
    private Map<Integer,SubNodes> subNodeMap;

    private RandomGen rand;
    /*9/29/14, jtc, make it optional to include base ecosystem species.  The functionality
     exists to increase likelihood of viable predator/prey relationships into the i
     initial ecosystem*/
    private boolean createBaseEcosys = DFLT_CREATEBASE;
    //11/9/14, jtc, add new options as follows:
    //prune animal species with no prey connections to basal species
    private boolean prune = DFLT_PRUNE;
    private boolean randomGrassBiomass = DFLT_RANDOMGRASS;

    public SimJobRandom() {
        super();
    }

    //copy a SimJob
    public SimJobRandom(SimJobRandom srcJob) {
        super(srcJob);
        this.subNodeMap = new HashMap<Integer,SubNodes> ();

        this.ppTotalBiomass = srcJob.ppTotalBiomass;
        this.ppPerUnitBiomass = srcJob.ppPerUnitBiomass;
        this.ppAR = srcJob.ppAR;
        this.ppFR = srcJob.ppFR;
        this.ppParamX = srcJob.ppParamX;
        this.ppParamK = srcJob.ppParamK;
        this.ppParamR = srcJob.ppParamR;
        this.maxBiomass = srcJob.maxBiomass;
        this.minSpecies = srcJob.minSpecies;
        this.maxSpecies = srcJob.maxSpecies;
    }

    /*one species has multiple nodes; need to break into individual nodes for use
     by simulation engine. */
    private List<SpeciesZoneType> addNodesFromRandomSpecies(int species_id, double biomass) {
        //9/25/14, JTC, integration with Gary's code (ServerResources)
        SpeciesType st = ServerResources.getSpeciesTable().getSpecies(species_id);
        int nodeId;
        double perUnitBiomass;
        float distrib;
        SimTestNode stn;
        List<SpeciesZoneType> sztList = new ArrayList<SpeciesZoneType>();
        SimJobSZT szt;
        try {
            boolean multiNode = st.getNodeDistribution().entrySet().size() > 1;
            if (multiNode) {
                subNodeMap.put(species_id, new SubNodes());
            }
            for (Map.Entry<Integer, Float> nodeDistr : st.getNodeDistribution().entrySet()) {
                nodeId = nodeDistr.getKey();
                distrib = nodeDistr.getValue();
                stn = st.getSimTestNode(nodeId);
                perUnitBiomass = stn.getPerUnitBiomass();
                
                /*if node already exists (should only happen for multi-node plants),
                 just update biomass - actually shouldn't happen at all with most
                 recent implementation */
                szt = getSpeciesZoneByNodeId(nodeId);
                if (szt != null) {
                    szt.setCurrentBiomass(szt.getCurrentBiomass() + biomass * distrib);
                } else {
                    szt = new SimJobSZT("", nodeId, 0, perUnitBiomass,
                            biomass * distrib, null, getUseSimTestNodeVals());
                    sztList.add(szt);
                }
                if (multiNode) {
                    subNodeMap.get(species_id).addNode(nodeId, biomass * distrib);
                }
                }
        } catch (Exception ex) {
            Log.printf_e("SimJobRandom.addNodesFromRandomSpecies() Error:\n%s", ex.getMessage());
            ex.printStackTrace();

        }
        return sztList;
    }
    
    private boolean pruneRandomSimJob() throws SQLException {

        //first, generate consume info
        int[] nodeIdArray = getSpeciesNodeList();
        ConsumeMap consumeMap = new ConsumeMap(nodeIdArray, Constants.ECOSYSTEM_TYPE);
        boolean altered;

        //too processes: 1) does every animal species have prey, and 2) does
        //every species connect to the primary producer.  Need to repeat both until
        //there are no further removals.
        do {
            altered = false;
            //if node is an animal check that it has prey (other than self); need
            //to loop until no more species are removed
            do {
                altered = false;
                for (int nodeId : nodeIdArray) {
                    if (getSpeciesZoneByNodeId(nodeId).getType()
                            != SpeciesZoneType.SpeciesTypeEnum.ANIMAL) {
                        continue;
                    }
                    List<Integer> preyList = consumeMap.getPreyList(nodeId);
                    //remove if no prey
                    if (preyList.isEmpty()) {
                        removeSimJobSZT(getSpeciesZoneByNodeId(nodeId));
                        altered = true;
                        continue;
                    }
                    //remove if only prey is self (cannibal)
                    if (preyList.size() == 1 && preyList.get(0) == nodeId) {
                        removeSimJobSZT(getSpeciesZoneByNodeId(nodeId));
                        altered = true;
                    }
                }
                if (altered) {  //regenerate map so that references are current
                    nodeIdArray = getSpeciesNodeList();
                    consumeMap = new ConsumeMap(nodeIdArray, Constants.ECOSYSTEM_TYPE);
                }
            } while (altered);

            //loop through nodes; if path list is empty for PP and current node (i.e. no connection), 
            //remove the node from the ecosystem
            //NOTE: do this after prior step of removing animals with no prey; pathTable
            //recursion is time consuming
            PathTable pathTable = new PathTable(consumeMap, nodeIdArray, PathTable.PP_ONLY);
            for (int nodeId : nodeIdArray) {
                if (nodeId == Constants.PP_NODE_ID) {
                    continue;
                }
                //check for paths between the two.  If empty, remove node
                //ignore "secondary" plant nodes: 2,3,4 (and 5 - the primary producer
                if ((nodeId > 5 || nodeId < 2) && pathTable.getPathArrayIJ(Constants.PP_NODE_ID, nodeId).
                        getPathList().isEmpty()) {
                    SubNodes subNodes = subNodeMap.get(getSpeciesZoneByNodeId(nodeId).getSpeciesIndex());
                    if (subNodes != null) {                
                        for (int i = 0; i < subNodes.nodeIds.size(); i++) {
                            SimJobSZT sjszt = getSpeciesZoneByNodeId(subNodes.getNodeId(i));
                            if (subNodes.getBiomass(i) == sjszt.getCurrentBiomass()) {
                                removeSimJobSZT(sjszt);
                            } else {
                                sjszt.setCurrentBiomass(sjszt.getCurrentBiomass() - subNodes.getBiomass(i));
                            }
                        }
                    } else {
                        removeSimJobSZT(getSpeciesZoneByNodeId(nodeId));
                    }
                    altered = true;
                }
            }
            if (altered) {  //regenerate map so that references are current
                nodeIdArray = getSpeciesNodeList();
                consumeMap = new ConsumeMap(nodeIdArray, Constants.ECOSYSTEM_TYPE);
            }
        } while (altered);

        return nodeIdArray.length >= minSpecies;
    }

    /*configure a random job with a few rules: must include grass, must include a vertebrate
     herbivore, must include an insect*/
    public void configRandomSimJob() throws SQLException {
        boolean success;

        //set biomass for primary producer
        rand = new RandomGen();
        if (randomGrassBiomass) {
            ppTotalBiomass += getRandomBiomass();
        }
        int speciesCnt = rand.getNextInt(maxSpecies - minSpecies + 1) + minSpecies;
        do {
            success = true;
            //9/25/14, JTC, integration with Gary's code (ECOSYSTEM_TYPE)
            List<Integer> speciesIdList = SpeciesType.getSpeciesIdList("", Constants.ECOSYSTEM_TYPE);
            speciesZoneList = new ArrayList<SpeciesZoneType>();

            //add primary producer information (w/ random biomass if so specified)
            speciesZoneList.addAll(addNodesFromRandomSpecies(Constants.PP_SPECIES_ID,
                    ppTotalBiomass));
            //job may have overrides for default values for per-unit-biomass, param K (carrying capacity)
            getSpeciesZoneByNodeId(Constants.PP_NODE_ID).setPerSpeciesBiomass(ppPerUnitBiomass);
            getSpeciesZoneByNodeId(Constants.PP_NODE_ID).setParamK(ppParamK);

            int entries = 1;
            //9/29/14, jtc, if flag is set to create base ecosystem for random sim jobs, do so.
            if (createBaseEcosys) {
            //select an herbivore
                //9/25/14, JTC, integration with Gary's code (ECOSYSTEM_TYPE)
                speciesZoneList.addAll(addNodesFromRandomSpecies(
                        getRandomSpeciesNode(
                                SpeciesType.getSpeciesIdList(
                                        "`diet_type`=2 AND (`category`='Large Animal' OR `category`='Small Animal')",
                                        Constants.ECOSYSTEM_TYPE)),
                        getRandomBiomass()));

            //select an insect
                //9/25/14, JTC, integration with Gary's code (ECOSYSTEM_TYPE)
                speciesZoneList.addAll(addNodesFromRandomSpecies(
                        getRandomSpeciesNode(
                                SpeciesType.getSpeciesIdList("`category`='Insect'", Constants.ECOSYSTEM_TYPE)),
                        getRandomBiomass()));

                //select a random non-pp plant species
                speciesZoneList.addAll(addNodesFromRandomSpecies(
                        PLANT_SPECIES_ID[rand.getNextInt(PLANT_SPECIES_ID.length)],
                        getRandomBiomass()));

                //select some decaying matter
                speciesZoneList.addAll(addNodesFromRandomSpecies(DECAY_SPECIES_ID,
                        getRandomBiomass()));

                entries += 4;
            }
            //select random species and random biomass for that species
            while (entries < speciesCnt) {
                speciesZoneList.addAll(addNodesFromRandomSpecies(
                        getRandomSpeciesNode(speciesIdList), getRandomBiomass()));
                entries++;
            }
        //initialize all animal metabolic rates via calculation
        /*10/5/14, JTC, want to use met rate in species table; loaded via SZT by
             default.
             job.setSpeciesZoneListAnimalParamX();
             */

        //prune species that don't have prey, or don't have a pred/prey path to 
            //primary producer
            if (prune) {
                success = pruneRandomSimJob();
                if (!success) {
                    System.out.printf("Failed to create ecosystem of %d nodes.  Reinitializing...\n", minSpecies);
                }
            }
        } while (!success);

        //randomize (skewed distribution) node parameters: x, r, k
        //for x,r (normalized) dlft param value is used as mean and distribution
        //is skewed based on that.
        int[] nodeIdArray = getSpeciesNodeList();
        for (int nodeId : nodeIdArray) {
            SimJobSZT szt = getSpeciesZoneByNodeId(nodeId);
            double min, paramVal;
            if (szt.getType() == SpeciesZoneType.SpeciesTypeEnum.PLANT) {
                //param K is not normalized; used dflt param as mean and derive min and
                //max based on current biomass
                //3/2/15 - because some plant nodes are components of multiple species that
                //may randomly be selected, "min", i.e. rand biomass, is not directly controllable.
                //t/f, let range be from min to + dflt param val, w/ mu = larger of the two
                min = szt.getCurrentBiomass();
                if (GAUSSIAN_PARAM_VAL) {
                    double mu = Math.max(min, szt.getParamK());
                    paramVal = Math.round(rand.getGaussian(min, szt.getParamK() + mu,
                            mu));
//                    paramVal = Math.round(rand.getGaussian(min, 2 * szt.getParamK() - min,
//                            szt.getParamK()));
                } else {
                    //must use for RANDOM_PARAM_VAL or not, as there can be no default value
                    //due to random initial biomass
                    paramVal = Math.round(rand.getNextDouble(min, 2 * szt.getParamK() + min));
//                    paramVal = Math.round(rand.getNextDouble(min, 2 * szt.getParamK() - min));
                }
                System.out.format("K[%d] paramVal=%10.3f, dlft=%10.3f, biomass=%10.3f\n",
                        szt.getNodeIndex(), paramVal, szt.getParamK(), min);
                szt.setParamK(paramVal);
                if (GAUSSIAN_PARAM_VAL) {
                    paramVal = rand.getSkewedGaussian(PARAM_MIN, PARAM_MAX,
                            szt.getParamR(), !ALLOW_MIN);
                } else if (RANDOM_PARAM_VAL) {
                    paramVal = rand.getSkewedDouble50Pcnt(PARAM_MIN, PARAM_MAX,
                            szt.getParamR(), !ALLOW_MIN);
                } else {
                    paramVal = DFLT_PP_PARAMR;
                }
                System.out.format("R[%d] paramVal=%10.3f, dlft=%10.3f\n",
                        szt.getNodeIndex(), paramVal, szt.getParamR());
                szt.setParamR(paramVal);
                
                //animals
            } else {
                paramVal = szt.getParamX();  //dflt = calculated value
                if (GAUSSIAN_PARAM_VAL) {
                    paramVal = rand.getSkewedGaussian(PARAM_MIN, PARAM_MAX,
                            szt.getParamX(), !ALLOW_MIN);
                } else if (RANDOM_PARAM_VAL) {
                    paramVal = rand.getSkewedDouble50Pcnt(PARAM_MIN, PARAM_MAX,
                            szt.getParamX(), !ALLOW_MIN);
                }
                System.out.format("X[%d] paramVal=%10.3f, dlft=%10.3f\n",
                        szt.getNodeIndex(), paramVal, szt.getParamX());
                szt.setParamX(paramVal);
            }
        }

        System.out.printf("Created %d nodes.\n", speciesZoneList.size());
    }

    private int getRandomBiomass() {
        return (int)rand.getNextDouble(MIN_BIOMASS, maxBiomass);
    }
    
    private int getRandomSpeciesNode(List<Integer> speciesIdList) {
        int speciesId = 0;
        boolean inUse = true;

        //make sure species hasn't already been selected
        while (inUse) {
            speciesId = speciesIdList.get(rand.getNextInt(speciesIdList.size()));
        
            //check to see if any of species nodes have already been selected.
            //(1-1 relationship for animals, but not plants).
            //Can't have more than one tree, as they become generic nodes, which
            //causes them to add up to extraordinarily large initial biomasses
            //if multiple trees are selected.  It is also possible for member nodes
            //of trees to be randomly selected independently of trees.
            SpeciesType st = ServerResources.getSpeciesTable().getSpecies(speciesId);
            for (Map.Entry<Integer, Float> nodeDistr : st.getNodeDistribution().entrySet()) {
                inUse = getSpeciesZoneByNodeId(nodeDistr.getKey()) != null;
                if (inUse) {
                    break;
                }
            }
        }
        return speciesId;
    }

    public void setCreateBaseEcosys(boolean base) {
        this.createBaseEcosys = base;
    }

    public void setRandomGrassBiomass(boolean randomGrass) {
        this.randomGrassBiomass = randomGrass;
    }

    public void setPrune(boolean prune) {
        this.prune = prune;
    }

    public boolean getCreateBaseEcosys() {
        return this.createBaseEcosys;
    }

    public boolean getRandomGrassBiomass() {
        return this.randomGrassBiomass;
    }

    public boolean prune() {
        return this.prune;
    }

    public void setPpTotalBiomass(double ppTotalBiomass) {
        this.ppTotalBiomass = ppTotalBiomass;
    }

    public void setPpPerUnitBiomass(double ppPerUnitBiomass) {
        this.ppPerUnitBiomass = ppPerUnitBiomass;
    }

    public void setPpAR(double ppAR) {
        this.ppAR = ppAR;
    }

    public void setPpFR(double ppFR) {
        this.ppFR = ppFR;
    }

    public void setPpParamX(double ppParamX) {
        this.ppParamX = ppParamX;
    }

    public void setPpParamK(double ppParamK) {
        this.ppParamK = ppParamK;
    }

    public void setPpParamR(double ppParamR) {
        this.ppParamR = ppParamR;
    }

    public void setMaxBiomass(double maxBiomass) {
        this.maxBiomass = maxBiomass;
    }

    public void setMinSpecies(int minSpecies) {
        this.minSpecies = minSpecies;
    }

    public void setMaxSpecies(int maxSpecies) {
        this.maxSpecies = maxSpecies;
    }

    public double getPpTotalBiomass() {
        return ppTotalBiomass;
    }

    public double getPpPerUnitBiomass() {
        return ppPerUnitBiomass;
    }

    public double getPpAR() {
        return ppAR;
    }

    public double getPpFR() {
        return ppFR;
    }

    public double getPpParamX() {
        return ppParamX;
    }

    public double getPpParamK() {
        return ppParamK;
    }

    public double getPpParamR() {
        return ppParamR;
    }

    public double getMaxBiomass() {
        return maxBiomass;
    }

    public int getMinSpecies() {
        return minSpecies;
    }

    public int getMaxSpecies() {
        return maxSpecies;
    }

}
