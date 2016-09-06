package cos.model;

/**
 *
 * @author Justina
 */
public class Consume {
    int predatorId, preyId;
    double paramA, paramD, paramE, paramQ, paramY;
    
    public Consume(int predatorId, int preyId,
                   double paramA, double paramD, double paramE, double paramQ, double paramY) {
        this.predatorId = predatorId;
        this.preyId = preyId;
        this.paramA = paramA;
        this.paramD = paramD;
        this.paramE = paramE;
        this.paramQ = paramQ;
        this.paramY = paramY;     
    }
    
    public double getParamA () {
        return paramA;
    }

    public double getParamD () {
        return paramD;
    }
    
    public double getParamE () {
        return paramE;
    }
    
    public double getParamQ () {
        return paramQ;
    }
    
    public double getParamY () {
        return paramY;
    }

}
