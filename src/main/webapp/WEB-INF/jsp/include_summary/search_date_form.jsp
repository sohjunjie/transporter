<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>

<link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css" rel="stylesheet"/>

<p style="text-decoration:underline;"><font size="+1"><b>Filter accidents</b></font></p>
<div class="container">
<form method="get" enctype="multipart/form-data">
    <div class="row">
        <div class='col-sm-3'>
            <div class="form-group">
                Start Date: <div class='input-group date' id='datetimepicker1'>
    				<input type='text' class="form-control" name="startdate" placeholder="MM/DD/YYYY" size="10"/>
                    	<span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
            <div class="form-group">
                End Date: <div class='input-group date' id='datetimepicker2'>
    				<input type='text' class="form-control" name="enddate" placeholder="MM/DD/YYYY" size="10"/>
                    	<span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
            <input type="radio" name="searchoption" value="both" checked> Current & Archived Accidents<br>
  			<input type="radio" name="searchoption" value="archived"> Archived Accidents<br>
  			<input type="radio" name="searchoption" value="current"> Current Accidents<br>
            <input type="submit" value="Submit"/>
            <br>
        </div>
        
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker2').datetimepicker();
            });
        </script>
    </div>
	</form>
</div>