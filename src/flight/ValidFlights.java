package flight;

import java.util.ArrayList;

public class ValidFlights extends ConnectingFlights {
	private static final long serialVersionUID = 1L;	
	ValidFlights () {
		super();
	}
	
	
}
/* This class will store connecting flights known to arrive at final destination. 
When a PotentialFlight is determined to arrive at final destination it is moved from the
PotentialFlights collection to this collection

Note: this because this class extends ArrayList we can use the Collections.sort( ) method by passing
the appropriate Comparator<T>
*/
