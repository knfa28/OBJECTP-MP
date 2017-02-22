package mp02;

import javax.swing.JOptionPane;

public class AnimonWorld{
        private BattleView wBattle;
	private WorldElement[][] worldElements;
	private final int MAX_WIDTH;
	private final int MAX_HEIGHT;
	
	public AnimonWorld(int width, int height){
		worldElements = new WorldElement[width][height];
		MAX_WIDTH = width;
		MAX_HEIGHT = height;
	}
	
	public boolean addElement(WorldElement newElem){
		if(worldElements[newElem.getX()][newElem.getY()]==null){
                   worldElements[newElem.getX()][newElem.getY()]=newElem;
                   return true;
                }
		return false;
	}
        
        public boolean removeElement(WorldElement removeElem){
            if(worldElements[removeElem.getX()][removeElem.getY()]==removeElem){
                worldElements[removeElem.getX()][removeElem.getY()]=null;
                return true;
            }
            return false;
        }
	
	public void removeElementAt(int x, int y){
               worldElements[x][y]=null;
	}
	/*
	public boolean isElement(WorldElement check){
		for(int i = 0; i < worldElements.size(); i++){
			if(worldElements.get(i).getX() == check.getX() && worldElements.get(i).getX() == check.getY()){
				return true;
			}
		}
		return false;
	}*/
	
	public WorldElement getElementAt(int x, int y){
            return worldElements[x][y];
	}
		
	
	public boolean moveElement(MovableWorldElement moveElem, char direction){
            switch(direction){
                case 'u':
                    if(moveElem.getOrientation()=='u'){
                        if(getElementAt(moveElem.getX(), moveElem.getY()-1)!=null){
                            TrainerSprite t = (TrainerSprite)getElementAt(moveElem.getX(), moveElem.getY()-1);
                            Sound.route1.stop();
                            Sound.battleTheme.loop();
                            JOptionPane.showMessageDialog(null, "Trainer " + t.getModel().getName() + " wants to battle!");
                            wBattle = new BattleView(new Battle(((TrainerSprite)moveElem).getModel(), t.getModel()));
                            wBattle.setVisible(true);
                           
                            removeElementAt(moveElem.getX(), moveElem.getY()-1); 
                        } 
                        
                        moveElem.moveUp();
                        addElement(moveElem);
                        removeElementAt(moveElem.getX(), moveElem.getY()+1);
                    }
                    else{
                        moveElem.setOrientation('u');
                        return false;
                    }
                    break;
                case 'd': 
                    if(moveElem.getOrientation()=='d'){
                        if(getElementAt(moveElem.getX(), moveElem.getY()+1)!=null){
                            TrainerSprite t = (TrainerSprite)getElementAt(moveElem.getX(), moveElem.getY()+1);
                            Sound.route1.stop();
                            Sound.battleTheme.loop();
                            JOptionPane.showMessageDialog(null, "Trainer " + t.getModel().getName() + " wants to battle!");
                            wBattle = new BattleView(new Battle(((TrainerSprite)moveElem).getModel(), t.getModel()));
                            wBattle.setVisible(true);
                            
                            removeElementAt(moveElem.getX(), moveElem.getY()+1); 
                        }
                        /* this should actually initiate battle, but while the battle class is 
                        being made, we simply remove the opposing element
                        */
                        moveElem.moveDown();
                        addElement(moveElem);
                        removeElementAt(moveElem.getX(), moveElem.getY()-1);
                    }
                    else{
                        moveElem.setOrientation('d');
                        return false;
                    }
                    break;
                case 'l': 
                    
                    if(moveElem.getOrientation()=='l'){
                        if(getElementAt(moveElem.getX()-1, moveElem.getY())!=null){
                            TrainerSprite t = (TrainerSprite)getElementAt(moveElem.getX()-1, moveElem.getY());
                            Sound.route1.stop();
                            Sound.battleTheme.loop();
                            JOptionPane.showMessageDialog(null, "Trainer " + t.getModel().getName() + " wants to battle!");
                            wBattle = new BattleView(new Battle(((TrainerSprite)moveElem).getModel(), t.getModel()));
                            wBattle.setVisible(true);
                            
                            removeElementAt(moveElem.getX()-1, moveElem.getY());
                        } 
                        /* this should actually initiate battle, but while the battle class is 
                        being made, we simply remove the opposing element
                        */
                        moveElem.moveLeft();
                        addElement(moveElem);
                        removeElementAt(moveElem.getX()+1, moveElem.getY());
                    }
                    else{
                        moveElem.setOrientation('l');
                        return false;
                    }
                    break;
                case 'r':
                    if(moveElem.getOrientation()=='r'){
                        if(getElementAt(moveElem.getX()+1, moveElem.getY())!=null){
                            TrainerSprite t = (TrainerSprite)getElementAt(moveElem.getX()+1, moveElem.getY());
                            Sound.route1.stop();
                            Sound.battleTheme.loop();
                            JOptionPane.showMessageDialog(null, "Trainer " + t.getModel().getName() + " wants to battle!");
                            wBattle = new BattleView(new Battle(((TrainerSprite)moveElem).getModel(), t.getModel()));
                            wBattle.setVisible(true);
                            
                            removeElementAt(moveElem.getX()+1, moveElem.getY());
                        }
                        /* this should actually initiate battle, but while the battle class is 
                        being made, we simply remove the opposing element
                        */
                        moveElem.moveRight();
                        addElement(moveElem);
                        removeElementAt(moveElem.getX()-1, moveElem.getY());
                    }
                    else{
                        moveElem.setOrientation('r');
                        return false;
                    }
                    break;
            }
            return true;
        }
}
		