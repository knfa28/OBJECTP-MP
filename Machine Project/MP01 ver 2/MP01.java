import java.util.*;

public class MP01{
	public static void main(String[] args){
		Trainer me = new Trainer("Me", false);
		me.addAnimon(new Animon("Meganium", 1, 0));
		me.addAnimon(new Animon("Koffing", 2, 0));
		me.addAnimon(new Animon("Growlithe", 3, 0));
		me.addAnimon(new Animon("Tentacool", 4, 0));
		me.switchAnimon(1);
		
		Trainer him = new Trainer("Him", true);
		him.switchAnimon(1);
		
		Animon seviper = new Animon("Arcanine", 3, 0);
		
        Battle vsTrainer = new Battle(me, him);
		Battle vsAnimon = new Battle(me, seviper);
		
		//BattleView vsTrainerView = new BattleView(vsTrainer);
		BattleView vsAnimonView = new BattleView(vsAnimon);
		
		//vsTrainerView.setVisible(true);
		vsAnimonView.setVisible(true);
    }
}
    

