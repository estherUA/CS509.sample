package flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import dao.ServerInterface;
import reservation.ReserveFlight;
import sorting.CoachPriceSorter;
import sorting.FirstClassPriceSorter;
import sorting.SortbyArrivalTime;
import sorting.SortbyDepartureTime;
import sorting.SortbyTravelTime;

public class FindFlights {
	DepartureCache cache = new DepartureCache();
	PotentialFlights potentialFlights = new PotentialFlights();
	ValidFlights validFlights = new ValidFlights();
	//ArrayList<ValidFlights> potentialValidFlights = new ArrayList<ValidFlights>();
	//ArrayList<ValidStringFlights> validStringFlightCollection = new ArrayList<ValidStringFlights>();
	//ValidStringFlights stringPotentialFlights = new ValidStringFlights();
	ArrayList<ValidFlights> validFlightCollection = new ArrayList<ValidFlights>();
	ArrayList<ValidFlights> potentialvalidFlightCollection = new ArrayList<ValidFlights>();
	String departureAirport = "";    // initialize with the departure airport code
	String finalDestinationAirport = "";    // initialize with the arrival airport code
	String currentAirport = "";
	String teamName = "SmartDesign";
	String sortVal = "price";

	public ArrayList<ValidFlights> getFlights(String departAirportCode, String arriveAirportCode, String departureDate, String sortValue) {
		sortVal = sortValue;
		departureAirport = departAirportCode;
		finalDestinationAirport = arriveAirportCode;
		for (int leg = 0; leg < 3; leg++) {
			Flights departingFlights = null;

			if (leg == 0) {
				currentAirport = departureAirport;
				departingFlights = getDepartingFlightsFromServer(currentAirport, departureDate);
				cache.put(currentAirport, departingFlights);
				for (Flight flight : departingFlights) {
					if (flight.arrivalFlightCode().equalsIgnoreCase(finalDestinationAirport)) {
						ValidFlights directFlights = new ValidFlights();
						directFlights.add(flight);
						validFlightCollection.add(directFlights);
					} else {
						potentialFlights.add(flight);
					}
				}

			} else if (leg == 1) {
				for (Flight connection : potentialFlights) {
					currentAirport = connection.arrivalFlightCode();
					if (cache.containsKey(currentAirport)) {
						departingFlights = cache.get(currentAirport);
					} else {
						departingFlights = getDepartingFlightsFromServer(currentAirport, departureDate);
						cache.put(currentAirport, departingFlights);
					}


					for (Flight flight : departingFlights) {
						ValidFlights validConnections = new ValidFlights();
						ValidFlights potentialtwoStopConnections = new ValidFlights();
						potentialtwoStopConnections.add(connection);
						validConnections.add(connection);
						if (flight.arrivalFlightCode().equalsIgnoreCase(finalDestinationAirport)) {
							if (validConnections.validConnection(flight)) {
								validConnections.add(flight);
								validFlightCollection.add(validConnections);
							}

						} else {

							if (potentialtwoStopConnections.validConnection(flight)) {
								potentialtwoStopConnections.add(flight);
								potentialvalidFlightCollection.add(potentialtwoStopConnections);
							}
						}
					}
				}
			} else {
				Iterator<ValidFlights> connectionIterator = potentialvalidFlightCollection.iterator();
				while (connectionIterator.hasNext()) {
					ValidFlights flights = connectionIterator.next();
					String secondStopover= flights.get(1).arrivalFlightCode();
					departingFlights = cache.get(secondStopover);
					ValidFlights twoStopConnections = flights;
					if(departingFlights == null) {
						departingFlights =  getDepartingFlightsFromServer(secondStopover, departureDate);
					}
					for (Flight flight : departingFlights) {
						if(flight.arrivalFlightCode().equalsIgnoreCase(finalDestinationAirport)){
							if(twoStopConnections.validConnection(flight) && twoStopConnections.size() < 3){
								twoStopConnections.add(flight);
								validFlightCollection.add(twoStopConnections);
							}
						}
					}
					connectionIterator.remove();

				}
			}


		}
		System.out.println("In findflights before sorting" + validFlightCollection);
		if(sortValue.equalsIgnoreCase("coachPrice")) {
			Collections.sort(validFlightCollection, new CoachPriceSorter());
		} else if (sortValue.equalsIgnoreCase("firstClassPrice")) {
			Collections.sort(validFlightCollection, new FirstClassPriceSorter());
		} else if (sortValue.equalsIgnoreCase("flightDuration")) {
			Collections.sort(validFlightCollection, new SortbyTravelTime());
		} else if (sortValue.equalsIgnoreCase("departureTime")) {
			Collections.sort(validFlightCollection, new SortbyDepartureTime());
		} else if (sortValue.equalsIgnoreCase("arrivalTime")) {
			Collections.sort(validFlightCollection, new SortbyArrivalTime());
		} else {
			Collections.sort(validFlightCollection, new CoachPriceSorter());
		}
		System.out.println("In findflights after sorting" + validFlightCollection);
		return validFlightCollection;
	}

	public Flights getDepartingFlightsFromServer(String airport, String date) {
		Flights flights = ServerInterface.INSTANCE.getDepartingFlights(teamName, airport, date);
		return flights;
	}
  }


