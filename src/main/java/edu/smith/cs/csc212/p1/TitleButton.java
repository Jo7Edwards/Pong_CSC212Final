package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;

/**
 * The title buttons that should appear on the title screen for user to click. Allows it to appear 
 * "clicked"/"selected" by darkening color and "unclicked"/"unselected" by being the regular color
 * @author Jo
 *
 */
public class TitleButton {
	int x;
	int y; 
	int width;
	int height;
	Color color;
	String words;
	String subWords;
	Rectangle2D area;
	
	/**
	 * colorchange is what color the button should turn if clicked, colorcopy is the same 
	 * as what the original color of the button should be, and wasClicked keeps track if it was
	 * clicked / selected or not 
	 */
	Color colorchange;
	Color colorcopy;
	boolean wasClicked = false;
	
	/**
	 * Create a button consisting of a rectangle and some words.
	 * 
	 * @param x - x coordinate of button's rectangle
	 * @param y - y coordinate of button's rectangle
	 * @param width - width of button's rectangle
	 * @param height - height of button's rectangle 
	 * @param color - the color the button should be 
	 * @param words - words that should appear on the button
	 * @param subWords - words that should appear under main button words, like a subtitle  
	 */
	public TitleButton( int x, int y, int width, int height, Color color, String words, String subWords) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.words = words;
		this.area = new Rectangle2D.Double(x, y, width, height);
		
		this.colorcopy = color;
		this.colorchange = color.darker();
		this.subWords = subWords;
	}
	
	
	public void draw(Graphics2D g) {
		/*
		 * if it was clicked on, that means it was selected and 
		 * changed the color to darker so it is visually easy for user to tell
		 */
		if (wasClicked) {
			this.color = colorchange;
		} else {
			this.color = colorcopy;
		}
		
		/*
		 * draw button's rectangle and any words it should have
		 */
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		Font font = g.getFont();
		g.setFont(font.deriveFont(25f));
		g.setColor(Color.white);
		g.drawString(words, x + (width/10), y + (height/2)); 
		
		Font subFont = g.getFont();
		if (subWords.length() > 25) { //If the subwords are long 
			g.setFont(subFont.deriveFont(10f));
			g.setColor(Color.white);
			g.drawString(subWords, (x + (width/10)) - 12, (y + (height/2)) + 20);
		} else {
			g.setFont(subFont.deriveFont(14f));
			g.setColor(Color.white);
			g.drawString(subWords, (x + (width/10)) - 7, (y + (height/2)) + 20);
		}
		
		
		
	}
	
	/**
	 * 
	 * @param click
	 * @return true if user clicked within the button's rectangle 
	 */
	public boolean contains(IntPoint click) {
		if (area.contains(click)) {
			return true;
		} else {
			return false;
		}
	}
	

}
