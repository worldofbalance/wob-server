/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.simulation;

/**
 *
 * @author Paul
 */
public class ParamValue {
    
    private int preyIdx;
    private double paramValue;

    public ParamValue(int idx, double value)
    {
        preyIdx = idx;
        paramValue = value;
    }
    
    public double getParamValue() {
        return paramValue;
    }

    public void setParamValue(double paramValue) {
        this.paramValue = paramValue;
    }

    public int getPreyIdx() {
        return preyIdx;
    }

    public void setPreyIdx(int preyIdx) {
        this.preyIdx = preyIdx;
    }
    
}
