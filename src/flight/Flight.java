package flight;

import java.util.Comparator;

import utils.Saps;

public class Flight implements Comparable<Flight>, Comparator<Flight>{
	
	private String mflightNumber;
	private int mflightDuration;
	private String mDepartFlightCode;
	private String mDepartFlightDateTime;
	private String mArrivalFlightCode;
	private String mArrivalFlightDateTime;
	private int mFirstClassSeating;
	private String mFirstClassPrice;
	private String mCoachSeating;
	private String mCoachPrice;
	private String mDepartLocalTime;
	private String mArrivalLocalTime;
	
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
		mflightDuration = 0;
		mDepartFlightCode = "";
		mDepartFlightDateTime = "";
		mArrivalFlightCode = "";
		mArrivalFlightDateTime = "";
		mFirstClassSeating = 0;
		mFirstClassPrice = "";
		mCoachSeating = "";
		mCoachPrice = "";
		mDepartLocalTime = "";
		mArrivalLocalTime = "";
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
	public Flight (String flightNumber, int flightDuration, String departFlightCode, String departFlightDateTime, String arrivalFlightCode, String arrivalFlightDateTime, int firstClassSeating, String firstClassPrice, String coachSeating, String coachPrice, String departLocalTime, String arrivalLocalTime) {
		if (!isValidFlightNumber(flightNumber))
			throw new IllegalArgumentException(flightNumber);
		if (!isValidFlightDuration(flightDuration)) 
			throw new IllegalArgumentException();
		if (!isValidDepartureFlightCode(departFlightCode)) 
			throw new IllegalArgumentException(departFlightCode);
		if (!isValidDepartFlightDateTime(departFlightDateTime)) 
			throw new IllegalArgumentException(departFlightDateTime);
		if (!isValidArrivalFlightCode(arrivalFlightCode)) 
			throw new IllegalArgumentException(arrivalFlightCode);
		if (!isValidArrivalFlightDateTime(arrivalFlightDateTime)) 
			throw new IllegalArgumentException(arrivalFlightDateTime);
		if (!isValidFirstClassSeating(firstClassSeating)) 
			throw new IllegalArgumentException();
		if (!isValidCoachSeating(coachSeating)) 
			throw new IllegalArgumentException();
		if (!isValidFirstClassPrice(firstClassPrice)) 
			throw new IllegalArgumentException();
		if (!isValidCoachSeating(coachSeating)) 
			throw new IllegalArgumentException();
		
		mflightNumber = flightNumber;
		mflightDuration = flightDuration;
		mDepartFlightCode = departFlightCode;
		mDepartFlightDateTime = departFlightDateTime;
		mArrivalFlightCode = arrivalFlightCode;
		mArrivalFlightDateTime = arrivalFlightDateTime;
		mFirstClassSeating = firstClassSeating;
		mFirstClassPrice = firstClassPrice;
		mCoachSeating = coachSeating;
		mCoachPrice = coachPrice;
		mDepartLocalTime = departLocalTime;
		mArrivalLocalTime= arrivalLocalTime;
	}
	
	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(mflightNumber).append(", ");
		sb.append(String.valueOf(mflightDuration)).append(", ");
		sb.append("Departure: ").append(mDepartFlightCode).append(", ");
		sb.append("Departure Date Time: ").append(mDepartFlightDateTime).append(", ");
		sb.append("Arrival: ").append(mArrivalFlightCode).append(", ");
		sb.append("FCSeating: ").append(String.valueOf(mFirstClassSeating)).append(", ");
		sb.append("FCPrice: ").append(mFirstClassPrice).append(", ");
		sb.append("CSeating: ").append(mCoachSeating).append(", ");
		sb.append("CPrice: ").append(mCoachPrice).append(", ");
		sb.append("Departure GMT: ").append(mDepartFlightDateTime).append(", ");
		sb.append("Depart LocalTime: ").append(mDepartLocalTime).append(", ");
		sb.append("Arrival GMT: ").append(mArrivalFlightDateTime).append(", ");
		sb.append("Arrival LocalTime: ").append(mArrivalLocalTime);

		return sb.toString();
	}
	/**
	 * Set the flight number
	 * 
	 * @param name The human readable number of the flight
	 * @throws IllegalArgumentException is number is invalid
	 */
	public void flightNumber (String flightNumber) {
		if (isValidFlightNumber (flightNumber))
			mflightNumber = flightNumber;
		else
			throw new IllegalArgumentException (flightNumber);
	}
	
	/**
	 * get the flight number
	 * 
	 * @return Flight number
	 */
	public String flightNumber () {
		return mflightNumber;
	}
	
	/**
	 * set the flight duration
	 * 
	 * @param flight duration in minutes
	 * @throws IllegalArgumentException is duration is invalid
	 */
	public void flightDuration (int flightDuration) {
		if (isValidFlightDuration(flightDuration))
			mflightDuration = flightDuration;
		else
			throw new IllegalArgumentException (Integer.toString(flightDuration));
	}
	
	/**
	 * Get the flight duration
	 * 
	 * @return The duration of the flight in minutes
	 */
	public int flightDuration () {
		return mflightDuration;
	}
	
	/**
	 * set the departure airport 3 letter code
	 * 
	 * @param code The 3 letter departure airport code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void departFlightCode (String departFlightCode) {
		if (isValidDepartureFlightCode(departFlightCode))
			mDepartFlightCode = departFlightCode;
		else
			throw new IllegalArgumentException (departFlightCode);
	}
	
	/**
	 * Get the 3 letter departure airport code
	 * 
	 * @return The 3 letter departure airport code
	 */
	public String departFlightCode () {
		return mDepartFlightCode;
	}
	
	/**
	 * set the departure flight date and time
	 * 
	 * @param departure flight date and time
	 * @throws IllegalArgumentException is flight date and time is invalid
	 */
	public void departFlightDateTime (String departFlightDateTime) {
		if (isValidDepartFlightDateTime(departFlightDateTime))
			mDepartFlightDateTime = departFlightDateTime;
		else
			throw new IllegalArgumentException (departFlightDateTime);
	}
	
	/**
	 * Get the departure flight date and time 
	 * 
	 * @return The departure flight date and time in GMT
	 */
	public String departFlightDateTime () {
		return mDepartFlightDateTime;
	}
	
	/**
	 * set the 3 letter arrival airport code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void arrivalFlightCode (String arrivalFlightCode) {
		if (isValidArrivalFlightCode(arrivalFlightCode))
			mArrivalFlightCode = arrivalFlightCode;
		else
			throw new IllegalArgumentException (arrivalFlightCode);
	}
	
	/**
	 * The 3 letter arrival airport code
	 * 
	 * @return The 3 letter arrival airport code
	 */
	public String arrivalFlightCode () {
		return mArrivalFlightCode;
	}
	
	/**
	 * set the arrival flight date and time
	 * 
	 * @param arrival flight date and time
	 * @throws IllegalArgumentException is flight date and time is invalid
	 */
	public void arrivalFlightDateTime (String arrivalFlightDateTime) {
		if (isValidArrivalFlightDateTime(arrivalFlightDateTime))
			mArrivalFlightDateTime = arrivalFlightDateTime;
		else
			throw new IllegalArgumentException (arrivalFlightDateTime);
	}
	
	/**
	 * Get the arrival flight date and time 
	 * 
	 * @return The arrival flight date and time in GMT
	 */
	public String arrivalFlightDateTime () {
		return mArrivalFlightDateTime;
	}
	
	
	public void firstClassSeating (int firstClassSeating) {
		if (isValidFirstClassSeating(firstClassSeating))
			mFirstClassSeating = firstClassSeating;
		else
			throw new IllegalArgumentException (Integer.toString(firstClassSeating));
	}
	

	public int firstClassSeating () {
		return mFirstClassSeating;
	}
	
	public void coachSeating (String coachSeating) {
		if (isValidCoachSeating(coachSeating))
			mCoachSeating = coachSeating;
		else
			throw new IllegalArgumentException (String.valueOf(coachSeating));
	}
	

	public String coachSeating () {
		return mCoachSeating;
	}
	
	public void firstClassPrice (String flFirstClassPrice) {
		if (isValidFirstClassPrice(flFirstClassPrice))
			mFirstClassPrice = flFirstClassPrice;
		else
			throw new IllegalArgumentException (flFirstClassPrice);
	}
	

	public String firstClassPrice () {
		return mFirstClassPrice;
	}
	
	public void coachPrice (String flCoachPrice) {
		if (isValidCoachPrice(flCoachPrice))
			mCoachPrice = flCoachPrice;
		else
			throw new IllegalArgumentException (flCoachPrice);
	}
	

	public String coachPrice () {
		return mCoachPrice;
	}
	
	/**
	 * Set the local time of the departure airport
	 * 
	 * @param localtime The localtime of the departure airport
	 * @throws IllegalArgumentException is localTime is invalid
	 */
	public void departLocalTime (String departLocalTime) {
		if (isValidDepartureLocalTime (departLocalTime))
		mDepartLocalTime = departLocalTime;
		else
			throw new IllegalArgumentException (departLocalTime);
	}
	
	/**
	 * get the local time for the departure airport
	 * 
	 * @return Local Time
	 */
	public String departLocalTime () {
		return mDepartLocalTime;
	}
	
	/**
	 * Set the local time of the arrival airport
	 * 
	 * @param localtime The localtime of the arrival airport
	 * @throws IllegalArgumentException is localTime is invalid
	 */
	public void arrivalLocalTime (String arrivalLocalTime) {
		if (isValidArrivalLocalTime (arrivalLocalTime))
		mArrivalLocalTime = arrivalLocalTime;
		else
			throw new IllegalArgumentException (arrivalLocalTime);
	}
	
	/**
	 * get the local time for the departure airport
	 * 
	 * @return Local Time
	 */
	public String arrivalLocalTime () {
		return mArrivalLocalTime;
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
	 * Compare two flight for sorting, ordering
	 * 
	 * 
	 * @param flight1 the first flight for comparison
	 * @param flight2 the second / other flight for comparison
	 * @return -1 if flight ordered before flight2, +1 of flight1 after flight2, zero if no different in order
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
		if ((mflightDuration < 1) || (mflightDuration == 0))
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
	 * Check for invalid flight duration.
	 * 
	 * @param flightDuration is the duration of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidFlightDuration (int flightDuration) {
		// If the name is null or empty it can't be valid
		if ((flightDuration == 0) || (flightDuration < 1))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid departure flight code.
	 * 
	 * @param flightCode is the 3 letter code of the airport to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidDepartureFlightCode (String departFlightCode) {
		// If the name is null or empty it can't be valid
		if ((departFlightCode == null) || (departFlightCode == ""))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight Date and Time.
	 * 
	 * @param departFlightDateTime is the departure date of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidDepartFlightDateTime (String departFlightDateTime) {
		// If the name is null or empty it can't be valid
		if ((departFlightDateTime == null) || (departFlightDateTime == ""))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid arrival flight code.
	 * 
	 * @param flightCode is the 3 letter code of the airport to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidArrivalFlightCode (String arrivalFlightCode) {
		// If the name is null or empty it can't be valid
		if ((arrivalFlightCode == null) || (arrivalFlightCode == ""))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid flight Date and Time.
	 * 
	 * @param arrivalFlightDateTime is the arrival date of the flight to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidArrivalFlightDateTime (String arrivalFlightDateTime) {
		if ((arrivalFlightDateTime == null) || (arrivalFlightDateTime == null))
			return false;
		return true;
	}
	
	public boolean isValidFirstClassPrice (String flFirstClassPrice) {
		if ((flFirstClassPrice == null) || (flFirstClassPrice == ""))
			return false;
		return true;
	}
	
	public boolean isValidCoachPrice (String flCoachPrice) {
		if ((flCoachPrice == null) || (flCoachPrice == ""))
			return false;
		return true;
	}
	
	public boolean isValidCoachSeating (String coachSeating) {
		if ((coachSeating == null) || (coachSeating == ""))
			return false;
		return true;
	}
	
	public boolean isValidFirstClassSeating (int firstClassSeating) {
		if ((firstClassSeating == 0) || (firstClassSeating < 1))
			return false;
		return true;
	}
	
	public boolean isValidArrivalLocalTime (String arrivalLocalTime) {
		if ((arrivalLocalTime == null) || (arrivalLocalTime == null))
			return false;
		return true;
	}
	
	public boolean isValidDepartureLocalTime (String departureLocalTime) {
		if ((departureLocalTime == null) || (departureLocalTime == null))
			return false;
		return true;
	}
}
