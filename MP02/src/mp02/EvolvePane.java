/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp02;

import java.awt.FlowLayout;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author MarcDominic
 */
public class EvolvePane extends JFrame{
    JLabel sprite = new JLabel();
    
    ImageIcon s1, s2;
    public EvolvePane(ImageIcon sprite1, ImageIcon sprite2){
        s1 = sprite1;
        s2 = sprite2;
        setLocation(580, 230);
        setResizable(false);
        setLayout(new FlowLayout());
        setSize(56, 120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sprite.setIcon(sprite1);
        add(sprite);
    }
    
    public void animation(final String name, final String newName, final Sound cry){
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>(){
            public ImageIcon doInBackground(){
                Sound.evolution.loop();
                int i=25;
                while(i>0){
                   try{
                       if(i==25) 
                           TimeUnit.MILLISECONDS.sleep(1000);
                        TimeUnit.MILLISECONDS.sleep(15*i);
                        sprite.setIcon(s1);
                        TimeUnit.MILLISECONDS.sleep(20*i);
                        sprite.setIcon(s2);
                        i--;
                    }
                    catch(InterruptedException e){
                        JOptionPane.showMessageDialog(null, "LOL");
                    }
                }
                Sound.evolution.stop();
                cry.play();
                try {
                    TimeUnit.MILLISECONDS.sleep(1250);
                } catch (InterruptedException ex) {
                    
                }
                Sound.victory.play();
                if(newName.charAt(0)=='A' || newName.charAt(0)=='E' || newName.charAt(0)=='I' || newName.charAt(0)=='O' || newName.charAt(0)=='U')
                    JOptionPane.showMessageDialog(null, "Congratulations! Your " + name + " evolved into an " + newName + "!");
                else 
                    JOptionPane.showMessageDialog(null, "Congratulations! Your " + name + " evolved into a " + newName + "!");
                return s2;
            }
            public void done(){
                Sound.victory.stop();
                Sound.route1.loop();
                dispose();
            }
        };
        worker.execute();
    }
}
