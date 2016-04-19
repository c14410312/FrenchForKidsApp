
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
		
		if (x > parent.width-ballWidth/2 || x < ballWidth/2)
		  {
		    deltaX = -deltaX;
		    deltaX = (float) (deltaX * 0.6);
		    if (x < ballWidth/2)
		    {
		    x = ballWidth/2;
		  }
		    else
		    {x = parent.width-ballWidth/2;
		  }
		  }
		  if (y > parent.width-ballWidth/2 || y < ballWidth/2)
		  {
		    deltaY = -deltaY;
		    deltaY = (float) (deltaY * 0.6);
		    if (y < ballWidth/2)
		    {y = ballWidth/2;
		  }
		    else
		    {y = parent.width-ballWidth/2;
		  }
		  }
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
			
		  
		  parent.fill(0);
		  parent.stroke(0);
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
	
	
	public void mousePressed(MouseEvent e){
		
	}
	
	public void mouseReleased(){
	
		System.out.println("Mouse released");
	  deltaX = (x - parent.mouseX)/33;
	  deltaY = (y - parent.mouseY)/33;
	  System.out.println(deltaX);
	}
	

}
