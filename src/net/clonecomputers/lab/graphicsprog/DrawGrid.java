package net.clonecomputers.lab.graphicsprog;

import java.awt.*;
import java.awt.image.*;
import java.lang.reflect.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGrid extends JPanel {
	public BufferedImage canvas;
	public DrawGrid(int w, int h) {
		canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = canvas.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,0,w,h);
	}
	
	public synchronized void point(double[] pos, Color c) {
		Graphics g = canvas.getGraphics();
		g.setColor(c);
		pos[0] = Math.round(pos[0]);
		pos[1] = Math.round(pos[1]);
		int gwidth = gpsize(), gheight = gpsize(),
				gx = xgp(pos[0])-gwidth/2, gy = ygp(pos[1])+gheight/2;
		g.fillRect(gx, gy, gwidth, gwidth);
		this.repaint();
	}

	public Color getColor(int[] pos){
		int gx = xgp(pos[0]), gy = ygp(pos[1]);
		return new Color(canvas.getRGB(gx, gy));
	}
	
	public synchronized void background(Color c){
		Graphics g = canvas.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		this.repaint();
	}
	
	private int gpsize() {
		return 10;
	}

	private int xgp(double x) {
		return (int)((canvas.getWidth()/2D) + (x * gpsize()));
	}

	private int ygp(double y) {
		return (int)((canvas.getWidth()/2D) - (y * gpsize()));
	}

	@Override
	public synchronized void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(canvas, 5, 5, this);
		g.drawRect(4, 4, canvas.getWidth()+1, canvas.getHeight()+1);
	}
}
