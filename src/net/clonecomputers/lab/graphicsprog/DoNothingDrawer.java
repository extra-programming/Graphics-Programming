package net.clonecomputers.lab.graphicsprog;

import java.awt.*;

import javax.swing.*;

public class DoNothingDrawer extends AbstractDrawer {

	public DoNothingDrawer(DrawGrid dg) {
		super(dg);
	}

	@Override
	public void drawDot(double x, double y) {
		drawDot(x,y,Color.BLUE);
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2) {
		drawLine(x1,y1,x2,y2,Color.BLUE);
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2, Color c) {
		show("drawLine",x1,y1,x2,y2,c);
	}

	@Override
	public void clear() {
		clear(Color.LIGHT_GRAY);
	}

	@Override
	public void drawCrossHair(double x, double y, double radius, double hole) {
		drawCrossHair(x,y,radius,hole,Color.BLUE);
	}

	@Override
	public void drawCrossHair(double x, double y, double radius, double hole, Color c) {
		show("drawCrossHair",x,y,radius,hole,c);
	}

	@Override
	public void drawCircle(double ctrX, double ctrY, double radius) {
		drawCircle(ctrX,ctrY,radius,Color.BLUE);
	}

	@Override
	public void drawCircle(double ctrX, double ctrY, double radius, Color c) {
		show("drawCircle",ctrX,ctrY,radius,c);
	}

	@Override
	public void fillTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3) {
		fillTriangle(x1,y1,x2,y2,x3,y3,Color.BLUE);
	}

	@Override
	public void fillTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3, Color c) {
		show("fillTriangle",x1,y1,x2,y2,x3,y3,c);
	}

	@Override
	public void fillTriangleUp(double leftX, double rightX, double leftY,
			double topX, double topY) {
		show("fillTriangleUp",leftX,rightX,leftY,topX,topY);
	}

	@Override
	public void fillTriangleDown(double leftX, double rightX, double leftY,
			double botX, double botY) {
		show("fillTriangleDown",leftX,rightX,leftY,botX,botY);
	}

	@Override
	public void drawPyramid(double x, double y) {
		show("drawPyramid",x,y);
	}

	@Override
	public void drawCube(double x1, double y1, double x2, double y2) {
		show("drawCube",x1,y1,x2,y2);
	}
	
	private void show(String s, Object... args){
		JOptionPane.showMessageDialog(null,
				s+str(args),
				s, JOptionPane.OK_OPTION);
	}
	
	private String str(Object... args){
		StringBuilder s = new StringBuilder("(");
		for(Object o: args){
			s.append(o.toString());
			s.append(", ");
		}
		if(args.length > 0) s.delete(s.length()-2, s.length());
		s.append(")");
		return s.toString();
	}

}
