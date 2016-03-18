/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.atn;

import shared.metadata.Constants;
import shared.simulation.simjob.SimJobSZT;

/**
 *
 * @author justinacotter

 "i" refers to "root", or species being evaluated "j" refers to "child", or
 species as it relates to species i. This is a one-directional object; an
 equivalent object would hold information for the inverse relationship (j-i)

 ATNModel class holds relationship data for two species in ecosystem, including
 model parameter values for those species and current biomasses. This
 information is used to generate "contribution" (+/-) of species j to species
 i using the ATN model formulae.
 */
public class ATNModel {

    protected SimJobSZT jSZT;
    protected double jBiomass;
    protected double jBiomassOrig;
    protected double jContrib;
    protected SimJobSZT iSZT;
    protected double iBiomass;
    protected double iBiomassOrig;

    protected double funcRespJI;  //functional response - i pred
    protected double funcRespIJ;  //functional response - j pred

    protected double xI;
    //protected double rI;  //never used in biomass equations (rJ is)
    //protected double kI;  //never used in biomass equations (kJ is)
    protected double yIJ;
    //protected double eIJ;  //never used in biomass equations (eJI is)
    protected double dIJ;  //aka c
    protected double qIJ;
    protected double aIJ;
    protected double b0IJ;
    protected double hIJ;  //1 + qIJ

    protected double xJ;
    protected double rJ;
    protected double kJ;
    protected double yJI;
    protected double eJI;
    protected double dJI;  //aka c
    protected double qJI;
    protected double aJI;
    protected double b0JI;
    protected double hJI;  //1 + qJI

    public ATNModel(
            SimJobSZT iSZT,
            SimJobSZT jSZT,
            double iBiomass,
            double jBiomass,
            LinkParams lPs
    ) {
        this.jSZT = jSZT;
        this.iSZT = iSZT;
        this.jBiomass = jBiomass;
        this.iBiomass = iBiomass;
        funcRespJI = 0;
        funcRespIJ = 0;
        jContrib = 0;

        xJ = jSZT.getParamX();
        xI = iSZT.getParamX();
        rJ = jSZT.getParamR();
        kJ = jSZT.getParamK();
        
        yIJ = lPs.getParamY();
        dIJ = lPs.getParamD();
        qIJ = lPs.getParamQ();
        aIJ = lPs.getParamA();
        b0IJ = lPs.getParamB0();
        hIJ = 1 + qIJ;

        yJI = yIJ;
        //eJI value depends on PREY'S (I species for this constant) organism type
        eJI = (iSZT.getSpeciesType().getOrganismType() == Constants.ORGANISM_TYPE_PLANT)
                ? lPs.getParamEPlant()
                : lPs.getParamEAnimal();
        dJI = dIJ;
        qJI = qIJ;
        aJI = aIJ;
        b0JI = b0IJ;
        hJI = hIJ;

    }

    public double getJBiomass() {
        return jBiomass;
    }

    public double getFuncRespIJ() {
        return funcRespIJ;
    }

    public double getFuncRespJI() {
        return funcRespJI;
    }

    public void setFuncRespIJ(double iPreyBM, int omega) {
        double b0IJPowH = Math.pow(b0IJ, hIJ);
        funcRespIJ = (omega * Math.pow(jBiomass, hIJ))
                / (b0IJPowH + dIJ * iBiomass * b0IJPowH + omega * iPreyBM);
    }

    public void setFuncRespJI(double jPreyBM, int omega) {
        double b0JIPowH = Math.pow(b0JI, hJI);
        funcRespJI = (omega * Math.pow(iBiomass, hJI))
                / (b0JIPowH + dJI * jBiomass * b0JIPowH + omega * jPreyBM);
    }

    public double getPreyBMCalcDouble(double preyBM) {
        return Math.pow(preyBM, hIJ);
    }

    public double getJContrib() {
        return jContrib;
    }

    public double setJContrib(boolean self, double dT) {
        if (iBiomass == 0 || jBiomass == 0) {
            jContrib = 0;
            return 0;
        }
        jContrib = getJContribAsPrey(dT);
        jContrib += getJContribAsPred(dT);
        if (self) {
            jContrib += getJContribAsSelf(dT);
        }

        return jContrib;
    }

    //prey contribution is proportionate to the biomass * met rate of the 
    //*predator* (i)
    public double getJContribAsPrey(double dT) {
        double val = 0;
        return val
                + xI
                * iBiomass
                * funcRespIJ
                * yIJ
                * dT;
    }

    //predator contribution is negative - reducing biomass of prey
    public double getJContribAsPred(double dT) {
        double val = 0;
        return val
                - xJ
                * jBiomass
                * funcRespJI
                * yJI
                * dT
                / eJI;
    }

    public double getJContribAsSelf(double dT) {
        double val = 0;
        if (jSZT.getSpeciesType().getOrganismType() == Constants.ORGANISM_TYPE_PLANT) {
            //plant species lose mass due to metabolism
            val -= xJ * jBiomass;
            //plant species gain mass from implicit resources up to limiting
            //factor based on param K (note; must be normalized - same as biomass)
            val += rJ
                    * jBiomass
                    * (1.0f - jBiomass / (kJ / Constants.BIOMASS_SCALE));

        } else {
            //species lose mass via metabolism
            val -= xJ * jBiomass;
        }
        return val * dT;
    }

    //the following methods used an old version of the functionl response 
    //equation (Williams)
    public void setFuncRespIJ(double iPreyBM) {
        funcRespIJ = Math.pow(jBiomass / aIJ, hIJ)
                / (1 + dIJ * iBiomass + iPreyBM);
    }

    public void setFuncRespJI(double jPreyBM) {
        funcRespJI = Math.pow(iBiomass / aJI, hJI)
                / (1 + dJI * jBiomass + jPreyBM);
    }

}
