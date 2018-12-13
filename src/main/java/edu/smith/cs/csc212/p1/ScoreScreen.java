package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScoreScreen {
	boolean showScore; 
	
	/**
	 * Creates a score at the top of the game being played 
	 */
	public ScoreScreen() {
		this.showScore = false; 
	}
	
	/**
	 * 
	 * @param g
	 * @param score - ball.scoreRP or ball.scoreLP should be passed to this, depending on which paddles score this is 
	 * @param isRightPaddle - true if this score is for the right paddle, false is for the left paddle 
	 */
	public void drawScore(Graphics2D g, int score, boolean isRightPaddle) {
		//pass to it ball.scoreRP or ball.scoreLP
		//pass in string form name of which paddle the draw score is for 
		Font font = g.getFont();
		g.setFont(font.deriveFont(18f));
		
		g.setColor(Color.black);
		if (isRightPaddle) {
			g.drawString("Right Paddle's Score: " + score, 275, 20); 
		} else {
			g.drawString("Left Paddle's Score: " + score, 20, 20); 
		}
		
	}

}
