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
	
	/**
	 * Abstract class for all paddles. Sets how they should appear, that they should all 
	 * not move off the screen, and says each kind of paddle needs to determine their own MovePaddle method 
	 * 
	 * @param color - Color of paddle 
	 * @param x - x coordinate of paddle's rectangle
	 * @param y - y coordinate of paddle's rectangle
	 */
	public AbstractPaddle(Color color, int x, int y) {
		this.color = color;
		this.y = y;
		this.x = x;
		this.width = 15;
		this.height = 55;
		this.speed = 3;
	}
	
	public void draw(Graphics2D g, GFX window, Ball ball) {
		//Set to specified color (make color change every time ball is hit with paddle?
		
		stayOnScreen();
		MovePaddle(g, window, ball);
		
		
		g.setColor(color);
		
		Shape paddle = new Rectangle2D.Double(x, y, width, height);
		
		g.fill(paddle);
		g.draw(paddle);
	}
	
	/**
	 * keeps paddles from moving off the screen
	 */
	public void stayOnScreen() {
		//keep the paddles from moving off the screen
		if (this.y + height >= 500) {
			this.y -= speed;
		} else if (this.y <= 0) {
			this.y += speed;
		}
	}

	
	/**
	 * How the paddle should move. What pressed keys should make it move? or should it move automatically? 
	 * "abstract" here means that each kind of paddle needs to write this method for itself 
	 * 
	 * @param Graphics2D g
	 * @param window 
	 * @param ball
	 */
	public abstract void MovePaddle(Graphics2D g, GFX window, Ball ball);
}



