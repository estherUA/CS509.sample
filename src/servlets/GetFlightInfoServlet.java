package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ServerInterface;
import flight.FindFlights;
import flight.Flight;
import flight.Flights;
import flight.ValidFlights;

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
		String sortValue = request.getParameter("sortValue").trim();;
		String departCode = request.getParameter("departureCode").trim();
		String arrivalCode = request.getParameter("arrivalCode").trim();
		String departDate = request.getParameter("departDate").trim();
		/*if(request.getParameter("sortBy").trim() != null) {
			sortValue = request.getParameter("sortBy").trim();
		} */
		
		
		FindFlights allFlights = new FindFlights();
		ArrayList<ValidFlights> directAndConnecting = allFlights.getFlights(departCode, arrivalCode, departDate, sortValue);
		System.out.println(directAndConnecting);
		
		//String depart = "Test " + departCode + " " + arrivalCode + " " + departDate + " " + userName;
		response.setContentType("text/plain");
		
		//String json = new Gson().toJson(departingFlights);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(directAndConnecting.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


