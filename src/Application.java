/*French learning application using words and sentences
 * use a series of categories and implement a beginner and intermediate level*/

import processing.core.*;

import java.util.ArrayList;

import controlP5.*;

import ddf.minim.*;


public class Application extends PApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Global Variables
	int screen; 
	int border = 50;
	int selected = 0;
	int ssIndex = 0;
	int rand = 0;
	int score = 0;
	int timer = 0;
	int loc = 0;
	boolean playFirst = true;
	boolean playLast = true;
	boolean click = false;
	boolean buttonClicked = false;
	static boolean hitTarget = false;
	boolean createTarget = true;
	static boolean createBall = true;
	boolean newItem = true;
	boolean chooseWord = true;
	//used to navigate through selected category
	int i = 0;
	int k = 0;
	String catName;
	String word;
	//used when looping through the selected category
	int maxSize = 0;
    int count = 0;
    int listCount = 0;
	int selectedCount = 0;
	static boolean matchGame = false;
	boolean startMatchGame = false;
	boolean setUpMatchGame = false;
	boolean wordLaunchGame = false;
	boolean startWordLaunchGame = false;
	boolean setUpWordLaunchGame = false;
	static boolean shootAndStrike = false;
	boolean startShootAndStrike = false;
	boolean setUpShootAndStrike = false;
	public int countSelected = 0;
	int butX = 10; 
	int butY = 10;

	
	ControlP5 nav;
	ControlP5 cat;
	ControlP5 games;
	ControlP5 gamesCat;
	ControlP5 menu;
	ControlP5 mode;
	ControlP5 start;
	ControlP5 gameNav;
	ControlP5 replaySound;
	PImage img;
	Minim minim;
	AudioPlayer track;
	PImage sound;
	PFont myFont;
	
    //yes
	//global arrayList to hold the loaded category
	ArrayList<CurrentCategory> CurCatItems = new ArrayList<CurrentCategory>();
	
	//String array to hold ten random items for match game
	ArrayList<CurrentCategory> randomItems = new ArrayList<CurrentCategory>();
	
	//used in the moving card game
	ArrayList<CurrentCategory> copyRandomItems = new ArrayList<CurrentCategory>();
	
	static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	boolean[] keys = new boolean[512];
	
	public void keyPressed()
	{
	  keys[keyCode] = true;
	}

	public void keyReleased()
	{
	  keys[keyCode] = false;
	}
	
	
	
	public void setup() {
	    size(500,500);
	    background(255);
	    screen = 0;
	    //add font to program
	    myFont = createFont("Funnier.ttf", 32);
	 
		
	    //****************BUTTONS**********************
	    
	    //start Menu Buttons
	  
  		start = new ControlP5(this);
  		start.addButton("Lets Start!")
  	     .setValue(1)
  	     .setColorActive(125)
  	     .setColorBackground(0) 
  	     .setPosition(150,150)
  	     .setSize(200,50)
  	     .addCallback(new CallbackListener() {
	        @SuppressWarnings("deprecation")
			public void controlEvent(CallbackEvent event) {
	           if (event.getAction() == ControlP5.ACTION_RELEASED) {
	             println("button released.");
	             screen = 1;
	           }
	         }
	       })
  	     ;
  		start.addButton("About")
  		 .setValue(2)
  		 .setPosition(150,200)
  		 .setSize(200,50)
  		 ;
  		start.addButton("Exit")
  		 .setValue(3)
  		 .setPosition(150,250)
  		 .setSize(200,50)
  		 ;
	    
  		
	    //Navigation Menu Buttons
	    menu = new ControlP5(this);
	    menu.addButton("Back")
	    .setValue(1)
	     .setPosition(5,5)
	     .setSize(50,50)
	     .addCallback(new CallbackListener() {
	        @SuppressWarnings("deprecation")
			public void controlEvent(CallbackEvent event) {
	           if (event.getAction() == ControlP5.ACTION_RELEASED) {
	             println("button released.");
	             screen --;
	           }
	         }
	       })
	     ;
	    //Mode Selection Buttons
	    mode = new ControlP5(this);
	    mode.addButton("Words")
	     .setValue(1)
	     .setPosition(150,150)
	     .setSize(200,50)
	     .addCallback(new CallbackListener() {
		        @SuppressWarnings("deprecation")
				public void controlEvent(CallbackEvent event) {
		           if (event.getAction() == ControlP5.ACTION_RELEASED) {
		             println("button released.");
		             screen =2;
		           }
		         }
		       })
	     ;
	    mode.addButton("Games")
	     .setValue(1)
	     .setPosition(150,200)
	     .setSize(200,50)
	     .addCallback(new CallbackListener() {
		        @SuppressWarnings("deprecation")
				public void controlEvent(CallbackEvent event) {
		           if (event.getAction() == ControlP5.ACTION_RELEASED) {
		             println("button released.");
		             screen = 4;
		           }
		         }
		       })
	     ;
	    
	    gameNav = new ControlP5(this);
	    gameNav.addButton("Back1")
	     .setValue(1)
	     .setPosition(butX,butY)
	     .setSize(50,50)
	     ;
	    		 
	    //Category Buttons (words)
	    cat = new ControlP5(this);
		cat.addButton("Numbers")
	     .setValue(1)
	     .setPosition(150,150)
	     .setSize(200,50)
	     ;
		cat.addButton("Alphabet")
		 .setValue(2)
		 .setPosition(150,200)
		 .setSize(200,50)
		 ;
		cat.addButton("Family")
		 .setValue(3)
		 .setPosition(150,250)
		 .setSize(200,50)
		 ;
		cat.addButton("Fruits")
		 .setValue(3)
		 .setPosition(150,300)
		 .setSize(200,50)
		 ;
		
		//game choice buttons
		games = new ControlP5(this);
		games.addButton("MatchGame")
		 .setValue(1)
	     .setPosition(150,150)
	     .setSize(200,50)
		;
		games.addButton("WordLaunch")
		 .setValue(2)
	     .setPosition(150,200)
	     .setSize(200,50)
		;
		games.addButton("ShootAndStrike")
		 .setValue(2)
	     .setPosition(150,250)
	     .setSize(200,50)
		;
		//Category Buttons (Games)
		gamesCat = new ControlP5(this);
		gamesCat.addButton("Numbers1")
	     .setValue(1)
	     .setPosition(150,150)
	     .setSize(200,50)
	     ;
		gamesCat.addButton("Fruits1")
	     .setValue(1)
	     .setPosition(150,200)
	     .setSize(200,50)
	     ;
		gamesCat.addButton("Family1")
	     .setValue(1)
	     .setPosition(150,250)
	     .setSize(200,50)
	     ;
		
	    //Navigation for Category words screen 
	    nav = new ControlP5(this);
		nav.addBang("navLeft")
	    .setPosition(0 + border, height-border*2)
	    .setSize(50, 50)
	    .setTriggerEvent(Bang.RELEASE)
	    .setColorValueLabel(0)
	    .setLabel("Left")
	    ;
		nav.addBang("navRight")
	    .setPosition(width-border*2, height-border*2)
	    .setColorValueLabel(0)
	    .setSize(50, 50)
	    .setTriggerEvent(Bang.RELEASE)
	    .setLabel("Right")
	    ;
	  }
	
	public void draw(){
		background(255);
		textFont(myFont);
		textSize(32);
		smooth();
		count = 1;
		
		//menu
		if(screen == 0){
			
			//visible buttons
			start.setVisible(true);
			//invisible buttons
			menu.setVisible(false);
			nav.setVisible(false);
			cat.setVisible(false);
			mode.setVisible(false);
			gamesCat.setVisible(false);
			games.setVisible(false);
			gameNav.setVisible(false);
		}
		//mode
		if(screen == 1){
			//visible buttons
			modeSelection();
			mode.setVisible(true);
			menu.setVisible(true);
			//invisible buttons
			start.setVisible(false);
			nav.setVisible(false);
			cat.setVisible(false);
			gamesCat.setVisible(false);
			games.setVisible(false);
			gameNav.setVisible(false);
		}
		//category
		if(screen == 2){
			categories();
			//visible buttons
			cat.setVisible(true);
			menu.setVisible(true);
			//invisible buttons
			nav.setVisible(false);
			start.setVisible(false);
			mode.setVisible(false);
			gamesCat.setVisible(false);
			games.setVisible(false);
			gameNav.setVisible(false);
			
		}
		
		//words navigation
		if(screen == 3){
			//visible buttons
			menu.setVisible(true);
			if(buttonClicked){
				loadCurrentCategory(catName);
				navigateCat(catName);
			}
			//invisible buttons
			cat.setVisible(false);
			gamesCat.setVisible(false);
			games.setVisible(false);
			gameNav.setVisible(false);
			
		}
		//Matching picture word game
		if(screen == 4){
			//visible buttons
			gamesCat.setVisible(true);
			gameNav.setVisible(true);
			
			//hidden buttons
			nav.setVisible(false);
			mode.setVisible(false);
			cat.setVisible(false);
			menu.setVisible(false);
			start.setVisible(false);
			games.setVisible(false);
			
			
			//load the chosen category 
			
				
		}
		
		//Correct picture shooting game 
		if(screen == 5){
			//visible buttons
			games.setVisible(true);
			gameNav.setVisible(true);
			
			
			//hidden buttons
			nav.setVisible(false);
			mode.setVisible(false);
			cat.setVisible(false);
			menu.setVisible(false);
			start.setVisible(false);
			gamesCat.setVisible(false);
			
			
			//if matchGame is selected
			if(matchGame){
				games.setVisible(false);
				
				
				if(setUpMatchGame)
				{
					
					//need to change the parameter
					loadCurrentCategory(catName);
					//Pass the arraylist to the function
					chooseRandomItems(CurCatItems);
					//sets up the game
					setUpMatchGame(catName);
					
					
					//make Matchgame Start
					startMatchGame = true;
					setUpMatchGame = false;
					buttonClicked = false;
				}
			
				if(startMatchGame){
				
					gamesCat.setVisible(false);
					for(int i = 0; i < gameObjects.size(); i++){
						GameObject go = gameObjects.get(i);
						go.render();
						go.update();
					}
					
					//function to constantly check which tiles are selected
					checkSelected();
				
					if(selectedCount >= 2){
						deSelectAll();
					}
			
				}
			}
			
			
			//Starts wordLaunch if its selected
			if(wordLaunchGame){
				gameNav.setVisible(true);
				games.setVisible(false);
				// 1. Set up the game
				if(setUpWordLaunchGame == true){
					
					//need to change the parameter
					loadCurrentCategory(catName);
					//Pass the arraylist to the function load up random items from category
					chooseRandomItems(CurCatItems);
					
					//copies random items to another array list
					for(CurrentCategory o : randomItems)
						copyRandomItems.add(o);
					
					startWordLaunchGame = true;
					setUpWordLaunchGame = false;
					click = false;
					
				}//end setuplaunchGame
				
				//if Picture Motion game selected
				if(startWordLaunchGame && copyRandomItems.size() > 0){
					gamesCat.setVisible(false);
					
					//launches the tiles into the screen
					launchTiles(catName);
					
					textSize(30);
					fill(0);
					textAlign(CENTER,CENTER);
					text(score + "/10", width-border, border);
			
				}
				
				//used to update the motionTiles during game play
				for(int i = 0; i < gameObjects.size(); i++){
					GameObject go = gameObjects.get(i);
					if(go instanceof MotionTile){
						go.render();
						go.update();
					}
					
				}
			}//end if launch game
			
			if(shootAndStrike){
				gameNav.setVisible(true);
				games.setVisible(false);
				
				if(startShootAndStrike){
					setupShootAndStrike();
					
				}
				
				if(createTarget){
					for(int i = 0 ; i < 5;i++){
						 Target target = new Target(this,(border + random(400)),(border + (border  * i)),15);
						 gameObjects.add(target);
					}
				 createTarget = false;
				}
				
				if(createBall){
					//creates a new instance of ball
					 Ball ball = new Ball(this,width/2,height-(border*2),25);
					 gameObjects.add(ball);
					 createBall = false;
				}
				
				if(hitTarget){
					hitTarget();
				}
				
				for(int i = 0; i < gameObjects.size(); i++){
					GameObject go = gameObjects.get(i);
					if(!hitTarget){
						if(go instanceof Ball){
							((Ball)go).aim();
						}
					}
					
					if(!hitTarget){
						go.update();
						go.render();
					}
					
					
				}
				checkBallCollisions();
			}//end shoot and strike
			
			
			
		}
		
	}
	
	public void setupShootAndStrike(){
		//need to change the parameter
		loadCurrentCategory(catName);
		//Pass the arraylist to the function load up random items from category
		chooseRandomItems(CurCatItems);
		
		for(int i = 0; i < copyRandomItems.size();i++){
			copyRandomItems.remove(i);
		}
		
		//copies random items to another array list
		for(CurrentCategory o : randomItems){
			copyRandomItems.add(o);
		}
		//leave 5 elements in copyRandomItems and delete the rest
		for(int i = 0; i < randomItems.size()-5;i++){
			copyRandomItems.remove(i);
		}
		
		
		startShootAndStrike = false;
	}
	
	public void hitTarget(){
		fill(0);
		rect(0,height/4, width,width/2);
		
		//places a tile into word variable this will be our correct word
		if(chooseWord){
			//use loc to choose a place in array for it
			loc = (int) random(2);
			//use word to locate image and audio files
			word = copyRandomItems.get(loc).eng;
			
			for(int i = 0 ; i < 3; i++){
				//if i doesnt already match the current location of the choosen word
				if(i != loc){
					Tile tile = new Tile(this,(border*2) + (110 * i),height/2,copyRandomItems.get(i).eng, 0, catName, copyRandomItems.get(i).fr);
					gameObjects.add(tile);
				}
				//if i does match the location of the chosen word
				else{
					Tile tile = new Tile(this,(border*2) + (110 * i),height/2,copyRandomItems.get(i).eng, 0, catName, copyRandomItems.get(i).fr);
					gameObjects.add(tile);
				}
				
			}
			chooseWord = false;
			//choosen random number between 0,1,2 in order to give the correct picture a random location each time 
		}
		
		textSize(30);
		fill(255);
		textAlign(CENTER,CENTER);
		text(copyRandomItems.get(loc).fr,width/2,height/3);
		
		
		//updates and renders the tiles currently in play
		//also detects selected tiles and checks to see if it is the correct answer
		for(int i = 0; i < gameObjects.size(); i++){
			GameObject go = gameObjects.get(i);
			if(go instanceof Tile){
				
				go.update();
				go.render();
				
				if(((Tile)go).selected == true){
					if(((Tile)go).id == word){
						System.out.println("Well Done");
					}
					else{
						System.out.println("Wrong");
					}
					
					for(int j = 0; j < gameObjects.size(); j++){
						GameObject t = gameObjects.get(j);
						if(t instanceof Ball){
							gameObjects.remove(t);
						}
					}
					
					startShootAndStrike = true;
					createBall = true;
					chooseWord = true;
					hitTarget = false;
					
					
				}
				
				
				
			}
		}
	}
	
	//checks ball and target collisions
	void checkBallCollisions()
	{
	 for(int i = gameObjects.size() - 1 ; i >= 0   ;i --)
	 {
	    GameObject go = gameObjects.get(i);
	    if (go instanceof Ball)
	    {
	      for(int j = gameObjects.size() - 1 ; j >= 0   ;j --)
	      {
	        GameObject other = gameObjects.get(j);
	        if (other instanceof Target) // Check the type of a object
	        {
	          // Bounding circle collisions
	          if (go.pos.dist(other.pos) < ((Ball)go).halfW + ((Target)other).halfW)
	          {
	        	  System.out.println(go.pos);
	        	  System.out.println(other.pos);
	              gameObjects.remove(other);
	              System.out.println("Target removed");
	              hitTarget = true;
	          }
	        }
	      }
	    }
	 } 
	}
	
	//used when user tries to click more than 2 tiles at once
	public void deSelectAll(){
		for(int i = 0 ; i < gameObjects.size(); i ++){
			GameObject go = gameObjects.get(i);
				if(go instanceof Tile){
					if(((Tile) go).selected == true){
						((Tile) go).selected = false;
				    }
			    }
		}
	}
	
	
	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "Application" });
	}
	
	public void checkSelected(){
		String var1 = null;
		String var2 = null;
		int var1Pos = 0;
		int var2Pos = 0;
		
		//goes through each object in game object and searches for instances of tile
		//when found , if it is selected , it is placed in var1
		//it then checks for a second selection and places it into var2 if that too is selected
		
		for(int i = 0 ; i < gameObjects.size(); i ++){
			GameObject go = gameObjects.get(i);
			if(var1 == null){
				//Polymorphism
				if(go instanceof Tile){
					if(((Tile) go).selected == true){
						 var1 = ((Tile) go).id;
						 var1Pos = i;
						 selectedCount = 1;
					}
					else{
						selectedCount = 0;
					}
				}
			}
			else{
				if(go instanceof Tile){
					if(((Tile) go).selected == true){
						 var2 = ((Tile) go).id;
						 var2Pos = i-1;
						 selectedCount = 2;
					}
				}
			}
		}
		
		//if the two selected items are equal then they are removed from the board
		if(var1 == var2 && var1 != null && var2 != null ){
			System.out.println(var1);
			minim = new Minim(this);
			track = minim.loadFile("Audio/"+catName+"/"+var1+".mp3");
			track.rewind();
			track.play();
			gameObjects.remove(var1Pos);
			gameObjects.remove(var2Pos);
		}
	}
	
	public void startScreen(){
		
		
	}
	
	//function to run the Launching Pictures game 
	public void launchTiles(String cat){
		
		int t = 0;
		minim = new Minim(this);
		if (newItem){
			
			rand = (int) random(copyRandomItems.size());
			track = minim.loadFile("Audio/"+catName+"/"+copyRandomItems.get(rand).eng+".mp3");
			track.rewind();
			track.play(); 
			newItem = false;
			k = 0;
		}
		
		String checkItem = copyRandomItems.get(rand).eng;
		textSize(30);
		fill(0);
		textAlign(CENTER,CENTER);
		text(copyRandomItems.get(rand).fr, width/2, border);
		
		//create a new tile to launch every second
		if(frameCount % 60 == 0 && timer > 2){
			if(k < randomItems.size()){
				//used to check against selected tile
				
				
				float x =random(border, width-border*2);
				float y = height ;
				
				MotionTile mTile = new MotionTile(this,x,y,randomItems.get(k).eng, t, cat, randomItems.get(k).fr);
				gameObjects.add(mTile);
				track = minim.loadFile("Bow.mp3");
				track.rewind();
				track.play(); 
				k++;
			}
			
		}
		
		//increment timer every second
		if(frameCount % 60 == 0){
			timer++;
		}
		
		for(int i = 0 ; i < gameObjects.size(); i ++){
			GameObject go = gameObjects.get(i);
			//Polymorphism
			if(go instanceof MotionTile){
				if(((MotionTile) go).selected == true){
					//if the selected tile matches the current word then remove the word from the copied list and increment count
					System.out.println(((MotionTile)go).id);
					if(checkItem == ((MotionTile)go).id){
						//increment the score
						score += 1;
						//give k value of ten in order to reinitialize 
						track = minim.loadFile("Audio/"+catName+"/"+((MotionTile)go).id+".mp3");
						track.rewind();
						track.play(); 
						delay(2000);
						((MotionTile) go).selected = false;
						timer = 0;
						newItem = true;
						//remove this element from the list so it doesn't re occur
						copyRandomItems.remove(rand);
					}
					else{
						if(score > 0){
							score -= 1;
						}
						track = minim.loadFile("Slap.mp3");
						track.rewind();
						track.play(); 
						delay(2000);
						((MotionTile) go).selected = false;
						timer = 0;
						newItem = true;
					}
				}
			}
		}
		
		//if no tiles are selected deduct points and create a newItem
		if(timer == 15){
			//delay(2000);
			timer = 0;
			newItem = true;
		}
		
	}
	
	public void modeSelection(){
		/*cp5 = new ControlP5(this);
		cp5.addButton("Beginner")
	     .setValue(1)
	     .setPosition(150,150)
	     .setSize(200,50)
	     ;
		cp5.addButton("Intermediate")
		 .setValue(2)
		 .setPosition(150,200)
		 .setSize(200,50)
		 ;
		cp5.addButton("Back")
		 .setValue(3)
		 .setPosition(150,250)
		 .setSize(200,50)
		 ;*/
	}
	public void categories(){
		
	}
	//function delay()
	public void delay(int delay){
		int time = millis();
		while(millis() - time <= delay);
	}
	
	/*Function to load in chosen category
	 */
	 void loadCurrentCategory(String catName){
		    //cat will be the selected category by the user and will place the name into cat.
		 	CurCatItems.clear();
		    String cat = catName;
		    String[] lines = loadStrings("Text/"+cat+".csv");
		    
		    
		    // System.out.println(CurCatItems.size());
	    	//if list is already empty
	    	if(CurCatItems.size() == 0){
	    		
	    	
	    		for(int i = 0 ; i < lines.length; i++){
	    			//reads in each line from the selected text file  
		    		CurrentCategory curCatItem = new CurrentCategory(lines[i]);
		        	CurCatItems.add(curCatItem);
		        	maxSize = i;
	    		}
	    		//System.out.println(CurCatItems.size());
	    	}
	    	else{
	    		for(int i = 0 ; i < lines.length; i++){
	    			//reads in each line from the selected text file  
	    			CurrentCategory curCatItem = new CurrentCategory(lines[i]);
		    		CurCatItems.set(i,curCatItem);
		    		maxSize = i;
	    		}
	    		
	    	}
		        
		  } 

	 public void navigateCat(String catName){
		
	    
		String cat = catName;
		//sets navigation buttons to visible
		nav.setVisible(true);
	    
		
		
		//handles if i goes beyond the array
	    if(i == maxSize + 1){
	    	i = 0;
	    	playFirst = true;
	    }
	    if(i < 0 ){
	    	i = maxSize;
	    	playLast = true;
	    }
	  //gets the matching Text for value in x
	    String x = CurCatItems.get(i).eng;
	    
	    if(playFirst){
		    minim = new Minim(this);
			track = minim.loadFile("Audio/"+catName+"/"+x+".mp3");
	        track.rewind();
	        track.play();
	        playFirst = false;
	    }
	    if(playLast){
		    minim = new Minim(this);
			track = minim.loadFile("Audio/"+catName+"/"+x+".mp3");
	        track.rewind();
	        track.play();
	        playLast = false;
	    }
	    
	    //gets the matching picture for value in x
	    img = loadImage("Images/"+cat+"/"+x+".jpg");
	    //gets the matching audio for value in x
	    imageMode(CENTER);
	    image(img, width/2, height/2, 200,200);
	    
	    textFont(myFont);
	    textAlign(CENTER,CENTER);
	    fill(0);
	    textSize(24);
	    text(CurCatItems.get(i).fr,width/2, height-border*2);
	    text((i+1) + "/" + (maxSize+ 1), border*2 , border*2 );
	 }
	 
	 
	 //**************************GAMES SET UPS ***************************************
	 //_______________________________________________________________________________
	 
	 
	 //sets up the matching game 
	 public void setUpMatchGame(String cat){
		 float y = 0;
		 float x = 70;
		 //k used to add category id's to tiles
		 int k = 0;
		 //type used to determine whether going to be image or text(0=text, 1 = image)
		 boolean type = false;
		 int t = 0;
		 
		 for(int i = 0 ; i < 5; i++){
				
				y = 50 + (90 * i);
				
				for(int j = 0 ; j < 4; j++){
					
					//when k gets to 9 reinitialize to zero to add second copy of random items to tiles
					if(k >= 10){
						k = 0;
						type = !type;
					}
					if(!type){
						t = 0;
					}
					if(type){
						t = 1;
					}
					Tile tile = new Tile(this,x,y,randomItems.get(k).eng, t, cat, randomItems.get(k).fr);
					gameObjects.add(tile);
					x += 90;
					type = !type;
					k++;
				}
				//reinitialize x before next iteration
				x = 70;
				
				
			}
	 }
	 
	//****************BUTTON EVENTS*******************************
	 
	 //_____MENU BUTTON EVENTS_____
	 public void Exit(){
		 if(count > 0){
			 exit();
		 }
	 }
	 
	 //______NAVIGATION BUTTON EVENTS_____
	 
	 //moves position of cards to the right
	 public void navRight(){
		 i++;
		 String x = CurCatItems.get(i).eng;
		 minim = new Minim(this);
		 track = minim.loadFile("Audio/"+catName+"/"+x+".mp3");
         track.rewind();
         track.play(); 
	 }
	 //moves position of cards to the Left
	 public void navLeft(){
		 i--;
		 String x = CurCatItems.get(i).eng;
		 minim = new Minim(this);
		 track = minim.loadFile("Audio/"+catName+"/"+x+".mp3");
         track.rewind();
         track.play(); 
	 }
	 
	 
	 //_____CATEGORY BUTTON EVENTS_____
	 
	 //if the button numbers is selected it loads that into the current category
	public void Numbers(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i=0;
			catName = "Numbers";
			buttonClicked = true;
			playFirst = true;
			screen = 3;
			
		}
	}
	//if the button Alphabet is selected it loads that into the current category
	public void Alphabet(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i= 0;
			catName = "Alphabet";
			buttonClicked = true;
			playFirst = true;
			screen = 3;
		}
	}
	public void Family(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i= 0;
			catName = "Family";
			buttonClicked = true;
			playFirst = true;
			screen = 3;
		}
	}
	public void Fruits(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i= 0;
			catName = "Fruits";
			buttonClicked = true;
			playFirst = true;
			screen = 3;
		}
	}
	
	public void Numbers1(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i=0;
			catName = "Numbers";
			click = true;
			buttonClicked = true;
			screen = 5;
		}
	}
	
	public void Fruits1(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i=0;
			catName = "Fruits";
			click = true;
			buttonClicked = true;
			screen = 5;
		}
	}
	
	public void Family1(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i=0;
			catName = "Family";
			click = true;
			buttonClicked = true;
			screen = 5;
		}
	}
	
	public void WordLaunch(){
		if(count > 0){
			click = true;
			wordLaunchGame = true;
			setUpWordLaunchGame = true;
		}
		
	}
	
	public void MatchGame(){
		if(count > 0){
			click = true;
			matchGame = true;
			setUpMatchGame = true;
		}
		
	}
	
	public void ShootAndStrike(){
		if(count > 0){
			click = true;
			shootAndStrike = true;
			startShootAndStrike = true;
			createTarget = true;
			hitTarget = false;
			createBall = true;
			setupShootAndStrike();
		}
		
	}
	public void Back1(){
		if(count > 0){
			if(screen == 4){
				screen = 1;
			}
			if(screen == 5 && !matchGame && !wordLaunchGame && !shootAndStrike){
				screen = 4;
			}
			if(matchGame){
				matchGame = false;
			}
			if(wordLaunchGame){
				wordLaunchGame = false;
			}
			if(shootAndStrike){
				shootAndStrike = false;
			}
			score = 0;
			//reinitializes gameobjects to 0
			while(gameObjects.size() > 0){
			for(int i  = 0 ; i < gameObjects.size();i++){
				gameObjects.remove(i);
				System.out.println("Removing all objects");
			}
			}
			System.out.println(randomItems.size());
			while(randomItems.size() > 0){
				for(int i  = 0 ; i < randomItems.size();i++){
					randomItems.remove(i);
					System.out.println("Removing all objects");
				}
			}
			System.out.println(randomItems.size());
			
			System.out.println(copyRandomItems.size());
			while(copyRandomItems.size() > 0){
				for(int i  = 0 ; i < copyRandomItems.size();i++){
					copyRandomItems.remove(i);
					System.out.println("Removing all objects");
				}
			}
			System.out.println(copyRandomItems.size());
		}
	}
	
	public void Back(){
		System.out.println("Button Called");
	}
	
	//chooses ten random items from a category and adds them to the array list Random items 
	public void chooseRandomItems(ArrayList<CurrentCategory> arrayList){
		int size = arrayList.size();
		//while the randomItems arraylist is not equal to ten loop through
		for(int i = 0 ; i < 10 ; i++){
			if(randomItems.size() < 10){
				//create random i on each loop
				int j = (int) random(0,size-1);
				
				//select item from arrayList
				randomItems.add(arrayList.get(j));
				arrayList.remove(j);
				size = arrayList.size();
				println(randomItems.size());
			}
			
			else{
				return;
			}
		}
		
		
	}

}
/*______________Picture and words Matching Game Notes____________________
 * User selects games and is presented with categories
 * Loading in the list into the picture match game
 * we need 10 clickable images and ten clickable label panels to match
 * Each label must have a matching image on the board
 * Both images and labels must be placed randomly into a matrix like structure
 * user must then select a picture and a matching label.
 * if user selects 2 pictures or 2 labels they are shown an error handling exception
 * if the user selects a wrong option they are shown an error handling exception.
 * if the user gets a right combo the two selected objects are removed from the matrix structure.
 * when there are no objects left the user wins.
 * */
 