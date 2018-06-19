$ = jQuery.noConflict();
$(function ($) {

	getLinks('/links/'+uname,'link_list');
});

function getLinks(url, id) {
    var data = {};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var date = new Date();
        //var dateEnd = new Date();
    	var hstring = '<ul class="list-group list-group-flush">';
        data.forEach(function (link) {
            date.setTime(link.startDate);
            var startString = "" + date.getDate()+"."+(date.getMonth()+1)+"."+date.getFullYear();
            date.setTime(link.endDate);
            var endString = "" + date.getDate()+"."+(date.getMonth()+1)+"."+date.getFullYear();
        	hstring += '<li class="list-group-item row">'+
            '<span class="col" title="'+link.url+'">'+link.url+'</span>'+
            '<span>&nbsp</span>'+
            '<span class="col title="'+link.target+'">'+link.target+'</span>'+
            '<span>&nbsp</span>'+
            '<span class="col">'+startString+'</span>'+
            '<span>&nbsp</span>'+
            '<span class="col">'+endString+'</span>'+
            '</li>'; 
        });
        hstring += '</ul>';
        $('#' + id).html(hstring);
    });
    getting.fail(function (event) {
        console.log(event.responseText);
    });
}
