package VehiclePro;

/**
 * Insert the type's description here.
 * Creation date: (6/3/03 2:41:43 AM)
 * @author: 
 */
 import java.awt.*;
 import java.awt.event.*;
 public class  MsgBox extends Dialog {	
	private boolean yes;
/**
 * MsgBox constructor comment.
 */
public MsgBox(Frame parent,String info,String bStr,String bStr1,String title) {
	super(parent,true);
	Font font=new Font("Dialog",Font.PLAIN,12);
	FontMetrics fm =getFontMetrics(font);
	int strW=fm.stringWidth(info);
	Label str=new Label("     " +info);
	Button ok=new Button(bStr);
	Button help=new Button(bStr1);
	Panel p=new Panel();
	Toolkit tk=Toolkit.getDefaultToolkit();	
	p.add(str);p.add(ok);p.add(help);
	add(p);	
	addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
			dispose();
			}
	});
	ok.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			yes=true;			
			dispose();
		}
		
	});
	help.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			yes=false;
			dispose();
		}
		
	});
	int w=getSize().width;
	int h=getSize().height;	
	int x=(tk.getScreenSize().width-(strW+20))/2;
	int y=(tk.getScreenSize().height-h)/3;	
	setLocation(x,y);
	setTitle(title);
	setSize(strW+20,100);	
	setVisible(true);
	
	
	
	
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/22/03 3:08:43 AM)
 * @return boolean
 */
public boolean getYesNo() {
	return yes;
}
}
