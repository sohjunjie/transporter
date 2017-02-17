$(document).ready(function() {
	setDateTimePicker();
	setImageUploadUI();
	event_PendingReportCountUpdater();
	event_ApprovedReportCountUpdater();
//	window.setInterval(function(){event_PendingReportCountUpdater()}, 60000);
});

// GLOBAL ALERT HIDE
function setHideAlert(AlertObject){
	AlertObject.children('button').click(function(event){
        AlertObject.addClass('hide');
    });
}

// GLOBAL ALERT SHOW
function showAlert(AlertObject, AlertType){
    var classList = AlertObject.attr("class").split(/\s+/);
    for (var i = 0; i < classList.length; i++) {
        if (classList[i].startsWith("alert-")) {
            AlertObject.removeClass(classList[i]);
        }
    }
    AlertObject.addClass('alert-' + AlertType);
    AlertObject.removeAttr('style');
    AlertObject.removeClass('hide');
}

// GLOBAL DATETIMEPICKER UI
function setDateTimePicker(){
	$('.datetimepicker').datetimepicker({
		useCurrent: true,
		format: 'DD/MM/YYYY HH:mm'
	});
}

// GLOBAL IMAGEUPLOAD UI
function setImageUploadUI(){
	$('.imageupload').imageupload();
}

function event_PendingReportCountUpdater(){	
	$.ajax({
		type : 'GET',
		url  : pagectx + '/accident/report/pending/count',
		success :  function(response){
			if(parseInt(response) > 0){
				$('#pending_report_count_badge').text(response);
			}else{
				$('#pending_report_count_badge').text("");
			}
		}
	});
}

function event_ApprovedReportCountUpdater(){	
	$.ajax({
		type : 'GET',
		url  : pagectx + '/accident/report/approved/count',
		success :  function(response){
			if(parseInt(response) > 0){
				$('#approved_report_count_badge').text(response);
			}else{
				$('#approved_report_count_badge').text("");
			}
		}
	});
}