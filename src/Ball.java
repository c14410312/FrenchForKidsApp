import processing.core.PApplet;

public class Ball extends GameObject {

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
	PApplet parent;
	
	Ball(PApplet p,float x, float y, float w){
		this.parent = p;
		this.x = x;
		this.y = y;
		this.ballWidth = w;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub

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

}
