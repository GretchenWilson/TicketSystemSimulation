import java.util.ArrayList;
import java.util.Collection;

//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//TicketOrder.java
//TicketOrder represents an order of a number of tickets.
//The order is held in a list and the total is calculated
//********************************************************************************

public class TicketOrder {
	private Collection<Ticket> order; 
	private double total;
	/**
	 * Default constructor of TicketOrder
	 * sets total to zero
	 */
	public TicketOrder(){
		order = new ArrayList<Ticket>();
		total = 0;
	}
	
	/**
	 * Add ticket to ticket order collection
	 * @param Ticket object
	 */
	public void add(Ticket tix) {
		order.add(tix);
	}
	
	/**
	 * Totals the price of the order
	 * @return order total
	 */
	public double totalPrice() {
		for (Ticket tix : order) {
			total += tix.getPrice();
			
		}
		return total;
	}
	/**
	 * @return string representation of the order
	 */
	public String toString() {
		totalPrice();
		String out = "Ticket Order: \n";
		for (Ticket tix : order){
			out += tix.toString() + "\n";
		}
		out += "----------------------------------------\n";
		String totalOut = String.format("%-20s %-10s $%-10.1f" , "Total", " ", this.total);
		out += totalOut;
		return out;
	}
	
}
