$ = jQuery.noConflict();
$(function ($) {

	getAuthors();
	getArticles('/articles/getActual','articles_list');
    clearInputs();

	$('#save_article').click(function(){
		saveArticle();
	});
	
	$('#create_article').click(function(){
		createArticle();
	});
	
	$('#submit_delete').click(function(){
		deleteArticle();
	});
});

function createArticle(){
    clearInputs();
}

function deleteArticle(){
	
	jcaUtils.ajaxJOperationAnswered("/articles/"+$('#delete_id').val(), "DELETE", {}, "message", true, ajaxDone, null);
}

function saveArticle(){
	
	if (!checkHeader()) return;
	
	data = {header: $("#inputHeader").val(), login: $("#inputAuthor").val()};
	if ($("#inputText").val() != '') data.text = $("#inputText").val();
	if ($("#inputCreatedDate").val() != '') data.created = $("#inputCreatedDate").val();
	if ($("#inputDateStart").val() != '') data.pubStart = $("#inputDateStart").val();
	if ($("#inputDateFinish").val() != '') data.pubFinish = $("#inputDateFinish").val();
	
	jcaUtils.ajaxJOperationAnswered("/articles/"+$('#art_id').val(), "POST", data, "message", true, ajaxDone, null);
}

function ajaxDone(){
	getArticles('/articles/getActual','articles_list');
	clearInputs();
}


function getArticles(url, id) {

	var data = {archive: false};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (article) {

       	hstring +=
       		'<li id=art_'+article.id+' class = "list-group-item list-group-item-action" style="cursor: pointer;">' +
       			'<small>'+article.header+'</small>' +
       			'<button type="button" class="close" data-toggle="modal" data-target="#confirmModal" aria-label="Close" id="del_'+article.id+'">' +
       				'<span aria-hidden="true">&times;</span>' +
       			'</button>' +
       		'</li>';
        });

        $('#' + id).html(hstring);
        
        $('li.list-group-item').click(function(){
        	$('li.list-group-item').removeClass('active');
        	$(this).addClass('active');
        	getArticle(this);
        });
        
        $('li button.close').click(function(){
        	strid = $(this).attr("id");
        	id = strid.substring(strid.lastIndexOf("_")+1);
        	$('#delete_id').val(id);
        });

    });
    
    getting.fail(function (event) {
    	console.log("GET article fail!");
    	console.log(event.responseText);
    });
}

function getArticle(li) {
	var strid = $(li).attr("id");
	var id = strid.substring(strid.lastIndexOf("_")+1);
	
	var getting = $.get('/articles/'+id, {}, 'json');
	
	getting.done(function (article) {
		//st = JSON.parse(data);
		$('#art_id').val(id);
		$('#inputHeader').val(article.header);
		$('#inputCreatedDate').val(jcaUtils.getCorrectDate(article.created));
		$('#opt_'+article.author.id).attr("selected","selected");
		$('#inputDateStart').val(jcaUtils.getCorrectDate(article.pubStart));
		$('#inputDateFinish').val(jcaUtils.getCorrectDate(article.pubFinish));
		$('#inputText').val(article.text);
    });
    
    getting.fail(function (event) {
    	console.log("GET Article info fail!");
    	console.log(event.responseText);
    });
}

function checkHeader(){
	var value = $("#inputHeader").val();
	if (value == ""){
		$("#inputHeader").removeClass("is-valid");
    	$("#inputHeader").addClass("is-invalid");
    	return false;
	} else {
		$("#inputHeader").removeClass("is-invalid");
    	$("#inputHeader").addClass("is-valid");
    	return true;
	}
}

function getAuthors(){	
	
	var getting = $.get('/users/getAdmins', {}, 'json');	
	getting.done(function (data) {
		hstring = "";
		
		data.forEach(function (author) {        
            hstring += 
            '<option id="opt_'+author.id+'">'+
            	author.login+
            '</option>';
        });
		$('#inputAuthor').html(hstring);
		
    });
    
    getting.fail(function (event) {
    	console.log("GET authors fail!");
    	console.log(event.responseText);
    });
}

function clearInputs(){
    $('#art_id').val(-1);
    $('#inputHeader').val("");
    $('#inputCreatedDate').val(jcaUtils.getCorrectDate(new Date()));
    $('#opt_1').attr("selected","selected");
    $('#inputDateStart').val("");
    $('#inputDateFinish').val("");
    $('#inputText').val("");
}