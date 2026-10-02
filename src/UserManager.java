import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * Manages loading user data from the user accounts file.
 * Responsible for creating User objects (Admin or Customer)
 * based on file data.
 */
public class UserManager {
	/** Name of the user accounts file */
	private static final String USER_FILE = "UserAccounts.txt";
	 /**
     * Loads all users from the user file.
     * Each line is parsed and converted into either an Admin or Customer object.
     *
     * @return list of users loaded from the file
     */
	public static ArrayList<User> loadUsers(){
		ArrayList<User>users = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader(USER_FILE))){
			String line ="";
			
			while((line = br.readLine())!= null) {
				if(line.trim().isEmpty())continue;
				String[] parts = line.split(";");
				int userId = convertToInt(parts[0]);
				String name = parts[1].trim();
				int houseNumber = convertToInt(parts[2]);
				String postcode = parts[3].trim();
				String city = parts[4].trim();
				String role = parts[5].trim().toLowerCase();
				
				Address address = new Address(houseNumber,postcode,city);
				
				if(role.equals("admin")) {
					users.add(new Admin(userId,name,address));
				}else if (role.equals("customer")) {
					users.add(new Customer(userId,name,address));
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error loading users. "+e.getMessage());
		}
		return users;
				
		
	}
	/**
     * Converts a string into an integer.
     *
     * @param text string value to convert
     * @return parsed integer
     */
	public static int convertToInt(String text){
		return Integer.parseInt(text.trim());
	}

}
