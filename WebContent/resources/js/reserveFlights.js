function reserveFlights() {
	 console.log($('input[name="flightSelected"]:checked').val());
	console.log("Workedddddd");
	$("#loader").removeClass("hidden");
	
	$.ajax({
		url : 'GetUserServlet',
		data : {
			userName : $('#userName').val()
		},
		success : function(responseText) {
			$('#ajaxGetUserServletResponse').text(responseText);
		}
	});
}