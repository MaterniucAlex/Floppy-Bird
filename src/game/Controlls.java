package game;

import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Controlls implements KeyListener{

	AudioInputStream audioStream;
	Clip flySound, coinDing;

	public Controlls(){
	
		try{

		flySound = AudioSystem.getClip();
		coinDing = AudioSystem.getClip();	
		
		audioStream = AudioSystem.getAudioInputStream(new File("./res/sounds/fss.wav").getAbsoluteFile());
		flySound.open(audioStream);
		audioStream = AudioSystem.getAudioInputStream(new File("./res/sounds/surubCalorifer.wav").getAbsoluteFile());
		coinDing.open(audioStream);

		} catch (Exception e) {System.out.println(e.getMessage());}
	}

	private void playFlySound(){

		flySound.stop();
		flySound.setMicrosecondPosition(0);
		flySound.start();
	}

	private void playCoinDing(){

		coinDing.stop();
		coinDing.setMicrosecondPosition(0);
		coinDing.start();
	}

	@Override
	public void keyPressed(KeyEvent e){
		switch(GamePanel.currentState){
		case PLAY_STATE:
			switch(e.getKeyChar()){
			case ' ':
			case 'w':
			case 'W':
				GamePanel.bird.acceleration = -8;
				playFlySound();
				break;
			case 'p':
			case 'P':
				GamePanel.currentState = GamePanel.GameStates.PAUSE_STATE;
				break;
			}
			break;
		case LOSE_STATE:
			switch(e.getKeyChar()){
			case ' ':
			case 'w':
			case 'W':
				GamePanel.currentState = GamePanel.GameStates.PLAY_STATE;
				GamePanel.restart();
				playCoinDing();
			}
			break;
		case TITLE_SCREEN:
		case PAUSE_STATE:
			switch(e.getKeyChar()){
			case ' ':
			case 'w':
			case 'W':
				GamePanel.currentState = GamePanel.GameStates.PLAY_STATE;
				playCoinDing();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		
	}

	@Override
	public void keyTyped(KeyEvent e){

	}
}