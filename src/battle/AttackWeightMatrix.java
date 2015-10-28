package battle;

public class AttackWeightMatrix {

	//modify these values to account for strengths/weaknesses, diagonal should be ones 
	
	//use row/columns to find attack weight, so lit attack vs. math creature might by twice as strong, for instance
	//need to decide which side is attack type, and which is target type
	
	public static final double[][] ATTACK_WEIGHT_MATRIX = new double[][]{
		
/*				Lit   Math  Sci   Hist.  CS	*/
		
/*   Lit  */	{1,    1,    1,    1,    1},
/*   Math */	{1,    1,    1,    1,    1},
/*   Sci  */	{1,    1,    1,    1,    1},
/*   Hist */	{1,    1,    1,    1,    1},
/*   CS   */	{1,    1,    1,    1,    1}

	};
	
}
