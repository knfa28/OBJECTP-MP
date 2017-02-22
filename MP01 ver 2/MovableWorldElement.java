public abstract class MovableWorldElement extends WorldElement implements Movable{
	public MovableWorldElement(int x, int y){
		super(x, y);
	}
	
	public void moveLeft(){
		if(x - 1 >= 0)
			x--;
	}
	
	public void moveRight(){
		if(x + 1 <= 40)
			x++;
	}
	
	public void moveDown(){
		if(y + 1 <= 15)
			y++;
	}
	
	public void moveUp(){
		if(y - 1 >= 0)
			y--;
	}
}