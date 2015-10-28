package battle;


public class Attack {
	
	private String name;
	private double baseDamage;
	private AttackType type;
	
	public Attack (String name, double damage, AttackType type){
		this.name = name;
		this.baseDamage = damage;
		this.type = type;
	}

	public double getBaseDamage() {
		return baseDamage;
	}

	public AttackType getType() {
		return this.type;
	}

}
