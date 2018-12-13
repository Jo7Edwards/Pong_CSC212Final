package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.jjfoley.gfx.GFX;

public class Ball {
	
	double x;
	double y;
	Color color;
	boolean travelR = true;
	boolean travelUp = true;
	boolean inPaddle = false;
	
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
	int DestX = 500;
	int DestY = rand.nextInt(500);
	double newthing = rand.nextDouble();
	
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
		
		
		angles.add(0.9);
		angles.add(1.0);
		angles.add(0.7);
		angles.add(2.0);
		angles.add(1.2);
		angles.add(3.0);
		angles.add(4.0);
		angles.add(1.8);
		
		//int subY = this.y;
		//int distY = DestY - subY;
		//this.subY = distY;
		
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
		
		
		//this needs change
		/*this.x += this.speedx;
		this.y += this.speedy;*/
		
		//This if statement speeds up ball over time 
		if (this.time % 100 == 0) {
			if (this.speedx > 0) {
			this.speedx += 0.75;
			System.out.println("BAll SPEED up");
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
	/*
	public void findAngle() {
		float difX = DestX - this.x;
		float difY = (float) (DestY - this.y);
		
		double theta = Math.atan2(difY, difX);
		
		double movex = speed * Math.cos(theta);
		double movey = speed * Math.sin(theta);
		
		this.speedx = movex;
		this.speedy = movey;
	}*/
	//Looking to make ball reflect off differently 
	
	public void checkHitPaddle(AbstractPaddle paddle) {
		//copy this exactly to left travelling, and then edit for right travelling 
		if (this.travelR ==true) {
			if ( paddle.x -10 <= this.x && this.x < paddle.x + paddle.width) {
				if (paddle.y <= this.y && this.y < paddle.y + paddle.height) {// make sure within x and y boundaries of paddle 
					speedx = 0- speedx;
					/*int num = rand.nextInt(angles.size());
					for (int i=num; i==num+1; i++) {
						if (this.y < ((paddle.y + paddle.height)/ 2 )) {
							speedy = angles.get(i);
						} else {
							double num2 = 0 - angles.get(i); 
							speedy = num2;
						}
					}*/
					if (this.y < ((paddle.y + paddle.height)/ 2 )) {
						if (speedy < 0) {
							speedy = 0 - speedy;
						}
					} else {
						if (speedy > 0) {
							speedy = 0 - speedy;
						}
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
