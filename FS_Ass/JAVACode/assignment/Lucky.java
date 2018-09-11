package assignment;

import java.applet.*;
import java.awt.*;
/**
 * Insert the type's description here.
 * Creation date: (5/2/2003 10:26:55 PM)
 * @author:
 */
public class Lucky extends Applet implements Runnable {
	Thread thread;
	String text[]=new String[11];
	String name[]={"","","Chay Samnang","Chap Sokhon"
		,"Men Norith","Lim Touch","Hun Thearith"
		,"Visual Age for Java","Mr. Yean Ly Ung","",""};

	Font font=new Font("Serif",1,14);
	FontMetrics fm=getFontMetrics(font);
	int j,y=getSize().height+fm.getHeight()*name.length;

/**
 * Initializes the applet.
 *
 * @see #start
 * @see #stop
 * @see #destroy
 */
public void init() {

	String nStr=getParameter("num");
	j=Integer.parseInt(nStr);
	setBackground(new Color(221,253,255));
	
	for(int i=0;i<j;i++){
		text[i]=getParameter("text"+i);
	}
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
	int z=0,textwidth,gsw=getSize().width,gsh=getSize().height;
	g.setColor(new Color(120,60,63));
	g.drawRect(0,0,gsw-1,gsh-1);
	for(int i=0;i<j;i++){
	textwidth=fm.stringWidth(text[i]);
	g.setFont(font);
		if(i==0) {
			g.setColor(Color.red);
			g.drawString(text[i],gsw/2-textwidth/2,y);
		}
		else if(i==j-1){
			g.setFont(new Font("dialog",3,12));
			g.setColor(new Color((255+i*25)%256,100,255));
			g.drawString(text[i],gsw/2,y+z);
		}
		else {
			g.setColor(new Color((255+i*25)%256,100,255));
			g.drawString(text[i],gsw/2-textwidth,y+z);
			g.drawString(name[i],gsw/2,y+z);
		}
	z=z+fm.getHeight();
	}
}
/**
 * Contains the thread execution loop.
 */
public void run() {
	int time=200,count=0;
	while(true){
	time-=5;
	if (count>50) {time=200;count=0;}
	count+=1;
	y-=3;
	if(y<-fm.getHeight()*j) y=getSize().height;
	repaint();
	try {Thread.sleep(time);}
	catch(Exception e){}
	}
}
/**
 * Starts up the thread.
 */
public void start() {
	thread = new Thread(this);
	thread.start();
}
/**
 * Terminates the thread and leaves it for garbage collection.
 */
public void stop() {
	thread = null;
}
}
