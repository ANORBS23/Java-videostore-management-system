
import java.util.regex.*;
import java.time.LocalDate;
/**
 * Represents a PayPal method
 * Implements the PaymentMethod interface and processes payments
 * Using a email address which is validated using regex
 */
public class PayPalPayment implements PaymentMethod {
	/**The PayPal email */
	private String email;
	 /**
     * Constructs a PayPalPayment object with the provided email
     *
     * @param email the PayPal account email address
     */
	public PayPalPayment(String email) {
		
		this.email = email;
	}
	/**
     * Processes a payment using PayPal
     * Validates the email format before generating a receipt
     *
     * @param total the total amount to be paid
     * @param address the billing address of the customer
     * @return a Receipt object if payment is successful, otherwise null
     */
	@Override
	public Receipt processPayment(double total,Address address) {
		try {
			//Regex pattern to validate email format
			String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
		//Check if the email is in the correct format
			if(matcher.matches()==true) {
				
				String ReceiptMessage = total+" paid via PayPal using "+email+" on "+LocalDate.now()+"."
						+ "Billing address "+address.getHouseNumber()+", "+address.getPostcode()+", "+address.getCity();
				
				return new Receipt(ReceiptMessage);
				
			}
			else {
				System.out.println("The email entered is invalid");
				return null;
			}
		}catch(Exception e) {
			System.out.println("Unexpected error occured"+e.getMessage());
			return null;
		}
		
		}
	
	

}
