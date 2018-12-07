package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import me.jjfoley.gfx.GFX;

public abstract class AbstractPaddle {
	int x;
	int y;
	int width;
	int height;
	Color color;
	int speed;
	
	public AbstractPaddle(Color color, int x, int y) {
		this.color = color;
		this.y = y;
		this.x = x;
		this.width = 10;
		this.height = 55;
		this.speed = 3;
	}
	
	public void draw(Graphics2D g, GFX window) {
		//Set to specified color (make color change every time ball is hit with paddle?
		
		stayOnScreen();
		MovePaddle(g, window);
		
		
		g.setColor(color);
		
		Shape paddle = new Rectangle2D.Double(x, y, width, height);
		
		g.fill(paddle);
		g.draw(paddle);
	}
	
	public void stayOnScreen() {
		//keep the paddles from moving off the screen
		if (this.y + height >= 500) {
			this.y -= speed;
		} else if (this.y <= 0) {
			this.y += speed;
		}
	}

	//ABSTRACT here means that each individual paddle needs to write this method for itself, but that
	//it should have this method 
	public abstract void MovePaddle(Graphics2D g, GFX window);
}
