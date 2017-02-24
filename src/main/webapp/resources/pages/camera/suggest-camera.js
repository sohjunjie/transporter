$(document).ready(function() {
	setHideAlert($("#suggest-camera-feedback"));
	event_suggestCameraAjax();
	event_suggestCameraValidate();
});

function event_suggestCameraAjax(){
	$(".suggest-camera-form").on('submit', function(event){

	    event.preventDefault();
		setHideAlert($("#suggest-camera-feedback"));
        var isvalid = $( this ).valid();
        if(!isvalid){ return; }

    	var data = new FormData();
    	data.append("lat", suggestMarker.position.lat());
    	data.append("lng", suggestMarker.position.lng());
    	data.append("cameraLocation", $("#cameraLocation").val());
    	data.append("cameraTypeOrdinal", $("#cameraType").val());

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
					location.reload();
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

function event_suggestCameraValidate(){
    $(".suggest-camera-form").validate({
        errorElement: "span",
        errorClass: "help-block",
        focusInvalid: !1,
        rules: {
        	cameraTypeOrdinal: "required"
        },
        messages: {
        	cameraTypeOrdinal: "Please select type of camera."
        },
        errorPlacement: function(e, r) {
            e.insertBefore(r)
        }
    });
}