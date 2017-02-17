<div id="resolve_accident_modal" class="modal">

	<!-- Modal content -->
	<div class="resolve-modal-content box box-info">
	    <div class="box-header ui-sortable-handle">
	      <i class="fa fa-exclamation-circle"></i>	
	      <h3 class="box-title">Resolve an accident</h3>
	      <!-- tools box -->
	      <div class="pull-right box-tools">
	        <button id="resolve_modal_close" type="button" class="btn btn-info btn-sm close" data-toggle="tooltip" title="Close">
	          <i class="fa fa-times"></i></button>
	      </div>
	      <!-- /. tools -->
	    </div>
	    <div class="box-body">
	        <div id="resolve-accident-feedback" class="alert alert-danger hide">
	            <button class="close message" data-close="alert">&times;</button>
	                <span></span>
	        </div>
	        <div id="accidentToResolve" class="row" style="padding-bottom:15px;">
				<div class="col-lg-4" style="text-align:center;">
					<img id="accidentToResolveImage">
				</div>
				<div class="col-lg-8">
					<span id="accidentToResolveDesc"></span>
				</div>
	        </div>
			<br/>
			<form class="resolve-accident-form" action="" method="post">

			    <input id="accidentReportId" type="hidden" class="form-control" name="accidentReportId" />

				<div class="form-group">
			    	<label>Official cause</label>
			    	<select id="accidentCause" class="form-control" name="accidentCause">
			    		<c:forEach items="${accidentCauses}" var="aCause" varStatus="status">
			    			<option value="${aCause.causeId}" ${status.first ? 'selected' : ''}>${aCause.cause}</option>
			    		</c:forEach>
			    	</select>
			  	</div>

				<div class="form-group">
			    	<label>Number of casualties</label>
			    	<input id="accidentCasualty" type="number" class="form-control" name="accidentCasualty" />
			  	</div>

				<button type="submit" class="pull-right btn btn-info">Submit</button>
			</form>
	    </div>

	</div>
</div>