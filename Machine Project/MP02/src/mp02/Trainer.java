package mp02;

import java.util.List;
import java.util.ArrayList;

public class Trainer{
	private String name;
	private List<Animon> party;
	private Animon activeAnimon = null;
	private final int maxAnimon;
	
	public Trainer(String name){
		this.name = name;
		party = new ArrayList<Animon>();
		maxAnimon = 4;
	}
	
	public String getName(){
            return name;
	}
	
	public boolean addAnimon(Animon newAnimon){
            if(party.size() <= maxAnimon){
                    party.add(newAnimon);
                    return true;
            }
            else return false;
	}
	
	public int countAnimon(){
            return party.size();
	}
	
        public Animon getAvailable(int i){
            if(party.get(i).getCurrHP() > 0)
                    return party.get(i);

            return null;
	}
        
	public boolean releaseAnimon(String animonName){
            for(int i = 0; i < party.size(); i++)
                    if(party.get(i).getName().equalsIgnoreCase(animonName)){
                            party.remove(i);
                            return true;
                    }

            return false;
        }
        
	public int countAvailable(){
		int ctr = 0;
		
		for(int i = 0; i < party.size(); i++)
			if(party.get(i).getCurrHP() > 0)
				ctr++;
				
		return ctr;
	}
	
	public int countFainted(){
            int ctr = 0;

            for(int i = 0; i < party.size(); i++)
                if(party.get(i).getCurrHP() <= 0)
                    ctr++;

            return ctr;
	}
	
	public void switchAnimon(int index){
            if(party.get(index).getCurrHP() > 0)
                activeAnimon = party.get(index);
	}
	
	public Animon getActive(){
            return activeAnimon;
	}
	
	public Animon getAnimon(int index){
            return party.get(index-1);
	}
}