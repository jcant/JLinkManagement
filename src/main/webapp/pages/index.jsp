<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JLink Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link href="css/main.css"  rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #FFFFFF;">

		<span class="sitename">JLink Management</span>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto nav-tabs">
				<li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link1</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link2</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link3</a></li>
			</ul>
			<button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#loginFormModal">Login</button>
			<span>&nbsp;</span>
			<button type="button" class="btn btn-outline-success">Register new</button>

		</div>
	</nav>


	<div align="center">
		<h1>Your login is: ${login}, your roles are:</h1>
		<c:forEach var="s" items="${roles}">
			<h3>
				<c:out value="${s}" />
			</h3>
		</c:forEach>

		<c:url value="/update" var="updateUrl" />
		<form action="${updateUrl}" method="POST">
			E-mail:<br />
			<input type="text" name="email" value="${email}" /><br /> <input
				type="submit" value="Update" />
		</form>

		<c:url value="/links" var="linksUrl" />
		<p>
			view Links List: <a href="${linksUrl}">Links</a>
		</p>
		<c:url value="/logout" var="logoutUrl" />
		<p>
			Click to logout: <a href="${logoutUrl}">LOGOUT</a>
		</p>
	</div>



	<!-- Modal Login Form -->
	<div class="modal fade" id="loginFormModal" tabindex="-1" role="dialog" aria-labelledby="loginFormTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="loginFormTitle">Login Form</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">

					<c:url value="/j_spring_security_check" var="loginUrl" />
	        <form action="${loginUrl}" method="POST">
  					<div class="form-group">
    					<label for="inputLogin1">Login</label>
    					<input type="text" name="j_login" class="form-control" id="inputLogin1" placeholder="Enter login">
  					</div>
  					<div class="form-group">
    					<label for="inputPassword1">Password</label>
    					<input type="password" name="j_password" class="form-control" id="inputPassword1" placeholder="Password">
  					</div>
  					<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</div>
	    </div>
	  </div>
	</div>


</body>
</html>
