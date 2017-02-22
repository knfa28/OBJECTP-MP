/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp02;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author MarcDominic
 */
public class AnimonMap extends JFrame{
    private JLabel[][] labels;
    private JLabel activeLabel;
    private int x, y;
    private final int xMAX, yMAX;
    private ImageIcon grass = new ImageIcon("Images/AnimonSprites/MaximumBlackGrass.png");
    private ImageIcon sUp = new ImageIcon("Images/AnimonSprites/standUp.png");
    private ImageIcon wUp1 = new ImageIcon("Images/AnimonSprites/walkUp1.png");
    private ImageIcon wUp2 = new ImageIcon("Images/AnimonSprites/walkUp2.png");
    private ImageIcon sDown = new ImageIcon("Images/AnimonSprites/standDown.png");
    private ImageIcon wDown1 = new ImageIcon("Images/AnimonSprites/walkDown1.png");
    private ImageIcon wDown2 = new ImageIcon("Images/AnimonSprites/walkDown2.png");
    private ImageIcon sLeft = new ImageIcon("Images/AnimonSprites/standLeft.png");
    private ImageIcon wLeft = new ImageIcon("Images/AnimonSprites/walkLeft.png");
    private ImageIcon sRight = new ImageIcon("Images/AnimonSprites/standRight.png");
    private ImageIcon wRight = new ImageIcon("Images/AnimonSprites/walkRight.png");
    private ImageIcon enemy = new ImageIcon("Images/AnimonSprites/enemy.png");
    private AnimonWorld aWorld;
    private int encounterBuffer=5;
    private String currSprite, cheatCode = "";
    private Trainer player;
    private BattleView wBattle;
    
    public AnimonMap(int x, int y){
        super("Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ImageIcon iconLogo = new ImageIcon("Images/TrainerSprite.png");
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        aWorld = new AnimonWorld(x, y);
        setSize(x*30+17, y*31+20);
        //setResizable(false);
        
        xMAX = x;
        yMAX = y;
        labels = new JLabel[yMAX][xMAX];
        int i, j;
        for(i=0; i<yMAX; i++)
            for(j=0; j<xMAX; j++){
                labels[i][j] = new JLabel();
                labels[i][j].setForeground(new Color(195, 195, 195));
                labels[i][j].setIcon(grass);
                add(labels[i][j]);
            }
        
        KeyL k = new KeyL();
        addKeyListener(k);
        Sound.route1.loop();
    }
    
    public void WalaLang(){
        ;
    }
    
    
    
    private class KeyL implements KeyListener {
        boolean moved = false;
        public void keyTyped(KeyEvent e) {
            WalaLang();
        }
        
        public void keyPressed(KeyEvent e) {
            moved = false;
            int keyCode = e.getKeyCode();
            switch(keyCode) {
                case KeyEvent.VK_A:
                    cheatCode += "A";
                    break;
                case KeyEvent.VK_B:
                    cheatCode += "B";
                    break;
                case KeyEvent.VK_C:
                    cheatCode += "C";
                    break;
                case KeyEvent.VK_D:
                    displayAnimon dPane = new displayAnimon((TrainerSprite)aWorld.getElementAt(x, y));
                    dPane.setVisible(true);
                    break;
                case KeyEvent.VK_E:
                    cheatCode += "E";
                    break;
                case KeyEvent.VK_F:
                    cheatCode += "F";
                    break;
                case KeyEvent.VK_G:
                    cheatCode += "G";
                    break;
                case KeyEvent.VK_H:
                    cheatCode += "H";
                    break;
                case KeyEvent.VK_I:
                    cheatCode += "I";
                    break;
                case KeyEvent.VK_J:
                    cheatCode += "J";
                    break;
                case KeyEvent.VK_K:
                    cheatCode += "K";
                    break;
                case KeyEvent.VK_L:
                    cheatCode += "L";
                    break;
                case KeyEvent.VK_M:
                    cheatCode += "M";
                    break;
                case KeyEvent.VK_N:
                    cheatCode += "N";
                    break;
                case KeyEvent.VK_O:
                    cheatCode += "O";
                    break;
                case KeyEvent.VK_P:
                    cheatCode += "P";
                    break;
                case KeyEvent.VK_Q:
                    cheatCode += "Q";
                    break;
                case KeyEvent.VK_R:
                    cheatCode += "R";
                    break;
                case KeyEvent.VK_S:
                    cheatCode += "S";
                    break;
                case KeyEvent.VK_T:
                    cheatCode += "T";
                    break;
                case KeyEvent.VK_U:
                    cheatCode += "U";
                    break;
                case KeyEvent.VK_V:
                    cheatCode += "V";
                    break;
                case KeyEvent.VK_W:
                    cheatCode += "W";
                    break;
                case KeyEvent.VK_X:
                    cheatCode += "X";
                    break;
                case KeyEvent.VK_Y:
                    cheatCode += "Y";
                    break;
                case KeyEvent.VK_Z:
                    cheatCode += "Z";
                    break;
                case KeyEvent.VK_UP:
                    encounterBuffer++;
                    if(y!=0){
                        moved = true;
                        if(aWorld.moveElement((MovableWorldElement)aWorld.getElementAt(x, y), 'u')){
                            y--;
                            activeLabel.setIcon(grass);
                            activeLabel = labels[y][x];
                            if(currSprite.equals("su")){
                                currSprite = "wu1";
                                activeLabel.setIcon(wUp1);
                            }
                            else if(currSprite.equals("wu1")){
                                currSprite = "wu2";
                                activeLabel.setIcon(wUp2);
                            }
                            else if(currSprite.equals("wu2")){
                                currSprite = "wu1";
                                activeLabel.setIcon(wUp1);
                            }
                        }
                        else
                            currSprite = "su";
                    }
                    if(currSprite.equals("su"))
                        activeLabel.setIcon(sUp);
                    break;
                case KeyEvent.VK_DOWN:
                    encounterBuffer++;
                    if(y!=yMAX-1){
                        moved = true;
                        if(aWorld.moveElement((MovableWorldElement)aWorld.getElementAt(x, y), 'd')){
                            y++;
                            activeLabel.setIcon(grass);
                            activeLabel = labels[y][x];
                            if(currSprite.equals("sd")){
                                currSprite = "wd1";
                                activeLabel.setIcon(wDown1);
                            }
                            else if(currSprite.equals("wd1")){
                                currSprite = "wd2";
                                activeLabel.setIcon(wDown2);
                            }
                            else if(currSprite.equals("wd2")){
                                currSprite = "wd1";
                                activeLabel.setIcon(wDown1);
                            }
                        }
                        else
                            currSprite = "sd";
                    }
                    if(currSprite.equals("sd"))
                        activeLabel.setIcon(sDown);
                    break;
                case KeyEvent.VK_LEFT:
                    encounterBuffer++;
                    if(x!=0){
                        moved = true;
                        if(aWorld.moveElement((MovableWorldElement)aWorld.getElementAt(x, y), 'l')){
                            x--;
                            activeLabel.setIcon(grass);
                            activeLabel = labels[y][x];
                            if(currSprite.equals("sl")){
                                currSprite = "wl";
                                activeLabel.setIcon(wLeft);
                            }
                            else if(currSprite.equals("wl")){
                                currSprite = "sl";
                                activeLabel.setIcon(sLeft);
                            }
                        }
                        else
                            currSprite = "sl";
                    }
                    if(currSprite.equals("sl"))
                        activeLabel.setIcon(sLeft);
                    break;
                case KeyEvent.VK_RIGHT:
                    encounterBuffer++;
                    if(x!=xMAX-1){
                        moved = true;
                        if(aWorld.moveElement((MovableWorldElement)aWorld.getElementAt(x, y), 'r')){
                            x++;
                            activeLabel.setIcon(grass);
                            activeLabel = labels[y][x];
                            if(currSprite.equals("sr")){
                                currSprite = "wr";
                                activeLabel.setIcon(wRight);
                            }
                            else if(currSprite.equals("wr")){
                                currSprite = "sr";
                                activeLabel.setIcon(sRight);
                            }
                        }
                        else
                            currSprite = "sr";
                    }
                    if(currSprite.equals("sr"))
                        activeLabel.setIcon(sRight);
                    break;
            }
            int rBattle = randInt(1, 100);
            if(encounterBuffer>=5 && moved && rBattle <= 6){
                encounterBuffer=0;
                Animon opponent;
                if(rBattle<=2)
                    opponent = new Animon(5);
                else if(rBattle<=4)
                    opponent = new Animon(6);
                else
                    opponent = new Animon(7);
                opponent.gainExp((int)Math.pow(player.getActive().getLevel(), 3));
                Sound.route1.stop();
                Sound.battleTheme.loop();
                JOptionPane.showMessageDialog(null, "A wild " + opponent.getName() + " appeared!");
                opponent.getCry().play();
                wBattle = new BattleView(new Battle(player, opponent));
                wBattle.setVisible(true);
                
            }
            if(cheatCode.contains("EXITGAME")){
                JOptionPane.showMessageDialog(null, "Thank you for playing!");
                System.exit(0);
                cheatCode = "";
            }
            else if(cheatCode.contains("GAMAB")){
                Sound.route1.stop();
                Sound.battleTheme.loop();
                JOptionPane.showMessageDialog(null, "A wild Gamab appeared!");
                System.exit(1);
            }
            else if(cheatCode.contains("ELIXIR")){
                JOptionPane.showMessageDialog(null, "All Animon healed!");
                player.getAnimon(1).addHP(player.getAnimon(1).getMaxHP());
                player.getAnimon(2).addHP(player.getAnimon(2).getMaxHP());
                player.getAnimon(3).addHP(player.getAnimon(3).getMaxHP());
                player.getAnimon(4).addHP(player.getAnimon(4).getMaxHP());

                cheatCode = "";
            }
            else if(cheatCode.contains("EXEGGUTOR")){
                player.getAnimon(1).addHP(player.getAnimon(1).getMaxHP());
                player.getAnimon(1).gainExp(999);
                player.getAnimon(1).evolve();
                cheatCode = "";
            }
            else if(cheatCode.contains("WEEZING")){
                player.getAnimon(2).addHP(player.getAnimon(2).getMaxHP());
                player.getAnimon(2).gainExp(999);
                player.getAnimon(2).evolve();
                cheatCode = "";
            }
            else if(cheatCode.contains("ARCANINE")){
                player.getAnimon(3).addHP(player.getAnimon(3).getMaxHP());
                player.getAnimon(3).gainExp(999);
                player.getAnimon(3).evolve();
                cheatCode = "";
            }
            else if(cheatCode.contains("TENTACRUEL")){
                player.getAnimon(4).addHP(player.getAnimon(4).getMaxHP());
                player.getAnimon(4).gainExp(999);
                player.getAnimon(4).evolve();
                cheatCode = "";
            }
            else if(cheatCode.contains("ALLHAILTHEMIGHTYPOLIWAG")){
                Animon mightyPoliwag = new Animon(7);
                mightyPoliwag.gainExp(90000000);
                Sound.route1.stop();
                Sound.OWA.loop();
                JOptionPane.showMessageDialog(null, "The Mighty Poliwag appeared!");
                mightyPoliwag.getCry().play();
                wBattle = new BattleView(new Battle(player, mightyPoliwag));
                wBattle.setVisible(true);
                cheatCode = "";
            }
        }

        public void keyReleased(KeyEvent e) {
            WalaLang();
        }  
    }
    
    public void addTrainer(TrainerSprite t, String type){
        if(aWorld.addElement(t)){
            if(type.equals("AI"))
                labels[t.y][t.x].setIcon(enemy);
            else if(type.equals("Player")){
                this.x = t.x;
                this.y = t.y;
                labels[t.y][t.x].setIcon(sDown);
                currSprite = "sd";
                activeLabel = labels[t.y][t.x];
                player = t.getModel();
            }
        }
    }
    
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;

        return randomNum;
    }
}
