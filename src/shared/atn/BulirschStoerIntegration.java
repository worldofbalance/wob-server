/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.atn;

import java.util.Arrays;
import java.util.Map;
import shared.metadata.Constants;
import shared.simulation.simjob.SimJobSZT;

/**
 *
 * @author justinacotter
 *
 * Solution to approx solution of ODE y'(x) = f(x,y); unknown y(x); known f(x,y)
 * and init conds y = yOrig when x = xOrig. Variable step method using modified
 * midpoint method for step size h to determine y(x+h) using a polynomial
 * approximation to limit as h->0. Estimates error and adjusts step size to
 * reduce if necessary, i.e. Bulirsch-Stoer method.
 *
 * Based on method description at
 * http://apps.nrbook.com/empanel/index.html#pg=921
 */
public class BulirschStoerIntegration {

    static final int stepCnt[] = {2, 4, 6, 8, 10, 12, 14, 16, 18};
    static final int attemptCnt = stepCnt.length;

    double[] yNew;
    double xOrig;
    double hOrig;
    double hNew;
    double maxErr;
    double[] yOrig;
    double[][] contribs;
    Map<Integer, NodeRelationships> ecosysRelationships;
    final int speciesCnt;
    int[] speciesID;
    SimJobSZT[] sztArray;
    LinkParams lPs;
    ATNModel[][] atnModel;
    int err = 0;
    int debugIdx = 0;
    double[][] extrapArray;
    int attempts = 0;
    int equationSet;

    public BulirschStoerIntegration(
            double hOrig,
            int[] speciesID,
            SimJobSZT[] sztArray,
            Map<Integer, NodeRelationships> ecosysRelationships,
            LinkParams lPs,
            double maxErr,
            int equationSet
    ) {
        this.hOrig = hOrig;
        this.maxErr = maxErr;
        speciesCnt = speciesID.length;
        this.speciesID = speciesID;  //note: shallow copy
        this.sztArray = sztArray;  //note: shallow copy
        this.yOrig = new double[speciesCnt];
        this.ecosysRelationships = ecosysRelationships;
        this.lPs = lPs;
        this.equationSet = equationSet;

        atnModel = new ATNModel[speciesCnt][speciesCnt];
        yNew = new double[speciesCnt];
        contribs = new double[speciesCnt][speciesCnt];

    }

    public boolean performIntegration(double xOrig, double[] yOrig) {
        this.xOrig = xOrig;
        System.arraycopy(yOrig, 0, this.yOrig, 0, speciesCnt);

        double[] stepSize = new double[attemptCnt];
        double[] yOld = new double[speciesCnt];
        boolean estWithinErr = true;
        extrapArray = new double[speciesCnt][attemptCnt + 1];

        /* 
         Perform the first estimate of y(x+h), store the step size which
         was used plus the estimate; for subsequent call to the
         polynomial extrapolation function for the value of y(x+h)      
         */
        stepSize[0] = hOrig / (double) stepCnt[0];
        yNew = modMidpointEst(xOrig, stepCnt[0], stepSize[0]);
//        System.out.printf("after 1st midpt, yOrig[debugIdx]=%9.2f, stepCnt=%d, "
//                + "yNew[debugIdx]=%9.2f\n",
//                yOrig[debugIdx], stepCnt[0], yNew[debugIdx]);

        extrapolate(extrapArray, stepSize, 0);
//        System.out.printf("after extrap, i=%d, yNew=%9.2f, stepSize=%9.6f\n",
//                0, yNew[debugIdx], stepSize[0]);

        /* 
         Repeat with smaller step sizes, until error is small enough
         or extrapolation fails             
         */
        for (int i = 1; i < attemptCnt; i++) {
            attempts = i;
            System.arraycopy(yNew, 0, yOld, 0, speciesCnt);

            //get next estimate
            stepSize[i] = hOrig / (double) stepCnt[i];
            yNew = modMidpointEst(xOrig, stepCnt[i], stepSize[i]);
//            System.out.printf("after midpt, i=%d, stepCnt=%d, yNew[debugIdx]=%9.2f\n",
//                    i, stepCnt[i], yNew[debugIdx]);

            //perform polynomial extrapolation
            extrapolate(extrapArray, stepSize, i);
//            System.out.printf("after extrap, i=%d, yNew[0]=%9.2f, "
//                    + "stepSize=%9.6f\n",
//                    i, yNew[debugIdx], stepSize[i]);

            //evaluate error for each species - difference between two highest
            //degree estimates in current row.
            estWithinErr = true;
            for (int j = 0; j < speciesCnt; j++) {
                estWithinErr = estWithinErr
                        && (Math.abs(extrapArray[j][i] - extrapArray[j][i - 1])
                        < maxErr);
            }

            //exit if error is acceptable
            if (estWithinErr) {
                break;
            }

        }
//        System.out.printf("*** yNew[debugIdx]=%9.2f\n", yNew[debugIdx]);

        return estWithinErr;
    }

    public double[] getYNew() {
        double[] rtn = new double[speciesCnt];
        System.arraycopy(yNew, 0, rtn, 0, speciesCnt);
        return rtn;
    }

    public double[][] getContribs() {
        double[][] rtn = new double[speciesCnt][speciesCnt];
        for (int i = 0; i < speciesCnt; i++) {
            System.arraycopy(contribs[i], 0, rtn[i], 0, speciesCnt);
        }
        return rtn;
    }

    public int getErr() {
        return err;
    }

    /*
     modMidpointEst () -
     Create n intermediate y values (y(1)-y(n)) using modified midpoint method
     h is step-size, n = # of steps, f(x,y) = slope
     y(0) = yOrig
     from Euler:
     y(1) = y(0) + h * f(x(0),y(0))
     modified midpoint - 
     est from t-minus-2 using weighted slope (2x)(h x slope of prior's prior):
     y(i) = y(i-2) + 2 * h * f(x(i-1) y(i-1)), 2 >= i <= n 
     average of prior two estimates plus (h x slope of prior)
     final estimate = 1/2 [ y(n) + y(n-1) + h * f(x(n),y(n) ]
     */
    private double[] modMidpointEst(double x, int steps, double h) {

        //rolling contribution (delta) and y (biomass) info to hold current and
        //two prior values
        double[][] contribs0 = new double[speciesCnt][speciesCnt];
        double[][] contribs1 = new double[speciesCnt][speciesCnt];
        double[][] contribs2 = new double[speciesCnt][speciesCnt];
        double[] y0 = new double[speciesCnt];
        double[] y1 = new double[speciesCnt];
        double[] y2 = new double[speciesCnt];
        double x0 = x;
        double x1;
        double x2 = 0.0;

        double[] yDelta = new double[speciesCnt];

        //step=1: solve equation w/ orig data
        System.arraycopy(yOrig, 0, y0, 0, speciesCnt);
//        System.out.printf("in ModMid, start,\t\t (x0, y0)=(%9.2f, %9.2f)\n",
//                x0, y0[debugIdx]);
        yDelta = calcYDelta(x0, y0, h, contribs1);
        for (int j = 0; j < speciesCnt; j++) {
            if (yOrig[j] == 0) {
                yDelta[j] = 0;
                continue;
            }
            y1[j] = y0[j] + yDelta[j];
            Arrays.fill(contribs0[j], 0.0f);
        }
        x1 = x0 + h;
//        System.out.printf("in ModMid, i = %d+1 of %d,\t (x1, y1)=(%9.2f, %9.2f) yDelta = %9.2f\n",
//                0, steps, x1, y1[debugIdx], yDelta[0]);

        //make weighted estimates for intermediate steps (steps-1)
        for (int i = 1; i < steps; i++) {
            yDelta = calcYDelta(x1, y1, h, contribs2);
            for (int j = 0; j < speciesCnt; j++) {
                if (yOrig[j] == 0) {
                    yDelta[j] = 0;
                    continue;
                }
                y2[j] = y0[j] + 2 * yDelta[j];
                for (int k = 0; k < speciesCnt; k++) {
                    contribs2[j][k] = contribs0[j][k] + 2 * contribs2[j][k];
                }
                System.arraycopy(contribs1[j], 0, contribs0[j], 0, speciesCnt);
                System.arraycopy(contribs2[j], 0, contribs1[j], 0, speciesCnt);
            }
            x2 = x1 + h;
            System.arraycopy(y1, 0, y0, 0, speciesCnt);
            x0 = x1;
            System.arraycopy(y2, 0, y1, 0, speciesCnt);
            x1 = x2;
//            System.out.printf("in ModMid, i = %d+1 of %d,\t (x2, y2)=(%9.2f, %9.2f) yDelta = %9.2f\n",
//                    i, steps, x2, y2[debugIdx], yDelta[0]);
//            System.out.printf("in ModMid, \t\t\t (x0, y0)=(%9.2f, %9.2f)\n",
//                    x0, y0[debugIdx]);
//            System.out.printf("in ModMid, \t\t\t (x1, y1)=(%9.2f, %9.2f)\n",
//                    x1, y1[debugIdx]);
        }

        //final estimate
        yDelta = calcYDelta(x1, y1, h, contribs2);
        for (int j = 0; j < speciesCnt; j++) {
            if (yOrig[j] == 0) {
                yDelta[j] = 0;
                continue;
            }
            //v1
            y2[j] = 0.5 * (y0[j] + y1[j] + yDelta[j]);
            
            //v2 (same as v1)
            //y2[j] = y0[j] + 2 * yDelta[j];
            //y2[j] = 0.25 * (y0[j] + 2 * y1[j] + y2[j]);
            if (equationSet == 0) {  //ATN: don't let bm fall below 0
                y2[j] = Math.max(y2[j], 0.000001);  
            }
            for (int k = 0; k < speciesCnt; k++) {
                contribs2[j][k] = 0.5 * (contribs0[j][k] + contribs1[j][k] + contribs2[j][k]);
            }

            //copy final contrib info to object array for later reference
            System.arraycopy(contribs2[j], 0, contribs[j], 0, speciesCnt);
        }
//        System.out.printf("in ModMid, end,\t\t\t (x2, y2)=(%9.2f, %9.2f) yDelta = %9.2f\n",
//                x2, y2[debugIdx], yDelta[0]);

        return y2;  //return final estimate
    }

    /* 
     extrapolate() performs polynomial extrapolation to refine mod-midpoint
     calculations and estimate error.
     Called repeatedly with decreasing step-size; each decrease results in the 
     addition of a new row (forming a "lower triangular matrix".  The first 
     element of each row contains the mod-midpoint estimate using the current 
     step-size.  The number of elements increases with each decrease in 
     step-size.  Prior row is retained temporarily to calulcate new row and then 
     disgarded.
     */
    private void extrapolate(double[][] extrapArray,
            double[] stepSize, int stepIdx) {

        int degrees = stepIdx + 1;
        double[][] priorRow = new double[speciesCnt][degrees];

        //first row has single element
        if (stepIdx == 0) {
            for (int j = 0; j < speciesCnt; j++) {
                extrapArray[j][0] = yNew[j];
            }
        }

        //subsequent rows have stepIdx+1 entries
//        System.out.printf("extrapArray (sp #2): val=%9.2f\t", extrapArray[debugIdx][0]);

        //process each species
        for (int j = 0; j < speciesCnt; j++) {
            //make copy of prior row
            System.arraycopy(extrapArray[j], 0, priorRow[j], 0, degrees);
            //initialize first element (mod-midpoint est)
            extrapArray[j][0] = yNew[j];

            /*
             Calculate polynomial estimates for each degree for current row.  
             Entries are based on combination of prior-degree value in 
             current row and prior-degree value in previous row.
             */
            for (int q = 0; q < stepIdx; q++) {
                extrapArray[j][q + 1]
                        = extrapArray[j][q]
                        + ((extrapArray[j][q] - priorRow[j][q])
                        / (Math.pow((double) stepCnt[stepIdx] / (double) stepCnt[stepIdx - q - 1], 2) - 1.0));
                //if (j == 2) {
//                System.out.printf("val=%9.2f\t", extrapArray[debugIdx][q + 1]);
                //}
            }

            yNew[j] = extrapArray[j][stepIdx];
            //if (j == 2) {
//            System.out.printf("\n");
            //}
        }

    }

    //calc ATN model terms; relative contrib of each species to each species biomass 
    private void calcATNTerms(double stepSize, double[] preyBM, int speciesIdx) {

        for (int j = 0; j < speciesCnt; j++) {
            NodeRelationships relnsI = ecosysRelationships.get(speciesID[speciesIdx]);
            NodeRelationships relnsJ = ecosysRelationships.get(speciesID[j]);
            int preyCntI = relnsI.getPreyCnt();
            int preyCntJ = relnsJ.getPreyCnt();
            //get reln FROM i TO j
            String relnIJ = relnsI.getReln(speciesID[j]);
            //(reln FROM i TO j)
            switch (relnIJ) {
                case "d":  //i predator of j
                    atnModel[speciesIdx][j].setFuncRespIJ(preyBM[speciesIdx], preyCntI);
                    break;
                case "y":  //i prey of j
                    atnModel[speciesIdx][j].setFuncRespJI(preyBM[j], preyCntJ);
                    break;
                case "b":  //i and j predate on each other
                    atnModel[speciesIdx][j].setFuncRespIJ(preyBM[speciesIdx], preyCntI);
                    atnModel[speciesIdx][j].setFuncRespJI(preyBM[j], preyCntJ);
                    break;
                case "c":  //i==j (cannibal)
                    atnModel[speciesIdx][j].setFuncRespIJ(preyBM[speciesIdx], preyCntI);
                    atnModel[speciesIdx][j].setFuncRespJI(preyBM[j], preyCntJ);
                    break;
            }
            atnModel[speciesIdx][j].setJContrib(speciesIdx == j, stepSize);
        }   //j

    }

    private void calcPreyBM(double[] bm, double[] preyBM) {

        for (int i = 0; i < speciesCnt; i++) {

            for (int j = 0; j < speciesCnt; j++) {  //note: j >= i
                atnModel[i][j] = new ATNModel(sztArray[i], sztArray[j], bm[i], bm[j], lPs);
                //get reln FROM i TO j
                String relnIJ = ecosysRelationships.get(speciesID[i]).
                        getReln(speciesID[j]);
                //reln FROM i TO j;  note: only prey info is stored -
                switch (relnIJ) {
                    case "d":  //i predator of j
                    case "b":  //species both predate each other
                        preyBM[i] = preyBM[i]
                                + atnModel[i][j].getPreyBMCalcDouble(bm[j]);
                        break;
                    case "c":  //cannibal
                        preyBM[i] = preyBM[i]
                                + atnModel[i][j].getPreyBMCalcDouble(bm[j]);
                        break;
                    case "y":
                    default:
                        break;
                }
            }
        }
    }

    private double[] calcYDelta(
            double x,
            double[] y,
            double stepSize,
            double[][] contribs
    ) {
        //calc prey bm
        double[] preyBM = new double[speciesCnt];

        if (equationSet == 0) {
            //prey calculations have to be performed prior to contrib calcs
            calcPreyBM(y, preyBM);
        }

        double[] yDelta = new double[speciesCnt];

        //sum contributions from each species
        for (int i = 0; i < speciesCnt; i++) {
            if (equationSet == 0) {
                calcATNTerms(stepSize, preyBM, i);
            }

            for (int j = 0; j < speciesCnt; j++) {
                double contrib;
                switch (equationSet) {
                    case 0:
                        contrib = atnModel[i][j].getJContrib();
                        break;
                    case 1:
                        contrib = stepSize * ((-y[j] * Math.sin(x)) + (2.0 * Math.tan(x))) * y[j];
                        break;
                    case 2:
                        contrib = stepSize * ((-200.0) * x * Math.pow(y[j],2.0));
                        break;
                    default:
                        contrib = 0.0;
                        break;
                }
                //track total change for species i
                yDelta[i] += contrib;
                if (yDelta[i] > 40.0) {
                    boolean dummy = true;
                }
                //track change for this species combo
                contribs[i][j] = contrib;
            }
        }
        //System.out.printf("in calcYDelta, yDelta[debugIdx]=%9.2f\n", yDelta[debugIdx]);

        return yDelta;
    }

    public String extrapArrayToString(int scale) {
        String arrayStr = "";
        for (int i = 0; i < speciesCnt; i++) {
            arrayStr += String.format("node %2d", speciesID[i]);
            for (int j = 0; j < attempts; j++) {
                arrayStr += String.format(", %9.2f",
                        extrapArray[i][j] * scale);
            }
            arrayStr += "\n";
        }

        return arrayStr;
    }

}
