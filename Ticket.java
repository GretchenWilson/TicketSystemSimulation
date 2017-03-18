import java.util.ArrayList;
import java.util.Random;
//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//Ticket.java
//Ticket represents an object with a serial number
//********************************************************************************

public abstract class Ticket {

	private int serialNumber;
	private static ArrayList<Integer> assignedNums = new ArrayList<Integer>();
	private String type;
	private double price;
	public Ticket() {
		//System.out.println("Initializing Ticket");
		
		boolean isNew = false;
		int num = 0;
		
		
		while (isNew==false) {
			boolean match = false;
			Random generateRand = new Random();
			num = generateRand.nextInt(8999) + 1000;
			//System.out.println(num);
			for (int i : assignedNums) {
				//System.out.println("Checking Serial Number");
				if (i==num) match = true;				
			}
			if (match != true) {
				//System.out.println("New Serial Number");
				isNew = true;
			}
		}
		serialNumber = num;
		assignedNums.add(serialNumber);
		
	}
	/**
	 * 
	 * @return type
	 * The ticket type of the ticket object
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * 
	 * @param typ
	 * Sets the private instance variable type for ticket objectww
	 */
	public void setType(String typ) {
		this.type = typ;
	}
	public void setPrice(double Price){
		if (Price<0) throw new IllegalArgumentException("Price of Ticket cannot be less than zero.");
		this.price = Price;
	}
	/**
	 * @category abstract 
	 * abstract class to be implemented by lower classes
	 */
	abstract double getPrice();

	/**
	 * 
	 * @return serial number of Ticket object
	 */
	public int getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @return String representation of Ticket object
	 */
	public String toString() {
		String serial = "(" + serialNumber + ")";
		String out = String.format("%-10s $%-10.1f", serial, this.price);

		return out;
	}
	

}
