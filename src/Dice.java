/**
 * Represents dice accessory product
 * This is a specific Accessory
 * Inherits all properties and behaviour from Accessory class
 */
public class Dice extends Accessory {
	/**
	 * Constructs an Dice object with all required details
	 * @param productID - unique identifier for the product
	 * @param productCategory - category of the product (ACCESSORY)
	 * @param productName - name of the accessory
	 * @param purchaseCost - cost the supplier paid for the stock
	 * @param quantityInStock - number of items available in stock
	 * @param price - retail price customers pay for the product
	 * @param compatibility - system/game the accessory is compatible with
	 */
	public Dice(int productID,ProductCategory productCategory,String productName,
			double purchaseCost,int quantityInStock,double price,String compatibility
			) {
		super(productID,productCategory,productName,purchaseCost,quantityInStock,price,compatibility);
	}

}
