$ = jQuery.noConflict();
$(function ($) {
	firstTime = true;
	getLinks('/links/'+uname,'link_list');
	
	//$(".form-row").mouseenter(function(){
	//		console.log("enter");
	//		rowHoverIn(this);
	//});
	
	//$(".form-row").mouseleave(function(){
	//		console.log("out"); 
	//		rowHoverOut(this);
	//});
	
});

function getLinks(url, id) {
    var data = {};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        //var date = new Date();
        var hstring = "";
        data.forEach(function (link) {
            //date.setTime(link.startDate);
            //var startString = "" + date.getDate()+"."+(date.getMonth()+1)+"."+date.getFullYear();
            var startString = getCorrectDate(link.startDate);
            //date.setTime(link.endDate);
            //var endString = "" + date.getDate()+"."+(date.getMonth()+1)+"."+date.getFullYear();
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
    		$("input").change(function(){
    			//console.log("input changed");
    			showSave(this);
    		});
    		
    		$("button").click(function(){
    			submitRow(this);
    		});
    		
    		firstTime = false;
    	}
    });
    
    getting.fail(function (event) {
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
	
	//console.log("id="+id);
	//console.log("url="+url);
	
	var data = {target: $("#inputTarget"+id).val()};
	
	//console.log("data=");
	//console.log(data);
    
	$.ajax({
		  method: "POST",
		  url: url,
		  data: data
		})
	  .done(function() {
		  //console.log("POST done!");
		  $(button).hide();
	  })
	  .fail(function(event) {
		  console.log("POST fail!");
		  console.log(event);
	  });
//	  .always(function() {
//		  console.log("POST always...");
//	  });
	
	
//	var posting = $.post(url, data, 'html');
//
//    posting.done(function (data) {
//    	console.log("POST -done!");
//    	console.log(data);
//    	getLinks('/links/'+uname,'link_list');
//    });
//    
//    posting.fail(function (resp, event) {
//        console.log("POST -error!");
//        console.log("resp="+resp);
//        console.log("event="+event);
//    });
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


