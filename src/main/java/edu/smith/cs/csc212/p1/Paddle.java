package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import me.jjfoley.gfx.GFX;

public class Paddle {
	
	int x;
	int y;
	Color color;
	
	Random rand = new Random();
	
	//int DestX = rand.nextInt
	
	public Paddle(Color color, int x, int y) {
		this.color = color;
		this.y = y;
		this.x = x;
		
	}
	
	public void draw(Graphics2D g) {
		//Set to specified color (make color change every time ball is hit with paddle? 
		g.setColor(color);
		
		Shape paddle = new Rectangle2D.Double(x, y, 10, 55);
		
		g.fill(paddle);
		g.draw(paddle);
	}
	
	public void MovePaddle(GFX window) {
		if (window.isKeyDown(KeyEvent.VK_UP)) {
			
		}
		int speed = 1; 
		
		
	}

}
