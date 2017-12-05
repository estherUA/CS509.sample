package sorting;

import java.util.Comparator;

import flight.Flight;
import flight.ValidFlights;

public class CoachPriceSorter implements Comparator<ValidFlights>{

	@Override
	public int compare(ValidFlights o1, ValidFlights o2) {
		// TODO Auto-generated method stub
		double sumO1 = 0; 
		double sumO2 = 0; 
		
		for (Flight flight1 : o1) {
			sumO1 += flight1.coachPrice();
		}
		
		for (Flight flight2 : o2) {
			sumO2 += flight2.coachPrice();
		}
		return (int) (sumO1 - sumO2);
		//return 0;
	}
}
