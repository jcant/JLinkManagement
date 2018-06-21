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
	})
});

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
            var endString = getCorrectDate(link.endDate);
            
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
	console.log("selected root="+rootSelected);
	
	var entered = $("#checkURL").val();
	console.log("entered URL part="+entered);
	
	var chURL1 = "http://"+entered+"."+rootSelected+"/";
	var chURL2 = "http://"+rootSelected+"/"+entered+"/";
	console.log("checking URL1="+chURL1);
	console.log("checking URL2="+chURL2);
	
	$("#sampleURL1").val(chURL1).show();
	$("#sampleURL2").val(chURL2).show();
	
	var data = {};
    var getting1 = $.post("/link/check", {url: chURL1}, 'json');
    var getting2 = $.post("/link/check", {url: chURL2}, 'json');

    getting1.done(function (data) {
        console.log("URL1 done");
        console.log(data);
        if(data){
        	$("#sampleURL1").removeClass("is-invalid");
        	$("#sampleURL1").addClass("is-valid");
        	$("#buyURL1").show();
        }else{
        	$("#sampleURL1").removeClass("is-valid");
        	$("#sampleURL1").addClass("is-invalid");
        	$("#buyURL1").hide();
        }
    });
    
    getting1.fail(function (event) {
    	console.log("URL1 check fail!");
    	console.log(event.responseText);
    });
    
    
    getting2.done(function (data) {
        console.log("URL2 done");
        console.log(data);
        if(data){
        	$("#sampleURL2").removeClass("is-invalid");
        	$("#sampleURL2").addClass("is-valid");
        	$("#buyURL2").show();
        }else{
        	$("#sampleURL2").removeClass("is-valid");
        	$("#sampleURL2").addClass("is-invalid");
        	$("#buyURL2").hide();
        }
    });
    
    getting2.fail(function (event) {
    	console.log("URL2 check fail!");
    	console.log(event.responseText);
    });
	
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
	var data = {target: $("#inputTarget"+id).val()};
 
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


