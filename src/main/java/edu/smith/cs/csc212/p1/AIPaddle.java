package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;

public class AIPaddle extends AbstractPaddle {

	public AIPaddle(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public void MovePaddle(Graphics2D g, GFX window) {
		// TODO Auto-generated method stub
		this.y -= 1;
	}


}
