import processing.core.PApplet;

abstract public class GameObject extends PApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int health = 0;
	public int score = 0;
	public float speed = 4.0f;
	int border = 50;
	
	boolean[] keys = new boolean[512];
	
	
	
	public String id;
	
	public GameObject(){
     this(null, 50, 50, null, 0, null);
    }
	
	public GameObject(PApplet p, float x, float y, String id, int type, String cat){
		this.id = id;
	}
	
	abstract public void update();
	abstract public void render();
	
	

}
