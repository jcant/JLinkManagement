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

<script src='js/articles.js'></script>
</head>
<body>

	<%@include file="header.jsp"%>

	<div>
		<div class="row">
			<div class="col-sm-2">
				<div class="card-header bg-warning">Articles</div>
				<ul class="list-group list-group-item-action" id="articles_list"></ul>
				<div class="card-header bg-warning">Advertising</div>
				<ul class="list-group list-group-item-action" id="advertising_list"></ul>
			</div>
			<div class="col">
				<div class="container" id="article_details">

					<form>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="inputHeader">Header</label>
								<input type="text" class="form-control" id="inputHeader" placeholder="header of an article">
							</div>
							<div class="form-group col-md-3">
								<label for="inputCreatedDate">Article creation date</label> 
								<input type="date" class="form-control" id="inputCreatedDate" placeholder="article creation date">
							</div>
							<div class="form-group col-md-3">
								<label for="inputAuthor">Author</label>
								<select id="inputAuthor" class="form-control">
									<option selected>Choose...</option>
									<option>...</option>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputDateStart">Start date</label>
								<input type="date" class="form-control" id="inputDateStart" placeholder="article start date of publication">
							</div>
							<div class="form-group col-md-6">
								<label for="inputDateFinish">Finish date</label> 
								<input type="date" class="form-control" id="inputDateFinish" placeholder="article finish date of publication">
							</div>
						</div>
						
						<div class="form-group">
							<label for="inputText">Text</label> 
							<input type="text" class="form-control" id="inputText" placeholder="article text">
						</div>
						
						<button type="button" class="btn btn-primary" id="save_article">Save</button>
					</form>

				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>

	<%@include file="footer.jsp"%>

</body>
</html>

<script type="application/javascript">
	
var uname = "<c:out value='${login}' />";

</script>