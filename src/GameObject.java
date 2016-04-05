

abstract public class GameObject{

	public GameObject(){
     this(50, 50, 50);
    }
	
	public GameObject(float x, float y, float w){
		
	}
	
	abstract public void update();
	abstract public void render();

}
