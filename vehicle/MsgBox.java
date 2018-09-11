package vehicle;

import java.awt.*;
import java.awt.event.*;
public class  MsgBox extends Dialog {	
	private boolean yes;
	int w=getSize().width;
public MsgBox(Frame parent,String info,String bStr, String bStrNo, String title) {
	super(parent,true);
	Font font=new Font("Dialog",Font.PLAIN,12);
	FontMetrics fm =getFontMetrics(font);
	int strW=fm.stringWidth(info);
	
	Label str=new Label("     " +info);
	Button bOk=new Button(bStr);
	Button bNo=new Button(bStrNo);
	Panel p=new Panel();

	Toolkit tk=Toolkit.getDefaultToolkit();
	p.add(str);p.add(bOk);
	p.add(bNo);
	add(p);
	if (bStrNo=="") bNo.setVisible(false);
	else bNo.setVisible(true);
	
	addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
			dispose();
			}
	});
	bOk.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			yes=true;			
			dispose();
		}
	});
	bNo.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			yes=false;
			dispose();
		}
	});
//	int w=getSize().width;
//	int h=getSize().height;	
//	int x=(w-(strW+20))/2;
	setLocation(300,240);
	setTitle(title);
	setSize(strW+20,100);	
	setVisible(true);
	setResizable(false);
}
/**
 * Insert the method's description here.
 * Creation date: (18/10/03 2:41:43 AM)
 * @return boolean
 */
public boolean getYesNo() {
	return yes;
}
}
