import java.util.ArrayList;
/**
 * Represents a customer in the system
 * Extends the user class and manages a shopping basket
 * including adding items, calculating totals and displaying the shopping basket
 */
public class Customer extends User {
	/** The customer's shopping basket */
	private  ArrayList<Basket> shoppingBasket;
	/**
     * Constructs a Customer object with user details
     *
     * @param userID unique identifier for the customer
     * @param name name of the customer
     * @param address the customer's address
     */
	public Customer(int userID,String name,Address address) {
		super(userID,name,address);
		this.shoppingBasket = new ArrayList<>();
	}
	/**
     * Gets the customer's shopping basket
     *
     * @return list of basket items
     */
	public ArrayList<Basket> getShoppingBasket(){
		return shoppingBasket;
	}
	/**
     * Calculates the total cost of all items in the basket
     *
     * @return total price of all basket items
     */
	public double calculateTotal() {
		double total=0.0;
		//Sum all of the basket sub totals together
		for(Basket item : shoppingBasket) {
			total+=item.getSubTotal();		
		}
		return total; 
	}
	/**
     * Adds a product to the basket. If the product already exists,
     * its quantity is increased instead of adding a duplicate entry
     *
     * @param product the product to add
     * @param quantity the quantity to add
     */
	public void addBasket(Product product, int quantity) {
		for(Basket item : shoppingBasket) {
			if(item.getProduct().getProductId()==product.getProductId()) {
				item.addQuantity(quantity);
				return; 
			}
		}
		//Add new item if it is not already present
		shoppingBasket.add(new Basket(product, quantity));
	}
	/**
	 * Clears all the products from the shopping basket
	 */
	public void clearBasket() {
		try {
			shoppingBasket.clear();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * Adds a basket object into the shopping basket
	 * 
	 * @param item - item that is added to the shopping basket
	 */
	
	
	/**
     * Returns a formatted string representation of the customer
     *
     * @return string containing customer ID, name, and address
     */
	@Override
	public String toString() {
		return "CustomerID :"+getUserID()+", Name : "+getName()+",Address :"+getAddress().toString(); 
	}
	/**
     * Displays the contents of the shopping basket along with the total cost
     */
	public void displayShoppingBasket() {
		if(shoppingBasket.isEmpty()) {
			System.out.println("Your Basket is empty");
		}
		else {
			System.out.println("---Your Shopping Basket---");
			for(Basket item : shoppingBasket) {
				//Split between the different instances
				System.out.println("Item: "+item);
			}
			System.out.println("Total Amount : £"+calculateTotal());
	
		}
	}
	

}
