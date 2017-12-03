/**
 * 
 */
package driver;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

import airplane.Airplane;
import airport.Airport;
import airport.Airports;
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


	public static void main(String[] args) {
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
		Airports allAirports = ServerInterface.INSTANCE.getAirports(teamName);

		//Try to get a list of airports
		/*Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Collections.sort(airports);
		for (Airport airport : airports) {
			System.out.println(airport.toString());
		}*/

		LocalTimeConverter localtime = new LocalTimeConverter();

		FindFlights allFlights = new FindFlights();
		ArrayList<ValidFlights> directAndConnecting = allFlights.getFlights(departureCode, arrivalCode, departuredate);
		System.out.println(directAndConnecting.toString());}


}