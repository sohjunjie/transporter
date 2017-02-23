<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	var hrsOfDay = "${hrsOfDay}";
	var hrAccidentCount = "${hrAccidentCount}";
</script>
<div class="row">
	<div class="col-xs-12">
	    <div class="box">
			<div id="summary_time_viewer_table" class="box-body table-responsive no-padding">
				<table class="table table-hover">
				<thead>
				<tr>
					<th>Time Of Day</th>
					<th>Number of Accidents</th>
				</tr>
				</thead>
					<tbody>
					<c:forEach items="${hrsOfDay}" var="hr" varStatus="status">
						<tr>
						    <td><b>${hr}:00 to ${hr}:59</b></td>
							 <td><b>${hrAccidentCount[status.index]}</b></td>
							 </tr>
							 </c:forEach>
					</tbody>
				</table>
			</div>
		<!-- /.box-body -->
		</div>
	<!-- /.box -->
	</div>
</div>

<form method="get" enctype="multipart/form-data">
    <table>
    <tr>
        <td>Start Date</td>
        <td><input type="date" name="startdate" class="datetimepicker form-control" size="20"></td>
    </tr>
    <tr>
        <td>End Date</td>
        <td><input type="date" name="enddate" class="datetimepicker form-control" size="20"></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="send"/>
        </td>
    </tr>
</table>
</form>

<!-- The Modal -->
<div id="imageModal" class="image-modal">
	<span class="imageModalClose" onclick="document.getElementsByClassName('imageModal').style.display='none'">&times;</span>
	<img class="modal-content" id="modalImage">
</div>

