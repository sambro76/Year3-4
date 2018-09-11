package Last;

import java.applet.*;
import java.awt.*;
/**
 * Insert the type's description here.
 * Creation date: (5/2/2003 10:26:55 PM)
 * @author:
 */
public class MyPro extends Applet implements Runnable {
	Thread thread;
	String text[]=new String[20];
	String name[]={"","","Chay Samnang","Chap Sokhon"
		,"Men Norith","Lim Touch",""
		,"Visual Age for Java","Yean Lung"};
	int w=280,h=170;
	int	j,textwidth;

	Font font=new Font("Serif",1,14);
	FontMetrics fm=getFontMetrics(font);
	int y=(getSize().height+h)/2+fm.getHeight()*8;

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
	int z=0;
	int gsw=getSize().width/2,gsh=getSize().height/2;

	g.setColor(new Color(145,50,60));
	g.drawRect(gsw-w/2,gsh-h/2,w,h);
	g.setClip(gsw-w/2,gsh-h/2,w,h);

	for(int i=0;i<j;i++){
	textwidth=fm.stringWidth(text[i]);
	g.setFont(font);
		if(i==0) {
			g.drawString(text[i],gsw-textwidth/2,y);
		}
		else{
		g.setColor(new Color((255+i*25)%256,100,255));
		g.drawString(text[i],gsw-textwidth,y+z);
		g.drawString(name[i],gsw,y+z);
		}
	z=z+fm.getHeight();
	}
	// insert code to paint the applet here
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
	if(y<getSize().height/2-h/2-fm.getHeight()*8) y=getSize().height/2+h/2;
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
