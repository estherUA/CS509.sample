/**
 * 
 */
package driver;

import java.util.ArrayList;
import java.util.Collections;

import airplane.Airplane;
import airport.Airport;
import airport.Airports;
import dao.ServerInterface;
import flight.ConnectingFlight;
import flight.ConnectingFlights;
import flight.FindFlights;
import flight.Flight;
import flight.Flights;
import flight.ValidFlights;
import airplane.Airplanes;

/**
 * @author blake
 *
 */
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will retrieve the list of airports from the CS509 server and print the list 
	 * to the console sorted by 3 character airport code
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public String sayTeamName (String name) {
		return "Hello " + name;
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}
		
		String teamName = args[0];
		String departureCode = args[1];
		String date = args[2];
		String arrivalCode = args[3];
		//Try to get a list of airports
		/*Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Collections.sort(airports);
		for (Airport airport : airports) {
			System.out.println(airport.toString());
		}*/
		
		/*Flights flights = ServerInterface.INSTANCE.getDepartingFlights(teamName, departureCode, date);
		for (Flight flight : flights) {
			System.out.println(flight.toString());}*/
		
		FindFlights findFlights = new FindFlights();
		ArrayList<ValidFlights> allFlights = findFlights.getFlights(departureCode, arrivalCode, date);
		
		System.out.println("All Flights: "+ allFlights);
		
		/*Airplanes airplanes = ServerInterface.INSTANCE.getAirplanes(teamName);
		Collections.sort(airplanes);
		for (Airplane airplane : airplanes) {
			System.out.println(airplane.toString());
		}*/

	}



}
