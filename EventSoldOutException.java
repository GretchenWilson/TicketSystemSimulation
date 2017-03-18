/**
 * Exception class for sold out events
 */
public class EventSoldOutException extends IllegalArgumentException{
	
	public EventSoldOutException(String message) {
		super(message);
	}

}
