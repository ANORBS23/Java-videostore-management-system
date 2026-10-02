import java.util.ArrayList;
import java.util.Scanner;
/**
 * Provides command line interface for customers
 * Allows customers to view products, manage their shopping basket
 * search/filter depending on the id/compatibility
 */

public class CustomerCLI {
	
    /**
     * Runs the customer interface loop until the user logs out
     *
     * @param consoleInput Scanner used for user input
     * @param customer the current customer using the system
     */
    public static void run(Scanner consoleInput,Customer customer) {

        System.out.println("CUSTOMER VIEW");
        //Main loop - until the user decides to log out
        while(true) {
        	printCustomerMenu();
        	int selection;
        	try {
        		selection = Integer.parseInt(consoleInput.nextLine().trim());
        	}catch(Exception e) {
        		System.out.println("Invalid input: "+e.getMessage());
        		continue;
        	}
        	switch(selection) {
        	case 1:
        		ViewProducts();
        		consoleInput.nextLine();
        		break;
        	case 2:
        		addToBasket(consoleInput, customer);
        		consoleInput.nextLine();
        		break;
        	case 3:
        		viewBasket(customer);
        		consoleInput.nextLine();
        		break;
        	case 4:
        		handlePurchase(consoleInput,customer);
        		consoleInput.nextLine();
        		break;
        	case 5:
        		customer.clearBasket();
        		System.out.println("Basket was cleared");
        		break;
        	case 6:
        		searchById(consoleInput);
        		consoleInput.nextLine();
        		break;
        	case 7:
        		FilterbyCompatibility(consoleInput);
        		consoleInput.nextLine();
        		break;
        	case 0:
        		return;
        	default:
        		System.out.println("Invalid input");
        		
        	}
        	
        }
    }
    /**
     * Filters accessories based on compatibility input by the user
     */
    private static void FilterbyCompatibility(Scanner consoleInput) {
		System.out.print("Enter compatability: ");
		String compatability = consoleInput.nextLine().trim();
		
		ArrayList<Product>results = StockManager.filterByCompatability(compatability);
		
		if(results.isEmpty()) {
			System.out.println("No matching accessories found");
			return;
		}
		System.out.println("--- RESULTS ---");
		for(Product p : results){
			displayProductForCustomer(p);
		}
		
	}
    /**
     * Searches for a product by ID
     */
	private static void searchById(Scanner consoleInput) {
		try {
			System.out.print("Enter product Id: ");
			int id = Integer.parseInt(consoleInput.nextLine().trim());
			Product product = StockManager.findByIds(id);
			
			if(product ==null) {
				System.out.println("No product found with that Id.");
				
			}else {
				displayProductForCustomer(product);
			}
			}catch(Exception e) {
				System.out.println("Invalid Input"+e.getMessage());
			}
	
		
	}

	
	/**
     * Handles the purchase process including payment selection,
     * validation, and stock reduction
     */
	private static void handlePurchase(Scanner consoleInput,Customer customer) {
		ArrayList<Basket> basket = customer.getShoppingBasket();
		if(basket.isEmpty()) {
			System.out.println("Your basket is empty");
			return;
		}
		//Ensure that there is enough stock before purchase
		if(!StockManager.checkStock(basket)) {
			System.out.println("Not enough available for one or more items");
			return;
		}
		double total = customer.calculateTotal();
		
		System.out.println("Choose payment method");
		System.out.println("1) PayPal");
		System.out.println("2) Credit Card");
		
		int choice = Integer.parseInt(consoleInput.nextLine().trim());
		//choose correct payment method depending on the user's input
		PaymentMethod paymentMethod = null;
		switch(choice) {
		case 1:
			System.out.print("Please enter your email you use for PayPal: ");
			String email = consoleInput.nextLine();
			paymentMethod = new PayPalPayment(email);
			break;
		case 2:
			System.out.print("Please enter your 6 digit card number: ");
			String cardNumber = consoleInput.nextLine();
			System.out.print("Please enter your 3 digit security code: ");
			String securityCode =consoleInput.nextLine();
			paymentMethod = new CreditPayment(cardNumber,securityCode);
			break;
		case 0:
			return;
		default:
			System.out.println("Enter a valid option");
			return;
			
		}
		Address address = customer.getAddress();
		viewBasket(customer);
		
		Receipt receipt = paymentMethod.processPayment(total, address);
		if(receipt==null) {
			System.out.println("Payment failed");
			return;
		}
		//reduce stock and clear basket after successful purchase
		StockManager.reduceStock(basket);
		basket.clear();
		System.out.println("Payment successful");
		
		
	}
	/**
     * Displays all products sorted by price (highest first)
     */
	private static void ViewProducts() {
	    ArrayList<Product> list = new ArrayList<>(StockManager.getAllProducts());

	    if (list.isEmpty()) {
	        System.out.println("No products available.");
	        return;
	    }
//sort product details (high to low) excluding the purchase cost
	    list.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));

	    for (Product p : list) {
	       displayProductForCustomer(p); 	    }
	}
	/**
	 * Displays the product information for the user excluding the purchase cost
	 * As required by the brief.
	 * @param p - current product to be displayed
	 */
	private static void displayProductForCustomer(Product p) {
	    String type = "";

	    // Determine product type using polymorphism
	    if (p instanceof BoardGame) {
	        type = (p instanceof PartyGame) ? "party" : "strategy";

	    } else if (p instanceof Accessory) {
	        type = (p instanceof AccessoryKit) ? "accessory kit"
	                : (p instanceof Dice) ? "dice" : "miniature";
	    }

	    System.out.printf(
	        "ID: %-4d | Category: %-10s | Type: %-16s | Product Name: %-30s | Price: £%-8.2f | Quantity: %-4d",
	        p.getProductId(),
	        p.getProductCategory(),
	        type,
	        p.getProductName(),
	        p.getPrice(),
	        p.getQuantityInStock()
	    );

	    if (p instanceof BoardGame) {

	        BoardGame bg = (BoardGame) p;
	        System.out.printf(" | Max Players: %-2d%n", bg.getMaxPlayers());

	    } else {

	        Accessory acc = (Accessory) p;
	        System.out.printf(" | Compatibility: %-15s%n", acc.getCompatibility());
	    }

	}
	 /**
     * Adds a product to the customer's basket
     */
	private static void addToBasket(Scanner consoleInput,Customer customer) {
	
		System.out.print("Enter product ID: ");
		int id = Integer.parseInt(consoleInput.nextLine());
		Product p = StockManager.findByIds(id);
		if(p ==null) {
			System.out.println("The item was not found, please try again");
			return;
		}
		System.out.print("Enter quantity: ");
		int quantity = Integer.parseInt(consoleInput.nextLine());
		
		if(quantity <= 0 ) {
			System.out.println("Invalid quantity");
			return;
		}
		//Add item using customer logic to prevent duplicates
		customer.addBasket(p,quantity);
		
		
		
	}
	/**
     * Displays the contents of the customer's basket
     */
	private static void viewBasket(Customer customer) {
		ArrayList<Basket>basket = customer.getShoppingBasket();
		if(basket.isEmpty()) {
			System.out.println("Your basket is empty");
			return;
		}
		System.out.println("--- YOUR BASKET ---");
		for(Basket item : basket) {
			System.out.println(item);
		}
		
	}
	 /**
     * Displays the customer menu options
     */
	private static void printCustomerMenu() {
        System.out.println("PLEASE SELECT ACTION BY INPUTTING THE CORRESPONDING NUMBER (or 0 for logout)");
        System.out.println("1) View all products");
        System.out.println("2) Add product to shopping basket");
        System.out.println("3) View contents of shopping basket");
        System.out.println("4) Purchase items in the basket");
        System.out.println("5) Cancel shopping basket");
        System.out.println("6) Lookup with product ID");
        System.out.println("7) Search/filter based on compatilbility");
        System.out.println("0) Log out");
    }
}