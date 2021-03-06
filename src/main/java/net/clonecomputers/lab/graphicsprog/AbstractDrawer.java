package net.clonecomputers.lab.graphicsprog;

import java.awt.Color;

public abstract class AbstractDrawer {
	private DrawGrid dg;
	public AbstractDrawer(DrawGrid dg){
		this.dg = dg;
	}
	
	@ParamaterNames({"x","y"})
	public abstract void drawDot(double x, double y);
	@ParamaterNames({"x","y","color"})
	public final void drawDot(double x, double y, Color c){
		dg.point(c, new double[]{x, y});
	}
	
	@ParamaterNames({"x1","y1","x2","y2"})
	public abstract void drawLine(double x1, double y1, double x2, double y2);
	@ParamaterNames({"x1","y1","x2","y2","color"})
	public abstract void drawLine(double x1, double y1, double x2, double y2, Color c);

	@ParamaterNames({})
	public abstract void clear();
	@ParamaterNames({"color"})
	public final void clear(Color c){
		dg.background(c);
	}
	
	@ParamaterNames({"x","y","radius","hole"})
	public abstract void drawCrossHair(double x, double y, double radius, double hole);
	@ParamaterNames({"x","y","radius","hole","color"})
	public abstract void drawCrossHair(double x, double y, double radius,
			double hole, Color c);
	
	@ParamaterNames({"center x","center y","radius"})
	public abstract void drawCircle(double ctrX, double ctrY, double radius);
	@ParamaterNames({"center x","center y","radius","color"})
	public abstract void drawCircle(double ctrX, double ctrY, double radius, Color c);
	
	@ParamaterNames({"x","y"})
	public final Color getDot(double x, double y){
		return dg.getColor(new int[]{(int)x,(int)y});
	}
	
	@ParamaterNames({"x1","y1","x2","y2","x3","y3"})
	public abstract void fillTriangle(double x1,double y1,
			double x2,double y2, double x3,double y3);
	@ParamaterNames({"x1","y1","x2","y2","x3","y3","color"})
	public abstract void fillTriangle(double x1,double y1,
			double x2,double y2, double x3,double y3, Color c);
	@ParamaterNames({"left x","right x","left y","top x","top y"})
	public abstract void fillTriangleUp(double leftX,double rightX,
			double leftY,double topX, double topY);
	@ParamaterNames({"left x","right x","left y","bottom x","bottom y"})
	public abstract void fillTriangleDown(double leftX,double rightX,
			double leftY,double botX, double botY);
	
	@ParamaterNames({"x1","y1","x2","y2"})
	public abstract void drawRect(double x1, double y1, double x2, double y2);
	
	@ParamaterNames({"x1","y1","radius"})
	public abstract void drawCube(double x1, double y1, double radius);
	
	@ParamaterNames({"x","y"})
	public void testlines2d( double x, double y ) {
	//from Miker, API, and David B 
	    /* Upper right quadrant... */
	  drawLine( x+3,y+5, x+10,y+20, Color.red/*Steep, slope > 1 */);
	  drawLine( x+3,y+3, x+20,y+20, Color.green/*Slope == 1 (aka 45 degrees == pi/4 radians)*/);
	  drawLine( x+5,y+3, x+20,y+10, Color.blue/*Shallow, (slope < 1) && (slope > 0)*/ );
	    
	    /* Upper left quadrant ... */
	  drawLine( x+-3,y+5, x+-10,y+20, Color.red/*Steep, slope < -1 */ );
	  drawLine( x+-3,y+3, x+-20,y+20, Color.green/*Slope == -1 (aka -45 degrees)*/);
	  drawLine( x+-5,y+3, x+-20,y+10, Color.blue/*Shallow (slope < 0) && (slope > -1)*/);
	    
	    /* Lower right quadrant ... */
	  drawLine( x+3,y+-5, x+10,y+-20, Color.red/*Steep, slope < -1 */);
	  drawLine( x+3,y+-3, x+20,y+-20, Color.green/*Slope == -1 (aka -45 degrees)*/ );
	  drawLine( x+5,y+-3, x+20,y+-10, Color.blue/*Shallow, (slope < 0) && (slope > -1)*/ );
	    
	    /* Lower left quadrant */
	  drawLine( x+-3,y+-5, x+-10,y+-20, Color.red/*Steep, slope > 1 */ );
	  drawLine( x+-3,y+-3, x+-20,y+-20, Color.green/*Slope == 1 */ );
	  drawLine( x+-5,y+-3, x+-20,y+-10, Color.blue/*Shallow (slope < 1) && (slope > 0)*/ );
	    
	    /* Vertical lines, slope == infinity */
	  drawLine( x+0,y+4, x+0,y+20, Color.black/*Above origin*/ );
	  drawLine( x+0,y+-4, x+0,y+-20, Color.black/*Below origin*/ );
	    
	    /* Horizontal lines, slope == 0 */
	  drawLine( x+4,y+0, x+20,y+0, Color.yellow/*To right of origin*/ );
	  drawLine( x+-4,y+0, x+-20,y+0, Color.yellow/*To left of origin*/ );
	} // testlines2d()
	
	@ParamaterNames({})
	public void testTri() {
	    fillTriangle (20,0,  40,-10,  30,30,  Color.blue ); 
	    
	    double leftX,leftY,  rightX,rightY,  topX,topY;
	    leftX = -10;
	    leftY = -10;
	    rightX = 10;
	    rightY = -10;
	    topX = 5;
	    topY = 10;
	    fillTriangleUp(leftX, rightX, leftY, topX, topY);
	    drawDot(leftX,leftY, Color.green);
	    drawDot(rightX,rightY, Color.yellow);
	    drawDot(topX,topY,Color.red);
	    
	    double botX,botY; // for triangle that goes down
	    leftX = -40;
	    leftY = 12;
	    rightX = -25;
	    rightY = 12;
	    botX = -15;
	    botY = -20;
	    fillTriangleDown(leftX,rightX,  leftY,  botX,botY);
	    drawDot(leftX,leftY, Color.green);
	    drawDot(rightX,rightY, Color.yellow);
	    drawDot(botX,botY,Color.red);
	} // testTri()

	@ParamaterNames({})
	public void testDg( ) {
	    // by Zane F , 2006 Apr 28, modified by Mike Roam 2007 Feb 7
	    fillTriangle (-27,27,  -32,0,  0,32, Color.yellow) ; /* Upper left, with broad face on lower right */
	    fillTriangle (27,27,  32,0,  0,32, Color.green) ;  /* Upper right, with broad face on lower left */
	    fillTriangle (27,-27,  32,0,  0,-32, Color.red) ;    /* Lower right, with broad face on upper left */
	    fillTriangle (-27,-27,  -32,0,  0,-32) ;   /* Lower left, with broad face on upper right */
	    drawDot(27,-27, Color.yellow);
	    drawDot(0,-32,Color.blue);
	    drawDot(27,27,Color.blue);
	    drawDot(32,0,Color.red);
	    drawDot(-27,27, Color.red);
	    drawDot(-32,0, Color.blue);
	    drawDot(0,32, Color.green);
	    drawDot(-27,-27,Color.green);
	    
	    
	    drawCube  ( 0,0, 10 ) ;
	    drawCircle  ( 0,0,20 ) ;
	    drawLine  ( -30,0,  0,30,  Color.red ) ;
	    drawLine  ( 0,30,  30,0,  Color.blue ) ;
	    drawLine  ( 30,0,  0,-30,  Color.yellow ) ;
	    drawLine  ( -29,-1,  0,-30,  Color.green ) ;
	    drawCrossHair  ( -10,0, 5,2 ) ;
	    drawCrossHair  ( 0,10, 8,3 ) ;
	    drawCrossHair  ( 10,0, 5,1 ) ;
	    drawCircle  ( 0,-10,  8, Color.blue ) ;
	    drawCircle  ( 0,-10,  6 ) ;
	    drawCircle  ( 0,-10,  4 ) ;
	    drawCircle  ( 0,-10,  2 ) ;
	    drawDot  ( 0,-10 ) ;
	} /* End testDg() */
		
}
