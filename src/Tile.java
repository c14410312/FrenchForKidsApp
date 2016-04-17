

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import processing.core.*;


public class Tile extends GameObject implements MouseListener {

	/**
	 * 
	 */
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
	
	Tile(PApplet p, float x, float y, String id, int type, String cat, String fr){
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
	
	public void render(){
		if(overTile(x,y,w)){
			color = 122;
		}
		
		if(selected){
			color = 190;
			tint = 40;
		}
		else{
			color = 0;
			tint = 255;
		}
		if(type == 0){
			//gets the matching picture for value in x
		    img = parent.loadImage("Images/"+cat+"/"+id+".jpg");
		    //gets the matching audio for value in x
		    //parent.imageMode(PConstants.CENTER);
		    parent.tint(255,tint);
		    parent.image(img, x, y, w, w);
		    parent.noFill();
		    parent.noStroke();
			parent.rect(x,y,w,w);
		}
		if(type == 1){
			parent.fill(color);
			parent.rect(x, y, w, w);
			parent.fill(255);
			parent.textSize(12);
			parent.textAlign(PConstants.CENTER);
			parent.text(fr,x+w/2,y+w/2);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	//function to check if mouse is over tile
	boolean overTile(float x, float y, float w){
		if(parent.mouseX >= x && parent.mouseX <= x + w
				&& parent.mouseY >= y && parent.mouseY <= y + w){
			return true;
		}
		else{
			return false;
		}

	}
	
	public void mousePressed(){
		System.out.println("Hello");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(overTile(x,y,w)){
			selected = !selected;
			System.out.println("tile id: " + id + "Type:" + type);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
