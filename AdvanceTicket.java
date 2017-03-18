//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//AdvanceTicket.java
//AdvanceTicket is a class that extends the FixedPriceTicket class
//Represents a ticket bought less than 10 days before event for one price 
//and greater than 10 days for another price
//********************************************************************************
public class AdvanceTicket extends FixedPriceTicket{
	
	/**
	 * Parameterized constructor that takes in the number of days before an event
	 * @param days
	 */
	public AdvanceTicket(double price, int days) {
		super(price);
		super.setType("AdvanceTicket");
		if (days < 0) {
			throw new IllegalArgumentException("Days before event cannot be less than zero");
		}
		if (days >= 10) {
			super.setPrice(super.getPrice()*0.9);
		}
		//if (days < 10) {
			//super.setPrice(40);
		//}
	}
	/**
	 * getPrice() returns the price of the ticket with the days given
	 * @return ticket price
	 */
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice();
	}

}
