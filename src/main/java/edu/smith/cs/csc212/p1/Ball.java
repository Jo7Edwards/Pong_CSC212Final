package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import me.jjfoley.gfx.GFX;

public class Ball {
	
	int x;
	double y;
	Color color;
	boolean travelR = true;
	boolean travelUp = true;
	boolean inPaddle = false;
	
	int size = 12;
	
	double speedx;
	double speedy;
	int speed;
	
	Paddle paddleL;
	Paddle paddleR;
	
	
	Random rand = new Random();
	int DestX = 500;
	int DestY = rand.nextInt(500);
	double newthing = rand.nextDouble();
	
	public Ball(Color color, int x, int y, Paddle Rpaddle, Paddle Lpaddle) {
		this.color = color;
		this.y = y;
		this.x = x; 
		
		this.speed = 1;
		
		this.speedx = 2;
		this.speedy = 0.8;
		
		this.paddleR = Rpaddle;
		this.paddleL = Lpaddle;
		
		//int subY = this.y;
		//int distY = DestY - subY;
		//this.subY = distY;
		
	}
	
	public void draw(Graphics2D g) { 
		MoveBall(g);
		
		g.setColor(color);
		
		Shape ball = new Ellipse2D.Double(x, y, size, size);
		
		g.fill(ball);
		g.draw(ball);
	}
	
	public void MoveBall(Graphics2D g) {
		int speed = 1;
		
		this.inPaddle = false; 
		
		
		/*x needs to always be moving +1 or -1, but Y can have random placement 
		//int distX = this.DestX - this.x;
		
		int distY = this.DestY - this.y;//upward downward or even 
		if (distY >0) {
			travelUp = true;
		} else if (distY <=0) {
			travelUp = false; 
		}
		
		int randNum = rand.nextInt(3); //exclusive of value put there 
		
		//move horizontally 
		if (travelR == true) {
			this.x += speed;
		} else {
			this.x -= speed;
		}
		
		//move vertically 
		//inc y vertically up
		if (travelUp == true) {
			this.y += speed;
		//Then if its exactly in line with destination
		} else if (distY == 0) {
		//continue? 
			
		} else if (travelUp == false) {
			this.y -=speed;
		}
		//If randNum equals 2, then choose whether to shoot towards CPU or player*/
		
		//double distY = DestY / 200;
		
		//this.speedy = distY;
		//double moveY = this.subY/100; 
		//speedy = moveY;
		
		//this.speedy = 0.8;
		//------------------------------------------------------------------------------------
		//this.x += this.speedx;
		//this.y += this.speedy;
		
		
		this.x += this.speedx;
		this.y += this.speedy;
		
		
		
		//check hitting top or bottom wall
		if (this.y <= 0 || this.y >= 500) {
			
			speedy = 0 - speedy; //change to opposite direction if hit top
		} 
		
		//check if hitting left or right wall 
		if (this.x <=0 || this.x >= 500) {
			//this.DestY = rand.nextInt(500);
			
			speedx = 0 - speedx;
		}
		
		//check if hitting a paddle 
		//If the ball is traveling to the right, should only hit the right paddle
		if (speedx > 0) {
			checkInPaddle(paddleR);
		} else {
			checkInPaddle(paddleL);
		}
		
		
		
	}
	
	/**
	 * changes the destination point 
	 */
	public void changeDest() {
		
		if (travelR == true) {
			this.DestX = 500;
		} else {
			this.DestX = 0;
		}
		
		this.DestY = rand.nextInt(500);
	}
	
	/**
	 * Figures out the angle and changes this.speedx and speedy accordingly 
	 */
	public void findAngle() {
		float difX = DestX - this.x;
		float difY = (float) (DestY - this.y);
		
		double theta = Math.atan2(difY, difX);
		
		double movex = speed * Math.cos(theta);
		double movey = speed * Math.sin(theta);
		
		this.speedx = movex;
		this.speedy = movey;
	}
	
	public void checkInPaddle(Paddle paddle) {
		if (this.travelR ==true) {
			if ( paddle.x <= this.x && this.x < paddle.x + paddle.width) {
				if (paddle.y <= this.y && this.y < paddle.y + paddle.height) {
					speedx = 0- speedx;
				}
			}
		} else if (this.travelR == false) {
			if (this.x <= paddle.x+paddle.width && paddle.x < this.x) {
				if (paddle.y <= this.y && this.y < paddle.y + paddle.height) {
					speedx = 0-speedx;
				}
			}
		}
		
		
	}
}
