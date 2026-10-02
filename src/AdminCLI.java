import java.util.ArrayList;
import java.util.Scanner;
/**
 * Provides a command line interface for the administrators
 * Allows admin  to view and add new products to the stock
 * Handles user input, validation and delegates logic to stockManager 
 * regarding the alteration of the stock
 */
public class AdminCLI {
    /** Message displayed for an invalid input */
    public final static String INVALID = "Invalid input";
    /**
     * Runs the interface loop until the user logs out
     * 
     * @param consoleInput Scanner to read user input
     */

    public static void run(Scanner consoleInput) {
        System.out.println("ADMIN VIEW");
        //Main admin loop - continues until user selects log out

        while (true) {
            printAdminMenu();

            int selection = readInt(consoleInput);

            switch (selection) {
                case 1:
                    viewProducts();
                    consoleInput.nextLine();
                    break;

                case 2:
                    addProduct(consoleInput);
                    consoleInput.nextLine();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(INVALID);
            }
        }
    }
    /**
     * Displays all products sorted by price (high to low)
     * As long as there are products available to purchase
     */
    private static void viewProducts() {
        ArrayList<Product> list = StockManager.getAllProducts();

        if (list.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        //sort products by highest order first
        list.sort((p1,p2)-> Double.compare(p2.getPrice(),p1.getPrice()));

        for (Product p : list) {
            displayProductForAdmin(p);
        }
    }
    /**
     * Displays product information to the admin so that it is easy to read
     * @param p
     */
    private static void displayProductForAdmin(Product p) {
		
		
		String type ="";
		//Determine product type using polymorphism
		if(p instanceof BoardGame) {
			type=(p instanceof PartyGame)? "party":"strategy";
			
		}else if(p instanceof Accessory) {
			type = (p instanceof AccessoryKit)? "accessory kit":
				(p instanceof Dice)? "dice":"miniature";
		}
		 System.out.printf(
			        "ID: %-4d | Category: %-10s | Type: %-16s | Product Name: %-30s | Price: £%-8.2f | Quantity: %-4d | Purchase Cost: £%-8.2f",
			        p.getProductId(),
			        p.getProductCategory(),
			        type,
			        p.getProductName(),
			        p.getPrice(),
			        p.getQuantityInStock(),
			        p.getPurchaseCost()
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
     * Handles adding new products by collecting user input from the admin
     * @param input - Scanner used for user input
     */
    private static void addProduct(Scanner input) {
        System.out.println("--- ADD NEW PRODUCT ---");

        try {
            int id = getUserId(input);
            ProductCategory category = getUserCategory(input);

            System.out.print("Enter Product Name: ");
            String name = input.nextLine().trim();

            double purchaseCost = getUserDouble(input, "Enter purchase cost: ");
            int quantity = getUserInt(input, "Enter Quantity in Stock: ");
            double retailPrice = getUserDouble(input, "Enter Sale Price: ");

            Product newProduct;
            //create new product depending on the selected category

            if (category == ProductCategory.BOARDGAME) {
                newProduct = createBoardGame(input, id, category, name,
                        purchaseCost, quantity, retailPrice);
            } else {
                newProduct = createAccessory(input, id, category, name,
                        purchaseCost, quantity, retailPrice);
            }

            if (newProduct != null) {
                StockManager.addProductToStockList(newProduct);
                System.out.println("Product added successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error: invalid input. Please try again.");
        }
    }
    /**
     * Prompts the user for a unique product Id
     * @param input - Scanner that is used to get user input
     * @return valid, non duplicate product Id
     */
    private static int getUserId(Scanner input) {
        while (true) {
            System.out.print("Enter Product ID: ");
            int id = Integer.parseInt(input.nextLine());

            if (!StockManager.checkIds(id)) {
                return id;
            }

            System.out.println("DUPLICATE ID DETECTED - try again.");
        }
    }
    /**
     * Prompts the user to select a product category
     * @param input - Scanner used to get the user input
     * @return selected ProductCategory
     */
    private static ProductCategory getUserCategory(Scanner input) {
        while (true) {
            System.out.println("Select Category: 1) Board Game 2) Accessory");
            int choice = Integer.parseInt(input.nextLine());

            if (choice == 1) return ProductCategory.BOARDGAME;
            if (choice == 2) return ProductCategory.ACCESSORY;

            System.out.println(INVALID);
        }
    }
    /**
     * Creates a BoardGame object based on user input
     * @param input - Scanner for input
     * @param id - Product Id
     * @param category - product category
     * @param name - product name
     * @param purchaseCost - supplier cost
     * @param quantity -  stock quantity
     * @param retailPrice - selling price
     * @return new BoardGame object
     */
    private static Product createBoardGame(Scanner input, int id,
            ProductCategory category, String name,
            double purchaseCost, int quantity, double retailPrice) {
//Ensure valid game type is selected
        while (true) {
            System.out.print("Choose 1) Strategy Game 2) Party Game: ");
            int type = Integer.parseInt(input.nextLine());

            int maxPlayers = getUserInt(input, "Enter the maximum number of players: ");

            if (type == 1) {
                return new StrategyGame(id, category, name,
                        purchaseCost, quantity, retailPrice, maxPlayers);
            } else if (type == 2) {
                return new PartyGame(id, category, name,
                        purchaseCost, quantity, retailPrice, maxPlayers);
            }

            System.out.println(INVALID);
        }
    }
    /**
     * 
     * @param input - Scanner for input
     * @param id - product Id
     * @param category - product category
     * @param name - product name
     * @param purchaseCost - supplier cost
     * @param quantity - stock quantity
     * @param retailPrice - selling price
     * @return new Accessory object
     */
    private static Product createAccessory(Scanner input, int id,
            ProductCategory category, String name,
            double purchaseCost, int quantity, double retailPrice) {
//loop until valid accessory type is selected
        while (true) {
            System.out.println("Choose 1) Accessory Kit 2) Dice 3) Miniature");
            int choice = Integer.parseInt(input.nextLine());

            System.out.print("Enter the compatibility: ");
            String compatibility = input.nextLine();
//select correct accessory subtype depending on the user's choice
            switch (choice) {
                case 1:
                    return new AccessoryKit(id, category, name,
                            purchaseCost, quantity, retailPrice, compatibility);
                case 2:
                    return new Dice(id, category, name,
                            purchaseCost, quantity, retailPrice, compatibility);
                case 3:
                    return new Miniature(id, category, name,
                            purchaseCost, quantity, retailPrice, compatibility);
                default:
                    System.out.println(INVALID);
            }
        }
    }
    /**
     * Prompts user for an integer input with validation
    *
    * @param input Scanner for input
    * @param message prompt message
    * @return valid integer
    */
    private static int getUserInt(Scanner input, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println(INVALID);
            }
        }
    }
    /**
     * Prompts user for an double input with validation
    *
    * @param input Scanner for input
    * @param message prompt message
    * @return valid double
    */

    private static double getUserDouble(Scanner input, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(input.nextLine());
            } catch (Exception e) {
                System.out.println(INVALID);
            }
        }
    }
    /**
     * Reads an integer safely from user input.
     *
     * @param input Scanner for input
     * @return parsed integer or -1 if invalid
     */
    private static int readInt(Scanner input) {
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
    /**
     * Displays the admin menu options
     */

    private static void printAdminMenu() {
        System.out.println("PLEASE SELECT ACTION BY INPUTTING THE CORRESPONDING NUMBER (or 0 for logout)");
        System.out.println("1) View all products");
        System.out.println("2) Add new product");
        System.out.println("0) Log out");
    }
}
