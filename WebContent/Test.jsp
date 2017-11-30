<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>jQuery, Ajax and Servlet/JSP integration example</title>
	
	<script src="http://code.jquery.com/jquery-1.10.2.js"
		type="text/javascript"></script>
	<script src="js/app-ajax.js" type="text/javascript"></script>
</head>
<body>
	<form>
		Enter Departure Aiport: <input type="text" id="departCode" />
		Enter Arrival Airport: <input type="text" id="arrivalCode" />
		Enter Date: <input type="text" id="departDate" />
		Enter Your Name: <input type="text" id="userName" />
	</form>
	<br>
	<br>

	<strong>Ajax Response</strong>:
	<div id="ajaxGetUserServletResponse"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#userName').blur(function() {
				console.log("Departure: ", $('#departCode').val());
				$.ajax({
					url : 'GetFlightInfoServlet',
					data : {
						userName : $('#userName').val(),
						departureCode : $('#departCode').val(),
						arrivalCode : $('#arrivalCode').val(),
						departDate: $('#departDate').val()
					},
					success : function(responseText) {
						$('#ajaxGetUserServletResponse').text(responseText);
					}
				});
			});
		});
	</script>
</body>
</html>