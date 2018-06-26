$ = jQuery.noConflict();
$(function ($) {

	$('form button').on("click",function(e){
	    e.preventDefault();
	});

		
	$("#buyButton").click(function(){
		$('#confirm').val('confirm');
		$('#confirmBuy').submit();
	});
	
	$("#cancelButton").click(function(){
		$('#confirm').val('cancel');
		$('#confirmBuy').submit();
	});
	
});