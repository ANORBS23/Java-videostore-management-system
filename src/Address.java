/**
 * Represents a customer's address
 * Stores house number, postcode and city information
 * Includes validation to ensure all fields contain valid data
 */
public class Address {
	/** The house number of the address */
	private int HouseNumber;
	/** The postcode of the address */
	private String Postcode;
	/** The city of the address */
	private String City;
	
	/**
	 * 
	 * @param HouseNumber - the house number (must not be negative)
	 * @param Postcode - the postcode (must not be null or empty)
	 * @param City - the city (must not be null or empty)
	 */
	public Address(int HouseNumber,String Postcode,String City) {
		if(HouseNumber<0) {throw new IllegalArgumentException("House Number cannot be negative");}
		if(Postcode==null||Postcode.trim().isEmpty()){throw new IllegalArgumentException("Postcode cannot be empty");}
		if(City==null||City.trim()=="") {throw new IllegalArgumentException("You must provide information for the city");}
		this.HouseNumber=HouseNumber;
		this.Postcode=Postcode;
		this.City=City;
		}
	/**
	 * Gets the house number
	 * @return house number
	 */
	public int getHouseNumber() {
		return HouseNumber;
	}
	
	/**
	 * Gets the postcode
	 * @return postcode string
	 */
	public String getPostcode() {
		return Postcode;
	 
	}
	
	/**
	 * Gets the city
	 * @return city name
	 */
	public String getCity() {
		return City;
	
	}
	
    /**
     * Returns a formatted string representation of the address
     *
     * @return address in the format houseNumber, postcode, city
     */
	@Override
	public String toString() {
		return getHouseNumber()+", "+getPostcode()+", "+getCity();
		}
	
	
}

		
		
		


