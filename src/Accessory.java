/**
 * Represents an accessory product in the system
 * Extends the product class and adds compatibility for different games/systems
 */
public class Accessory extends Product {
	/**
	 * The compatibility of the product (e.g.universal, Ancient Battles)
	 */
	private String compatibility;
	/**
	 * Constructs an Accessory object with all required details
	 * @param productID - unique identifier for the product
	 * @param productCategory - category of the product (ACCESSORY)
	 * @param productName - name of the accessory
	 * @param purchaseCost - cost the supplier paid for the stock
	 * @param quantityInStock - number of items available in stock
	 * @param price - retail price customers pay for the product
	 * @param compatibility - system/game the accessory is compatible with
	 */
	public Accessory(int productID,ProductCategory productCategory,String productName,
			double purchaseCost,int quantityInStock,double price,String compatibility
			) {
		super(productID,productCategory,productName,purchaseCost,quantityInStock,price);
		this.compatibility = compatibility;
	}
	/**
	 * Returns string representation of the accessory including the compatibility
	 * This is the same format that is used for saving data into the stock file
	 * 
	 * @return formatted string containing product details
	 */
@Override
public String toString() {
	return getProductId()+";"+getProductCategory()+";"+getProductName()+";"
			+getPrice()+";"+getQuantityInStock()+";"+getPurchaseCost()+";"+compatibility;
}
public String getCompatibility() {
	return compatibility;
}
/**
 * Gets the compatibility of the accessory
 * 
 * @returns compatibility string
 */
}
