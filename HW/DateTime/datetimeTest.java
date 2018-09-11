package DateTime;

import java.applet.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/**
 * Insert the type's description here.
 * Creation date: (3/7/02 9:16:28 AM)
 * @author:You Yoeun Mophyna 
 */
public class datetimeTest extends Applet implements Runnable,MouseListener,MouseMotionListener {
	Thread thread;
	Font font=new Font("Helvetica",Font.ITALIC,20);
	Label clock=new Label("");
	String day[]={"Sun","Mon","Tue","Wen","Thu","Fri","Sat"};
	String month[]={"Jan","Feb","Mar","Apl","May","Jun","Jul","Agu","Set","Obt","Dec"};
	int h1,s,m;
	int xs,ys,xm,ym,xh,yh;
	int xslast,yslast,xmlast,ymlast,xhlast,yhlast;
	Point p1=new Point(10,10);
	Point p2=new Point();
	Point p3=new Point();
	int xdiff;
	int ydiff;
	int x=10; int y=10;
	int w = 100;
	int h = 100;
	int x1;
	int y1;int x1last;int y1last;
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "datetimeTest\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (3/7/02 9:16:26 AM)\n" + 
		"@author: \n" + 
		"";
}
/**
 * Initializes the applet.
 * 
 * @see #start
 * @see #stop
 * @see #destroy
 */
public void init() {
	this.add(clock);
	addMouseListener(this);
	addMouseMotionListener(this);
}
/**
 * Insert the method's description here.
 * Creation date: (4/1/00 9:17:34 AM)
 */
public void mouseClicked(MouseEvent me) {}
/**
 * Insert the method's description here.
 * Creation date: (4/1/00 9:20:39 AM)
 */
public void mouseDragged(MouseEvent me) {
	p2 = me.getPoint();
	if (p1.x <= x + w && p1.x >= x && p1.y <= y + h && p1.y >= y) {
		xdiff = p2.x - p1.x;
		ydiff = p2.y - p1.y;
		repaint();
		p1 = p2;
	}
}
/**
 * Insert the method's description here.
 * Creation date: (4/1/00 9:19:30 AM)
 */
public void mouseEntered(MouseEvent me) {}
/**
 * Insert the method's description here.
 * Creation date: (4/1/00 9:19:59 AM)
 */
public void mouseExited(MouseEvent me) {}
/**
 * Insert the method's description here.
 * Creation date: (4/1/00 9:21:28 AM)
 */
public void mouseMoved(MouseEvent me) {
	p3=me.getPoint();
	x1=p3.x;
	y1=p3.y;
	
	
	
	
	
	}
/**
 * Insert the method's description here.
 * Creation date: (4/1/00 9:19:01 AM)
 */
public void mousePressed(MouseEvent me) {
	p1=me.getPoint();
	
	
	
	
	}
/**
 * Insert the method's description here.
 * Creation date: (4/1/00 9:18:20 AM)
 */
public void mouseReleased(MouseEvent me) {}
/**
 * Paints the applet.
 * If the applet does not need to be painted (e.g. if it is only a container for other
 * awt components) then this method can be safely removed.
 * 
 * @param g  the specified Graphics window
 * @see #update
 */
public void paint(Graphics g) {
	// insert code to paint the applet here

	int w = 100;
	int h = 100;
	int xcenter = x + w / 2;
	int ycenter = y + h / 2;
	//Erase Before you repaint
	g.setColor(getBackground());
	g.fillOval(x, y, w, h);
	g.drawString("12", xcenter - 5, ycenter - 40);
	g.drawString("3", xcenter + w / 2 - 8, ycenter + 3);
	g.drawString("6", xcenter - 2, ycenter + h / 2 - 3);
	g.drawString("9", x + 4, y + h / 2 + 1);
	g.drawLine(xcenter, ycenter, xslast, yslast);
	g.drawLine(xcenter, ycenter, xmlast, ymlast);
	g.drawLine(xcenter, ycenter, xhlast, yhlast);
	g.drawString("Date Time",x1last,y1last);
	//Display
	x += xdiff;
	y += ydiff;
	xcenter = x + w / 2;
	ycenter = y + h / 2;
	g.setColor(Color.yellow);
	g.fillOval(x, y, w, h);
	g.setColor(Color.blue);
	g.drawString("12", xcenter - 5, ycenter - 40);
	g.drawString("3", xcenter + w / 2 - 8, ycenter + 3);
	g.drawString("6", xcenter - 2, ycenter + h / 2 - 3);
	g.drawString("9", x + 4, y + h / 2 + 1);
	
	//Second
	xs = (int) (Math.cos(s * 3.14f / 30 - 3.14f / 2) * (w / 2 - 5) + xcenter);
	ys = (int) (Math.sin(s * 3.14f / 30 - 3.14f / 2) * (w / 2 - 5) + ycenter);
	//Miutes
	xm = (int) (Math.cos(m * 3.14f / 30 - 3.14f / 2) * (w / 2 - 15) + xcenter);
	ym = (int) (Math.sin(m * 3.14f / 30 - 3.14f / 2) * (w / 2 - 15) + ycenter);
	//Houre
	xh = (int) (Math.cos(h1 * 3.14f / 6 + m * 3.14f / 360 - 3.14f / 2) * (w / 2 - 24) + xcenter);
	yh = (int) (Math.sin(h1 * 3.14f / 6 + m * 3.14f / 360 - 3.14f / 2) * (w / 2 - 24) + ycenter);
	g.setColor(getBackground());
	//g.setColor(Color.yellow);
	if (xs != xslast || ys != yslast) {
		g.drawLine(xcenter, ycenter, xslast, yslast);
	}
	if (xm != xmlast || ym != ymlast) {
		g.drawLine(xcenter, ycenter, xmlast, ymlast);
	}
	if (xh != xhlast || yh != yhlast) {
		g.drawLine(xcenter, ycenter, xhlast, yhlast);
	}
	g.setColor(Color.blue);
	g.drawLine(xcenter, ycenter, xs, ys);
	g.drawLine(xcenter, ycenter, xm, ym);
	g.drawLine(xcenter, ycenter, xh, yh);
	g.drawString("Date Time",x1,y1);
	x1last=x1;
	y1last=y1;
	xslast = xs;
	yslast = ys;
	xmlast = xm;
	ymlast = ym;
	xhlast = xh;
	yhlast = yh;
	//Erase
	xdiff = 0;
	ydiff = 0;
}
/**
 * Contains the thread execution loop.
 */
public void run() {
	while (true) {
		Date d = new Date();
		String ampm = "am";
		String tclock = "";
		h1 = d.getHours();
		if (h1 >= 12) {
			ampm = "Pm";
			if (h1 > 12) {
				h1 -= 12;
			}
		}
		tclock += h1 + ":";
		m = d.getMinutes();
		if (m < 10)
			tclock += "0" + m;
		else
			tclock += m + ":";
		s = d.getSeconds();
		if (s < 10)
			tclock += "0" + s;
		else
			tclock += ":" + s;
		tclock += " " + ampm;
		tclock += " " + d.getDate() + " " + day[d.getDay()] + "," + month[d.getMonth()] + " " + (d.getYear() + 1900);
		clock.setFont(font);
		clock.setForeground(Color.red);
		clock.setBackground(Color.cyan);
		clock.setText("This Time is:" + " " + tclock);
		repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}
}
/**
 * Starts up the thread.
 */
public void start() {
	if (thread == null){
		thread = new Thread(this);
		thread.start();
	}
}
/**
 * Terminates the thread and leaves it for garbage collection.
 */
public void stop() {
	thread = null;
}
/**
 * Insert the method's description here.
 * Creation date: (3/25/02 9:29:32 AM)
 */
public void update(Graphics g) {
	paint(g);
}
}
