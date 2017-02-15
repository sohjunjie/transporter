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
						    <td>Options here</td></tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		<!-- /.box-body -->
		</div>
	<!-- /.box -->
	</div>
</div>
