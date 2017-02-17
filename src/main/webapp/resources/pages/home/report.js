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
        	data.append("accidentLocation", $("#accidentLocation").val());
        	data.append("accidentDescription", $("#reportAccidentDescription").val());
        	data.append("accidentDateTime", $("#accidentOccuredDatetime").val());
        	data.append("accidentImage", $('#accidentImage')[0].files[0]);

			$.ajax({
				type : 'POST',
				url  : pagectx + '/accident/report/new',
				data : data,
				dataType: 'text',
				processData: false,
				contentType: false,
				success :  function(response){
					if(response){
						$('.imageupload').imageupload('reset');
						$("#reportAccidentDescription").val("");
						reportAccidentClose.click();
						alert("The accident was successfully reported. Thank you for your contribution.");
					}else{
						$("#report-accident-feedback > span").html("A problem was encountered sending an accident report.");
						showAlert($( "#report-accident-feedback" ), "danger");
					}
				},
				error : function(e) {
					$("#report-accident-feedback > span").html("A problem was encountered sending an accident report.");
					showAlert($( "#report-accident-feedback" ), "danger");
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
        	reportAccidentDescription: "required",
        	accidentOccuredDatetime: "required",
        	accidentImage: "required"
        },
        messages: {
        	reportAccidentDescription: "Please give a description of the accident",
        	accidentOccuredDatetime: "Please enter the datetime of the accident",
        	accidentOccuredDatetime: "Please upload an image of the accident"
        },
        errorPlacement: function(e, r) {
            e.insertBefore(r)
        }
    });
}