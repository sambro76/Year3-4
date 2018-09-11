package This;

import java.applet.*;
import java.awt.*;
/**
 * Insert the type's description here.
 * Creation date: (5/2/2003 10:26:55 PM)
 * @author: 
 */
public class MyPro extends Applet implements Runnable {
	Thread thread;
	String text[]=new String[10];
	int w=200,h=100,j;
	int y=getSize().height/2+h/2;
	
	Font font=new Font("Serif",1,14);
	FontMetrics fm=getFontMetrics(font);
	int	textwidth;
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "MyPro\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (5/2/2003 10:26:55 PM)\n" + 
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
	super.init();
	String nStr=getParameter("num");
	j=Integer.parseInt(nStr);

	for(int i=0;i<j;i++){
		text[i]=getParameter("text"+i);
	}
	// insert code to initialize the applet here
}
/**
 * Paints the applet.
 * If the applet does not need to be painted (e.g. if it is only a container for other
 * awt components) then this method can be safely removed.
 * 
 * @param g  the specified Graphics window
 * @see #update
 */
public void paint(Graphics g) {
	int z=0;
	int gsw=getSize().width/2,gsh=getSize().height/2;
	
	g.setFont(font);
	g.setColor(new Color(145,50,60));
	g.drawRect(gsw-w/2,gsh-h/2,w,h);
	g.setClip(gsw-w/2,gsh-h/2,w,h);
	
	for(int i=0;i<j;i++){
	textwidth=fm.stringWidth(text[i]);
	g.setColor(new Color((255+i*75)%256,0,255));
	g.drawString(text[i],gsw-textwidth/2,y+z);
	z=z+fm.getHeight();
	}
	// insert code to paint the applet here
}
/**
 * Contains the thread execution loop.
 */
public void run() {
	int time=200;
	while(true){
	time-=50;
	if (time<50) time=100;

	y-=3;
	if(y<getSize().height/2-h/2-fm.getHeight()*3) y=getSize().height/2+h/2;
		repaint();
	
	try{Thread.sleep(time);}
	catch(Exception e){}
			
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
}
