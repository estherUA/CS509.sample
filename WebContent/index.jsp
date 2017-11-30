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
</head>
<body>
	<div class="container">
	<!-- Content here -->
	<div class="row">
		<div class="col-md-12 mb-6">
			<img src="resources/img/WorldPlaneInc.png" style="float: right;"/>
		</div>
		<form class="container" id="needs-validation" novalidate method="get">
			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="validationCustom01">Departure Airport</label>
					<input type="text" name="departureAirport" class="form-control" id="departCode" placeholder="First name" value="BOS" required>
				</div>
				<div class="col-md-6 mb-3">
					<label for="validationCustom02">Arrival Airport</label>
					<input type="text" name="arrivalAirport" class="form-control" id="arrivalCode" placeholder="Last name" value="JFK" required>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="validationCustom03">Departure Date</label>
					<input type="date" name="departureDate" class="form-control" id="departDate" placeholder="" required>
				<div class="invalid-feedback">
				Please provide a valid city.
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
		<button class="btn btn-primary" id="returnFlights" type="submit" value="getFlights">Submit form</button>
		</form>
		
		<div id="displayTable">
			
			
		</div>
	</div>
</div>
	<strong>Ajax Response</strong>:
	<div id="ajaxGetUserServletResponse"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#returnFlights').click(function() {
				console.log("Departure: ", $('#departCode').val());
				$.ajax({
					url : 'GetFlightInfoServlet',
					data : {
						//arrivalDate : $('#arrivalDate').val(),
						departureCode : $('#departCode').val(),
						arrivalCode : $('#arrivalCode').val(),
						departDate:  $('#departDate').val().replace(/-/g, '_')
					},
					success : function(responseText) {
						$('#ajaxGetUserServletResponse').text(responseText);
					}
				});
			});
			
			$( "#roundTrip" ).click(function() {
				if($("#roundTrip").is(':checked')){
					$(".arrival").removeClass('hidden');
				} else {
					$(".arrival").addClass('hidden');
				}
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