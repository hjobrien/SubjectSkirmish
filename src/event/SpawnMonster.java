package event;

import creature.Enemy;
import creature.Tanimonster;

public class SpawnMonster implements Event {
	
	public SpawnMonster(){
//		System.out.println("Monster Spawned");
	}

	public Enemy getEnemy(){
		return null;
	}
	
	@Override
	public String toString() {
		return "Monster Spawned";
	}
	
	
}
