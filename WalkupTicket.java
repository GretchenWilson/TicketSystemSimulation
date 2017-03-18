//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//WalkupTicket.java
//WalkupTicket is a class that extends the FixedTicketPriceTicket class
//It represents a ticket bought at the event
//********************************************************************************
public class WalkupTicket extends FixedPriceTicket{
	/**
	 * Default constructor that sets the ticket price to $50.00
	 */
	public WalkupTicket() {
		super(50.0);
		super.setType("WalkupTicket");
		
	}
	/**
	 * Parameterized constructor the sets the ticket price
	 * @param Price
	 */
	public WalkupTicket(double Price) {
		super(Price);
		super.setType("WalkupTicket");
		// TODO Auto-generated constructor stub
	}
	/**
	 * Method returns the ticket price 
	 * @return ticket price
	 */
	@Override
	public double getPrice() {
		
		return super.getPrice();
	}
	

}
