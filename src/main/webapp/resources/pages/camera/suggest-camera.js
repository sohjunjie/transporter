$(document).ready(function() {
	setHideAlert($("#suggest-camera-feedback"));
	event_reportAccidentAjax();
	event_reportAccidentValidate();
});

function event_reportAccidentAjax(){
	$(".suggest-camera-form").on('submit', function(event){

	    event.preventDefault();
		setHideAlert($("#suggest-camera-feedback"));
        var isvalidate = $( this ).valid();
        if(isvalidate){ return; }

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
					$("#suggest-camera-feedback > span").html("A problem was encountered sending an accident report.");
					showAlert($( "#report-accident-feedback" ), "danger");
				}
			},
			error : function(e) {
				$("#suggest-camera-feedback > span").html("A problem was encountered sending an accident report.");
				showAlert($( "#suggest-camera-feedback" ), "danger");
			},
			done : function(e) {}
		});

	});
}

function event_reportAccidentValidate(){
    $(".suggest-camera-form").validate({
        errorElement: "span",
        errorClass: "help-block",
        focusInvalid: !1,
        rules: {
        	reportAccidentDescription: "required",
        	accidentOccuredDatetime: "required"
        },
        messages: {
        	reportAccidentDescription: "Please give a description of the accident",
        	accidentOccuredDatetime: "Please enter the datetime of the accident"
        },
        errorPlacement: function(e, r) {
            e.insertBefore(r)
        }
    });
}