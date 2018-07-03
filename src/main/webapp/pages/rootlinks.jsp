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

    <script src='js/rootlinks.js'></script>
</head>
<body>


<%@include file="header.jsp"%>

<div>
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col">
            <div class="container" id="link_add">
                <div id = "message"></div>
                <form>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="rootlinkURL" >Root Link</label>
                            <input type="text" class="form-control" id="rootlinkURL">
                        </div>
                        <div class="form-group align-self-end col-md-2">
                            <button type="button" id="createButton" class="btn btn-primary">Create</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="container">
                <div class="table-responsive-lg">
                    <table class="table table-hover">
                        <thead className="thead-dark">
                        <tr>
                            <th scope="col">URL</th>
                            <th scope="col">Enabled</th>
                            <th scope="col">Save</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody id="link_list">
                        <tr>
                            <td><img src="images/loading.gif"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-sm-2">

        </div>
    </div>
</div>


<%@include file="footer.jsp"%>

<!-- Modal Login Form -->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmTitle">Confirmation of delete RootLink</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container">
                    Are you sure to delete the RootLink?
                </div>
                <input type="hidden" id="delete_id">
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="submit_delete">YES</button>
                <button type="button" class="btn btn-success" data-dismiss="modal">NO</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>