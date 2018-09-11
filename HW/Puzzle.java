

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Insert the type's description here.
 * Creation date: (9/11/2003 9:14:28 PM)
 * @author: 
 */
public class Puzzle extends Applet implements ActionListener,MouseListener,Runnable {
	Label puz=new Label("PUZZLE GAME",Label.CENTER);
	Panel flow=new Panel(new FlowLayout());
	Panel grid=new Panel(new GridLayout(3,3,2,2));
	Button nbutton[]=new Button[9];
	Button babout=new Button("About");
	Button bnew=new Button("New");
	Button bexit=new Button("Exit");
	int xPos=250,yPos=50;
	int horiPos=300,vertiPos=250;
	int hidden=0;
	Cursor cursor=Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	Font font=new Font("Dialog",Font.PLAIN,14);
	String str[]={"By :They Tevy","ID:4112,Year 3,Group 2,Evening","","Taught By:Ung Yean."};
	Thread thr;
	FontMetrics fm;
	int strWidth,y;
	public void actionPerformed(ActionEvent ae){
		Button b=(Button) ae.getSource();
			if(b==bexit){
				System.exit(0);
				}
			for(int i=0;i<nbutton.length;i++){
			if(b==nbutton[i]){
				if (i-1==hidden || i+1==hidden ||i-3==hidden||i+3==hidden){
				nbutton[hidden].setLabel(nbutton[i].getLabel());
				nbutton[hidden].setVisible(true);
				nbutton[i].setVisible(false);
				hidden=i;
				winer();
			}
			return;	
					}
				}
			if(b==bnew){
				newPuz();
				return;
				}
			
		if(babout.getLabel()=="About"){
			grid.setVisible(false);
			babout.setLabel("Puzzle");
			y= vertiPos;
			thr = new Thread(this);
			thr.start();
		}
		else{
			thr.stop();
			grid.setVisible(true);
			babout.setLabel("About");
		}

	}
				
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "Puzzle\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (9/11/2003 9:14:28 PM)\n" + 
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
	setLayout(null);
	
	for(int i=0;i<nbutton.length;i++){
		nbutton[i]=new Button(""+i);
		grid.add(nbutton[i]);
		nbutton[i].addActionListener(this);
		nbutton[i].addMouseListener(this);
	}
	
	add(puz);
	puz.setBounds(xPos,yPos-32,horiPos,31);
	puz.setBackground(Color.gray);
	puz.setFont(new Font("Serif",Font.BOLD+Font.PLAIN,24));
	puz.setForeground(Color.red);
	
	add(grid);
	grid.setBounds(xPos,yPos,horiPos,vertiPos);
	grid.setBackground(Color.lightGray);
	
	flow.add(babout);
	flow.add(bnew);
	add(flow);
	flow.setBounds(xPos,vertiPos+50,horiPos,32);
	flow.setBackground(Color.gray);

	flow.add(bexit);

	babout.addActionListener(this);

	bnew.addActionListener(this);

	nbutton[hidden].setVisible(false);
	bexit.addActionListener(this);

	y= vertiPos;
	// insert code to initialize the applet here
}
	public void mouseClicked(MouseEvent me){
		
		}
	public void mouseEntered(MouseEvent me){
		setCursor(cursor);
		}
	public void mouseExited(MouseEvent me){
		//setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	public void mousePressed(MouseEvent me){
		
		}
	public void mouseReleased(MouseEvent me){
		
		}
	public void newPuz(){
		int label[]=new int[9];	
		for(int i=1;i<nbutton.length;i++){
	    label[i]=i;
		}
		for(int i=0;i<100;i++){
		int r=(int)((Math.random()*8)+1);
		int t= label[1];
		label[1]=label[r];
		label[r]=t;
		}
		for(int i=0;i<nbutton.length;i++){
		if(i==0)
		  nbutton[i].setVisible(false);
		else
		  nbutton[i].setVisible(true);
		nbutton[i].setLabel(""+label[i]);
		}
		hidden=0;
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
	g.setColor(getBackground());
	g.fillRect(xPos, yPos, getSize().width/2-50, getSize().height);

	fm=g.getFontMetrics(font);
	g.setClip(xPos, yPos, horiPos+1, vertiPos+1);
	g.setColor(Color.gray);
	g.drawRect(xPos, yPos, horiPos-1, vertiPos);
	g.setFont(font);

		
	for(int i=0; i<str.length; i++){
		strWidth=fm.stringWidth(str[i]);
		g.setColor(Color.gray);
		g.drawString(str[i],xPos+(horiPos-strWidth)/2,i*fm.getHeight()+y);
	}
}
public void run(){
	Graphics g=getGraphics();
	fm=g.getFontMetrics(font);
		
	while(true){ y-=2;			
		if(y+str.length*fm.getHeight()<yPos) y=vertiPos;
		paint(g);
		try{thr.sleep(100);}
		catch(InterruptedException ex){}	
	}
	
}
	public void start(){
		thr=new Thread(this);
		thr.start();
		}
	public void stop(){
		thr.stop();
		}
	public void winer(){
		for(int i=0;i<nbutton.length-1;i++){
		if(!nbutton[i].getLabel().equals(""+(i+1))){
			puz.setForeground(Color.cyan);
			puz.setText("BEGIN PUZZLE");
			return;
		}
	}
		puz.setText("You Win.Thank");
}
		
		
}
