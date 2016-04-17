import processing.core.PApplet;

public class PlayerGun extends GameObject {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//used to control gun
	char left;
	char right;
	char shoot;
	float posX;
	float posY;
	PApplet parent;
	float offset = 0;
	float easing = 0.05f;
	
	PlayerGun(PApplet p, char right, char left, char shoot, float startX, float startY){
		parent = p;
		this.left = left;
		this.right = right;
		this.shoot = shoot;
		this.posX = startX;
		this.posY = startY;
		health = 100;
		score = 0;
		
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//update player position on key press
		float dx = (parent.mouseX - (50/2)) - offset;
		offset += dx * easing; 
		System.out.println(parent.mouseX);

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		parent.fill(255);
		parent.rect(offset, posY, 50, 50);
	}
	
	

}
