import java.util.Random;
import java.util.Scanner;

public class Battle{
	//randomizes a number given a minimum and maximum range
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}
	
	public Battle(){
		Scanner sc = new Scanner(System.in);
		Trainer[] player = new Trainer[2];
		String aName, dump;
		boolean isAI = false;
		int i;
		int nChoice;
		int type;
		
		//PvP or PvCPU
		System.out.println("\nChoose battle: \n1 - Player vs Player\n2- Player vs AI");
		do{
			System.out.print("Choice: ");
			nChoice = sc.nextInt();
			dump = sc.nextLine();
			if(nChoice < 1 || nChoice > 2)
				System.out.print("\nInvalid, please try again: ");
		}while(nChoice < 1 || nChoice > 2);
		
		if(nChoice == 1)
			isAI = false;
		else isAI = true;
		
		
		//initializes two trainers through user input
		for(i = 0; i < 2; i++){
			//if the index is at 0 and the selected mode is PvP
			if(i == 0 || !isAI){
				//instantiate trainer
				System.out.print("\nPlayer " + (i + 1) + "'s name: ");
				aName = sc.nextLine();
				player[i] = new Trainer(aName);
				
				//initialize animon party
				System.out.println("\nCreate party:");
				do{
					System.out.print("\nAnimon " + (player[i].countAnimon() + 1) + ": enter name: ");
					aName = sc.nextLine();
					
					int type1, type2;	
					System.out.println("\nChoose your Animon's 1st type:\n1- Grass\n2- Poison\n3- Fire\n4- Water\n5 - None");
					do{
						type1 = sc.nextInt();
						dump = sc.nextLine();
						if(type1 < 1 || type1 > 5)
							System.out.print("Invalid, please try again: ");
					}while(type1 < 1 || type1 > 5);
						
					System.out.println("\nChoose your Animon's 2nd type:\n1- Grass\n2- Poison\n3- Fire\n4- Water\n5 - None");
					do{
						type2 = sc.nextInt();
						dump = sc.nextLine();
						if(type2 < 1 || type2 > 5)
							System.out.print("\nInvalid, please try again: ");
					}while(type2 < 1 || type2 > 5);
					
					player[i].addAnimon(new Animon(aName, type1, type2));
				}while(player[i].countAnimon() < 4);
			}
			//if the selected mode is PvCPU
			else{
				//instantiates a CPU trainer with fixed pokemon
				player[i] = new Trainer("Youngster Ben");
				player[i].addAnimon(new Animon("Venusaur", 1, 0));
				player[i].addAnimon(new Animon("Arbok", 2, 5));
				player[i].addAnimon(new Animon("Charizard", 3, 5));
				player[i].addAnimon(new Animon("Blastoise", 4, 5));
				player[i].switchAnimon(1);
			}
		}
		
		//randomizes the first attacker
		int turn = randInt(1, 2);
		int attacker, opponent;
		int move;
		double damage;
		int activeAnimon;
		
		//asks for an active animon to be chosen
		for(i = 0; i < 2; i++){
			//if the index is at 0 and the selected mode is PvP
			if(i == 0 || !isAI){
				System.out.println("\nPlayer " + (i + 1) + ": ");
				System.out.println("Please choose your first Animon: ");
				player[i].printAnimon();
				
				do{
					activeAnimon = sc.nextInt();
					if(activeAnimon < 1 || activeAnimon > player[i].countAnimon())
						System.out.print("\nInvalid, please try again: ");
					else
						player[i].switchAnimon(activeAnimon);
				}while(activeAnimon < 1 || activeAnimon > player[i].countAnimon());
			//if the slected mode is PvCPU
			}
			else break;
		}
		
		do{
			//determines who is the current attacker
			if(turn == 1){
				attacker = 0;
				opponent = 1;
			}
			else{
				attacker = 1;
				opponent = 0;
			}
			
			//displays attacker's and the opponent's active animon
			System.out.println("\n" + player[attacker].getName() + " :");
			System.out.println("What will " + player[attacker].getActive().getName() + " do?");
			System.out.println("HP: " + player[attacker].getActive().getCurrHP());
			System.out.println("\n" + player[opponent].getName() + " 's " + player[opponent].getActive().getName() + "'s HP: " + player[attacker].getActive().getCurrHP());
			
			//if PvCPU, move selection is randomized
			if(turn == 2 && isAI)
				move = randInt(1, 3);
			//asks for a move to be selected
			else{
				player[attacker].getActive().printMoves();	
				do{
					move = sc.nextInt();
					if(move < 1 || move > 3)
						System.out.print("\nInvalid, please try again: ");
				}while(move < 1 || move > 3);
			}
			
			//sets the damage to the opponent's animon based on the attacker's move
			damage = player[attacker].getActive().attack(move, player[opponent].getActive());
			damage = Math.round(damage * 100.00) / 100.00;
			player[opponent].getActive().setDamage(damage);
			
			if(turn == 2 && isAI)
				System.out.println( "\nOpponent's " + player[attacker].getActive().getName() + " used " + player[attacker].getActive().getMove(move) + "! " + damage + " damage dealt!");
			else
				System.out.println(player[attacker].getActive().getName() + " used " + player[attacker].getActive().getMove(move) + "! " + damage + " damage dealt!");
				
			//checks if the opponent's animon is alive
			if(player[opponent].getActive().getCurrHP() > 0){
				if(turn == 2 && isAI)
					System.out.println("\nYour " + player[opponent].getActive().getName() + "'s HP: " + player[opponent].getActive().getCurrHP());
				else
					System.out.println("\nOpponent's " + player[opponent].getActive().getName() + "'s HP: " + player[opponent].getActive().getCurrHP());
			}
			
			//if the opponent has fainted
			else{
				//attacker earns exp
				player[attacker].getActive().earnExp(player[opponent]);
				
				if(turn == 2 && isAI)
					System.out.println("\nYour " + player[opponent].getActive().getName() + " fainted!");
				else
					System.out.println("\nOpponent's " + player[opponent].getName() + "'s " + player[opponent].getActive().getName() + " fainted!");
				
				//checks if the opponent has any more animon
				if(player[opponent].countAvailable() == 0){
					System.out.println("\n" + player[attacker].getName() + " wins!");
					System.out.println("\nGame over!");
				}
				
				//if the opponent is an AI
				if(turn == 1 && isAI){
					//if the current animon has fainted, switch to the next one
					if(player[opponent].getAnimon(1).getCurrHP() <= 0)
						player[opponent].switchAnimon(2);
					else if(player[opponent].getAnimon(2).getCurrHP() <= 0)
						player[opponent].switchAnimon(3);
					else if(player[opponent].getAnimon(3).getCurrHP() <= 0)
						player[opponent].switchAnimon(4);
					if(player[opponent].getAnimon(4).getCurrHP() <= 0)
						System.out.println("\nVictory!");
				}
				
				//asks the opponent to switch animons
				else{
					System.out.println("\n" + player[opponent].getName() + " :");
					System.out.println("Please choose your next Animon: ");
					
					player[opponent].printAvailable();
					do{
						activeAnimon = sc.nextInt();
						if(activeAnimon < 1 || activeAnimon > player[opponent].countAnimon() || player[opponent].getAnimon(activeAnimon).getCurrHP() <= 0)
							System.out.print("\nInvalid, please try again: ");
						else
							player[opponent].switchAnimon(activeAnimon);
					}while(activeAnimon < 1 || activeAnimon > player[opponent].countAnimon() || player[opponent].getAnimon(activeAnimon).getCurrHP() <= 0);
				}
			}
			
			//changes the player's turn
			if(turn == 1)
				turn = 2;
			else turn = 1;
		}while(player[0].countAvailable() > 0 || player[1].countAvailable() > 0);
	}
}