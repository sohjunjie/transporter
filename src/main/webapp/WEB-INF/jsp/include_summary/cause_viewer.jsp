<script>
	var pendingAccidents = "${pendingAccidents}";
</script>
<div class="row">
	<div class="col-xs-12">
	    <div class="box">
			<div id="accident_approved_viewer_table" class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<tbody>
					<c:forEach items="${approvedAccidents}" var="aAccident">
						<tr>
						    <td><b>${aAccident.formattedAddress}</b><br/>
						    	${aAccident.description}<br/>
						    	${aAccident.accidentDateTime}
						    </td>
						    <td><div class="itemOptions">
									<button value="${aAccident.reportId}" type="button" class="close aAccidentMapLoc">
	          							<i class="fa fa-map-marker"></i></button>
									<button value="${aAccident.reportId}" type="button" class="close aAccidentResolve">
	          							<i class="fa fa-check"></i></button>
						    	</div>
							</td></tr>
					</c:forEach>
					
					</tbody>
				</table>
			</div>
		<!-- /.box-body -->
		</div>
	<!-- /.box -->
	</div>
</div>

<!-- The Modal -->
<div id="imageModal" class="image-modal">
	<span class="imageModalClose" onclick="document.getElementsByClassName('imageModal').style.display='none'">&times;</span>
	<img class="modal-content" id="modalImage">
</div>

