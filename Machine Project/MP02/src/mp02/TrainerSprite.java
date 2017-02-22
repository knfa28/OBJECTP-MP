package mp02;

public class TrainerSprite extends MovableWorldElement{
	private Trainer model;
	
	public TrainerSprite(int x, int y, Trainer model){
		super(x, y);
		this.model = model;
	}
	
	public Trainer getModel(){
		return model;
	}
}