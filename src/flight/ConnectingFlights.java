package flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TimeZone;

import flight.Flight;
import flight.Flights;
import airplane.Airplane;


//public class ConnectingFlights extends ArrayList<ConnectingFlight> {
public class ConnectingFlights extends ArrayList<Flight> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int lastAdded = this.size() - 1;

	//String destinationAirportCode;
	
	//ArrayList <Flight>connectedFlights = new ArrayList<Flight>();
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
	//?
	ConnectingFlights() {
		super(3);	// at most we can have 3 legs on a connecting flight
	}
	
	/* return true if the flight is the first flight added to the list 
		or if it can be added to the sequence of connecting flights.
		it departs the previous arrival airport and the layover is 30 - 240 minutes.
	*/
	public boolean validConnection(Flight flight) { 
		if(this.isEmpty() || this.size() == 0) {
			System.out.println("Its empty!");
			return true;
		} else if(this.get(0) == flight) {
			return true;
		} else {
			Flight previousStop = this.get(this.size()-1);
			String d1, d2;
			Date date1, date2;
			
			Date previousFlightDateTime = new Date(previousStop.arrivalFlightDateTime());
			long previousFlightTimeMil = previousFlightDateTime.getTime();
			d1 = formatter.format(previousFlightTimeMil);
			
			Date flightDateTime = new Date(flight.departFlightDateTime());
			long FlightMil = flightDateTime.getTime();
			d2 = formatter.format(FlightMil);
			
			try {
				date1 = formatter.parse(d1);
				date2 = formatter.parse(d2);
				
				// Get msec from each, and subtract.
				long diff = date2.getTime() - date1.getTime();		 
				long diffMinutes = diff / (60 * 1000);   
				//System.out.println("Difference in time in Minutes: " + diffMinutes);
				
				if(diffMinutes >= 30 && diffMinutes <= 240) {
					return true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean validSeating(Flight flight, Airplane airplane, int customerChoice) {
		int coachcapacity = airplane.coach();
		int firstclasscapacity = airplane.firstclass();
		int reservedcoach = flight.coachSeating();
		int reservedfirstclass = flight.firstClassSeating();
		if (customerChoice == 0) {
			if (coachcapacity - reservedcoach <= 0){
				return false;
			} else
				return true;
		} else if (firstclasscapacity - reservedfirstclass <=0){
				return false;
			} else
				return true;



	}


	/* add a connection to the connections with error processing
		the method will throw if the flight is not a validConnection
	*/
//	public void add(Flight flight) throws IllegalArgumentException {
//		if(validConnection(flight)) {
//			//ConnectingFlight newConnection = (ConnectingFlight) flight;
//			this.add(flight);
//			System.out.println("Here");
//		}
//	}

	/* return the 3-char airport code of the current destination
	*/
	public String lastDestination() {
		return this.get(this.size()-1).arrivalFlightCode();
	}

	/* This class will return true if the PotentialFlight arrives at the specified destination
	*/
	public boolean arrivesDestination (String destination, Flight flight) {
		if (flight.arrivalFlightCode() == destination) {
			return true;
		}
		return false;
	}
	
}
