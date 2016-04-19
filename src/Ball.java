
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
	static PApplet parent;
	
	Ball(){
		super(parent,250 * 0.5f, 500 * 0.1f, 50);
	}
	
	Ball(PApplet p,float x, float y, float w){
		super(parent,x, y, 50); 
		Ball.parent = p;
		this.ballWidth = w;
		parent.addMouseListener(this);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		pos.x = pos.x + deltaX;
		pos.y = pos.y + deltaY;
		
		if (pos.x > parent.width-ballWidth/2 || pos.x < ballWidth/2)
		  {
		    deltaX = -deltaX;
		    deltaX = (float) (deltaX * 0.6);
		    if (pos.x < ballWidth/2)
		    {
		    pos.x = ballWidth/2;
		  }
		    else
		    {pos.x = parent.width-ballWidth/2;
		  }
		  }
		  if (pos.y > parent.width-ballWidth/2)
		  {
		    deltaY = -deltaY;
		    deltaY = (float) (deltaY * 0.6);
		    if (pos.y < ballWidth/2)
		    {pos.y = ballWidth/2;
		  }
		    else
		    {pos.y = parent.width-ballWidth/2;
		  }
		  }
		  if(pos.y < ballWidth/2){
			  Application.gameObjects.remove(this);
			  System.out.println("Ball removed");
			  Application.createBall = true;
		  }
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
			
		  
		  parent.fill(0);
		  parent.stroke(0);
		  parent.ellipse (pos.x, pos.y, ballWidth, ballWidth);
		  parent.stroke (0);
		  parent.noFill();
		  parent.translate(pos.x,pos.y);
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
			parent.line(pos.x, pos.y, parent.mouseX, parent.mouseY);
		}
	}
	
	
	public void mousePressed(MouseEvent e){
		
	}
	
	public void mouseReleased(){
	
		System.out.println("Mouse released");
	  deltaX = (pos.x - parent.mouseX)/33;
	  deltaY = (pos.y - parent.mouseY)/33;
	  System.out.println(deltaX);
	}
	

}
