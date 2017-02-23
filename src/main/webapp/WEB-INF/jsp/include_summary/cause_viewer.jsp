<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	var accidentCauses = "${accidentCauses}";
	var causeCount = "${causeCount}";
</script>
<div class="row">
	<div class="col-xs-12">
	    <div class="box">
			<div id="summary_cause_viewer_table" class="box-body table-responsive no-padding">
				<table class="table table-hover">
				<thead>
				<tr>
					<th>Cause</th>
					<th>Number of Accidents</th>
				</tr>
				</thead>
					<tbody>
					<c:forEach items="${accidentCauses}" var="aCause" varStatus="status">
						<tr>
						    <td><b>${aCause.cause}</b></td>
							 <td><b>${causeCount[status.index]}</b></td>
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

