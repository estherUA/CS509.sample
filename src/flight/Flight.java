package flight;

import java.util.Comparator;

import utils.Saps;

public class Flight implements Comparable<Flight>, Comparator<Flight>{
	
	private String mflightNumber;
	private String mflightDuration;
	private String mDepartFlightCode;
	private String mDepartFlightDateTime;
	private String mArrivalFlightCode;
	private String mArrivalFlightDateTime;

	private String mFirstClassSeating;
	private String mCoachSeating;

	private String mFirstClassPrice;
	private String mCoachPrice;

	
	//private FlightDetails mDepartFlight;
	//private FlightDetails mArriveFlight;
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 * 
	 * @pre None
	 * @post member attributes are initialized to invalid default values
	 */	
	public Flight () {
		mflightNumber = "";
		mflightDuration = "";
		mDepartFlightCode = "";
		mDepartFlightDateTime = "";
		mArrivalFlightCode = "";
		mArrivalFlightDateTime = "";
		mFirstClassSeating = "";
		mFirstClassPrice = "";
		mCoachSeating = "";
		mCoachPrice = "";
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
	public Flight (String flightNumber, String flightDuration, String departFlightCode, String departFlightDateTime, String arrivalFlightCode, String arrivalFlightDateTime, String firstClassSeating, String firstClassPrice, String coachSeating, String coachPrice) {
		if (!isValidFlightNumber(flightNumber))
			throw new IllegalArgumentException(flightNumber);
		if (!isValidFlightDuration(flightDuration)) 
			throw new IllegalArgumentException(flightDuration);
		if (!isValidDepartFlightCode(departFlightCode)) 
			throw new IllegalArgumentException(departFlightCode);
		if (!isValidDepartFlightDateTime(departFlightDateTime)) 
			throw new IllegalArgumentException(departFlightDateTime);
		if (!isValidArrivalFlightCode(arrivalFlightCode)) 
			throw new IllegalArgumentException(arrivalFlightCode);
		if (!isValidArrivalFlightDateTime(arrivalFlightDateTime)) 
			throw new IllegalArgumentException(arrivalFlightDateTime);
		if (!isValidFirstClassSeating(firstClassSeating)) 
			throw new IllegalArgumentException(firstClassSeating);
		if (!isValidCoachSeating(coachSeating)) 
			throw new IllegalArgumentException(coachSeating);
		if (!isValidFirstClassPrice(firstClassPrice)) 
			throw new IllegalArgumentException(firstClassPrice);
		if (!isValidCoachSeating(coachPrice)) 
			throw new IllegalArgumentException(coachPrice);
		
		mflightNumber = flightNumber;
		mflightDuration = flightDuration;
		mDepartFlightCode = departFlightCode;
		mDepartFlightDateTime = departFlightDateTime;
		mArrivalFlightCode = arrivalFlightCode;
		mArrivalFlightDateTime = arrivalFlightDateTime;
		mFirstClassSeating = firstClassPrice;
		mFirstClassPrice = firstClassSeating;
		mCoachSeating = coachSeating;
		mCoachPrice = coachPrice;
	}
	
	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Flight Number: ").append(mflightNumber).append(", ");
		sb.append("Flight Duration: ").append(mflightDuration).append(", ");
		sb.append("Departure Airport: ").append(mDepartFlightCode).append(", ");
		sb.append("Departure Date Time: ").append(mDepartFlightDateTime).append(", ");
		sb.append("Arrival Airport: ").append(mArrivalFlightCode).append(", ");
		sb.append("Arrival Date Time: ").append(mArrivalFlightDateTime).append(", ");
		sb.append("First Class Seating: ").append(mFirstClassSeating).append(", ");
		sb.append("First Class Price: ").append(mFirstClassPrice).append(", ");
		sb.append("Coach Seating: ").append(mCoachSeating).append(", ");
		sb.append("Coach Price: ").append(mCoachPrice);

		return sb.toString();
	}
	/**
	 * Set the airport name
	 * 
	 * @param name The human readable name of the airport
	 * @throws IllegalArgumentException is name is invalid
	 */
	public void flightNumber (String flightNumber) {
		if (isValidFlightNumber (flightNumber))
			mflightNumber = flightNumber;
		else
			throw new IllegalArgumentException (flightNumber);
	}
	
	/**
	 * get the airport name
	 * 
	 * @return Airport name
	 */
	public String flightNumber () {
		return mflightNumber;
	}
	
	/**
	 * set the airport 3 letter code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void flightDuration (String flightDuration) {
		if (isValidFlightDuration(flightDuration))
			mflightDuration = flightDuration;
		else
			throw new IllegalArgumentException (flightDuration);
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String flightDuration () {
		return mflightDuration;
	}
	
	/**
	 * set the airport 3 letter code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void departFlightCode (String departFlightCode) {
		if (isValidFlightDuration(departFlightCode))
			mDepartFlightCode = departFlightCode;
		else
			throw new IllegalArgumentException (departFlightCode);
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String departFlightCode () {
		return mDepartFlightCode;
	}
	
	/**
	 * set the airport 3 letter code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void departFlightDateTime (String departFlightDateTime) {
		if (isValidDepartFlightDateTime(departFlightDateTime))
			mDepartFlightDateTime = departFlightDateTime;
		else
			throw new IllegalArgumentException (departFlightDateTime);
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String departFlightDateTime () {
		return mDepartFlightDateTime;
	}
	
	/**
	 * set the airport 3 letter code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void arrivalFlightCode (String arrivalFlightCode) {
		if (isValidFlightDuration(arrivalFlightCode))
			mArrivalFlightCode = arrivalFlightCode;
		else
			throw new IllegalArgumentException (arrivalFlightCode);
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String arrivalFlightCode () {
		return mArrivalFlightCode;
	}
	
	/**
	 * set the airport 3 letter code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void arrivalFlightDateTime (String arrivalFlightDateTime) {
		if (isValidArrivalFlightDateTime(arrivalFlightDateTime))
			mArrivalFlightDateTime = arrivalFlightDateTime;
		else
			throw new IllegalArgumentException (arrivalFlightDateTime);
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String arrivalFlightDateTime () {
		return mArrivalFlightDateTime;
	}
	
	
	public void firstClassSeating (String firstClassSeating) {
		if (isValidFirstClassSeating(firstClassSeating))
			mFirstClassSeating = firstClassSeating;
		else
			throw new IllegalArgumentException (firstClassSeating);
	}
	

	public String firstClassSeating () {
		return mFirstClassSeating;
	}
	
	public void coachSeating (String coachSeating) {
		if (isValidCoachSeating(coachSeating))
			mCoachSeating = coachSeating;
		else
			throw new IllegalArgumentException (coachSeating);
	}
	

	public String coachSeating () {
		return mCoachSeating;
	}
	
	public void firstClassPrice (String firstClassPrice) {
		if (isValidFirstClassPrice(firstClassPrice))
			mFirstClassPrice = firstClassPrice;
		else
			throw new IllegalArgumentException (firstClassPrice);
	}
	

	public String firstClassPrice () {
		return mFirstClassPrice;
	}
	
	public void coachPrice (String coachPrice) {
		if (isValidCoachPrice(coachPrice))
			mCoachPrice = coachPrice;
		else
			throw new IllegalArgumentException (coachPrice);
	}
	

	public String coachPrice () {
		return mCoachPrice;
	}
	
	
	/**
	 * Compare two flights based on flight number
	 * 
	 * This implementation delegates to the case insensitive version of string compareTo
	 * @return results of String.compareToIgnoreCase
	 */
	public int compareTo(Flight other) {
		return this.mflightNumber.compareToIgnoreCase(other.mflightNumber);
	}
	
	/**
	 * Compare two airports for sorting, ordering
	 * 
	 * Delegates to airport1.compareTo for ordering by 3 character code
	 * 
	 * @param airport1 the first airport for comparison
	 * @param airport2 the second / other airport for comparison
	 * @return -1 if airport ordered before airport2, +1 of airport1 after airport2, zero if no different in order
	 */
	public int compare(Flight flight1, Flight flight2) {
		return flight1.compareTo(flight2);
	}
	
	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the name is not null and not an empty string. Verifies code is 3 characters in length.
	 * Verifies latitude is between +90.0 north pole and -90.0 south pole.
	 * Verifies longitude is between +180.0 east prime meridian and -180.0 west prime meridian.
	 * 
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValid() {
		
		// If the name isn't valid, the object isn't valid
		if ((mflightNumber == null) || (mflightNumber == ""))
			return false;
		
		// If we don't have a 3 character code, object isn't valid
		if ((mflightDuration == null) || (mflightDuration.length() != 3))
			return false;
		
		return true;
	}
	
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidFlightNumber (String flightNumber) {
		// If the name is null or empty it can't be valid
		if ((flightNumber == null) || (flightNumber == ""))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidFlightDuration (String flightDuration) {
		// If the name is null or empty it can't be valid
		if ((flightDuration == null) || (flightDuration == ""))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidDepartFlightCode (String departFlightCode) {
		// If the name is null or empty it can't be valid
		if ((departFlightCode == null))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidDepartFlightDateTime (String departFlightDateTime) {
		// If the name is null or empty it can't be valid
		if ((departFlightDateTime == null))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidArrivalFlightCode (String arrivalFlightCode) {
		// If the name is null or empty it can't be valid
		if ((arrivalFlightCode == null))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight number.
	 * 
	 * @param flightNumber is the number of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidArrivalFlightDateTime (String arrivalFlightDateTime) {
		// If the name is null or empty it can't be valid
		if ((arrivalFlightDateTime == null))
			return false;
		return true;
	}
	
	public boolean isValidFirstClassPrice (String firstClassPrice) {
		// If the name is null or empty it can't be valid
		if ((firstClassPrice == null))
			return false;
		return true;
	}
	
	public boolean isValidCoachPrice (String coachPrice) {
		// If the name is null or empty it can't be valid
		if ((coachPrice == null))
			return false;
		return true;
	}
	
	public boolean isValidCoachSeating (String coachSeating) {
		// If the name is null or empty it can't be valid
		if ((coachSeating == null))
			return false;
		return true;
	}
	
	public boolean isValidFirstClassSeating (String firstClassPrice) {
		// If the name is null or empty it can't be valid
		if ((firstClassPrice == null))
			return false;
		return true;
	}
	
}
