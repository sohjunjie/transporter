$(document).ready(function() {
	setHideAlert($("#login-feedback"));
    event_loginFormAjax();
    event_loginFormValidate();
});

var reportAccidentModal = document.getElementById('report_accident_modal');
var reportAccidentBtn = document.getElementById("report_accident_btn");
var reportAccidentClose = document.getElementById("report_modal_close");

if(reportAccidentModal != null){
	reportAccidentBtn.onclick = function() {
		$('.datetimepicker').val(moment().format("DD/MM/YYYY HH:mm"))
		reportAccidentModal.style.display = "block";
	}
	reportAccidentClose.onclick = function() {
		reportAccidentModal.style.display = "none";
	}
	window.onclick = function(event) {
	    if (event.target == reportAccidentModal) {
	    	reportAccidentModal.style.display = "none";
	    }
	}
}
