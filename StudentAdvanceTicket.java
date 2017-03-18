//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//StudentAdvanceTicket.java
//StudentAdvanceTicket is a class that extends the FixedPriceTicket class
//Represents a ticket that is 1/2 the price of an advance ticket 
//given the days before event
//********************************************************************************
public class StudentAdvanceTicket extends AdvanceTicket {
	/**
	 * Parameterized constructor with the number of days before event
	 * @param days
	 */
	public StudentAdvanceTicket(double price, int days) {
		super(price, days);
		// TODO Auto-generated constructor stub
		super.setPrice(super.getPrice()/2);
		super.setType("StudentAdvanceTicket");
	}
	/**
	 * @return String representation of this ticket type
	 */
	public String toString(){
	
		return super.toString();
	}

}
