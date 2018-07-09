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
     hstring += '<div class="card fat fittext">\n' +
                	'<h5 class="card-header">' + article.header + '</h5>\n' +
                	'<div class="card-body">' + 
                		'<p class="card-text">' + article.text + '</p>' +
                	'</div>\n' +
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

     hstring += '<div class="card text-white bg-warning mb-3 fat fittext" style="max-width: 18rem; min-width: 18rem;">' +
     				'<div class="card-header">' + adv.company + '</div>' +
     				'<div class="card-body">' +
     					'<h5 class="card-title">' + adv.header + '</h5>' +
     					'<p class="card-text">' + adv.text + '</p>' +
     				'</div>' +
     			'</div>';
        });
        
        
        
        $('#' + id).html(hstring);
    });
    advGetting.fail(function (event) {
        console.log(event);
    });
}


function register(){
	data = {login: $('#inputLogin2').val(), password: $('#inputPassword2').val(), email: $('#inputEmail2').val()};
	jcaUtils.ajaxJOperationAnswered("/user/add", "POST", data, "message", true, ajaxDone, ajaxFail);
	$('#close_button2').click();
}

function ajaxDone(){
	console.log("in registerGood()");
}
function ajaxFail(){
	console.log("in registerFail()");
}