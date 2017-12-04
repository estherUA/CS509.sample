function getFlights() {
	console.log("Worked");
	$("#loader").removeClass("hidden");
	$("#flightsTableBody").html(""); 
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
				flightString += "<tr>" +
				"<td><input type='radio' name='flightSelected' value='" +arrayOfFlight+ "'>"  +
				"</td><td>" +jsonData.Departure +
				"</td><td>" +jsonData.Arrival +
				"</td><td>" +jsonData.DepartureGMT +
				"</td><td>" +jsonData.ArrivalGMT +
				"</td><td>" +jsonData.FlightNumber + 
				"</td><td>" +jsonData.FlightDuration +
				"</td><td>" +jsonData.FCSeating + 
				"</td><td>" +jsonData.CSeating +
				"</td><td>" +jsonData.FCPrice + 
				"</td><td>" +jsonData.CPrice +
				"</td></tr>";
				}
				else if(temp > 1){
					var connections = str.split("},");
					var conDeparture = ""; 
					var conArrival = "";
					var conDepTime = ""; 
					var conArrTime = "";
					var conFlightNum = "";  
					var conFlightDur = ""; 
					var conFCSeat = "";  
					var conCSeat = "";
					var conFCPrice = "";  
					var conCPrice = "";
					var arrayOfFlights = [];
					
					var initialString ="";
					
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
						 conDeparture += jsonData.Departure + "<hr>"; 
						 conArrival += jsonData.Arrival + "<hr>";
						 conDepTime += jsonData.DepartureGMT + "<hr>"; 
						 conArrTime += jsonData.ArrivalGMT + "<hr>";
						 conFlightNum += jsonData.FlightNumber + "<hr>";  
						 conFlightDur += jsonData.FlightDuration + "<hr>"; 
						 conFCSeat += jsonData.FCSeating + "<hr>";  
						 conCSeat += jsonData.CSeating + "<hr>";
						 conFCPrice += jsonData.FCPrice + "<hr>";  
						 conCPrice += jsonData.CPrice + "<hr>";
						 
						 /*initialString += "<tr>" +
							"<td>" +jsonData.Departure +
							"</td><td>" +jsonData.Arrival +
							"</td><td>" +jsonData.DepartureGMT +
							"</td><td>" +jsonData.ArrivalGMT +
							"</td><td>" +jsonData.FlightNumber + 
							"</td><td>" +jsonData.FlightDuration +
							"</td><td>" +jsonData.FCSeating + 
							"</td><td>" +jsonData.CSeating +
							"</td><td>" +jsonData.FCPrice + 
							"</td><td>" +jsonData.CPrice +
							"</td></tr>";
							}*/
					}
					
					flightString += "<tr>" +
					"<td><input type='radio' name='flightSelected' value='" +arrayOfFlights+ "'>"  +
					"</td><td>" +conDeparture + 
					"</td><td>" +conArrival +
					"</td><td>" +conDepTime +
					"</td><td>" +conArrTime +
					"</td><td>" +conFlightNum + 
					"</td><td>" +conFlightDur +
					"</td><td>" +conFCSeat + 
					"</td><td>" +conCSeat +
					"</td><td>" +conFCPrice + 
					"</td><td>" +conCPrice +
					"</td></tr>";
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
