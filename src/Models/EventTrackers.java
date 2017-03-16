package Models;

public interface EventTrackers {

	
	/**
	 * Notifies an object that a certain time has been reached. The notified object can then 
	 * figure out what event is scheduled and can send out the corresponding notification.
	 */
	public void notifyEventTimeHasBeenReached();
	
}
