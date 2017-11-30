package flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ConnectingFlight extends Flight {
	private static final long serialVersionUID = 1L;	
		
	ConnectingFlight(){
		Flight flight;
	};
		String destinationAirportCode;
		
		public String destination() {
			return this.arrivalFlightCode();
		}

		/* This class will return true if the PotentialFlight arrives at the specified destination
		*/
		public boolean arrivesDestination (Flight flight) {
			if (flight.arrivalFlightCode() == this.arrivalFlightCode()) {
				return true;
			}
			return false;
		}

		
		
}
