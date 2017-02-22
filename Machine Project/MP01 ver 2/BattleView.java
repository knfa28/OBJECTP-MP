import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class BattleView extends JFrame implements ActionListener{
	private Battle model;
	
	//left panel
	private JPanel leftPanel = new JPanel();
	
	//animon details panel
	private JPanel battlePanel = new JPanel();
	
	//p1 details panel
	private JPanel p1Panel = new JPanel();
	private JLabel p1NameLabel = new JLabel("");
	private JLabel p1LvlLabel = new JLabel("lvl: ");
	private JLabel p1TypeLabel = new JLabel("Type(s): ");
	private JLabel p1HPLabel = new JLabel("HP: ");
	
	//p2 details panel
	private JPanel p2Panel =  new JPanel();
	private JLabel p2NameLabel = new JLabel("");
	private JLabel p2LvlLabel = new JLabel("lvl: ");
	private JLabel p2TypeLabel = new JLabel("Type(s): ");
	private JLabel p2HPLabel = new JLabel("HP: ");
	
	//notification panel
	private JPanel notifPanel = new JPanel();
	private JButton clearBtn = new JButton("Clear");
	private DefaultListModel<String> notifListModel = new DefaultListModel<String>();
	private JList notifList = new JList(notifListModel);
	private JScrollPane browseNotif = new JScrollPane(notifList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	//right panel
	private JPanel rightPanel = new JPanel();
	
	//menu panel
	private JPanel menuPanel = new JPanel();
	private JButton fightBtn = new JButton("FIGHT");
	private JButton animonBtn = new JButton("ANIMON");
	private JButton healBtn = new JButton("HEAL");
	private JButton runBtn = new JButton("RUN");
	
	//list panel
	private JPanel listPanel = new JPanel();
	
	//attack list
	private DefaultListModel<String> atkListModel = new DefaultListModel<String>();
	private JList atkList = new JList(atkListModel);
	private JScrollPane browseAtk = new JScrollPane(atkList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton atkBtn = new JButton("Attack");
	
	//animon list
	private DefaultListModel<String> animonListModel = new DefaultListModel<String>();
	private JList animonList = new JList(animonListModel);
	private JScrollPane browseAnimon = new JScrollPane(animonList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton switchBtn = new JButton("Switch");

	public BattleView(Battle battle){
		this.model = battle;
		
		if(model.isVsTrainer())
			setTitle(model.getPlayer().getName() +" vs. Trainer "+ model.getOpponent().getName());
		else
			setTitle(model.getPlayer().getName() +" vs. Wild "+ model.getOpponent().getActive().getName());
		
		setSize(652, 350);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//player stats
		p1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		p1Panel.setLayout(new GridLayout(4, 1));
		p1Panel.add(p1NameLabel);
		p1Panel.add(p1LvlLabel);
		p1Panel.add(p1TypeLabel);
		p1Panel.add(p1HPLabel);
		
		//opponent stats
		p2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		p2Panel.setLayout(new GridLayout(4, 1));
		p2Panel.add(p2NameLabel);
		p2Panel.add(p2LvlLabel);
		p2Panel.add(p2TypeLabel);
		p2Panel.add(p2HPLabel);
		
		//battle panel
		battlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		battlePanel.setLayout(new GridLayout(2, 2));
		battlePanel.add(p2Panel);
		battlePanel.add(p1Panel);
		
		//notification list, clear button
		notifPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		clearBtn.addActionListener(this);
		notifPanel.add(clearBtn, BorderLayout.NORTH);
		notifPanel.add(browseNotif, BorderLayout.SOUTH);
		
		//left panel
		leftPanel.add(battlePanel, BorderLayout.NORTH);
		leftPanel.add(notifPanel, BorderLayout.SOUTH);
		
		//menu panel
		menuPanel.setLayout(new GridLayout(2, 2));
		fightBtn.addActionListener(this);
		animonBtn.addActionListener(this);
		healBtn.addActionListener(this);
		runBtn.addActionListener(this);
		menuPanel.add(fightBtn);
		menuPanel.add(animonBtn);
		menuPanel.add(healBtn);
		menuPanel.add(runBtn);
		
		//moves list
		browseAtk.setPreferredSize(new Dimension(163,200));
		browseAtk.setVisible(false);
		atkBtn.setVisible(false);
		atkBtn.addActionListener(this);
		listPanel.add(browseAtk);
		listPanel.add(atkBtn);
		
		//animon list
		browseAnimon.setPreferredSize(new Dimension(163,200));
		browseAnimon.setVisible(false);
		switchBtn.setVisible(false);
		switchBtn.addActionListener(this);
		listPanel.add(browseAnimon);
		listPanel.add(switchBtn);
		
		//right panel
		rightPanel.add(listPanel);
		rightPanel.add(menuPanel);
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		
		initializeStats();
	}
	
	public void initializeStats(){
		//initializes player's stats
		p1NameLabel.setText(model.getPlayer().getActive().getName());
        p1LvlLabel.setText("lvl    : " +  model.getPlayer().getActive().getLvl());
		if(model.getPlayer().getActive().isEvolved())
			p1TypeLabel.setText("Type(s): " + model.getPlayer().getActive().getType1() + " / " + model.getPlayer().getActive().getType2());
		else
			p1TypeLabel.setText("Type(s): " + model.getPlayer().getActive().getType1());
        p1HPLabel.setText("HP     : " + model.getPlayer().getActive().getCurrHP() + " / " + model.getPlayer().getActive().getMaxHP());
        
		//initializes opponent's stats
		p2NameLabel.setText(model.getOpponent().getActive().getName());
        p2LvlLabel.setText("lvl    : " +  model.getOpponent().getActive().getLvl());
		if(model.getOpponent().getActive().isEvolved())
			p2TypeLabel.setText("Type(s): " + model.getOpponent().getActive().getType1() + " / " + model.getOpponent().getActive().getType2());
		else
			p2TypeLabel.setText("Type(s): " + model.getOpponent().getActive().getType1());
        p2HPLabel.setText("HP     : " + model.getOpponent().getActive().getCurrHP() + " / " + model.getOpponent().getActive().getMaxHP());
	}
	
	public void initializeList(){
		int i;
		
		//clears current contents
		atkListModel.clear();
		animonListModel.clear();
		
		//initializes moves list
		for(i = 0; i < model.getPlayer().getActive().getMoveCnt(); i++)
            atkListModel.addElement(model.getPlayer().getActive().getMoveDetails(i));
		
		//initializes animon list
		for(i = 0; i < model.getPlayer().countAnimon(); i++)
			animonListModel.addElement(model.getPlayer().getAvailable(i).getAnimonDetails());
	}
	
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
			notifListModel.addElement("Send out which Animon?");
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
			model.getPlayer().healAll();
			
			notifListModel.addElement("Healed all Animon!");
			
			initializeStats();
		}
    }
}