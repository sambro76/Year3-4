package vehicle;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import sun.jdbc.odbc.*;

public class VehicleRegistration extends Frame {
	Hashtable hash;
	
	Panel all=new Panel(new BorderLayout());
	Panel pTxt=new Panel(new GridLayout(6,2,10,2));
	Panel pBut=new Panel();
	Panel pTitle=new Panel();
	Panel pPic =new Panel(new BorderLayout());
	Panel pNone=new Panel(new FlowLayout());
	
	TextField txtID;
	TextField txtMake;
	TextField txtModel;
	TextField txtYear;
	TextField txtFname;
	TextField txtLname;
	Frame f=new Frame();
	Vehicle v;
/**
 * VehicleRegistration constructor comment.
 */
public VehicleRegistration() {
	super("Vehicle Registration");
	
	Label Lid=new Label("ID:",Label.RIGHT);
	Label Lmake=new Label("Make:",Label.RIGHT);
	Label Lmodel=new Label("Model:",Label.RIGHT);
	Label Lyear=new Label("Year:",Label.RIGHT);
	Label Lfname=new Label("First Name:",Label.RIGHT);
	Label Llname=new Label("Last Name:",Label.RIGHT);
	Label title=new Label("VEHICLE REGISTRATION");
	title.setForeground(Color.red);
	title.setFont(new Font("Serif",Font.BOLD,20));
	pTitle.add(title);
	pTitle.setBackground(new Color(20,80,100));
	
	txtID=new TextField("");
	txtMake=new TextField("");
	txtModel=new TextField("");
	txtYear=new TextField("");
	txtFname=new TextField("");
	txtLname=new TextField("");
	
	Button bAdd=new Button("Add");
	Button bSearch=new Button("Search");
	Button bUpdate=new Button("Update");//bUpdate.setEnabled(false);
	Button bDelete=new Button("Delete");//bDelete.setEnabled(false);
	Button bClear=new Button("Clear");//bClear.setEnabled(false);
	Button bQuit=new Button("Exit");
	
	pTxt.add(Lid);pTxt.add(txtID);	
	pTxt.add(Lmake);pTxt.add(txtMake);
	pTxt.add(Lmodel);pTxt.add(txtModel);
	pTxt.add(Lyear);pTxt.add(txtYear);
	pTxt.add(Lfname);pTxt.add(txtFname);
	pTxt.add(Llname);pTxt.add(txtLname);
	
	pBut.add(bAdd);pBut.add(bSearch);pBut.add(bUpdate);
	pBut.add(bDelete);pBut.add(bClear);
	pBut.add(bQuit);
	pBut.setBackground(new Color(100,100,20));
	Label none=new Label("");
	pNone.add(none);
	
	all.add(pTitle,BorderLayout.NORTH);
	all.add(pNone,BorderLayout.EAST);
	all.add(pTxt,BorderLayout.CENTER);//Add textfield Panel
	all.add(pBut,BorderLayout.SOUTH); //add Button panel
	all.setBackground(Color.lightGray);
	add(all);
	setVisible(true);
	setResizable(false);
	setLocation(280,200);
	pack();
	connect();

	//Set enable window closing
	addWindowListener(new WindowAdapter(){
	public void windowClosing(WindowEvent we){
		dispose();
	}
	});

	//Add New Record
	bAdd.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae){
	addRecord();
 	}
	});
	
	// Search Record
	bSearch.addActionListener(new ActionListener(){		
		public void actionPerformed(ActionEvent ae){
		connect();
		v =(Vehicle)hash.get(txtID.getText());
				if (v==null)
					new MsgBox(f," No car has been assigned to this ID "," Ok ","","Car's ID not found ");
			 	else{				
				 	txtMake.setText(v.make);
					txtModel.setText(v.model);
					txtYear.setText(v.year);
					txtFname.setText(v.fname);
					txtLname.setText(v.lname);
					txtID.setEditable(false);
				}
		}
	});

	//Update Record
	bUpdate.addActionListener(new ActionListener(){		
		public void actionPerformed(ActionEvent ae){		
			v =(Vehicle)hash.get(txtID.getText());				
			 	if (v==null)
				 	new MsgBox(f," Please search first!!! "," Ok ",""," Neet a record... ");
			 	else{
				 	MsgBox y=new MsgBox(f," Are you sure to update this record? ","Yes","No","Update Record");
				 		if(y.getYesNo()) 	//call to 'getYesNo' method in MsgBox class
							updateRecord();
				 		else y.dispose();
				 }
			 }		
	});
		
	//Clear TextField's content
	bClear.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			clear();
		}
	});
	
	//Delete Record 
	bDelete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
	 	if (v==null)
		 	new MsgBox(f," Please search first!!! "," Ok ",""," Need a record... ");
	 	else{
			MsgBox y=new MsgBox(f," Are you sure to delete this record? ","Yes"," No "," Confirm... ");
			if(y.getYesNo()){
				deleteRecord();		//execute SQL in deleteRecord method
		    	hash.remove(txtID.getText());
				clear();
				}
			else y.dispose();
		}
		}
	});
	// Exit the current window
	bQuit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){			
			dispose();
			System.exit(0);
			}
	});
}
/**
 * Insert the method's description here.
 * Creation date: (10/19/03 11:32:36 PM)
 */
public void addRecord() {	
	   if(hash.containsKey(txtID.getText())){
			MsgBox msg=new MsgBox(f,"The car Id already exists. Use a different Id."," Ok ","Close","Doublicated Data");
		}
		else{	
			Vehicle	v =new Vehicle(txtID.getText(),txtMake.getText(),txtModel.getText(),txtYear.getText(),txtFname.getText(),txtLname.getText());
			hash.put(txtID.getText(),v);				
			try{
				String sqlString ="INSERT INTO tblVehicle VALUES ('" + txtID.getText() + "','" + 
			                    txtMake.getText()+ "','" + txtModel.getText()+"','" + txtYear.getText()+"','" +txtFname.getText()+"','" + txtLname.getText()+ "')";
				String url="jdbc:odbc:dv";
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
			clear();
	}
}
public void clear() {	
	txtID.setText("");
	txtMake.setText("");
	txtModel.setText("");
	txtYear.setText("");
	txtFname.setText("");
	txtLname.setText("");
	txtID.requestFocus();
	txtID.setEditable(true);
}
/**
 * Insert the method's description here.
 * Creation date: (10/19/03 4:29:27 AM)
 */
public void connect() {
	hash=new Hashtable();
	try{
		String sqlString ="Select * From tblVehicle";
		String url="jdbc:odbc:dv";
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
		Connection con=DriverManager.getConnection(url);
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery(sqlString);
		while(rs.next()){
			 String id=rs.getString(1);
			 String make=rs.getString(2);
			 String model=rs.getString(3);
			 String year=rs.getString(4);
			 String fName=rs.getString(5);
			 String lName=rs.getString(6); 
			 Vehicle v=new Vehicle(id,make,model,year,fName,lName);
			 hash.put(id,v);
		}
		con.close();
		}
	catch (Exception e){
	System.out.println(e.getMessage());
	}
}
/**
 * Insert the method's description here.
 * Creation date: (10/19/03 11:44:48 PM)
 */
public void deleteRecord() {
	try{
		String sqlString ="DELETE FROM tblVehicle WHERE ID = '"+ txtID.getText() + "'" ;
		String url="jdbc:odbc:dv";
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
 * Creation date: (18/10/03 2:41:43 AM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	new VehicleRegistration();
}
/**
 * Insert the method's description here.
 * Creation date: (6/16/03 11:45:02 PM)
 */
public void updateRecord() {
	try{
		String sqlString ="UPDATE tblVehicle SET Make = '"+ txtMake.getText() + "'," +"Year='"+txtYear.getText()+"' ,"+"Model='" + txtModel.getText()+ "'," + "FirstName='"+txtFname.getText() + "'," + "LastName='" +txtLname.getText() + "'" + "WHERE ID='" + txtID.getText()+ "'" ;
		String url="jdbc:odbc:dv";
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
		Connection con=DriverManager.getConnection(url);
		con.setAutoCommit(false);
		Statement stm=con.createStatement();				
		int ct=stm.executeUpdate(sqlString);
		con.commit();
		con.close();
		}
	catch (Exception e){
//	System.out.println(e.getMessage());
	}	
}
}
