<p style="text-decoration:underline;"><font size="+1"><b>Filter accidents</b></font></p>
<font size="+1"><p id="displayfilter">Current filter: all accidents</p></font>
<div class="container">
<form method="get" enctype="multipart/form-data">
    <div class="row">
        <div class='col-sm-3'>
            <div class="form-group" style="position: relative;">
					    <label>Start Date</label>
					    <input type="text" class="datetimepicker form-control" name="startdate" id="startdateinput">
				</div>
            <div class="form-group" style="position: relative;">
					    <label>End Date</label>
					    <input type="text" class="datetimepicker form-control" name="enddate" id="enddateinput">
				</div>
            	<label>	<input type="radio" name="searchoption" value="both" checked> Current & Archived Accidents</label><br>
  		<label> <input type="radio" name="searchoption" value="archived"> Archived Accidents</label><br>
  		<label>	<input type="radio" name="searchoption" value="current"> Current Accidents</label><br>
            <input type="submit" value="Filter"/>
            <br>
        </div>
    </div>
	</form>
</div>

<script>
var startDate=getQueryVariable("startdate");
startDate=toDate(startDate);
var endDate=getQueryVariable("enddate");
endDate=toDate(endDate);
var searchOption=getQueryVariable("searchoption");
var formattedStartDate = new Date(startDate.substring(6,10),startDate.substring(3,5),startDate.substring(0,2),
		startDate.substring(11,13),startDate.substring(14,16));
var formattedEndDate = new Date(endDate.substring(6,10),endDate.substring(3,5),endDate.substring(0,2),
		endDate.substring(11,13),endDate.substring(14,16));
if (searchOption == "both")
	searchOption = "all";
if (startDate== '' || endDate == '' || formattedStartDate.getTime() > formattedEndDate.getTime()) {
    document.getElementById('displayfilter').innerHTML = "Current filter: " + searchOption + " accidents";
} 
else {
        document.getElementById('displayfilter').innerHTML = "Current filter: " + searchOption + " accidents between " + startDate + " and " + endDate;
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
