package game;

import java.awt.event.*;

public class Controlls implements KeyListener{
	
	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyChar()){
		case 'w':
			GamePanel.bird.acceleration = -8;
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