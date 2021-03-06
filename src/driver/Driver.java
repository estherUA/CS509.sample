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
		//userInput
		//String departureCode= ui.getDepartureCode();
		String departureCode = args[1];
		//String departuredate = ui.getDeparturedate();
		String departuredate = args[2];
		//String arrivalCode = args[3];
		String arrivalCode = args[3];
		Airports allAirports = ServerInterface.INSTANCE.getAirports(teamName);
//		LocalTimeConverter localtime = new LocalTimeConverter();
//		localtime.getTimeZone(allAirports, Integer.toString(1331766000) );

		//from and to airport
		//String departureCode = args[1];
		//String date = args[2];
		//String arrivalCode = args[3];

		//Try to get a list of airports
		/*Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Collections.sort(airports);
		for (Airport airport : airports) {
			System.out.println(airport.toString());
		}*/

		LocalTimeConverter localtime = new LocalTimeConverter();
//		Flights departflights = ServerInterface.INSTANCE.getDepartingFlights(teamName, departureCode, departuredate);
//		//departflights = localtime.updateLocalTime(depart)
//		for (Flight departflight : departflights) {
//			departflight = localtime.updateLocalTime(teamName, departflight);
//			System.out.println(departflight.toString());
//		}



		//Flights arrivalflights = ServerInterface.INSTANCE.getArrivingFlights(teamName, code, date);
		//for (Flight arrivalflight : arrivalflights) {
		//System.out.println(arrivalflight.toString());}

		//Airplanes airplanes = ServerInterface.INSTANCE.getAirplanes(teamName);
		//Collections.sort(airplanes);
		//for (Airplane airplane : airplanes) {
		//System.out.println(airplane.toString());

//
//		String flightnumber = ui.getFlightNumber();
//
//		System.out.println("Desired flight number: \r");
//		System.out.println(flightnumber);


//		Flights flights = ServerInterface.INSTANCE.getFlights(teamName, departureCode, departuredate, arrivalCode);
//		for (Flight flight : flights) {
//			flight = localtime.updateLocalTime(teamName, flight);
//			System.out.println(flight.toString());}

		FindFlights allFlights = new FindFlights();
		ArrayList<ValidFlights> directAndConnecting = allFlights.getFlights(departureCode, arrivalCode, departuredate);
		System.out.println(directAndConnecting.toString());}


}