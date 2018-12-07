package edu.smith.cs.csc212.p1;

import java.awt.Color;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import me.jjfoley.gfx.GFX;

//Cite: https://docs.oracle.com/javase/7/docs/api/java/awt/Shape.html

public class Aquarium extends GFX {
	 //GFX win 500 by 500? 
	public Aquarium() {
		//startGame(true);
	}
	
	//For Y, 0 its at the top edge and getHeight() is at bottom edge 

	public boolean up = false;
	boolean down = false;
	
	//Right and left paddle x distances from edge of win
	int rPaddleX = getWidth() -75;
	int lPaddleX = 75;
	
	//Make our paddles 
	AbstractPaddle LPaddle;
	AbstractPaddle RPaddle;
	Ball ball;
	
	boolean onTitleScreen = true;

	
	public void startGame(boolean singlePlayer) {
		if (singlePlayer) {
			LPaddle = new AIPaddle(Color.blue, lPaddleX, getHeight()/2);
		} else {
		LPaddle = new Paddle(Color.blue, lPaddleX, getHeight()/2, false);
		}
		RPaddle = new Paddle(Color.cyan, rPaddleX, getHeight()/2, true);
		ball = new Ball(Color.orange, getWidth()/2, getHeight()/2, RPaddle, LPaddle);
		onTitleScreen = false;
	}
	

	@Override
	public void draw(Graphics2D g) {
		// Draw the background, set the window 
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (onTitleScreen) {
			// draw title screen
			if (this.processClick() != null) {
				startGame(true);
			}
		} else {
		
		
			LPaddle.draw(g, this);
			RPaddle.draw(g, this); //pass 'this' to give it GFX / access to window 
			
			ball.draw(g);
		}
		
	
		
		
	}
	
	public boolean getUp() {
		return this.up;
	}

	public static void main(String[] args) {
		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}

}
