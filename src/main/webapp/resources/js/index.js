$ = jQuery.noConflict();
$(function ($) {

    getArticles('articles/getActual','articles_container');
    getAdv('promo/getActual','adv_container');
    $('#create_new_user').click(function(){
    	register();
    });
});

function getArticles(url, id) {
    var data = {};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (article) {
            hstring += '<div class="container">\n' +
                '<h3>' + article.header + '</h3>\n' +
                '<div class="container">' + article.text + '</div>\n' +
                '</div>';
        });
        $('#' + id).html(hstring);
    });
    getting.fail(function (event) {
        console.log(event.responseText);
    });
}

function getAdv(url, id) {
    var data = {};
    var advGetting = $.get(url, data, 'json');

    advGetting.done(function (data) {
        var hstring = "";
        data.forEach(function (adv) {

            hstring += '<div class="container">\n' +
                '<h3>' + adv.company + '</h3>\n' +
                '<h4>' + adv.header + '</h4>\n' +
                '<div class="container">' + adv.text + '</div>\n' +
                '</div>';
        });
        $('#' + id).html(hstring);
    });
    advGetting.fail(function (event) {
        console.log(event);
    });
}

function register(){
	console.log("in register1()");
	data = {login: $('#inputLogin2').val(), password: $('#inputPassword2').val(), email: $('#inputEmail2').val()};
	console.log("in register2()");
	posting = $.post('/users/add', data, 'json');
	
	$('#close_button2').click();
	
	posting.done(function (data) {
    	console.log("in registerGood()");
    	$("#message").html(
				  '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
				  	'<div><strong>New user created!</strong></div>' +
				  	'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
				  	'<span aria-hidden="true">&times;</span>' +
				  '</button>' +
				  '</div>');
    	
    });
    
    posting.fail(function (event) {
    	console.log("in registerFail()");
    	$("#message").html(
				  '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
				  	'<div><strong>Error!</strong> User did\'t create</div>' +
				  	'<div>response: "' +JSON.parse(event.responseText)["message"]+ '"</div>' +
				  	'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
				  	'<span aria-hidden="true">&times;</span>' +
				  '</button>' +
				  '</div>');
		  console.log("POST create new User - fail!");
		  console.log(event);
    });
}
