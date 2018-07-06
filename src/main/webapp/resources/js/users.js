$ = jQuery.noConflict();
$(function ($) {

    getUsers('/user/getAdmins','admins_list');
    getUsers('/user/getUsers','users_list');
    clearInputs();

    $('#save_user').click(function(){
        saveUser();
    });


});

function createArticle(){
    clearInputs();
}

function deleteArticle(){

	jcaUtils.ajaxJOperationAnswered("/promo/"+$('#delete_id').val(), "DELETE", {}, "message", true, ajaxDone, null);
}

function saveUser(){

    if (!checkRequirements()) return;
    
    data = {header: $("#inputHeader").val()};
    if ($("#inputText").val() != '') data.text = $("#inputText").val();
    if ($("#inputCompany").val() != '') data.company = $("#inputCompany").val();
    if ($("#inputDateStart").val() != '') data.pubStart = $("#inputDateStart").val();
    if ($("#inputDateFinish").val() != '') data.pubFinish = $("#inputDateFinish").val();
    
    jcaUtils.ajaxJOperationAnswered("/promo/"+$('#art_id').val(), "POST", data, "message", true, ajaxDone, null);
}

function ajaxDone(){
	getArticles('/promo/getActual','articles_list');
	clearInputs();
}

function getUsers(url, id) {

    getting = $.get(url, {}, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (user) {
        	hstring +=
                //'<li id=usr_'+user.id+' class = "list-group-item list-group-item-action '+(user.blocked)?("blocked"):("")+'" style="cursor: pointer;">' +
        		'<li id=usr_'+user.id+' class = "list-group-item list-group-item-action " style="cursor: pointer;">' +
                    '<div><small>'+user.login+'</small></div>' +
                    '<div><small>'+user.email+'</small></div>' +
                '</li>';
        });

        $('#' + id).html(hstring);

        $('li.list-group-item').click(function(){
            $('li.list-group-item').removeClass('active');
            $(this).addClass('active');
            getUser(this);
        });

    });

    getting.fail(function (event) {
        console.log("GET users fail!");
        console.log(event.responseText);
    });
}

function getUser(li) {
    var strid = $(li).attr("id");
    var id = strid.substring(strid.lastIndexOf("_")+1);

    var getting = $.get('/user/'+id, {}, 'json');

    getting.done(function (user) {
        $('#userName').val(user.name);
        $('#userLogin').val(user.login);
        $('#userEmail').val(user.email);
        if (user.blocked) $('#blockUser').attr("checked", "checked");
        if (user.resetPassword) $('#resetPassword').attr("checked", "checked");
    });

    getting.fail(function (event) {
        console.log("GET user info info fail!");
        console.log(event.responseText);
    });
}

function checkRequirements(){
    var value = $("#adminPass").val();
    if (value == ""){
    	jcaUtils.setInvalid("adminPass");
        return false;
    } else {
    	jcaUtils.setValid("adminPass");
        return true;
    }
}

function clearInputs(){
	jcaUtils.clearValues(['userName', 'userLogin', 'userEmail'], 'val');
	$('#blockUser').removeAttr("checked");
	$('#resetPassword').removeAttr("checked");
}