package VehiclePro;

import java.applet.*;
/**
 * Insert the type's description here.
 * Creation date: (6/3/03 2:38:27 AM)
 * @author: Suy Bunthan
 */
 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import sun.jdbc.odbc.*;

public class VehicleRegistration extends Frame implements Runnable{
	Hashtable hash;
	String key;
	Thread thread=new Thread(this);
	int picID=0;
	Panel pLogo=new Panel(new GridLayout(3,1));
	Panel all=new Panel(new BorderLayout());
	Panel pTxt=new Panel(new GridLayout(5,2));
	Panel pBut=new Panel();
	Panel pNote=new Panel();
	Panel pPic =new Panel(new BorderLayout());
	TextField txtID;
	TextField txtMake;
	TextField txtModel;
	TextField txtFname;
	TextField txtLname;
	MsgBox msg;
	Frame f=new Frame();
	private PopupMenu pMn = new PopupMenu();
	Vehicle v;
	int record;
	Toolkit tk=Toolkit.getDefaultToolkit();	
	Dimension frm;
	Image img;
	ImageCanvas canvas;
	panelPicture pic;	
	
/**
 * VehicleRegistration constructor comment.
 */
public VehicleRegistration() {
	super("VehicleRegistration");
	//Frame frame =new startUp();			
	/*Panel pLogo=new Panel(new GridLayout(3,1));
	Panel all=new Panel(new BorderLayout());
	Panel pTxt=new Panel(new GridLayout(5,2));
	Panel pBut=new Panel();
	Panel pNote=new Panel();
	Panel pPic =new Panel(new BorderLayout());
	*/
	Button bShow= new Button("Show Dialog");
	Label log=new Label("          ");
	Label log1=new Label("Public Work");
	Label log2=new Label("and Transport");
	Label Lid=new Label("ID");
	Label Lmake=new Label("Make");
	Label Lmodel=new Label("Model");
	Label Lfname=new Label("First Name");
	Label Llname=new Label("Last Name");
	Label lPic=new Label("      Picture of the Car  ");	
	txtID=new TextField("Enter ID");
	txtID.setBackground(new Color(178,216,204));
	txtMake=new TextField("");
	txtModel=new TextField("");
	txtFname=new TextField("");
	txtLname=new TextField("");
	Button Add=new Button("Add");
	Button Search=new Button("Search");
	Button Update=new Button("Update");	
	Button Delete=new Button("Delete");
	Button Clear=new Button("Clear");
	Button Refresh=new Button("Exit");
	Button Next =new Button("Image");
	Label note=new Label("Vehicle Entry Form");
	note.setFont(new Font("Times Roman",Font.BOLD,20));	
	pNote.add(note);
	pLogo.add(log);
	/* Set form appearance property*/
	Toolkit tk=Toolkit.getDefaultToolkit();
	img=tk.getImage("landcruiser.gif");
	Image icon=tk.getImage("Prius.gif");	
	pic=new panelPicture(img);	
	pPic.add(lPic,BorderLayout.NORTH);
	pPic.add(pic,BorderLayout.CENTER);	
	pTxt.add(Lid);pTxt.add(txtID);	
	pTxt.add(Lmake);pTxt.add(txtMake);
	pTxt.add(Lmodel);pTxt.add(txtModel);
	pTxt.add(Lfname);pTxt.add(txtFname);
	pTxt.add(Llname);pTxt.add(txtLname);	
	pBut.add(Add);pBut.add(Search);pBut.add(Update);
	pBut.add(Delete);pBut.add(Clear);pBut.add(Next);
	pBut.add(Refresh);
	pBut.setBackground(new Color(92,200,192));
	all.add(pNote,BorderLayout.NORTH); 
	all.add(pLogo,BorderLayout.WEST);//add Title		
	all.add(pTxt,BorderLayout.CENTER);//Add textfield Panel
	all.add(pBut,BorderLayout.SOUTH); //add Button panel
	all.add(pPic,BorderLayout.EAST);//add Picture Panel	
	all.setBackground(Color.cyan);
	add(all);		
	//Menu
	MenuBar mb=new MenuBar();
	Menu file=new Menu("File");
	Menu help=new Menu("Help");	
	MenuItem mnAdd=new MenuItem("Add");
	MenuItem mnSearch=new MenuItem("Search");
	MenuItem mnUpdate=new MenuItem("Update");
	MenuItem mnDelete=new MenuItem("Delete");
	MenuItem mnExit=new MenuItem("Exit");
	MenuItem mnClear=new MenuItem("Clear");
	MenuItem subAuthor=new MenuItem("Author");
	MenuItem subSupport=new MenuItem("Support");
	
	Menu mnAbout=new Menu("About");
	file.add(mnAdd);file.add(mnSearch);
	file.addSeparator();file.add(mnUpdate);
	file.add(mnDelete);file.add(mnClear);
	file.addSeparator();	file.add(mnExit);
	help.add(mnAbout);	mnAbout.add(subSupport);
	mnAbout.add(subAuthor);	mnAbout.addSeparator();	
	help.add(mnAbout);mb.add(file);mb.add(help);		
	txtID.setSelectionStart(0);
	txtID.setSelectionEnd(15);	
	pack();
	int w=getSize().width;
	int h=getSize().height;	
	int x=(tk.getScreenSize().width-w)/2;
	int y=(tk.getScreenSize().height-h)/3;	
	setLocation(x,y);	
	setMenuBar(mb);
	setVisible(true);
	setIconImage(icon);
	setTitle("Vehicle Registration");	
	connect();		
	all.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e){
			 showPop(e);
			 
		}
		public void mouseClicked(MouseEvent e){
			 showPop(e);
			 System.out.println("clicked");
			 
		}
		public void mouseReleased(MouseEvent e){
			 showPop(e);
			
		}
	});
	
/* Add new Record Here */
		Add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				addRecord();
	 	 	}
		
		
		});
		
/* Search Record Here */
		Search.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent ae){		
				v =(Vehicle)hash.get(txtID.getText());
			 		if (v==null){
				 	  MsgBox y= new MsgBox(f,"The car to search is not found.","Ok","Help","Unreachable Data");
				 		
					 					 		
			 		}
			 		else{						
						txtMake.setText(v.make);
						txtModel.setText(v.model);
						txtFname.setText(v.fname);
						txtLname.setText(v.lname);
						txtID.setEditable(false);
			 		}
			
			}
		
		});

/* Update Record*/
		Update.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent ae){		
				v =(Vehicle)hash.get(txtID.getText());				
			 		if (v==null){
				 		MsgBox msg=new MsgBox(f,"The record to update is not found.","Ok","Help","Unreachable Data");
			 		}
			 		else{
				 		MsgBox msg=new MsgBox(f,"Are you sure to update this record?.","Yes","No","Update Data");
				 			if(msg.getYesNo()){
								updateRecord();
				 			}
				 			else{
					 			msg.dispose();
				 			}	
			 		}
			
			}//It doesn't update the Hashtable 
		
		});
		
/* Clear Text contain*/	
		Clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){				
				MsgBox msg=new MsgBox(f,"Do you want to clear the content?","Yes","No","Cear?");				
				if(msg.getYesNo()){
					
					clear();
				}
				else{
					
				}
				
			
			}
		
		});
	
/* Delete Record */
	Delete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		MsgBox msg=new MsgBox(f,"Do you want to delete this record?","Yes","No","Deletion Confirm");
			if(msg.getYesNo()){
			deleteRecord();
		    hash.remove(txtID.getText());
			clear();
			}
			else{
				msg.dispose();
			}
		}
		
	});
/* Exit*/	
		Refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				//connect();
				dispose();
				System.exit(0);
			}
		
		});
		Next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Toolkit tk=Toolkit.getDefaultToolkit();
				String imgName;
				FileDialog d=new FileDialog(f,"Browse Picture");
				d.show();
				imgName=d.getFile();
				Image image=tk.getImage(imgName);
				pPic.remove(pic);
				//all.remove(pPic);	
				pic=new panelPicture(image);
				pPic.add(pic,BorderLayout.CENTER);
				//pPic.resize(150,150);	
				all.add(pPic,BorderLayout.EAST);				
				
			}
		
		});
	
/*Add Event to Menu*/
	
	mnAdd.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			addRecord();
		}
	});

	
	mnSearch.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			
			v =(Vehicle)hash.get(txtID.getText());
			 		if (v==null){
				 		MsgBox msg=new MsgBox(f,"The car to search is not found.","Ok","Help","Unreachable Data");
			 		}
			 		else{
						connect(); 
						txtMake.setText(v.make);
						txtModel.setText(v.model);
						txtFname.setText(v.fname);
						txtLname.setText(v.lname);
			 		}
			
		}
	});
	
	mnDelete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			deleteRecord();
		}
	});
		
	mnUpdate.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			updateRecord();
		}
	});
	
	mnClear.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			txtID.setText("");
			clear();
		}
	});
	
	mnExit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			System.exit(0);
		}
	});

	subAuthor.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			MsgBox msg =new MsgBox(f,"Computer 4"
				,"Ok","Close","About");
		}
	});
	
	addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
			dispose();
			System.exit(0);			
		}
	 });
	addWindowListener(new WindowAdapter(){
		public void windowIconified(WindowEvent we){			
			setTitle("Min");	
			
		}
	 });
	addWindowListener(new WindowAdapter(){
		public void windowActivated(WindowEvent we){						
			setTitle("Vehicle Registration");
		}
	 });
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/16/03 11:32:36 PM)
 */
public void addRecord() {	
	   if(hash.containsKey(txtID.getText())){
			MsgBox msg =new MsgBox(f,"The car ID already exists. Use a different ID.","Ok","Help","Doublicated Data");
		}
	
		else{			
			Vehicle	v =new Vehicle(txtID.getText(),txtMake.getText(),txtModel.getText(),txtFname.getText(),txtLname.getText());			    
			hash.put(txtID.getText(),v);				
			try{
				String sqlString ="INSERT INTO tblVehicle VALUES ('" + txtID.getText() + "','" + 
			                    txtMake.getText()+ "','" + txtModel.getText()+"','" + txtFname.getText()+"','" + txtLname.getText()+ "')";
				String url="jdbc:odbc:Vehicle";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
				Connection con=DriverManager.getConnection(url);
				con.setAutoCommit(false);
				Statement stm=con.createStatement();				
				int ct=stm.executeUpdate(sqlString);
				con.commit();
				con.close();
				}		
			catch (Exception e){				
				System.out.println(e.getMessage());				
			}
			clear(); //clear the text				
	}
}
/**
 * Insert the method's description here.
 * Creation date: (6/23/03 10:50:37 PM)
 */
public void BrowsePicture() {
	
	

}
/**
 * Insert the method's description here.
 * Creation date: (6/17/03 6:52:59 PM)
 */
public void clear() {	
			txtID.setText("");
			txtMake.setText("");
			txtModel.setText("");
			txtFname.setText("");
			txtLname.setText("");
			txtID.requestFocus();
			txtID.setEditable(true);
			
}
/**
 * Insert the method's description here.
 * Creation date: (6/10/03 4:29:27 AM)
 */
public void connect() {
	//String Rec[];
	hash=new Hashtable();
		try{
			String sqlString ="Select * From tblVehicle";
			String url="jdbc:odbc:Vehicle";
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
			Connection con=DriverManager.getConnection(url);
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sqlString);
			int i=0;
			while(rs.next()){
				    
					String id=rs.getString(1);
				    String make=rs.getString(2);
				    String model=rs.getString(3);
				    String fName=rs.getString(4);
				    String lName=rs.getString(5); 
				    Vehicle v=new Vehicle(id,make,model,fName,lName);		   												
				    hash.put(id,v);
				    //i++;				    
					//record=i;
					//Rec[i]=rs.getString(1);	
				//System.out.println("ID  " + Rec[i] );			
			}
			
			//System.out.println("Total Cound" +record );		
			con.close();
	}
		catch (Exception e){
		System.out.println(e.getMessage());
}
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/16/03 11:44:48 PM)
 */
public void deleteRecord() {
	
	try{
			String sqlString ="DELETE FROM tblVehicle WHERE ID = '"+ txtID.getText() + "'" ;
			String url="jdbc:odbc:Vehicle";
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
			Connection con=DriverManager.getConnection(url);
			con.setAutoCommit(false);
			Statement stm=con.createStatement();				
			int ct=stm.executeUpdate(sqlString);
			con.commit();
			con.close();
		}
		
		catch (Exception e){
			
			System.out.println(e.getMessage());
		}
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/3/03 2:40:09 AM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	new VehicleRegistration();
	
	
	}
/**
 * Insert the method's description here.
 * Creation date: (6/19/03 1:19:07 AM)
 * @param g java.awt.Graphics
 */
public void paint(Graphics g) {
	Toolkit tk=Toolkit.getDefaultToolkit();
	Image img=tk.getImage("Boulders.gif");	
	g.drawImage(img,250,150,this);
	//System.out.println(picID);
}
/**
 * Insert the method's description here.
 * Creation date: (6/17/03 8:32:31 PM)
 */
public void run() {
	
	while(true){
		
		if (picID>3){
			picID=0;
		}
		else{
			picID++;
		}
		////repaint();
	 try{thread.sleep(100);}
	 catch(Exception e){}
	}
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/8/03 9:24:54 AM)
 * @param e java.awt.event.MouseEvent
 */
void showPop(MouseEvent e) {
	
	if(e.isPopupTrigger())
		pMn.show(f ,e.getX(),e.getY());
		
	}
/**
 * Insert the method's description here.
 * Creation date: (6/17/03 8:32:58 PM)
 */
public void start() {
	if (thread == null){
		//thread = new Thread(this);
		thread.start();
	}
}
/**
 * Insert the method's description here.
 * Creation date: (6/17/03 8:33:07 PM)
 */
public void stop() {
	thread.stop();
}
/**
 * Insert the method's description here.
 * Creation date: (6/16/03 11:45:02 PM)
 */
public void updateRecord() {
	
	try{
			String sqlString ="UPDATE tblVehicle SET Make = '"+ txtMake.getText() + "'," + "Model='" + txtModel.getText()+ "'," + "FirstName='"+txtFname.getText() + "'," + "LastName='" +txtLname.getText() + "'" + "WHERE ID='" + txtID.getText()+ "'" ;
			String url="jdbc:odbc:Vehicle";
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
			Connection con=DriverManager.getConnection(url);
			con.setAutoCommit(false);
			Statement expecting ; 