package game;

import java.awt.*;

public class Bird{

	private final int WIDTH = 60, HEIGHT = 50;
	float acceleration = -10;
	Rectangle hitbox;

	public Bird(int x, int y){
		this.hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public void draw(Graphics2D g2d){

		g2d.setColor(Color.YELLOW);
		g2d.fillRect(this.hitbox.x, this.hitbox.y, WIDTH, HEIGHT);

	}

	public void update(){
		if(this.hitbox.y <= 0){
			this.hitbox.y += 1;
			acceleration = 0;
		}	
		if(this.hitbox.y + this.HEIGHT <= 600){
			this.hitbox.y += (int)acceleration;
			acceleration += 0.335f;
		} else 
			GamePanel.lose();

	}
}