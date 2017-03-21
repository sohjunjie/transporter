<%@ taglib uri="http://java.sun.com/jsp/jstl/core"		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
						    <td><img src="${resourcePath}${aAccident.imageLink}" class="accident_img imageEnlarge"></td>
						    <td><b>${aAccident.formattedAddress}</b><br/>
						    	${aAccident.description}<br/>
						    	${aAccident.accidentDateTime}
						    </td>
						    <td><div class="itemOptions">
									<button value="${aAccident.reportId}" type="button" class="close aAccidentMapLoc" data-toggle="tooltip" title="Zoom to location">
	          							<i class="fa fa-map-marker"></i></button>
									<button value="${aAccident.reportId}" type="button" class="close aAccidentResolve" data-toggle="tooltip" title="Resolve accident">
	          							<i class="fa fa-check"></i></button>
						    	</div>
							</td></tr>
					</c:forEach>
					<c:choose>
						<c:when test="${fn:length(approvedAccidents) <= 0}">
							<div class="emptyTableMessage" style="height: 500px;">
								<div class="msgContent">
									<i class="fa fa-smile-o"></i><br/>
									<span>You have no approved accidents to resolve.</span>
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

<!-- The Modal -->
<div id="imageModal" class="image-modal">
	<span class="imageModalClose" onclick="document.getElementsByClassName('imageModal').style.display='none'">&times;</span>
	<img class="modal-content" id="modalImage">
</div>

