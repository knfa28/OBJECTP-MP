/**
 *
 * Authors: Marc Dominic San Pedro, Kurt Neil Aquino
 */

import java.util.Random;
import java.lang.Math;

public class Animon {
	private String name;
	private List<Move> moves;
	private int nMoves;
	private int lvl;
	private int exp;
	private double baseHP;
	private double maxHP;
	private double currHP;
	private int baseAtk;
	private int atk;
	private int baseDef;
	private int def;
	private String[] type = new String[2];
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
		
		this.nMoves = 3;
		lvl = 0;
		lvlUp();
		evolved = false;
	}
	
	public int getLvl(){
		return (int)Math.cbrt(exp);
	}
	
	public int getExp(){
		return this.exp;
	}
	
	public double getMaxHP(){
		return this.maxHP;
	}
	
	public double getCurrHP(){
		return this.currHP;
	}
	
	public int getDefense(){
		return this.def;
	}
	
	public String getType1(){
		return this.type[0];
	}
	
	public String getType2(){
		return this.type[1];
	}
	
	public void lvlUp(){
		lvl++;
		maxHP += (2 * baseHP + 100) * lvl / 100 + 10;
		atk += (2 * baseAtk * lvl / 100) + 5;
		def += (2 * baseDef * lvl / 100) + 5;
	}
	
	public int earnExp(Animon opponent){
		int expEarned = 30 * opponent.getLvl();
		currExp += expEarned;
		
		if(currExp >= maxExp){
			currExp += (currExp%maxExp);
			lvlUp();
		}
		
		return expEarned;
	}
	
	public void setDamage(double damage){
		this.maxHP -= damage;	
		this.maxHP = Math.round(this.maxHP * 100.00) / 100.00;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getMove(int i){
		return this.moves[i].getName();
	}
	
	public int randInt(int min, int max){

		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}
	
	public double computeMult(String moveType, String targetType){
		double mult = 1;
		
		if(moveType == "grass"){
			if(targetType == "grass")
				mult = 0.5;
			else if(targetType == "fire")
				mult = 0.5;
			else if(targetType == "water")
				mult = 2;
			else if(targetType == "poison")
				mult = 0.5;
		}
		else if(moveType == "fire"){
			if(targetType == "grass")
				mult = 2;
			else if(targetType == "fire")
				mult = 0.5;
			else if(targetType == "water")
				mult = 0.5;
			else if(targetType == "poison")
				mult = 1;
		}
		else if(moveType == "water"){
			if(targetType == "grass")
				mult = 0.5;
			else if(targetType == "fire")
				mult = 2;
			else if(targetType == "water")
				mult = 0.5;
			else if(targetType == "poison")
				mult = 1;
		}
		else if(moveType == "poison"){
			if(targetType == "grass")
				mult = 2;
			else if(targetType == "fire")
				mult = 1;
			else if(targetType == "water")
				mult = 1;
			else if(targetType == "poison")
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
		
		mult1 = computeMult(moves[move - 1].getType(), target.getType1()); 
		mult2 = computeMult(moves[move - 1].getType(), target.getType2()); 
		mult = mult1 * mult2;
		
		damage = ((0.4 * lvl + 2) * atk * moves[move-1].getPower() / 50 / target.getDefense() + 2) * mult * randInt(217, 255) / 255;

		return damage;
	}
}
