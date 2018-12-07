package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import me.jjfoley.gfx.GFX;

public class Paddle{
	
	int x;
	int y;
	int width;
	int height;
	Color color;
	
	
	Random rand = new Random();
	
	//int DestX = rand.nextInt
	
	public Paddle(Color color, int x, int y) {
		this.color = color;
		this.y = y;
		this.x = x;
		this.width = 10;
		this.height = 55;
		
		
		
		
		
	}
	
	public void draw(Graphics2D g, GFX window) {
		//Set to specified color (make color change every time ball is hit with paddle?
		
		
		MovePaddle(g, window);
		
		g.setColor(color);
		
		Shape paddle = new Rectangle2D.Double(x, y, width, height);
		
		g.fill(paddle);
		g.draw(paddle);
	}
	
	public void MovePaddle(Graphics2D g, GFX window) {
		int speed = 3; 
		
		//keep the paddles from moving off the screen
		if (this.y + height >= 500) {
			this.y -= speed;
		} else if (this.y <= 0) {
			this.y += speed;
		}
		
		//move if key is pressed down 
		if (window.isKeyDown(KeyEvent.VK_UP)) {
			this.y -= speed;
		} else if (window.isKeyDown(KeyEvent.VK_DOWN)) {
			this.y += speed;
		}
		
		
		
	}

}
