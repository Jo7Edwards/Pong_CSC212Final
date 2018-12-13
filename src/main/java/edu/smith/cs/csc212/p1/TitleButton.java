package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;

/**
 * The title buttons that should appear on the title screen for you to click. Allows it to appear 
 * "clicked" by darkening color and "unclicked" by being the regular color
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
	 * 
	 * @param x - x coordinate of button's rectangle
	 * @param y - y coordinate of button's rectangle
	 * @param width - width of button's rectangle
	 * @param height - height of button's rectangle 
	 * @param color - the color the button should be 
	 * @param words - words that should appear on the button 
	 */
	public TitleButton( int x, int y, int width, int height, Color color, String words) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.words = words;
		this.area = new Rectangle2D.Double(x, y, width, height);
		
		this.colorcopy = color;
		this.colorchange = color.darker();
		
		
		
		
	}
	
	public void draw(Graphics2D g) {
		/*if(wasClicked) {
			this.color = Color.darkGray;
		} else {
			this.color = Color.lightGray;
		}*/
		if (wasClicked) {
			this.color = colorchange;
		} else {
			this.color = colorcopy;
		}
		
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		Font font = g.getFont();
		g.setFont(font.deriveFont(25f));
		
		g.setColor(Color.white);
		g.drawString(words, x + (width/10), y + (height/2)); 
		
		
	}
	
	public boolean contains(IntPoint click) {
		if (area.contains(click)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @return if its true that the button was clicked on 
	 
	public boolean checkIfClicked() {
		
	}*/

}
