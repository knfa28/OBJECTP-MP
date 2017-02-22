package mp02;

import java.util.*;
import javax.swing.JOptionPane;

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
        if(this.player[0].getActive()==null)
            this.player[0].switchAnimon(0);
        this.player[1].switchAnimon(0);
        vsTrainer = true;
    }

    //vs an Animon
    public Battle(Trainer player, Animon wildAnimon){
        this.player[0] = player;
        this.player[1] = new Trainer("asdf");
        this.player[1].addAnimon(wildAnimon);
        if(this.player[0].getActive()==null)
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
    public String attack(int move){
        int turn = randInt(1,2);
        
        int ctr = 0;
        double damage;
        boolean end = false;
        List<String> toNotif = new ArrayList<String>();
        String messages = "";
        damage = player[0].getActive().attack(move, player[1].getActive());
        messages += player[0].getActive().getName() + " used " + player[0].getActive().getMove(move) + 
            "! " + Math.round(damage*100.0)/100.0 + " damage dealt!\n";
        player[1].getActive().setDamage(damage);
        if(player[1].getActive().getCurrHP()>0){
            messages += opponentAttack();
        }
        else{
            messages += "Opponent's " + player[1].getActive().getName() + " fainted!\n";
            player[0].getActive().gainExp(30*player[1].getActive().getLevel());
            int i;
            for(i=1; i<=player[1].countAnimon(); i++){
                if(player[1].getAnimon(i).getCurrHP()>0)
                    break;
            }
            if(i<=player[1].countAnimon()){
                player[1].switchAnimon(i-1);
                messages += "Opponent sent out " + player[1].getActive().getName()+"!\n";
            }
            else{
                Sound.victory.play();
                Sound.battleTheme.stop();
                JOptionPane.showMessageDialog(null, "Victory!");
                Sound.victory.stop();
            }
        }
          

        return messages;
    }

    public String opponentAttack(){
        double damage;
        String messages = "";
        int oppMove = randInt(0, player[1].getActive().getNoOfMoves()-1);
        damage = player[1].getActive().attack(oppMove, player[0].getActive());
        messages += player[1].getActive().getName() + " used " + player[1].getActive().getMove(oppMove) + 
                    "! " + Math.round(damage*100.0)/100.0 + " damage dealt!\n";
                player[0].getActive().setDamage(damage);
        return messages;
    }
    
    public List<String> switchActive(int index){
        List<String> toNotif = new ArrayList<String>();

        player[attacker].switchAnimon(index);
        toNotif.add(player[attacker].getName() + " sent out " + player[attacker].getActive().getName() + " !");
        toNotif.add("Go " + player[attacker].getActive().getName() + " !");

        return toNotif;
    }
    
    public void heal(){
        player[0].getActive().addHP(player[0].getActive().getMaxHP());
    }
}