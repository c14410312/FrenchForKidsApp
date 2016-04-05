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
	boolean buttonClicked = false;
	//used to navigate through selected category
	int i = 0;
	String catName;
	//used when looping through the selected category
	int maxSize = 0;
	int count = 0;
	boolean matchGame;

	
	ControlP5 nav;
	ControlP5 cat;
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
	
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	
	public void setup() {
	    size(500,500);
	    background(255);
	    screen = 4;
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
	    		 
	    //Category Buttons
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
			
		}
		//Matching picture word game
		if(screen == 4){
			nav.setVisible(false);
			mode.setVisible(false);
			cat.setVisible(false);
			menu.setVisible(false);
			start.setVisible(false);
			
			float y = 0;
			float x = 70;
			
			//only create one game when button clicked. add a function within the button so this occurs
			//load the chosen category 
			if(keyPressed)
			{
				if(key == '1')
				{
					//need to change the parameter
					loadCurrentCategory("Numbers");
					//Pass the arraylist to the function
					chooseRandomItems(CurCatItems);
					for(int i = 0; i < randomItems.size(); i ++)
					{
						System.out.println(randomItems.get(i).eng);
					}
					for(int i = 0 ; i < 5; i++){
						
						y = 50 + (90 * i);
						
						for(int j = 0 ; j < 4; j++){
							Tile tile = new Tile(this,x,y);
							gameObjects.add(tile);
							x += 90;
						}
						//reinitialize x before next iteration
						x = 70;
						
						
					}
					
					//make Matchgame Start
					matchGame = !matchGame;
				}
			}
			
			if(!matchGame){
				
				
				for(int i = 0; i < gameObjects.size()-1; i++){
					GameObject go = gameObjects.get(i);
					go.render();
				}
			}
		}
	}
	
	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "Application" });
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
 