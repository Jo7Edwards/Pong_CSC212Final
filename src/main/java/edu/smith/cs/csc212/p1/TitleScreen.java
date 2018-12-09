package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class TitleScreen {
	
	boolean titleScreenOn = true;
	boolean clicked = false; 
	
	public TitleScreen() {
		
	}
	
	
	public void drawTitle(Graphics2D g) {
		if (this.titleScreenOn == true) {
			//draw title screen
			g.setColor(Color.GRAY);
			
			Shape title = new Rectangle2D.Double(50, 50, 400, 400);
			
			g.fill(title);
			g.draw(title);
			
			drawWords(g, 60, 60, 340, 100, Color.black, "Click which modes to play");
			drawWords(g, 60, 200, 150, 100, Color.BLUE, "1-player");
			drawWords(g, 250, 200, 150, 100, Color.red, "2-player");
			drawWords(g, 60, 320, 150, 100, Color.green, "Endless");
			drawWords(g, 250, 320, 150, 100, Color.magenta, "Play to win");
		}
	}
	
	/**
	 * This is to auto make text, and auto makes a box behind it 
	 * @param g
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 * @param words
	 */
	public void drawWords(Graphics2D g,int x, int y, int width, int height, Color color, String words) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		Font font = g.getFont();
		g.setFont(font.deriveFont(25f));
		
		g.setColor(Color.white);
		g.drawString(words, x + (width/10), y + (height/2)); 
	}

}
