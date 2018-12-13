package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScoreScreen {
	boolean showScore; 
	
	public ScoreScreen() {
		this.showScore = false; 
	}
	
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
