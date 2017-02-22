package mp02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.ArrayList;

public class BattleView extends JFrame{
	private Battle model;
	
	//left panel
	private JPanel leftPanel = new JPanel();
	
	//animon details panel
	private JPanel battlePanel = new JPanel();
        private JPanel PlayersPanel = new JPanel();
	
	//p1 details panel
	private JPanel p1Panel = new JPanel();
        private JPanel p1InfoPanel = new JPanel();
        private JPanel p1ActionsPanel = new JPanel();
        private JLabel p1Sprite = new JLabel();
	private JLabel p1NameLabel = new JLabel();
	private JLabel p1LvlLabel = new JLabel();
	private JLabel p1TypeLabel = new JLabel();
	private JLabel p1HPLabel = new JLabel();
        private JButton atkBtn = new JButton("Attack");
        private JButton switchBtn = new JButton("Switch");
        private JButton runBtn = new JButton("Run");
        private JLabel p1MovesLabel = new JLabel("Moves: ");
        private ArrayList<JRadioButton> moves = new ArrayList<JRadioButton>();
        private ButtonGroup movesList = new ButtonGroup();
	
	//p2 details panel
	private JPanel p2Panel =  new JPanel();
        private JLabel p2Sprite = new JLabel();
	private JLabel p2NameLabel = new JLabel("P2");
	private JLabel p2LvlLabel = new JLabel("lvl: ");
	private JLabel p2TypeLabel = new JLabel("Type(s): ");
	private JLabel p2HPLabel = new JLabel("HP: ");
	
	//notification panel
	private JPanel notifPanel = new JPanel();
	private JTextArea infoPane = new JTextArea();
        
        private ArrayList<JRadioButton> switchAnimon = new ArrayList<JRadioButton>();
        private ButtonGroup aniList = new ButtonGroup();
        private JButton okSwitch = new JButton("OK");
        private JFrame switcher = new JFrame();
        private JPanel sPanel = new JPanel();
        
	public BattleView(Battle battle){
            //Sound.battleTheme.loop();
            
            this.model = battle;
            setLayout(new BorderLayout());
            if(model.isVsTrainer())
                setTitle(model.getPlayer().getName() +" vs. Trainer "+ model.getOpponent().getName());
            else if(model.getOpponent().getActive().getLevel()==448)
                setTitle(model.getPlayer().getName() +" vs. the Mighty "+ model.getOpponent().getActive().getName());
            else
                setTitle(model.getPlayer().getName() +" vs. Wild "+ model.getOpponent().getActive().getName());

            setSize(450, 375);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            //player stats
            p1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
            p1Panel.setLayout(new GridLayout(1, 2));
            
            p1InfoPanel.setLayout(new BoxLayout(p1InfoPanel, BoxLayout.Y_AXIS));
            p1InfoPanel.add(p1Sprite);
            p1InfoPanel.add(p1NameLabel);
            p1InfoPanel.add(p1LvlLabel);
            p1InfoPanel.add(p1TypeLabel);
            p1InfoPanel.add(p1HPLabel);
            p1InfoPanel.add(p1MovesLabel);
            
            p1ActionsPanel.setLayout(new BoxLayout(p1ActionsPanel, BoxLayout.Y_AXIS));
            JLabel padding = new JLabel();
            padding.setIcon(new ImageIcon("Images/AnimonSprites/lol.png"));
            atkBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
            switchBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
            runBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
            actionL a = new actionL();
            atkBtn.addActionListener(a);
            switchBtn.addActionListener(a);
            runBtn.addActionListener(a);
            atkBtn.setSize(70, 20);
            p1ActionsPanel.add(padding);
            p1ActionsPanel.add(atkBtn);
            p1ActionsPanel.add(switchBtn);
            p1ActionsPanel.add(runBtn);
            
            p1Panel.add(p1InfoPanel);
            p1Panel.add(p1ActionsPanel);

            //opponent stats
            p2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
            p2Panel.setLayout(new BoxLayout(p2Panel, BoxLayout.Y_AXIS));
            p2Panel.add(p2Sprite);
            p2Panel.add(p2NameLabel);
            p2Panel.add(p2LvlLabel);
            p2Panel.add(p2TypeLabel);
            p2Panel.add(p2HPLabel);
            
            PlayersPanel.setLayout(new GridLayout(1, 2));
            PlayersPanel.add(p1Panel);
            PlayersPanel.add(p2Panel);
            add(PlayersPanel, BorderLayout.NORTH);
            
            notifPanel.setLayout(new BorderLayout());
            infoPane.setLineWrap(true);
            infoPane.setEditable(false);
            setDefaultInfo();
            notifPanel.add(infoPane, BorderLayout.CENTER);
            add(notifPanel, BorderLayout.CENTER);
            
            setStats();
            setMoves();
         
            
            okSwitch.addActionListener(new actionL());
	}
	
	public void setStats(){
            double hp;
            //initializes player's stats
            p1Sprite.setSize(56, 56);
            p1Sprite.setIcon(model.getPlayer().getActive().getSprite());
            p1NameLabel.setText(model.getPlayer().getActive().getName());
            p1LvlLabel.setText("Level " +  model.getPlayer().getActive().getLevel());
            if(model.getPlayer().getActive().getType2() != null)
                p1TypeLabel.setText(model.getPlayer().getActive().getType1().toUpperCase() + " / " + model.getPlayer().getActive().getType2().toUpperCase());
            else
                p1TypeLabel.setText(model.getPlayer().getActive().getType1().toUpperCase());
            hp = model.getPlayer().getActive().getCurrHP();
            if(hp<0)
                hp=0;
            p1HPLabel.setText("HP: " + hp + " / " + model.getPlayer().getActive().getMaxHP());
            
            //initializes opponent's stats
            p2Sprite.setSize(56,56);
            p2Sprite.setIcon(model.getOpponent().getActive().getSprite());
            p2NameLabel.setText(model.getOpponent().getActive().getName());
            p2LvlLabel.setText("Level " +  model.getOpponent().getActive().getLevel());
            if(model.getOpponent().getActive().getType2()!=null)
                p2TypeLabel.setText(model.getOpponent().getActive().getType1().toUpperCase() + " / " + model.getOpponent().getActive().getType2().toUpperCase());
            else
                p2TypeLabel.setText(model.getOpponent().getActive().getType1().toUpperCase());
            hp = model.getOpponent().getActive().getCurrHP();
            if(hp<0)
                hp=0;
            p2HPLabel.setText("HP: " + hp + " / " + model.getOpponent().getActive().getMaxHP());
	}
        
        public void setMoves(){
            movesList = new ButtonGroup();
            moves.clear();
            for(int i = 0; i<model.getPlayer().getActive().getNoOfMoves(); i++){
                moves.add(new JRadioButton(model.getPlayer().getActive().getMove(i)));
                p1InfoPanel.add(moves.get(i));
                movesList.add(moves.get(i));
            }
            moves.get(0).setSelected(true);
        }
	
	public void setDefaultInfo(){
            infoPane.setText("What will " + model.getPlayer().getActive().getName() + " do?");
        }
        
        
        
        private class actionL implements ActionListener{

            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==atkBtn){
                    int i, nDead = model.getPlayer().countFainted();
                    for(i=0; i<movesList.getButtonCount(); i++){
                        if(moves.get(i).isSelected()){
                            infoPane.setText(model.attack(i));
                            setStats();
                            if(model.getOpponent().countFainted()==model.getOpponent().countAnimon()){
                                dispose();
                                Sound.battleTheme.stop();
                                Sound.route1.loop();
                                if(model.getPlayer().getActive().getLevel()>=10 && !(model.getPlayer().getActive().checkEvolved()))
                                    model.getPlayer().getActive().evolve();
                            }
                            else if(model.getPlayer().countFainted()==4){
                                Sound.battleTheme.stop();
                                Sound.OWA.stop();
                                Sound.gameOver.loop();
                                JOptionPane.showMessageDialog(null, "You lose :(");
                                System.exit(0);
                            }
                            break;
                        }
                    }
                    if(i==movesList.getButtonCount())
                        JOptionPane.showMessageDialog(null, "Error, no move selected.");
                    if(model.getPlayer().countFainted()>nDead)
                        initializeSwitchable(2);
                }
                else if(e.getSource()==switchBtn){
                    initializeSwitchable(1);
                }
                else if(e.getSource()==runBtn){
                    int chance = randInt(1, 100);
                    if(model.getOpponent().getActive().getLevel()==448)
                        chance = randInt(100, 200);
                    if(chance <= 90){
                        JOptionPane.showMessageDialog(null, "Got away successfully!");
                        dispose();
                        Sound.battleTheme.stop();
                        Sound.OWA.stop();
                        Sound.route1.loop();
                        if(model.getPlayer().getActive().getLevel()>=10 && !(model.getPlayer().getActive().checkEvolved()))
                            model.getPlayer().getActive().evolve();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Failed to get away!");
                        infoPane.setText(model.opponentAttack());
                        if(model.getPlayer().getActive().getCurrHP()<=0 && model.getPlayer().countFainted()!=4){
                            initializeSwitchable(2);
                        }
                        else if(model.getPlayer().countFainted()==4){
                            Sound.battleTheme.stop();
                            Sound.OWA.stop();
                            Sound.gameOver.loop();
                            JOptionPane.showMessageDialog(null, "You lose :(");
                            System.exit(0);
                        }
                        setStats();
                    }
                }
                else if(e.getSource()==okSwitch){
                    double hp = model.getPlayer().getActive().getCurrHP();
                    for(int i=0; i<movesList.getButtonCount(); i++)
                        p1InfoPanel.remove(moves.get(i));
                    String newA = "";
                    for(int i=0; i<aniList.getButtonCount(); i++){
                        if(switchAnimon.get(i).isSelected()){
                            newA = switchAnimon.get(i).getText();
                            break;
                        }
                    }
                    for(int i=1; i<=model.getPlayer().countAnimon(); i++){
                        if(newA.equals(model.getPlayer().getAnimon(i).getName())){
                            model.getPlayer().switchAnimon(i-1);
                            switcher.dispose();
                            setStats();
                            setMoves();
                            setDefaultInfo();
                            break;
                        }
                    }
                    if(hp>0){
                        infoPane.setText("You sent out " + model.getPlayer().getActive().getName() + "!\n" + model.opponentAttack());
                        setStats();
                        if(model.getPlayer().getActive().getCurrHP()<=0)
                            initializeSwitchable(2);
                    }
                }
            }
            
        }
        
        public void initializeSwitchable(int type){
            switcher = new JFrame();
            switcher.setResizable(false);
            switcher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            switcher.setLocation(100, 75);
            sPanel = new JPanel();
            sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.Y_AXIS));
            int ctr=0;
            switchAnimon.clear();
            for(int i=1; i<=model.getPlayer().countAnimon(); i++){
                if(model.getPlayer().getAnimon(i).getCurrHP()>0 && 
                        model.getPlayer().getAnimon(i)!=model.getPlayer().getActive()){
                    switchAnimon.add(new JRadioButton(model.getPlayer().getAnimon(i).getName()));
                    switchAnimon.get(ctr).setAlignmentY(Component.CENTER_ALIGNMENT);
                    sPanel.add(switchAnimon.get(ctr));
                    aniList.add(switchAnimon.get(ctr));
                    ctr++;
                }
            }
            switchAnimon.get(0).setSelected(true);
            okSwitch.setAlignmentY(Component.CENTER_ALIGNMENT);
            sPanel.add(okSwitch);
            switcher.add(sPanel);
            switcher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            switcher.pack();
            switcher.setVisible(true);
        }
	/*
	 public void actionPerformed(ActionEvent event){
		//clears notification list
        if(event.getSource() == clearBtn){
            notifListModel.clear();
            initializeStats();
        }
		//displays moves list
        else if(event.getSource() == fightBtn){
            initializeList();
			browseAnimon.setVisible(false);
			switchBtn.setVisible(false);
			browseAtk.setVisible(true);
			atkBtn.setVisible(true);
            notifListModel.addElement("What will " + model.getPlayer().getActive().getName() + " do?");
        }
		//move selection, battle phase
        else if(event.getSource() == atkBtn){
            List<String> toNotif = new ArrayList<String>();
			
			toNotif = model.attack(atkList.getSelectedIndex(), false);
            
            for(int i = 0; i < toNotif.size(); i++)
                notifListModel.addElement(toNotif.get(i));
				
			browseAtk.setVisible(false);
			atkBtn.setVisible(false);
			
			initializeStats();
        }
		//displays available animon list
		else if(event.getSource() == animonBtn){
            initializeList();
			browseAtk.setVisible(false);
			atkBtn.setVisible(false);
			browseAnimon.setVisible(true);
			switchBtn.setVisible(true);
        }
		//animon selection, enemy only battle phase
		else if(event.getSource() == switchBtn){
			List<String> toNotif1 = new ArrayList<String>();
			List<String> toNotif2 = new ArrayList<String>();
			
			toNotif1 = model.switchActive(animonList.getSelectedIndex());
            
            for(int i = 0; i < toNotif1.size(); i++)
                notifListModel.addElement(toNotif1.get(i));
				
			browseAnimon.setVisible(false);
			switchBtn.setVisible(false);
			
			toNotif2 = model.attack(0, true);
			
			for(int i = 0; i < toNotif2.size(); i++)
                notifListModel.addElement(toNotif2.get(i));
			
			initializeStats();
        }
		//heals all animon
		else if(event.getSource() == healBtn){
                    //model.getPlayer().healAll();

                    notifListModel.addElement("Healed all Animon!");

                    initializeStats();
		}
    }
        */
    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;

        return randomNum;
    }

}