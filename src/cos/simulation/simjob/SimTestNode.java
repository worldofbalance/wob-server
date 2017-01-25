/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos.simulation.simjob;

import cos.simulation.ParamValue;

import java.util.ArrayList;
import java.util.List;

/**
 * SimTestNode contains new parameter values for nodes (species) that are 
 * different than current production environment.  For testing with Sim_Job
 * environment.  Data obtained from table simtest_node_params and read in by
 * SpeciesDAO.java.
 * 
 * @author Justina
 */
public class SimTestNode {
    protected int nodeId;
    protected int metType;
    protected int categoryId;
    protected double perUnitBiomass;
    protected double paramK;
    protected double paramR;
    protected double paramX;
    protected double aR;
    protected List<ParamValue> paramE = new ArrayList<ParamValue>();  // assimilation efficiency
    protected List<ParamValue> paramD = new ArrayList<ParamValue>();  // predator interference     
    protected List<ParamValue> paramQ = new ArrayList<ParamValue>();  // functional response control parameter      
    protected List<ParamValue> paramA = new ArrayList<ParamValue>();  // relative half saturation density
    protected List<ParamValue> paramY = new ArrayList<ParamValue>();  // max ingestion rate
    
    public SimTestNode(int nodeId) {
        this.nodeId = nodeId;
    }
    
    public SimTestNode(int nodeId, int metType, int categoryId, double perUnitBiomass,
                       double paramK, double paramR, double paramX, double a_r) {
        this.nodeId = nodeId;
        this.metType = metType;
        this.categoryId = categoryId;
        this.perUnitBiomass = perUnitBiomass;
        this.paramK = paramK;
        this.paramR = paramR;
        this.paramX = paramX;
        this.aR = aR;
    }  
    
    public int getMetType () {
        return metType;
    }
    public int getCategoryId () {
        return categoryId;
    }
    public double getPerUnitBiomass () {
        return perUnitBiomass;
    }
    public double getParamK () {
        return paramK;
    }
    public double getParamR () {
        return paramR;
    }
    public double getParamX () {
        return paramX;
    }
    public double getAR () {
        return aR;
    }

    public void setMetType (int metType) {
        this.metType = metType;
    }
    public void setCategoryId (int categoryId) {
        this.categoryId = categoryId;
    }
    public void setPerUnitBiomass (double perUnitBiomass) {
        this.perUnitBiomass = perUnitBiomass;
    }
    public void setParamK (double paramK) {
        this.paramK = paramK;
    }
    public void setParamR (double paramR) {
        this.paramR = paramR;
    }
    public void setParamX (double paramX) {
        this.paramX = paramX;
    }
    public void setAR (double aR) {
        this.aR = aR;
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

    //added 4/5/2014, JTC, add *individual elements* to param lists
    public void setParamE(ParamValue pv) {
        this.paramE.add(pv);
    }
    public void setParamD(ParamValue pv) {
        this.paramD.add(pv);
    }
    public void setParamQ(ParamValue pv) {
        this.paramQ.add(pv);
    }
    public void setParamA(ParamValue pv) {
        this.paramA.add(pv);
    }
    public void setParamY(ParamValue pv) {
        this.paramY.add(pv);
    }
    
    //added 4/5/2014, JTC, gets and sets for new params, param lists
    public List<ParamValue> getParamY() {
        return paramY;
    }
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
    
    /*attempt at binning trophic level 
    first attempt: binning by halves */
    public String getTrophicGroup (double trophicLevel) {
        return String.valueOf((double) ((int) (trophicLevel * 2.0)) / 2.0);
    }
    
    /*attempt to identify "competitor" species by diet and category
    (category ID actually stored in this object, just passing in for 
    transparency) */
    public String getCompetitorGroup (int categoryId, int dietType) {
        return String.valueOf(categoryId).concat(String.valueOf(dietType));
    }
}
