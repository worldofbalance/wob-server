package shared.model;

import java.util.HashMap;
import java.util.Map;
import shared.metadata.Constants;
import shared.simulation.SpeciesZoneType;
import shared.simulation.SpeciesZoneType.SpeciesTypeEnum;

/**
 * ZoneNodes was created to allow persistent SpeciesZoneType HashMap for each
 * player, so that players or the system could make modifications to their
 * species parameters, currently carrying capacity (plants), growth rate
 * (plants) and metabolic rate (all).
 * A major element of this class has become management of plant node carrying
 * capacity param k).  This requirement was realized late in the development cycle, 
 * but plant capacity must reflect the sum of player's tiles' carrying capacities.
 * At this time, the carrying capacity is implemented as follows.  Based on the
 * total carrying capacity (stored in Zone class, but reproduced locally here),
 * the plant nodes evenly divide the carrying capacity.  This means that every
 * time a plant node is added or removed, or the carrying capacity of the player
 * changes, all plant nodes' carrying capacities must be adjusted.  For this reason
 * a separate plantNodes HashMap is maintained to facilitate these manipulations.
 *
 * @author Justina
 */
public class ZoneNodes {

    private Map<Integer, SpeciesZoneType> nodes = new HashMap<Integer, SpeciesZoneType>();
    private Map<Integer, SpeciesZoneType> plantNodes = new HashMap<Integer, SpeciesZoneType>();
    private double k = 10000, kPerNode = 10000;

    /**
     * Constructor creates empty HashMaps.
     */
    public ZoneNodes() {
    }

    /**
     * getter for nodes.
     *
     * @return Map<Integer, SpeciesZoneType> all nodes
     */
    public Map<Integer, SpeciesZoneType> getNodes() {
        return (Map) nodes;
    }

    /**
     * set carrying capacity for plants. Needs to be called every time a plant
     * is added or removed or the carrying capacity is updated..
     */
    private void setPlantNodesCarryingCapacity() {
        if (!plantNodes.isEmpty() && kPerNode != (k / plantNodes.size())) {
            kPerNode = k / plantNodes.size();
            for (SpeciesZoneType szt : plantNodes.values()) {
                szt.setParamK(kPerNode);
                //TODO (JTC): error checking for plants that currentBiomass <= 
                //param K (carrying capacity)
            }
        }
    }

    /**
     * Setter for carrying capacity (k) for entire zone (plant specific). Must
     * call setPlantNodesCarryingCapacity to update individual plant nodes.
     *
     * @param k
     */
    public void setCarryingCapacity(double k) {
        this.k = k;
        setPlantNodesCarryingCapacity();
    }

    /*Check to see if new node is a plant node.  If so, add to plantNodes HashMap
    and adjust all plant nodes' carrying capacity*/
    private void addPlantNode(int node_id, SpeciesZoneType szt) {
        if (szt.getType() == SpeciesTypeEnum.PLANT) {
            plantNodes.put(node_id, szt);
            setPlantNodesCarryingCapacity();
        }
    }

    /*Remove plant node from plantNodes HashMap.  Make call to adjust other
    plants carrying capacity.
    */
    private void removePlantNode(int node_id) {
        SpeciesZoneType szt = plantNodes.get(node_id);
        if (szt != null && szt.getType() == SpeciesTypeEnum.PLANT) {
            plantNodes.remove(node_id);
            setPlantNodesCarryingCapacity();
        }
    }

    /**
     * Add additional node to HashMap.  Note, have to add plant nodes to plant-node
     * HashMap.
     * @param node_id
     * @param szt
     */
    public void addNode(int node_id, SpeciesZoneType szt) {
        nodes.put(node_id, szt);
        addPlantNode(node_id, szt);
    }

    /**
     * Add additional nodes to HashMap.
     * @param sztMap
     */
    public void addNodes(Map<Integer, SpeciesZoneType> sztMap) {
        for (SpeciesZoneType szt : sztMap.values()) {
            addNode(szt.getNodeIndex(), szt);
        }
    }

    /**
     * Set HashMap nodes to submitted parameter.  Note, have to add plant
     * nodes to plant-node HashMap.
     * @param sztMap
     */
    public void setNodes(Map<Integer, SpeciesZoneType> sztMap) {
        nodes = sztMap;
        for (SpeciesZoneType szt : sztMap.values()) {
            addPlantNode(szt.getNodeIndex(), szt);
        }
    }

    /**
     * Delete node from HashMap.  Note, also have to remove from plantNodes 
     * HashMap.
     * @param node_id
     */
    public void removeNode(int node_id) {
        if (nodes.get(node_id) != null) {
            nodes.remove(node_id);
        }
        removePlantNode(node_id);
    }

    /*there can be more than one node per species id, loop throuh to find all
    and return HashMap of found nodes*/
    private Map<Integer, SpeciesZoneType> getNodeBySpeciesId(int species_Id) {
        Map<Integer, SpeciesZoneType> sztMap = new HashMap<Integer, SpeciesZoneType>();
        for (SpeciesZoneType szt : nodes.values()) {
            if (szt.getSpeciesIndex() == species_Id) {
                sztMap.put(szt.getNodeIndex(), szt);
            }
        }
        return sztMap;
    }

    //set specified parameter
    private void setNodeParamForSZT(SpeciesZoneType szt,
            int param, double value) {
        switch (param) {
            case Constants.PARAM_MET_RATE:
                szt.setParamX(value);
                szt.setParamUpdated(true);
                break;
            case Constants.PARAM_GROWTH_RATE:
                if (szt.getType() == SpeciesZoneType.SpeciesTypeEnum.PLANT) {
                    szt.setParamR(value);
                    szt.setParamUpdated(true);
                }
                break;
        }
    }

    /**
     * Set indicated parameter value for all species in zone of specified type.
     * @param param
     * @param value
     * @param type
     */
        public void setNodeParam(int param, double value, SpeciesTypeEnum type) {
        for (SpeciesZoneType szt : nodes.values()) {
            if (szt.getType() == type) {
                setNodeParamForSZT(szt, param, value);
            }
        }
    }

    /**
     * Set indicated parameter value for single species in zone.  Note, may be 
     * multiple nodes per species.
     * @param param
     * @param value
     * @param species_id
     */
        public void setNodeParam(int param, double value, int species_id) {
        Map<Integer, SpeciesZoneType> nodes = getNodeBySpeciesId(species_id);
        //there can be more than one node per species id, loop throuh to set all
        for (SpeciesZoneType szt : nodes.values()) {
            setNodeParamForSZT(szt, param, value);
        }
    }

    /**
     * Get indicated parameter value for single species in zone.  Note, may be
     * multiple nodes per species.
     * @param param
     * @param species_id
     * @return parameter value (double)
     */
        public double getNodeParam(int param, int species_id) {
        Map<Integer, SpeciesZoneType> nodes = getNodeBySpeciesId(species_id);
        //really only need first instance; all nodes w/in a species share same values
        for (SpeciesZoneType szt : nodes.values()) {
            switch (param) {
                case Constants.PARAM_MET_RATE:
                    return szt.getParamX();
                case Constants.PARAM_GROWTH_RATE:
                    return szt.getParamR();
                default:

            }
        }
        return Constants.PARAM_INITVALUE;
    }

    //reset specified parameter
    private void resetNodeParamForSZT(SpeciesZoneType szt, int param) {
        switch (param) {
            case Constants.PARAM_MET_RATE:
                szt.resetParamX();
                szt.setParamUpdated(true);
                break;
            case Constants.PARAM_GROWTH_RATE:
                if (szt.getType() == SpeciesZoneType.SpeciesTypeEnum.PLANT) {
                    szt.resetParamR();
                    szt.setParamUpdated(true);
                }
                break;
        }
    }

    /**
     * Reset indicated parameter value to default for all species of specified type.
     * @param param
     * @param type
     */
        public void resetNodeParam(int param, SpeciesTypeEnum type) {
        for (SpeciesZoneType szt : nodes.values()) {
            if (szt.getType() == type) {
                resetNodeParamForSZT(szt, param);
            }
        }
    }

    /**
     * Reset indicated parameter value to default for single species in zone.  Note,
     * may be multiple nodes per species.
     * @param param
     * @param species_id
     */
        public void resetNodeParam(int param, int species_id) {
        Map<Integer, SpeciesZoneType> sztMap = getNodeBySpeciesId(species_id);
        //there can be more than one node per species id, loop throuh to set all
        for (SpeciesZoneType szt : sztMap.values()) {
            resetNodeParamForSZT(szt, param);
        }
    }

    //modify biomass by specified amount; set flag for getPrediction()
    private void modifyNodeBiomass(SpeciesZoneType szt, double modAmount) {
        szt.setCurrentBiomass(szt.getCurrentBiomass() + modAmount);
        szt.setBiomassUpdated(true);
    }

    //

    /**
     * Modify current biomass for all species in zone of specified type by specified 
     * amt (+/- fraction).
     * @param fraction
     * @param type
     */
        public void modifyNodeBiomassByFraction(double fraction, SpeciesTypeEnum type) {
        if (Math.abs(fraction) <= 1.0) {
            for (SpeciesZoneType szt : nodes.values()) {
                if (szt.getType() == type) {
                    modifyNodeBiomass(szt, fraction * szt.getCurrentBiomass());
                }
            }
        }
    }

    /**
     * Modify current biomass for single species in zone by specified amt.  Note, 
     * may be multiple nodes per species.
     * (+/- fraction).
     * @param fraction
     * @param species_id
     */
        public void modifyNodeBiomassByFraction(double fraction, int species_id) {
        if (Math.abs(fraction) <= 1.0) {
            Map<Integer, SpeciesZoneType> sztMap = getNodeBySpeciesId(species_id);
            for (SpeciesZoneType szt : sztMap.values()) {
                modifyNodeBiomass(szt, fraction * szt.getCurrentBiomass());
            }
        }
    }

    /**
     * Modify current biomass for all species in zone of specified type by specified
     * amt (+/-).
     * @param amount
     * @param type
     */
        public void modifyNodeBiomassByAmount(double amount, SpeciesTypeEnum type) {
        for (SpeciesZoneType szt : nodes.values()) {
            if (szt.getType() == type) {
                modifyNodeBiomass(szt, amount + szt.getCurrentBiomass());
            }
        }
    }

    /**
     * Modify current biomass for single species in zone by specified amt (+/-).
     * Note, may be multiple nodes per species.
     * @param amount
     * @param species_id
     */
        public void modifyNodeBiomassByAmount(double amount, int species_id) {
        Map<Integer, SpeciesZoneType> sztMap = getNodeBySpeciesId(species_id);
        for (SpeciesZoneType szt : sztMap.values()) {
            modifyNodeBiomass(szt, amount + szt.getCurrentBiomass());
        }
    }
}
