package net.clonecomputers.lab.graphicsprog;

import java.awt.image.*;
import java.awt.*;
import org.junit.*;

public class BufferedImageWriteTest {

	private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);;
	private double zoom = 5;
	
	@Before
	public void clearBufferedImage() {
		Graphics g = image.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
	}
	
	private void drawLargePoint(double x, double y) {
		Graphics g = image.getGraphics();
		g.setColor(Color.BLACK);
		int gwidth = (int) zoom;
		int gheight = (int) zoom;
		int gx = xgp(x)-gwidth/2;
		int gy = ygp(y)+gheight/2;
		g.fillRect(gx, gy, gwidth, gwidth);
	}
	
	private int xgp(double x) {
		return (int)((image.getWidth()/2D) + (x * zoom));
	}

	private int ygp(double y) {
		return (int)((image.getWidth()/2D) - (y * zoom));
	}

	@Test
	public void testDrawSinglePoint() {
		long nanos = System.nanoTime();
		drawLargePoint(0,0);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()
							+ " took " + (System.nanoTime() - nanos) + " ns");
	}
	
	@Test
	public void testDrawManyPoints() {
		int howMany = 1000;
		long nanos = System.nanoTime();
		for(int i = 0; i < howMany; ++i) {
			drawLargePoint(0,i);
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()
				+ " took " + (System.nanoTime() - nanos) + " ns");
	}
	
	@Test
	public void testClear() {
		long nanos = System.nanoTime();
		clearBufferedImage();
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()
				+ " took " + (System.nanoTime() - nanos) + " ns");
	}
	
	
	@Test
	public void testMathSpeed() {
		int howMany = 1000;
		double[] nums = new double[howMany];
		for(int i = 0; i < howMany; ++i) {
			nums[i] = Math.random();
		}
		long nanos = System.nanoTime();
		for(int i = 0; i < 2*howMany; ++i) {
			if(i >= howMany) continue;
			if(nums[i] > 0.66) {
				double n = i/(nums[i] + i);
				Math.abs(n);
			} else if(nums[i] < 0.33) {
				double n = i/(nums[i] + i);
				Math.abs(n);
			} else {
				double n = i/(nums[i] + i);
				Math.abs(n);
			}
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()
				+ " took " + (System.nanoTime() - nanos) + " ns");
	}

}