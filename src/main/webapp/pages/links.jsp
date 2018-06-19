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

<script src='js/links.js'></script>
</head>
<body>


	<%@include file="header.jsp"%>

	<div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col">
				<div class="container" id="link_list">
					Problems with connection to REST service?
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>


	<%@include file="footer.jsp"%>

<div id="link_edit" style="display: none;">
				<form>
					<div class="form-group">
						<label for="inputURL">Name</label>
						<input type="text" class="form-control" id="inputURL" readonly>
					</div>
					<div class="form-group">
						<label for="inputLogin1">Login</label> <input type="text"
							class="form-control" id="inputLogin1" readonly
							value="<c:out value='${login}'/>">
					</div>
					<div class="form-group">
						<label for="inputEmail1">Email address</label> <input type="email"
							class="form-control" id="inputEmail1"
							aria-describedby="emailHelp" placeholder="* none *" readonly
							value="<c:out value='${email}'/>"> <small id="emailHelp"
							class="form-text text-muted">We'll never share your email
							with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="inputPassword1">Password</label> <input
							type="password" class="form-control" id="inputPassword1"
							placeholder="Password">
					</div>
					<div class="form-group">
						<label for="inputPassword2">Password confirm</label> <input
							type="password" class="form-control" id="inputPassword2"
							placeholder="Password">
					</div>
					<button type="submit" class="btn btn-primary">Save</button>
				</form>
</div>

</body>
</html>

<script type="application/javascript">
var uname = "<c:out value='${login}' />";
</script>