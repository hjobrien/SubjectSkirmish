package creature;

public class Move {
	private String name;
	private double damage;
	private MoveType type;
	
	public Move (String name, double damage, MoveType type){
		this.name = name;
		this.damage = damage;
		this.type = type;
	}
}
