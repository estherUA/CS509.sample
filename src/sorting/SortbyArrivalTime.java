package sorting;
import flight.ValidFlights;
import java.util.Comparator;
import java.util.Date;

public class SortbyArrivalTime implements Comparator<ValidFlights>{
    public int compare (ValidFlights a, ValidFlights b) {
//
//        String arrivaltimeA = a.get(0).arrivalFlightDateTime();
//        Date timeA = new Date(arrivaltimeA);
//        long arrivalTimeA = timeA.getTime();
//        Integer TimeA = (int) (long) arrivalTimeA;
//
//        String arrivaltimeB = b.get(0).arrivalFlightDateTime();
//        Date timeB = new Date(arrivaltimeB);
//        long arrivalTimeB = timeB.getTime();
//        Integer TimeB = (int) (long) arrivalTimeB;
//
//        return TimeA - TimeB;
        return a.get(a.size()-1).arrivalFlightDateTime().compareToIgnoreCase(b.get(b.size() -1).arrivalFlightDateTime());

    }
}
