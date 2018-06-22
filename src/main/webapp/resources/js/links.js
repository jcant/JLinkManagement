$ = jQuery.noConflict();
$(function ($) {
	
	$('form button').on("click",function(e){
	    e.preventDefault();
	});
	
	firstTime = true;
	getLinks('/links/'+uname,'link_list');
	getRootLinks('/rootlinks', 'rootLinks');
	
	$("#checkButton").click(function(){
		checkURL();
	});
	
	$("#buyURL1").click(function(){
		buyURL1();
	});
	$("#buyURL2").click(function(){
		buyURL2();
	});
	
	$("#checkURL").blur(function(){
		var value = $(this).val().replace(/\s/g, ''); 
		$(this).val(value);
	});
	$("#target").blur(function(){
		checkTarget();
	})
});



function buyURL1(){
	buyURL("subdomain");
}
function buyURL2(){
	buyURL("parameter");
}
function buyURL(mode){
	if (!checkTarget()){
		return;
	}
	
	//console.log("mode="+mode);
	var url = "/link/add";
	var data = {rootUrl: $("#rootLinks").val(), userPart: $("#checkURL").val(), mode: mode, target: $("#target").val()};
 
	$.ajax({
		  method: "POST",
		  url: url,
		  data: data
		})
	  .done(function() {
		  getLinks('/links/'+uname,'link_list');
		  $("#checkURL").val("");
		  $("#target").val("");
		  $("#sampleURL1").hide();
		  $("#sampleURL2").hide();
		  $("#buyURL1").hide();
		  $("#buyURL2").hide();
		  $("#message").html(
				  '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
				  	'<strong>New link created!</strong>' +
				  	'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
				  	'<span aria-hidden="true">&times;</span>' +
				  '</button>' +
				  '</div>');
	  })
	  .fail(function(event) {
		  $("#sampleURL1").hide();
		  $("#sampleURL2").hide();
		  $("#buyURL1").hide();
		  $("#buyURL2").hide();
		  $("#message").html(
				  '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
				  	'<strong>Error!</strong> Some problem with you parameters. Link did\'t create' +
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
    var data = {};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (link) {
            var startString = getCorrectDate(link.startDate);
            var endString = getCorrectDate(link.finishDate);
            
            var checked = "";
            if (link.enabled) checked = "checked";
            
            hstring += 
            '<div class="card" id=link'+link.id+'>'+
				'<div class="tiny card-body">'+
					'<form>'+
						'<div class="form-row">'+	
						
							'<div class="tiny form-group col-md-3">'+
								'<label for="inputURL'+link.id+'" >URL</label>'+
								'<input type="text" class="form-control" id="inputURL'+link.id+'" readonly value="'+link.url+'">'+
							'</div>'+
							'<div class="tiny form-group col-md-3">'+
								'<label for="inputTarget'+link.id+'" >Target</label>'+
								'<input type="text" class="form-control" id="inputTarget'+link.id+'" name="target" value="'+link.target+'">'+
							'</div>'+
							'<div class="tiny form-group col-md-2">'+
								'<label for="inputDateStart'+link.id+'" >Start Date</label>'+
								'<input type="date" class="form-control" id="inputDateStart'+link.id+'" readonly value="'+startString+'">'+
							'</div>'+
							'<div class="tiny form-group col-md-2">'+
								'<label for="inputDateFinish'+link.id+'" >Finish Date</label>'+
								'<input type="date" class="form-control" id="inputDateFinish'+link.id+'" readonly value="'+endString+'">'+
							'</div>'+
							'<div class="tiny form-group align-self-end form-check">' +
					    		'<input type="checkbox" class="form-check-input" id="enabledCheck'+link.id+'" '+checked+'>' +
					    		'<label class="form-check-label" for="enabledCheck'+link.id+'">Enabled</label>' +
					    	'</div>' +
							'<div class="tiny form-group align-self-end col-md-2">'+
								'<button type="button" id="'+link.id+'" class="btn btn-primary" style="display:none;">Save</button>'+
							'</div>'+
							
						'</div>'+
					'</form>'+
				'</div>'+
			'</div>';
        });

        $('#' + id).html(hstring);
        
    	if(firstTime){
    		$("#link_list").find("input").change(function(){
    			showSave(this);
    		});
    		
    		$("#link_list").find("button").click(function(){
    			submitRow(this);
    		});
    		
    		firstTime = false;
    	}
    });
    
    getting.fail(function (event) {
    	console.log("GET Links fail!");
    	console.log(event.responseText);
    });
}

function checkURL(){
	var free1 = false;
	var free2 = false;
	
	var rootSelected = $("#rootLinks").val();
	//console.log("selected root="+rootSelected);
	
	var entered = $("#checkURL").val();
	//remove all spaces:
	entered = entered.replace(/\s/g, '');
	//console.log("entered URL part="+entered);
	
	var chURL1 = "http://"+entered+"."+rootSelected+"/";
	var chURL2 = "http://"+rootSelected+"/"+entered;
	//console.log("checking URL1="+chURL1);
	//console.log("checking URL2="+chURL2);
	
	$("#sampleURL1").val(chURL1).show();
	$("#sampleURL2").val(chURL2).show();
	
	if(entered==""){
		setBuyAbility("sampleURL1", "buyURL1", false);
		setBuyAbility("sampleURL2", "buyURL2", false);
		return;
	}
	
	var data = {};
    var posting1 = $.post("/link/check", {url: chURL1}, 'json');
    var posting2 = $.post("/link/check", {url: chURL2}, 'json');

    posting1.done(function (data) {
        //console.log("URL1 done");
        //console.log(data);
        setBuyAbility("sampleURL1", "buyURL1", data);
    });
    
    posting1.fail(function (event) {
    	console.log("URL1 check fail!");
    	console.log(event.responseText);
    });
    
    
    posting2.done(function (data) {
        //console.log("URL2 done");
        //console.log(data);
        setBuyAbility("sampleURL2", "buyURL2", data);
    });
    
    posting2.fail(function (event) {
    	console.log("URL2 check fail!");
    	console.log(event.responseText);
    });
	
}

function setBuyAbility(idInput, idButton, ability){
	if (ability) {
		$("#"+idInput).removeClass("is-invalid");
    	$("#"+idInput).addClass("is-valid");
    	$("#"+idButton).show();
	} else {
		$("#"+idInput).removeClass("is-valid");
    	$("#"+idInput).addClass("is-invalid");
    	$("#"+idButton).hide();
	}
}

function rowHoverIn(row){
	$(row).find("label").show();
}

function rowHoverOut(row){
	$(row).find("label").hide();
}

function showSave(input){
	$(input).closest(".form-row").find("button").show();
}

function submitRow(button){
	var id = $(button).attr("id");
	var url = "/link/"+id;
	var data = {target: $("#inputTarget"+id).val(), enabled: $('#enabledCheck'+id).is(":checked")};
 
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
	date.setTime(shtamp)
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


