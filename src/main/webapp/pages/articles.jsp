<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JLink Management</title>

<link rel="stylesheet" href="bootstrap/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<script type="application/javascript" src="bootstrap/jquery-3.3.1.js" ></script>

<script src="bootstrap/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>

<script	src="bootstrap/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<script src='https://www.google.com/recaptcha/api.js'></script>

<script src='js/articles.js'></script>
<link href="css/main.css" rel="stylesheet">

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
				<div class="container"><button type="button" class="btn btn-success" id="create_article">Create new Article</button></div>
				<div class="container" id="message"></div>
				<div class="container" id="article_details">
					<form>
						<input type="hidden" id="art_id" name="id">
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
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="inputDateStart">Start date</label>
								<input type="date" class="form-control" id="inputDateStart" placeholder="article start date of publication">
							</div>
							<div class="form-group col-md-3">
								<label for="inputDateFinish">Finish date</label> 
								<input type="date" class="form-control" id="inputDateFinish" placeholder="article finish date of publication">
							</div>
						</div>
						
						<div class="form-group">
							<label for="inputText">Text</label> 
							<textarea class="form-control" rows="8" id="inputText" placeholder="article text"></textarea>
						</div>
						
						<button type="button" class="btn btn-primary" id="save_article">Save</button>
					</form>

				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
	
	
	<!-- Modal Login Form -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmTitle">Confirmation of delete Article</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container">
						Are you sure to delete the Article?  
					</div>
					<input type="hidden" id="delete_id">
					<button type="button" class="btn btn-danger" id="submit_delete">YES</button>
					<button type="button" class="btn btn-success" data-dismiss="modal">NO</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

