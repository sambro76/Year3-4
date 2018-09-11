package VehiclePro;

/**
 * Insert the type's description here.
 * Creation date: (6/3/03 9:33:21 AM)
 * @author: 
 */
public class Vehicle {
	String id,make,model,fname,lname;
/**
 * Insert the method's description here.
 * Creation date: (6/8/03 8:50:17 AM)
 */
public Vehicle(String id) {
	this.id=id;
	}
/**
 * Vehicle constructor comment.
 */
public Vehicle(String id, String make,String model, String fname,String lname) {
	//super();
	this.id=id;this.make=make;this.model=model;this.fname=fname;this.lname=lname;
}
}
