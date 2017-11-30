package flight;

import java.util.HashMap;

/* This class will map a 3-char airport string to the list of flights that depart the airport
	so, once we have retrieved flights departing an airport, we can keep them around
*/
public class DepartureCache extends HashMap<String, Flights> {
	private static final long serialVersionUID = 1L;	
}

