package graphics;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Scanner;

import battle.Attack;
import battle.AttackType;
import battle.Effect;
import creature.Creature;
import event.Event;
import event.FindItem;
import event.SpawnMonster;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import player.Item;
import player.Player;
import tile.DoorTile;
import tile.Stepable;
import tile.Tile;

public class Main extends Application {

	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	//commonly used formulas that can easily be changed now
	//Still needs to be optimized for all screens
	private static final int X_MAX = (SCREEN_WIDTH / 50);
	private static final int Y_MAX = (SCREEN_HEIGHT / 50) - 3;
	
	public static final Color PLAYER_COLOR = Color.BLACK;
	
	private static int borderWidth = 0;
	private static int borderHeight = 0;
	private static int menuHeight = 400;
	private static int menuWidth = 250;
	private static int menuButtonWidth = 225;
	private static boolean hasUpdated = false;
	
	private static int tbX = 10; //text box x value to make it look nice
	private static int tbY = 20; //text box y value to make it look nice
	
	private int[] location = new int[]{X_MAX / 2,Y_MAX / 2}; //x and y coordinate 
	private Board board;
	

	//I tried changing this and nothing happened. Does it do
	//anything at the moment?
	//---update, i changed the moving so now tile size affects the player,
	//but it still doesn't affect the tiles
	public static final int TILE_SIZE = 1;
	
	private static final BoardStyle GEN_STYLE = BoardStyle.LARGER_GROUPS;
	private static final String PLAYER_ICON_PATH = "/creatureImages/ImageNotFound.png"; 
	
	public static void main(String[] args) {	
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.board = BoardGenerator.generate(GEN_STYLE);

		Group root = new Group();
//		GridPane grid = new GridPane();
		Canvas canvas = new Canvas(SCREEN_WIDTH - borderWidth, SCREEN_HEIGHT - borderHeight);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		Player player = new Player(location[0],location[1],PLAYER_COLOR, this.board, PLAYER_ICON_PATH);
		Creature friend = player.getMonsters().get(0);
		
		//prompts the user to enter their name, can be commented out if you don't like it
		//commenting to make running program faster --Hank
//		System.out.print("What is your name? ");
//		Scanner console = new Scanner(System.in);
//		String name = console.next();
//		console.close();
//		player.setName(name);
		
		scaleGraphics(g);
		update(player, g, Direction.NO_MOVE);
		
		Scene boardScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		
		stage.setScene(boardScene);
		//make escape close window
		stage.addEventFilter(KeyEvent.KEY_PRESSED,e -> {
			if(e.getCode() == KeyCode.ESCAPE)
				System.exit(0);
		});  
		
		//full screen looks nicer but it doesn't work with multiple stages, so when we change that then we can 
		//uncomment this
		
//		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		
		boardScene.addEventFilter(KeyEvent.KEY_PRESSED, e ->{
			Direction moveDirection = Direction.NO_MOVE;
//			boolean moved; //to test if player moved
			
			//basic event handling structure part 1:
			/*
			 * a new event is created as null, whenever any button is pressed
			 * when one of the arrow keys is pressed, the player.advance returns a new event, 
			 * which is the event created by whatever tile it steps onto, though this can change later
			 * then, the event is (attempted) to be passed to a generic method that handles all kinds of events
			 * though in the future, we could overload (<-- more polymorphism) the method so that if the event was a 
			 * spawn event, it did one thing, but if it was an item event, it would do another.
			 * I choose to put it as the first action to be taken, because i figured the event handling could affect 
			 * the graphics (for example) so it should be the highest precedence
			 */
			Event onAdvance = null;
			if(e.getCode() == KeyCode.LEFT){
				if((this.board.getBoard()).get(location[0] - 1).get(location[1]) instanceof Stepable){
					moveDirection = Direction.WEST;
					if ((this.board.getBoard()).get(location[0] - 1).get(location[1]) instanceof DoorTile){
						updateBoard(moveDirection, player, g);
					} else {
						onAdvance = player.advance(-TILE_SIZE,0);
					}
				}
			}
			if(e.getCode() == KeyCode.RIGHT){
				if((this.board.getBoard()).get(location[0] + 1).get(location[1]) instanceof Stepable){
					moveDirection = Direction.EAST;
					if((this.board.getBoard()).get(location[0] + 1).get(location[1]) instanceof DoorTile){
						updateBoard(moveDirection, player, g);
					} else {
						onAdvance = player.advance(TILE_SIZE,0);
					}
				}
			}
			if(e.getCode() == KeyCode.UP){
				if((this.board.getBoard()).get(location[0]).get(location[1] - 1) instanceof Stepable){
					moveDirection = Direction.NORTH;
					if((this.board.getBoard()).get(location[0]).get(location[1] - 1) instanceof DoorTile){
						updateBoard(moveDirection, player, g);
					} else {
						onAdvance = player.advance(0,-TILE_SIZE);
					}
				}
			}
			if(e.getCode() == KeyCode.DOWN){
				if((this.board.getBoard()).get(location[0]).get(location[1] + 1) instanceof Stepable){
					moveDirection = Direction.SOUTH;
					if((this.board.getBoard()).get(location[0]).get(location[1] + 1) instanceof DoorTile){
						updateBoard(moveDirection, player, g);
					} else {
					onAdvance = player.advance(0,TILE_SIZE);
					}
				}
			}
			
			//Im keeping this because our items textbox right now still cant scroll
			//so there is no way to see all the items except with this
			//when we make a scroll bar, we can get rid of this
//			//I for items
//			if(e.getCode() == KeyCode.I){
//				System.out.println(player.printBag()); //this will (hopefully) nicely print the array in neat columns
//			}
			
			if (e.getCode() == KeyCode.M){
				showMenu(player);
			}
			
			//R for refresh
			if (e.getCode() == KeyCode.R){
				updateBoard(Direction.NO_MOVE, player, g);
			}
			
			try{
				//I really like this format, it seems to work well with what we are doing
				
				if(onAdvance instanceof SpawnMonster){
					Creature c = ((SpawnMonster) onAdvance).getCreature();
					ArrayList<Attack> m = new ArrayList<Attack>();
					for (int i = 0; i < 4; i++){
						Attack a = new Attack("Attack " + (i + 1), 50 - 10 * i, AttackType.COMPUTER_SCIENCE, Effect.NONE);
						m.add(a);
					}
					c.setMoves(m);
					
					//this part still bugs for me, java 8u65
					Scene monsterEncounter = handle(c, player, boardScene);
					stage.setScene(monsterEncounter);
					
					monsterEncounter.addEventFilter(KeyEvent.KEY_PRESSED, action -> {
						if(action.getCode() == KeyCode.A){
							//this should just be the stage.setScene line, but the other stuff is to tame the JVM bug
							Platform.runLater(new Runnable(){
								@Override
								public void run(){
									stage.setScene(boardScene);
								}
							});
						}
						if (action.getCode() == KeyCode.SPACE || action.getCode() == KeyCode.ENTER){
							//would be nice to get some printing going on that tells the user whats happening
							//this is where a text box that displays strings would come in really nicely
							//maybe it would work best if we built that object in this try {if part
							//and then passed it to the handle method?
							if (!c.isAlive() || !friend.isAlive()){ 			
								//resets the health of your creature after each battle
								friend.setHealth(friend.getOriginalHealth());
								friend.setAlive();
								stage.setScene(boardScene);
							} else {
								//TO-DO
								//implement the creature's attack
							}
						}
					});
										
				}
				else if(onAdvance instanceof FindItem){
					Item newItem = ((FindItem) onAdvance).getItem();
					player.addToBag(newItem);
				}else{
					handle(onAdvance);
				}
				
			}catch(NullPointerException NPException){
				//means a button other than an arrow key was pressed, probably no big deal, 
				//though they could/should be handled more gracefully
			}
			location = player.getLocation();
			update(player, g, moveDirection);
		});
		root.getChildren().add(canvas);
		
		stage.show();

	}

	public Scene handle(Creature c, Player p, Scene boardScene){
		GridPane grid = new GridPane();

		//adding constraints builds on to the last restraint added so i isn't necessary in the placing of the
		//constraints, only in the deciphering of how many are necessary
		for (int i = 0; i < X_MAX; i++){
			grid.getColumnConstraints().add(new ColumnConstraints(SCREEN_WIDTH / X_MAX));
		}
		for (int i = 0; i < Y_MAX; i ++){
			grid.getRowConstraints().add(new RowConstraints(SCREEN_HEIGHT / Y_MAX));
		}

		Scene encounter = new Scene(grid, SCREEN_WIDTH, SCREEN_HEIGHT);
		encounter.getStylesheets().add("/stylesheets/EncounterStyle.css");
		
		//very helpful for debugging purposes
		grid.setGridLinesVisible(true);
		
		//drawing creature
		grid.add(c.getImage(), X_MAX - 5, 4);
		Text enemyName = new Text(c.getName());
		enemyName.setId("fancytext");
		grid.add(enemyName, X_MAX - 12, 1);
		
		//drawing creature's health bar
		c.setHealth(100); //arbitrary
		Canvas enemyHealthBar = new Canvas(120, 50);
		GraphicsContext enemyHealthBarG = enemyHealthBar.getGraphicsContext2D();
		enemyHealthBarG.setFill(Color.RED);
		enemyHealthBarG.fillRect(0, 0, enemyHealthBar.getWidth(), enemyHealthBar.getHeight());
		grid.add(enemyHealthBar, X_MAX - 10, 4);
		Text enemyHealth = new Text("  Health = " + c.getCurrentHealth() + " / " + c.getOriginalHealth());
		//enemyHealth.setId("healthText"); TODO make a health text thing in css
		//could maybe also be used to fashion a better health bar
		grid.add(enemyHealth, X_MAX - 10, 4);

		//drawing player
		grid.add(Player.getImage(), 2, Y_MAX - 3);
		Text playerName = new Text(p.getName());
		playerName.setId("fancytext");
		grid.add(playerName, 2, Y_MAX - 6);
		
		//drawing player's health bar
		Creature playerC = p.getMonsters().get(0);
		playerC.setHealth(100);
		Canvas playerHealthBar = new Canvas(120, 50);
		GraphicsContext playerHealthBarG = playerHealthBar.getGraphicsContext2D();
		playerHealthBarG.setFill(Color.RED);
		playerHealthBarG.fillRect(0, 0, playerHealthBar.getWidth(), playerHealthBar.getHeight());
		grid.add(playerHealthBar, 6, Y_MAX - 4);
		Text playerHealth = new Text("  Health = " + playerC.getCurrentHealth() + " / " + playerC.getOriginalHealth());
		//playerHealth.setId("healthText"); TODO make a health text thing in css
		//could maybe also be used to fashion a better health bar
		grid.add(playerHealth, 6, Y_MAX - 4);
		
		//drawing text box that narrates to user
		Canvas textBox = new Canvas(450, 300);
		GraphicsContext textBoxG = textBox.getGraphicsContext2D();
		clearTextBox(textBox, textBoxG);
		textBoxG.fillText(p.getName() + " encountered a wild " + c.getName(), tbX, tbY);
		grid.add(textBox, X_MAX - 10, Y_MAX - 4);
		
		//*******************drawing and creating move buttons**********************
		//move 1
		Button move1 = getMove(p, 0, c, enemyHealthBarG, enemyHealthBar, enemyHealth, textBoxG, textBox,
										playerHealthBarG, playerHealthBar, playerHealth);
		grid.add(move1, 6, Y_MAX - 3, 2, 1);
		
		//move 2
		Button move2 = getMove(p, 1, c, enemyHealthBarG, enemyHealthBar, enemyHealth, textBoxG, textBox,
										playerHealthBarG, playerHealthBar, playerHealth);
		grid.add(move2, 8, Y_MAX - 3, 2, 1);
		
		//move 3
		Button move3 = getMove(p, 2, c, enemyHealthBarG, enemyHealthBar, enemyHealth, textBoxG, textBox,
										playerHealthBarG, playerHealthBar, playerHealth);
		grid.add(move3, 6, Y_MAX - 2, 2, 1);
		
		//move 4
		Button move4 = getMove(p, 3, c, enemyHealthBarG, enemyHealthBar, enemyHealth, textBoxG, textBox,
										playerHealthBarG, playerHealthBar, playerHealth);
		grid.add(move4, 8, Y_MAX - 2, 2, 1);
		
		return encounter;
	}
	
	//this works by passing a bunch of nodes and changing their values
	//not the best style but is better than before
	public Button getMove(Player p, int moveNum, Creature enemy, GraphicsContext enemyHealthBarG, 
			Canvas enemyHealthBar, Text enemyHealth, GraphicsContext textBoxG, Canvas textBox, 
			GraphicsContext playerHealthBarG, Canvas playerHealthBar, Text playerHealth){
		
		Creature friend = p.getMonsters().get(0);
		Attack[] moves = friend.getMoves();
		
		Button move = new Button();
		move.setMaxWidth(2 * SCREEN_WIDTH / X_MAX);
		move.setMinWidth(2 * SCREEN_WIDTH / X_MAX);
		move.setMaxHeight(SCREEN_HEIGHT / Y_MAX);
		move.setMinHeight(SCREEN_HEIGHT / Y_MAX);

		if (moves[moveNum] == null){
			move.setText("move 1");
		} else {
			move.setText(moves[moveNum].toString());
		}
		
		move.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE){
				/* any time a move is chosen, the health bar will first be cleared
				 * if the creature is alive, the health will be corrected according to the move's damage
				 * if the creature is not alive, nothing needs to happen because the health bar 
				 * is already wiped. 
				 */
				enemyHealthBarG.clearRect(0, 0, enemyHealthBar.getWidth(), enemyHealthBar.getHeight());
				enemyHealthBarG.strokeRect(0, 0, enemyHealthBar.getWidth(), enemyHealthBar.getHeight());
				enemy.takeDamage(moves[moveNum].getBaseDamage());
				enemyHealth.setText("  Health = " + enemy.getCurrentHealth() + " / " + enemy.getOriginalHealth());
				if(enemy.isAlive()){
					clearTextBox(textBox, textBoxG);
					textBoxG.fillText(p.getName() + " used " + moves[moveNum].toString(), tbX, tbY);
					enemyHealthBarG.fillRect(0, 0, (enemy.getCurrentHealth() * 1.0) / enemy.getOriginalHealth() * 
							enemyHealthBar.getWidth(), enemyHealthBar.getHeight());
					Attack a = enemy.performAttack();
					friend.takeDamage(a.getBaseDamage());
					playerHealth.setText("  Health = " + friend.getCurrentHealth() + " / " + friend.getOriginalHealth());
					textBoxG.fillText(enemy.getName() + " used " + a.toString(), tbX, 2 * tbY);
					playerHealthBarG.clearRect(0, 0, playerHealthBar.getWidth(), playerHealthBar.getHeight());
					playerHealthBarG.strokeRect(0, 0, playerHealthBar.getWidth(), playerHealthBar.getHeight());

					if (friend.isAlive()){
						playerHealthBarG.fillRect(0, 0, (friend.getCurrentHealth() * 1.0) / friend.getOriginalHealth() * 
								playerHealthBar.getWidth(), playerHealthBar.getHeight());
					} else {
						textBoxG.fillText(p.getName() + " fainted!", tbX, 3 * tbY);
					}
				} else {
					//print some kind of victory text?
					clearTextBox(textBox, textBoxG);
					textBoxG.fillText(p.getName() + " used " + moves[moveNum].toString(), tbX, tbY);
					textBoxG.fillText(p.getName() + " defeated " + enemy.getName(), tbX, 2 * tbY);
					friend.setHealth(friend.getOriginalHealth());
				}
			}
		});
		return move;
	}
	
	public static void clearTextBox(Canvas c, GraphicsContext g){
		g.clearRect(0, 0, c.getWidth(), c.getHeight());
		g.setFill(Color.WHITE);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		g.setFill(Color.BLACK);
	}
 	
	
	//creates a new random board and moves the player to where they would appear if they 
	//had entered the door they moved through
	private void updateBoard(Direction d, Player player, GraphicsContext g) {
		this.board = BoardGenerator.generate(GEN_STYLE);
		
		//since player is initiated with the original board, the player's board 
		//must be edited every time a board is altered
		player.setBoard(this.board);
		hasUpdated = false;
		
		if (d.equals(Direction.WEST)){
			player.advance(X_MAX - 2, 0);
		} else if (d.equals(Direction.EAST)){
			player.advance(-(X_MAX - 2), 0);
		} else if (d.equals(Direction.NORTH)){
			player.advance(0, (Y_MAX - 2));
		} else if (d.equals(Direction.SOUTH)){
			player.advance(0, -(Y_MAX - 2));
		}
		update(player, g, Direction.NO_MOVE);
	}

	private void showMenu(Player player) {
		//****I want to set a general rule that declares what buttons control which aspects of the program****
		//I currently am setting it where space is an "accept" button, carrying out the button's role
		//and B is the "back" button, returning to the previous screen. 
		//we can change this if we want but i think its easy and intuitive
		//these buttons should be reserved for these two uses to avoid confusion
		
		//this will make a new window that is the menu, so it is different from what some programs do, but idk 
		//a good way of doing it another way
		
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
			if (e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ENTER)
				menuStage.close();
		});
		
		Button inventory = new Button("Inventory");
		inventory.setMinWidth(menuButtonWidth);
		inventory.setMaxWidth(menuButtonWidth);
		
		
		inventory.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ENTER){
				Stage inventoryStage = new Stage();
				inventoryStage.setTitle("Inventory Menu");
				inventoryStage.initModality(Modality.APPLICATION_MODAL);
				inventoryStage.setHeight(menuHeight);
				inventoryStage.setWidth(menuWidth);
				VBox inventoryMenuObjs = new VBox();
				Scene inventoryMenuScene = new Scene(inventoryMenuObjs, menuHeight, menuWidth);
				inventoryMenuObjs.setAlignment(Pos.CENTER);
				Region inventoryMidSpring = new Region();
				VBox.setVgrow(inventoryMidSpring, Priority.ALWAYS);
				
				Button pictures = new Button("Pictures");
				pictures.setMinWidth(menuButtonWidth);
				pictures.setMaxWidth(menuButtonWidth);
				
				pictures.addEventFilter(KeyEvent.KEY_PRESSED, f -> {
					if (f.getCode() == KeyCode.SPACE || f.getCode() == KeyCode.ENTER){
						inventoryStage.setScene(getInventoryScene(player));
						
						inventoryStage.addEventFilter(KeyEvent.KEY_PRESSED, g -> {
							if (g.getCode() == KeyCode.B)
								inventoryStage.setScene(inventoryMenuScene);
						});
					}
				}); 
				
				Button words = new Button("Words");
				words.setMinWidth(menuButtonWidth);
				words.setMaxWidth(menuButtonWidth);
				
				words.addEventFilter(KeyEvent.KEY_PRESSED, f -> {
					if (f.getCode() == KeyCode.SPACE || f.getCode() == KeyCode.ENTER){
						inventoryStage.setScene(getWordsScene(player, inventoryMenuObjs));
						inventoryStage.addEventFilter(KeyEvent.KEY_PRESSED, g -> {
							if (g.getCode() == KeyCode.B)
								inventoryStage.setScene(inventoryMenuScene);
						});
					}
				});
				
				Button cont2 = new Button("Continue"); 		//i can't name it continue, its a reserved word
				cont2.setMinWidth(menuButtonWidth);
				cont2.setMaxWidth(menuButtonWidth);
				
				cont2.addEventFilter(KeyEvent.KEY_PRESSED, f -> {
					if (f.getCode() == KeyCode.SPACE || f.getCode() == KeyCode.ENTER)
						inventoryStage.close();
				});
				
				inventoryMenuObjs.getChildren().addAll(inventoryMidSpring, pictures, words, cont2);
				inventoryStage.setScene(inventoryMenuScene);
				inventoryStage.show();

			}
				
		});
		
		Button money = new Button("Money"); 		//i can't name it continue, its a reserved word
		money.setMinWidth(menuButtonWidth);
		money.setMaxWidth(menuButtonWidth);
		
		money.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ENTER){
				menuStage.setScene(getMoneyScene(player));
				menuStage.addEventFilter(KeyEvent.KEY_PRESSED, f -> {
					if (f.getCode() == KeyCode.B)
						menuStage.setScene(mainMenuScene);
				});
			}
		});
		
		mainMenuObjs.getChildren().addAll(midSpring, inventory, money, cont);
		
		menuStage.setScene(mainMenuScene);
		menuStage.show();
	}
	
	private Scene getMoneyScene(Player player){
		GridPane moneyGrid = new GridPane();
		Canvas canvas = new Canvas(menuWidth, menuHeight);
		GraphicsContext g = canvas.getGraphicsContext2D();
		Scene moneyScene = new Scene(moneyGrid, menuWidth, menuHeight);
		
		g.fillText(player.getName() + " has $" + player.getMoney(), 10, 20);
		
		moneyGrid.add(canvas, 0, 0); 		
		return moneyScene;
	}
	
	private Scene getWordsScene(Player player, VBox vb){
		GridPane wordsGrid = new GridPane();
//		ScrollBar s = new ScrollBar();
//		wordsGrid.getChildren().add(s);
//		
//		s.setLayoutX(menuWidth-s.getWidth());
//        s.setMin(0);
//        s.setOrientation(Orientation.VERTICAL);
//        s.setPrefHeight(180);
//        s.setMax(360);
//        
//        s.valueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                Number old_val, Number new_val) {
//                    vb.setLayoutY(-new_val.doubleValue());
//            }
//        });
        
		Canvas canvas = new Canvas(menuWidth, menuHeight);
		GraphicsContext g = canvas.getGraphicsContext2D();
		Scene moneyScene = new Scene(wordsGrid, menuWidth, menuHeight);
		
		g.fillText(player.printBag(), 10, 20);
		
		wordsGrid.add(canvas, 0, 0); 
		
		return moneyScene;
	}

	private Scene getInventoryScene(Player player) {
		ArrayList<Item> localBag = player.getBag();
		System.out.println(player.printBag());
		GridPane inventoryGrid = new GridPane();
		Scene inventoryScene = new Scene(inventoryGrid , menuWidth, menuHeight);
//		int maxHorizIcons = (int) ((double)(MENU_WIDTH) / Item.ICON_SIZE);
//		int numRows = (int) Math.ceil((double)(localBag.size()-1) / maxHorizIcons);
		int count = 0;
		for(int i = 0; i < menuWidth / Item.ICON_SIZE; i++){
			for(int j = 0; j < menuHeight / Item.ICON_SIZE-2; j++){
				if(count < localBag.size()){
					ImageView temp = new ImageView();
					temp.setImage(localBag.get(count).getIcon());
					inventoryGrid.add(temp, j, i);
				}
				count++;
			}
		}
		return inventoryScene;
	}
	
	public void scaleGraphics(GraphicsContext g){
		
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
	 * I changed the objects that implemented event from printing in their constructor to producing 
	 * the same output in toString, its cleaner
	 */
	public void handle(Event e){
		System.out.println(e.toString());
	}
		
	public void update(Player player, GraphicsContext g, Direction moveDirection){
		if(!hasUpdated){
			drawGround(g);
		}
		hasUpdated = true;
		
		//draw player
		double size = Player.SIZE;
		location = player.getLocation();
		int x = location[0];
		int y = location[1];
		
		//clears old player
		switch(moveDirection){
		case NORTH:
			g.drawImage(new Image(board.getBoard().get(x).get(y+TILE_SIZE).getImagePath()), 
					x, y+TILE_SIZE,TILE_SIZE,TILE_SIZE);
			break;
		case SOUTH:
			g.drawImage(new Image(board.getBoard().get(x).get(y-TILE_SIZE).getImagePath()), 
					x, y-TILE_SIZE,TILE_SIZE,TILE_SIZE);
			break;
		case EAST:
			g.drawImage(new Image(board.getBoard().get(x-TILE_SIZE).get(y).getImagePath()), 
					x-TILE_SIZE, y,TILE_SIZE,TILE_SIZE);
			break;
		case WEST:
			g.drawImage(new Image(board.getBoard().get(x+TILE_SIZE).get(y).getImagePath()), 
					x+TILE_SIZE, y,TILE_SIZE,TILE_SIZE);
			break;
		default:
			break;
		}
		
		g.setFill(player.getColor());
		g.fillRect(location[0] + (TILE_SIZE-size)/2, location[1] + (TILE_SIZE-size)/2, size, size);
		
	}

	private void drawGround(GraphicsContext g) {
		for(ArrayList<Tile> row : board.getBoard()){
			for(Tile tempTile : row){
				Image temp = new Image(tempTile.getImagePath());
				g.drawImage(temp, tempTile.getX(), tempTile.getY(), TILE_SIZE, TILE_SIZE);
			}
		}

		
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
