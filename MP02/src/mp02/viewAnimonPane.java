/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp02;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author MarcDominic
 */
public class viewAnimonPane extends JPanel{
    private JLabel sprite = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel levelLabel = new JLabel();
    private JLabel hpLabel = new JLabel();
    private JLabel typeName = new JLabel("Type: ");
    private JLabel typeLabel = new JLabel();
    private JLabel movesLabel = new JLabel("Moves: ");
    private JLabel[] moves;
    private Sound cry;
    private Animon a;
    
    public viewAnimonPane(Animon a){
        this.a = a;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(60, 300);
        sprite.setIcon(a.getSprite());
        add(sprite);
        nameLabel.setText("Name: " + a.getName());
        add(nameLabel);
        levelLabel.setText("Level " + a.getLevel());
        add(levelLabel);
        double hp = a.getCurrHP();
        if(hp<0) hp=0;
        hpLabel.setText("HP: " + hp + " / " + a.getMaxHP());
        add(hpLabel);
        add(typeName);
        if(a.getType2()!=null)
            typeLabel.setText(a.getType1().toUpperCase() + " / " + a.getType2().toUpperCase());
        else
            typeLabel.setText(a.getType1().toUpperCase());
        add(typeLabel);
        add(movesLabel);
        
        moves = new JLabel[a.getNoOfMoves()];
        for(int i=0; i<a.getNoOfMoves(); i++){
            moves[i] = new JLabel(a.getMove(i));
            add(moves[i]);
        }
        
        cry = a.getCry();
        sprite.addMouseListener(new MouseL());
    }
    
    private class MouseL implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            cry.play();
        }
        
        public void mousePressed(MouseEvent e) {
            
        }

        public void mouseReleased(MouseEvent e) {
            
        }

        public void mouseEntered(MouseEvent e) {
         
        }

        public void mouseExited(MouseEvent e) {
        
        }
        
    }
}
