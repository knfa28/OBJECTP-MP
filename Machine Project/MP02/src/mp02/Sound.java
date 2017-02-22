/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp02;

import java.io.*;

import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author MarcDominic
 */
public class Sound {
        public static Sound route1 = new Sound("route1.wav");
        public static Sound battleTheme = new Sound("battle.wav");
        public static Sound victory = new Sound("Victory.wav");
        public static Sound evolution = new Sound("Evolution.wav");
        public static Sound alert = new Sound("Alert.wav");
        public static Sound gameOver = new Sound("GameOver.wav");
        public static Sound OWA = new Sound("OWA.wav");
    
        private Clip clip;
        public Sound(String fileName) {
            // specify the sound to play
            // (assuming the sound can be played by the audio system)
            // from a wave File
            fileName = "Music/" + fileName;
            try {
                File file = new File(fileName);
                if (file.exists()) {
                    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                 // load the sound into memory (a Clip)
                    clip = AudioSystem.getClip();
                    clip.open(sound);
                }
                else {
                    throw new RuntimeException("Sound: file not found: " + fileName);
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Malformed URL: " + e);
            }
            catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Unsupported Audio File: " + e);
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Input/Output Error: " + e);
            }
            catch (LineUnavailableException e) {
                e.printStackTrace();
                throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
            }

        // play, stop, loop the sound clip
        }
        public void play(){
            clip.setFramePosition(0);  // Must always rewind!
            clip.start();
        }
        public void loop(){
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        public void stop(){
            clip.stop();
        }
       
    }
