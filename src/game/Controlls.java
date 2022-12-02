package game;

import java.awt.event.*;

public class Controlls implements KeyListener{
	
	@Override
	public void keyPressed(KeyEvent e){
		switch(GamePanel.currentState){
		case PLAY_STATE:
			switch(e.getKeyChar()){
			case ' ':
			case 'w':
			case 'W':
				GamePanel.bird.acceleration = -8;
				break;
			case 'p':
			case 'P':
				GamePanel.currentState = GamePanel.GameStates.PAUSE_STATE;
				break;
			}
		case TITLE_SCREEN:
		case PAUSE_STATE:
			switch(e.getKeyChar()){
			case ' ':
			case 'w':
			case 'W':
				GamePanel.currentState = GamePanel.GameStates.PLAY_STATE;
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		
	}

	@Override
	public void keyTyped(KeyEvent e){

	}
}