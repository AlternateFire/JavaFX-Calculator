/* Interest class handles all calculations for simple and compound interest. 
 * Both interest is also handled through here. */
public class Interest {

	//Fields for Interest
	private double principal; 
	private double rate; 
	private byte years; 

	//Constructor
	public Interest(double principal, double rate, byte years) {
		this.principal = principal; 
		this.rate = rate; 
		this.years = years; 
	}

	//Method for simple interest calculation
	public static double simpleInterest
	(double principal, double rate, byte years) {
		return principal + (principal * (rate/100) * years); 
	}

	//Method for compound interest calculation 
	public static double compoundInterest
	(double principal, double rate, byte years) {
		return principal * Math.pow(1 + rate/100, years); 
	}

	//Method for display bothInterest
	public static String bothInterest
	(double principal, double rate, byte years) {
		//Calling simpleInterest and compoundInterest for calculations
		Double simpleInterestAns = simpleInterest(principal, rate, years); 
		Double compoundInterestAns = compoundInterest(principal, rate, years); 

		//Displaying simple and compound calculations
		return years + "-->" + simpleInterestAns.toString() + 
				"-->" + compoundInterestAns.toString();
	}	
}
