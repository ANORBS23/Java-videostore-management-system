/**
 * Represents party style board game
 * This is a specific type of board game
 * Inherits all properties and behaviour from the BoardGame class
 */
public class PartyGame extends BoardGame {
	/**
     * Constructs a PartyGame object with the provided product details.
     *
     * @param productID unique identifier for the product
     * @param productCategory category of the product (BOARDGAME)
     * @param productName name of the party game
     * @param purchaseCost supplier cost of the product
     * @param quantityInStock number of items available in stock
     * @param price retail price for customers
     * @param maxPlayers maximum number of players supported
     */
	public PartyGame(int productID,ProductCategory productCategory,String productName,
			double purchaseCost,int quantityInStock,double price,int maxPlayers
			) {
		super(productID,productCategory,productName,purchaseCost,quantityInStock,price,maxPlayers);
	}
}

