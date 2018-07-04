<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JLink Management</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<script type="application/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<script src='https://www.google.com/recaptcha/api.js'></script>

<script src='js/payment.js'></script>
<link href="css/main.css" rel="stylesheet">
</head>
<body>


	<%@include file="_header.jsp"%>

	<div>
		<div class="row">

			<div class="col-sm-2">left</div>

			<div class="col">
				
				<div class="card text-center">
					<div class="card-header">Payment system frame</div>
					<div class="card-body">
						<h5 class="card-title">Visa/MasterCard etc...</h5>
						<p class="card-text">You are going to buy Link:</p>
						<p class="card-text">Link URL: <strong><c:out value="${sessionScope.jlink.url}"/></strong></p>
						<p class="card-text">Link Target: <strong><c:out value="${sessionScope.jlink.target}"/></strong></p>
						<form id="confirmBuy" action="/paymentconfirm" method="post">
							<input type="hidden" name="confirm" id="confirm">
						</form>
						<a href="#" class="btn btn-primary" id="buyButton">BUY</a>
						<a href="#" class="btn btn-primary" id="cancelButton">CANCEL</a>
					</div>
					<div class="card-footer text-muted">some message</div>
				</div>
				
			</div>

			<div class="col-sm">right</div>
		</div>
	</div>


	<%@include file="_footer.jsp"%>


</body>
</html>
