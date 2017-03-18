//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//Project6.java
//Project6 reads the event list file and reads a collection of events
//It it the handler file for the ticket sales class
//********************************************************************************
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Project6 {
	
	private static HashMap<String,Event> Events= new HashMap<String, Event>();
	private static LocalDate currentDate = LocalDate.now();
	
	private static void printHeading() {
		String name = "\nGretchen Wilson";
		System.out.println(name);
		System.out.println("CSC 256 Fall 2016");
		System.out.println("Programming Project 6");
		System.out.println("Project6.java"); 		
	}
	/**
	 * Checks the input month and converts it 
	 * to the correct Month format
	 * @param mon
	 * @return
	 */
	private static Month getMonth(String mon) {
		if (mon.equalsIgnoreCase("Jan")||mon.equalsIgnoreCase("1")|| mon.equalsIgnoreCase("january"))return Month.JANUARY;
		if (mon.equalsIgnoreCase("Feb")||mon.equalsIgnoreCase("2")|| mon.equalsIgnoreCase("february"))return Month.FEBRUARY;
		if (mon.equalsIgnoreCase("Mar")||mon.equalsIgnoreCase("3")|| mon.equalsIgnoreCase("march"))return Month.MARCH;
		if (mon.equalsIgnoreCase("Apr")||mon.equalsIgnoreCase("4")|| mon.equalsIgnoreCase("april"))return Month.APRIL;
		if (mon.equalsIgnoreCase("May")||mon.equalsIgnoreCase("5")|| mon.equalsIgnoreCase("may"))return Month.MAY;
		if (mon.equalsIgnoreCase("Jun")||mon.equalsIgnoreCase("6")|| mon.equalsIgnoreCase("june"))return Month.JUNE;
		if (mon.equalsIgnoreCase("Jul")||mon.equalsIgnoreCase("7")|| mon.equalsIgnoreCase("july"))return Month.JULY;
		if (mon.equalsIgnoreCase("aug")||mon.equalsIgnoreCase("8")|| mon.equalsIgnoreCase("august"))return Month.AUGUST;
		if (mon.equalsIgnoreCase("sept")||mon.equalsIgnoreCase("9")|| mon.equalsIgnoreCase("september"))return Month.SEPTEMBER;
		if (mon.equalsIgnoreCase("oct")||mon.equalsIgnoreCase("10")|| mon.equalsIgnoreCase("october"))return Month.OCTOBER;
		if (mon.equalsIgnoreCase("nov")||mon.equalsIgnoreCase("11")|| mon.equalsIgnoreCase("november"))return Month.NOVEMBER;
		if (mon.equalsIgnoreCase("dec")||mon.equalsIgnoreCase("11")|| mon.equalsIgnoreCase("december"))return Month.DECEMBER;
		return null;

	}
	/**
	 * Processes the event list file scanner 
	 * and creates the Event Collection
	 * @param in
	 */
	private static void createEventList(Scanner in) {

		System.out.println("Processing Event List for: " + currentDate);
		int itemCount = 0;
		String title = null, month = null, weekday = null;
		Collection<String> allowedTix = null;
		LocalDate date = null;
		int day = 0, year = 0, numTix = 0;
		double price = 0;
		boolean ready = false;
		while (in.hasNextLine()){
			ready = true;
			title = month = weekday = null;
			allowedTix = null;
			date = null;
			day = year = numTix = 0; price = 0.0;
			itemCount = 0;
			String line = in.nextLine();
			//System.out.println(line);
			Scanner lineScan = new Scanner(line);
			lineScan.useDelimiter(";");
			while(lineScan.hasNext()){
				itemCount++;
				String item = lineScan.next().trim();
				if (itemCount ==1) title = item;
				else if (itemCount == 2) { // parse date
					Scanner dateScan = new Scanner(item);
					weekday = dateScan.next();
					weekday = weekday.substring(0, weekday.length() -1);
					month = dateScan.next();
					try {
						Month mon = getMonth(month);
						String dayString = dateScan.next();
						day = Integer.parseInt(dayString.substring(0, dayString.length() -1));
						year = dateScan.nextInt();
						date = LocalDate.of(year, mon, day);
						
						if(date.compareTo(currentDate) < 0)throw new Exception();
					}	
					catch (Exception e) {
						System.out.println("Incorrect Date Input for " + title + ". Event skipped");
						ready = false;
						continue;
					}
					dateScan.close();
				}
				if (itemCount == 3) { // parse price
					try{
						price = Double.parseDouble(item);
						if (price < 0) throw new Exception();
					}
					catch (Exception e) {
						System.out.println("Incorrect Price Input for " + title + ". Event skipped");
						ready = false;
						continue;	
					}}
				if (itemCount == 4) { // parse number of available ticket
					try{
						numTix = Integer.parseInt(item);
						if (numTix < 1) throw new Exception();
					}
					catch (Exception e) {
						System.out.println("Incorrect Ticket amount for " + title + ". Event skipped");
						ready = false;
						continue;	
					}}
				if (itemCount == 5) { // parse available tickets 
					allowedTix = new ArrayList<String>();
					Scanner availScan = new Scanner(item);
					Collection<String> possibleTix = Arrays.asList("student", "advance", "walkup", "free");
					boolean isProhibited = false;
					while(availScan.hasNext()) {
						String arg = availScan.next().trim().toLowerCase();
						if (arg.length() > 1) {
							String first = arg.substring(0,1);
							if (first.equals("-")){ //then argument is a prohibited
								arg = arg.substring(1, arg.length());
								isProhibited = true;
							}
						}
						if(isProhibited == false) {
							boolean present = allowedTix.contains(arg);
							if (present == false){
								if(possibleTix.contains(arg))allowedTix.add(arg); 
							
								if (arg.equals("all")){for(String tix : possibleTix) allowedTix.add(tix); }
							}}
						if (isProhibited ==true) allowedTix.remove(arg);		
					}
					availScan.close();
				}	
			}
			lineScan.close();
			if (itemCount==4 || allowedTix.size()==0) allowedTix = Arrays.asList("student", "advance", "walkup", "free");
			if(ready == true){
				Event event = new Event(title, price, numTix, date, allowedTix);
				System.out.println("Adding Event.......................\n" + event.toString());
				Events.put(title, event);
			}
		}	
	}
	/**
	 * Main method of Project6.java
	 * @param args
	 */
	public static void main(String[] args) {
		printHeading();
		String filename = null;
		boolean statusSimulation = false;
		int minutes = 0, count = 0, num = 0;
		File infile = null;
		Scanner in = null; 
		
		//Reads Program start input
		//if initial variables are incorrect program must resubmit
		while(filename == null && minutes == 0) {
			count ++;
			String textFile = "", number = "", mode = "";
			if (args.length != 5){
				System.out.println("Reinitiate Project6.java with the correct input:" 
						+ " java Project6 number_of_minutes event_file status_simulation(Y/N)" 
						+ "/n Example: java Project6 100 EventList.txt N ");
				System.exit(1);
			}
			else {
				if (count == 1) { //first round of input
					number = args[2]; textFile = args[3]; mode = args[4]; 
				}
				if (count > 1) {
					Scanner nextFile = new Scanner(System.in);
					number = nextFile.next(); textFile = nextFile.next(); mode = nextFile.next();
					nextFile.close();
				}
				try {
					infile = new File(textFile);
					in = new Scanner(infile);
					num = Integer.parseInt(number);
					if (num < 1 || num > 5000) throw new Exception("Incorrect argument for simulation time: "
											+ "/nMust enter simulation time in minutes.");
					if (mode.equalsIgnoreCase("No"))statusSimulation = false;
					else if (mode.equalsIgnoreCase("Yes")) statusSimulation = true;
					else throw new Exception("Incorrect argument for status mode: "
											+ "/nMust enter Yes for Status simulation or No for normal simulation.");
					
				}
				catch (FileNotFoundException e){
					System.out.println("File not Found. /nPlease enter: time in minutes,  Event filename, and Status Mode (Yes/No)");
					continue;
				}
				catch (Exception e) {
					System.out.println(e.getMessage() + "Please enter: time in minutes,  Event filename, and Status Mode (Yes/No)");
					continue;
				}
				createEventList(in);
				in.close();
				minutes = num; filename = textFile;
			}}			
			
				TicketBooth line = new TicketBooth();
				line.simulate(num, 0.5, 15, Events, statusSimulation);
				line.displayResults();
			
				
				
	}
}

