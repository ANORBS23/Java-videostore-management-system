/**
 * Abstract base class representing a user in the system
 * A user has a unique ID, name, and address
 * This class is extended by Admin and Customer classes
 */
public abstract class User {
	/** Unique identifier for the user */
	private int userID;
	/** Name of the user */
	private String name;
	/** Address of the user */
	private Address address;
	/**
     * Constructs a User with the specified details
     *
     * @param userID unique identifier for the user
     * @param name name of the user
     * @param address the user's address
     */
	public User(int userID, String name, Address address) {
		this.userID=userID;
		this.name=name;
		this.address=address;
	}
	/**
     * Gets the user ID
     *
     * @return user ID
     */
	public int getUserID() {
		return userID;
	}
	 /**
     * Gets the user's name
     *
     * @return user name
     */
	public String getName() {
		return name;
	}
	 /**
     * Gets the user's address
     *
     * @return Address object
     */
	public Address getAddress() {
		return address;	
	}
	/**
     * Returns a string representation of the user
     * Must be implemented by subclasses
     *
     * @return formatted user details
     */
	public abstract String toString();
	

}
