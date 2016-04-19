import processing.core.PApplet;

public class Target extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float x, y, w;
	PApplet parent;
	boolean left = false;
	
	Target(PApplet p, float x, float y, float w){
		this.parent = p;
		this.x = x;
		this.y= y;
		this.w = w;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(left){
			x += speed/1.5;
		}
		else{
			x -= speed/1.5;
		}
		if(x < 0){
			left = !left;
		}
		else if(x > parent.width){
			left = !left;
		}
		
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		parent.fill(255,255,0);
		parent.ellipse(x,y,w*3,w*3);
		parent.fill(0,0,255);
		parent.ellipse(x,y,w*2,w*2);
		parent.fill(255,0,0);
		parent.ellipse(x,y,w,w);
	}

}
