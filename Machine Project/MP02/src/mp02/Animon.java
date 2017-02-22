package mp02;
/**
 *
 * Authors: Marc Dominic San Pedro, Kurt Neil Aquino
 */

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Animon {
	private String name;
	private Move[] moves = new Move[6];
	private int nMoves;
	private double maxHP;
	private double currHP;
        private double bHP;
	private int bAtk;
        private int atk;
	private int bDef;
        private int def;
        private int level=1;
        private int exp=1;
	private String[] type = new String[2];
        private ImageIcon sprite;
        private Sound cry;
        private boolean evolved = false;

	public Animon (int num){
            type[1] = null;
            switch(num){
                case 1:
                    this.name = "Exeggcute";
                    this.type[0] = "grass";
                    this.bHP = 45;
                    this.bAtk = 49;
                    this.bDef = 49;
                    this.moves[0] = new Move("Tackle", 50, 0);
                    this.moves[1] = new Move("Vine Whip", 45, 1);
                    this.moves[2] = new Move("Take Down", 90, 0);
                    this.sprite = new ImageIcon("Images/AnimonSprites/Exeggcute.png");
                    this.cry = new Sound("AnimonCries/ExeggcuteCry.wav");
                    break;
                case 2:
                    this.name = "Koffing";
                    this.type[0] = "poison";
                    this.bHP = 35;
                    this.bAtk = 60;
                    this.bDef = 44;
                    this.moves[0] = new Move("Sting", 15, 2);
                    this.moves[1] = new Move("Wrap", 15, 2);
                    this.moves[2] = new Move("Bite", 60, 0);
                    this.sprite = new ImageIcon("Images/AnimonSprites/Koffing.png");
                    this.cry = new Sound("AnimonCries/KoffingCry.wav");
                    break;
                case 3:
                    this.name = "Growlithe";
                    this.type[0] = "fire";
                    this.bHP = 39;
                    this.bAtk = 52;
                    this.bDef = 43;
                    this.moves[0] = new Move("Scratch", 40, 0);
                    this.moves[1] = new Move("Ember", 40, 3);
                    this.moves[2] = new Move("Fire Fang", 65, 3);
                    this.sprite = new ImageIcon("Images/AnimonSprites/Growlithe.png");
                    this.cry = new Sound("AnimonCries/GrowlitheCry.wav");
                    break;
                case 4:
                    this.name = "Tentacool";
                    this.type[0] = "water";
                    this.bHP = 44;
                    this.bAtk = 48;
                    this.bDef = 65;
                    this.moves[0] = new Move("Tackle", 50, 0);
                    this.moves[1] = new Move("Water Gun", 40, 4);
                    this.moves[2] = new Move("Bubble", 40, 4);
                    this.sprite = new ImageIcon("Images/AnimonSprites/Tentacool.png");
                    this.cry = new Sound("AnimonCries/TentacoolCry.wav");
                    break;
                case 5:
                    this.name = "Ponyta";
                    this.type[0] = "fire";
                    this.bHP = 39;
                    this.bAtk = 52;
                    this.bDef = 43;
                    this.moves[0] = new Move("Scratch", 40, 0);
                    this.moves[1] = new Move("Ember", 40, 3);
                    this.moves[2] = new Move("Fire Fang", 65, 3);
                    this.sprite = new ImageIcon("Images/AnimonSprites/Ponyta.png");
                    this.cry = new Sound("AnimonCries/PonytaCry.wav");
                    break;
                case 6:
                    this.name = "Oddish";
                    this.type[0] = "grass";
                    this.bHP = 45;
                    this.bAtk = 49;
                    this.bDef = 49;
                    this.moves[0] = new Move("Tackle", 50, 0);
                    this.moves[1] = new Move("Vine Whip", 45, 1);
                    this.moves[2] = new Move("Take Down", 90, 0);
                    this.sprite = new ImageIcon("Images/AnimonSprites/Oddish.png");
                    this.cry = new Sound("AnimonCries/OddishCry.wav");
                    break;
                case 7:
                    this.name = "Poliwag";
                    this.type[0] = "water";
                    this.bHP = 44;
                    this.bAtk = 48;
                    this.bDef = 65;
                    this.moves[0] = new Move("Tackle", 50, 0);
                    this.moves[1] = new Move("Water Gun", 40, 4);
                    this.moves[2] = new Move("Bubble", 40, 4);
                    this.sprite = new ImageIcon("Images/AnimonSprites/Poliwag.png");
                    this.cry = new Sound("AnimonCries/PoliwagCry.wav");
                    break;
            }	
			
		this.nMoves = 3;
                this.setStats();
	}
	
        public int getNoOfMoves(){
            return this.nMoves;
        }
        
	public String getType1(){
		return this.type[0];
	}
	
	public String getType2(){
		return this.type[1];
	}
	
	public boolean checkEvolved(){
            return this.evolved;
        }
	
	public int getDefense(){
		return this.def;
	}
	
	public double getMaxHP(){
            return Math.round(this.maxHP*100.0)/100.0;
	}
	
	public double getCurrHP(){
            return Math.round(this.currHP*100.0)/100.0;
	}
        
        public int getExp(){
            return this.exp;
        }
	
	public void addHP(double heal){
            if(this.currHP<0)
                this.currHP = 0;
            this.currHP += heal;
            if(this.currHP > this.maxHP)
                this.currHP = this.maxHP;
	}
	
	public void setDamage(double damage){
            this.currHP -= damage;	
            this.currHP = Math.round(this.currHP * 100.00) / 100.00;
	}
	
	public String getName(){
		return this.name;
	}
        
        public int getLevel(){
            return level;
        }
        
        public ImageIcon getSprite(){
            return this.sprite;
        }
        
        public Sound getCry(){
            return this.cry;
        }
	
	public String getMove(int i){
		return this.moves[i].getName();
	}
        
        public void gainExp(int gain){
            exp += gain;
            levelUp();
        }
        
        public void levelUp(){
            while(level < (int) Math.cbrt(exp) && (level < 100 || exp==90000001))
                level++;
            setStats();
        }
        
        public void evolve(){
            ImageIcon newSprite = null;
            String newName = "";
            evolved = true;
            int lol=0;
            if(name.equals("Exeggcute")){
                newName = "Exeggutor";
                this.type[1] = "water";
                this.moves[3] = new Move("Tackle", 50, 0);
                this.moves[4] = new Move("Water Gun", 40, 4);
                this.moves[5] = new Move("Bubble", 40, 4);
                newSprite = new ImageIcon("Images/AnimonSprites/Exeggutor.png");
                this.cry = new Sound("AnimonCries/ExeggutorCry.wav");    
                lol=1;
            }
            if(name.equals("Koffing")){
                
                newName = "Weezing";
                this.type[1] = "fire";
                this.moves[3] = new Move("Scratch", 40, 0);
                this.moves[4] = new Move("Ember", 40, 3);
                this.moves[5] = new Move("Fire Fang", 65, 3);
                newSprite = new ImageIcon("Images/AnimonSprites/Weezing.png");
                this.cry = new Sound("AnimonCries/WeezingCry.wav");
                lol=1;
            }
            if(name.equals("Growlithe")){
                newName = "Arcanine";
                this.type[1] = "poison";
                this.moves[3] = new Move("Sting", 15, 2);
                this.moves[4] = new Move("Wrap", 15, 2);
                this.moves[5] = new Move("Bite", 60, 0);
                newSprite = new ImageIcon("Images/AnimonSprites/Arcanine.png");
                this.cry = new Sound("AnimonCries/ArcanineCry.wav");
                lol=1;
            }
            if(name.equals("Tentacool")){
                newName = "Tentacruel";
                this.type[1] = "poison";
                this.moves[3] = new Move("Sting", 15, 2);
                this.moves[4] = new Move("Wrap", 15, 2);
                this.moves[5] = new Move("Bite", 60, 0);
                newSprite = new ImageIcon("Images/AnimonSprites/Tentacruel.png");
                this.cry = new Sound("AnimonCries/TentacruelCry.wav");
                lol=1;
            }
            if(lol==1){
                nMoves = 6;
                Sound.route1.stop();
                Sound.alert.play();
                JOptionPane.showMessageDialog(null, "What's this? Your " + this.name + " is evolving!");
                EvolvePane ev = new EvolvePane(this.sprite, newSprite);
                ev.setVisible(true);
                ev.animation(this.name, newName, this.cry);

                this.sprite = newSprite;
                this.name = newName;
            }
        }
	
	public int randInt(int min, int max) {
            Random rand = new Random();
            int randomNum = rand.nextInt(max - min + 1) + min;

            return randomNum;
	}
        
        public void setStats(){
            double temp = maxHP;
            atk = (2*bAtk*level/100)+5;
            def = (2*bDef*level/100)+5;
            maxHP = (2*bHP+100)*level/100+10;
            currHP += (maxHP-temp);
        }
        
	public double attack(int move, Animon target){
            /* Damage_on_Animon_y = (A * B / 50 / D + 2) * M * R / 255
            Where :
            A = Attack power of Animon_x
            B = Base Power of the Move of Animon_x
            D = Defense Power of Animon_y
            M = Multiplier of the Damage as show in Table 1
            R = any random number from 217 to 255 */

            double mult1=1, mult2=1, damage, mult;
            if(move<=2){
                mult1 = getMult(this.getType1(), target.getType1());
                if(this.getType2()!=null && target.getType2()!=null)
                    mult2 = getMult(this.getType1(), target.getType2());
            }
            else{
                mult1 = getMult(this.getType2(), target.getType1());
                if(target.getType2()!=null)
                    mult2 = getMult(this.getType2(), target.getType2());
            }
            mult = (mult1+mult2)/2;

            damage = ((this.atk * this.moves[move].getPower() / 50 / target.getDefense() + 2)*mult*randInt(217, 255)/255);		
            return damage;
	}
        
        public double getMult(String t1, String t2){
            double mult=1;
            if(t1.equals("grass"))
                if(t2.equals("grass"))
                    mult = 0.5;
                else if(t2.equals("fire"))
                    mult = 0.5;
                else if(t2.equals("water"))
                    mult = 2;
                else if(t2.equals("poison"))
                    mult = 0.5;
            if(t1.equals("fire"))
                if(t2.equals("grass"))
                        mult = 2;
                else if(t2.equals("fire"))
                        mult = 0.5;
                else if(t2.equals("water"))
                        mult = 0.5;
                else if(t2.equals("poison"))
                        mult = 1;
            if(t1.equals("water"))
                if(t2.equals("grass"))
                        mult = 0.5;
                else if(t2.equals("fire"))
                        mult = 2;
                else if(t2.equals("water"))
                        mult = 0.5;
                else if(t2.equals("poison"))
                        mult = 1;
            if(t1.equals("poison"))
                if(t2.equals("grass"))
                        mult = 2;
                else if(t2.equals("fire"))
                        mult = 1;
                else if(t2.equals("water"))
                        mult = 1;
                else if(t2.equals("poison"))
                        mult = 0.5;
            else mult = 1;
            return mult;
        }
}
