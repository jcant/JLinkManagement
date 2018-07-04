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
	data = {login: $('#inputLogin2').val(), password: $('#inputPassword2').val(), email: $('#inputEmail2').val()};
	jcaUtils.ajaxJOperationAnswered("/users/add", "POST", data, "message", true, ajaxDone, ajaxFail);
	$('#close_button2').click();
}

function ajaxDone(){
	console.log("in registerGood()");
}
function ajaxFail(){
	console.log("in registerFail()");
}