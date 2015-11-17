package event;

import creature.Enemy;

public abstract class SpawnMonster implements Event {
	
	public SpawnMonster(){
//		System.out.println("Monster Spawned");
	}

	public abstract Enemy getEnemy();
	
	@Override
	public String toString() {
		return "Monster Spawned";
	}
	
	
}
