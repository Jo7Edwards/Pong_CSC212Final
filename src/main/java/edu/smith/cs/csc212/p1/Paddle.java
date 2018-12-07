package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import me.jjfoley.gfx.GFX;

public class Paddle extends AbstractPaddle {
		
	String upKey;
	String downKey;
	
	Boolean isPlayerOne;
	
	
	Random rand = new Random();
	
	//int DestX = rand.nextInt
	
	public Paddle(Color color, int x, int y, boolean isPlayer) {
		super(color, x, y);
		
		this.isPlayerOne = isPlayer;
		
	}
	

	
	
    @Override
	public void MovePaddle(Graphics2D g, GFX window) { 
		//move if key is pressed down
		//Move player one with up and down arrow keys and move player 2 with w and s keys  
		if (this.isPlayerOne) {
			if (window.isKeyDown(KeyEvent.VK_UP)) {
				this.y -= speed;
			} else if (window.isKeyDown(KeyEvent.VK_DOWN)) {
				this.y += speed;
			}
		} else {
			if (window.isKeyDown(KeyEvent.VK_W)) {
				this.y -= speed;
			} else if (window.isKeyDown(KeyEvent.VK_S)) {
				this.y += speed;
			}
		}
		
		
		
	}

}
