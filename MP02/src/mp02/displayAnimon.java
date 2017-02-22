/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp02;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author MarcDominic
 */
public class displayAnimon extends JFrame{
    public displayAnimon(TrainerSprite tSprite){
        Trainer t = tSprite.getModel();
        setLocation(70,70);
        setSize(450, 300);
        setResizable(false);
        JPanel dPanel = new JPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        dPanel.setLayout(new GridLayout(1, 4));
        dPanel.add(new viewAnimonPane(t.getAnimon(1)));
        dPanel.add(new viewAnimonPane(t.getAnimon(2)));
        dPanel.add(new viewAnimonPane(t.getAnimon(3)));
        dPanel.add(new viewAnimonPane(t.getAnimon(4)));
        add(dPanel);
    }
}
