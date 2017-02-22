import java.util.*;

public class Animon {
	private String name;
	private String[] type = new String[2];
	private List<Move> moves;
	private int lvl;
	private int exp;
	private double baseHP;
	private double maxHP;
	private double currHP;
	private int baseAtk;
	private int atk;
	private int baseDef;
	private int def;
	private boolean evolved;

	public Animon (String name, int type1, int type2){
		this.name = name;
		moves = new ArrayList<Move>();
		
		switch(type1){
			case 1:
				this.type[0] = "grass";
				this.baseHP = 45;
				this.baseAtk = 49;
				this.baseDef = 49;
				this.moves.add(new Move("Tackle", 50, 0));
				this.moves.add(new Move("Vine Whip", 45, 1));
				this.moves.add(new Move("Take Down", 90, 0));
				break;
			case 2:
				this.type[0] = "poison";
				this.baseHP = 35;
				this.baseAtk = 60;
				this.baseDef = 44;
				this.moves.add(new Move("Sting", 15, 2));
				this.moves.add(new Move("Wrap", 15, 2));
				this.moves.add(new Move("Bite", 60, 0));
				break;
			case 3:
				this.type[0] = "fire";
				this.baseHP = 39;
				this.baseAtk = 52;
				this.baseDef = 43;
				this.moves.add(new Move("Scratch", 40, 0));
				this.moves.add(new Move("Ember", 40, 3));
				this.moves.add(new Move("Fire Fang", 65, 3));
				break;
			case 4:
				this.type[0] = "water";
				this.baseHP = 44;
				this.baseAtk = 48;
				this.baseDef = 65;
				this.moves.add(new Move("Tackle", 50, 0));
				this.moves.add(new Move("Water Gun", 40, 4));
				this.moves.add(new Move("Bubble", 40, 4));
				break;
		}
		
		switch(type2){
			case 1: this.type[1] = "grass";
			        break;
			case 2: this.type[1] = "poison";
			        break;
			case 3: this.type[1] = "fire";
			        break;
			case 4: this.type[1] = "water";
			        break;
			default: this.type[1] = "";
		}
		
		lvl = 1;
		exp = 1;
		computeStats();
		evolved = false;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAnimonDetails(){
		return "<html>" + name +
		       "<br/>lvl    : " + getLvl() +
			   "<br/>Type(s): " + type[0] + " / " + type[1] +
			   "<br/>HP     : " + currHP + " / " + maxHP + "<br/><br/></html>";
	}
	
	
	public int getLvl(){
		computeLvl();
		return lvl;
	}
	
	public int getExp(){
		return exp;
	}
	
	public double getMaxHP(){
		return maxHP;
	}
	
	public double getCurrHP(){
		return currHP;
	}
	
	public void healHP(){
		currHP = maxHP;
	}
	
	public int getDefense(){
		return def;
	}
	
	public String getType1(){
		return type[0];
	}
	
	public String getType2(){
		return type[1];
	}
	
	public void computeLvl(){
		lvl = (int)Math.cbrt(exp);
	}
	
	public void computeStats(){
		maxHP += (2 * baseHP + 100) * (lvl / 100) + 10;
		currHP = maxHP;
		atk += (2 * baseAtk * (lvl / 100)) + 5;
		def += (2 * baseDef * (lvl / 100)) + 5;
	}
	
	public boolean isEvolved(){
		return evolved;
	}
	
	public void evolve(){
		int newType;
		
		do{
			newType = randInt(1, 4);
			
			switch(newType){
				case 1: type[1] = "grass";
						break;
				case 2: type[1] = "poison";
						break;
				case 3: type[1] = "fire";
						break;
				case 4: type[1] = "water";
						break;
			}
		}while(type[1].equals(type[0]));
		
		switch(newType){
			case 1:
				this.moves.add(new Move("Tackle", 50, 0));
				this.moves.add(new Move("Vine Whip", 45, 1));
				this.moves.add(new Move("Take Down", 90, 0));
				break;
			case 2:
				this.moves.add(new Move("Sting", 15, 2));
				this.moves.add(new Move("Wrap", 15, 2));
				this.moves.add(new Move("Bite", 60, 0));
				break;
			case 3:
				this.moves.add(new Move("Scratch", 40, 0));
				this.moves.add(new Move("Ember", 40, 3));
				this.moves.add(new Move("Fire Fang", 65, 3));
				break;
			case 4:
				this.moves.add(new Move("Tackle", 50, 0));
				this.moves.add(new Move("Water Gun", 40, 4));
				this.moves.add(new Move("Bubble", 40, 4));
				break;
		}
	}	
	
	public boolean earnExp(Animon opponent){
		boolean lvlUp = false;
		int currLvl = lvl;
		
		exp += 30 * opponent.getLvl();
		computeLvl();
		
		if(lvl > currLvl){
			computeStats();
			lvlUp = true;
			if(lvl >= 10)
				if(!evolved)
					evolved = true;
		}
		
		return lvlUp;
	}		
	
	public void setDamage(double damage){
		currHP -= damage;	
		currHP = Math.round(currHP * 100.00) / 100.00;
	}
	
	public String getMove(int i){
		return moves.get(i).getName();
	}
	
	public String getMoveDetails(int i){
		return "<html>" + moves.get(i).getName() +
		       "<br/>Type : " + moves.get(i).getType() +
			   "<br/>Power: " + moves.get(i).getPower() + "<br/><br/></html>";
	}
	
	public int getMoveCnt(){
		return moves.size();
	}
	
	public int randInt(int min, int max){

		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}
	
	public double computeMult(String moveType, String targetType){
		double mult = 1;
		
		if(moveType == "grass"){
			if(targetType.equalsIgnoreCase("grass"))
				mult = 0.5;
			else if(targetType.equalsIgnoreCase("fire"))
				mult = 0.5;
			else if(targetType.equalsIgnoreCase("water"))
				mult = 2;
			else if(targetType.equalsIgnoreCase("poison"))
				mult = 0.5;
		}
		else if(moveType == "fire"){
			if(targetType.equalsIgnoreCase("grass"))
				mult = 2;
			else if(targetType.equalsIgnoreCase("fire"))
				mult = 0.5;
			else if(targetType.equalsIgnoreCase("water"))
				mult = 0.5;
			else if(targetType.equalsIgnoreCase("poison"))
				mult = 1;
		}
		else if(moveType == "water"){
			if(targetType.equalsIgnoreCase("grass"))
				mult = 0.5;
			else if(targetType.equalsIgnoreCase("fire"))
				mult = 2;
			else if(targetType.equalsIgnoreCase("water"))
				mult = 0.5;
			else if(targetType.equalsIgnoreCase("poison"))
				mult = 1;
		}
		else if(moveType == "poison"){
			if(targetType.equalsIgnoreCase("grass"))
				mult = 2;
			else if(targetType.equalsIgnoreCase("fire"))
				mult = 1;
			else if(targetType.equalsIgnoreCase("water"))
				mult = 1;
			else if(targetType.equalsIgnoreCase("poison"))
				mult = 0.5;
		}
		
		return mult;
	}	
		
	public double attack(int move, Animon target){
		/* Damage_on_Animon_y = (A * B / 50 / D + 2) * M * R / 255
		Where :
		A = Attack power of Animon_x
		B = Base Power of the Move of Animon_x
		D = baseDefense Power of Animon_y
		M = Multiplier of the Damage as show in Table 1
		R = any random number from 217 to 255 */
		
		double mult1, mult2, mult, damage;
		
		mult1 = computeMult(moves.get(move - 1).getType(), target.getType1()); 
		mult2 = computeMult(moves.get(move - 1).getType(), target.getType2()); 
		mult = mult1 * mult2;
		
		damage = ((0.4 * lvl + 2) * atk * moves.get(move - 1).getPower() / 50 / target.getDefense() + 2) * mult * randInt(217, 255) / 255;

		return damage;
	}
}