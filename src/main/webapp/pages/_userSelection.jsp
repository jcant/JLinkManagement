<c:if test="${admin eq true}">
	<form>
		<div class="form-group align-self-end col-md-8">
			<select id="select_users" class="form-control">
				<option selected>Problems with connection to REST service?</option>
			</select>
		</div>
	</form>

	<script type="application/javascript">
		
		$ = jQuery.noConflict();
		$(function ($) {
			$('#header_username').html(uname);
			getUsers('select_users');
					
			$('#select_users').change(function(){			
				changeSelectedUserImpl(this);
			});
		});
				
				
		function getUsers(id) {
				    
			getting = $.get('/users/all', {}, 'json');

		    getting.done(function (data) {
		        var hstring = "";
		        data.forEach(function (user) {        
		            hstring += 
		            '<option>'+
		            	user.login+
		            '</option>';
		        });

		        $('#' + id).html(hstring);
		    	
		    });
				    
		    getting.fail(function (event) {
		    	console.log("GET users fail!");
		    	console.log(event.responseText);
		    });
		}
				
	</script>

</c:if>