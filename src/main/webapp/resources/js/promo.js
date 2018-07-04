$ = jQuery.noConflict();
$(function ($) {

    getArticles('/promo/getActual','articles_list');
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

	jcaUtils.ajaxJOperationAnswered("/promo/"+$('#delete_id').val(), "DELETE", {}, "message", true, ajaxDone, null);
}

function saveArticle(){

    if (!checkHeader()) return;
    
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

function getArticles(url, id) {

    var data = {archive: false};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (adv) {

            hstring +=
                '<li id=art_'+adv.id+' class = "list-group-item list-group-item-action" style="cursor: pointer;">' +
                    '<div><small>'+adv.header+'</small></div>' +
                    '<div><small>'+adv.company+'</small></div>' +
                    '<button type="button" class="close" data-toggle="modal" data-target="#confirmModal" aria-label="Close" id="del_'+adv.id+'">' +
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
        console.log("GET advertising fail!");
        console.log(event.responseText);
    });
}

function getArticle(li) {
    var strid = $(li).attr("id");
    var id = strid.substring(strid.lastIndexOf("_")+1);

    var getting = $.get('/promo/'+id, {}, 'json');

    getting.done(function (adv) {
        //st = JSON.parse(data);
        $('#art_id').val(id);
        $('#inputHeader').val(adv.header);
        $('#inputCompany').val(adv.company);
        $('#inputDateStart').val(jcaUtils.getCorrectDate(adv.pubStart));
        $('#inputDateFinish').val(jcaUtils.getCorrectDate(adv.pubFinish));
        $('#inputText').val(adv.text);
    });

    getting.fail(function (event) {
        console.log("GET Advertising info fail!");
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

function clearInputs(){
    $('#art_id').val(-1);
    $('#inputHeader').val("");
    $('#inputCreatedDate').val(jcaUtils.getCorrectDate(new Date()));
    $('#opt_1').attr("selected","selected");
    $('#inputDateStart').val("");
    $('#inputDateFinish').val("");
    $('#inputText').val("");
}