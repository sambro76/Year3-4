package VehiclePro;

/**
 * Insert the type's description here.
 * Creation date: (6/10/03 4:39:00 AM)
 * @author: 
 */
 import java.sql.*;
 import sun.jdbc.odbc.*;
 import java.awt.*;
 import java.util.*;
 public class connectDb {
	String id;

	
/**
 * connectDb constructor comment.
 */
public connectDb() {
	super();
	
	
}
/**
 * Insert the method's description here.
 * Creation date: (6/10/03 4:47:17 AM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	String id,make,model,fName,lName;	
	
		try{
			Properties p = new Properties();
			String sqlString ="Select * From tblVehicle";
			String url="jdbc:odbc:Vehicle";
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			
			Connection con=DriverManager.getConnection(url);
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sqlString);
			
				while(rs.next()){
					id=rs.getString(1);
				    make=rs.getString(2);
				    model=rs.getString(3);
				    fName=rs.getString(4);
				    lName=rs.getString(5);
				    	
					System.out.println(id+" "+ make +"  "+  model+"   "+fName + "  " + lName);			
			}
				
		con.close();
		}
	catch (Exception e){
	//System.out.println(e.getMessage());
	}
	
}
}
