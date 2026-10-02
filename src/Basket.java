/**
 * Represents an item in the customer's shopping basket
 * Stores both the product and the quantity by the user
 * Provides functionality to calculate subtotal for each time and update quantity
 */
public class Basket {
	private Product product;/**Product added to the basket */
	private int quantity;/**Quantity selected by the user */
	/**
     * Constructs a Basket item with a product and quantity
     *
     * @param product the product being added to the basket
     * @param quantity the quantity of the product
     */
	public Basket(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	/**
     * Gets the product in the basket
     *
     * @return the product object
     */
	public Product getProduct() {
		return product;
	}
	/**
     * Gets the quantity in the basket
     *
     * @return the quantity object
     */
	public int getQuantity() {
		return quantity;
	}
	/**
     * Increases the quantity of the product in the basket
     *
     * @param amount the number of items to add
     */
	public void addQuantity(int amount) {
		//Increased quantity by the specified value
		this.quantity+=amount;
		
	}
	/**
	 * Calculates the subtotal price for this basket item
	 * @return total cost for the item (price x quantity)
	 */
	public double getSubTotal() {
		return product.getPrice()*quantity;
	}
	/**
     * Returns a formatted string representation of the basket item
     *
     * @return string showing product name, quantity, and subtotal
     */
	@Override
	public String toString() {
		return product.getProductName()+" x"+quantity+" = £"+getSubTotal();
	}
}
