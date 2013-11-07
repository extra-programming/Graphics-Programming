package net.clonecomputers.lab.graphicsprog;

import java.awt.*;
import static java.lang.Math.*;

public class SampleDrawer extends AbstractDrawer {

	public SampleDrawer(DrawGrid dg) {
		super(dg);
	}

	@Override
	public void drawDot(double x, double y) {
		drawDot(x,y,Color.BLACK);
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2) {
		drawLine(x1,y1,x2,y2,Color.BLACK);
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2, Color c) {
		double dx = x2-x1, dy=y2-y1;
		boolean mirror = abs(dy) > abs(dx);
		if(mirror){
			double tmp;
			tmp=dx;
			dx=dy;
			dy=tmp;
			tmp=x1;
			x1=y1;
			y1=tmp;
			tmp=x2;
			x2=y2;
			y2=tmp;
		}
		double m = dy/dx;
		double b = y1 - m*x1;
		double max = max(x1,x2);
		double min = min(x1,x2);
		for(double x = min; x <= max; x++){
			if(mirror) drawDot(m*x+b,x,c);
			else drawDot(x,m*x+b,c);
		}
	}

	@Override
	public void clear() {
		clear(Color.LIGHT_GRAY);
	}


	@Override
	public void drawCrossHair(double x, double y, double radius, double hole) {
		drawCrossHair(x,y,radius,hole,Color.BLACK);
	}

	@Override
	public void drawCrossHair(double x, double y,
			double radius, double hole, Color c) {
		drawLine(x+hole,y,x+radius,y, c);
		drawLine(x-radius,y,x-hole,y, c);
		drawLine(x,y+hole,x,y+radius, c);
		drawLine(x,y-radius,x,y-hole, c);
	}

	@Override
	public void drawCircle(double ctrX, double ctrY, double radius) {
		drawCircle(ctrX,ctrY,radius,Color.BLACK);
	}

	@Override
	public void drawCircle(double ctrX, double ctrY, double radius, Color c) {
		for(double theta = 0; theta <= 2*PI*radius; theta++){
			//drawLine(ctrX+radius*cos(theta-1),ctrY+radius*sin(theta-1),
			//		ctrX+radius*cos(theta),ctrY+radius*sin(theta),c);
			drawDot(ctrX + (radius*cos(theta/radius)),ctrY + (radius*sin(theta/radius)),c);
		}
	}

	@Override
	public void fillTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3) {
		fillTriangle(x1,y1,x2,y2,x3,y3,Color.BLACK);
	}
	
	@Override
	public void fillTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3, Color c) {
		double l1x = y2-y3,l1y = x3-x2,l1s = -(x2*l1x + y2*l1y),
			   l2x = y3-y1,l2y = x1-x3,l2s = -(x3*l2x + y3*l2y),
			   l3x = y1-y2,l3y = x2-x1,l3s = -(x1*l3x + y1*l3y);
		double d1 = x1*l1x + y1*l1y + l1s,
			   d2 = x2*l2x + y2*l2y + l2s,
			   d3 = x3*l3x + y3*l3y + l3s;
		double maxX = max(x1,x2,x3), minX = min(x1,x2,x3),
				maxY = max(y1,y2,y3), minY = min(y1,y2,y3);
		for(double x = minX; x <= maxX; x++){
			for(double y = minY; y <= maxY; y++){
				if((Math.signum(d1) == signum(x*l1x + y*l1y + l1s)) &&
				   (Math.signum(d2) == signum(x*l2x + y*l2y + l2s)) &&
				   (Math.signum(d3) == signum(x*l3x + y*l3y + l3s)) ){
					drawDot(x,y,c);
				}
			}
		}
		drawLine(x2,y2,x3,y3,c);
		drawLine(x3,y3,x1,y1,c);
		drawLine(x1,y1,x2,y2,c);
	}

	private static double max(double... args) {
		double max = -Double.MAX_VALUE;
		for(double d: args) if(d > max) max = d;
		return max;
	}

	private static double min(double... args) {
		double min = Double.MAX_VALUE;
		for(double d: args) if(d < min) min = d;
		return min;
	}

	@Override
	public void fillTriangleUp(double leftX, double rightX, double leftY,
			double topX, double topY) {
		fillTriangle(rightX,leftY,leftX,leftY,topX,topY);
	}

	@Override
	public void fillTriangleDown(double leftX, double rightX, double leftY,
			double botX, double botY) {
		fillTriangle(rightX,leftY,leftX,leftY,botX,botY);
	}

	@Override
	public void drawCube(double x, double y, double r) {
		drawCube(x, y, r, .8, PI/6);
	}
	
	public void drawCube(double x, double y, double r, double scale, double angle){
		double s = scale; // perspective scalefactor
		double a = angle; // angle
		double x1=x+r,x2=x-r,y1=y+r,y2=y-r;
		double x3=x+r*(2*cos(a)+1)*s,x4=x+r*(2*cos(a)-1)*s,y3=y+r*(2*sin(a)+1)*s,y4=y+r*(2*sin(a)-1)*s;
		drawRect(x1,y1,x2,y2);
		drawRect(x3,y3,x4,y4);
		drawLine(x1,y1,x3,y3);
		drawLine(x2,y2,x4,y4);
		drawLine(x1,y2,x3,y4);
		drawLine(x2,y1,x4,y3);
	}
	
	public void fillQuadralateral(double x1, double y1, double x2, double y2,
								  double x3, double y3, double x4, double y4, Color c) {
		fillTriangle(x1,y1,x2,y2,x3,y3, c);
		fillTriangle(x1,y1,x4,y4,x3,y3, c);
	}
	
	public void fillCube(double x, double y, double r, double scale, double angle){
		double s = scale; // perspective scalefactor
		double a = angle; // angle
		double x1=x+r,x2=x-r,y1=y+r,y2=y-r;
		double x3=x+r*(2*cos(a)+1)*s,x4=x+r*(2*cos(a)-1)*s,y3=y+r*(2*sin(a)+1)*s,y4=y+r*(2*sin(a)-1)*s;
		
		fillQuadralateral(x1, y1, x1, y2, x2, y2, x2, y1, Color.RED);
		fillQuadralateral(x1, y1, x3, y3, x4, y3, x2, y1, Color.GREEN);
		fillQuadralateral(x1, y1, x3, y3, x3, y4, x1, y2, Color.BLUE);
	}

	@Override
	public void drawRect(double x1, double y1, double x2, double y2) {
		drawLine(x1,y1,x2,y1);
		drawLine(x1,y1,x1,y2);
		drawLine(x1,y2,x2,y2);
		drawLine(x2,y1,x2,y2);
	}

}
