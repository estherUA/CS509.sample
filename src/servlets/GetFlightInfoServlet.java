package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ServerInterface;
import flight.FindFlights;
import flight.Flight;
import flight.Flights;

/**
 * Servlet implementation class GetFlightInfoServlet
 */
@WebServlet("/GetFlightInfoServlet")
public class GetFlightInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetFlightInfoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String userName = request.getParameter("userName").trim();
		String userName = "SmartDesign";
		String departCode = request.getParameter("departureCode").trim();
		String arrivalCode = request.getParameter("arrivalCode").trim();
		String departDate = request.getParameter("departDate").trim();
		String departFlight = "";
		
		if(userName == null || "".equals(userName)){
			userName = "Guest";
		}
		
		
		//String departingFlights = (ServerInterface.INSTANCE.getDepartingFlights(userName, departCode, departDate)).toString();
		
		
		FindFlights findFlights = new FindFlights();
		String flightsAndConnections = (findFlights.getFlights(departCode, arrivalCode, departDate)).toString();
		
		
		//String depart = "Test " + departCode + " " + arrivalCode + " " + departDate + " " + userName;
		//response.setContentType("text/plain");
		//response.getWriter().write(departingFlights);
		
		//String json = new Gson().toJson(departingFlights);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(flightsAndConnections);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


