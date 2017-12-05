function getFlights() {
	console.log("Worked");
	$("#loader").removeClass("hidden");
	$("#flightsTableBody").html(""); 
	var departCode = $('#departCode').val().toUpperCase();
	var arriveCode = $('#arrivalCode').val().toUpperCase();
	var seating = $('input[name="seating"]:checked').val()
	//,seating: $('input[name="seating"]:checked').val()
	$.ajax({
		url : 'GetFlightInfoServlet',
		data : {
			//arrivalDate : $('#arrivalDate').val(),
			departureCode : $('#departCode').val().toUpperCase(),
			arrivalCode : $('#arrivalCode').val().toUpperCase(),
			departDate:  $('#departDate').val().replace(/-/g, '_')
		},
		success : function(responseText) {
			var res = responseText.slice(1, -1);
			var finalResult = [];
			var result = res.split("],");
			var flightString = "";
			function count(string,char) {
				var re = new RegExp(char,"gi");
				return string.match(re).length;
			}
			for(var i=0; i<result.length-1; i++){
				var str = result[i].replace('[',''); //result[i].slice(1);
				var temp = count(str, "FlightNumber");
				if(temp == 1){
					var replaceSingleQuote = str.replace(/'/g, '"');
					var jsonData = JSON.parse(replaceSingleQuote);
					var arrayOfFlight = [jsonData.FlightNumber];
					var cost = 0;
					if(seating == "coach"){
						cost = jsonData.CPrice;
					} else {
						cost = jsonData.FCPrice;
					}
				flightString += "<tr>" +
				"<td><input type='radio' name='flightSelected' value='" +arrayOfFlight+ "'>"  +
				"</td><td><div class='alert' role='alert'><h4 class='alert-heading'>" +
				departCode + " --> " + arriveCode + "</h4>" +
				"<p> Departing " + jsonData.Departure + " " + jsonData.DepartureLocal + " --> " +
				"Arriving " + jsonData.Arrival + " " + jsonData.ArrivalLocal + "</p>" +
				"<p> Flight Duration: " + jsonData.FlightDuration + " minutes </p>" +
				"<p> Cost: $" + cost + " </p>" +
				
				"</tr>";
				}
				
				else if(temp > 1){
					var connections = str.split("},");
					var conFlightDur = 0; 
					var conFCPrice = 0;  
					var conCPrice = 0;
					var arrayOfFlights = [];
//					
					var initialString ="";
//					
					for(var j=0; j<connections.length; j++){
						var trim = connections[j].trim();
						var lastChar = trim[trim.length -1 ];
						var con = trim;
						
						if(lastChar != "}"){
							con = trim + "}";
						}
						var connection = con.replace(/'/g, '"');
						var jsonData = JSON.parse(connection);
						
						arrayOfFlights.push(jsonData.FlightNumber);
						 conFlightDur += parseInt(jsonData.FlightDuration); 
						 conFCPrice += parseInt(jsonData.FCPrice);  
						 conCPrice += parseInt(jsonData.CPrice);

						initialString += "<p> Departing " + jsonData.Departure + " " + jsonData.DepartureLocal + " --> " +
							"Arriving " + jsonData.Arrival + " " + jsonData.ArrivalLocal + "</p>" 
					}
					
					 var cost = "";
					if(seating == "coach"){
						cost = conCPrice;
					} else {
						cost = conFCPrice;
					}
					
					flightString +=  "<tr>" +
					"<td><input type='radio' name='flightSelected' value='" +arrayOfFlight+ "'>"  +
					"</td><td><div class='alert' role='alert'><h4 class='alert-heading'>" +
					departCode + " --> " + arriveCode + "</h4>" +
					initialString +
					"<p> Total Flight Duration: " + conFlightDur + " minutes </p>" +
					"<p> Total Cost: $" + cost + " </p>"
					"</tr>";
				}
				
				finalResult.push("Test");
			}
			if(flightString == ""){
				flightString = "<tr><td colspan='11'>No flights found</td></tr>"
			}
			$("#displayTable").removeClass("hidden");
			$("#flightsTableBody").append(flightString);
			$("#loader").addClass("hidden");
			$('#ajaxGetUserServletResponse').text(finalResult);
		},
		error: function (request, status, error) {
			console.log(request.responseText);
		}
	});
}
