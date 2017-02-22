package mp02;

/**
 *
 * Authors: Marc Dominic San Pedro, Kurt Neil Aquino
 */

public class MP01{
	public static void main(String[] args){
            TrainerSprite[] CPUs = new TrainerSprite[3];
            CPUs[0] = new TrainerSprite(2, 5, new Trainer("Paul"));
            CPUs[1] = new TrainerSprite(33, 7, new Trainer("Nick"));
            CPUs[2] = new TrainerSprite(16, 10, new Trainer("Mike"));
            TrainerSprite player = new TrainerSprite(20, 7, new Trainer("Marc"));
            player.getModel().addAnimon(new Animon(1));
            //player.getModel().getAnimon(1).gainExp(998);
            player.getModel().addAnimon(new Animon(2));
            player.getModel().addAnimon(new Animon(3));
            player.getModel().addAnimon(new Animon(4));
            player.getModel().switchAnimon(0);
            
            CPUs[0].getModel().addAnimon(new Animon(1));
            CPUs[0].getModel().addAnimon(new Animon(2));
            CPUs[0].getModel().addAnimon(new Animon(3));
            
            CPUs[1].getModel().addAnimon(new Animon(2));
            CPUs[1].getModel().addAnimon(new Animon(3));
            CPUs[1].getModel().addAnimon(new Animon(4));
            
            CPUs[2].getModel().addAnimon(new Animon(5));
            CPUs[2].getModel().addAnimon(new Animon(6));
            CPUs[2].getModel().addAnimon(new Animon(7));
            
            AnimonMap aMap = new AnimonMap(40, 15);
            aMap.setLocation(20, 20);
            aMap.addTrainer(CPUs[0], "AI");
            aMap.addTrainer(CPUs[1], "AI");
            aMap.addTrainer(CPUs[2], "AI");
            aMap.addTrainer(player, "Player");
            aMap.setResizable(false);
            aMap.setVisible(true);
        }
}
    

