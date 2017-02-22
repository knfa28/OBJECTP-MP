import java.util.*;

public class Trainer{
	private String name;
	private List<Animon> party;
	private Animon activeAnimon;
	private final int maxAnimon;
	
	public Trainer(String name, boolean isAI){
		if(!isAI){
			this.name = name;
			party = new ArrayList<Animon>();
		}
		else{
			this.name = name;
			party = new ArrayList<Animon>();
			party.add(new Animon("Venusaur", 1, 0));
			party.add(new Animon("Arbok", 2, 0));
			party.add(new Animon("Charizard", 3, 0));
			party.add(new Animon("Blastoise", 4, 0));	
		}
		
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
	
	public Animon getAnimon(int i){
		return party.get(i);
	}
	
	public Animon getAvailable(int i){
		if(party.get(i).getCurrHP() > 0)
			return party.get(i);
			
		return null;
	}
	
	public Animon getFainted(int i){
		if(party.get(i).getCurrHP() <= 0)
			return party.get(i);
			
		return null;
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
	
	public void switchAnimon(int i){
		if(party.get(i).getCurrHP() > 0)
			activeAnimon = party.get(i);
	}
	
	public Animon getActive(){
		return activeAnimon;
	}
	
	public void healAll(){
		for(int i = 0; i < party.size(); i++)
			party.get(i).healHP();
	}
}