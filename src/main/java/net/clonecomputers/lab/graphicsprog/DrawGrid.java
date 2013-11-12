package net.clonecomputers.lab.graphicsprog;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawGrid extends JPanel {
	
	private BufferedImage canvas;
	private double zoom = 10;
	
	private ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
	
	public DrawGrid(int w, int h) {
		canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = canvas.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, w, h);
	}
	
	public void point(Color c, double... pos) {
		taskExecutor.submit(new DrawTaskRunner(new DrawPointTask(c, pos)));
	}
	
	public Color getColor(int[] pos) {
		int gx = xgp(pos[0]), gy = ygp(pos[1]);
		return new Color(canvas.getRGB(gx, gy));
	}
	
	public void background(Color c) {
		taskExecutor.submit(new DrawTaskRunner(new ClearTask(c)));
	}
	
	private int xgp(double x) {
		return xgp(x, canvas.getWidth(), zoom);
	}
	
	private int ygp(double y) {
		return ygp(y, canvas.getHeight(), zoom);
	}
	
	public static int xgp(double x, int width, double zoom) {
		return (int) ((width / 2D) + (x * zoom));
	}
	
	public static int ygp(double y, int height, double zoom) {
		return (int) ((height / 2D) - (y * zoom));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(canvas, 5, 5, this);
		g.drawRect(4, 4, canvas.getWidth() + 1, canvas.getHeight() + 1);
	}
	
	private class DrawTaskRunner implements Runnable {
		
		private final DrawTask t;

		public DrawTaskRunner(DrawTask task) {
			t = task;
		}
		
		@Override
		public void run() {
			Graphics g = canvas.getGraphics();
			t.draw(g, canvas.getWidth(), canvas.getHeight(), zoom);
			g.dispose();
			repaint();
		}
		
	}
}
