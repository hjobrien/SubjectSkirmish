package battle;

public class AttackWeightMatrix {

	//modify these values to account for strengths/weaknesses, diagonal should be ones 
	
	//use row/columns to find attack weight, so lit attack vs. math creature might by twice as strong, for instance
	//need to decide which side is attack type, and which is target type
	
	public static final double[][] ATTACK_WEIGHT_MATRIX = new double[][]{
						//Defense
/*				Lit   Math  Sci   Hist.  CS	*/

	//Attack	
/*   Lit  */	{1.0,    2.0,    2.0,    0.5,    0.5},
/*   Math */	{0.5,    1.0,    2.0,    2.0,    0.5},
/*   Sci  */	{2.0,    0.5,    1.0,    2.0,    0.5},
/*   Hist */	{2.0,    2.0,    0.5,    1.0,    0.5},
/*   CS   */	{2.0,    2.0,    2.0,    2.0,    2.0}

	};
	
}

/*
					Defense
			Lit   Math  Sci   Hist.  CS	
Attack	
   Lit  	{1,    1,    1,    1,    1},   
   Math 	{1,    1,    1,    1,    1},
   Sci  	{1,    1,    1,    1,    1},
   Hist 	{1,    1,    1,    1,    1},
   CS   	{1,    1,    1,    1,    1}
*/