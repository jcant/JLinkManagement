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

    data = {id: userId, currentPassword: $("#currentPassword").val()};
    if ($("#userName").val() != '') data.userName = $("#userName").val();
    if ($("#userEmail").val() != '') data.userEmail = $("#userEmail").val();
    if ($("#newPassword").val() != '') data.newPassword = $("#newPassword").val();

    jcaUtils.ajaxJOperationAnswered("/user/update", "POST", data, "message", true, null, null);
    
    setClear(currPswd,true);
    setClear(newPswd,true);
    setClear(confPswd,true);
    
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

