package flight;

import java.util.ArrayList;
import java.util.Comparator;

public class ValidFlights extends ConnectingFlights implements Comparable<ValidFlights>, Comparator<ValidFlights>{
	private static final long serialVersionUID = 1L;	
	ValidFlights () {
		super();
	}
	@Override
	public int compareTo(ValidFlights o) {
		// TODO Auto-generated method stub
		return this.compareTo(o);
	}
	
	@Override
	public int compare(ValidFlights o1, ValidFlights o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
/* This class will store connecting flights known to arrive at final destination. 
When a PotentialFlight is determined to arrive at final destination it is moved from the
PotentialFlights collection to this collection

Note: this because this class extends ArrayList we can use the Collections.sort( ) method by passing
the appropriate Comparator<T>
*/
