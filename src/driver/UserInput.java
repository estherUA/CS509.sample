package driver;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInput {

    private String flightnumber;
    private String Seating;
    private String depAirportcode;
    private String arrAirportcode;
    private String deparDate;
    private String returnDate;

    public UserInput() {
        flightnumber = "";
        Seating= "";
        deparDate= "";
        returnDate = "";
        depAirportcode = "";
        arrAirportcode = "";
    }


    public UserInput(String flightnumber, String seating, String depAirportcode, String arrAirportcode, String deparDate, String returnDate) {
        //add isvalid functions later

        this.flightnumber = flightnumber;
        this.Seating = seating;
        this.depAirportcode = depAirportcode;
        this.arrAirportcode = arrAirportcode;
        this.deparDate = deparDate;
        this.returnDate = returnDate;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getSeating() {
        return Seating;
    }

    public void setSeating(String seating) {
        Seating = seating;
    }

    public String getDepAirportcode() {
        return depAirportcode;
    }

    public void setDepAirportcode(String depAirportcode) {
        this.depAirportcode = depAirportcode;
    }

    public String getArrAirportcode() {
        return arrAirportcode;
    }

    public void setArrAirportcode(String arrAirportcode) {
        this.arrAirportcode = arrAirportcode;
    }

    public String getDeparDate() {
        return deparDate;
    }

    public void setDeparDate(String deparDate) {
        this.deparDate = deparDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }




    public String getFlightNumber(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter desired flight number: \r");
        String number = scan.next();
        //int i = Integer.parseInt(s);
        //int flightnumber = Integer.parseInt(br.readLine());
        return number;
    }


    public String getAirportCode(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter departure airport: \r");
        String airport = scan.next();
        //int i = Integer.parseInt(s);
        //int flightnumber = Integer.parseInt(br.readLine());
        return airport;
    }

    public String getDeparturedate(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter departure date: \r");
        String date = scan.next();
        //int i = Integer.parseInt(s);
        //int flightnumber = Integer.parseInt(br.readLine());
        return date;
    }




}
