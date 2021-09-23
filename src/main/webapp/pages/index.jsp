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

<script src='js/index.js'></script>
<script src='js/utils.js'></script>
<link href="css/main.css" rel="stylesheet">
</head>
<body>
	

<%@include file="_header.jsp" %>


	<div>
		<div class="row">
			<div class="col">
			<div id="message"></div>
			<div class="container" id="articles_container"></div>
			</div>
			<div class="col-sm-4" id="adv_container"></div>
		</div>
	</div>



<%@include file="_footer.jsp" %>



	<!-- Modal Login Form -->
	<div class="modal fade" id="loginFormModal" tabindex="-1" role="dialog"
		aria-labelledby="loginFormTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginFormTitle">Login Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<c:url value="/j_auth" var="loginUrl" />
					<form action="${loginUrl}" method="POST">
						<div class="form-group">
							<label for="inputLogin1">Login</label> <input type="text"
								name="j_login" class="form-control" id="inputLogin1"
								placeholder="Enter login">
						</div>
						<div class="form-group">
							<label for="inputPassword1">Password</label> <input
								type="password" name="j_password" class="form-control"
								id="inputPassword1" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</div>
			</div>
		</div>
	</div>

	<!-- Modal Register new Form -->
	<div class="modal fade" id="registerFormModal" tabindex="-1"
		role="dialog" aria-labelledby="registerFormTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="registerFormTitle">Register New
						User</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="close_button2">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="inputLogin2">Login</label> <input type="text"
								name="login" class="form-control" id="inputLogin2"
								placeholder="Enter login">
						</div>
						<div class="form-group">
							<label for="inputPassword2">Password</label> <input
								type="password" name="password" class="form-control"
								id="inputPassword2" placeholder="Password">
						</div>
						<div class="form-group">
							<label for="inputPasswordConf2">Confirm Password</label> <input
								type="password" name="passwordConf" class="form-control"
								id="inputPasswordConf2" placeholder="Password">
						</div>
						<div class="form-group">
							<label for="inputEmail2">Email</label> <input type="email"
								name="email" class="form-control" id="inputEmail2"
								aria-describedby="email2Help" placeholder="Enter email">
							<small id="email2Help" class="form-text text-muted">We'll
								never share your email with anyone else.</small>
						</div>
						<button type="button" class="btn btn-primary" id="create_new_user">Submit</button>
						<c:if test="${exists ne null}">
							<p>User already exists!</p>
						</c:if>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>
