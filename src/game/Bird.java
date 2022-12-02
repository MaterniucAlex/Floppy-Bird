package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;


public class Bird{

	private final int WIDTH = 60, HEIGHT = 50;
	float acceleration = -10;
	Rectangle hitbox;
	BufferedImage birdImage;

	public Bird(int x, int y){
		this.hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
		try{
			birdImage = ImageIO.read(new FileInputStream("./res/bird.png"));
		} catch (Exception e) { System.out.println(e.getMessage());}
	}
	
	public void draw(Graphics2D g2d){

		g2d.drawImage(birdImage, this.hitbox.x, this.hitbox.y, WIDTH, HEIGHT, null);

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