package dao;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import airport.Airport;
import airport.Airports;
import flight.Flight;
import flight.Flights;
import flight.LocalTimeConverter;

public class DaoFlight {
	/**
	 * Builds collection of airports from airports described in XML
	 * 
	 * Parses an XML string to read each of the airports and adds each valid airport 
	 * to the collection. The method uses Java DOM (Document Object Model) to convert
	 * from XML to Java primitives. 
	 * 
	 * Method iterates over the set of Airport nodes in the XML string and builds
	 * an Airport object from the XML node string and add the Airport object instance to
	 * the Airports collection.
	 * 
	 * @param xmlFlights XML string containing set of airports
	 * @return [possibly empty] collection of Airports in the xml string
	 * @throws NullPointerException included to keep signature consistent with other addAll methods
	 * 
	 * @pre the xmlAirports string adheres to the format specified by the server API
	 * @post the [possibly empty] set of Airports in the XML string are added to collection
	 */
	public static Flights addAll (String xmlFlights, HashMap<String, Airport> airportMap) throws NullPointerException {
		Flights flights = new Flights();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airport to our collection
		Document docFlights = buildDomDoc (xmlFlights);
		NodeList nodesFlights = docFlights.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesFlights.getLength(); i++) {
			Element elementFlight = (Element) nodesFlights.item(i);
			Flight flight = buildFlight (elementFlight, airportMap);
			
			if (flight.isValid()) {
				flights.add(flight);
			}
		}
		
		return flights;
	}

	/**
	 * Creates an Airport object from a DOM node
	 * 
	 * Processes a DOM Node that describes an Airport and creates an Airport object from the information
	 * @param nodeFlight is a DOM Node describing an Airport
	 * @return Airport object created from the DOM Node representation of the Airport
	 * 
	 * @pre nodeAirport is of format specified by CS509 server API
	 */
	static private Flight buildFlight (Node nodeFlight, HashMap<String, Airport> airportMap) {
		/**
		 * Instantiate an empty Airport object
		 */
		Flight flight = new Flight();

		String flNumber;
		int flDuration;
		String flDepartureCode;
		String flDepartureTime;
		String flArrivalCode;
		String flArrivalTime;
		int flFirstClassSeating;
		String flFirstClassPrice;
		String flCoachSeating;
		String flCoachPrice;
		String flDepartAirportLocalTime;
		String flArrivalAirportLocalTime;
		DateFormat formatter= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
		
		
		// The airport element has attributes of Name and 3 character airport code
		Element elementFlight = (Element) nodeFlight;
		flNumber = elementFlight.getAttributeNode("Number").getValue();
		flDuration = Integer.valueOf(elementFlight.getAttributeNode("FlightTime").getValue());
		
		
		Element elementDepartureAirport;
		elementDepartureAirport = (Element)elementFlight.getElementsByTagName("Departure").item(0);
		
		Element elementDepartureCode;
		elementDepartureCode = (Element)elementDepartureAirport.getElementsByTagName("Code").item(0);
		flDepartureCode = String.valueOf(getCharacterDataFromElement(elementDepartureCode));
		
		Element elementDepartureTime;
		elementDepartureTime = (Element)elementDepartureAirport.getElementsByTagName("Time").item(0);
		flDepartureTime = String.valueOf(getCharacterDataFromElement(elementDepartureTime));

		
		Element elementArrivalAirport;
		elementArrivalAirport = (Element)elementFlight.getElementsByTagName("Arrival").item(0);
		
		Element elementArrivalCode;
		elementArrivalCode = (Element)elementArrivalAirport.getElementsByTagName("Code").item(0);
		flArrivalCode = String.valueOf(getCharacterDataFromElement(elementArrivalCode));
		
		Element elementArrivalTime;
		elementArrivalTime = (Element)elementArrivalAirport.getElementsByTagName("Time").item(0);
		flArrivalTime = String.valueOf(getCharacterDataFromElement(elementArrivalTime));
		
		
		Element elementSeating;
		elementSeating = (Element)elementFlight.getElementsByTagName("Seating").item(0);
		
		
		//flDuration = elementFlight.getAttributeNode("FlightTime").getValue();
		
		Element elementFirstClass;
		elementFirstClass = (Element)elementSeating.getElementsByTagName("FirstClass").item(0);
		flFirstClassSeating = Integer.valueOf(String.valueOf(getCharacterDataFromElement(elementFirstClass)));
		flFirstClassPrice = elementFirstClass.getAttributeNode("Price").getValue();
		
		Element elementCoach;
		elementCoach = (Element)elementSeating.getElementsByTagName("Coach").item(0);
		flCoachSeating = String.valueOf(String.valueOf(getCharacterDataFromElement(elementCoach)));
		flCoachPrice = elementCoach.getAttributeNode("Price").getValue();
		
		//flArrivalTime = String.valueOf(getCharacterDataFromElement(elementArrivalTime));
		
		
		//Calculate Local Time
		//Departure Airport
		Airport departureAirport = airportMap.get(flDepartureCode);
		String dlon = Double.toString(departureAirport.longitude());
		String dlat = Double.toString(departureAirport.latitude());
		Date departureTime = new Date(flDepartureTime);
		long departureTimeMil = departureTime.getTime();
		long dts = departureTimeMil/1000;
		
		LocalTimeConverter dltc = new LocalTimeConverter();
		String departTimeZone = dltc.convertLocalTime(dlon, dlat, Long.toString(dts));
		
		//DateFormat formatter= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
		formatter.setTimeZone(TimeZone.getTimeZone(departTimeZone));
		
		flDepartAirportLocalTime = formatter.format(departureTimeMil);
		
		//Arrival Airport
		Airport arrivalAirport = airportMap.get(flArrivalCode);
		String alon = Double.toString(arrivalAirport.longitude());
		String alat = Double.toString(arrivalAirport.latitude());
		Date arrivalTime = new Date(flArrivalTime);
		long arrivalTimeMil = arrivalTime.getTime();
		long ats = arrivalTimeMil/1000;
		
		LocalTimeConverter altc = new LocalTimeConverter();
		String arrivalTimeZone = altc.convertLocalTime(alon, alat, Long.toString(ats));
		
		formatter.setTimeZone(TimeZone.getTimeZone(arrivalTimeZone));
		flArrivalAirportLocalTime = formatter.format(arrivalTimeMil);
		
		
		
		
		/**
		 * Update the Airport object with values from XML node
		 */
		flight.flightNumber(flNumber);
		flight.flightDuration(flDuration);
		flight.departFlightCode(flDepartureCode);
		flight.departFlightDateTime(flDepartureTime);
		flight.arrivalFlightCode(flArrivalCode);
		flight.arrivalFlightDateTime(flArrivalTime);
		flight.coachPrice(flCoachPrice);
		flight.coachSeating(flCoachSeating);
		flight.firstClassPrice(flFirstClassPrice);
		flight.firstClassSeating(flFirstClassSeating);
		flight.departLocalTime(flDepartAirportLocalTime);
		flight.arrivalLocalTime(flArrivalAirportLocalTime);
		
		return flight;
	}

	/**
	 * Builds a DOM tree from an XML string
	 * 
	 * Parses the XML file and returns a DOM tree that can be processed
	 * 
	 * @param xmlString XML String containing set of objects
	 * @return DOM tree from parsed XML or null if exception is caught
	 */
	static private Document buildDomDoc (String xmlString) {
		/**
		 * load the xml string into a DOM document and return the Document
		 */
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlString));
			
			return docBuilder.parse(inputSource);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Retrieve character data from an element if it exists
	 * 
	 * @param e is the DOM Element to retrieve character data from
	 * @return the character data as String [possibly empty String]
	 */
	private static String getCharacterDataFromElement (Element e) {
		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	        CharacterData cd = (CharacterData) child;
	        return cd.getData();
	      }
	      return "";
	}
}
