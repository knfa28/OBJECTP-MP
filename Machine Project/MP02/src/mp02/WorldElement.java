package mp02;


public abstract class WorldElement{
	protected int x;
	protected int y;
        private char orientation = 'd';
	
	public WorldElement(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
        
        public char getOrientation(){
            return this.orientation;
        }
        
        public void setOrientation(char c){
            this.orientation = c;
        }
}