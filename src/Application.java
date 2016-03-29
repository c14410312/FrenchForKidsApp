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
	ControlP5 nav;
	ControlP5 cat;
	PImage img;
	Minim minim;
	AudioPlayer track;
	

	//global arrayList to hold the loaded category
	ArrayList<CurrentCategory> CurCatItems = new ArrayList<CurrentCategory>();
	
	public void setup() {
	    size(500,500);
	    background(255);
	    screen = 2;
	    
		
	    //****************BUTTONS**********************
	    
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
		
		//menu
		if(screen == 0){
			startScreen();
			nav.setVisible(false);
			cat.setVisible(false);
		}
		//difficulty
		if(screen == 1){
			difficultyScreen();
			nav.setVisible(false);
			cat.setVisible(false);
		}
		//category
		if(screen == 2){
			categories();
			nav.setVisible(false);
			cat.setVisible(true);
		}
		//words/sentences
		if(screen == 3){
			cat.setVisible(false);
			if(buttonClicked){
				loadCurrentCategory(catName);
				navigateCat(catName);
			}
			
		}
		//activity content
		if(screen == 4){
			
		}
	}
	
	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "Application" });
	}
	
	public void startScreen(){
		//create instance of control p5
		/*p5 = new ControlP5(this);
		cat.addButton("Lets Start!")
	     .setValue(1)
	     .setColorActive(125)
	     .setColorBackground(0) 
	     .setPosition(150,150)
	     .setSize(200,50)
	     ;
		cat.addButton("About")
		 .setValue(2)
		 .setPosition(150,200)
		 .setSize(200,50)
		 ;
		cp5.addButton("Exit")
		 .setValue(3)
		 .setPosition(150,250)
		 .setSize(200,50)
		 ;*/
		
	}
	
	public void difficultyScreen(){
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
	 * cat = chosenCategory(e.g Numbers)(button.getLabel())
	 * load in the text file belonging to that category("Numbers.txt")
	 * as each number is loaded in from the text file within that loop we will import the image and sound associated with that number
	 * e.g first line from text file = 1
	 * load in 1.jpg, 1.mp3
	 * when the user clicks the arrow for next the loop continues to the next i. 
	 */
	 void loadCurrentCategory(String catName){
		    //cat will be the selected category by the user and will place the name into cat.
		    String cat = catName;
		    String[] lines = loadStrings("Text/"+cat+".csv");
		    maxSize = lines.length;
		    //for loop to load in each line from csv file
		    for (int i = 0 ; i < lines.length ; i ++){
		        //reads in each line from the selected text file  
		        CurrentCategory curCatItem = new CurrentCategory(lines[i]);
		        CurCatItems.add(curCatItem);
		        
		      //puts the current value of the list in x
		      
		      //System.out.println(x);
		      
		       
		    } 
		  }
	 public void navigateCat(String catName){
		 
		String cat = catName;
		//sets navigation buttons to visible
		nav.setVisible(true);
	    
		//handles if i goes beyond the array
	    if(i == maxSize){
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
	    
	    textAlign(CENTER,CENTER);
	    fill(0);
	    textSize(24);
	    text(CurCatItems.get(i).fr,width/2, height-border*2);
	 }
	 
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
	 
	 //****************BUTTON EVENTS*******************************
	 //events to deal with button clicks from the categories
	public void Numbers(){
		if(count > 0){
			catName = "Numbers";
			buttonClicked = true;
			screen = 3;
		}
		count = 1;
	}
	public void Alphabet(){
		if(count > 1){
			catName = "Alphabet";
			buttonClicked = true;
			screen = 3;
		}
		count = 2;
	}

}
