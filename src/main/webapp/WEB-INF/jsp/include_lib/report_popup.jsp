<div id="report_accident_modal" class="modal">

	<!-- Modal content -->
	<div class="report-modal-content box box-info">
	    <div class="box-header ui-sortable-handle">
	      <i class="fa fa-exclamation-circle"></i>	
	      <h3 class="box-title">Report an accident</h3>
	      <!-- tools box -->
	      <div class="pull-right box-tools">
	        <button id="report_modal_close" type="button" class="btn btn-info btn-sm close" data-toggle="tooltip" title="Close">
	          <i class="fa fa-times"></i></button>
	      </div>
	      <!-- /. tools -->
	    </div>
	    <div class="box-body">
	        <div id="report-accident-feedback" class="alert alert-danger hide">
	            <button class="close message" data-close="alert">&times;</button>
	                <span></span>
	        </div>
			<form class="report-accident-form" action="" method="post">
				<div class="form-group">
			    	<label>Cause of accident</label>
			    	<input id="reportAccidentCause" type="text" class="form-control" name="reportAccidentCause">
			  	</div>
			  	<div class="form-group">
			    	<label>Accident date and time</label>
			    	<input id="accidentOccuredDatetime" type="text" class="datetimepicker form-control" name="accidentOccuredDatetime">
			  	</div>
			    <div class="box-footer clearfix">
			      <button type="submit" class="pull-right btn btn-info">OK</button>
			    </div>
			</form>
	    </div>

	</div>
</div>