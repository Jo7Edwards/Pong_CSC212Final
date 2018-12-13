package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;

public class AIPaddle extends AbstractPaddle {
	//Ball ball;

	public AIPaddle(Color color, int x, int y) {
		super(color, x, y);
		
	}

	@Override
	public void MovePaddle(Graphics2D g, GFX window, Ball ball) {
		// TODO Auto-generated method stub
		//this.y -= 1;
		
		if (ball.speedx < 0) { // if it's traveling leftward towards the AI paddle 

			if (ball.y > this.y) {
				this.y += this.speed;
			} else {
				this.y -= this.speed;
			}
		}
	}


}
