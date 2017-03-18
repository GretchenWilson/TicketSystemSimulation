//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//TicketBooth.java
//Uses Waitline.java as a framework to mimick the sales of a ticketing booth
//Simulations can be run in two different modes: With Status(true) and Without status(false)
//********************************************************************************
/** 
* A class that use a queue to simulate a waiting line.
 */
 import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
 
public class TicketBooth{
	private PriorityQueue<Customer> line;
	private HashMap<String, Event> possibleEvents;
	private Collection<Event> valueEvents; 
	private int numberOfArrivals;
	private int numberServed;
	private int totalTimeWaited;
  	private double totalProfit;
  	private LocalDate saleDate = LocalDate.now();
  	private int lostCustomers;
  	
  	
  	public TicketBooth(){
  		CompareCustomerStatus compare = new CompareCustomerStatus();
  		line = new PriorityQueue<Customer>(20,compare);
  		reset();
  	} 
  
  	/**
  	 * Task: Simulates waiting in line for a ticket booth 
  	 * @param duration 				time of simulation
  	 * @param arrivalProbability 	probability that a customer with arrive each minute
  	 * @param maxTransactionTime	the maximum possible transaction time of customer
  	 * @param events				a hashmap that includes all possible events
  	 * @param mode					is status mode enabled
  	 */
  	public void simulate(int duration, double arrivalProbability, int maxTransactionTime, HashMap<String,Event> events, boolean mode) {
  		
  		int transactionTimeLeft = 0;
  		lostCustomers = 0;
  		possibleEvents = events;
  		valueEvents = possibleEvents.values();
  		System.out.println("*************************************************************************************************************");
  		System.out.println("*******************************************Simulation Started************************************************");
  		System.out.println("*************************************************************************************************************");
  		for (int clock = 0; clock < duration; clock++) {
  			checkWaitTimes(clock);
  			if (Math.random() < arrivalProbability)  {
  				numberOfArrivals++;
  				Event event = randomEvent();
  				String eventTitle = event.getTitle();
  				String ticketType = randomTicketType(event);
  				int numberOfTickets = (int) (Math.round(Math.random()* 6 + 1));
  				int transactionTime = (int)(Math.random() * maxTransactionTime + 1);
  				String status = "none";
  				if (mode == true) status=randomStatus();
  				Customer nextArrival = new Customer(clock, transactionTime, numberOfArrivals, eventTitle, ticketType, numberOfTickets, status);
  				line.add(nextArrival);
  				System.out.println("Customer " + numberOfArrivals + " enters line at time " + 
        					clock + ". Waiting for "+ numberOfTickets + " " + ticketType + " tickets for "
        					+ eventTitle + ". Transaction time is " + transactionTime);
  			}
  			
  			if (transactionTimeLeft > 0)
  				transactionTimeLeft--;
  			else if (!line.isEmpty()) {
  				Customer nextCustomer = line.poll();
  				transactionTimeLeft = nextCustomer.getTransactionTime() - 1;
  				//System.out.println(nextCustomer.getArrivalTime());
  				int timeWaited = clock - nextCustomer.getArrivalTime();
  				//System.out.println(timeWaited);
  				totalTimeWaited = totalTimeWaited + timeWaited;
  				//System.out.println(totalTimeWaited);
  				numberServed++;
  				
  				System.out.println("Customer " + nextCustomer.getCustomerNumber()
                         + " begins service at time " + clock 
                         + ". Time waited is " + timeWaited);
  				
  				int tickets = nextCustomer.getTicketNumber(); 
  				String eventName = nextCustomer.getEvent(), ticketType = nextCustomer.getTicketType();
  				Event event = possibleEvents.get(eventName);
  				int days = (int)ChronoUnit.DAYS.between(saleDate, event.getDate());
  				
  				//System.out.println("Date Difference of " + event.getTitle() + " " + days);
  				if (event.getTicketsLeft() >= tickets){
  					for(int i=1; i<=tickets; i++){
  						Ticket tix = event.makeTicket(ticketType, days);
  						nextCustomer.addToCart(tix);
  						this.totalProfit += tix.getPrice();
  						//System.out.println(this.totalProfit);
  					}
  				}
  				else System.out.println("Insufficient number of tickets to complete transaction.");
  				
  			}
  		}
 
  	} 
  /**
   * Task: Returns the name of a random ticket type available for an event
   * @param event
   * @return ticket name
   */
  private String randomTicketType(Event event) {
	  
	  Object[] allowedTickets = event.getAllowedTickets().toArray();
	  String type = (String)allowedTickets[(int)(Math.round(Math.random() * (allowedTickets.length - 1)))];
	  return type;
	}
  /**
   * Task: Returns a randomly selected Event from the Hashmap
   * @return event
   */
  private Event randomEvent(){
	  Object[] valueEvents = possibleEvents.values().toArray();
	  Event selection = (Event)valueEvents[(int)(Math.round(Math.random() * (valueEvents.length -1)))];
	  return selection;
	  
  }
  /**
   * Task: Checks all the wait times of the customers in line and removes the ones
   * 		whose wait times have reached 10 minutes
   * @param clock
   */
  private void checkWaitTimes(int clock){
	  Iterator<Customer> scanTimes = line.iterator();
	  while (scanTimes.hasNext()) {
		  Customer cust = scanTimes.next();
		  if((clock - cust.getArrivalTime()) >=10) {
			  scanTimes.remove();
			  lostCustomers++;
			  System.out.println("Customer " + cust.getCustomerNumber() + " was waiting 10 minutes and has left the line.");
		  }
	  }
  }
  /**
   * Task: Assigns customer status based of the following probabilities: platinum-30%; gold–40%; silver–20%; none–10%
   * @return status 	of customer
   */
  private String randomStatus(){
	  double probability = Math.random();
	  if (probability <= 0.3)return "platinum";
	  if(probability <=0.7) return "gold";
	  if (probability <=0.9) return "silver";
	  if (probability <= 1.0) return "none";
	  else return "none";
  }
  
  	/** Task: Displays summary results of the simulation. 
   */ 
  public ArrayList<Double> displayResults() {
    System.out.println();
    System.out.println("Total Events Available = " + valueEvents.size());
    System.out.println("Number served = " + numberServed);
    System.out.println("Customers left = " + lostCustomers);
    System.out.println("Total time waited = " + totalTimeWaited);
    System.out.println("Total profit = $" + this.totalProfit);
    double averageTimeWaited = ((double)totalTimeWaited) / numberServed;
    System.out.println("Average time waited = " + averageTimeWaited);
 
    int leftInLine = numberOfArrivals - numberServed;
    System.out.println("Number left in line = " + leftInLine);
    ArrayList<Double> results = new ArrayList<Double>();
	results.add(averageTimeWaited); results.add(this.totalProfit); results.add((double)this.lostCustomers);
	return results;
  } 
  
  
  /** Task: Initializes the simulation. 
  */ 
  public final void reset() {
    line.clear();
    numberOfArrivals = 0;
    numberServed = 0;
    totalTimeWaited = 0;
    totalProfit = 0.0;
  } 
}

