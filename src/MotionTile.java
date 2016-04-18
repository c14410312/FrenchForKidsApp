import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import processing.core.PApplet;
import processing.core.PImage;

public class MotionTile extends GameObject implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	float x, y, w;
	int color = 0;
	int tint = 0;
	String id;
	public boolean selected = false;
	PApplet parent;
    int type;
    PImage img;
	String cat;
	String fr;
	float gravity = 0.08f;

	MotionTile(PApplet p, float x, float y, String id, int type, String cat, String fr){
		parent = p;
		this.x = x;
		this.y = y;
		this.w = 80;
		this.id = id;
		this.type = type;
		this.cat = cat;
		this.fr = fr;
		parent.addMouseListener(this);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//creates a gravitational effect on the objects
		speed -= gravity;
		y -= speed;
		
		
		if (y > parent.height + border * 2)
	    {
	      Application.gameObjects.remove(this);
	      System.out.println("Removing Tile");
	    }
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
			//gets the matching picture for value in x
		    img = parent.loadImage("Images/"+cat+"/"+id+".jpg");
		    //gets the matching audio for value in x
		    //parent.imageMode(PConstants.CENTER);
		    parent.image(img, x, y, w, w);
		    parent.noFill();
		    parent.noStroke();
			parent.rect(x,y,w,w);

	}
	
	//checks if the mouse is over the tile
	boolean overTile(float x, float y, float w){
		if(parent.mouseX >= x && parent.mouseX <= x + w
				&& parent.mouseY >= y && parent.mouseY <= y + w){
			return true;
		}
		else{
			return false;
		}

	}
	
	//when the mouse is clicked and the mouse is over the tile then the object is selected
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(overTile(x,y,w)){
			selected = !selected;
			System.out.println("tile id: " + id + "Type:" + type);
		}
		
	}

}
