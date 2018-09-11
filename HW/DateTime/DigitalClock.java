

import java.applet.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/**
 * Insert the type's description here.
 * Creation date: (3/7/02 9:16:28 AM)
 * @author:You Yoeun Mophyna 
 */
public class DigitalClock extends Applet implements Runnable,MouseListener,MouseMotionListener {
	Thread thread;
	Font font=new Font("Helvetica",Font.ITALIC,20);
	Label clock=new Label("");
	String day[]={"Sun","Mon","Tue","Wen","Thu","Fri","Sat"};
	String month[]={"Jan","Feb","Mar","Apl","May","Jun","Jul","Agu","September","Obt","Dec"};
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
			tclock += "" + s;
		tclock += " " + ampm;
		tclock += " " + d.getDate() + " " + day[d.getDay()] + "," + month[d.getMonth()] + "," + (d.getYear() + 1900);
		clock.setFont(font);
		clock.setForeground(Color.blue);
		clock.setBackground(Color.white);
		clock.setText("Date Time:" + " " + tclock);
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
