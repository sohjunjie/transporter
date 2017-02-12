$(document).ready(function() {
	setHideAlert($("#report-accident-feedback"));
	event_reportAccidentAjax();
	event_reportAccidentValidate();
});

function event_reportAccidentAjax(){
	$(".report-accident-form").on('submit', function(event){

	    event.preventDefault();
		setHideAlert($("#report-accident-feedback"));
        var isvalidate = $( this ).valid();

        if(isvalidate){

        	var data = new FormData();
        	data.append("lat", marker.position.lat());
        	data.append("lng", marker.position.lng());
        	data.append("lng", marker.position.lng());
        	data.append("accidentCause", $("#reportAccidentCause").val());
        	data.append("accidentDateTime", $("#accidentOccuredDatetime").val());
        	data.append("accidentImage", $('#accidentImage')[0].files[0]);

			$.ajax({
				type : 'POST',
				url  : 'accident/report/new',
				data : data,
				dataType: 'text',
				processData: false,
				contentType: false,
				success :  function(response){
					console.log(response);
					if(response){
						reportAccidentClose.click();
						alert("Thank you. The accident was successfully reported.")
					}else{
						$("#report-accident-feedback > span").html("A problem was encountered sending an accident report.");
						showAlert($( "#report-accident-feedback" ), "danger");
					}
				},
				error : function(e) {
					$("#report-accident-feedback > span").html("A problem was encountered sending an accident report.");
					showAlert($( "#report-accident-feedback" ), "danger");
					console.log("ERROR: ", e);
				},
				done : function(e) {}
			});
        }
	});
}

function event_reportAccidentValidate(){
    $(".report-accident-form").validate({
        errorElement: "span",
        errorClass: "help-block",
        focusInvalid: !1,
        rules: {
        	reportAccidentCause: "required",
        	accidentOccuredDatetime: "required"
        },
        messages: {
        	reportAccidentCause: "Please select cause of accident from the options",
        	accidentOccuredDatetime: "Please enter the datetime of the accident"
        },
        errorPlacement: function(e, r) {
            e.insertBefore(r)
        }
    });
}