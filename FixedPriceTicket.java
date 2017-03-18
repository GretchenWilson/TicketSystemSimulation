//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//FixedPriceTicket.java
//FixedPriceTicket is a class that extends the Ticket class
//Represents a ticket of a fixed price
//********************************************************************************
public abstract class FixedPriceTicket extends Ticket {
	private double price;
	/**
	 * Default constructor for FixedPriceTicket
	 * @construct
	 */
	public FixedPriceTicket() {
		
		price = 0;
		super.setType("FixedPriceTicket");
	}
	/**
	 * Parameterized constructor for FixedPriceTicket
	 * @param Price
	 */
	public FixedPriceTicket(double Price) {
		super();
		setPrice(Price);
		super.setType("FixedPriceTicket");
	}
	/**
	 * @return price of ticket
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * sets the price of FixedPriceTicket
	 * @throws IllegalArgumentException
	 * @param Price
	 */
	public void setPrice(double Price) {
		if (price >=0) {
			price = Price;
		}
		else throw new IllegalArgumentException("Ticket price cannot be less than zero.");
	}
	/**
	 * @return String representation of FixedPriceTicket object
	 */
	public String toString() {
		
		String out = String.format("%-20s %-10s", super.getType(), super.toString());
		
		return out;
		
	}
}
