package VehiclePro;

/**
 * Insert the type's description here.
 * Creation date: (6/8/03 10:17:59 AM)
 * @author: 
 */
 import java.awt.*;
public class BorderedPanel extends Panel {
/**
 * Insert the method's description here.
 * Creation date: (6/8/03 10:20:33 AM)
 * @return java.awt.Insets
 */
public Insets getInsets() {
	
	return new Insets(2,2,2,2);
}
/**
 * Insert the method's description here.
 * Creation date: (6/8/03 10:21:18 AM)
 * @param g java.awt.Graphics
 */
public void paint(Graphics g) {
	Dimension size=getSize();
	g.setColor(Color.black);
	g.drawRect(0,0,size.width-1,size.height-1);
	
	g.setColor(Color.lightGray);
	g.draw3DRect(1,1,size.width-3,size.height-3,true);	
}
}
