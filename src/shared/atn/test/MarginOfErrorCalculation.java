package shared.atn.test;
/*
 * We calculate Margin of Error Confidence Interval for a population proportion 
 * with the formula p~ * (z * sqrt ( (p~ *(1 - p~)) / n) for a 95% confidence interval.
 * For eg: Out of 900 sample size, if 400 people like ice cream, the p~ = 400/900 = 0.44
 * which means 44% like ice cream
 * 
 * 
 * We calculate the biomass difference between actual(web services) and calculated(atn engine)
 * and find the percentage error, we then see what proportions have what percentage error
 * when we don't know population proportion, we estimate with 0.5
 */
public class MarginOfErrorCalculation {
	private double pStar;
	private double marginOfError;
	private double confidenceCoefficient = 1.96; //for a 95% confidence interval
	private int sampleSize = 900;
	private int variableX = 400;
	
	
	public double marginOfError(){
		pStar = (double) variableX / sampleSize;
		marginOfError = (confidenceCoefficient * Math.sqrt(pStar * (1-pStar)/sampleSize));
		return marginOfError;
	}
	
	public static void main(String[] args){
		MarginOfErrorCalculation mc = new MarginOfErrorCalculation();
		System.out.println("Margin of Error = " + mc.marginOfError());
	}
}
