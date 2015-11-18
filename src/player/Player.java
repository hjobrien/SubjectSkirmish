package player;

import java.util.ArrayList;

import creature.Creature;
import event.Event;
import graphics.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Player {

	private String name = "Player";
	public static final double SIZE = 0.8; // 0 < SIZE < TILE_SIZE
	private static final int MAX_MONSTERS = 5;
	private int x;
	private int y;
	private static ImageView icon;
	private Color color;
	private Board board;
	private ArrayList<Item> bag = new ArrayList<Item>();
	private ArrayList<Creature> monsters = new ArrayList<Creature>();
	
	//introduces the possibility of buying things in the future
	private int money = 0;
	
	public Player(int startX, int startY, Color color, Board board, String iconFilePath){
		this.x = startX;
		this.y = startY;
		this.color = color;
		this.board = board;
		icon = new ImageView(iconFilePath);
	}
	
	public Event advance(int deltaX, int deltaY){
		x += deltaX;
		y += deltaY;
		return board.getBoard().get(x).get(y).onStep();
	}

	
	public int[] getLocation(){
		return new int[]{x,y};
	}

	public Color getColor() {
		return color;
	}
	
	
	public void addToBag(Item itemToAdd){
		//checks to see if the item found is money, and responds appropriately
		if (itemToAdd.getName().charAt(0) == '$'){		//also could use String.contains("$");
			String money = itemToAdd.getName().substring(1, itemToAdd.getName().length());
			this.money += Integer.parseInt(money);
		}
		else {
			bag.add(itemToAdd);
		}
	}
	
	public void addFriendlyCreature(Creature creatureToAdd) {
		if(monsters.size() < MAX_MONSTERS){
			monsters.add(creatureToAdd);
		}
		else{
			//we can actually deal with this case later
			System.err.println("Player bag of creatures is full");
		}
	}

	public ArrayList<Item> getBag(){
		return bag;
	}
	
	public String printBag(){
		String bagString = "";
		String heading1 = "Item Name";
		String heading2 = "Rarity";
//		String heading3 = "Icon Name";
		bagString += String.format("%-20s %-20s %n", heading1, heading2);
//		bagString += String.format("%-20s %-20s %-20s %n", heading1, heading2, heading3);
		for (Item i : bag){
			String name = i.getName();
			String rarity = i.getRarity().toString();
//			String iconName = i.getIconName();
			bagString += String.format("%-20s %-20s %n", name, rarity);
//			bagString += String.format("%-20s %-20s %-20s %n", name, rarity, iconName);
		}
		return bagString;
	}

	public int getMoney() {
		return this.money;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public static ImageView getImage() {
		return icon;
	}
}
