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
    	data.append("lat", suggestMarker.position.lat());
    	data.append("lng", suggestMarker.position.lng());
    	data.append("cameraLocation", $("#cameraLocation").val());
    	data.append("cameraInstalledDatetime", $("#cameraInstalledDatetime").val());

		$.ajax({
			type : 'POST',
			url  : pagectx + '/camera/suggest/new',
			data : data,
			dataType: 'text',
			processData: false,
			contentType: false,
			success :  function(response){
				if(response){
					suggestCameraClose.click();
					alert("The camera was suggested.");
				}else{
					$("#suggest-camera-feedback > span").html("A problem was encountered suggesting camera.");
					showAlert($( "#suggest-camera-feedback" ), "danger");
				}
			},
			error : function(e) {
				$("#suggest-camera-feedback > span").html("A problem was encountered suggesting camera.");
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