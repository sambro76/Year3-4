package VehiclePro;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Insert the type's description here.
 * Creation date: (6/8/03 9:07:47 AM)
 * @author: 
 */
public class popup extends Applet {
	PopupMenu pMenu=new PopupMenu();
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "popup\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (6/8/03 9:07:47 AM)\n" + 
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
	add(pMenu);
	pMenu.add(new MenuItem("Add"));
	pMenu.add(new MenuItem("Search"));
	pMenu.add(new MenuItem("Delete"));
	pMenu.add(new MenuItem("Clear"));
	///new connectDb();	
	addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e){
			 showPop(e);	
		}
		public void mouseClicked(MouseEvent e){
			 showPop(e);	
		}
		public void mouseReleased(MouseEvent e){
			 showPop(e);
			 
		}
	});

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
	super.paint(g);

	// insert code to paint the applet here
}
/**
 * Insert the method's description here.
 * Creation date: (6/8/03 9:16:38 AM)
 */
void showPop(MouseEvent e) {
	if(e.isPopupTrigger()==true)
		pMenu.show(this,e.getX(),e.getY());
	
}
}
