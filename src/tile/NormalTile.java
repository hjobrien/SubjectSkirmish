package tile;

import java.util.Random;

import event.Event;
import event.FindItem;
import event.SpawnNormalMonster;
import javafx.scene.paint.Color;

public class NormalTile extends Tile implements Stepable {

	/*
	 * I don't know the purpose of this class, it seems like grass tile should be a filler, so i don't know the role this plays
	 * I'm not going to use it for right now
	 * --Hank
	 * I was thinking that grass had monsters in it, so we needed
	 * a normal tile that wouldnt have any chance of encounter
	 * --Liam
	 */
	
	private static double chanceOfItemEncounter = 0.0;
	private static double chanceOfCreatureEncounter = 0.0;
	private static Color defaultColor = Color.DARKGREY;
	
	public NormalTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		Random r = new Random();
		double chance = r.nextDouble();
		if (chance <= chanceOfItemEncounter){
			return new FindItem(super.calculateRarity());
		} else if (chance <= chanceOfCreatureEncounter + chanceOfItemEncounter){ //account for full probability
			return new SpawnNormalMonster();
		}
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " Normal";
	}

}
