import processing.core.PApplet;

public class Target extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float x, y, w;
	static PApplet parent;
	boolean left = false;
	Target(){
		super(parent,500/4, 500/4, 50);
	}
	
	Target(PApplet p, float x, float y, float w){
		super(parent,x, y, w); 
		Target.parent = p;
		this.w = w;
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		if(left){
			pos.x += speed/1.5;
		}
		else{
			pos.x -= speed/1.5;
		}
		if(pos.x < 0){
			left = !left;
		}
		else if(pos.x > parent.width){
			left = !left;
		}
		
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		parent.fill(255,255,0);
		parent.ellipse(pos.x,pos.y,w*3,w*3);
		parent.fill(0,0,255);
		parent.ellipse(pos.x,pos.y,w*2,w*2);
		parent.fill(255,0,0);
		parent.ellipse(pos.x,pos.y,w,w);
	}

}
