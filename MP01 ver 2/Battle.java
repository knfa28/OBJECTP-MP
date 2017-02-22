import java.util.*;

public class Battle{
	private Trainer[] player = new Trainer[2];
	private boolean vsTrainer;
	private int attacker, opponent;
	
	//randomizes a number given a minimum and maximum range
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}
	
	//vs a Trainer
	public Battle(Trainer player, Trainer opponent){
		this.player[0] = player;
		this.player[1] = opponent;
		this.player[0].switchAnimon(0);
		this.player[1].switchAnimon(0);
		vsTrainer = true;
	}
	
	//vs an Animon
	public Battle(Trainer player, Animon wildAnimon){
		this.player[0] = player;
		this.player[1] = new Trainer("", false);
		this.player[1].addAnimon(wildAnimon);
		this.player[0].switchAnimon(0);
		this.player[1].switchAnimon(0);
		vsTrainer = false;
	}
	
	public Trainer getPlayer(){
		return player[0];
	}
	
	public Trainer getOpponent(){
		return player[1];
	}
	
	public boolean isVsTrainer(){
		return vsTrainer;
	}
	
	public boolean checkEnd(){
		if(player[0].countAvailable() > 0 && player[1].countAvailable() > 0)
			return false;
		return true;
	}
	
	//battle phase
	public List<String> attack(int move, boolean enemyOnly){
		int turn = randInt(1,2);
		int ctr = 0;
		double damage;
		boolean end = false;
		List<String> toNotif = new ArrayList<String>();
		
		if(player[attacker].getActive().getCurrHP() <= 0 || player[opponent].getActive().getCurrHP() <= 0){
			if(turn == 1)
				toNotif.add("Please choose your next Animon");
			else
				toNotif.add("You win!");
		}
		else{
			do{
				//determines the attacker/opponent
				if(turn == 2 || enemyOnly){
					attacker = 1;
					opponent = 0;
				}
				else{
					attacker = 0;
					opponent = 1;
				}
				
				//displays the player and the opponent's active animon's details -> move selection
				if(turn == 2 || enemyOnly)
					 move = randInt(1, player[attacker].getActive().getMoveCnt() - 1);
				
				//sets the damage to the opponent's animon based on the attacker's move
				damage = player[attacker].getActive().attack(move, player[opponent].getActive());
				damage = Math.round(damage * 100.00) / 100.00;
				player[opponent].getActive().setDamage(damage);
				
				//displays damage dealt
				if(turn == 1){
					toNotif.add(player[attacker].getActive().getName() + " used " + player[attacker].getActive().getMove(move) + "!");
					toNotif.add(damage + " damage dealt!");
				}
				else{
					if(vsTrainer){
						toNotif.add(player[attacker].getName() + "'s " + player[attacker].getActive().getName() + " used " + player[attacker].getActive().getMove(move) + "!");
						toNotif.add(damage + " damage dealt!");
					}
					else{
						toNotif.add(player[attacker].getActive().getName() + " used " + player[attacker].getActive().getMove(move) + "!");
						toNotif.add(damage + " damage dealt!");
					}
				}
				
				//checks if the opponent's animon is alive, displays opponent's hp
				if(player[opponent].getActive().getCurrHP() <= 0){
					if(turn == 1){
						if(vsTrainer){
							toNotif.add(player[opponent].getName() + "'s " + player[opponent].getActive().getName() + " fainted!");
							
							//if the current animon has fainted, switch to the next one
							if(player[opponent].countAvailable() > 0){
								if(player[opponent].getAnimon(1).getCurrHP() <= 0)
									if(player[opponent].getAnimon(2).getCurrHP() <= 0)
										if(player[opponent].getAnimon(3).getCurrHP() <= 0)
											player[opponent].switchAnimon(3);
									else player[opponent].switchAnimon(2);
								else player[opponent].switchAnimon(1);
								
								toNotif.add(player[opponent].getName() +" sent out "+ player[opponent].getActive().getName() +" !");
							}
							else{
								toNotif.add("You win!");
								end = true;
							}
						}
						else{
							toNotif.add(player[opponent].getActive().getName() + " fainted!");
							toNotif.add("You win!");
							end = true;
						}
								
						//attacker earns exp
						if(player[attacker].getActive().earnExp(player[opponent].getActive()))
							toNotif.add(player[attacker].getActive().getName() + " grew to level " + player[attacker].getActive().getLvl() + "!");
					}
					else{
						toNotif.add("Your " + player[opponent].getActive().getName() + " fainted!");
						
						//asks the player to switch animons
						if(player[opponent].countAvailable() > 0)
							toNotif.add("Please choose your next Animon");
						else
							toNotif.add("You lost!");
						
						end = true;
					}
				}
		
				if(turn == 1)
					turn = 2;
				else turn = 1;
				
				ctr++;
			}while(ctr < 2 && !end && !enemyOnly);
		}

		return toNotif;
	}
	
	public List<String> switchActive(int index){
		List<String> toNotif = new ArrayList<String>();
		
		player[attacker].switchAnimon(index);
		toNotif.add(player[attacker].getName() + " sent out " + player[attacker].getActive().getName() + " !");
		toNotif.add("Go " + player[attacker].getActive().getName() + " !");
		
		return toNotif;
	}
}