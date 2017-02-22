  /**
 *
 * Authors: Marc Dominic San Pedro, Kurt Neil Aquino
 */

import java.util.Random;
import java.util.Scanner;

public class PvCPU {
    public static void printAnimon(Animon[] animon, int nCtr){
		for(int i = 0; i < nCtr; i++)
			if(animon[i].getHP() > 0)
				pln((i+1) + ": " + animon[i].getName());
	}

	public static int livingAnimon(Animon[] animon, int nCtr){
		int ctr=0;
		for(int i = 0; i < nCtr; i++)
			if(animon[i].getHP() > 0)
				ctr++;
		return ctr;
	}
	
	public static void pln(Object str){
		System.out.println(""+str);
	}
	
	public static void p(Object str){
		System.out.print(""+str);
	}
	
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}
	
	public PvCPU(){
		Scanner sc = new Scanner(System.in);
		Animon[] playerAnimon = new Animon[4];
		Animon[] CPUAnimon = new Animon[4];
		String aName, dump;
		int pAnimonCtr = 0, CPUAnimonCtr = 0, i;
		do{
			p("Animon " + (pAnimonCtr+1) + ": enter name: ");
			aName = sc.nextLine();
			int type;
			pln("Choose your Animon's type:\n1- Grass\n2- Poison\n3- Fire\n4- Water");
			do{
				type = sc.nextInt();
				dump = sc.nextLine();
				if(type > 4 || type < 1)
					p("Invalid, please try again: ");
			}while(type > 4 || type < 1);
			playerAnimon[pAnimonCtr] = new Animon(aName, type);
			pAnimonCtr++;
		}while(pAnimonCtr < 4);
		CPUAnimon[0] = new Animon("Venusaur", 1);
		CPUAnimon[1] = new Animon("Arbok", 2);
		CPUAnimon[2] = new Animon("Charizard", 3);
		CPUAnimon[3] = new Animon("Blastoise", 4);
		CPUAnimonCtr = 4;
		boolean p1Turn = true;
		
		int move;
		double damage;
		int p1ActiveAnimon, CPUActiveAnimon = 0;

		pln("Please choose your first Animon: ");
		printAnimon(playerAnimon, pAnimonCtr);
		do{
			p1ActiveAnimon = sc.nextInt();
			if(p1ActiveAnimon > 4 || p1ActiveAnimon < 1)
				p("Invalid, please try again: ");
		}while(p1ActiveAnimon > 4 || p1ActiveAnimon < 1);
		p1ActiveAnimon--;
		pln("Battle start!");
		do{
			if(p1Turn){
				pln("What will " + playerAnimon[p1ActiveAnimon].getName() + " do?");
				pln("HP: " + playerAnimon[p1ActiveAnimon].getHP());
				playerAnimon[p1ActiveAnimon].printMoves();
				pln("Opponent " + CPUAnimon[CPUActiveAnimon].getName() + "'s HP: " + CPUAnimon[CPUActiveAnimon].getHP());
				do{
					move = sc.nextInt();
					if(move>3 || move<1)
						p("Invalid, please try again: ");
				}while(move>3 || move<1);
				
				damage = playerAnimon[p1ActiveAnimon].attack(move, CPUAnimon[CPUActiveAnimon]);
				damage = Math.round(damage * 100.00) / 100.00;
				CPUAnimon[CPUActiveAnimon].setDamage(damage);
				pln(playerAnimon[p1ActiveAnimon].getName() + " used " + playerAnimon[p1ActiveAnimon].getMove(move) + "! " + damage + " damage dealt!");
				if(CPUAnimon[CPUActiveAnimon].getHP() > 0)
					pln("Opponent " + CPUAnimon[CPUActiveAnimon].getName() + "'s HP: " + CPUAnimon[CPUActiveAnimon].getHP());
				else{
					pln("Opponent's " + CPUAnimon[CPUActiveAnimon].getName() + " fainted!");
					if(CPUActiveAnimon==0)
						CPUActiveAnimon = 1;
					else if(CPUActiveAnimon==1)
						CPUActiveAnimon = 2;
					else if(CPUActiveAnimon==2)
						CPUActiveAnimon = 3;
					if(CPUAnimon[3].getHP() <= 0)
						pln("Victory!");
				}
				p1Turn = false;
			}
			else{
				move = randInt(1, 3);
				//damage = CPUAnimon[CPUActiveAnimon].attack(move, playerAnimon[p1ActiveAnimon]);
				//damage = Math.round(damage * 100.00) / 100.00;
				//playerAnimon[p1ActiveAnimon].setDamage(damage);
				pln("Opponent's " + CPUAnimon[CPUActiveAnimon].getName() + " used " + CPUAnimon[CPUActiveAnimon].getMove(move) + "! " + damage + " damage dealt!");
				if(playerAnimon[p1ActiveAnimon].getHP() <= 0)
					pln("Your " + playerAnimon[p1ActiveAnimon].getName() + " fainted!");
				p1Turn = true;
				if(livingAnimon(playerAnimon, 4) == 0)
					pln("Game over!");
				else if(playerAnimon[p1ActiveAnimon].getHP() <= 0){
					pln("Please choose your next Animon: ");
					printAnimon(playerAnimon, pAnimonCtr);
					do{
						p1ActiveAnimon = sc.nextInt();
						if(p1ActiveAnimon > 4 || p1ActiveAnimon < 1 || playerAnimon[p1ActiveAnimon-1].getHP() < 0)
							p("Invalid: please try again: ");
					}while(p1ActiveAnimon > 4 || p1ActiveAnimon < 1 || playerAnimon[p1ActiveAnimon-1].getHP() < 0);
                                        p1ActiveAnimon--;
                                }
			}
		}while(livingAnimon(playerAnimon, 4) > 0 || livingAnimon(CPUAnimon, 4) > 0);
	}
}
