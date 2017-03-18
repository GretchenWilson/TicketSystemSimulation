import java.util.Comparator;
import java.util.HashMap;
//********************************************************************************
//Gretchen Wilson
//CMSC 256 Fall 2016
//CompareCustomerStatus.java
//CompareCustomerStatus implements comparator to compare customers by their status
//********************************************************************************
public class CompareCustomerStatus implements Comparator<Customer> {
	private HashMap<String,Integer> statusValues;
	
	public CompareCustomerStatus(){
		statusValues = new HashMap<String,Integer>();
		statusValues.put("platinum", 1);
		statusValues.put("gold", 2);
		statusValues.put("silver", 3);
		statusValues.put("none", 4);
	}
	@Override
	public int compare(Customer arg0, Customer arg1) {
		int value0 = (int)statusValues.get(arg0.getCustomerStatus());
		int value1 = (int)statusValues.get(arg1.getCustomerStatus());
		if(value0 != value1) return value0 - value1;
		else return arg0.getArrivalTime() - arg1.getArrivalTime();
	}

}
