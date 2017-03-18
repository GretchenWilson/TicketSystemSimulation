//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//Event.java
//Event is a class that represents an event with a date, title, price,
//and maximum number of tickets.
//********************************************************************************
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Event {
	private String title;
	private double fixedPrice;
	private int maxTix;
	private int ticketsLeft;
	private LocalDate eventDate;
	
	private Collection<String> allowedTickets = null;
	/**
	 * Default constructor sets all instance variables to default values
	 */
	public Event() {
		this.title = "Title Unknown";
		this.fixedPrice = 0;
		this.maxTix = 0;
		eventDate = LocalDate.of(0,0,0);
		allowedTickets = new ArrayList<String>();
		ticketsLeft = 0;
	}
	
	/**
	 * Parameterized constructor sets all instance variables to the given parameters
	 * @param Title
	 * @param price
	 * @param tix
	 * @param Date
	 */
	public Event(String Title, double price, int tix, LocalDate Date, Collection<String>alltix) {
		setTitle(Title);
		setFixedPrice(price);
		setMaxTix(tix);
		setDate(Date);
		setAllowedTix(alltix);
		
	}

	/**
	 * Sets the date of the event
	 * event is input as a string month/date/year
	 * @param Date
	 * @throws IllegalArgumentException
	 */
	public void setDate(LocalDate Date){
		if (Date.isBefore(LocalDate.now())) throw new IllegalArgumentException("The date of the event must be in the future.");
		eventDate = Date;
	}
	/**
	 * Returns the event of the date
	 * @return eventDate
	 */
	public LocalDate getDate(){
		return eventDate;
	}
	/**
	 * Sets the maximum number of tickets available for the event
	 * @param tix
	 * @throws IllegalArgumentException
	 */
	public void setMaxTix(int tix) throws IllegalArgumentException{
		if (tix > 0) {
			this.maxTix = tix; 
			this.ticketsLeft = maxTix;
		}
		else throw new IllegalArgumentException("The maximum number of tickets must be greater than zero.");
		ticketsLeft = maxTix;
	}
	/**
	 * 
	 * @return maximum number of tickets for the event
	 * @throws IllegalArgumentException
	 */
	public int getMaxTix() throws IllegalArgumentException {
		return maxTix;
	}
	/**
	 * Sets the collection of available ticket types
	 * @param ticketTypes 
	 */
	public void setAllowedTix(Collection<String> ticketTypes) {
		List<String> possibleTix = Arrays.asList("student", "advance", "walkup", "free");
		allowedTickets = new ArrayList<String>();
		for (String ticket: ticketTypes) {
			if (possibleTix.contains(ticket))allowedTickets.add(ticket);
			else throw new IllegalArgumentException("Ticket type " + ticket + " is not valid.");		
		}
	}
	/**
	 * 
	 * @return a collection of the allowed ticket types
	 */
	public Collection<String> getAllowedTickets(){
		return allowedTickets;
	}
	/**
	 * 
	 * @return the fixed price amount
	 */
	public double getFixedPrice() {
		return fixedPrice;
	}
	/**
	 * Sets the fixed price variable for this event
	 * @param price
	 * @throws IllegalArgumentException
	 */
	public void setFixedPrice(double price) throws IllegalArgumentException{
		if (price<0) throw new IllegalArgumentException("Fixed price for Event cannot be less than zero.");
		this.fixedPrice = price;
	}

	/**
	 * Returns the title of the event
	 * @return title of event
	 */
	public String getTitle() {
		return  title;
	}
	
	/**
	 * Sets the title for this event
	 * @param Title
	 */
	public void setTitle(String Title) {
		this.title = Title;
		
	}
	/**
	 * Returns the number of tickets left for this event
	 * @return ticketsLeft
	 */
	public int getTicketsLeft(){
		return ticketsLeft;
	}
	/**
	 * As long as there are more than 0 tickets left
	 * Creates a ticket object based of the requested ticket type 
	 * @param ticketRequest
	 * @param days
	 * @return Ticket object
	 * @throws IllegalArgumentException
	 * @throws EventSoldOutException
	 */
	public Ticket makeTicket(String ticketRequest, int days)throws IllegalArgumentException, EventSoldOutException{
		
		if(!this.allowedTickets.contains(ticketRequest))throw new IllegalArgumentException(ticketRequest + " tickets are not available for this event!!!");
		if (ticketsLeft == 0)throw new EventSoldOutException("Tickets are sold out for " + this.title);
		this.ticketsLeft--;
		if (ticketRequest.equalsIgnoreCase("walkup")) return new WalkupTicket(this.fixedPrice);	
		else if (ticketRequest.equalsIgnoreCase("advance")) return new AdvanceTicket(this.fixedPrice,days);
		else if (ticketRequest.equalsIgnoreCase("free")) return new FreeTicket();	
		else if (ticketRequest.equalsIgnoreCase("student")) return new StudentAdvanceTicket(this.fixedPrice,days);
		else return null;
	}
	/**
	 * @return A string representation of this event
	 */
	public String toString(){
		String str = String.format("Event Title: %s \n\tMaximum Tickets: %d \n\tFixed Price: $%5.2f", this.title, this.maxTix, this.fixedPrice);
		return str;
	}
}
