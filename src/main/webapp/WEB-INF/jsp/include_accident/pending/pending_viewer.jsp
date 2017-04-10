<%@ taglib uri="http://java.sun.com/jsp/jstl/core"		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
						<tr id="accident_row_${pAccident.reportId}">
						    <td><img src="${resourcePath}${pAccident.imageLink}" class="accident_img imageEnlarge"></td>
						    <td><b>${pAccident.formattedAddress}</b><br/>
						    	${pAccident.description}<br/>
						    	${pAccident.accidentDateTime}</br>
						    	PENDING
						    </td>
						    <td><div class="itemOptions">
									<button value="${pAccident.reportId}" type="button" class="close pAccidentMapLoc" data-toggle="tooltip" title="Zoom to location">
	          							<i class="fa fa-map-marker"></i></button>
									<button value="${pAccident.reportId}" type="button" class="close pAccidentApprove" data-toggle="tooltip" title="Approve accident">
	          							<i class="fa fa-check"></i></button>
									<button value="${pAccident.reportId}" type="button" class="close pAccidentDelete" data-toggle="tooltip" title="Reject accident">
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
						<c:when test="${fn:length(pendingAccidents) > 0}">
							<c:forEach items="${unresolvedAccidents}" var="uAccident">
								<tr id="accident_row_${uAccident.reportId}">
								    <td><img src="${resourcePath}${uAccident.imageLink}" class="accident_img imageEnlarge"></td>
								    <td><b>${uAccident.formattedAddress}</b><br/>
								    	${uAccident.description}<br/>
								    	${uAccident.accidentDateTime}<br/>
								    	APPROVED
								    </td>
								    <td><div class="itemOptions"></div>
									</td></tr>
							</c:forEach>
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

<!-- The Modal -->
<div id="imageModal" class="image-modal">
	<span class="imageModalClose" onclick="document.getElementsByClassName('imageModal').style.display='none'">&times;</span>
	<img class="modal-content" id="modalImage">
</div>

