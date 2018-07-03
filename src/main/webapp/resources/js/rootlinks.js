$ = jQuery.noConflict();
var showArc = false;
$(function ($) {

    //$('form button').on("click",function(e){
    //    e.preventDefault();
    //});

    getRootLinks('/rootlinks/all', 'link_list');

    $("#createButton").click(function(){
        createRootLink();
    });

    $('#submit_delete').click(function(){
        deleteRootLink();
    });
});

function deleteRootLink(){

    rl_id = $('#delete_id').val();
    url = "/rootlinks/"+rl_id;

    $.ajax({
        method: "DELETE",
        url: url,
        data: {}
    })
        .done(function(data) {
            getRootLinks('/rootlinks/all','link_list');

            $("#message").html(
                '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                '<div><strong>' + data.message + '</strong></div>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>');

            clearInputs();
        })
        .fail(function(event) {
            $("#message").html(
                '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                '<div><strong>Error!</strong> Some problem with you parameters. RootLink did\'t delete</div>' +
                '<div>response: "'+ JSON.parse(event.responseText)["message"] + '"</div>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>');
            console.log("DELETE RootLink - fail!");
            console.log(event);
        });
}

function createRootLink(){
    if (!checkRootLink()){
        return;
    }

    var url = "/rootlinks/-1";
    var data = {url: $("#rootlinkURL").val(), enabled: true};

    $.ajax({
        method: "POST",
        url: url,
        data: data
    })
        .done(function(data) {
            getRootLinks('/rootlinks/all','link_list');
            $("#rootlinkURL").val("");
            $("#message").html(
                '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                '<div><strong>' + data.message + '</strong></div>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>');
        })
        .fail(function(event) {
            $("#message").html(
                '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                '<div><strong>Error!</strong> Some problem with you parameters. RootLink did\'t create</div>' +
                '<div>response: "'+ JSON.parse(event.responseText)["message"] + '"</div>' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
                '</div>');
            console.log("POST add new RootLink - fail!");
            console.log(event);
        });
}

function checkRootLink(){
    var value = $("#rootlinkURL").val();
    if (value == ""){
        $("#rootlinkURL").removeClass("is-valid");
        $("#rootlinkURL").addClass("is-invalid");
        return false;
    } else {
        $("#rootlinkURL").removeClass("is-invalid");
        $("#rootlinkURL").addClass("is-valid");
        return true;
    }
}


function getRootLinks(url, id) {

    getting = $.get(url, {}, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (link) {

            checked = "";
            if (link.enabled) checked = "checked";

            hstring +=
        '<tr id=link'+link.id+' >' +
            '<td>' +
                '<input type="text" class="form-control col-md-6" id="inputURL_'+link.id+'" value="'+link.url+'">'+
            '</td>' +
            '<td class="align-middle">' +
                '<input type="checkbox" class="form-check-input" id="enabledCheck_' + link.id + '" ' + checked + '>' +
                '<label class="form-check-label" for="enabledCheck_' + link.id + '">Enabled</label>' +
            '</td>' +
            '<td>' +
                '<button type="button" id="'+link.id+'" class="btn btn-success" style="display:none;">Save</button>'+
            '</td>' +
            '<td>' +
                '<button type="button" class="close" data-toggle="modal" data-target="#confirmModal" aria-label="Close" id="del_'+link.id+'">' +
                '<span aria-hidden="true">&times;</span>' +
                '</button>' +
            '</td>' +
        '</tr>';
        });

        $('#' + id).html(hstring);

        $("#link_list").find("input").change(function(){
            showSave(this);
        });

        $("#link_list").find("button.btn-success").click(function(){
            submitRow(this);
        });

        $('td button.close').click(function(){
            strid = $(this).attr("id");
            id = strid.substring(strid.lastIndexOf("_")+1);
            $('#delete_id').val(id);
        });

    });

    getting.fail(function (event) {
        console.log("GET RootLinks fail!");
        console.log(event.responseText);
    });
}

function showSave(input){
    var strid = $(input).attr("id");
    var id = strid.substring(strid.lastIndexOf("_")+1);
    $("#"+id).show();
}

function submitRow(button){
    var id = $(button).attr("id");
    var url = "/rootlinks/"+id;
    var data = {url: $("#inputURL_"+id).val(), enabled: $('#enabledCheck_'+id).is(":checked")};

    $.ajax({
        method: "POST",
        url: url,
        data: data
    })
        .done(function() {
            $(button).hide();
        })
        .fail(function(event) {
            console.log("POST to submit row - fail!");
            console.log(event);
        });
}

function clearInputs(){
    $('#rootlinkURL').val("");
}