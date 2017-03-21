<%@ taglib uri="http://java.sun.com/jsp/jstl/core"		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="row">
	<div class="col-xs-12">
	    <div class="box">
			<div id="camera_viewer_table" class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<tbody>
					<c:forEach items="${enforcementCamera}" var="camera">
						<tr>
						    <td><b>${camera.formattedAddress}</b><br/>
						    	${camera.type}<br/>
						    	${camera.status}
						    </td>
						    <td><div class="itemOptions">
									<button value="${camera.cameraId}" type="button" class="close cameraMapLoc" data-toggle="tooltip" title="Zoom to location">
	          							<i class="fa fa-map-marker"></i></button>
									<button value="${camera.cameraId}" type="button" class="close cameraSetInstalled" data-toggle="tooltip" title="Set camera to installed!">
	          							<i class="fa fa-check"></i></button>
									<button value="${camera.cameraId}" type="button" class="close cameraDelete" data-toggle="tooltip" title="Remove camera">
	          							<i class="fa fa-times"></i></button>
						    	</div>
							</td></tr>
					</c:forEach>
					<c:choose>
						<c:when test="${fn:length(enforcementCamera) <= 0}">
							<div class="emptyTableMessage" style="height: 500px;">
								<div class="msgContent">
									<i class="fa fa-video-camera"></i><br/>
									<span>You do not have existing cameras.</span>
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