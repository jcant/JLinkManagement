var jcaUtils = {


setValid: function (id) {
    $("#"+id).removeClass("is-invalid");
    $("#"+id).addClass("is-valid");
},
		
setInvalid: function (id) {
    $("#"+id).removeClass("is-valid");
    $("#"+id).addClass("is-invalid");
},
setNeutral: function (id) {
	$("#"+id).removeClass("is-valid");
	$("#"+id).removeClass("is-invalid");
},
		
clearValues: function (id_array, type) {
	id_array.forEach(function (input) {
		if(type=="val") $('#'+input).val("");
		if(type=="html") $('#'+input).html("");
	});
},

ajaxJOperationAnswered: function (url, method, data, messageElementId, isLog, doneFuncName, failFuncName) {

    $.ajax({
        method: method,
        url: url,
        data: data
    })
        .done(function(data) {
            color = "warning";
            if (data.success) color = "success";
        	$("#"+messageElementId).html(
                '<div class="alert alert-'+color+' alert-dismissible fade show" role="alert">' +
                '<div><strong>' + data.message + '</strong></div>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>');

            if (doneFuncName != null) eval(doneFuncName)();
            
            if(isLog){
            	console.log("" + method + " on " + url + " - done!");
            	console.log(data);
            }
        })
        .fail(function(event) {
            $("#"+messageElementId).html(
                '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                '<div><strong>Error!</strong> Some problem with you parameters.</div>' +
                '<div>response: "'+ JSON.parse(event.responseText)["message"] + '"</div>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>');
            
            if (failFuncName != null) eval(failFuncName)();
            
            if(isLog){
            	console.log("" + method + " on " + url + " - fail!");
            	console.log(event);
            }
        });
},


getCorrectDate: function (shtamp) {
	if (shtamp == null) return "";
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


}