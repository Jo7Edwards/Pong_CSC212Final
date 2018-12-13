package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.jjfoley.gfx.GFX;

/**
 * This makes a ball object, and has methods that move the ball. It will speed up over
 * time (the longer a point hasn't been made the faster it will go), and within the ball's methods
 * it determines whether it should be reflecting off of a paddle 
 * 
 * @author Jo
 *
 */
public class Ball {
	
	double x;
	double y;
	Color color;
	boolean travelR = true;
	
	boolean inPaddle = false;
	
	/*
	 * This boolean is for if a point has just been made,
	 * which determines if it needs to "reset" the game in Aquarium 
	 */
	boolean justInc = false;
	
	int size = 12;
	
	double speedx;
	double speedy;
	double speed;
	
	double time;
	
	AbstractPaddle paddleL;
	AbstractPaddle paddleR;
	
	int scoreLP;
	int scoreRP;
	
	List<Double> angles = new ArrayList<>();
	
	
	Random rand = new Random();
	
	public Ball(Color color, int x, int y, AbstractPaddle Rpaddle, AbstractPaddle Lpaddle) {
		this.color = color;
		this.y = y;
		this.x = x; 
		
		this.speed = 3;
		
		this.speedx = 2;
		this.speedy = 0.8;
		
		this.paddleR = Rpaddle;
		this.paddleL = Lpaddle;
		
		this.scoreLP = 0;
		this.scoreRP = 0;
		
	}
	
	public void draw(Graphics2D g) { 
		this.time += 1;
		
		MoveBall(g);
		
		g.setColor(color);
		
		Shape ball = new Ellipse2D.Double(x, y, size, size);
		
		g.fill(ball);
		g.draw(ball);
	}
	
	public void MoveBall(Graphics2D g) {
		int speed = 1;
		
		this.inPaddle = false; 
		
		justInc = false; 
		
		
		//This if statement speeds up ball over time 
		if (this.time % 100 == 0) {
			if (this.speedx > 0) {
			this.speedx += 0.75;
			} else {
				this.speedx -= 0.75;
			}
		}
		
		
		this.x += this.speedx;
		this.y += this.speedy;
	
		//check hitting top or bottom wall
		if (this.y <= 0 || this.y >= 500) {
			
			speedy = 0 - speedy; //change to opposite direction if hit top
		} 
		
		//check if hitting left or right wall 
		if (this.x <=0 || this.x >= 500) {
			//this.DestY = rand.nextInt(500);
			if (this.x <= 0) { // then right paddle gets point 
				scoreRP += 1; 
				justInc = true;
				
			} else if (this.x >= 500) { // then left paddle gets a point 
				scoreLP += 1;
				justInc = true;
			}
			speedx = 0 - speedx;
		}
		
	
		
		//check if hitting a paddle 
		//If the ball is traveling to the right, should only hit the right paddle
		if (speedx > 0) {
			checkHitPaddle(paddleR);
		} else {
			checkHitPaddle(paddleL);
		}
		
		if (speedx > 0) {
			this.travelR = true;
		} else {
			this.travelR = false;
		}
		
		
		
	}
	

	
	
	public void checkHitPaddle(AbstractPaddle paddle) {
		
		if (this.travelR ==true) {
			if ( paddle.x -10 <= this.x && this.x < paddle.x + paddle.width) {
				if (paddle.y <= this.y && this.y < paddle.y + paddle.height) {// make sure within x and y boundaries of paddle 
					speedx = 0- speedx;
					int num = rand.nextInt(10);
					
					if (num <= 4) {
						speedy = 0 - speedy;
					}
					
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
