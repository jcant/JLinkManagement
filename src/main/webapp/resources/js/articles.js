$ = jQuery.noConflict();
$(function ($) {

	getArticles('/articles/getActual','articles_list');
	getArticles('/promo/getActual','advertising_list');

});

function getArticles(url, id) {

	var data = {archive: false};
    var getting = $.get(url, data, 'json');

    getting.done(function (data) {
        var hstring = "";
        data.forEach(function (article) {

       	hstring +=
       		'<li id=link_'+article.id+' class = "list-group-item list-group-item-action" style="cursor: pointer;">' +
       			'<small>'+article.header+'</small>' +
       		'</li>';
        });

        $('#' + id).html(hstring);
        
        $('li.list-group-item').click(function(){
        	$('li.list-group-item').removeClass('active');
        	$(this).addClass('active');
        	//getLinkStats(this);
        });

    });
    
    getting.fail(function (event) {
    	console.log("GET Links fail!");
    	console.log(event.responseText);
    });
}

function getLinkStats(li) {
	var strid = $(li).attr("id");
	var id = strid.substring(strid.lastIndexOf("_")+1);
	
	var getting = $.get('/stats/'+id, {}, 'json');
	
	getting.done(function (st) {
		//st = JSON.parse(data);
		$('#AllValue').html(st.allCnt);
		$('#AllPerDay').html(st.allPerDay);
		
		$('#DayValue').html(st.dayCnt);
		$('#DayPerHour').html(st.dayPerHour);
		
		$('#WeekValue').html(st.weekCnt);
		$('#WeekPerDay').html(st.weekPerDay);
		
		$('#MonthValue').html(st.monthCnt);
		$('#MonthPerDay').html(st.monthPerDay);
		
		$('#YearValue').html(st.yearCnt);
		$('#YearPerDay').html(st.yearPerDay);
		
		$('#DayRemains').html('<div class="btn btn-warning" >Days left: <span class="badge badge-light">'+st.daysLeft+'</span>');

    });
    
    getting.fail(function (event) {
    	console.log("GET Stats info fail!");
    	console.log(event.responseText);
    });
}
