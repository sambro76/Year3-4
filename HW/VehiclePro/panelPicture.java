package VehiclePro;

/**
 * Insert the type's description here.
 * Creation date: (6/20/03 12:37:03 AM)
 * @author: 
 */
 import java.awt.*;

public class panelPicture extends Panel{
	private Image image;
/**
 * panelPicture constructor comment.
 */
public panelPicture(Image image) {
	super();
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
 * Creation date: (6/20/03 12:39:18 AM)
 * @param g java.awt.Graphics
 */
public void paint(Graphics g) {
	g.drawImage(image,20,10,this);	
}
/**
 * Insert the method's description here.
 * Creation date: (6/20/03 12:39:55 AM)
 * @param g java.awt.Graphics
 */
public void update(Graphics g) {
	
	paint(g);	
}
}
