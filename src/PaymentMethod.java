/**
 * Represents a payment method used to process customer transactions
 * Defines a common interface for different payment types such as
 * PayPal and Credit Card
 */
public interface PaymentMethod {
	/**
     * Processes a payment using the implemented payment method
     *
     * @param total the total amount to be paid
     * @param address the billing or delivery address of the customer
     * @return a Receipt object if payment is successful, otherwise null
     */
	Receipt processPayment(double total, Address address);
		
	
	
	

}
