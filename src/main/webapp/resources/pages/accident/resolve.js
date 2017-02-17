$(document).ready(function() {
	setHideAlert($("#resolve-accident-feedback"));
	event_resolveAccidentAjax();
	event_resolveAccidentValidate();
});

function event_resolveAccidentAjax(){
	$(".resolve-accident-form").on('submit', function(event){

		event.preventDefault();
		setHideAlert($("#resolve-accident-feedback"));
		var isvalid = $( this ).valid();

		if (!isvalid) { return; }
		var confirmApprove = confirm("Confirm resolve accident report?");
		if (!confirmApprove) { return; }

    	var data = {};
    	data['reportId'] = $('#accidentReportId').val();
    	data['causeId'] = $("#accidentCause").val();
    	data['numOfCasualties'] = $("#accidentCasualty").val();

		$.ajax({
			type : 'POST',
			url  : pagectx + '/accident/report/resolve',
			data : data,
			success :  function(response){
				if(response){
					resolveAccidentClose.click();
					alert("The accident was successfully resolved.");
					location.reload();
				}else{
					$("#resolve-accident-feedback > span").html("A problem was encountered resolving the accident report.");
					showAlert($( "#report-accident-feedback" ), "danger");
				}
			},
			error : function(e) {
				$("#resolve-accident-feedback > span").html("A problem was encountered resolving the accident report.");
				showAlert($( "#report-accident-feedback" ), "danger");
			},
			done : function(e) {}
		});

	});
}

function event_resolveAccidentValidate(){
    $(".resolve-accident-form").validate({
        errorElement: "span",
        errorClass: "help-block",
        focusInvalid: !1,
        rules: {
        	accidentCause: "required"
        },
        messages: {
        	accidentCause: "Please select cause of accident from the options"
        },
        errorPlacement: function(e, r) {
            e.insertBefore(r)
        }
    });
}