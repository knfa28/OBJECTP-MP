import java.util.List;
import java.util.ArrayList;

public class Trainer{
	private String name;
	private List<Animon> party;
	private Animon activeAnimon;
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
	
	public boolean releaseAnimon(int i){
		party.remove(i);
	}
	
	public String getAnimon(int i){
		return party.get(i).getName();
	}
	
	public String printAvailable(int i){
		if(party.get(i).getCurrHP() > 0)
			return party.get(i).getName();
			
		return "none";
	}
	
	public String printFainted(int i){
		if(party.get(i).getCurrHP() <= 0)
			return party.get(i).getName();
			
		return "none";
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
		if(party.get(index - 1).getCurrHP() > 0)
			activeAnimon = party.get(index - 1);
	}
	
	public Animon getActive(){
		return activeAnimon;
	}
}