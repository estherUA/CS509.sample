package flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.text.DateFormat;
import java.util.TimeZone;

import airport.*;
import dao.DaoAirport;
import dao.ServerInterface;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import org.json.JSONArray;

public class LocalTimeConverter {
	private String GoogleAPIKey1 = "AIzaSyAD9dZgmw9HV97q1rbJG_wCPt2eVHL-B_A";
	private String GoogleAPIKey = "AIzaSyCO642Oj4uXv7t-v8LtnSruS45DE0i6PDA";
	//private String Longitude;
	//private String Latitude;

	private String baseURL = "https://maps.googleapis.com/maps/api/timezone/json?location=";


	public void getTimeZone(Airports airports, String timeStamp) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();

		String TimeZoneId;
		//Airports airports;
		HashMap<String, String> timeZoneMap = new HashMap<>();
		for (Airport airport : airports) {
			String code = airport.code();
			String lat = Double.toString(airport.latitude());
			String lon = Double.toString(airport.longitude());

			try {
				/**
				 * Create an HTTP connection to the server for a GET
				 */
				url = new URL(baseURL + lat + "," + lon + "&timestamp=" + timeStamp + "&key=" + GoogleAPIKey);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");

				/**
				 * If response code of SUCCESS read the XML string returned
				 * line by line to build the full return string
				 */
				int responseCode = connection.getResponseCode();
				if (responseCode >= HttpURLConnection.HTTP_OK) {
					InputStream inputStream = connection.getInputStream();
					String encoding = connection.getContentEncoding();
					encoding = (encoding == null ? "UTF-8" : encoding);

					reader = new BufferedReader(new InputStreamReader(inputStream));
					while ((line = reader.readLine()) != null) {
						result.append(line);

					}
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String jsonString = result.toString();
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonString);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				jsonObject = null;
				e1.printStackTrace();
			}

			try {

				TimeZoneId = jsonObject.getString("timeZoneId");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				TimeZoneId = "";
				//e.setStackTrace(TimeZoneId);
				//e.printStackTrace(); //Failing as of 11/21/2017
			}
			//
			timeZoneMap.put(code, TimeZoneId);

		}


		try {
			FileOutputStream fos = new FileOutputStream("TimeZoneMap.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(timeZoneMap);
			oos.close();
			fos.close();
			System.out.printf("Serialized HashMap data is saved in TimeZoneMap.ser");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}


		//return TimeZoneId;

	}



	public String updateLocalTime(String airportcode, String flightdatetime) {



		DateFormat formatter= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date departureTime = new Date(flightdatetime);

		long departureTimeMil = departureTime.getTime();
		long dts = departureTimeMil / 1000;

		HashMap<String, String> map = new HashMap<>();
		map = LoadHashMap();

		//departing timezone
		String departTimeZone = map.get(airportcode);

		if (departTimeZone != "" && departTimeZone != null) {
			formatter.setTimeZone(TimeZone.getTimeZone(departTimeZone));
			return formatter.format(departureTimeMil);
			//flight.departLocalTime(formatter.format(departureTimeMil));

		} else {
			return "";
		}


	}

	public HashMap<String, String> LoadHashMap() {
		HashMap<String, String> map = new HashMap<>();
		try {
			FileInputStream fis = new FileInputStream("/Users/estheragbaji/Downloads/CS509.sample/TimeZoneMap.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			map = (HashMap) ois.readObject();
			ois.close();
			fis.close();
		}catch(IOException ioe) {
			ioe.printStackTrace();

		}catch(ClassNotFoundException c){
			System.out.println("Class not found");
			c.printStackTrace();
		}
		return map;
	}




}