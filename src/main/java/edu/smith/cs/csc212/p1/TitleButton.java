package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;

public class TitleButton {
	int x;
	int y; 
	int width;
	int height;
	Color color;
	String words;
	
	boolean wasClicked = false;
	
	public TitleButton(GFX thisWin, int x, int y, int width, int height, Color color, String words) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.words = words;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		Font font = g.getFont();
		g.setFont(font.deriveFont(25f));
		
		g.setColor(Color.white);
		g.drawString(words, x + (width/10), y + (height/2)); 
	}
	/**
	 * 
	 * @return if its true that the button was clicked on 
	 */
	public boolean checkIfClicked() {
		
	}

}
