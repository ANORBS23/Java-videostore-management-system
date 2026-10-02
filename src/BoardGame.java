/**
 * Represents a board game product in the system
 * Extends the product class and includes extra information like max players
 */
public class BoardGame extends Product {
	/** maximum number of players for the board game */
	private int maxPlayers;
	/**
     * Constructs a BoardGame object with all required product details.
     *
     * @param productID unique identifier for the product
     * @param productCategory category of the product (BOARDGAME)
     * @param productName name of the board game
     * @param purchaseCost supplier cost of the product
     * @param quantityInStock number of items available in stock
     * @param price retail price for customers
     * @param maxPlayers maximum number of players supported by the game
     */
	public BoardGame(int productID,ProductCategory productCategory,String productName,
			double purchaseCost,int quantityInStock,double price,int maxPlayers
			) {
		super(productID,productCategory,productName,purchaseCost,quantityInStock,price);
		this.maxPlayers= maxPlayers;
		
		
	}
	/**
	 * Gets the maximum number of players for the board game
	 * @return maximum number of players for the board game
	 */
	public int getMaxPlayers() {
		return maxPlayers;
		
	}
	/**
     * Returns a string representation of the board game including all fields
     * This format is used for saving data to the stock file
     *
     * @return formatted string containing product details including purchase cost
     */
	@Override
	public String toString() {
		return getProductId()+";"+getProductCategory()+";"+getProductName()+";"
				+getPrice()+";"+getQuantityInStock()+";"+getPurchaseCost()+";"+maxPlayers;
	}

}
