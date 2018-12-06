package edu.smith.cs.csc212.p1;

import java.awt.Color;


import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;

//Cite: https://docs.oracle.com/javase/7/docs/api/java/awt/Shape.html

public class Aquarium extends GFX {
	//GFX win 500 by 500? 
	
	//For Y, 0 its at the top edge and getHeight() is at bottom edge 

	
	
	//Right and left paddle x distances from edge of win
	int rPaddleX = getWidth() -75;
	int lPaddleX = 75;
	
	//Make our paddles 
	Paddle LPaddle = new Paddle(Color.blue, lPaddleX, getHeight()/2);
	Paddle RPaddle = new Paddle(Color.cyan, rPaddleX, getHeight()/2);
	
	Ball ball = new Ball(Color.orange, getWidth()/2, getHeight()/2);

	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		LPaddle.draw(g);
		RPaddle.draw(g);
		
		ball.draw(g);
		
		//Creatures.drawFishFacingLeft(g, Color.yellow, rPaddleX, 200);
		//Creatures.drawFishFacingLeft(g, Color.cyan, lPaddleX, 200);
		
		/*// Draw the fish!
		Creatures.drawFishFacingLeft(g, Color.yellow, fish1X, 200);
		// Draw the confused fish!
		Creatures.drawFishFacingRight(g, Color.green, fish2X, 300);

		// What if we wanted this little fish to swim, too?
		Creatures.drawSmallFishFacingLeft(g, Color.red, 200, 100);

		// Move the fish!
		fish1X -= 1;
		fish2X -= 2;*/
	}

	public static void main(String[] args) {
		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}

}
