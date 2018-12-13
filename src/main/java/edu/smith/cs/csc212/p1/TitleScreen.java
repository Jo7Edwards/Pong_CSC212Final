package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class TitleScreen {
	
	boolean titleScreenOn = true;
	boolean clicked = false; 
	
	/**
	 * Makes a "TitleScreen", really acts as the 
	 * background rectangle any time a screen is being displayed 
	 */
	public TitleScreen() {
		
	}
	
	
	public void drawTitle(Graphics2D g) {
		if (this.titleScreenOn == true) {
			//draw title screen
			g.setColor(Color.lightGray);
			
			Shape title = new Rectangle2D.Double(50, 50, 365, 400);
			
			g.fill(title);
			g.draw(title);
			
		}
	}
	


}
