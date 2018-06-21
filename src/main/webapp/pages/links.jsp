<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
				<div class="container" id="link_add">
					<form>
						<div class="form-row">	
							<div class="form-group align-self-end col-md-3">
								<select id="rootLinks" class="form-control">
        							<option selected>Problems with connection to REST service?</option>
      							</select>
      						</div>
							<div class="form-group col-md-3">
								<label for="checkURL" >CheckedURL</label>
								<input type="text" class="form-control" id="checkURL">
							</div>
							<div class="form-group align-self-end col-md-2">
								<button type="button" id="checkButton" class="btn btn-primary">Check</button>
							</div>
						</div>
						<div class="container" id="checkSamples">			
							<div class="form-row">
								<div class="form-group col-md-3">
									<input type="text" class="form-control" readonly id="sampleURL1" style="display:none;">
								</div>
								<div class="form-group align-self-end col-md-2">
									<button type="button" id="buyURL1" class="btn btn-primary" style="display:none;">Buy</button>
								</div>
								<div class="form-group col-md-3">
									<input type="text" class="form-control" readonly id="sampleURL2" style="display:none;">
								</div>
								<div class="form-group align-self-end col-md-2">
									<button type="button" id="buyURL2" class="btn btn-primary" style="display:none;">Buy</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="container" id="link_list">
					Problems with connection to REST service?
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