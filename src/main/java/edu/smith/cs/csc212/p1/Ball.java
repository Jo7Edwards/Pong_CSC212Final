package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {
	
	int x;
	int y;
	Color color;
	boolean travelR = true;
	boolean travelUp = true;
	
	int size = 12;
	
	
	Random rand = new Random();
	int DestX = rand.nextInt(500);
	int DestY = rand.nextInt(500);
	
	public Ball(Color color, int x, int y) {
		this.color = color;
		this.y = y;
		this.x = x; 
		
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
		/*x needs to always be moving +1 or -1, but Y can have random placement */
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
		//If randNum equals 2, then choose whether to shoot towards CPU or player
		
		
		
		
		
		
		
	}
}
