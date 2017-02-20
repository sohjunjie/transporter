<!-- TODO: Refine suggest camera popup form -->
<div id="suggest_camera_modal" class="modal">

	<!-- Modal content -->
	<div class="camera-modal-content box box-info">
	    <div class="box-header ui-sortable-handle">
	      <i class="fa fa-video-camera"></i>
	      <h3 class="box-title">Suggest Camera</h3>
	      <!-- tools box -->
	      <div class="pull-right box-tools">
	        <button id="suggest_camera_close" type="button" class="btn btn-info btn-sm close" data-toggle="tooltip" title="Close">
	          <i class="fa fa-times"></i></button>
	      </div>
	      <!-- /. tools -->
	    </div>
	    <div class="box-body">
	        <div id="suggest-camera-feedback" class="alert alert-danger hide">
	            <button class="close message" data-close="alert">&times;</button>
	                <span></span>
	        </div>
			<form class="suggest-camera-form" action="" method="post">
				<div>
					<div class="form-group">
				    	<label>Camera Location</label>
				    	<input id="cameraLocation" type="text" class="form-control" name="cameraLocation" disabled>
				  	</div>
					<div class="form-group">
				    	<label>Latitude</label>
				    	<input id="cameraLatitude" type="text" class="form-control" name="cameraLatitude" disabled>
				  	</div>
						<div class="form-group">
				    	<label>Longitude</label>
				    	<input id="cameraLongitude" type="text" class="form-control" name="cameraLongitude" disabled>
				  	</div>

				  	<div class="form-group">
				    	<label>Installed date and time</label>
				    	<input id="cameraInstalledDatetime" type="text" class="datetimepicker form-control" name="cameraInstalledDatetime">
				  	</div>
				</div>

			    <div class="box-footer clearfix">
			      <button type="submit" class="pull-right btn btn-info">Submit</button>
			    </div>
			</form>
	    </div>

	</div>
</div>