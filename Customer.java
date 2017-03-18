//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//Customer.java
//A customer has an arrival time, status, transaction time, Event, Ticket Type, 
//Number of Tickets, and a shopping cart
//********************************************************************************
import java.util.ArrayList;

/**
 * An immutable class that represents a customer
 * of a bank or similar place of business.
 * 
 */
public class Customer
{
	private int arrivalTime;
	private int transactionTime;
	private int customerNumber;
	private int customerTickets;
	private String customerStatus;
	private String customerEvent;
	private String typeOfTix;
	private TicketOrder shoppingCart;
	
	public Customer(int arrivalTime, int transactionTime, int customerNumber, String event, String typeTix, int tickets, String status){
		this.arrivalTime = arrivalTime;
		this.transactionTime = transactionTime;
		this.customerNumber = customerNumber;
		this.customerEvent = event;
		this.customerTickets = tickets;
		this.customerStatus = status;
		this.shoppingCart = new TicketOrder();
		this.typeOfTix= typeTix;
	}
	/**
	 *Returns the Time of customer arrival
	 * @return arrivalTime
	 */
	public int getArrivalTime() {
		return arrivalTime;
	} 
	/**
	 * Returns the time of customer transaction
	 * @return
	 */
	public int getTransactionTime() {
		return transactionTime;
	} 
	/**
	 * returns the customer number
	 * @return customerNumber
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * returns the status of the customer
	 * @return customerStatus
	 */
	public String getCustomerStatus() {
		return customerStatus;
	}
	/**
	 * returns the event of the customer
	 * @return customerEvent
	 */
	public String getEvent() {
		return customerEvent;
	}
	/**
	 * Returns the number of tickets
	 * @return customerTickets
	 */
	public int getTicketNumber(){
		return customerTickets;
	}
	/**
	 * Returns the ticket type of customer request
	 * @return typeOfTix
	 */
	public String getTicketType(){
		return typeOfTix;
	}
	/**
	 * Adds ticket object the the customers cart after purchase
	 * @param item
	 */
	public void addToCart(Ticket item){
		shoppingCart.add(item);
	}
	/**
	 * Returns a string representation of the customer
	 */
	public String toString(){
		String out = "Customer Number: " + this.customerNumber + "/n/tStatus: " + this.customerStatus
				+ "/n/tArrival Time: " + this.arrivalTime + "/n/tTransaction Time: " + this.transactionTime 
				+ "/n/tNumber of Tickets: " + this.customerTickets + "/n/tType: " + this.typeOfTix
				+ "/n/t/Event: " + this.customerEvent + "/n/tCart:" + this.shoppingCart.toString();
		return out;
	}
} 
