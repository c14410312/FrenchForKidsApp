import processing.core.*;

import java.util.ArrayList;

import controlP5.*;

import ddf.minim.*;

abstract public class GameObject extends PApplet {
	
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	   float w;
	   float halfW;
	   int dead;
	   
	   
	   GameObject()
	   {
	     
	   }
	   
	   GameObject(float x, float y, float w)
	   {
	     this.w = w;
	     this.halfW = w * 0.5f;
	   }
	   
	   abstract void update();

}
