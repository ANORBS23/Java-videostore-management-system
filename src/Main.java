import java.util.ArrayList;
import java.util.Scanner;
/**
 * Entry point for the application
 * Load stock and user data, displays the welcome menu
 * Directs user to admin or customer command line interface
 * Depending on their choice
 */

public class Main {
	
    public static void main(String[] args) {
    	 /**
         * Starts the program and controls the main login/menu loop
         *
         * @param args command-line arguments, not used in this program
         */
    	
        Scanner consoleInput = new Scanner(System.in);
        // load Stock data from text file
        StockManager.loadData();
        // load user data from text file
        ArrayList<User>users = UserManager.loadUsers();
        

        System.out.println("WELCOME");
        //Main loop - until the user exits
        while (true) {
        	
            printWelcomeMenu(users);
            int selection =0;

            String line = consoleInput.nextLine().trim();
            try {
              selection = Integer.parseInt(line.trim());
            }catch(Exception e) {
            	System.out.println("Invalid input");
            	continue;
            }
            if(selection == 0 ) {
            	System.out.println("Goodbye");
            	return;
            }
            if(selection<1 || selection > users.size()) {
            	System.out.println("Invalid Input");
            	continue;
            }
            //Send the user to the correct command line interface
            User selectedUser = users.get(selection -1);
            if(selectedUser instanceof Admin) {
            	AdminCLI.run(consoleInput);
            	
            }else {
            	CustomerCLI.run(consoleInput, (Customer)selectedUser);
            }
        }
    }
    /**
     * Displays all available users and allows selection by number
     *
     * @param users list of users loaded into the system
     */
    private static void printWelcomeMenu(ArrayList<User>users) {

        // Display users and their roles
    	System.out.println("Please choose the corresponding number (or 0 to exit)");
    	for(int i=0;i<users.size();i++) {
    		User user = users.get(i);
    		String role = (user instanceof Admin)?"admin":"customer";
    		System.out.println((i+1)+")"+user.getUserID()+"| "+user.getName()+"| "+role);
    		
    		
    	}
    	System.out.println("0) Exit");
    }
}