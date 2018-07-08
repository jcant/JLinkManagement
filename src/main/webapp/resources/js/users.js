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
    
    data = {currentPassword: $("#adminPass").val(), id: $("#userId").val()};
    if ($("#userName").val() != '') data.userName = $("#userName").val();
    if ($("#userEmail").val() != '') data.userEmail = $("#userEmail").val();
    data.resetPassword = $("#resetPassword").is(":checked");
    data.blockUser = $("#blockUser").is(":checked");
    data.userRole = $("#userRole").val();
    
    jcaUtils.ajaxJOperationAnswered("/user/update", "POST", data, "message", true, ajaxDone, null);
}

function ajaxDone(){
    $('#adminPass').val("");
    jcaUtils.setNeutral('adminPass');
}

function getUsers(url, id) {

    getting = $.get(url, {}, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (user) {
        	hstring +=
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
    clearInputs();
    var strid = $(li).attr("id");
    var id = strid.substring(strid.lastIndexOf("_")+1);

    var getting = $.get('/user/'+id, {}, 'json');

    getting.done(function (user) {
        $('#userId').val(user.id);
        $('#userName').val(user.name);
        $('#userLogin').val(user.login);
        $('#userEmail').val(user.email);

        if (user.blocked) $('#blockUser').prop("checked", true);
        else $('#blockUser').prop("checked", false);

        if (user.resetPassword) $('#resetPassword').prop("checked", true);
        else $('#resetPassword').prop("checked", false);

        if (user.role == "ADMIN") $('#userRole').val(0);
        if (user.role == "USER") $('#userRole').val(1);
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
	jcaUtils.clearValues(['adminPass', 'userName', 'userLogin', 'userEmail'], 'val');
    $('#blockUser').prop("checked", false);
    $('#resetPassword').prop("checked", false);
}