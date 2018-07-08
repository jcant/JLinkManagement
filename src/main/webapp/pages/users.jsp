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

    <script src='js/users.js'></script>
    <script src='js/utils.js'></script>
    <link href="css/main.css" rel="stylesheet">

</head>
<body>

<%@include file="_header.jsp"%>

<div>
    <div class="row">
        <div class="col-sm-2">
            <div class="card-header bg-warning">Role: Admin</div>
            <ul class="list-group list-group-item-action" id="admins_list"></ul>
            <div class="card-header bg-warning">Role: User</div>
            <ul class="list-group list-group-item-action" id="users_list"></ul>
        </div>
        <div class="col">
            <div class="container" id="message"></div>
            <div class="container" id="user_details">
				<form>
					<input type="hidden" id="userId" name="userId" />
					<div class="form-group">
						<label for="adminPass">Admin password</label>
						<input type="password" class="form-control" id="adminPass" name="adminPass" placeholder="confirm your rights..." />
					</div>
					<div class="form-group">
						<label for="userName">Name</label>
						<input type="text" class="form-control" id="userName" name="userName" placeholder="* name *" />
					</div>
					<div class="form-group">
						<label for="userLogin">Login</label>
						<input type="text" class="form-control" id="userLogin" readonly placeholder="* login *" />
					</div>
					<div class="form-group">
						<label for="userEmail">Email address</label>
						<input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="* email *" />
					</div>
					<div class="form-group">
						<input type="checkbox" id="resetPassword">
						<label class="form-check-label" for="resetPassword">Password reseting request</label>
					</div>
					<div class="form-group">
						<input type="checkbox" id="blockUser">
						<label class="form-check-label" for="blockUser">Blocked</label>
					</div>
					<div class="form-group">
						<select id="userRole" class="form-control" name="userRole">
							<option value="0">Admin</option>
							<option value="1">User</option>
						</select>
					</div>
					<button type="button" id="save_user" class="btn btn-primary">Save</button>
				</form>

            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>

<%@include file="_footer.jsp"%>

<%@include file="_modalConfirmForm.jsp"%>

</body>
</html>

