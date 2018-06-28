<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JLink Management</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<link href="css/main.css" rel="stylesheet">

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

<script src='js/profile.js'></script>
</head>
<body>


<%@include file="header.jsp" %>

	<div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col">
				<div id = "message"></div>
				<form id="user_update_form" method="post">
					<div class="form-group">
						<label for="userName">Name</label>
						<input type="text" class="form-control" id="userName" name="userName" placeholder="Enter your name" value="<c:out value='${name}'/>">
					</div>
					<div class="form-group">
						<label for="userLogin">Login</label>
						<input type="text" class="form-control" id="userLogin" readonly value="<c:out value='${login}'/>">
					</div>
					<div class="form-group">
						<label for="userEmail">Email address</label>
						<input type="email" class="form-control" id="userEmail" name="userEmail" aria-describedby="emailHelp" placeholder="* none *" readonly value="<c:out value='${email}'/>">
						<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="currentPassword">Password</label>
						<input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Current Password">
					</div>
					<div class="form-group">
						<label for="newPassword">New Password</label>
						<input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter New Password">
					</div>
					<div class="form-group">
						<label for="confirmNewPassword">New Password</label>
						<input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" placeholder="Confirm New Password">
					</div>
					<button type="button" id="saveButton" class="btn btn-primary">Save</button>
				</form>
			</div>
			<div class="col-sm"></div>
		</div>
	</div>


<%@include file="footer.jsp" %>


</body>
</html>

<script type="application/javascript">
    var uname = "<c:out value='${login}' />";
    var userId = "<c:out value='${userId}' />";
</script>
