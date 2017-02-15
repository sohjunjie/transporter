<script>
	var pendingAccidents = "${pendingAccidents}";
</script>
<div class="row">
	<div class="col-xs-12">
	    <div class="box">
			<div id="accident_pending_viewer_table" class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<tbody>
					<c:forEach items="${pendingAccidents}" var="pAccident">
						<tr>
						    <td><img src="${resourcePath}${pAccident.imageLink}" class="accident_img"></td>
						    <td><b>${pAccident.formattedAddress}</b><br/>
						    	${pAccident.description}<br/>
						    	${pAccident.accidentDateTime}
						    </td>
						    <td><div class="itemOptions">
									<button value="${pAccident.reportId}" type="button" class="close pAccidentMapLoc">
	          							<i class="fa fa-map-marker"></i></button>
									<button value="${pAccident.reportId}" type="button" class="close pAccidentApprove">
	          							<i class="fa fa-check"></i></button>
									<button value="${pAccident.reportId}" type="button" class="close pAccidentDelete">
	          							<i class="fa fa-times"></i></button>
						    	</div>
							</td></tr>
					</c:forEach>
					<c:choose>
						<c:when test="${fn:length(pendingAccidents) <= 0}">
							<div class="emptyTableMessage" style="height: 500px;">
								<div class="msgContent">
									<i class="fa fa-smile-o"></i><br/>
									<span>You have no pending accidents to approved.</span>
								</div>
							</div>
						</c:when>
					</c:choose>
					</tbody>
				</table>
			</div>
		<!-- /.box-body -->
		</div>
	<!-- /.box -->
	</div>
</div>
