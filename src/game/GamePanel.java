package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import game.Bird;
import game.Controlls;
import game.Pipes;

public class GamePanel extends JPanel{

	static Bird bird;
	static ArrayList<Pipes> pipes;
	static int updatesPerPipeSpawn = 160, updates = 0, score = 0;
	static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;

	public enum GameStates{
		TITLE_SCREEN,
		PLAY_STATE,
		PAUSE_STATE
	}

	static GameStates currentState;

	public GamePanel(){
		currentState = GameStates.TITLE_SCREEN;
		bird = new Bird(100, 300);
		pipes = new ArrayList<Pipes>();

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.addKeyListener(new Controlls());
	}
	

	@Override
	public void paintComponent(Graphics g){

		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		g2d.setFont(new Font("Berlin Sans FB", Font.BOLD, 72));

		switch(currentState){
		case TITLE_SCREEN:

			drawTitleScreen(g2d);
			break;
		case PLAY_STATE:

			drawMainGame(g2d);
			break;

		case PAUSE_STATE:

			drawMainGame(g2d);

			g2d.setColor(new Color(0, 0, 0, 100));
			g2d.drawString("Paused", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
			
			break;

		}		
	}

	public void update(){

		switch(currentState){
		case PLAY_STATE:

			bird.update();
			updates++;

			switch(score){
			case 10:
				updatesPerPipeSpawn = 120;
				break;
			case 20:
				updatesPerPipeSpawn = 90;
				break;
			case 30:
				updatesPerPipeSpawn = 60;
				break;
			}

			if(updates >= updatesPerPipeSpawn){
				this.pipes.add(new Pipes(SCREEN_WIDTH));
				updates = 0;
			}

			for(int i = 0; i < pipes.size(); i++){
				if(pipes.get(i).topPipe.x + pipes.get(i).WIDTH <= 0){
					score ++;
					pipes.remove(i);
				}
				pipes.get(i).update();
			}

			break;
		}
	}
	
	public static void lose(){

		bird.hitbox.y = 300;
		bird.acceleration = -10;
		pipes.clear();
		updates = 0;
		score = 0;
		updatesPerPipeSpawn = 120;

	}

	private void drawTitleScreen(Graphics2D g2d){
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g2d.setColor(new Color(0, 0, 0, 100));
		g2d.drawString("Floppy Bird", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
	}

	private void drawMainGame(Graphics2D g2d){
		g2d.setColor(Color.CYAN);
		g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); //sky

		bird.draw(g2d);
		for(int i = 0; i < pipes.size(); i++){
			pipes.get(i).draw(g2d);
		}

		g2d.setColor(new Color(0, 0, 0, 100));			//score
		g2d.drawString(String.valueOf(score), 5, SCREEN_HEIGHT - 5);
	}

}