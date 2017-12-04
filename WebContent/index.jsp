<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Design's Airline Reservation System</title>

<!-- CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/main.css">

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="resources/js/getFlights.js"></script>
<script src="resources/js/reserveFlights.js"></script>
</head>
<body>
	<div class="container">
	<!-- Content here -->
	<div class="row">
		<div class="col-md-12 mb-6">
			<img src="resources/img/WorldPlaneInc.png" style="float: right;"/>
		</div>
		<div class="col-md-12">
		<form class="container" id="needs-validation" novalidate method="get">
			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="validationCustom01">Departure Airport</label>
					<input type="text" name="departureAirport" class="form-control" id="departCode" maxlength="3" value="BOS" required>
				</div>
				<div class="col-md-6 mb-3">
					<label for="validationCustom02">Arrival Airport</label>
					<input type="text" name="arrivalAirport" class="form-control" id="arrivalCode" maxlength="3" value="JFK" required>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="validationCustom03">Departure Date</label>
					<input type="date" name="departureDate" class="form-control" id="departDate" placeholder="" required min="2017-12-10">
				<div class="departure-date-feedback hidden">
				Please use the date picker to select a date.
				</div>
			</div>
			<div class="col-md-6 mb-3 arrival hidden">
				<label for="validationCustom04">Arrival Date</label>
				<input type="date" name="arrivalAirport" class="form-control" id="arrivalDate" placeholder="State" required>
				<div class="invalid-feedback">
				Please provide a valid state.
				</div>
			</div>
			<div class="col-md-6 mb-3">
				<label for="">Round Trip?</label>
				<input type="checkbox" name="roundTrip" class="" id="roundTrip">
			</div>
		</div>
		<button class="btn btn-primary" id="returnFlights" type="submit" value="getFlights">Find Flights</button>
		</form>
		</div>
		<div id="loader" class="hidden"></div>
		<div id="displayTable" class="container hidden">
			<table class="table table-bordered">
				<thead class="thead-default">
					<tr>
						<th>Select</th>
						<th>Departure City</th>
						<th>Arrival City</th>
						<th>Departure Time</th>
						<th>Arrival Time</th>
						<th>Flight Number</th>
						<th>Flight Duration</th>
						<th>First Class</th>
						<th>Coach</th>
						<th>First Class Price</th>
						<th>Coach Price</th>
					</tr>
				</thead>
				<tbody id="flightsTableBody">
					
				</tbody>
			</table>
			<button class="btn btn-success" id="reserveFlight">Reserve Flight</button>
		</div>
	</div>
</div>
	<!-- strong>Ajax Response</strong>:
	<div id="ajaxGetUserServletResponse"></div-->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#returnFlights').click(function() {
				console.log("Departure: ", $('#departCode').val());
				if($('#departCode').val().toLowerCase() == $('#arrivalCode').val().toLowerCase()) {
					alert("Departure Aiport and Arrival Airport can't be the same");
				}
				getFlights();
			});
			
			$( "#roundTrip" ).click(function() {
				if($("#roundTrip").is(':checked')){
					$(".arrival").removeClass('hidden');
				} else {
					$(".arrival").addClass('hidden');
				}
			});
			
			$("#reserveFlight").click(function() {
				reserveFlights();
			});

			$("#departDate").on("keydown", function(){
				event.preventDefault(); 
				$(".departure-date-feedback").removeClass('hidden');
				setTimeout(function() { $(".departure-date-feedback").addClass('hidden'); }, 2500);
				console.log("clicked");
			});
		});
	</script>
	<script>
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
	  'use strict';
	
	window.addEventListener('load', function() {
	    var form = document.getElementById('needs-validation');
	    form.addEventListener('submit', function(event) {
	      if (form.checkValidity() === false) {
	        event.preventDefault();
	        event.stopPropagation();
	      }
	      form.classList.add('was-validated');
	    }, false);
	  }, false);
	})();
	</script>
</body>
</html>