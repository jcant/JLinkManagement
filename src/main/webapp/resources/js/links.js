$ = jQuery.noConflict();
$(function ($) {

	getLinks('/links/'+uname,'link_list');
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
								'<label for="inputURL">URL</label>'+
								'<input type="text" class="form-control" id="inputURL" readonly value="'+link.url+'">'+
							'</div>'+
							'<div class="tiny form-group col-md-3">'+
								'<label for="inputTarget">Target</label>'+
								'<input type="text" class="form-control" id="inputTarget" readonly value="'+link.target+'">'+
							'</div>'+
							'<div class="tiny form-group col-md-2">'+
								'<label for="inputDateStart">Start Date</label>'+
								'<input type="date" class="form-control" id="inputDateStart" readonly value="'+startString+'">'+
							'</div>'+
							'<div class="tiny form-group col-md-2">'+
								'<label for="inputDateFinish">Finish Date</label>'+
								'<input type="date" class="form-control" id="inputDateFinish" readonly value="'+endString+'">'+
							'</div>'+
							'<div class="tiny form-group align-self-end col-md-2">'+
								'<button type="submit" class="btn btn-primary">Save</button>'+
							'</div>'+
					
						'</div>'+
					'</form>'+
				'</div>'+
			'</div>';
        });

        $('#' + id).html(hstring);
    });
    
    getting.fail(function (event) {
        console.log(event.responseText);
    });
}

function replaceRowByForm(rowId, formId){
	htmlForm = $('#'+formID).html();
	
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


