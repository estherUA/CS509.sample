package flight;

public class FlightDetails {
	private String mCode;
	private String mTime;
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 * 
	 * @pre None
	 * @post member attributes are initialized to invalid default values
	 */	
	public FlightDetails () {
		mCode = "";
		mTime = "";
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 * @param name The human readable name of the airport
	 * @param code The 3 letter code for the airport
	 * @param latitude The north/south coordinate of the airport 
	 * @param longitude The east/west coordinate of the airport
	 * 
	 * @pre code is a 3 character string, name is not empty, latitude and longitude are valid values
	 * @post member attributes are initialized with input parameter values
	 * @throws IllegalArgumentException is any parameter is invalid
	 */
	public FlightDetails (String code, String time) {
		if (!isValidCode(code))
			throw new IllegalArgumentException(code);
		if (!isValidTime(time)) 
			throw new IllegalArgumentException(time);
		
		mCode = code;
		mTime = time;
	}
	
	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(mCode).append(", ");
		sb.append(mTime);

		return sb.toString();
	}
	/**
	 * Set the airport name
	 * 
	 * @param name The human readable name of the airport
	 * @throws IllegalArgumentException is name is invalid
	 */
	public void code (String code) {
		if (isValidCode (code))
			mCode = code;
		else
			throw new IllegalArgumentException (code);
	}
	
	/**
	 * get the airport name
	 * 
	 * @return Airport name
	 */
	public String code () {
		return mCode;
	}
	
	/**
	 * set the airport 3 letter code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void flightTime (String time) {
		if (isValidTime(time))
			mTime = time;
		else
			throw new IllegalArgumentException (time);
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String time () {
		return mTime;
	}
	
	public boolean isValid() {
		
		// If the name isn't valid, the object isn't valid
		if ((mCode == null) || (mCode == ""))
			return false;
		
		// If we don't have a 3 character code, object isn't valid
		if ((mTime == null) || (mTime.length() != 3))
			return false;
		
		return true;
	}
	
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidCode (String code) {
		// If the name is null or empty it can't be valid
		if ((code == null) || (code == ""))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidTime (String time) {
		// If the name is null or empty it can't be valid
		if ((time == null) || (time == ""))
			return false;
		return true;
	}
}
