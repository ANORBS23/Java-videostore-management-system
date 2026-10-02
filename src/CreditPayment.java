
import java.time.LocalDate;
/**
 * Represents a credit payment method
 * Implements the PaymentMethod interface and processes payments
 * Uses a card number and security code
 */

public class CreditPayment implements PaymentMethod{
	/** 6 digit card number */
	private String cardNumber;
	/** 3 digit security code */
	private String securityCode;
	
	/**
     * Constructs a CreditPayment object with card details.
     *
     * @param cardNumber the credit card number (must be 6 digits)
     * @param securityCode the security code (must be 3 digits)
     */
	public CreditPayment(String cardNumber,String securityCode) {
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
	}
	/**
     * Processes a payment using credit card details
     * Validates the card number and security code before generating a receipt
     *
     * @param total the total amount to be paid
     * @param address the billing address of the customer
     * @return a Receipt object if payment is successful, otherwise null
     */
	@Override
	public Receipt processPayment(double total, Address address) {
		try {
			//validate  card number to ensure it is 6 digits
			//as well as validating the security code is 3 digits
			if(cardNumber.matches("\\d{6}")&&securityCode.matches("\\d{3}")) {
				String ReceiptMessage = total+" paid by Credit Card: "+cardNumber
						+"on "+LocalDate.now()+". Billing address"+
						 address.getHouseNumber()+", "
						+address.getPostcode()+", "
						+address.getCity();
				
				return new Receipt(ReceiptMessage);
			}
			else {
				//Invalid card details
				System.out.print("Unexpected error occured during payment occured, "
						+ "Please try again");
				return null;
			}
			
		}catch (Exception e){
			//catch exceptions
			System.out.println("Unexpected error occured"+e.getMessage());
			return null;
		}
		
	}
	

}
