package battle;

public enum Type {
	/*not sure at all about this. just seems like moveType should be stored as an enum
	 * basically just copied and slightly modified the code from Rarity
	 * Needs a lot of work
	 */
	
	LITERATURE(1),					//address with value 1
	MATH(2),						//address with value 2
	SCIENCE(3),						//address with value 3
	HISTORY(4),						//address with value 4
	COMPUTER_SCIENCE(5);			//address with value 5
	
	private int value;    

	private Type(int value) {
		this.value = value;
	}

	public int getValue() {
	  return value;
	}
}
