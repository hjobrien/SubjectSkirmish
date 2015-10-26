package player;

import java.util.ArrayList;

import event.Event;
import graphics.Board;
import javafx.scene.paint.Color;

public class Player {

	private String name = "Player";
	public static final double SIZE = 0.8; // 0 < SIZE < TILE_SIZE
	private int x;
	private int y;
	private Color color;
	private Board board;
	private ArrayList<Item> bag = new ArrayList<Item>();
	
	//introduces the possibility of buying things in the future
	private int money = 0;
	
	public Player(int startX, int startY, Color color, Board board){
		this.x = startX;
		this.y = startY;
		this.color = color;
		this.board = board;
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
		if (itemToAdd.getName().charAt(0) == '$'){
			String money = itemToAdd.getName().substring(1, itemToAdd.getName().length());
			this.money += Integer.parseInt(money);
		} else {
			bag.add(itemToAdd);
		}
	}
	
	public ArrayList<Item> getBag(){
		return bag;
	}
	
	//Should be styled into two nice rows that display item name and rarity under the appropriate titles.
	//I forget how to do this
	public String printBag(){
		String bagString = "Item Name		Rarity\n";
		for (Item i : bag){
			bagString += i.getName() + "\t\t\t"+  i.getRarity() + "\n";
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
}
