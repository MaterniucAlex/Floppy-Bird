package game;

import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Pipes{

	final int WIDTH = GamePanel.SCREEN_WIDTH / 10, HEIGHT = GamePanel.SCREEN_HEIGHT / 2;
	int x, height, scoreGiven;

	AudioInputStream ais;
	Clip deathSound;

	BufferedImage pipeUp, pipeDown;
	Rectangle topPipe, bottomPipe;
	Random rand = new Random();

	public Pipes(int x){

		this.x = x;
		this.height = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.SCREEN_HEIGHT / 6 * (rand.nextInt(3) - 1));
		this.scoreGiven = 1;

		this.topPipe = new Rectangle(x, height - HEIGHT - 100, WIDTH, HEIGHT);
		this.bottomPipe = new Rectangle(x, height + 100, WIDTH, HEIGHT);

		try{
			pipeUp = ImageIO.read(new FileInputStream("./res/img/pipe_up.png"));
			pipeDown = ImageIO.read(new FileInputStream("./res/img/pipe_down.png"));

			this.ais = AudioSystem.getAudioInputStream(new File("./res/sounds/spoc.wav"));
			this.deathSound = AudioSystem.getClip();
			deathSound.open(ais);
		} catch (Exception e) { System.out.println(e.getMessage());}
	}
	
	public void draw(Graphics2D g2d){
		
		g2d.drawImage(pipeDown, this.bottomPipe.x, this.bottomPipe.y, WIDTH, HEIGHT, null);
		g2d.drawImage(pipeUp, this.topPipe.x, this.topPipe.y, WIDTH, HEIGHT, null);
	}

	public void update(){
		this.topPipe.x -= 2;
		this.bottomPipe.x -= 2;
		if(this.topPipe.x + WIDTH / 2 <= GamePanel.bird.hitbox.x + GamePanel.bird.hitbox.width / 2){
			GamePanel.score += this.scoreGiven;
			this.scoreGiven = 0;
		}
		if(this.topPipe.intersects(GamePanel.bird.hitbox) || this.bottomPipe.intersects(GamePanel.bird.hitbox)){
			GamePanel.currentState = GamePanel.GameStates.LOSE_STATE;
			playDeathSound();
		}
	}

	public void playDeathSound(){
		deathSound.stop();
		deathSound.setMicrosecondPosition(0);
		deathSound.start();
	}
}