
import java.awt.event.MouseListener;

import org.w3c.dom.events.MouseEvent;

import processing.core.PApplet;

public class Ball extends GameObject implements MouseListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float x;
	float y;
	float deltaX;
	float deltaY;
	float ballWidth;
	float t = 20;
	float arcStart = -PI/2;
	float arcEnd = PI/2;
	boolean isPressed = false;
	PApplet parent;
	
	Ball(PApplet p,float x, float y, float w){
		this.parent = p;
		this.x = x;
		this.y = y;
		this.ballWidth = w;
		parent.addMouseListener(this);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		x = x + deltaX;
		y = y + deltaY;
		System.out.println("Updating");
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
			
		  parent.noStroke();
		  parent.fill(255);
		  parent.ellipse (x-5, y-5, ballWidth, ballWidth);
		  parent.fill(125);
		  parent.stroke(125);
		  parent.ellipse (x, y, ballWidth, ballWidth);
		  parent.stroke (0);
		  parent.noFill();
		  parent.translate(x,y);
		  if (deltaY < 0 && deltaX > 0)
		  {parent.rotate(PI/2 - tan(deltaY/deltaX));
		  }
		  if (deltaY < 0 && deltaX < 0)
		  {parent.rotate(PI - tan(deltaY/deltaX));
		  }
		  if (deltaY > 0 && deltaX > 0)
		  {parent.rotate(-PI/2 + tan(deltaY/deltaX));
		  }
		  if (deltaY > 0 && deltaX < 0)
		  {parent.rotate(-PI + tan(deltaY/deltaX));
		  }

	}

	void aim(){
		if(parent.mousePressed == true){
			parent.stroke(0);
			parent.line(x, y, parent.mouseX, parent.mouseY);
		}
	}
	
	public void launchBall(){
	  x = x + deltaX;
	  y = y + deltaY;
	}
	
	public void mousePressed(MouseEvent e){
		
	}
	
	public void mouseReleased(){
	
		System.out.println("Mouse released");
	  deltaX = (x - parent.mouseX)/33;
	  deltaY = (y - parent.mouseY)/33;
	  System.out.println(deltaX);
	}
	

}
