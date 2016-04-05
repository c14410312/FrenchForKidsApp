import processing.core.*;

public class Tile extends GameObject {

	float x, y, w;
	PApplet parent;
	
	Tile(PApplet p, float x, float y){
		parent = p;
		this.x = x;
		this.y = y;
		this.w = 80;
	}
	
	public void render(){
		parent.fill(0);
		parent.rect(x,y,w,w);
		System.out.println("Working");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
