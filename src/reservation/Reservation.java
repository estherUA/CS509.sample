package reservation;
import flight.Flight;
import flight.Flights;
import airport.Airports;
import airport.Airport;
import airplane.Airplane;
import airplane.Airplanes;

import java.text.SimpleDateFormat;
import java.util.Comparator;

public class Reservation  {
	Flights option;
	String seatPrefrence="firstClass";
	
	public Flights getOption() {
		return option;
	}
	public void setOption(Flights option) {
		this.option = option;
	}
	public String getSeatPrefrence() {
		return seatPrefrence;
	}
	
	//right now just taking seat preference as first class
	public void setSeatPrefrence(String seatPrefrence) {
		this.seatPrefrence = seatPrefrence;
	}

	public Reservation(Flights option) {
		super();
		this.option = option;
		
	}
	
	//
	public Reservation(Flights option, String seatPrefrence) {
		super();
		this.option = option;
		this.seatPrefrence = seatPrefrence;
	}
	public int getDuration(){
		int duration=0;
		for(int i =0;i<option.size();i++) {
			duration+=option.get(i).flightDuration();
		}
		return duration;
	}
	public Double getPrice() {
		double price=0;
		for(int i=0;i<option.size();i++) {
			price+=(seatPrefrence.equals("firstClass"))?option.get(i).firstClassPrice():option.get(i).coachPrice();
			
		}
		return price;
	}
	

}
