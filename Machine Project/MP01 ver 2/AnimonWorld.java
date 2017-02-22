import java.util.List;
import java.util.ArrayList;

public class AnimonWorld{
	private List<WorldElement> worldElements;
	private final int MAX_WIDTH;
	private final int MAX_HEIGHT;
	
	public AnimonWorld(int width, int height){
		worldElements = new ArrayList<WorldElement>();
		MAX_WIDTH = width;
		MAX_HEIGHT = height;
	}
	
	public boolean addElement(WorldElement newElem){
		for(WorldElement w: worldElements){
			if(w.getX() != newElem.getX() && w.getY() != newElem.getY()){
				worldElements.add(newElem);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeElement(WorldElement removeElem){
		for(int i = 0; i < worldElements.size(); i++){
			if(worldElements.get(i).getX() == removeElem.getX() && worldElements.get(i).getX() == removeElem.getY()){
				worldElements.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean isElement(WorldElement check){
		for(int i = 0; i < worldElements.size(); i++){
			if(worldElements.get(i).getX() == check.getX() && worldElements.get(i).getX() == check.getY()){
				return true;
			}
		}
		return false;
	}
	
	public WorldElement getElementAt(int x, int y){
		for(WorldElement w: worldElements){
			if(w.getX() == x && w.getY() == y)
				return w;
		}
		return null;
	}
		
	
	public void moveElement(WorldElement moveElem, char direction){
		//implement code for moving an element in the map using w, a, s, d
	}
	
	public void displayMap(){
		//implement code for displaying the map with "#" as the base, "!" as the wild Animon, and "@" as the trainer
	}
}
		