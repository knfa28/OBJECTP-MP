package mp02;


public class AnimonSprite extends WorldElement{
	private Animon model;
	
	public AnimonSprite(int x, int y, Animon model){
		super(x, y);
		this.model = model;
	}
	
	public Animon getModel(){
		return model;
	}
}