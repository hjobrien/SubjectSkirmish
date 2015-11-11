package graphics;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import event.Event;
import event.FindItem;
import event.SpawnMonster;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
//import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import player.Item;
import player.Player;
import tile.BorderTile;
import tile.FireTile;
import tile.GrassTile;
import tile.Stepable;
import tile.Tile;
import tile.WaterTile;

public class Main extends Application {

	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static int borderWidth = 0;
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private static int borderHeight = 0;
	private static int menuHeight = 400;
	private static int menuWidth = 250;
	private static int menuButtonWidth = 225;

	
	//commonly used formulas that can easily be changed now
	//Still needs to be optimized for all screens
	private static final int X_MAX = (SCREEN_WIDTH / 50);
	private static final int Y_MAX = (SCREEN_HEIGHT / 50) - 3;
	
	private int[] location = new int[]{X_MAX / 2,Y_MAX / 2}; //x and y coordinate 
	private Board board;
	

	//I tried changing this and nothing happened. Does it do
	//anything at the moment?
	//---update, i changed the moving so now tile size affects the player,
	//but it still doesn't affect the tiles
	public static final int TILE_SIZE = 1; 
	public static final Color PLAYER_COLOR = Color.BLACK;



	//BUG: after ending a SpawnMonsterEvent, board is vertically compressed and then a JVM error is thrown (on my computer) 
	//i don't think i can do anything about the error, its a java language problem
	//Tested on Java 8 Builds 60 and 65 (newest version as of 10/24/15)
	
	//Other note, if you want a method to go away, but you don't know where things are calling it, or whatever, use the @Deprecated tag above the method
	@Deprecated
	public static void doNothing(){
		
	}
	
	
	
	public static void main(String[] args) {	
		launch(args);
		//example of calling a Deprecated method, its pretty clear to see
		doNothing();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();
		Random rand = new Random();
		
		//still needs to be optimized
		for(int i = 0; i < X_MAX + 1; i++){
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for(int j = 0; j < Y_MAX + 1; j++){
				//such beautiful polymorphism
				Tile tempTile = null;
				if (isOnScreenEdge(i, j)){
					tempTile = new BorderTile(i,j);
				} else if (i == X_MAX / 2 && j == Y_MAX / 2){
					//automatically makes the tile that the player starts on a grass tile
					tempTile = new GrassTile(i, j);
				} else {
					int x = rand.nextInt(6);
					if(x < 1){
						tempTile = new FireTile(i,j);
					} else if (x < 4){
						tempTile = new GrassTile(i,j);
					} else { 
						tempTile = new WaterTile(i,j);
					}
					//normal tiles can also be used
				}
				temp.add(tempTile);
			}
			board.add(temp);
		}
		this.board = new Board(board);

		
		GridPane grid = new GridPane();
		Canvas canvas = new Canvas(SCREEN_WIDTH - borderWidth, SCREEN_HEIGHT - borderHeight);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		Player player = new Player(location[0],location[1],PLAYER_COLOR, this.board);
		
		//prompts the user to enter their name, can be commented out if you don't like it
		//commenting to make running program faster --Hank
//		System.out.print("What is your name? ");
//		Scanner console = new Scanner(System.in);
//		String name = console.next();
//		console.close();
//		player.setName(name);
		
		scaleGraphics(grid, g);
		update(player, g);
				
		Scene boardScene = new Scene(grid, SCREEN_WIDTH, SCREEN_HEIGHT);

		stage.setScene(boardScene);
		//make escape close window
		stage.addEventFilter(KeyEvent.KEY_PRESSED,e -> {
			if(e.getCode() == KeyCode.ESCAPE)
				System.exit(0);
		});
//		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		
		stage.addEventFilter(KeyEvent.KEY_PRESSED, e ->{
//			boolean moved; //to test if player moved
			
			//basic event handling structure part 1:
			/*
			 * a new event is created as null, whenever any button is pressed
			 * when one of the arrow keys is pressed, the player.advance returns a new event, 
			 * which is the event created by whatever tile it steps onto, though this can change later
			 * then, the event is (attempted) to be passed to a generic method that handles all kinds of events
			 * though in the future, we could overload (<-- more polymorphism) the method so that if the event was a spawn event, it did
			 * one thing, but if it was an item event, it would do another.
			 * I choose to put it as the first action to be taken, because i figured the event handling could affect the graphics (for example) so it should be the highest precedence
			 */
			Event onAdvance = null;
			if(e.getCode() == KeyCode.LEFT){
				if((this.board.getBoard()).get(location[0] - 1).get(location[1]) instanceof Stepable){
					onAdvance = player.advance(-TILE_SIZE,0);
				}
			}
			if(e.getCode() == KeyCode.RIGHT){
				if((this.board.getBoard()).get(location[0] + 1).get(location[1]) instanceof Stepable){
					onAdvance = player.advance(TILE_SIZE,0);
				}
			}
			if(e.getCode() == KeyCode.UP){
				if((this.board.getBoard()).get(location[0]).get(location[1] - 1) instanceof Stepable){
					onAdvance = player.advance(0,-TILE_SIZE);
				}
			}
			if(e.getCode() == KeyCode.DOWN){
				if((this.board.getBoard()).get(location[0]).get(location[1] + 1) instanceof Stepable){
					onAdvance = player.advance(0,TILE_SIZE);
				}
			}
			
			//resets after a spawn monster event was triggered, this is temporary
			if(e.getCode() == KeyCode.ENTER){
				stage.setScene(boardScene);
			}
			
			//I for items
			if(e.getCode() == KeyCode.I){
//				System.out.println(player.getBag()); //this just prints the array in a gross array format
				System.out.println(player.printBag()); //this will (hopefully) nicely print the array in neat columns
			}
			
			if(e.getCode() == KeyCode.M){
				player.printBag();
				showMenu(player);
			}
			
			
			if(e.getCode() == KeyCode.DIGIT4){
				System.out.println(player.getName() + " has $" + player.getMoney());
			}
			
			try{
				//I really like this format, it seems to work well with what we are doing
				
				if(onAdvance instanceof SpawnMonster){
					SpawnMonster newSpawn  = (SpawnMonster) onAdvance;
					System.out.println(newSpawn.toString());
					
					//in this new scene, the direction buttons have to control different things
					//so that the options are maneuverable and the player cant keep moving
//					stage.setScene(handle(newSpawn));	//uncomment for JVM Bug, doens't actually do anything yet, so its staying commented
														//Mine isnt bugging, it prints test on a clear white screen -Liam
				}
				else if(onAdvance instanceof FindItem){
					Item newItem = ((FindItem) onAdvance).getItem();
					player.addToBag(newItem);
				}else{
					handle(onAdvance);
				}
				
			}catch(NullPointerException NPException){
				//means a button other than an arrow key was pressed, probably no big deal, though they could/should be handled more gracefully
			}
			location = player.getLocation();
			update(player,g);
		});

//		TextArea textArea = new TextArea("Testing\n this is another line\n and another one");
//		textArea.toFront();
//		textArea.maxHeight(1);
//		textArea.maxWidth(2);
////		textArea.getStylesheets().add("GUI Style.css");
//		grid.add(textArea, 0, 0);
		grid.add(canvas, 0, 0); 

		
		stage.show();

	}
	
	private void showMenu(Player player) {
		//****I want to set a general rule that declares what buttons control which aspects of the program****
		//I currently am setting it where space is an "accept" button, carrying out the button's role
		//and B is the "back" button, returning to the previous screen. 
		//we can change this if we want but i think its easy and intuitive
		//these buttons should be reserved for these two uses to avoid confusion
		
		//this will make a new window that is the menu, so it is different from what some programs do, but idk a good way of doing it another way
		Stage menuStage = new Stage();
		menuStage.setTitle("Main Menu");
		//forces you to deal with the menu instead of going back to the game
		menuStage.initModality(Modality.APPLICATION_MODAL);
		menuStage.setHeight(menuHeight);
		menuStage.setWidth(menuWidth);
		
		VBox mainMenuObjs = new VBox();
		Scene mainMenuScene = new Scene(mainMenuObjs, menuHeight, menuWidth);
		
		mainMenuObjs.setAlignment(Pos.CENTER);
		//empty region that forces components below it to the bottom of the stage
		Region midSpring = new Region();
		VBox.setVgrow(midSpring, Priority.ALWAYS);
		
		Button cont = new Button("Continue"); 		//i can't name it continue, its a reserved word
		cont.setMinWidth(menuButtonWidth);
		cont.setMaxWidth(menuButtonWidth);
		
		cont.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.SPACE)
				menuStage.close();
		});
		
		Button inventory = new Button("Inventory");
		inventory.setMinWidth(menuButtonWidth);
		inventory.setMaxWidth(menuButtonWidth);
		
		
		inventory.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.SPACE)
				menuStage.setScene(getInventory(player));
			
				//this adds another event filter after the inventory is viewed so that in the future, we can access items
				//and right now, we can easily return to the menu screen
				menuStage.addEventFilter(KeyEvent.KEY_PRESSED, f -> {
					if (f.getCode() == KeyCode.B)
						menuStage.setScene(mainMenuScene);
				});
		});
		
		mainMenuObjs.getChildren().addAll(midSpring, inventory, cont);
		
		menuStage.setScene(mainMenuScene);
		menuStage.show();
	}

	private Scene getInventory(Player player) {
		ArrayList<Item> localBag = player.getBag();
		System.out.println(player.printBag());
		GridPane inventoryGrid = new GridPane();
		Scene inventoryScene = new Scene(inventoryGrid , menuWidth, menuHeight);
//		int maxHorizIcons = (int) ((double)(MENU_WIDTH) / Item.ICON_SIZE);
//		int numRows = (int) Math.ceil((double)(localBag.size()-1) / maxHorizIcons);
		int count = 0;
		for(int i = 0; i < menuWidth / Item.ICON_SIZE-1; i++){
			for(int j = 0; j < menuHeight / Item.ICON_SIZE-2; j++){
				if(count < localBag.size()){
					ImageView temp = new ImageView();
					temp.setImage(localBag.get(i*j+j).getIcon());
					inventoryGrid.add(temp, j, i);
				}
				count++;
			}
		}
		return inventoryScene;
	}



	public boolean isOnScreenEdge(int x, int y){
		
		//a benefit of this is that the grid could now be 
		//bigger than is actually needed, as long as the border 
		//restricts the player. 
		if (x == 0 || x == X_MAX|| y == 0|| y == Y_MAX){
			return true;
		} 
		return false;
	}
	
	public void scaleGraphics(GridPane grid, GraphicsContext g){
		
		//puts 0,0 in center of display
//		g.scale(1,-1);
//		g.translate(0, (-1) * SCREEN_HEIGHT);
//		g.translate(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		g.scale(50,50);
		g.setLineWidth(0.05);

	}
	
	
	//basic event handling structure part 2
	/*
	 * this is the generic method for all events, should make more specific examples
	 * right now it just does the toString of the events
	 * I changed the objects that implemented event from printing in their constructor to producing the same output in toString, its cleaner
	 */
	public void handle(Event e){
		System.out.println(e.toString());
	}
	
	//comment the contents of this method to stop JVM bug
	//no bug for me 
	//Also we really need to figure out how this works-liam
	public Scene handle(SpawnMonster spawn){
		Group group = new Group();//idk what this does, but its how some scenes are made, wouldn't hurt to look into it

//		JButton j = new JButton("Bag");
//		j.setBounds(100, 100, 100, 100);
//		group.getChildren().add(j); //doesnt work for some reason
		
		group.getChildren().add(new Label("Test"));
		group.getChildren().add(new Label("Test2"));
		return new Scene(group, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
//	public Item handle(FindItem item){
//		return new Item(item.getName(), item.getRarity());
//	}
	
	public void update(Player player, GraphicsContext g){
		//draw player
		double size = Player.SIZE;
		
		//clears old player
		g.clearRect(location[0]-TILE_SIZE,location[1]-TILE_SIZE, TILE_SIZE * 3,TILE_SIZE * 3);

		for(ArrayList<Tile> row : board.getBoard()){
			for(Tile tempTile : row){
				g.setFill(tempTile.getColor());
				g.fillRect(tempTile.getX(), tempTile.getY(), TILE_SIZE, TILE_SIZE);
			}
		}

		g.setFill(player.getColor());
		g.fillRect(location[0] + (TILE_SIZE-size)/2, location[1] + (TILE_SIZE-size)/2, size, size);
		
	}
	
	
//	private void initConstants(){
//		Scanner configReader;
//		try {
//			configReader = new Scanner(new File("config.txt"));
//		} catch (FileNotFoundException e) {
//			createDefaultConfig();
//		}
//		
//		
//	}



//	private void createDefaultConfig() {
//		//for right now, i will keep the defaults up in the top of this file, but this format is good
//		//because it generates its dependencies, i.e. it wont throw errors for FileNotFound, which is good for UX
//		//create a new file with default values
//		initConstants(); //go back and initialize constants based off new file we just made
//		
//	}



}
