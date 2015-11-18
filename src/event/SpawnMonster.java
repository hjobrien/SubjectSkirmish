package event;

import creature.Creature;

public class SpawnMonster implements Event {
	
	public SpawnMonster(){
//		System.out.println("Monster Spawned");
	}

	public Creature getCreature(){
		return null;
	}
	
	@Override
	public String toString() {
		return "Monster Spawned";
	}
	
	
}
