package Thr;

import java.applet.*;
import java.awt.*;
/**
 * Insert the type's description here.
 * Creation date: (3/28/2003 8:34:19 PM)
 * @author: 
 */
public class ScrollText extends Applet implements Runnable{
int x,y,z,t;
Thread thr;
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "ScrollText\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (3/28/2003 8:34:19 PM)\n" + 
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
/**
 * Paints the applet.
 * If the applet does not need to be painted (e.g. if it is only a container for other
 * awt components) then this method can be safely removed.
 * 
 * @param g  the specified Graphics window
 * @see #update
 */
public void paint(Graphics g) {
		g.setColor(Color.red);
		g.drawString("Scroll Horizontal !",x,50);
		g.setColor(Color.blue);
		g.drawString("Scroll Horizontal !",y,150);
		g.setColor(Color.black);
		g.drawString("Scroll Vertical !",50,z);
		g.setColor(Color.green);
		g.drawString("Scroll Horizontal !",150,t);
}
	public void run(){
		while(true){
			x-=10;
			y+=10;
			z-=10;
			t+=10;
			if(x<0) x=getSize().width;
			repaint();
			if(y==getSize().width) y=0;
			repaint();
			if(z<0) z=getSize().height;
			repaint();
			if(t==getSize().height) t=0;
			repaint();

			try{Thread.sleep(50);}
			catch(Exception e){}
		
		}
	}
public void start(){
	Thread thr=new Thread(this);
	thr.start();
}
}
