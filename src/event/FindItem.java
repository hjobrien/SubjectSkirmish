package event;

public class FindItem implements Event {
	private String name;
	//could style item based on rarity
	//should have some scale 1-5? 1-10?
	private Rarity rarity;
	
	
	//maybe make the random generation of properties in this object?
	public FindItem(String name, Rarity rarity){
		this.name = name;
		this.rarity = rarity;
	}

	@Override
	public String toString() {
		return name + " " + rarity;
	}
	
	public String getName() {
		return name;
	}

	public Rarity getRarity() {
		return rarity;
	}
	
	
}
