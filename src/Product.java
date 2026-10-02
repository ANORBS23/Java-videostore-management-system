/**
 * Abstract base class representing a product in the system
 * Defines common properties and behaviour shared by all product types
 * such as board games and accessories
 * 
 * This class enforces implementation of the toString() method
 * in all subclasses
 */
public abstract class Product {
	
	    /** Unique identifier for the product */
	    private int productId;
        /** Category of the product (e.g. BOARDGAME or ACCESSORY) */ 
	    private ProductCategory productCategory;
	    /** Name of the product */   
	    private String productName;
	    /** Supplier purchase cost */   
	    private double purchaseCost;
	    /** Number of items currently in stock */
	    private int quantityInStock;
	    /** Retail price for customers */
	    private double price;

	    /**
	     * Constructs a product with the specified properties.
	     *
	     * @param productId unique product ID (must not be negative)
	     * @param productCategory category of the product
	     * @param productName name of the product
	     * @param purchaseCost supplier cost of the product
	     * @param quantityInStock stock quantity (must not be negative)
	     * @param price retail price for customers
	     * @throws IllegalArgumentException if productId or quantity is negative
	     */
	    public Product(int productId, ProductCategory productCategory, String productName, 
	                   double purchaseCost, int quantityInStock, double price) {
	        
	        // Validation logic
	        if (productId < 0) { throw new IllegalArgumentException("ID cannot be negative"); }
	        if (quantityInStock < 0) { throw new IllegalArgumentException("Stock cannot be negative"); }
	        
	        this.productId = productId;
	        this.productCategory = productCategory;
	        this.productName = productName;
	        this.purchaseCost = purchaseCost;
	        this.quantityInStock = quantityInStock;
	        this.price = price;
	    }
	    /**
	     * Gets the product ID
	     *
	     * @return product ID
	     */
    public int getProductId() {
        return productId;
    }
    /**
     * Gets the product category
     *
     * @return product category
     */
    public ProductCategory getProductCategory() {
        return productCategory;
    }
    /**
     * Gets the product name
     *
     * @return product name
     */
    public String getProductName() {
        return productName;
    }
    /**
     * Gets the quantity in stock
     *
     * @return stock quantity
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }
    /**
     * Gets the retail price of the product
     *
     * @return product price
     */
    public double getPrice() {
        return price;
    }
    /**
     * Gets the purchase cost
     *
     * @return purchase cost
     */
    public double getPurchaseCost() {
        return purchaseCost;
    }
    /**
     * Updates the stock quantity
     *
     * @param stockQuantity new stock value (must not be negative)
     * @throws IllegalArgumentException if stockQuantity is negative
     */
    public void setQuantityInStock(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        } else {
            this.quantityInStock = stockQuantity;
        }
    }
    /**
     * Returns a string representation of the product
     * Must be implemented by all subclasses
     *
     * @return formatted product details
     */
    @Override
    public abstract String toString();
}