var resolveAccidentModal = document.getElementById('resolve_accident_modal');
var resolveAccidentClose = document.getElementById("resolve_modal_close");

if(resolveAccidentModal != null){

	$('.aAccidentResolve').on("click", function(event){
		resolveAccidentModal.style.display = "block";

		var resolveAccidentImg = document.getElementById("accidentToResolveImage");
		var relatedRow = $(this).closest('tr');
		var accidentMarker = markers[this.value];
		var markerLat = accidentMarker.position.lat();
		var markerLng = accidentMarker.position.lng();
		var requestStaticMap = "https://maps.googleapis.com/maps/api/staticmap?center=" + markerLat + 
							"," + markerLng + "&zoom=13&size=150x150&markers=color:red|label:A|" + markerLat + "," + markerLng + 
							"|&key=" + googleApiKey;
		resolveAccidentImg.src = requestStaticMap;
		$('#accidentToResolveDesc').html(relatedRow.children()[1].innerHTML);
		$('#accidentReportId').val(this.value);
	})
	resolveAccidentClose.onclick = function() {
		resolveAccidentModal.style.display = "none";
	}
	window.onclick = function(event) {
	    if (event.target == resolveAccidentModal) {
	    	resolveAccidentModal.style.display = "none";
	    }
	}
}
