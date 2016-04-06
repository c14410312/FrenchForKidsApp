

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import processing.core.*;


public class Tile extends GameObject implements MouseListener {

	float x, y, w;
	int color = 0;
	String id;
	public boolean selected = false;
	PApplet parent;
	
	Tile(PApplet p, float x, float y, String id){
		parent = p;
		this.x = x;
		this.y = y;
		this.w = 80;
		this.id = id;
		parent.addMouseListener(this);
	}
	
	public void render(){
		if(overTile(x,y,w)){
			color = 122;
		}
		
		if(selected){
			color = 190;
		}
		else{
			color = 0;
		}
		parent.fill(color);
		parent.rect(x,y,w,w);
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
			System.out.println("tile id: " + id);
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
