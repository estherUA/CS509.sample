package airplane;

import java.util.Comparator;
import utils.Saps;

import javax.print.attribute.standard.NumberUp;

public class Airplane implements Comparable<Airplane>, Comparator<Airplane> {
//public class Airplane{
	/**
	 * Airplane attributes as defined by the CS509 server interface XML - not done yet
	 */
	private String mManufacturer;
	private String mModel;
	private int mFirstClassSeats;
	private int mCoachSeats;

	/**
	 * Default constructor
	 * <p>
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 *
	 * @pre None
	 * @post member attributes are initialized to invalid default values
	 */
	public Airplane() {
		mManufacturer = "";
		mModel = "";
		mFirstClassSeats = 0;
		mCoachSeats = 0;
	}

	/**
	 * Initializing constructor.
	 * <p>
	 * All attributes are initialized with input values
	 *
	 * @param manufacturer The human readable name of the airport
	 * @param model        The 3 letter code for the airport
	 * @param firstclass   The north/south coordinate of the airport
	 * @param coach        The east/west coordinate of the airport
	 * @throws IllegalArgumentException is any parameter is invalid
	 * @pre code is a 3 character string, name is not empty, latitude and longitude are valid values
	 * @post member attributes are initialized with input parameter values
	 */
	public Airplane(String manufacturer, String model, int firstclass, int coach) {
		if (!isValidManufacturer(manufacturer))
			throw new IllegalArgumentException(manufacturer);
		if (!isValidModel(model))
			throw new IllegalArgumentException(model);
		if (!isValidFirstClass(firstclass))
			throw new IllegalArgumentException(Double.toString(firstclass));
		if (!isValidCoach(coach))
			throw new IllegalArgumentException(Double.toString(coach));

		mManufacturer = manufacturer;
		mModel = model;
		mFirstClassSeats = firstclass;
		mCoachSeats = coach;
	}

	/**
	 * Initializing constructor with all params as type String. Converts latitude and longitude
	 * values to required double format.
	 *
	 * @param manufacturer The human readable name of the airport
	 * @param model        The 3 letter code for the airport
	 * @param firstclass   is the string representation of latitude decimal format
	 * @param coach        is the String representation of the longitude in decimal format
	 * @throws IllegalArgumentException is any parameter is invalid
	 * @pre the latitude and longitude are valid String representations of valid lat/lon values
	 * @post member attributes are initialized with input parameter values
	 */
	public Airplane(String manufacturer, String model, String firstclass, String coach) {
		int tmpFirstclass, tmpCoach;
		try {
			tmpFirstclass = Integer.parseInt(firstclass);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException("Firstclass ", ex);
		}

		try {
			tmpCoach = Integer.parseInt(coach);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException("Coachseat", ex);
		}

		mManufacturer = manufacturer;
		mModel = model;
		mFirstClassSeats = tmpFirstclass;
		mCoachSeats = tmpCoach;
	}

	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 *
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(mManufacturer).append(", ");
		sb.append(mModel).append(", ");
		sb.append(String.format("%d", mFirstClassSeats)).append(", ");
		sb.append(String.format("%d", mCoachSeats));


		return sb.toString();
	}

	/**
	 * setter for manufacture
	 */
	public void manufacturer(String manufacturer) {
		if (isValidManufacturer(manufacturer))
			mManufacturer = manufacturer;
		else
			throw new IllegalArgumentException(manufacturer);
	}

	/**
	 * getter for Manufacture
	 */
	public String manufacturer() {
		return mManufacturer;
	}

	/**
	 * setter for model
	 */
	public void model(String model) {
		if (isValidModel(model))
			mModel = model;
		else
			throw new IllegalArgumentException(model);
	}

	/**
	 * getter for model
	 */
	public String model() {

		return mModel;
	}

	/**
	 * setter for firstclass
	 * string and integer
	 */
	public void firstclass(int firstclass) {
		if (isValidFirstClass(firstclass))
			mFirstClassSeats = firstclass;
		else
			throw new IllegalArgumentException(Integer.toString(firstclass));
	}

	public void firstclass(String firstclass) {
		if (isValidFirstClass(firstclass))
			mFirstClassSeats = Integer.parseInt(firstclass);
		else
			throw new IllegalArgumentException(firstclass);
	}

	/**
	 * getter for firstclass
	 */
	public int firstclass() {
		return mFirstClassSeats;
	}

	/**
	 * setter for coach seat
	 * string and integer
	 */
	public void coach(int coach) {
		if (isValidCoach(coach))
			mCoachSeats = coach;
		else
			throw new IllegalArgumentException(Integer.toString(coach));
	}

	public void coach(String coach) {
		if (isValidCoach(coach))
			mCoachSeats = Integer.parseInt(coach);
		else
			throw new IllegalArgumentException(coach);
	}

	/**
	 * getter for coach seat
	 */
	public int coach() {
		return mCoachSeats;
	}

	/**
	 * Compare two airplane for sorting, ordering
	 * ??not sure about model or manufacturer or doesn't matter
	 * or no need to implement comparable
	 */
	public int compareTo(Airplane other) {
		return this.mModel.compareToIgnoreCase(other.mModel);
	}

	public int compare(Airplane airplane1, Airplane airplane2) {
		return airplane1.compareTo(airplane2);
	}
	@Override
	public boolean equals(Object obj) {
		//every object is equal to itself
		if (obj == this)
			return true;

		//null not equal to anything
		if (obj == null)
			return false;

		//can't be equal if obj is not an instance of Airport
		if (!(obj instanceof Airplane))
			return false;

		//if all fields are equal, the Airplanes are the same
		Airplane rhs = (Airplane) obj;
		if ((rhs.mManufacturer.equals(mManufacturer)) &&
				(rhs.mModel.equals(mModel)) &&
				(rhs.mFirstClassSeats == mFirstClassSeats) &&
				(rhs.mCoachSeats == mCoachSeats)) {
			return true;
		}
		return false;
	}

	/**
	 * Determine if object instance has valid attribute data
	 * <p>
	 * Verifies the manufacture  is not null and not an empty string
	 * model is not null and not an empty string
	 * <p>
	 * firstclass is 0 and ?
	 * coach seats is 0 and ?
	 */
	public boolean isValid() {

		//If the manufacturer isn't valid, the object isn't valid
		if ((mManufacturer == null) || (mManufacturer == ""))
			return false;

		//if we don't have model
		if ((mModel == null) || (mModel == ""))
			return false;

		//verify first class coach seats are within range
		if ((mFirstClassSeats < 0) || (mCoachSeats < 0))
			return false;

		return true;
	}

	/**
	 * check for valid airport manufacture
	 */
	public boolean isValidManufacturer(String manufacturer) {
		//if the value is null or empty
		if ((manufacturer == null) || (manufacturer == ""))
			return false;
		return true;
	}

	/**
	 * check for valid model
	 */
	public boolean isValidModel(String model) {
		//if the model is null or empty
		if ((model == null) || (model == ""))
			return false;
		return true;
	}

	/**
	 * check if firstclass is valid
	 */
	public boolean isValidFirstClass(int firstclass) {
		//verify is within range
		if (firstclass < 0) {
			return false;
		}
		return true;
	}

	public boolean isValidFirstClass(String firstclass) {
		int first;
		try {
			first = Integer.parseInt(firstclass);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidFirstClass(first);
	}

	/**
	 * check id coach is valid
	 */
	public boolean isValidCoach(int coach) {
		if (coach < 0) {
			return false;
		}
		return true;
	}

	public boolean isValidCoach(String coach) {
		int coa;
		try {
			coa = Integer.parseInt(coach);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidCoach(coa);
	}


}
