package VehiclePro;

/**
 * Insert the type's description here.
 * Creation date: (6/19/03 9:34:39 AM)
 * @author: 
 */
 import java.awt.*;
 import java.awt.event.*;

public class ImageCanvas extends Canvas {
	private Image image;
/**
 * ImageCanvas constructor comment.
 */
public ImageCanvas(Image image) {
	
	MediaTracker mdt=new MediaTracker(this);
	mdt.addImage(image,0);
		try{
			mdt.waitForID(0);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	this.image=image;
	
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/03 9:46:43 AM)
 * @param g java.awt.Graphics
 */
public void paint(Graphics g) {

	g.drawImage(image,0,0,this);
}
/**
 * Insert the method's description here.
 * Creation date: (6/19/03 9:53:56 AM)
 * @param g java.awt.Graphics
 */
public void update(Graphics g) {
	//paint(g);	
}
}
