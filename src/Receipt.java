/**
 * Represents a payment receipt generated after a successful transaction
 * Stores the details of the payment in a formatted string
 */
public class Receipt {
	 /** The receipt details containing payment information */
	private String details;
	 /**
     * Constructs a Receipt object with the provided details
     *
     * @param details formatted string containing payment information
     */
	public Receipt(String details) {
		this.details = details;
	}
	  /**
     * Returns the receipt details as a string
     *
     * @return formatted receipt information
     */
	@Override
	public String toString() {
		return this.details;
	}

}
