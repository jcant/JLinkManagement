$ = jQuery.noConflict();
var showArc = false;
$(function ($) {

    $('form button').on("click",function(e){
        e.preventDefault();
    });

    $('#saveButton').click(function(){
        saveUserInfo();
    });


});

function saveUserInfo(){
    currPswd = $('#currentPassword');
    newPswd = $('#newPassword');
    confPswd = $('#confirmNewPassword');

    if(isEmpty(currPswd)){
        setWrong(currPswd);
        return;
    } else {
        setRight(currPswd);
    }

    if (newPswd.val() != confPswd.val()){
        setWrong(newPswd);
        setWrong(confPswd);
        return;
    } else {
        if(!isEmpty(confPswd) && !isEmpty(newPswd)) {
            setRight(newPswd);
            setRight(confPswd);
        } else {
            setClear(newPswd);
            setClear(confPswd);
        }
    }

    data = {currentPassword: $("#currentPassword").val()};
    if ($("#userName").val() != '') data.userName = $("#userName").val();
    if ($("#userEmail").val() != '') data.userEmail = $("#userEmail").val();
    if ($("#newPassword").val() != '') data.newPassword = $("#newPassword").val();

    var posting = $.post('/users/'+userId, data, 'json');

    posting.done(function (data) {
        hstring = "";
        if (data.success){
            hstring +=
            '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                '<strong>' + data.message + '</strong>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span>' +
                '</button>' +
            '</div>';
        } else {
            hstring +=
            '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                '<strong>' + data.message + '</strong>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span>' +
                '</button>' +
            '</div>';
        }

        $('#message').html(hstring);

        setClear(currPswd,true);
        setClear(newPswd,true);
        setClear(confPswd,true);

    });

    posting.fail(function (event) {
        hstring +=
            '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                '<strong>Update request failed!</strong>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span>' +
                '</button>' +
            '</div>';
        $('#message').html(hstring);

        setClear(currPswd,true);
        setClear(newPswd,true);
        setClear(confPswd,true);

        console.log("Update user failed!");
        console.log(event.responseText);
    });
}

function isEmpty(element){
    if (element.val()=="") {
        return true;
    } else{
        return false;
    }
}

function setWrong(element){
    element.removeClass('is-valid');
    element.addClass('is-invalid');
}
function setRight(element){
    element.removeClass('is-invalid');
    element.addClass('is-valid');
}
function setClear(element, valueToo){
    element.removeClass('is-invalid');
    element.removeClass('is-valid');

    if (valueToo==true) element.val('');
}

