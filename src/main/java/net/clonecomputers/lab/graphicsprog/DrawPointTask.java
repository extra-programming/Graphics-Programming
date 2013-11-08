package net.clonecomputers.lab.graphicsprog;

import java.awt.Color;
import java.awt.Graphics;


public class DrawPointTask implements DrawTask {
	
	private final Color c;
	private final double[] pos;
	
	public DrawPointTask(Color color, double... position) {
		c = color;
		pos = position;
	}
	
	@Override
	public void draw(Graphics g, int width, int height, double zoom) {
		g.setColor(c);
		int gwidth = (int) zoom,
			gheight = (int) zoom,
			gx = DrawGrid.xgp(Math.round(pos[0]), width, zoom) - gwidth / 2,
			gy = DrawGrid.ygp(Math.round(pos[1]), height, zoom) + gheight / 2;
		g.fillRect(gx, gy, gwidth, gwidth);
	}
	
}
