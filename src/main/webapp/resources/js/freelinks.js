$ = jQuery.noConflict();
var showArc = false;
$(function ($) {
	
	$('form button').on("click",function(e){
	    e.preventDefault();
	});

	$('#header_username').html(uname);
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
    
    $('#submit_delete').click(function(){
        deleteFreeLink();
    });
});


function deleteFreeLink(){
	
	jcaUtils.ajaxJOperationAnswered("/link/"+$('#delete_id').val(), "DELETE", {}, "message", true, ajaxDone, null);
}

function createLink(){
	if (!checkTarget()) return;

	jcaUtils.ajaxJOperationAnswered("/link/addfree", "POST", {rootUrl: $("#rootLinks").val(), userPart: "", mode: "", target: $("#target").val(), type: "free"}, "message", true, ajaxDone, null);
}

function ajaxDone(){
	getLinks('/link/'+uname+'/free','link_list');
	getRootLinks('/rootlinks/getActual', 'rootLinks');
	clearInputs();
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
            var startString = jcaUtils.getCorrectDate(link.startDate);
            var finishString = jcaUtils.getCorrectDate(link.finishDate);

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
                	'<td>' +
                		'<button type="button" class="close" data-toggle="modal" data-target="#confirmModal" aria-label="Close" id="del_'+link.id+'">' +
                			'<span aria-hidden="true">&times;</span>' +
                		'</button>' +
            		'</td>' +
                '</tr>';
        });

        $('#' + id).html(hstring);
        
    	$("#link_list").find("input").change(function(){
    		showSave(this);
    	});
    		
    	$("#link_list").find("button.btn-success").click(function(){
    		submitRow(this);
    	});
    	
    	$('td button.close').click(function(){
            strid = $(this).attr("id");
            id = strid.substring(strid.lastIndexOf("_")+1);
            $('#delete_id').val(id);
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

function clearInputs(){
    $('#target').val("");
}