<!-- Modal Confirm Form -->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmTitle">Delete confirmation</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container">
						Are you sure to delete?  
					</div>
					<input type="hidden" id="delete_id">
					<button type="button" class="btn btn-danger" data-dismiss="modal" id="submit_delete">YES</button>
					<button type="button" class="btn btn-success" data-dismiss="modal">NO</button>
				</div>
			</div>
		</div>
	</div>