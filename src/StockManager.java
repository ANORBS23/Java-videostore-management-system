import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileReader;
/**
 * Manages all product stock data
 * Handles loading products from the stock file, saving changes
 * checking stock availability, reducing stock after purchases
 * and searching/filtering products
 */

public class StockManager {
	 /** Name of the stock data file */
	private static final String STOCK_FILE = "Stock.txt";
	 /** List containing all products currently loaded into the system */
	static ArrayList<Product>allProducts = new ArrayList<Product>();
	
	/**
     * Updates the stock file using the current products in memory
     * Rewrites the full Stock.txt file in the required format
     */
	public static void updateStockFile() {
	   
	    try (PrintWriter writer = new PrintWriter(new FileWriter(STOCK_FILE))) {
	    	  // Convert each product object back into file format
	        for (Product p : allProducts) {
	            String line = "";

	            if (p instanceof BoardGame) {
	                
	                String type = (p instanceof PartyGame) ? "party" : "strategy";
	                
	                BoardGame bg = (BoardGame)p;
	                line = p.getProductId() + ";" + "board game" + ";" + type + ";" + 
	                       p.getProductName() + ";" + p.getPrice() + ";" + 
	                       p.getQuantityInStock() + ";" + p.getPurchaseCost() + ";"+bg.getMaxPlayers();
	            } 
	            else if(p instanceof Accessory) {
	            String type = (p instanceof AccessoryKit) ? "accessory kit":
	            	(p instanceof Dice)?"dice":"miniature";
	            Accessory accessory = (Accessory)p;
	            
	            line = p.getProductId() + ";" + "accessory" + ";" + type + ";" + 
	                       p.getProductName() + ";" + p.getPrice() + ";" + 
	                       p.getQuantityInStock() + ";" + p.getPurchaseCost() + ";"+accessory.getCompatibility();
	            }
	            else {
	                System.out.println("Item is not recognised: " + p.getProductName());
	                continue; 
	            }

	            writer.println(line);
	        }
	    } catch (Exception e) {
	        System.out.println("Error occurred updating stock: " + e.getMessage());
	    }
	}
	/**
     * Checks whether there is enough stock for every item in a basket
     *
     * @param userBasket basket items to check
     * @return true if all requested quantities are available otherwise false
     */
	public static boolean checkStock(ArrayList<Basket>userBasket) {
		//Check each item against current stock
		for(Basket item : userBasket) {
			Product stockProduct = findByIds(item.getProduct().getProductId());
			if(stockProduct == null) {
				return false;
				
					
				}
			if(item.getQuantity()>stockProduct.getQuantityInStock()) {
				System.out.println("Not enough stock for:"+stockProduct.getProductName()
				+" (Requested: "+item.getQuantity()+ ", Available: "+stockProduct.getQuantityInStock()+")");
			
				return false;
			}
				
			
		}
	
			return true;
	}
	 /**
     * Reduces stock quantities after a successful purchase
     * Also updates the stock file so changes are persistent
     *
     * @param basket purchased basket items
     */
	public static void reduceStock(ArrayList<Basket> basket) {
		//Reduce each purchased product's stock quantity
		for(Basket item : basket) {
			Product stockProduct = findByIds(item.getProduct().getProductId());
			if(stockProduct != null) {
				int newQuantity = stockProduct.getQuantityInStock() -item.getQuantity();
				stockProduct.setQuantityInStock(newQuantity);
				
			}
		
		}
		updateStockFile();
	}
	
	 /**
     * Loads product data from Stock.txt into the allProducts list
     * Creates the correct subclass depending on category and type
     */
	public static void loadData() {
	    allProducts.clear(); 

	    try (BufferedReader br = new BufferedReader(new FileReader(STOCK_FILE))) {
	        String line;
//Read file one line at a time
	        while ((line = br.readLine()) != null) {
	            if (line.trim().isEmpty()) continue;
//Splits the string when it finds a semi colon
	            String[] parts = line.split(";\\s*");
	            

	            int id = Integer.parseInt(parts[0].trim());
	            String categoryText = parts[1].trim().toLowerCase();
	            ProductCategory category;
	            if(categoryText.equals("board game")) {
	            	category = ProductCategory.BOARDGAME;
	            }
	            else if(categoryText.equals("accessory")){
	            	category = ProductCategory.ACCESSORY;
	            }
	            else {
	            	throw new IllegalArgumentException("Unkown Category: "+categoryText);
	            	
	            }
	            String type = parts[2].trim();
	            String name = parts[3].trim();
	            double retailPrice = Double.parseDouble(parts[4].trim());
	            int stockQuantity = Integer.parseInt(parts[5].trim());
	            double purchaseCost = Double.parseDouble(parts[6].trim());
	            String extraInfo = parts[7].trim();
//create correct sub class from file data
	            if (category == ProductCategory.BOARDGAME) {
	                int maxPlayers = Integer.parseInt(extraInfo);

	                if (type.equalsIgnoreCase("strategy")) {
	                    allProducts.add(new StrategyGame(id, category, name,
	                            purchaseCost, stockQuantity, retailPrice, maxPlayers));
	                } else {
	                    allProducts.add(new PartyGame(id, category, name,
	                            purchaseCost, stockQuantity, retailPrice, maxPlayers));
	                }
	            } else {
	                if (type.equalsIgnoreCase("dice")) {
	                    allProducts.add(new Dice(id, category, name,
	                            purchaseCost, stockQuantity, retailPrice, extraInfo));
	                } else if (type.equalsIgnoreCase("accessory kit")) {
	                    allProducts.add(new AccessoryKit(id, category, name,
	                            purchaseCost, stockQuantity, retailPrice, extraInfo));
	                } else {
	                    allProducts.add(new Miniature(id, category, name,
	                            purchaseCost, stockQuantity, retailPrice, extraInfo));
	                }
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error loading stock: " + e.getMessage());
	    }
	}
	/**
     * Adds a new product to the stock list if its ID is unique
     * Updates the stock file after adding
     *
     * @param item product to add
     * @return true if product was added false if ID already exists
     */
	public static boolean addProductToStockList(Product item) {
	    if (checkIds(item.getProductId())) {
	        return false;
	    }
	    allProducts.add(item);
	    updateStockFile(); 
	    return true;
	}
		
	/**
     * Gets all products currently loaded
     *
     * @return list of all products
     */
	public static ArrayList<Product> getAllProducts() {
		return allProducts;
	}
	public static boolean checkIds(int id) {
		    for (Product p : allProducts) {   
		        if (p.getProductId() == id) {
		            return true;
		        }
		    }
		    return false;
		
	}
	/**
     * Checks whether a product ID already exists
     *
     * @param id product ID to check
     * @return true if ID exists otherwise false
     */
	public static Product findByIds(int id) {
		for(Product p : allProducts) {
			if(p.getProductId() == id) {
				return p;
			}
		}
		return null;
	}
	/**
     * Filters accessories based on compatibility text
     *
     * @param compatibility compatibility keyword entered by the user
     * @return list of matching accessory products
     */
	public static ArrayList<Product>filterByCompatability(String compatability){
		ArrayList<Product> results = new ArrayList<Product>();
		for (Product p : allProducts) {
			if( p instanceof Accessory) {
				Accessory accessory = (Accessory)p;
				
				if(accessory.getCompatibility().toLowerCase().contains(compatability.toLowerCase())) {
					results.add(p);
				}
				
			}
		}
		return results;
		
	}
}
