package flight;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;

import dao.ServerInterface;
import reservation.ReserveFlight;

public class FindFlights{
	DepartureCache cache = new DepartureCache();
	PotentialFlights potentialFlights = new PotentialFlights();
	ValidFlights validFlights = new ValidFlights();
	ArrayList<ValidFlights> potentialValidFlights = new ArrayList<ValidFlights>();
	ArrayList<ValidStringFlights> validStringFlightCollection = new ArrayList<ValidStringFlights>();
	ValidStringFlights stringPotentialFlights = new ValidStringFlights();
	ArrayList<ValidFlights> validFlightCollection = new ArrayList<ValidFlights>();
	String departureAirport = ""; 	// initialize with the departure airport code
	String finalDestinationAirport = ""; 	// initialize with the arrival airport code
	String currentAirport = "";
	String teamName = "SmartDesign";


	
	public ArrayList<ValidFlights> getFlights(String departAirportCode, String arriveAirportCode, String departureDate) {
	//public ArrayList<ValidStringFlights> getFlights(String departAirportCode, String arriveAirportCode, String departureDate) {
		departureAirport = departAirportCode;
		finalDestinationAirport = arriveAirportCode;
		for (int leg = 0; leg < 3; leg++) {
			Flights departingFlights = null;
			
			if (leg == 0) {
				currentAirport = departureAirport;
				departingFlights = getDepartingFlightsFromServer(currentAirport, departureDate);
				cache.put (currentAirport, departingFlights);
				
				for (Flight flight : departingFlights) {
					if (flight.arrivalFlightCode().equalsIgnoreCase(finalDestinationAirport)) {
						//validFlights.add(flight);
						ValidFlights directFlights = new ValidFlights();
						//ArrayList<String> directFlights = new ArrayList<String>();
						//String jsonFlight = gson.toJson(flight);
						
						directFlights.add(flight);
						//directFlights.add(jsonFlight);
						validFlightCollection.add(directFlights);
					} else {
						potentialFlights.add(flight);
					}
				}
				//System.out.println("Cache: " + cache);
			} else {
				for (Flight connection : potentialFlights) {
					currentAirport = connection.arrivalFlightCode(); //Where potential flight arrives
					if (cache.containsKey(currentAirport)) {
						departingFlights = cache.get(currentAirport);
					} else {
						departingFlights = getDepartingFlightsFromServer(currentAirport, departureDate); //Add to potential if the flight arrives destination then add to valid flights
						cache.put(currentAirport, departingFlights);
					}
					
					for (Flight flight : departingFlights) {
						ValidFlights possibleConnections = new ValidFlights();
						possibleConnections.add(connection);
						if (flight.arrivalFlightCode().equalsIgnoreCase(finalDestinationAirport)) {
							if (possibleConnections.validConnection(flight)) { //connections different from connections
								/*
								String jsonConnection = gson.toJson(connection);
								String jsonPossibleFlight = gson.toJson(flight);
								stringPotentialFlights.add(jsonConnection);
								stringPotentialFlights.add(jsonPossibleFlight);*/
								//System.out.println(jsonFlight);
								
								possibleConnections.add(flight);
								validFlightCollection.add(possibleConnections);
								validStringFlightCollection.add(stringPotentialFlights);
							} 
						} /*else {
							ValidFlights validFlightConnections = new ValidFlights();// they are valid but they haven't gotten to the final destination just yet.
							if (possibleConnections.validConnection(flight)) {
								validFlightConnections.add(connection);
								validFlightConnections.add(flight);
								potentialValidFlights.add(validFlightConnections);
							}
						}*/
					}
				}
				
				/*for(ValidFlights validConnections : potentialValidFlights) {
					Flight previousLeg = validConnections.get(1);
					currentAirport = previousLeg.arrivalFlightCode(); //Where potential flight arrives
					if (cache.containsKey(currentAirport)) {
						departingFlights = cache.get(currentAirport);
					} else {
						departingFlights = getDepartingFlightsFromServer(currentAirport, departureDate); //Add to potential if the flight arrives destination then add to valid flights
						cache.put(currentAirport, departingFlights);
					}
					if (previousLeg.arrivalFlightCode().equalsIgnoreCase(finalDestinationAirport)) {
						
					}
				}*/
				
				Iterator<Flight> connectionIterator = potentialFlights.iterator();
				while (connectionIterator.hasNext()) {
					Flight flight = connectionIterator.next();
					if (flight.arrivalFlightCode().equals(finalDestinationAirport)) {
						validFlights.add(flight);
						connectionIterator.remove();
					}
				}	
			}
			
		}
		//System.out.println("Valid Flights: " + validFlightCollection);
		//System.out.println("Potential Flights: " + potentialValidFlights);
		
		return validFlightCollection;
	}
	
	public Flights getDepartingFlightsFromServer (String airport, String date){
		Flights flights = ServerInterface.INSTANCE.getDepartingFlights(teamName, airport, date);
		return flights;
	}
}