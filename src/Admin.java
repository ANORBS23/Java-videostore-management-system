/**
 * Represents an administrator for the system
 * An admin has full access to manage products and system operations
 * This class extends the User class and inherits all user properties
 */
public class Admin extends User {
	
	/**
	 * 
	 * @param userID - unique identifier for the admin
	 * @param name - name of the admin
	 * @param address - the admin's address
	 */
	public Admin(int userID,String name,Address address) {
		super(userID,name,address);
		
	}
	
	/**
	 * Returns a formatted string representation of the admin
	 * 
	 * @return string containing admin ID, name and address
	 */
@Override
    public String toString() {
		return "Admin ID:"+getUserID()+",Name :"+getName()+",Address :"+getAddress();
}	


}
