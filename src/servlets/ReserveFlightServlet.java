package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.ReserveFlight;

@WebServlet("/ReserveFlightServlet")
public class ReserveFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] flightNumberReceived = request.getParameterValues("flightNumbers"); //request.getParameter("flightNumbers");
		String[] seatingReceived = request.getParameterValues("flightSeating");
		ArrayList<String> flightList = new ArrayList<String>();
		ArrayList<String> flightSeating = new ArrayList<String>();
		
		if(flightNumberReceived != null) {
			for (int i=0; i<flightNumberReceived.length; i++ ) {
				flightList.add(flightNumberReceived[i]);
			}
		}
		
		if(seatingReceived != null) {
			for (int i=0; i<seatingReceived.length; i++ ) {
				flightSeating.add(seatingReceived[i]);
			}
		}
		
		
		String userName = "Test";
		String teamName = "SmartDesign";
		String greetings = "Hello " + userName;
		//ArrayList<String> flightNumbers = new ArrayList<String>();
		//flightNumbers = userName;
		//ArrayList<String> seating = new ArrayList<String>();
		
		try {
			ReserveFlight.makeReservation(teamName, flightList, flightSeating);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/plain");
		response.getWriter().write(greetings);
	}

}