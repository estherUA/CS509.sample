package flight;

import java.util.Comparator;

import com.google.gson.Gson;

import airport.Airport;
import airport.Airports;

import flight.LocalTimeConverter;
import utils.Saps;

public class Flight implements Comparable<Flight>, Comparator<Flight>{
	
	private String mflightNumber;
	private int mflightDuration;

	private String mDepartFlightCode;
	//? sort by time?
	private String mDepartFlightDateTime;
	private String mArrivalFlightCode;
	//sort by time?
	private String mArrivalFlightDateTime;

	//initiate seating as integer
//	private String mFirstClassSeating;
//	private String mCoachSeating;
	private int mFirstClassSeating;
	private int mCoachSeating;

	//initiate price as double
//	private String mFirstClassPrice;
//	private String mCoachPrice;
	private double mFirstClassPrice;
	private double mCoachPrice;


	
	//private FlightDetails mDepartFlight;
	//private FlightDetails mArriveFlight;
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
		mFirstClassPrice = 0;
		mCoachSeating = 0;
		mCoachPrice = 0;
		mDepartLocalTime = "";
		mArrivalLocalTime = "";
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 *
	 * 
	 * @pre code is a 3 character string, name is not empty, latitude and longitude are valid values
	 * @post member attributes are initialized with input parameter values
	 * @throws IllegalArgumentException is any parameter is invalid
	 */
	public Flight (String flightNumber, int flightDuration, String departFlightCode, String departFlightDateTime, String arrivalFlightCode,
				   String arrivalFlightDateTime, int firstClassSeating, double firstClassPrice, int coachSeating, double coachPrice,
				   String departLocalTime, String arrivalLocalTime) {
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
		//changes later
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
	 * Initializing constructor with all params as type String. Converts latitude and longitude
	 * values to required double format.
	 *
	 *
	 * @pre the latitude and longitude are valid String representations of valid lat/lon values
	 * @post member attributes are initialized with input parameter values
	 * @throws IllegalArgumentException is any parameter is invalid
	 */
	public Flight (String flightNumber, String flightDuration, String departFlightCode, String departFlightDateTime, String arrivalFlightCode,
				   String arrivalFlightDateTime, String firstClassSeating, String firstClassPrice, String coachSeating, String coachPrice,
				   String departLocalTime, String arrivalLocalTime) {
		int tmpFlightDuration;
		int tmpFirstClassSeating,tmpCoachSeating;
		double tmpFirstClassPrice;
		double tmpCoachPrice;
		try {
			tmpFlightDuration = Integer.parseInt(flightDuration);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException("flight duration must be above zero", ex);
		}

		try {
			tmpFirstClassSeating = Integer.parseInt(firstClassSeating);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException("seating must be between zero and ?", ex);
		}

		try {
			tmpCoachSeating = Integer.parseInt(coachSeating);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException("seating must be between zero and ?", ex);
		}

		try {
			tmpFirstClassPrice = Double.parseDouble(firstClassPrice);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException("seating must be between zero and ?", ex);
		}

		try {
			tmpCoachPrice = Double.parseDouble(coachPrice);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException("seating must be between zero and ?", ex);
		}

		mflightNumber = flightNumber;
		mflightDuration = tmpFlightDuration;
		mDepartFlightCode = departFlightCode;
		mDepartFlightDateTime = departFlightDateTime;
		mArrivalFlightCode = arrivalFlightCode;
		mArrivalFlightDateTime = arrivalFlightDateTime;
		mFirstClassSeating = tmpFirstClassSeating;
		mFirstClassPrice = tmpFirstClassPrice;
		mCoachSeating = tmpCoachSeating;
		mCoachPrice = tmpCoachPrice;
		mDepartLocalTime = departLocalTime;
		mArrivalLocalTime= arrivalLocalTime;


	}
	
		//System.out.println(flight.toString());}
	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("'FlightNumber': '").append(mflightNumber).append("', ");
		sb.append("'FlightDuration': '").append(String.valueOf(mflightDuration)).append("', ");
		sb.append("'Departure': '").append(mDepartFlightCode).append("', ");
		sb.append("'Arrival': '").append(mArrivalFlightCode).append("', ");
		sb.append("'FCSeating': '").append(mFirstClassSeating).append("', ");
		sb.append("'FCPrice': '").append(mFirstClassPrice).append("', ");
		sb.append("'CSeating': '").append(mCoachSeating).append("', ");
		sb.append("'CPrice': '").append(mCoachPrice).append("', ");
		sb.append("'DepartureGMT': '").append(mDepartFlightDateTime).append("', ");
		sb.append("'DepartureLocal': '").append(mDepartLocalTime).append("', ");
		sb.append("'ArrivalLocal': '").append(mArrivalLocalTime).append("', ");
		sb.append("'ArrivalGMT': '").append(mArrivalFlightDateTime).append("'}");
		

		//sb.append("FCSeating: ").append(mFirstClassSeating).append(", ");
		//sb.append("FCPrice: ").append("$").append(mFirstClassPrice).append(", ");
		//sb.append("CSeating: ").append(mCoachSeating).append(", ").append("\n");
		//sb.append("CPrice: ").append("$").append(mCoachPrice).append(", ");
		//sb.append("Departure GMT: ").append(mDepartFlightDateTime).append(", ");
		//sb.append("Depart LocalTime: ").append(mDepartLocalTime).append(", ");
		//sb.append("Arrival GMT: ").append(mArrivalFlightDateTime).append(", ");
		//sb.append("Arrival LocalTime: ").append(mArrivalLocalTime).append("\n");

		return sb.toString();
	}
	
	
	
	public String toJson() {
		Gson gson = new Gson();
		String jsonInString = gson.toJson(this);
		return jsonInString;
	}

	/**
	 * Set the flight number
	 * 
	 * @param  flightNumber unique number for each flight
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
	 * @param flightDuration duration in minutes
	 * @throws IllegalArgumentException is duration is invalid
	 */
	public void flightDuration (int flightDuration) {
		if (isValidFlightDuration(flightDuration))
			mflightDuration = flightDuration;
		else
			throw new IllegalArgumentException (Integer.toString(flightDuration));
	}

	//set string format of flight duration
	//add is validFlightnumber(String flightDuration) later
	public void flightDuration(String flightDuration) {
		if (isValidFlightDuration(flightDuration))
			mflightDuration = Integer.parseInt(flightDuration);
		else
			throw new IllegalArgumentException(flightDuration);
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
	 * @param departFlightCode The 3 letter code for the airport
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
	 * @param departFlightDateTime The 3 letter code for the airport
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
	 * @param arrivalFlightCode The 3 letter code for the airport
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
	 * @param arrivalFlightDateTime flight date and time
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

	/**
	 * Set the number of first class seating
	 * @param firstClassSeating
	 * @throws IllegalArgumentException if firstclass is invalid
	 */
	//add isValidfirstClassSeating(int firstClassSeating)
	public void firstClassSeating (int firstClassSeating) {
		if (isValidFirstClassSeating(firstClassSeating))
			mFirstClassSeating = firstClassSeating;
		else
			throw new IllegalArgumentException (Integer.toString(firstClassSeating));
	}

	public void firstClassSeating (String firstClassSeating) {
		if (isValidFirstClassSeating(firstClassSeating))
			mFirstClassPrice = Integer.parseInt(firstClassSeating);
		else
			throw new IllegalArgumentException(firstClassSeating);
	}

	/**
	 * get the number of first class seating
	 * @return
	 */
	public int firstClassSeating () {
		return mFirstClassSeating;
	}

	/**
	 * set the number of coach seating
	 * @param coachSeating
	 * @throws IllegalArgumentException if coach is invalid
	 */
	//add isValidfCoachSeating(int coachSeating)
	public void coachSeating (int coachSeating) {
		if (isValidCoachSeating(coachSeating))
			mCoachSeating = coachSeating;
		else
			throw new IllegalArgumentException (Integer.toString(coachSeating));
	}


	public void coachSeating (String coachSeating) {
		if (isValidCoachSeating(coachSeating))
			mCoachSeating = Integer.parseInt(coachSeating);
		else
			throw new IllegalArgumentException (coachSeating);
	}

	/**
	 * get the number of coach seating
	 * @return
	 */
	public int coachSeating () {
		return mCoachSeating;
	}

	/**
	 * set the value of first class price
	 * @param firstClassPrice
	 * @throws IllegalArgumentException if first class price is invalid
	 */
	//add isValidfirstClassprice(double coachSeating)
	public void firstClassPrice (double firstClassPrice) {
		if (isValidFirstClassPrice(firstClassPrice))
			mFirstClassPrice = firstClassPrice;
		else
			throw new IllegalArgumentException (Double.toString(firstClassPrice));
	}

	public void firstClassPrice (String firstClassPrice) {
		if (isValidFirstClassPrice(firstClassPrice))
			mFirstClassPrice = Double.parseDouble(firstClassPrice);
		else
			throw new IllegalArgumentException (firstClassPrice);
	}

	/**
	 * get the value of first class price
	 * @return
	 */
	public double firstClassPrice () {
		return mFirstClassPrice;
	}


	/**
	 * set the value of first class price
	 * @param coachPrice
	 * @throws IllegalArgumentException if coach price is not available
	 */
	public void coachPrice (double coachPrice) {
		if (isValidCoachPrice(coachPrice))
			mCoachPrice = coachPrice;
		else
			throw new IllegalArgumentException (Double.toString(coachPrice));
	}


	public void coachPrice (String coachPrice) {
		if (isValidCoachPrice(coachPrice))
			mCoachPrice = Double.parseDouble(coachPrice);
		else
			throw new IllegalArgumentException (coachPrice);
	}


	/**
	 * get the value of coach price
	 * @return
	 */
	public double coachPrice () {
		return mCoachPrice;
	}
	
	/**
	 * Set the local time of the departure airport
	 * 
	 * @param departLocalTime The localtime of the departure airport
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
	 * @param arrivalLocalTime The localtime of the arrival airport
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
	//finish isValid() last
	public boolean isValid() {
		
		// If the name isn't valid, the object isn't valid
		if ((mflightNumber == null) || (mflightNumber == ""))
			return false;

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
		//?if flight Duration ==0 then it must be < 1?
		if ((flightDuration == 0) || (flightDuration < 1))
			return false;
		return true;
	}

	public boolean isValidFlightDuration (String flightDuration) {
		int duration;
		try {
			duration = Integer.parseInt(flightDuration);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidFlightDuration(duration);
	}
	
	/**
	 * Check for invalid departure flight code.
	 * 
	 * @param departFlightCode is the 3 letter code of the airport to validate
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
	 * @param arrivalFlightCode is the 3 letter code of the airport to validate
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
		if ((arrivalFlightDateTime == null) || (arrivalFlightDateTime == ""))
			return false;
		return true;
	}


	//what's the valid range of firstclass price and coach price?
	//isValidFirstClassPrice(double firstClassPrice)
	public boolean isValidFirstClassPrice (double firstClassPrice) {
		if (firstClassPrice <= 0)
			return false;
		return true;
	}

	//isValidFirstClassPrice(String firstClassPrice)
	public boolean isValidFirstClassPrice (String firstClassPrice) {
		double firstclass;
		try {
			firstclass = Double.parseDouble(firstClassPrice);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidFirstClassPrice(firstclass);
	}

	//isValidCoachPrice(double coachPrice)
	public boolean isValidCoachPrice (double coachPrice) {
		if (coachPrice <= 0)
			return false;
		return true;
	}

	//isValidCoachPrice(String coachPrice)
	public boolean isValidCoachPrice (String coachPrice) {
		double coach;
		try {
			coach = Double.parseDouble(coachPrice);
		} catch (NullPointerException | NumberFormatException ex){
			return false;
		}
		return isValidCoachPrice(coach);
	}


	//what's the valid range of coach seating and first class seating?
	//isValidCoachSeating(int coachSeating)
	public boolean isValidCoachSeating (int coachSeating) {
		if (coachSeating < 0)
			return false;
		return true;
	}
	//isValidCoachSeating(String coachSeating)
	public boolean isValidCoachSeating (String coachSeating) {
		int coachseat;
		try {
			coachseat = Integer.parseInt(coachSeating);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidCoachSeating(coachseat);
	}

	//isValidFirstClassSeating(int firstClassSeating)
	public boolean isValidFirstClassSeating(int firstClassSeating) {
		if (firstClassSeating < 0)
			return false;
		return true;
	}

	//isValidFirstClassSeating(String firstClassSeating)
	public boolean isValidFirstClassSeating (String firstClassSeating) {
		int firstclasseat;
		try {
			firstclasseat= Integer.parseInt(firstClassSeating);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidFirstClassSeating(firstclasseat);
	}


	public boolean isValidArrivalLocalTime (String arrivalLocalTime) {
		if ((arrivalLocalTime == null))
			return false;
		return true;
	}
	
	public boolean isValidDepartureLocalTime (String departureLocalTime) {
		if ((departureLocalTime == null))
			return false;
		return true;
	}
}
