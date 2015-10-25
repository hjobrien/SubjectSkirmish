package event;

public enum Rarity {
	//the basic premise of enums is to wrap variable types in a new one that makes more contextual sense
	//	Rarity: RARE	vs.		 Rarity: 3
	//Makes sense ^ 		Doesn't ^
	
	//by default enums are 0-based, but the NAME(NUM) syntax basically has you constructing a new object with a constructor
	//incidentally, we declare the constructor below
	
	COMMON(1),			//address with value 1
	UNCOMMON(2),		//address with value 2
	RARE(3),			//address with value 3
	VERY_RARE(4),		//address with value 4
	UNIQUE(5);			//address with value 5
	
	private int value;    

	private Rarity(int value) {
		this.value = value;
	}

	public int getValue() {
	  return value;
	}
}
