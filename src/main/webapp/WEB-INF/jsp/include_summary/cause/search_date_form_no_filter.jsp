<p style="text-decoration:underline;"><font size="+1"><b>Filter accidents</b></font></p>
<font size="+1"><p id="displayfilter">Current filter: archived accidents</p></font>

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
            <input type="submit" value="Filter"/>
        </div>
    </div>
	</form>
</div>

<script>
var startDate=getQueryVariable("startdate");
startDate=toDate(startDate);
var endDate=getQueryVariable("enddate");
endDate=toDate(endDate);
if (startDate !='' && endDate != '') {
	document.getElementById('displayfilter').innerHTML = "Current filter: archived accidents between " + startDate + " and " + endDate;
}

function getQueryVariable(variable){
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}

function toDate(variable) {
	while (variable.search("%2F") != -1)
		variable = variable.replace("%2F","/");
	variable = variable.replace("+", " ");
	variable = variable.replace("%3A",":");
	return variable;
}
</script>