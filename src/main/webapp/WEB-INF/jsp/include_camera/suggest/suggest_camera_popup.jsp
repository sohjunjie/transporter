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
				    	<label>Accident Location</label>
				    	<input id="accidentLocation" type="text" class="form-control" name="accidentLocation" disabled>
				  	</div>
					<div class="form-group">
				    	<label>Latitude</label>
				    	<input id="accidentLatitude" type="text" class="form-control" name="accidentLatitude" disabled>
				  	</div>
						<div class="form-group">
				    	<label>Longitude</label>
				    	<input id="accidentLongitude" type="text" class="form-control" name="accidentLongitude" disabled>
				  	</div>
					<div class="form-group">
				    	<label>Brief description of accident</label>
				    	<input id="reportAccidentDescription" type="text" class="form-control" name="reportAccidentDescription">
				  	</div>
				  	<div class="form-group">
				    	<label>Accident date and time</label>
				    	<input id="accidentOccuredDatetime" type="text" class="datetimepicker form-control" name="accidentOccuredDatetime">
				  	</div>
				</div>

			    <div class="box-footer clearfix">
			      <button type="submit" class="pull-right btn btn-info">Submit</button>
			    </div>
			</form>
	    </div>

	</div>
</div>