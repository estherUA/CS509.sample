package sorting;
import flight.ValidFlights;
import java.util.Comparator;
import java.util.Date;

public class SortbyDepartureTime implements  Comparator<ValidFlights> {

    public int compare (ValidFlights a, ValidFlights b) {

        String departuretimeA = a.get(0).departLocalTime();
        Date timeA = new Date(departuretimeA);
       long departureTimeA = timeA.getTime();
        Integer TimeA = (int) (long) departureTimeA;
//
        String departuretimeB = b.get(0).departLocalTime();
        Date timeB = new Date(departuretimeB);
        long departureTimeB = timeB.getTime();
        Integer TimeB = (int) (long) departureTimeB;

        return TimeA - TimeB;
        //return a.get(a.size()-1).departFlightDateTime().compareToIgnoreCase(b.get(b.size() -1).departFlightDateTime());


    }
}
