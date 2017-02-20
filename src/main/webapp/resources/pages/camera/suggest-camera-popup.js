var suggestCameraModal = document.getElementById('suggest_camera_modal');
var suggestCameraBtn = document.getElementById("suggest_camera_btn");
var suggestCameraClose = document.getElementById("suggest_camera_close");

if(suggestCameraModal != null){
	suggestCameraBtn.onclick = function() {
		$('.datetimepicker').val(moment().format("DD/MM/YYYY HH:mm"))
		suggestCameraModal.style.display = "block";
	}
	suggestCameraClose.onclick = function() {
		suggestCameraModal.style.display = "none";
	}
	window.onclick = function(event) {
	    if (event.target == suggestCameraModal) {
	    	suggestCameraModal.style.display = "none";
	    }
	}
}
