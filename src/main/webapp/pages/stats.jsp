<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JLink Management</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<script type="application/javascript" src="https://code.jquery.com/jquery-3.3.1.js" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<script src='https://www.google.com/recaptcha/api.js'></script>

<script src='js/stats.js'></script>
<script src='js/utils.js'></script>
<link href="css/main.css" rel="stylesheet">
</head>
<body>

<%@include file="_header.jsp" %>

	<div>
		<div class="row">
			<div class="col-sm-2">
				<div class="card-header bg-warning">Paid links</div>
				<ul class="list-group list-group-item-action" id="paid_link_list"></ul>
				<div class="card-header bg-warning">Free links</div>
				<ul class="list-group list-group-item-action" id="free_link_list"></ul>
			</div>
			<div class="col">
				<div class="container">
					<h5>Links statistics of <strong><span id="header_username">#none</span></strong></h5>
				</div>
				<div class="container" id="statistics">
					<div class="table-responsive-lg">
					<table class="table table-hover">
						<thead className="thead-dark">
							<tr>
								<th scope="col">Scope</th>
								<th scope="col">Value</th>
								<th scope="col">Value per Day</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>All</td>
								<td id="AllValue"></td>
								<td id="AllPerDay"></td>
							</tr>
							<tr>
								<td>Last Day</td>
								<td id="DayValue"></td>
								<td id="DayPerHour"></td>
							</tr>
							<tr>
								<td>Last Week</td>
								<td id="WeekValue"></td>
								<td id="WeekPerDay"></td>
							</tr>
							<tr>
								<td>Last Month</td>
								<td id="MonthValue"></td>
								<td id="MonthPerDay"></td>
							</tr>
							<tr>
								<td>Last Year</td>
								<td id="YearValue"></td>
								<td id="YearPerDay"></td>
							</tr>
						</tbody>
					</table>
					<div class="container" id="DayRemains"></div>
			
					</div>
					
				</div>
			</div>
			<div class="col-sm-2">
			
				<%@include file="_userSelection.jsp" %>
			
			</div>
		</div>
	</div>

<%@include file="_footer.jsp" %>


<script type="application/javascript">
var uname = "<c:out value='${login}' />";

function changeSelectedUserImpl(item){
	uname = $(item).val();
	$('#header_username').html(uname);
	getLinks('/link/'+uname+'/paid','paid_link_list');
	getLinks('/link/'+uname+'/free','free_link_list');
			
	jcaUtils.clearValues(['AllValue', 'AllPerDay', 'DayValue', 'DayPerHour', 'WeekValue', 'WeekPerDay', 'MonthValue', 'MonthPerDay', 'YearValue', 'YearPerDay', 'DayRemains'], 'html');
}

</script>

</body>
</html>