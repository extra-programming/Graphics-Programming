package net.clonecomputers.lab.graphicsprog;

import java.awt.Color;
import java.awt.Graphics;


public class ClearTask implements DrawTask {
	
	private final Color c;
	
	public ClearTask(Color color) {
		c = color;
	}
	
	@Override
	public void draw(Graphics g, int width, int height, double zoom) {
		g.setColor(c);
		g.fillRect(0, 0, width, height);
	}
	
}
