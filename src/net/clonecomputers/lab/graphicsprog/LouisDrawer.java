package net.clonecomputers.lab.graphicsprog;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.image.*;
import java.lang.reflect.*;

import javax.swing.JLabel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 * Fine Gavin I will use your illogical API
 * :)
 * @author Louis
 *
 */
public class LouisDrawer extends AbstractDrawer {

	private static final boolean SNEAKY = false; //not being sneaky!!!!!!

	public static final Color DEFAULT_FOREGROUND_COLOR = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GRAY;

	public LouisDrawer(DrawGrid dg) {
		super(dg);
		if(SNEAKY) {
			dg.addAncestorListener(new AncestorListener() {


				/**
				 * The sad thing is that there is no way to prevent this really.
				 * Even if you don't give me direct access to JFrame methods by passing me an interface I can just use Frame.getFrames() and screw around with stuff anyway.
				 * Boring
				 * @param sneakyDg
				 */
				private void beSneaky(Component sneakyDg) {
					Container sneakyParent = sneakyDg.getParent();
					sneakyParent.removeAll();
					Font sneakyFont = new Font("Monospaced", Font.PLAIN, 70);
					JLabel sneakyLabel = new JLabel("Louis was sneaky");
					sneakyLabel.setFont(sneakyFont);
					sneakyParent.add(sneakyLabel);
				}

				@Override
				public void ancestorAdded(AncestorEvent e) {
					beSneaky(e.getComponent());
				}

				@Override
				public void ancestorMoved(AncestorEvent e) {
					beSneaky(e.getComponent());
				}

				@Override
				public void ancestorRemoved(AncestorEvent e) {
					beSneaky(e.getComponent());
				}

			});
		}
	}

	@Override
	public void drawDot(double x, double y) {
		drawDot(x, y, DEFAULT_FOREGROUND_COLOR);
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2) {
		drawLine(x1, y1, x2, y2, DEFAULT_FOREGROUND_COLOR);
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2, Color c) {
		if(abs(y1 - y2) > abs(x1 - x2)) {
			double invSlope = (x1 - x2)/(y1 - y2);
			double xIntcpt = x1 - invSlope*y1;
			for(double y = min(y1, y2); y <= max(y1, y2); ++y) {
				drawDot(invSlope*y + xIntcpt, y, c);
			}
		} else {
			double slope = (y1 - y2)/(x1 - x2);
			double yIntcpt = y1 - slope*x1;
			for(double x = min(x1, x2); x <= max(x1, x2); ++x) {
				drawDot(x, slope*x + yIntcpt, c);
			}
		}
	}

	@Override
	public void clear() {
		clear(DEFAULT_BACKGROUND_COLOR);
	}

	@Override
	public void drawCrossHair(double x, double y, double radius, double hole) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawCrossHair(double x, double y, double radius, double hole, Color c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawCircle(double ctrX, double ctrY, double radius) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawCircle(double ctrX, double ctrY, double radius, Color c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3, Color c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillTriangleUp(double leftX, double rightX, double leftY,
			double topX, double topY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillTriangleDown(double leftX, double rightX, double leftY,
			double botX, double botY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawRect(double x1, double y1, double x2, double y2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawCube(double x1, double y1, double radius) {
		// TODO Auto-generated method stub

	}

}
