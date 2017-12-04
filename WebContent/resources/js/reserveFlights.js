function reserveFlights() {
	 console.log($('input[name="flightSelected"]:checked').val());
	console.log("Workedddddd");
	$("#loader").removeClass("hidden");
	var flightNumbers = [];
	var flightSeats = [];
	
	$.ajax({
		url : 'ReserveFlightServlet',
		data : {
			flightNumbers : $('input[name="flightSelected"]:checked').val()
		},
		success : function(responseText) {
			$('#test').text(responseText);
		}
	});
}