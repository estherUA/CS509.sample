package sorting;

import flight.ValidFlights;

import java.awt.peer.CanvasPeer;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SortbyTravelTime implements Comparator<ValidFlights>{
    public int compare (ValidFlights a, ValidFlights b) {
        int traveltimea = CalculateTimeDifference(a.get(0).departFlightDateTime(), a.get(a.size() - 1).arrivalFlightDateTime());
        int traveltimeb = CalculateTimeDifference(b.get(0).departFlightDateTime(), b.get(b.size() - 1).arrivalFlightDateTime());
        return traveltimea - traveltimeb;

    }


    public int CalculateTimeDifference(String departuretime, String arrivaltime) {
        Date departureTime = new Date(departuretime);
        long departureTimeMil = departureTime.getTime();

        Date arrivalTime = new Date(arrivaltime);
        long arrivalTimeMil = arrivalTime.getTime();

        long diff = arrivalTimeMil - departureTimeMil;
        //long diffMinutes = diff/(60 * 1000) % 60;
        Integer diffMinutes1 = (int) (long) diff;

        return diffMinutes1 ;
    }

}
