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


<link href="/css/main.css" rel="stylesheet">
</head>
<body>
	
<%@include file="_header.jsp" %>

	<div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col red_warn">
				<div class="text-center fittext">
					<h3>JLink Management ERROR Page</h3>
					<div class=""><strong><c:out value = "${datetime}"/></strong></div>
					<div class="">URI: <strong><c:out value = "${url}"/></strong></div>
					<div class="">Message: <strong><c:out value = "${message}"/></strong></div>
				</div>
				<div>
					<a href="/">Return to Home</a>
				</div>			
			</div>
			<div class="col-sm-2" id="adv_container"></div>
		</div>
	</div>

<%@include file="_footer.jsp" %>

</body>
</html>
