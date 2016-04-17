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
	boolean click = false;
	boolean buttonClicked = false;
	//used to navigate through selected category
	int i = 0;
	int k = 0;
	String catName;
	//used when looping through the selected category
	int maxSize = 0;
    int count = 0;
	int selectedCount = 0;
	boolean matchGame = false;
	boolean shootGame = false;
	public int countSelected = 0;

	
	ControlP5 nav;
	ControlP5 cat;
	ControlP5 gamesCat;
	ControlP5 menu;
	ControlP5 mode;
	ControlP5 start;
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
	    screen = 5;
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
	     .setSize(150,50)
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
		
		//Category Buttons (Games)
		gamesCat = new ControlP5(this);
		gamesCat.addButton("Numbers1")
	     .setValue(1)
	     .setPosition(150,150)
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
		}
		//category
		if(screen == 2){
			categories();
			//visible buttons
			cat.setVisible(true);
			menu.setVisible(true);
			//invisible buttons
			nav.setVisible(false);
			mode.setVisible(false);
			gamesCat.setVisible(false);
			
		}
		//words
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
			
		}
		//Matching picture word game
		if(screen == 4){
			nav.setVisible(false);
			mode.setVisible(false);
			cat.setVisible(false);
			menu.setVisible(false);
			start.setVisible(false);
			gamesCat.setVisible(true);
			
			
			
			//only create one game when button clicked. add a function within the button so this occurs
			//load the chosen category 
			
				if(buttonClicked)
				{
					
					//need to change the parameter
					loadCurrentCategory(catName);
					//Pass the arraylist to the function
					chooseRandomItems(CurCatItems);
					//sets up the game
					setUpMatchGame(catName);
					
					
					//make Matchgame Start
					matchGame = !matchGame;
					buttonClicked = false;
				}
			
			if(matchGame){
				
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
		
		//Correct picture shooting game 
		if(screen == 5){
			
			nav.setVisible(false);
			mode.setVisible(false);
			cat.setVisible(false);
			menu.setVisible(false);
			start.setVisible(false);
			gamesCat.setVisible(true);
			//set type to 0 to create an image in tile class
			int t = 0;
			String cat = "Numbers";
			
			background(255);
			// 1. Set up the game
			if(buttonClicked && click == true){
				
				//need to change the parameter
				loadCurrentCategory(catName);
				//Pass the arraylist to the function load up random items from category
				chooseRandomItems(CurCatItems);
				shootGame = true;
				click = false;
				
			}
			
			//if Picture Motion game selected
			if(shootGame){
				gamesCat.setVisible(false);
				
				for(int i = 0; i < gameObjects.size(); i++){
					GameObject go = gameObjects.get(i);
					if(go instanceof MotionTile){
						go.render();
						go.update();
					}
					
				}
				
				if(frameCount % 60 == 0){
					if(k < randomItems.size()){
						float x =random(border, width-border);
						float y = height ;
						MotionTile mTile = new MotionTile(this,x,y,randomItems.get(k).eng, t, cat, randomItems.get(k).fr);
						gameObjects.add(mTile);
						System.out.println("Tile created");
						k++;
					}
				}
			}
			
			
			// 2. dynamically create objects
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
		
		System.out.println(selectedCount);
		//if the two selected items are equal then they are removed from the board
		if(var1 == var2 && var1 != null && var2 != null ){
			System.out.println("Well done");
			gameObjects.remove(var1Pos);
			gameObjects.remove(var2Pos);
		}
	}
	
	public void startScreen(){
		
		
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
		        	System.out.println("loading new" + cat);
	    		}
	    		//System.out.println(CurCatItems.size());
	    	}
	    	else{
	    		for(int i = 0 ; i < lines.length; i++){
	    			//reads in each line from the selected text file  
	    			CurrentCategory curCatItem = new CurrentCategory(lines[i]);
		    		CurCatItems.set(i,curCatItem);
		    		maxSize = i;
		    		System.out.println("replacing with" + cat);
	    		}
	    		System.out.println(CurCatItems.size());
	    		
	    	}
		        
		  } 

	 public void navigateCat(String catName){
		
	    
		String cat = catName;
		//sets navigation buttons to visible
		nav.setVisible(true);
	    
		//handles if i goes beyond the array
	    if(i == maxSize + 1){
	    	i = 0;
	    }
	    if(i < 0 ){
	    	i = maxSize;
	    }
	        
	    //gets the matching Text for value in x
	    String x = CurCatItems.get(i).eng;
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
			screen = 3;
		}
	}
	public void Family(){
		if(count > 0){
			//ensure to reinitialize i to ensure it ends up at the start of the list
			i= 0;
			catName = "Family";
			buttonClicked = true;
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
 