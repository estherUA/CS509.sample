/**
 * 
 */
package driver;
import java.io.IOException;
import java.util.*;

import airplane.Airplane;
import airport.Airport;
import airport.Airports;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.security.ntlm.Server;
import dao.DaoAirport;
import dao.ServerInterface;
import flight.ConnectingFlight;
import flight.ConnectingFlights;
import flight.FindFlights;
import flight.Flight;
import flight.Flights;
import flight.ValidFlights;
import flight.ValidStringFlights;
import java.util.HashMap;

import airplane.Airplanes;
import flight.LocalTimeConverter;
import jdk.nashorn.internal.runtime.ECMAException;
import reservation.Reservation;
import reservation.ReserveFlight;
import sorting.SortbyDepartureTime;


/**
 * @author blake
 *
 */
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * <p>
	 * This driver will retrieve the list of airports from the CS509 server and print the list
	 * to the console sorted by 3 character airport code
	 *
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */





	public static void main(String[] args) throws Exception {



		if (args.length < 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}


		String teamName = args[0];


		UserInput ui = new UserInput();
		String departureCode = args[1];
		String departuredate = args[2];
		String arrivalCode = args[3];
		String sortValue = args[4];
		Airports allAirports = ServerInterface.INSTANCE.getAirports(teamName);

		//Try to get a list of airports
		/*Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Collections.sort(airports);
		for (Airport airport : airports) {
			System.out.println(airport.toString());
		}*/

		LocalTimeConverter localtime = new LocalTimeConverter();

		FindFlights allFlights = new FindFlights();
		ArrayList<ValidFlights> directAndConnecting = allFlights.getFlights(departureCode, arrivalCode, departuredate, sortValue);
		Collections.sort(directAndConnecting, new SortbyDepartureTime());


		System.out.println(directAndConnecting.toString());

		ArrayList<String> flightnumber = new ArrayList<String>();
		flightnumber.add("5960");
		flightnumber.add("8293");
		flightnumber.add("5962");
		ArrayList<String> seating = new ArrayList<String>();
		seating.add("Coach");
		seating.add("Coach");
		seating.add("Coach");

		//ReserveFlight.makeReservation(teamName, flightnumber, seating);
		//String xmlflights = ReserveFlight.makeFlightXML(flightnumber, seating);
		//ServerInterface.INSTANCE.reserveFlight(teamName, xmlflights);
		
		
		//Flights rlist = ServerInterface.INSTANCE.getFlights(teamName, departureCode, departuredate, arrivalCode);
		
		//Collections.sort(rlist, new FlightDurationSorter());

	}




}