package game;

import javax.swing.*;
import game.GamePanel;

public class GameFrame extends JFrame implements Runnable{

	GamePanel panel;
	Thread gameLoop;

	final short FPS = 60;
	long lastCheck = System.nanoTime();

	public static void main(String[] args) {
		
		new GameFrame("Floppy Bird");
	}

	public GameFrame(String title){
		
		panel = new GamePanel();
		gameLoop = new Thread(this);

		this.setTitle(title);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(panel);
		panel.setFocusable(true);
		
		this.setIconImage(panel.bird.birdImage);
		this.pack();

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		gameLoop.run();
	}

	@Override
	public void run(){
		while(true){
			if(System.nanoTime() >= lastCheck + (1000000000 / FPS)){
				panel.update();
				panel.repaint();
				lastCheck = System.nanoTime();
			}
		}
	}

}