/**
 *
 * Authors: Marc Dominic San Pedro, Kurt Neil Aquino
 */

import java.util.Random;
import java.util.Scanner;

public class PvP{
	public static void printAnimon(Animon[] animon, int nCtr){
		for(int i = 0; i < nCtr; i++)
			if(animon[i].getCurrHP() > 0)
				pln((i+1) + ": " + animon[i].getName());
	}

	public static int livingAnimon(Animon[] animon, int nCtr){
		int ctr=0;
		for(int i = 0; i < nCtr; i++)
			if(animon[i].getCurrHP() > 0)
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
	
	public PvP(){
		Scanner sc = new Scanner(System.in);
		Animon[] player1Animon = new Animon[4];
		Animon[] player2Animon = new Animon[4];
		String aName, dump;
		int p1AnimonCtr = 0, p2AnimonCtr = 0, i;
		do{
			pln("Player 1:");
			p("Animon " + (p1AnimonCtr+1) + ": enter name: ");
			aName = sc.nextLine();
			int type;
			pln("Choose your Animon's type:\n1- Grass\n2- Poison\n3- Fire\n4- Water");
			do{
				type = sc.nextInt();
				dump = sc.nextLine();
				if(type > 4 || type < 1)
					p("Invalid, please try again: ");
			}while(type > 4 || type < 1);
			player1Animon[p1AnimonCtr] = new Animon(aName, type);
			p1AnimonCtr++;
		}while(p1AnimonCtr < 4);
		
		do{
			pln("Player 2:");
			p("Animon " + (p2AnimonCtr+1) + ": enter name: ");
			aName = sc.nextLine();
			int type;
			pln("Choose your Animon's type:\n1- Grass\n2- Poison\n3- Fire\n4- Water");
			do{
				type = sc.nextInt();
				dump = sc.nextLine();
				if(type > 4 || type < 1)
					p("Invalid, please try again: ");
			}while(type > 4 || type < 1);
			player2Animon[p2AnimonCtr] = new Animon(aName, type);
			p2AnimonCtr++;
		}while(p2AnimonCtr < 4);
		
		boolean p1Turn;
		int turn = randInt(1, 2);
		int move;
		double damage;
		int p1ActiveAnimon, p2ActiveAnimon;
		
		pln("Player 1:");
		pln("Please choose your first Animon: ");
		printAnimon(player1Animon, p1AnimonCtr);
		do{
			p1ActiveAnimon = sc.nextInt();
			if(p1ActiveAnimon > 4 || p1ActiveAnimon < 1)
				p("Invalid, please try again: ");
		}while(p1ActiveAnimon > 4 || p1ActiveAnimon < 1);
		p1ActiveAnimon--;

		pln("Player 2:");
		pln("Please choose your first Animon: ");
		printAnimon(player2Animon, p2AnimonCtr);
		do{
			p2ActiveAnimon = sc.nextInt();
			if(p2ActiveAnimon > 4 || p2ActiveAnimon < 1)
				p("Invalid, please try again: ");
		}while(p2ActiveAnimon > 4 || p2ActiveAnimon < 1);
		p2ActiveAnimon--;
		
		if(turn == 1)
			p1Turn = true;
		else p1Turn = false;
		
		pln("Battle start!");
		
		do{
			if(p1Turn){
				pln("Player 1");
				pln("What will " + player1Animon[p1ActiveAnimon].getName() + " do?");
				pln("HP: " + player1Animon[p1ActiveAnimon].getCurrHP());
				pln("Player 2's " + player2Animon[p2ActiveAnimon].getName() + "'s HP: " + player2Animon[p2ActiveAnimon].getCurrHP());
				player1Animon[p1ActiveAnimon].printMoves();
				
				do{
					move = sc.nextInt();
					if(move>3 || move<1)
						p("Invalid, please try again: ");
				}while(move>3 || move<1);
				
				damage = player1Animon[p1ActiveAnimon].attack(move, player2Animon[p2ActiveAnimon]);
				damage = Math.round(damage * 100.00) / 100.00;
				player2Animon[p2ActiveAnimon].setDamage(damage);
				pln(player1Animon[p1ActiveAnimon].getName() + " used " + player1Animon[p1ActiveAnimon].getMove(move) + "! " + damage + " damage dealt!");
				
				if(player2Animon[p2ActiveAnimon].getCurrHP() > 0)
					pln("Player 2's " + player2Animon[p2ActiveAnimon].getName() + "'s HP: " + player2Animon[p2ActiveAnimon].getCurrHP());
				else
					pln("Player 2's " + player2Animon[p2ActiveAnimon].getName() + " fainted!");
					
				p1Turn = false;
				
				if(livingAnimon(player2Animon, 4) == 0){
					pln("Player 1 wins!");
					pln("Game over!");
				}
				else if(player2Animon[p2ActiveAnimon].getCurrHP() <= 0){
					pln("Player 2:");
					pln("Please choose your next Animon: ");
					printAnimon(player2Animon, p2AnimonCtr);
					do{
						p2ActiveAnimon = sc.nextInt();
						if(p2ActiveAnimon > 4 || p2ActiveAnimon < 1 || player2Animon[p2ActiveAnimon-1].getCurrHP() < 0)
							p("Invalid: please try again: ");
					}while(p2ActiveAnimon > 4 || p2ActiveAnimon < 1 || player2Animon[p2ActiveAnimon-1].getCurrHP() < 0);
                    p2ActiveAnimon--;
                }
			}
			
			else{
				pln("Player 2");
				pln("What will " + player2Animon[p2ActiveAnimon].getName() + " do?");
				pln("HP: " + player2Animon[p2ActiveAnimon].getCurrHP());
				pln("Player 1's " + player1Animon[p1ActiveAnimon].getName() + "'s HP: " + player1Animon[p1ActiveAnimon].getCurrHP());
				player2Animon[p2ActiveAnimon].printMoves();
				
				do{
					move = sc.nextInt();
					if(move>3 || move<1)
						p("Invalid, please try again: ");
				}while(move>3 || move<1);
				
				damage = player2Animon[p2ActiveAnimon].attack(move, player1Animon[p1ActiveAnimon]);
				damage = Math.round(damage * 100.00) / 100.00;
				player1Animon[p1ActiveAnimon].setDamage(damage);
				pln(player2Animon[p2ActiveAnimon].getName() + " used " + player2Animon[p2ActiveAnimon].getMove(move) + "! " + damage + " damage dealt!");
				
				if(player1Animon[p1ActiveAnimon].getCurrHP() > 0)
					pln("Player 1's " + player1Animon[p1ActiveAnimon].getName() + "'s HP: " + player1Animon[p1ActiveAnimon].getCurrHP());
				else
					pln("Player 1's " + player1Animon[p1ActiveAnimon].getName() + " fainted!");
				
				p1Turn = true;
				
				if(livingAnimon(player1Animon, 4) == 0){
					pln("Player 2 wins!");
					pln("Game over!");
				}
				else if(player1Animon[p1ActiveAnimon].getCurrHP() <= 0){
					pln("Player 1:");
					pln("Please choose your next Animon: ");
					printAnimon(player1Animon, p1AnimonCtr);
					do{
						p1ActiveAnimon = sc.nextInt();
						if(p1ActiveAnimon > 4 || p1ActiveAnimon < 1 || player1Animon[p1ActiveAnimon-1].getCurrHP() < 0)
							p("Invalid: please try again: ");
					}while(p1ActiveAnimon > 4 || p1ActiveAnimon < 1 || player1Animon[p1ActiveAnimon-1].getCurrHP() < 0);
                    p1ActiveAnimon--;
                }
			}
		}while(livingAnimon(player1Animon, 4) > 0 && livingAnimon(player2Animon, 4) > 0);
	}
}