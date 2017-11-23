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

import dao.ServerInterface;

public class connectingFlights {	
	public Flights getConnectingFlights(String departingAirport, String arrivalAirport, Flights departingFlights, Flights arrivingFlights, String teamName, String departureDate) {
		
		ArrayList<Flights> connectedFlights= new ArrayList<Flights>();
		Flights directFlights = new Flights();
        Stack<String> pathStack = new Stack<String>();
        HashMap<String, Flights> graph = new HashMap<String, Flights>();
        Flights stop_1_Flights = new Flights();
        Map<String, Flights> stop_1 = new HashMap<String, Flights>();
        ArrayList<String> visitedAirportsStop1 = new ArrayList<String>();
        
        for (int i = 0; i < departingFlights.size(); i++) {
        		String departCode = departingFlights.get(i).arrivalFlightCode();
        		if (departCode.equalsIgnoreCase(arrivalAirport)) {
        			//directFlights.add(departingFlights.get(i));
        		}
        		/*else {
        			flightForHash = ServerInterface.INSTANCE.getDepartingFlights(teamName, departCode, departureDate);
        			graph.put(departCode, flightForHash);
        		}*/
        }
        
        System.out.println("Departing FLights: " + departingFlights);
        if(directFlights.isEmpty()) {
        	 for (int i = 0; i < departingFlights.size(); i++) {
        		 if(visitedAirportsStop1.contains(departingFlights.get(i).arrivalFlightCode())) {
        			 System.out.println("Skipping");
        		 } else {
        			 stop_1_Flights = ServerInterface.INSTANCE.getDepartingFlights(teamName, departingFlights.get(i).arrivalFlightCode(), departureDate);
        			 for(int j = 0; j < stop_1_Flights.size(); j++) {
        				 Flights connectingFlights = new Flights();
        				 if (stop_1_Flights.get(j).arrivalFlightCode().equalsIgnoreCase(arrivalAirport)) {
        					 
        					 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");;
        					 String d1;
        					 Date date1;
        					 String d2;
        					 Date date2;
							
							Date arrivalTimeFromDepartureDate = new Date(departingFlights.get(i).arrivalFlightDateTime());
							long depatureTimeMil = arrivalTimeFromDepartureDate.getTime();
							d1 = formatter.format(depatureTimeMil);
							
							Date arrivalTime = new Date(stop_1_Flights.get(j).departFlightDateTime());
							long arrivalTimeMil = arrivalTime.getTime();
							d2 = formatter.format(arrivalTimeMil);
							
							try {
								date1 = formatter.parse(d1);
								date2 = formatter.parse(d2);
								
								// Get msec from each, and subtract.
								long diff = date2.getTime() - date1.getTime();
								long diffSeconds = diff / 1000;         
								long diffMinutes = diff / (60 * 1000);         
								long diffHours = diff / (60 * 60 * 1000);
								System.out.println("Difference in time in Minutes: " + diffMinutes);
								
								if(diffMinutes >= 30 && diffMinutes <= 240) {
									//System.out.println("Departure flight: Leaving - "+ departingFlights.get(i).departFlightCode() + "@ "+departingFlights.get(i).departFlightDateTime() + " Arriving - "+ departingFlights.get(i).arrivalFlightCode() + " " + departingFlights.get(i).arrivalFlightDateTime());
		        						//System.out.println("Stop 1 flight: Leaving - "+ stop_1_Flights.get(j).departFlightCode() + "@ "+stop_1_Flights.get(j).departFlightDateTime() + " Arriving - "+ stop_1_Flights.get(j).arrivalFlightCode() + " " + stop_1_Flights.get(j).arrivalFlightDateTime());
		        						connectingFlights.add(departingFlights.get(i));
		        						connectingFlights.add(stop_1_Flights.get(j));
		        						connectedFlights.add(connectingFlights);
								}
								
								
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
        					 
        				 }
        			 }
            		 visitedAirportsStop1.add(departingFlights.get(i).arrivalFlightCode());
        		 }
        	 }
        }
        pathStack.add(departingAirport);
        System.out.println("List all the connected flights!" + connectedFlights);
		return directFlights;
	}
}
