package game;

import java.awt.*;
import java.util.Random;

public class Pipes{

	final int WIDTH = GamePanel.SCREEN_WIDTH / 10;
	int x, height;

	Rectangle topPipe, bottomPipe;
	Random rand = new Random();
	public Pipes(int x){

		this.x = x;
		this.height = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.SCREEN_HEIGHT / 6 * (rand.nextInt(3) - 1));

		this.topPipe = new Rectangle(x, 0, WIDTH, height - 100);
		this.bottomPipe = new Rectangle(x, height + 100, WIDTH, GamePanel.SCREEN_HEIGHT);
	}
	
	public void draw(Graphics2D g2d){
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(this.topPipe.x, this.topPipe.y, WIDTH, this.topPipe.height);
		g2d.fillRect(this.bottomPipe.x, this.bottomPipe.y, WIDTH, this.bottomPipe.height);
	}

	public void update(){
		this.topPipe.x -= 2;
		this.bottomPipe.x -= 2;
		if(this.topPipe.intersects(GamePanel.bird.hitbox) || this.bottomPipe.intersects(GamePanel.bird.hitbox)){
			GamePanel.lose();
		}
	}
}