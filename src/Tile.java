import processing.core.*;
import processing.event.MouseEvent;

public class Tile extends GameObject {

	float x, y, w;
	int color = 0;
	PApplet parent;
	
	Tile(PApplet p, float x, float y){
		parent = p;
		this.x = x;
		this.y = y;
		this.w = 80;
	}
	
	public void render(){
		if(overTile(x,y,w)){
			color = 122;
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
	
	//function to check if tile has been selected
	void mouseClicked(MouseEvent e){
		if(overTile(x, y, w)){
			System.out.println("Clicked");
		}
	}
}
