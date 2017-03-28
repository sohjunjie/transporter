<p style="text-decoration:underline;"><font size="+1"><b>Filter accidents</b></font></p>
<div class="container">
<form method="get" enctype="multipart/form-data">
    <div class="row">
        <div class='col-sm-3'>
            <div class="form-group" style="position: relative;">
					    <label>Start Date</label>
					    <input type="text" class="datetimepicker form-control" name="startdate">
				</div>
            <div class="form-group" style="position: relative;">
					    <label>End Date</label>
					    <input type="text" class="datetimepicker form-control" name="enddate">
				</div>
            	<label>	<input type="radio" name="searchoption" value="both" checked> Current & Archived Accidents</label><br>
  		<label> <input type="radio" name="searchoption" value="archived"> Archived Accidents</label><br>
  		<label>	<input type="radio" name="searchoption" value="current"> Current Accidents</label><br>
            <input type="submit" value="Submit"/>
            <br>
        </div>
    </div>
	</form>
</div>
