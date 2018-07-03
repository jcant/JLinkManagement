$ = jQuery.noConflict();
var showArc = false;
$(function ($) {
	
	$('form button').on("click",function(e){
	    e.preventDefault();
	});

	getLinks('/link/'+uname+'/free','link_list');
	getRootLinks('/rootlinks/getActual', 'rootLinks');
	
	$("#createButton").click(function(){
		createLink();
	});

	$("#target").blur(function(){
		checkTarget();
	});

    $("#showArchive").click(function(){
        showArc = !showArc;
        getLinks('/link/'+uname+'/free','link_list');
    });
});


function createLink(){
	if (!checkTarget()){
		return;
	}

	var url = "/link/addfree";
	var data = {rootUrl: $("#rootLinks").val(), userPart: "", mode: "", target: $("#target").val(), type: "free"};
 
	$.ajax({
		  method: "POST",
		  url: url,
		  data: data
		})
	  .done(function(data) {
		  getLinks('/link/'+uname+'/free','link_list');
		  $("#target").val("");
		  $("#message").html(
				  '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
				  	'<div><strong>New link created!</strong></div>' +
				  	'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
				  	'<span aria-hidden="true">&times;</span>' +
				  '</button>' +
				  '</div>');
	  })
	  .fail(function(event) {
		  $("#message").html(
				  '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
				  	'<div><strong>Error!</strong> Some problem with you parameters. Link did\'t create</div>' +
				  	'<div>response: "'+ JSON.parse(event.responseText)["message"] + '"</div>' +
				  	'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
				  	'<span aria-hidden="true">&times;</span>' +
				  '</button>' +
				  '</div>');
		  console.log("POST add new Link - fail!");
		  console.log(event);
	  });
}

function checkTarget(){
	var value = $("#target").val();
	if (value == ""){
		$("#target").removeClass("is-valid");
    	$("#target").addClass("is-invalid");
    	return false;
	} else {
		$("#target").removeClass("is-invalid");
    	$("#target").addClass("is-valid");
    	return true;
	}
}


function getRootLinks(url, id) {
    var data = {};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (rlink) {        
            hstring += 
            '<option>'+
            	rlink.url+
            '</option>';
        });

        $('#' + id).html(hstring);
    	
    });
    
    getting.fail(function (event) {
    	console.log("GET Links fail!");
    	console.log(event.responseText);
    });
}

function getLinks(url, id) {
    var data = {archive: showArc};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (link) {
            var warnClass = "";
            var errClass = "";
            var readOnlyAdd = "";
            var startString = getCorrectDate(link.startDate);
            var finishString = getCorrectDate(link.finishDate);

            var fDate = new Date();
            var currDate = new Date();
            fDate.setTime(link.finishDate);
            if(currDate > fDate){
                warnClass = "red_warn";
                errClass = "red_err";
                readOnlyAdd = "readonly";
            }

            var checked = "";
            if (link.enabled) checked = "checked";

            hstring +=

                '<tr id=link'+link.id+' class = "'+warnClass+'">' +
                	'<td>' +
                		'<input type="text" class="form-control" id="inputURL_'+link.id+'" readonly value="'+link.url+'">'+
                	'</td>' +
                	'<td>' +
                		'<input type="text" class="form-control" id="inputTarget_'+link.id+'" name="target" '+readOnlyAdd+' value="'+link.target+'">'+
                	'</td>' +
                	'<td>' +
                		'<input type="date" class="form-control" id="inputDateStart_'+link.id+'" readonly value="'+startString+'">'+
                	'</td>' +
                	'<td>' +
                		'<input type="date" class="form-control '+errClass+'" id="inputDateFinish_'+link.id+'" readonly value="'+finishString+'">'+
                	'</td>' +
                	'<td class="align-middle">';
            if (currDate <= fDate) {
                hstring +=
                    	'<input type="checkbox" class="form-check-input" id="enabledCheck_' + link.id + '" ' + checked + '>' +
                    	'<label class="form-check-label" for="enabledCheck_' + link.id + '">Enabled</label>';
            }
            hstring+=
                	'</td>' +
                	'<td>' +
                		'<button type="button" id="'+link.id+'" class="btn btn-success" style="display:none;">Save</button>'+
                	'</td>' +
                '</tr>';
        });

        $('#' + id).html(hstring);
        
    	$("#link_list").find("input").change(function(){
    		showSave(this);
    	});
    		
    	$("#link_list").find("button").click(function(){
    		submitRow(this);
    	});

    });
    
    getting.fail(function (event) {
    	console.log("GET Links fail!");
    	console.log(event.responseText);
    });
}

function showSave(input){
	var strid = $(input).attr("id");
	var id = strid.substring(strid.lastIndexOf("_")+1);
	$("#"+id).show();
}

function submitRow(button){
	var id = $(button).attr("id");
	var url = "/link/"+id;
	var data = {target: $("#inputTarget_"+id).val(), enabled: $('#enabledCheck_'+id).is(":checked")};
 
	$.ajax({
		  method: "POST",
		  url: url,
		  data: data
		})
	  .done(function() {
		  $(button).hide();
	  })
	  .fail(function(event) {
		  console.log("POST to submit row - fail!");
		  console.log(event);
	  });
}

function getCorrectDate(shtamp){
	var date = new Date();
	date.setTime(shtamp);
	var result = "";
	var day = date.getDate();
	var month = date.getMonth()+1;
	
	result+=date.getFullYear();
	
	result+="-";
	
	if (month<10) result+="0"+month;
	else result+=month;
	
	result+="-";
	
	if (day<10) result+="0"+day;
	else result+=day;
	
	return result;
}


