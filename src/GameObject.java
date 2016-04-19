import processing.core.PApplet;
import processing.core.PVector;

abstract public class GameObject extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int health = 0;
	public int score = 0;
	public float speed = 8.0f;
	public float halfW;
	int border = 50;
	PVector pos;
	
	boolean[] keys = new boolean[512];
	
	
	
	public String id;
	
	public GameObject(){
     this(50, 50, 50);
    }
	
	public GameObject(float x, float y,float w){
		pos = new PVector(x, y);
		this.halfW = w * 0.5f;
	}
	
	abstract public void update();
	abstract public void render();
	
	

}
