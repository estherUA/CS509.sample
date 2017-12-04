function reserveFlights() {
	 console.log($('input[name="flightSelected"]:checked').val());
	 var numbers = $('input[name="flightSelected"]:checked').val()
	console.log("Workedddddd");
	$("#loader").removeClass("hidden");
	var flightNumbersArray = numbers.split(",");
	var flightSeatsArray = ["Coach", "Coach"];
	
	$.ajax({
		url : 'ReserveFlightServlet',
		data : {
			flightNumbers : flightNumbersArray,
			flightSeats : flightSeatsArray
		},
		success : function(responseText) {
			//$('#test').text(responseText);
			$("#flightsTableBody").html("");
			$("#loader").addClass("hidden");
			alert("Your Trip has been reserved!");
		},
		error: function (request, status, error) {
			console.log(request.responseText);
			alert("Oops! An error occured while reserving your trip. Please try again.");
		}
	});
}