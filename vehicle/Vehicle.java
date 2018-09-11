package vehicle;

/**
 * Insert the type's description here.
 * Creation date: (18/10/03 2:41:43 AM)
 * @author: 
 */
public class Vehicle {
	String id,make,model,year,fname,lname;
/**
 * Insert the method's description here.
 * Creation date: (18/10/03 2:41:43 AM)
 */
public Vehicle(String id) {
	this.id=id;
	}
/**
 * Vehicle constructor comment.
 */
public Vehicle(String id, String make,String model,String year, String fname,String lname) {
	
	this.id=id;
	this.make=make;
	this.model=model;
	this.year=year;
	this.fname=fname;
	this.lname=lname;
}
}
